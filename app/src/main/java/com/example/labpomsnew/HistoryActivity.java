package com.example.labpomsnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;



public class HistoryActivity extends AppCompatActivity {

    HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent = getIntent();

        adapter = new HistoryAdapter();
        if(intent!=null && intent.hasExtra(MainActivity.HISTORY_KEY)){
            adapter.initialize(intent.getParcelableArrayListExtra(MainActivity.HISTORY_KEY));
        }

        RecyclerView recyclerView = findViewById(R.id.histryListSecondDono);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}