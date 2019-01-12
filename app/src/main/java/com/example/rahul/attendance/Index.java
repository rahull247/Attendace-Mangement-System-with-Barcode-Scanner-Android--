package com.example.rahul.attendance;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

    }

    public void Register(View view) {
        Intent RF=new Intent(this,RegisterStudent.class);
        startActivity(RF);
    }

    public void Note(View view) {
        Intent NT=new Intent(this,Notes.class);
        startActivity(NT);
    }

    public void Attend(View view) {
        Intent AT=new Intent(this,Attendance.class);
        startActivity(AT);
    }

    public void ViewStud(View view) {
        Intent VS=new Intent(this,ViewStudent.class);
        startActivity(VS);
    }

    public void CGPA(View view) {
        Intent SC=new Intent(this,StudentCGPA.class);
        startActivity(SC);
    }
}
