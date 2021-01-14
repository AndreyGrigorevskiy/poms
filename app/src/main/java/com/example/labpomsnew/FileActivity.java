package com.example.labpomsnew;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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
        String result = "";
        StringBuilder stringBuilder = new StringBuilder();
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myfile = new File(downloadsDir, "logFile.txt");
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(myfile));

            String line = null;
            String ls =  System.getProperty("line.separator");
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        result = stringBuilder.toString();
        fileContent.setText(result);
    }

    private void clearFile()
    {
        File dowloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(dowloadDir, "logFile.txt");
        try
        {
            PrintWriter writer = new PrintWriter(myFile);
            writer.print("");
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}