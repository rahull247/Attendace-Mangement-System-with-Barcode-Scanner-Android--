package com.example.rahul.attendance;

import android.app.Activity;
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
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RegisterStudent extends AppCompatActivity {
    private Spinner spinner;
    DatabaseHelper mydb;
    RadioGroup grpbtn;
    RadioButton radiobtn;
    Button submit;
    EditText RegisterForm, RollNo, FullName, CollegeId, MobNo;
    TextView msg;
    String record = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_student);
        //typecast
        RegisterForm = (EditText) findViewById(R.id.RegisterForm);
        RollNo = (EditText) findViewById(R.id.RollNo);
        FullName = (EditText) findViewById(R.id.FullName);
        CollegeId = (EditText) findViewById(R.id.COLLEGEID);
        MobNo = (EditText) findViewById(R.id.MobNo);
        //submit=(Button)findViewById(R.id.Submit);
        grpbtn = (RadioGroup) findViewById(R.id.radiogrp);
        mydb = new DatabaseHelper(this);


        //Spinner
        spinner = findViewById(R.id.Course);
        List<String> Categories = new ArrayList<>();
        Categories.add(0, "Select Class");
        Categories.add("First Year.BSC(Computer Science)");
        Categories.add("Second Year.BSC(Computer Science)");
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        record = "Select Class";
                        break;
                    case 1:
                        record = "First Year BSC(Computer Science)";
                        break;
                    case 2:
                        record = "Second Year BSC(Computer Science)";
                        break;
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void SubmitBtn(View view) {
        int selectedid = grpbtn.getCheckedRadioButtonId();
        radiobtn = (RadioButton) findViewById(selectedid);
        String gender = radiobtn.getText().toString();
        String formno = RegisterForm.getText().toString();
        String rollno = RollNo.getText().toString();
        String fullname = FullName.getText().toString();
        String Classe = record;
        String collegeid = CollegeId.getText().toString();
        String mobileno = MobNo.getText().toString();
        if (formno.length() != 0 && rollno.length() != 0 && fullname.length() != 0 && gender.length() != 0 && Classe.length() != 0 && collegeid.length() != 0 && mobileno.length() != 0) {
            AddData(formno, rollno, fullname, gender, Classe, collegeid, mobileno);
        } else {
            Toast.makeText(RegisterStudent.this, "You Must Fill Value", Toast.LENGTH_LONG).show();
        }

    }


    public void AddData(String formno, String rollno, String fullname, String gender, String Classe, String collegeid, String mobileno) {
        boolean inserData = mydb.insertRegisterForm(formno, rollno, fullname, gender, Classe, collegeid, mobileno);
        if (inserData) {
            Toast.makeText(RegisterStudent.this, "Data Inserted Succesfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(RegisterStudent.this, "Data Not Inserted Succesfully", Toast.LENGTH_LONG).show();
        }
    }

}

