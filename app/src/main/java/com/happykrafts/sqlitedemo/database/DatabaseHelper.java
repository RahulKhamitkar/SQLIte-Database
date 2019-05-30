package com.happykrafts.sqlitedemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "Student.db";
    public static String TABLE_NAME = "student_table";
    public static String COL_1 = "ID";
    public static String COL_2 = "NAME";
    public static String COL_3 = "SURNAME";
    public static String COL_4 = "MARKS";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "SURNAME TEXT" +
                ",MARKS TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
                onCreate(db);
    }

    public boolean insertData(String name, String surname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
       long result = db.insert(TABLE_NAME,null,contentValues);

   if (result ==-1)
       return false;
   else
       return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }

    public boolean updateData(String id, String name, String surname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValuesU = new ContentValues();
        contentValuesU.put(COL_2,name);
        contentValuesU.put(COL_3,surname);
        contentValuesU.put(COL_4,marks);
        db.update(TABLE_NAME,contentValuesU,"ID = ?",new String[] { id });
        return true;
    }
}
