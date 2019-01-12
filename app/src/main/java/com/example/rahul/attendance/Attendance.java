package com.example.rahul.attendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Attendance extends AppCompatActivity {
    //private Spinner spinner;
    Button scanbtn,calender;
    TextView tvrollno,tvname,tvgender,tvcourse,tvcollegeid,tvmobileno,tv1,tv2,tv3,tv4,tv5,tv6;
    DatabaseHelper mydb;
    EditText datepicker;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance);

       /* spinner = findViewById(R.id.spinner);
        List<String> Categories = new ArrayList<>();
        Categories.add(0, "Select Class");
        Categories.add("First Year.BSC(Computer Science)");
        Categories.add("Second Year.BSC(Computer Science)");
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);*/
        //all textview cast
        tvrollno= (TextView) findViewById(R.id.tvvrollno);
         tvname= (TextView) findViewById(R.id.tvvfullname);
         tvgender= (TextView) findViewById(R.id.tvvgender);
         tvcourse= (TextView) findViewById(R.id.tvvcourse);
         tvcollegeid= (TextView) findViewById(R.id.tvvcollegeid);
         tvmobileno= (TextView) findViewById(R.id.tvvmobileno);
         tv1= (TextView) findViewById(R.id.tvrollno);
         tv2= (TextView) findViewById(R.id.tvname);
         tv3= (TextView) findViewById(R.id.tvgender);
         tv4= (TextView) findViewById(R.id.tvcourse);
         tv5= (TextView) findViewById(R.id.tvcollegeid);
         tv6= (TextView) findViewById(R.id.tvmobileno);
        scanbtn = (Button) findViewById(R.id.scannerbtn);


        //invisible all text data
        tvrollno.setVisibility(View.INVISIBLE);
        tvname.setVisibility(View.INVISIBLE);
        tvgender.setVisibility(View.INVISIBLE);
        tvcourse.setVisibility(View.INVISIBLE);
        tvcollegeid.setVisibility(View.INVISIBLE);
        tvmobileno.setVisibility(View.INVISIBLE);
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);
        tv4.setVisibility(View.INVISIBLE);
        tv5.setVisibility(View.INVISIBLE);
        tv6.setVisibility(View.INVISIBLE);
        mydb = new DatabaseHelper(this);
        datepicker=(EditText)findViewById(R.id.datepicker);
        calender=(Button)findViewById(R.id.calenderbtn);
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialo=new DatePickerDialog(Attendance.this,android.R.style.Theme_DeviceDefault_Dialog,mDateSetListener,year,month,day);
                dialo.getWindow();
                dialo.show();
            }
        });
    mDateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month=month+1;
            String date=month+"/"+dayOfMonth+"/"+year;
            datepicker.setText(date);
        }
    };

        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(Attendance.this);
                scanIntegrator.initiateScan();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent in) {

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, in);


        if (scanningResult != null) {

            String contents = in.getStringExtra("SCAN_RESULT");
            String format = in.getStringExtra("SCAN_RESULT_FORMAT");


            Cursor res = mydb.getAllData(contents);
            if (res.getCount()<=0)
            {               tvgender.setVisibility(View.VISIBLE);
                            tvgender.setText("Data Not Found");
            }
            else {
                while (res.moveToNext()){

                    String Rollno = res.getString(res.getColumnIndex("Roll_No"));
                    String Name = res.getString(res.getColumnIndex("Full_Name"));
                    String Gender = res.getString(res.getColumnIndex("Gender"));
                    String Course = res.getString(res.getColumnIndex("Class"));
                    String ColegeID = res.getString(res.getColumnIndex("College_Id_No"));
                    String MobNO = res.getString(res.getColumnIndex("Mobile_No"));
                    //showcode.setText("Roll NO:" + Rollno + "\n" + "Name:" + Name + "\n" + "Gender:" + Gender + "\n" + "Course:" + Course + "\n" + "College Id:" + ColegeID + "\n" + "Mobile No:" + MobNO + "\n");
                    //move next position until end of the data
                    tv1.setVisibility(View.VISIBLE);
                    tvrollno.setVisibility(View.VISIBLE);
                    tvrollno.setText(Rollno);

                    tv2.setVisibility(View.VISIBLE);
                    tvname.setVisibility(View.VISIBLE);
                    tvname.setText(Name);

                    tv3.setVisibility(View.VISIBLE);
                    tvgender.setVisibility(View.VISIBLE);
                    tvgender.setText(Gender);

                    tv4.setVisibility(View.VISIBLE);
                    tvcourse.setVisibility(View.VISIBLE);
                    tvcourse.setText(Course);

                    tv5.setVisibility(View.VISIBLE);
                    tvcollegeid.setVisibility(View.VISIBLE);
                    tvcollegeid.setText(ColegeID);

                    tv6.setVisibility(View.VISIBLE);
                    tvmobileno.setVisibility(View.VISIBLE);
                    tvmobileno.setText(MobNO);
                }

            }

            //  showcode.setText("Content:"+contents+"\n"+"Format:"+format+"\n");
        } else {
            tvgender.setText("Data Could not be scanned");
        }

    }


}


