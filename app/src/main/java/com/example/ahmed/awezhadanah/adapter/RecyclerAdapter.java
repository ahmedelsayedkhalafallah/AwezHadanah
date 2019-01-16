package com.example.ahmed.awezhadanah.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ahmed.awezhadanah.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ahmed on 24/05/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{
    private List<order> morder;
    FirebaseDatabase fdb ;
    DatabaseReference dbr;


    public RecyclerAdapter(List<order> morder)
    {

        this.morder = morder;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {

        order p = morder.get(position);
        holder.age.setText(p.getAge());
        holder.comment.setText(p.getComment());
        holder.date.setText("Order date :"+p.getDate());
        holder.id.setText(p.getId());
        holder.name.setText(p.getName());
        holder.phone.setText(p.getPhone());
        holder.time.setText("Order time :"+p.getTime());
        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbr = fdb.getInstance().getReference();

                dbr.child("done").child(holder.id.getText().toString()).child("name").setValue(holder.name.getText().toString());
                dbr.child("done").child(holder.id.getText().toString()).child("age").setValue(holder.age.getText().toString());
                dbr.child("done").child(holder.id.getText().toString()).child("phone").setValue(holder.phone.getText().toString());
                dbr.child("done").child(holder.id.getText().toString()).child("comment").setValue(holder.comment.getText().toString());
                dbr.child("done").child(holder.id.getText().toString()).child("id").setValue(holder.id.getText().toString());
                dbr.child("done").child(holder.id.getText().toString()).child("date").setValue(holder.date.getText().toString());
                dbr.child("done").child(holder.id.getText().toString()).child("time").setValue(holder.time.getText().toString());
                dbr.child("done").child(holder.id.getText().toString()).child("ddate").setValue(getCurrentDate());
                dbr.child("done").child(holder.id.getText().toString()).child("dtime").setValue(getCurrentTime());
                dbr.child("binding").child(holder.id.getText().toString()).setValue(null);
            }


        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbr = fdb.getInstance().getReference();

                dbr.child("cancelled").child(holder.id.getText().toString()).child("name").setValue(holder.name.getText().toString());
                dbr.child("cancelled").child(holder.id.getText().toString()).child("age").setValue(holder.age.getText().toString());
                dbr.child("cancelled").child(holder.id.getText().toString()).child("phone").setValue(holder.phone.getText().toString());
                dbr.child("cancelled").child(holder.id.getText().toString()).child("comment").setValue(holder.comment.getText().toString());
                dbr.child("cancelled").child(holder.id.getText().toString()).child("id").setValue(holder.id.getText().toString());
                dbr.child("cancelled").child(holder.id.getText().toString()).child("date").setValue(holder.date.getText().toString());
                dbr.child("cancelled").child(holder.id.getText().toString()).child("time").setValue(holder.time.getText().toString());
                dbr.child("cancelled").child(holder.id.getText().toString()).child("ddate").setValue(getCurrentDate());
                dbr.child("cancelled").child(holder.id.getText().toString()).child("dtime").setValue(getCurrentTime());
                dbr.child("binding").child(holder.id.getText().toString()).setValue(null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return morder.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public TextView age,comment,date,id,name,phone,time;
        public Button done,cancel;

        public RecyclerViewHolder(View view)
        {

            super(view);
            age = view.findViewById(R.id.age);
            comment = view.findViewById(R.id.comment);
            date = view.findViewById(R.id.date);
            id = view.findViewById(R.id.id);
            name = view.findViewById(R.id.name);
            phone = view.findViewById(R.id.phone);
            time = view.findViewById(R.id.time);
            done = view.findViewById(R.id.done);
            cancel = view.findViewById(R.id.cancel);
        }
    }
    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }
    public String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }

}
