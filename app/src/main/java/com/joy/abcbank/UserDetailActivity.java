package com.joy.abcbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class UserDetailActivity extends AppCompatActivity {

    RoomDB roomDB;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        if (getIntent().getStringExtra("user_id") == null){
            Toast.makeText(this, "Can not find user's details", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            initVoid();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initVoid();
    }

    @SuppressLint("SetTextI18n")
    void initVoid(){
        TextView nametv,accnotv,emailtv,mobtv,baltv;
        MaterialButton materialButton;
        nametv = findViewById(R.id.nameET);
        accnotv = findViewById(R.id.accNoET);
        emailtv = findViewById(R.id.emailET);
        mobtv = findViewById(R.id.phET);
        baltv = findViewById(R.id.balET);
        materialButton = findViewById(R.id.transferBtn);

        roomDB = RoomDB.getInstance(this);

        UserData userData = roomDB.mainDao()
                .getUserInfo(Integer.parseInt(getIntent().getStringExtra("user_id")));

        nametv.setText("Account Holder : "+userData.getUserName());
        accnotv.setText("Account No : "+userData.getUserAccountNo());
        emailtv.setText("Email : "+userData.getUserEmail());
        mobtv.setText("Phone No : "+userData.getUserMobNo());
        baltv.setText("Current Balance : $"+userData.getUserCurrentBalance());
        List<UserData> sortedList = roomDB.mainDao()
                .getAllUserExceptMe(Integer.parseInt(getIntent().getStringExtra("user_id")));


        materialButton.setOnClickListener(v -> {

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UserDetailActivity.this);
            @SuppressLint("InflateParams")
            View vw = getLayoutInflater().inflate(R.layout.bottom_sheet_layout,null);
            bottomSheetDialog.setContentView(vw);

            RecyclerView recyclerView = vw.findViewById(R.id.bottomRecyclerView);
            ItemAdapter itemAdapter = new ItemAdapter(UserDetailActivity.this,
                    sortedList,Integer.parseInt(getIntent().getStringExtra("user_id")));
            recyclerView.setLayoutManager(new LinearLayoutManager(UserDetailActivity.this));
            recyclerView.setAdapter(itemAdapter);
            itemAdapter.notifyDataSetChanged();
            bottomSheetDialog.show();
        });
    }
}