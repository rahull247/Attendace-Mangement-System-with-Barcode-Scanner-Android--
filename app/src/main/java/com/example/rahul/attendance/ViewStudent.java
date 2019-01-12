package com.example.rahul.attendance;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewStudent extends AppCompatActivity {
Button showInfo;
DatabaseHelper mydb;
EditText Data;
TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_student);
        info=(TextView)findViewById(R.id.textView4);
        Data=(EditText)findViewById(R.id.RegisterForm);
        showInfo=(Button)findViewById(R.id.search);
        mydb=new DatabaseHelper(this);
        info.setVisibility(View.INVISIBLE);
        showInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Values=Data.getText().toString();

                Cursor res = mydb.DisplayInfo(Values);

                Log.d("viewStudent", "onClick: res length: "+res.getCount());
                if(res.getCount() == 0)
            {
                info.setVisibility(View.VISIBLE);
                info.setText("XX Data not found XX");

                Log.d("viewStudent", "onClick: xx data not found");
            }
                else
            {
                   /* do
                    {

                        String Rollno=res.getString(res.getColumnIndex("Roll_No"));
                        String Name=res.getString(res.getColumnIndex("Full_Name"));
                        String Gender=res.getString(res.getColumnIndex("Gender"));
                        String Course=res.getString(res.getColumnIndex("Class"));
                        String ColegeID=res.getString(res.getColumnIndex("College_Id_No"));
                        String MobNO=res.getString(res.getColumnIndex("Mobile_No"));
                        info.setVisibility(View.VISIBLE);
                        info.setText("Roll NO:"+Rollno+"\n"+"Name:"+Name+"\n"+"Gender:"+Gender+"\n"+"Course:"+Course+"\n"+"College Id:"+ColegeID+"\n"+"Mobile No:"+MobNO+"\n");
                        //move next position until end of the data
                    }
                    while(res.moveToNext());
                    res.moveToFirst();*/


                while(res.moveToNext())
                {

                    String Rollno=res.getString(res.getColumnIndex("Roll_No"));
                    String Name=res.getString(res.getColumnIndex("Full_Name"));
                    String Gender=res.getString(res.getColumnIndex("Gender"));
                    String Course=res.getString(res.getColumnIndex("Class"));
                    String ColegeID=res.getString(res.getColumnIndex("College_Id_No"));
                    String MobNO=res.getString(res.getColumnIndex("Mobile_No"));
                    info.setVisibility(View.VISIBLE);
                    info.setText("Roll NO:"+Rollno+"\n"+"Name:"+Name+"\n"+"Gender:"+Gender+"\n"+"Course:"+Course+"\n"+"College Id:"+ColegeID+"\n"+"Mobile No:"+MobNO+"\n");
                    //move next position until end of the data
                }

            }



        }
        });
    }
}
