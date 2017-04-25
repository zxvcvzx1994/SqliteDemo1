package com.example.kh.sqlitedemo1.View;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.kh.sqlitedemo1.R;
import com.example.kh.sqlitedemo1.View.Module.MyAdapter;
import com.example.kh.sqlitedemo1.View.Module.MySqlite;
import com.example.kh.sqlitedemo1.View.Module.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
      private  Button btninsert;
      private  Button btnupdate;
      private  Button btndelete;
      private MySqlite sqlite;
      private EditText etid;
      private EditText etname;
      private EditText etaddress;
      private EditText etsalary;
  private MyAdapter adapter;
    private     Person person;
    private ArrayList<Person> arrayList ;
    private GridView griddata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sqlite = new MySqlite(MainActivity.this);

        btninsert.setOnClickListener(onclickListener);
        btnupdate.setOnClickListener(onclickListener);
        btndelete.setOnClickListener(onclickListener);
    }


    public  void init() {
        btninsert = (Button) findViewById(R.id.btninsert);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        btndelete = (Button) findViewById(R.id.btndelete);
        etid = (EditText) findViewById(R.id.etid);
        etname = (EditText) findViewById(R.id.etname);
        etaddress = (EditText) findViewById(R.id.etaddress);
        etsalary = (EditText) findViewById(R.id.etsalary);
        griddata = (GridView) findViewById(R.id.griddata);
        griddata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                etid.setText(String.valueOf(arrayList.get(position).getId()));
                etname.setText(arrayList.get(position).getName());
                etaddress.setText(arrayList.get(position).getAddress());
                etsalary.setText(String.valueOf(arrayList.get(position).getSalary()));
            }
        });
    }


    private View.OnClickListener onclickListener  =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btninsert:
                        if (etsalary.getText().toString().trim().length() == 0||etid.getText().toString().trim().length() == 0) {
                            Toast.makeText(MainActivity.this, "salary is null", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        long insert =sqlite.insert(getInt(etid), getValue(etname), getValue(etaddress), getdouble(etsalary));
                        if (insert != -1) {
                            Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                            loadGridView();

                        } else {
                            Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();

                        }
                      //  loadGridView();

                        break;
                    case R.id.btnupdate:

                        if (etsalary.getText().toString().trim().length() == 0||etid.getText().toString().trim().length() == 0) {

                            Toast.makeText(MainActivity.this, "salary is null", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        long update = sqlite.update(getInt(etid), getValue(etname), getValue(etaddress), getdouble(etsalary));
                        Toast.makeText(MainActivity.this, String.valueOf(update), Toast.LENGTH_SHORT).show();
                        if (update != 0) {
                            Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                            loadGridView();


                        } else {
                            Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();

                        }
                        break;
                    case R.id.btndelete:
                        long delete = sqlite.delete(getInt(etid));

                        if (delete == 0) {
                            Toast.makeText(MainActivity.this, "id is null", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (sqlite.delete(getInt(etid)) == 0) {
                            Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                            loadGridView();
                        } else
                            Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                        break;

                }

        }
    };
    public  int getInt(EditText editText){

           return Integer.parseInt(editText.getText().toString().trim()) ;


    }
    public  String getValue(EditText editText){
        return editText.getText().toString().trim();
    }
    public  double getdouble(EditText editText){

           return Double.parseDouble(editText.getText().toString().trim()) ;


    }
    public void loadGridView(){

        arrayList = new ArrayList<Person>();
     Cursor cursor= sqlite.getAllData();
        if(cursor!=null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                int id = Integer.parseInt( cursor.getString(cursor.getColumnIndex(MySqlite.id)));
                String name =cursor.getString(cursor.getColumnIndex(MySqlite.name));
                String address =  cursor.getString(cursor.getColumnIndex(MySqlite.address));
                double salary = Double.parseDouble( cursor.getString(cursor.getColumnIndex(MySqlite.salary)));
               person = new Person(id,name,address,salary);
               // Toast.makeText(this, "id ="+id+"id ="+name+"id ="+address+"id ="+salary, Toast.LENGTH_SHORT).show();
                arrayList.add(person);
                cursor.moveToNext();
            }

             adapter = new MyAdapter(MainActivity.this,R.layout.listmain,arrayList);
            griddata.setAdapter(adapter);
        }


    }


    @Override
    protected void onStart() {
        super.onStart();
        sqlite.opendb();
        loadGridView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sqlite.closedb();
    }
}
