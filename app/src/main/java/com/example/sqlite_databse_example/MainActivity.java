package com.example.sqlite_databse_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHelper dbHelper = new MyDBHelper( this);

        dbHelper.addContact("RAMAN", "9870797329");
        dbHelper.addContact("NAMAN", "9870797329");
        dbHelper.addContact("RAM", "9870797329");
        dbHelper.addContact("AMAN", "9870797329");
        dbHelper.addContact("RAMA", "9870797329");
        dbHelper.addContact("HARDEEP", "9870797329");
        dbHelper.addContact("RANDEEP", "9870797329");

        ContactModel model = new ContactModel();
        model.id = 1;
        model.name = "RAMAN";
        model.phone_no = "1234567890";

        dbHelper.updateContact(model);


        dbHelper.deleteContact(2);


        ArrayList<ContactModel> arrContacts = dbHelper.fetchContact();

        for (int i = 0; i < arrContacts.size(); i++) {
            Log.d("CONTACT_INFO", "name: "+ arrContacts.get(i).name +", Phone No: "+ arrContacts.get(i).phone_no);
        }

    }
}