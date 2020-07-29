package com.example.fixitreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    //changed database name to userDB
    private static final String DATABASE_NAME = "user.db";
    private static final String table_name = "user_table";
    private static final int DATABASE_VERSION = 1;

    //needs to have the underscore for some odd reason warna it gives maslay
    public static final String COL_0 = "_ID";
    public static final String COL_1 = "_TIME";
    public static final String COL_2 = "_COMMENT";


    public static final String CREATE_TABLE_FLIGHTS= "CREATE TABLE IF NOT EXISTS "+ table_name +" ("
            +COL_0 +" INTEGER PRIMARY KEY AUTOINCREMENT , "
            +COL_1 +" INTEGER, "
            +COL_2 +" TEXT "
            +");";




    private DatabaseHelper dbHelper;
    private SQLiteDatabase ourDatabase;


    public DatabaseHelper(Context context) {
        //changed second argument here
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.disableWriteAheadLogging();


//        changing this
//        db.execSQL("CREATE TABLE " + table1 + " (_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "_TIME INTEGER NOT NULL, _COMMENT TEXT NOT NULL)");
        db.execSQL(CREATE_TABLE_FLIGHTS);





        // this is code is better because it uses the variables. upara waala variables nahi use kr rha tha.
        // db.execSQL("CREATE TABLE " + table_name + " (" +
        //      COL_0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        //    COL_1 + " INTEGER NOT NULL, " +
        //  COL_2 + " TEXT NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        db.disableWriteAheadLogging();
        super.onOpen(db);

    }






    public void insertData(int time, String comment) {

//        SQLiteDatabase db = this.getWritableDatabase();
//        db.beginTransaction();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, time);
        contentValues.put(COL_2, comment);
        ourDatabase.insert(table_name, null, contentValues);
//        ourDatabase.setTransactionSuccessful();
//        ourDatabase.endTransaction();
        Log.d("humariApp", "Data entered successfully: ");

    }

    public String getData() {
        String[] columns = new String[] {COL_0, COL_1, COL_2};
        Cursor cursor = ourDatabase.query(table_name, columns, null, null,
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

        db.delete(table_name, COL_0 + "=" + key, null);

        return true;
    }


}