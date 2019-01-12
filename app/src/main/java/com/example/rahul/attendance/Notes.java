package com.example.rahul.attendance;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Notes extends AppCompatActivity {
    EditText title,body;
    Button btn;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);

        title= (EditText) findViewById(R.id.noteTitle);
        body = (EditText) findViewById(R.id.noteBody);

        mydb = new DatabaseHelper(this);



    }

    public void SubmitBtn(View view) {

        String title1 =title.getText().toString();
        String body1 = body.getText().toString();
        if (title1.length() != 0 && body1.length() != 0 ) {
            AddData(title1, body1);
        } else {
            Toast.makeText(Notes.this, "You Must Fill Value", Toast.LENGTH_LONG).show();
        }

    }


    public void AddData(String title1, String body1) {
        boolean inserData = mydb.saveNotes(title1,body1);
        if (inserData) {
            Toast.makeText(Notes.this, "Data Inserted Succesfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Notes.this, "Data Not Inserted Succesfully", Toast.LENGTH_LONG).show();
        }
    }

}

