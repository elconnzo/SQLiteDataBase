package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter {
    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "Login.db";
    //Variable to hold the database instance
    private static SQLiteDatabase db;
    //Database open/upgrade helper
    private DataBaseHelper dbHelper;

    //Constructor
    public LoginDataBaseAdapter(Context context){
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Method to open the database
    public LoginDataBaseAdapter open() throws SQLException{
        db = dbHelper.getWritableDatabase();
        return this;
    }

    //Method to close the database
    public void close(){
        db.close();
    }

    //Method returns an Instance of the Database
    public SQLiteDatabase getDatabaseInstance(){
        return db;
    }

    public String getSingleEntry(String un){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("User", null, "userName=?",
                new String[]{un}, null, null, null);
        if(cursor.getCount() < 1) // Username Doesn't Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String getPassword = cursor.getString(cursor.getColumnIndex("userPassword"));
        cursor.close();
        return getPassword;
    }

    public void insertEntry(String un, String pw){
        ContentValues newValues = new ContentValues();
        //Assign new values to each row
        newValues.put("userName", un);
        newValues.put("userPassword", pw);

        //Insert the row into your table
        db.insert("User", null, newValues);
    }
}
