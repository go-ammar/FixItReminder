package com.example.fixitreminder;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //changed database name to userDB
    private static final String DATABASE_NAME = "user.db";
    private static final String table1 = "user_table";
    private static final int DATABASE_VERSION = 1;

    //needs to have the underscore for some odd reason warna it gives maslay
    public static final String COL_0 = "_ID";
    public static final String COL_1 = "_TIME";
    public static final String COL_2 = "_COMMENT";

    private DatabaseHelper dbHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;


    public DatabaseHelper(@Nullable Context context) {
        //changed second argument here
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ourContext = context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.disableWriteAheadLogging();


//        changing this
//        db.execSQL("CREATE TABLE " + table1 + " (_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "_TIME INTEGER NOT NULL, _COMMENT TEXT NOT NULL)");


        // this is code is better because it uses the variables. upara waala variables nahi use kr rha tha.
        db.execSQL("CREATE TABLE " + table1 + " (" +
                COL_0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_1 + " INTEGER NOT NULL, " +
                COL_2 + " TEXT NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table1);
        onCreate(db);
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        db.disableWriteAheadLogging();
        super.onOpen(db);

    }

    public DatabaseHelper open() throws SQLException {
        dbHelper = new DatabaseHelper(ourContext);
        ourDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }


    public void insertData(int time, String comment) {

//        SQLiteDatabase db = this.getWritableDatabase();
//        db.beginTransaction();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, time);
        contentValues.put(COL_2, comment);
        ourDatabase.insert(table1, null, contentValues);
//        ourDatabase.setTransactionSuccessful();
//        ourDatabase.endTransaction();
        Log.d("humariApp", "Data entered successfully: ");

    }

    public String getData() {
        String[] columns = new String[] {COL_0, COL_1, COL_2};
        Cursor cursor = ourDatabase.query(table1, columns, null, null,
                null, null, null);
        String comment = "";
        int iRowId = cursor.getColumnIndex(COL_0);
        int iRowTime = cursor.getColumnIndex(COL_1);
        int iRowComment = cursor.getColumnIndex(COL_2);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            comment = comment + cursor.getString(iRowComment) + "/n";
        }
        cursor.close();

        return comment;

    }

    public boolean deleteData(String key) {


        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        db.delete(table1, COL_0 + "=" + key, null);

        return true;
    }


}

