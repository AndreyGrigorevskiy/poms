package com.example.labpomsnew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DataBaseActivity extends AppCompatActivity {

    TextView dbContent;
    DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        dbContent = findViewById(R.id.textViewDBContent);
        dataBaseManager = new DataBaseManager(this);
        dataBaseManager.open();

        Button showDB = findViewById(R.id.buttonShowDB);
        showDB.setOnClickListener(v -> showDateBase());

        Button clearDB = findViewById(R.id.buttonClearDB);
        clearDB.setOnClickListener(v -> clearDateBase());

        Button clearScreen = findViewById(R.id.buttonClearTextContentDB);
        clearScreen.setOnClickListener(v -> clearScreenDateBase());


    }

    private void showDateBase() {

        dbContent.setText(dataBaseManager.getAllAsText());

    }

    private void clearDateBase()
    {

        dataBaseManager.deleteAll();

    }

    private void clearScreenDateBase()
    {

        dbContent.setText("");

    }


}