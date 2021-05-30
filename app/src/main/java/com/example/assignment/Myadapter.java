package com.example.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    Context ctx;
    private ArrayList<PersonModel> mDataList;

    public Myadapter(Context ctx, ArrayList<PersonModel> arrayList) {
        this.ctx = ctx;
        this.mDataList=arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater
                .from(ctx)
                .inflate(R.layout.recyler_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.myEmail.setText(String.valueOf(mDataList.get(position).getEmail()));
        holder.myNumber.setText(String.valueOf(mDataList.get(position).getNumber()));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

       AppCompatTextView myEmail,myNumber;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myEmail=itemView.findViewById(R.id.email_Tv);
            myNumber=itemView.findViewById(R.id.number_Tv);

        }
    }
}
