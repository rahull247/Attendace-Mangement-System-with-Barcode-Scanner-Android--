package com.example.rahul.attendance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    DatabaseHelper mydb;
    SQLiteDatabase db;
     private ArrayList<String> alrollno=new ArrayList<String>();
    private ArrayList<String> alname=new ArrayList<String>();
    private ArrayList<String> algender=new ArrayList<String>();
    private ArrayList<String> alclasse=new ArrayList<String>();
    private ArrayList<String> alcollegeid=new ArrayList<String>();
    private ArrayList<String> almobno=new ArrayList<String>();

    public CustomAdapter(Context mContext,ArrayList<String>alrollno,ArrayList<String>alname,ArrayList<String>algender,ArrayList<String>alclasse,ArrayList<String>alcollegeid,ArrayList<String>almobno) {
        this.mContext = mContext;
        this.alrollno=alrollno;
        this.alname=alname;
        this.algender=algender;
        this.alclasse=alclasse;
        this.alcollegeid=alcollegeid;
        this.almobno=almobno;
    }

    @Override
    public int getCount() {
        return alrollno.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final viewHolder holder;
        mydb=new DatabaseHelper(mContext);
        LayoutInflater layoutInflater;
        if(convertView==null){
            layoutInflater=(LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.custom_student_layout,null);
            holder=new viewHolder();
            holder.rollno=(TextView)convertView.findViewById(R.id.tvrollno);
            holder.name=(TextView)convertView.findViewById(R.id.tvname);
            holder.gender=(TextView)convertView.findViewById(R.id.tvgender);
            holder.classe=(TextView)convertView.findViewById(R.id.tvclass);
            holder.collegeid=(TextView)convertView.findViewById(R.id.tvcollegeid);
            holder.mobno=(TextView)convertView.findViewById(R.id.tvmobileno);
            convertView.setTag(holder);
        }
        else{
           holder=(viewHolder)convertView.getTag();
        }
        holder.rollno.setText(alrollno.get(position));
        holder.name.setText(alname.get(position));
        holder.gender.setText(algender.get(position));
        holder.classe.setText(alclasse.get(position));
        holder.collegeid.setText(alcollegeid.get(position));
        holder.mobno.setText(almobno.get(position));
        return convertView;
    }

    public class viewHolder
    {
        TextView rollno;
        TextView name;
        TextView gender;
        TextView classe;
        TextView collegeid;
        TextView mobno;

    }
}
