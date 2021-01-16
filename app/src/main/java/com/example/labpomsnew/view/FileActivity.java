package com.example.labpomsnew.view;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.labpomsnew.R;
import com.example.labpomsnew.viewmodel.FileProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileActivity extends AppCompatActivity {

    TextView fileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        Button buttonOutput = findViewById(R.id.buttonOutputFile);
        buttonOutput.setOnClickListener(v -> loadFileContext());
        Button buttonClear = findViewById(R.id.buttonClearFile);
        buttonClear.setOnClickListener(v -> clearFile());
        fileContent = findViewById(R.id.textViewFile);

    }

    private void loadFileContext()
    {
        fileContent.setText(FileProcessor.loadFileContext());
    }

    private void clearFile()
    {
        FileProcessor.clearFile();
    }


}