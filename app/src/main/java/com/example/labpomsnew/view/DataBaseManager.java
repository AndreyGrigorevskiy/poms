package com.example.labpomsnew.view;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {

    private DBHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DataBaseManager(Context c){context = c;}

    public DataBaseManager open() throws SQLException
    {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {dbHelper.close();}

    public void insert(String op1, String op2, String function, String result)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.OPERAND1, op1);
        contentValues.put(DBHelper.OPERAND2, op2);
        contentValues.put(DBHelper.FUNCTION, function);
        contentValues.put(DBHelper.RESULT, result);
        database.insert(DBHelper.TABLE_NAME, null, contentValues);
    }

    private Cursor fetch()
    {
        String[] columns = new String[]
                {
                DBHelper._ID,
                DBHelper.OPERAND1,
                DBHelper.OPERAND2,
                DBHelper.FUNCTION,
                DBHelper.RESULT
                };
        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    private int update(long _id, String op1, String op2, String function, String result)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.OPERAND1, op1);
        contentValues.put(DBHelper.OPERAND2, op2);
        contentValues.put(DBHelper.FUNCTION, function);
        contentValues.put(DBHelper.RESULT, result);
        int i = database.update(DBHelper.TABLE_NAME, contentValues, DBHelper._ID + "=" + _id, null);
        return i;
    }

    private void delete(long _id)
    {
        database.delete(DBHelper.TABLE_NAME, DBHelper._ID + "=" + _id, null );
    }


    public void insert(HistoryItem item)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.OPERAND1, item.getFirstArg());
        contentValues.put(DBHelper.OPERAND2, item.getSecondArg());
        contentValues.put(DBHelper.FUNCTION, item.getOperationArg());
        contentValues.put(DBHelper.RESULT, item.getResult());
        database.insert(DBHelper.TABLE_NAME, null, contentValues);
    }

    public void deleteAll() {database.delete(DBHelper.TABLE_NAME, "", null);}

    public String getAllAsText()
    {
        String text = "";
        Cursor cursor = fetch();
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            String op1, op2, function, result;
            op1 = cursor.getString(cursor.getColumnIndex(DBHelper.OPERAND1));
            op2 = cursor.getString(cursor.getColumnIndex(DBHelper.OPERAND2));
            function = cursor.getString(cursor.getColumnIndex(DBHelper.FUNCTION));
            result = cursor.getString(cursor.getColumnIndex(DBHelper.RESULT));
            HistoryItem item = new HistoryItem(op1, op2, function, result);
            text += (item.getText() + "\n" + "1");
            cursor.moveToNext();
        }
        return text;
    }


}
