package com.example.labpomsnew;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ServicesAndIntentionsActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PENDING_INTENT_KEY = "pending_intent";
    public static final String COUNTER_ANSWER_KEY = "pending_intent";

    private final int MY_PERMISSION_STORAGE_READ = 1;
    private final int MY_PERMISSION_STORAGE_WRITE = 2;


    private static final int COUNTER_SERVICE = 1;

    public static final int COUNTER_START = 1;
    public static final int COUNTER_ANSWER = 2;
    public static final int COUNTER_FINISH = 3;

    EditText browserLink;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_and_intentions);

        Button start = findViewById(R.id.buttonStart);
        Button stop = findViewById(R.id.buttonStop);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);

        browserLink = findViewById(R.id.editTextURL);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(10);
        Intent intent = getIntent();
        if(intent!=null)
        {
            browserLink.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        }

    }

    public void startService()
    {

        PendingIntent pendingIntent = createPendingResult(COUNTER_SERVICE,new Intent(),0);

        Intent intent = new Intent(this, CounterService.class);
        intent.putExtra(PENDING_INTENT_KEY, pendingIntent);

        startService(intent);

    }

    private void checkService()
    {
        int readStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int writeStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(readStatus == PackageManager.PERMISSION_GRANTED && writeStatus == PackageManager.PERMISSION_GRANTED)
        {
            startService();
        }
        else
        {
            if(readStatus != PackageManager.PERMISSION_GRANTED )
            {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_STORAGE_READ);
            }
            if (writeStatus != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSION_STORAGE_WRITE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean readF = false;
        boolean writeF = false;
        switch (requestCode)
        {
            case MY_PERMISSION_STORAGE_READ:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    readF = true;
                }
                else
                {
                    Toast.makeText(this, "Give permission", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case  MY_PERMISSION_STORAGE_WRITE:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    writeF = true;
                }
                else
                {
                    Toast.makeText(this, "Give permission", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
        if (readF && writeF)
        {
            startService();
        }
        return;
    }

    public void stopService()
    {
        Intent intent = new Intent(this,CounterService.class);
        stopService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == COUNTER_SERVICE) {
            switch (resultCode)
            {
                case COUNTER_START:
                    Toast.makeText(this,"Service started", Toast.LENGTH_SHORT).show();
                    break;
                case COUNTER_ANSWER:
                    int counter = data.getIntExtra(COUNTER_ANSWER_KEY,0);
                    progressBar.setProgress(counter);
                    writeToFile("Counter = "+String.valueOf(counter));
                    if(counter % 5 ==0&&counter!=0)
                    {
                        String link = browserLink.getText().toString();
                        if(link.contains("https://"))
                        {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(link));
                            startActivity(intent);
                            writeToFile("open link : "+link);
                        }
                        else Toast.makeText(this,"to open the link enter https://",Toast.LENGTH_SHORT).show();
                        writeToFile("entered string : "+link);
                    }

                    Toast.makeText(this,getResources().getString(R.string.counted_to,counter), Toast.LENGTH_SHORT).show();
                    break;
                case COUNTER_FINISH:
                    Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonStart:
                checkService();
                break;
            case R.id.buttonStop:
                stopService();
                break;
        }
    }

    private void writeToFile(String message)
    {
        File dowloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(dowloadDir, "logFile.txt");
        try
        {
            FileWriter out = new FileWriter(myFile,true);
            out.write(message+"\n");
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}