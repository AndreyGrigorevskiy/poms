package com.example.labpomsnew.viewmodel;

import android.content.Context;

import com.example.labpomsnew.model.AppDatabase;
import com.example.labpomsnew.model.HistoryEntry;

import java.util.List;

public class HistoryFacade {

    public static void addItem(Context context, HistoryEntry newItem)
    {
        AppDatabase.getInstance(context).historyDao().addHistoryEntry(newItem);
    }

    public static void deleteAll(Context context)
    {
        AppDatabase.getInstance(context).historyDao().deleteAll();
    }

    public static String getAllAsString(Context context)
    {
        List<HistoryEntry > history =AppDatabase.getInstance(context).historyDao().getAll();
        StringBuilder result = new StringBuilder();
        for (HistoryEntry historyEntry: history)
        {
            result.append(historyEntry.getTextRepresentation()).append("\n");
        }
        return result.toString();
    }

    public static List<HistoryEntry> getAllAsList(Context context)
    {
        return AppDatabase.getInstance(context).historyDao().getAll();
    }


}
