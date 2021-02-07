package com.joy.abcbank;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<UserData> dataList;
    private Activity context;
    private RoomDB database;

    public MainAdapter(Activity context,List<UserData> dataList){
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_data_holder,parent,false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserData data = dataList.get(position);

        database = RoomDB.getInstance(context);

        holder.userName.setText("Name : "+ data.getUserName());
        holder.userAccNo.setText("Account No : " + data.getUserAccountNo());
        holder.userBal.setText("Current Balance : $"+ data.getUserCurrentBalance());

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context,UserDetailActivity.class);
            intent.putExtra("user_id",String.valueOf(data.getID()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        TextView userName;
        TextView userAccNo;
        TextView userBal;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.circularImageView);
            userName = itemView.findViewById(R.id.textViewName);
            userAccNo = itemView.findViewById(R.id.textViewAccNo);
            userBal = itemView.findViewById(R.id.textViewAmount);
            cardView = itemView.findViewById(R.id.cardViewUser);
        }
    }
}
