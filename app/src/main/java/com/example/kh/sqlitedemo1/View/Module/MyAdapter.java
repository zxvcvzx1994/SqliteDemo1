package com.example.kh.sqlitedemo1.View.Module;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kh.sqlitedemo1.R;

import java.util.ArrayList;

/**
 * Created by kh on 4/24/2017.
 */

public class MyAdapter extends ArrayAdapter<Person>{
    private Context context;
    private int resource;
    private ArrayList<Person> arrayList =new ArrayList<Person>();
    private LayoutInflater inflater;
    public MyAdapter( Context context,  int resource,  ArrayList<Person> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayList = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v  = convertView;
        ViewHoldler viewHoldler;
        if(v==null){
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
          v=  inflater.inflate(R.layout.listmain,null);
            viewHoldler = new ViewHoldler();
            viewHoldler.txtid = (TextView) v.findViewById(R.id.txtid);
            viewHoldler.txtname = (TextView) v.findViewById(R.id.txtname);
            viewHoldler.txtaddress = (TextView) v.findViewById(R.id.txtaddress);
            viewHoldler.txtsalary = (TextView) v.findViewById(R.id.txtsalary);
            v.setTag(viewHoldler);
        } else viewHoldler = (ViewHoldler) convertView.getTag();

        viewHoldler.txtid.setText(String.valueOf(arrayList.get(position).getId()));
        viewHoldler.txtname.setText(arrayList.get(position).getName());
        viewHoldler.txtaddress.setText(arrayList.get(position).getAddress());
        viewHoldler.txtsalary.setText(String.valueOf(arrayList.get(position).getSalary()));

        return v;
    }
    private static class ViewHoldler{
       private TextView txtid;
        private TextView txtname;
        private TextView txtaddress;
        private TextView txtsalary;
    }
}
