package com.example.rahul.attendance;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class StudentCGPA extends AppCompatActivity {
Spinner spinner;

Button seachbtn;
DatabaseHelper mydb;
ListView lv;
private ArrayList<String> rollno=new ArrayList<String>();
private ArrayList<String>name=new ArrayList<String>();
private ArrayList<String>gender=new ArrayList<String>();
private ArrayList<String>classe=new ArrayList<String>();
private ArrayList<String>collegeid=new ArrayList<String>();
private ArrayList<String>mobno=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_cgp);
        mydb=new DatabaseHelper(this);
        lv=(ListView)findViewById(R.id.listviewid);
        //Course Spinner
        spinner = findViewById(R.id.spinner2);
        List<String> Categories = new ArrayList<>();
        Categories.add(0, "Select Class");
        Categories.add("First Year BSC(Computer Science)");
        Categories.add("Second Year BSC(Computer Science)");
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);



        }
    public void Search(View view) {

        Displaylistview();
    }

        private void Displaylistview(){
        Cursor res=mydb.getAllStuds();
        rollno.clear();
        name.clear();
        gender.clear();
        classe.clear();
        collegeid.clear();
        mobno.clear();
        if(res.moveToFirst()){
            do {
                rollno.add(res.getString(res.getColumnIndex("Roll_No")));
                name.add(res.getString(res.getColumnIndex("Full_Name")));
                gender.add(res.getString(res.getColumnIndex("Gender")));
                classe.add(res.getString(res.getColumnIndex("Class")));
                collegeid.add(res.getString(res.getColumnIndex("College_Id_No")));
                mobno.add(res.getString(res.getColumnIndex("Mobile_No")));
            }while (res.moveToNext());
            }
            CustomAdapter adapter=new CustomAdapter(StudentCGPA.this,rollno,name,gender,classe,collegeid,mobno);
                    lv.setAdapter(adapter);

    }
}

