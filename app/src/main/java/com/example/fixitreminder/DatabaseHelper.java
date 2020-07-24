package com.example.fixitreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "user.db";
    public static final String table1 = "user_table";
    public static final String COL_0 = "TIME";
    public static final String COL_1 = "COMMENT";


    public DatabaseHelper(@Nullable Context context) {
        super(context, table1 , null, 1);


    }


    @Override
    public void onCreate(SQLiteDatabase db){
        db.disableWriteAheadLogging();
        db.execSQL("CREATE TABLE " + table1 + "(C_NO INTEGER PRIMARY KEY AUTOINCREMENT, TIME INTEGER, COMMENT TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table1);
        onCreate(db);
    }


    @Override
    public void onOpen(SQLiteDatabase db){
        db.disableWriteAheadLogging();
        super.onOpen(db);

    }




    public boolean insertData(int time, String comment){

        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_0, time);
        contentValues.put(COL_1, comment);
        db.insert(table1, null, contentValues);
        db.setTransactionSuccessful();
        db.endTransaction();
        Log.d("humariApp", "Data entered successfully: ");
        return true;

    }


    public boolean deleteData(String key){


        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        db.delete(table1, COL_0 + "=" + key, null);

        return true;
    }





}

