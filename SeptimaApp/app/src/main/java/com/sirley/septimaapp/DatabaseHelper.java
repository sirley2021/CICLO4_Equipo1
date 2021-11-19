package com.sirley.septimaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String TABLE_NAME="TABLA1";
    public final static String DATABASE_NAME="DB_PRUEBA";
    public final static String COL_1="ID";
    public final static String COL_2="NOMBRE";
    public final static String COL_3="APELLIDO";
    public final static String COL_4="TELEFONO";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE TEXT,APELLIDO TEXT,TELEFONO INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData (String nombre,String apellido,String telefono)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL_2,nombre);
        cv.put(COL_3,apellido);
        cv.put(COL_4,telefono);
        long result=db.insert(TABLE_NAME,null,cv);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT *FROM "+TABLE_NAME+" WHERE ID='"+id+"'",null);
        return cursor;
    }

    public boolean updateData(String id, String nombre, String apellido,String telefono)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL_2,nombre);
        cv.put(COL_3,apellido);
        cv.put(COL_4,telefono);
        long result=db.update(TABLE_NAME,cv,"ID=?",new String[]{id});
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public boolean deleteData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.delete(TABLE_NAME,"ID=?",new String[]{id});
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
