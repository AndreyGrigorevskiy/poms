package com.example.labpomsnew.viewmodel;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileProcessor {

    public static void writeToFile(String message) {
        File dowloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(dowloadDir, "logFile.txt");
        try {
            FileWriter out = new FileWriter(myFile, true);
            out.write(message + "\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadFileContext()
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
        return  result;
    }

    public static void clearFile()
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
