package com.example.kh.sqlitedemo1.View.Module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kh on 4/24/2017.
 */

public  class MySqlite extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "dbdemo";
    private static final int DATABASE_VERSION = 1;
    public static   String table = "person";
    public static  String id = "id";
    public static  String name = "name";
    public  static String address = "address";
    public static  String salary = "salary";
    private SQLiteDatabase sqLiteDatabase;
    private static final String CREATE_TABLE = "CREATE TABLE "+table+"("+id+" integer primary key, "
                                                                        +name+" text not null,"
                                                                        +address+" text not null,"
                                                                        +salary+" REAL not null"

                                                                        +");";

    public MySqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_TABLE);
    }
    public void opendb(){
        sqLiteDatabase = getWritableDatabase();
    }
    public void closedb(){
        if(sqLiteDatabase!=null && sqLiteDatabase.isOpen())
            sqLiteDatabase.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(int id, String name, String address, double salary){
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.id, id);
        contentValues.put(this.name, name);
        contentValues.put(this.address, address);
        contentValues.put(this.salary, salary);

       return sqLiteDatabase.insert(table,null,contentValues);
    }
    public long update(int id, String name, String address, double salary){
        ContentValues contentValues = new ContentValues();
       // contentValues.put(this.id, id);
        contentValues.put(this.name, name);
        contentValues.put(this.address, address);
        contentValues.put(this.salary, salary);
        String where = this.id +"="+ id;

       return sqLiteDatabase.update(table,contentValues,where,null);
    }
    public long delete(int id){
        ContentValues contentValues = new ContentValues();
       // contentValues.put(this.id, id);
//        contentValues.put(this.name, name);
//        contentValues.put(this.address, address);
//        contentValues.put(this.salary, salary);
        String where = this.id +"="+ id;

       return sqLiteDatabase.delete(table,where,null);
    }

    public Cursor getAllData(){
        try {
            return sqLiteDatabase.rawQuery("select * from " + table, null);
        }catch (Exception e){
            return null;
        }
    }


}
