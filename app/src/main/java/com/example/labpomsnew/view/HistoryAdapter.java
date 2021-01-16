package com.example.labpomsnew.view;

import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labpomsnew.R;
import com.example.labpomsnew.model.HistoryEntry;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryItemViewHolder> {

    //private ArrayList<HistoryItem> historyItems;
    private ArrayList<HistoryEntry> historyItems;
    HistoryAdapter() { historyItems = new ArrayList<>();}

    void initialize(ArrayList<HistoryEntry> historyItems) {

        this.historyItems = historyItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.history_item, viewGroup, false);
        return new HistoryItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder historyItemViewHolder, int i)
    {
        historyItemViewHolder.bind(historyItems.get(i));
    }

    @Override
    public int getItemCount() { return historyItems.size(); }

    class HistoryItemViewHolder extends RecyclerView.ViewHolder
    {
        private TextView historyText;
        private Button historyButton;
        private Button historyButtonZ;

        HistoryItemViewHolder(View itemView){
            super(itemView);
            historyText = itemView.findViewById(R.id.textHistoryItem);
            historyButton = itemView.findViewById(R.id.buttonHistoryItem);
            historyButtonZ = itemView.findViewById(R.id.buttonZ);
        }

        void bind(final HistoryItem historyItem)
        {
            historyText.setText(historyItem.getText());
            historyButton.setOnClickListener(view -> Toast.makeText(historyButton.getContext(), historyItem.getText(), Toast.LENGTH_SHORT).show());
            String[] args = historyItem.getArgs();
            String ent = String.format("You entered: %1s %2s\n",args[0],args[1]);
            String res = String.format("Your result: %1s",args[2]);
            historyButtonZ.setOnClickListener(v -> {

                Spannable spannableString = new SpannableString(ent+res);
                Spannable span = new SpannableString(ent+res);
                CountDownTimer countDownTimer = new CountDownTimer(4000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                     if (millisUntilFinished>=2000)
                     {
                         spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0,ent.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                         historyText.setText(spannableString);
                     }
                     else
                     {
                         span.setSpan(new StyleSpan(Typeface.BOLD), ent.length(),res.length()+ent.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                         historyText.setText(span);
                     }
                    }
                    @Override
                    public void onFinish() {
                        historyText.setText(historyItem.getText());
                    }
                }.start();

            });
        }
        void bind(final HistoryEntry historyItem) {
            historyText.setText(historyItem.getTextRepresentation());
            historyButton.setOnClickListener(view -> Toast.makeText(historyButton.getContext(), historyItem.getTextRepresentation(), Toast.LENGTH_SHORT).show());
            /*String[] args = historyItem.getArgs();
            String ent = String.format("You entered: %1s %2s\n", args[0], args[1]);
            String res = String.format("Your result: %1s", args[2]);
            historyButtonZ.setOnClickListener(v -> {

                Spannable spannableString = new SpannableString(ent + res);
                Spannable span = new SpannableString(ent + res);
                CountDownTimer countDownTimer = new CountDownTimer(4000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (millisUntilFinished >= 2000) {
                            spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, ent.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            historyText.setText(spannableString);
                        } else {
                            span.setSpan(new StyleSpan(Typeface.BOLD), ent.length(), res.length() + ent.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            historyText.setText(span);
                        }
                    }

                    @Override
                    public void onFinish() {
                        historyText.setText(historyItem.getText());
                    }
                }.start();

            });*/
        }

    }



}
