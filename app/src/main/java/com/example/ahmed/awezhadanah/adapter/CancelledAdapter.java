package com.example.ahmed.awezhadanah.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmed.awezhadanah.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CancelledAdapter extends RecyclerView.Adapter<CancelledAdapter.RecyclerViewHolder> {
    private List<order> morder;



    public CancelledAdapter(List<order> morder)
    {

        this.morder = morder;
    }

    @NonNull
    @Override
    public CancelledAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cancelled_item,parent,false);
        return new CancelledAdapter.RecyclerViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final CancelledAdapter.RecyclerViewHolder holder, int position) {

        order p = morder.get(position);
        holder.age.setText(p.getAge());
        holder.comment.setText(p.getComment());
        holder.date.setText("Order date :"+p.getDate());
        holder.id.setText(p.getId());
        holder.name.setText(p.getName());
        holder.phone.setText(p.getPhone());
        holder.time.setText("Order time :"+p.getTime());
        holder.ddate.setText("Done date :"+p.getDdate());
        holder.dtime.setText("Done time :"+p.getDtime());

    }

    @Override
    public int getItemCount() {
        return morder.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public TextView age,comment,date,id,name,phone,time,ddate,dtime;

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
            ddate = view.findViewById(R.id.ddate);
            dtime = view.findViewById(R.id.dtime);

        }
    }

}
