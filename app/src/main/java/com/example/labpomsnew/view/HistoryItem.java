package com.example.labpomsnew.view;

import android.os.Parcel;
import android.os.Parcelable;

public class HistoryItem  implements Parcelable {

    private String firstArg;
    private String secondArg;
    private String operationArg;
    private String result;

    public HistoryItem(String operOne, String operTwo, String function, String result)
    {
        this.firstArg = operOne;
        this.secondArg = operTwo;
        this.operationArg = function;
        this.result = result;
    }

    public String getFirstArg() {
        return firstArg;
    }

    public String getSecondArg() {
        return secondArg;
    }

    public String getOperationArg() {
        return operationArg;
    }

    public String getResult() {
        return result;
    }

    protected HistoryItem(Parcel in) {
        firstArg = in.readString();
        secondArg = in.readString();
        operationArg = in.readString();
        result = in.readString();
    }

    public static final Creator<HistoryItem> CREATOR = new Creator<HistoryItem>() {
        @Override
        public HistoryItem createFromParcel(Parcel in) { return new HistoryItem(in); }

        @Override
        public HistoryItem[] newArray(int size) { return new HistoryItem[size]; }
    };

    public String getText()
    {
        return  String.format("You entered: %1s, %2s\nYour result %3s",firstArg, secondArg, result);
    }

    public String[] getArgs()
    {
        String[] strings = {firstArg, secondArg, result};
        return strings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstArg);
        parcel.writeString(secondArg);
        parcel.writeString(operationArg);
        parcel.writeString(result);
    }
}
