package com.joy.abcbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewAllUser;
    List<UserData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAll();

    }

    void initAll(){
        recyclerViewAllUser = findViewById(R.id.recyclerViewAllUser);

        database = RoomDB.getInstance(this);

        if(database.mainDao().getRowCount() <= 0){
            insertAllUser();
        }

        dataList = database.mainDao().getAllUser();

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerViewAllUser.setLayoutManager(linearLayoutManager);

        adapter = new MainAdapter(MainActivity.this,dataList);

        recyclerViewAllUser.setAdapter(adapter);
    }

    private void insertAllUser() {
        database.mainDao().insert(new UserData("Joy Majumdar",
                "joymajumdar15828@gmail.com",
                "8116832026",
                "1235430973454",
                (long) 75000));
        database.mainDao().insert(new UserData("Dip Roy",
                "diproy435@gmail.com",
                "81166598",
                "9864562348975",
                (long) 47500));
        database.mainDao().insert(new UserData("Souvik Laha",
                "souvik.laha666@gmail.com",
                "9654368734",
                "9823465437658",
                (long) 19743));
        database.mainDao().insert(new UserData("Suman Sarkar",
                "suman009@gmail.com",
                "9876123765",
                "3454543336788",
                (long) 123300));
        database.mainDao().insert(new UserData("Ajoy Sarkar",
                "ajdj4332@gmail.com",
                "9876541234",
                "1234566669875",
                (long) 7030));
        database.mainDao().insert(new UserData("Shuvo Saha",
                "sahashuvo@gmail.com",
                "6202658754",
                "6543398733456",
                (long) 51116));
        database.mainDao().insert(new UserData("Sriparna Das",
                "dasasriparna15828@gmail.com",
                "9976345909",
                "5478434434500",
                (long) 36542));
        database.mainDao().insert(new UserData("Soumo Talukdar",
                "smtluk@gmail.com",
                "9879877899",
                "6789540097534",
                (long) 16800));
        database.mainDao().insert(new UserData("Jue Roy",
                "jueroy0987@gmail.com",
                "9967534909",
                "1237650000987",
                (long) 29954));
        database.mainDao().insert(new UserData("Puja Ghosh",
                "ghoshpuja0000@gmail.com",
                "9870984321",
                "2235568790096",
                (long) 1760));
        database.mainDao().insert(new UserData("Amit Paul",
                "ap.008@gmail.com",
                "6996695409",
                "9998654400969",
                (long) 68010));
    }

    @Override
    protected void onStart() {
        super.onStart();
        initAll();
    }
}