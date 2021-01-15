package com.example.labpomsnew.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "history")
public class HistoryEntry  implements  Parcelable{
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "operand1")
    private String operand1;
    @ColumnInfo(name = "operand2")
    private String operand2;
    @ColumnInfo(name = "function")
    private String function;
    @ColumnInfo(name = "result")
    private String result;

    public String getOperand1() {
        return operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public String getFunction() {
        return function;
    }

    public String getResult() {
        return result;
    }

    public HistoryEntry(String operand1, String operand2, String function, String result){
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.function = function;
        this.result = result;
    }

    @Ignore
    public HistoryEntry(String operand1, String function, String result){
        this.operand1 = operand1;
        this.operand2 = "";
        this.function = function;
        this.result = result;
    }

    public static final Parcelable.Creator<HistoryEntry> CREATOR = new Parcelable.Creator<HistoryEntry>() {
        @Override
        public HistoryEntry createFromParcel(Parcel in) {
            return new HistoryEntry(in);
        }

        @Override
        public HistoryEntry[] newArray(int size) {
            return new HistoryEntry[size];
        }
    };

    public String getTextRepresentation(){
        String textRepresentation;

        if(operand2.equals(""))
            textRepresentation = String.format("We got %1s as result of %2s of %3s",
                    result, function, operand1);
        else
            textRepresentation = String.format("We got %1s as result of %2s of %3s and %4s",
                    result, function, operand1, operand2);
        return textRepresentation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(operand1);
        dest.writeString(operand2);
        dest.writeString(function);
        dest.writeString(result);
    }


    protected HistoryEntry(Parcel in) {
        operand1 = in.readString();
        operand2 = in.readString();
        function = in.readString();
        result = in.readString();
    }


}
