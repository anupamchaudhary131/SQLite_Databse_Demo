package com.example.sqlite_databse_example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ContactsDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CONTACT = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NO = "phone_no";


    public MyDBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTACT + "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                               + KEY_NAME + " TEXT ," + KEY_PHONE_NO + " TEXT" +")" );



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);

        onCreate(sqLiteDatabase);

    }


    // Method to add contacts in database.

    public void addContact(String name, String phone_no){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PHONE_NO, phone_no);

        sqLiteDatabase.insert(TABLE_CONTACT, null, values);


    }


    // Method to fetch data from database.

    public ArrayList<ContactModel> fetchContact(){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_CONTACT, null);

        ArrayList<ContactModel> arrContacts = new ArrayList<>();

        while(cursor.moveToNext()){

            ContactModel model = new ContactModel();
            model.id = cursor.getInt(0);
            model.name = cursor.getString(1);
            model.phone_no = cursor.getString(2);

            arrContacts.add(model);
        }

        return arrContacts;
    }


    // Method to Update data from database

    public void  updateContact(ContactModel contactModel){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PHONE_NO, contactModel.phone_no);

        sqLiteDatabase.update(TABLE_CONTACT, values, KEY_ID +" = "+ contactModel.id, null);

    }



    // Method to delete data from database
    
    public void deleteContact(int id){

        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(TABLE_CONTACT, KEY_ID+ " = ? ", new String[]{String.valueOf(id)});

    }

}
