package com.joy.abcbank;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.shreyaspatil.MaterialDialog.MaterialDialog;

public class TransferActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getStringExtra("to_id") == null || getIntent().getStringExtra("from_id") == null){
            Toast.makeText(this, "Can not find user's details", Toast.LENGTH_SHORT).show();
            finish();
        }


        setContentView(R.layout.activity_transfer);
        TextView rcvName,rcvAccNo;
        TextInputEditText inputEditText;
        MaterialButton materialButton;

        rcvName = findViewById(R.id.rcvName);
        rcvAccNo = findViewById(R.id.rcvAccNo);
        inputEditText = findViewById(R.id.edtText);
        materialButton = findViewById(R.id.transBtn);

        RoomDB database = RoomDB.getInstance(this);
        rcvName.setText("Account Holder : " + database.mainDao().getUserInfo(
                Integer.parseInt(getIntent().getStringExtra("to_id"))).getUserName());
        rcvAccNo.setText("Account No : " + database.mainDao().getUserInfo(
                Integer.parseInt(getIntent().getStringExtra("to_id"))).getUserAccountNo());

        Long myBal = database.mainDao().getUserInfo(
                Integer.parseInt(getIntent().getStringExtra("from_id"))).getUserCurrentBalance();
        Long rcvBal = database.mainDao().getUserInfo(
                Integer.parseInt(getIntent().getStringExtra("to_id"))).getUserCurrentBalance();

        materialButton.setOnClickListener(v -> {
            if (inputEditText.getText() == null){
                Toast.makeText(TransferActivity.this, "Enter an amount", Toast.LENGTH_SHORT).show();
            }else {
                if (Long.parseLong(inputEditText.getText().toString()) > myBal){
                    Toast.makeText(TransferActivity.this, "You don't have enough balance", Toast.LENGTH_SHORT).show();
                }else {
                    Long deduct = Long.parseLong(inputEditText.getText().toString());
                    database.mainDao().updateBalance(Integer.parseInt(getIntent().getStringExtra("from_id")),
                            myBal - deduct);
                    database.mainDao().updateBalance(Integer.parseInt(getIntent().getStringExtra("to_id")),
                            rcvBal + deduct);
                    materialButton.setVisibility(View.GONE);
                    inputEditText.setText("");

                    MaterialDialog materialDialog = new MaterialDialog.Builder(this)
                            .setTitle("Money Transfer Successful")
                            .setMessage("$" + deduct + " has been transferred to " +
                                    database.mainDao().getUserInfo(
                                            Integer.parseInt(getIntent().getStringExtra("to_id"))).getUserName())
                            .setCancelable(false)
                            .setAnimation(R.raw.done2)
                            .setPositiveButton("OK",R.drawable.ic_baseline_done_24, (dialogInterface, which) -> finish())
                            .build();
                    materialDialog.show();

                }
            }
        });
    }
}