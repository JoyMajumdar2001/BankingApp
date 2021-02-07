package com.joy.abcbank;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<UserData> dataList;
    private Activity context;
    private RoomDB database;
    private int senderID;

    public ItemAdapter(Activity context,List<UserData> dataList,int ID){
        this.context = context;
        this.dataList = dataList;
        this.senderID = ID;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyler_item_user,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserData data = dataList.get(position);

        database = RoomDB.getInstance(context);

        holder.nameTV.setText(data.getUserName());

        holder.linearLayout.setOnClickListener(v -> {
            Intent i = new Intent(context,TransferActivity.class);
            i.putExtra("to_id",String.valueOf(data.getID()));
            i.putExtra("from_id",String.valueOf(senderID));
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTV;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.name_bottom_TV);
            linearLayout = itemView.findViewById(R.id.linearLayClick);
        }
    }
}
