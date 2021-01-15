package com.example.labpomsnew.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert()
    public void addHistoryEntry(HistoryEntry userEntry);

    @Query("SELECT * FROM history")
    public List<HistoryEntry> getAll();

    @Query("DELETE FROM history")
    public void deleteAll();

    @Query("SELECT * FROM history WHERE id==:id")
    public List<HistoryEntry> getEntryById(int id);
}
