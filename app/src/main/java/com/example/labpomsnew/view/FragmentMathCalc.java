package com.example.labpomsnew.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labpomsnew.R;
import com.example.labpomsnew.model.HistoryEntry;


public class FragmentMathCalc extends Fragment implements View.OnClickListener {


    public static final String TAG ="MathTAG";
    private String parentTAG = "MainActivity";

    Button buttonSum;


    EditText x;
    EditText y;


    public static FragmentMathCalc newInstance() {
        return new FragmentMathCalc();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_math_calc, container, false);

        x = (EditText)view.findViewById(R.id.editTextNumberX);
        y = (EditText)view.findViewById(R.id.editTextNumberY);


        return view;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) { super.onCreate(saveInstanceState); }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        buttonSum = getActivity().findViewById(R.id.buttonSum);
        buttonSum.setOnClickListener(view -> {
            if(x.getText().toString().isEmpty()||y.getText().toString().isEmpty())
            {
                toastResult(getString(R.string.error));
            }
            else
            {
                sum(x.getText().toString(),y.getText().toString());
            }

        });

    }

    public void sum(String x, String y)
    {
        String sumResult = Integer.toString(Integer.valueOf(x) + Integer.valueOf(y));
        toastResult(sumResult);
        addHistoryItem(Integer.valueOf(x).toString(),Integer.valueOf(y).toString(),"sum", sumResult);
    }

    public void toastResult(String result)
    {
       Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
    }

    private void addHistoryItem(String oper1, String oper2, String function, String result)
    {
        String oper1String = String.format("%1s",oper1);
        String oper2String = String.format("%1s",oper2);
        String functionString = String.format("%1s",function);
        String resultString = String.format("%1s",result);

        if(parentTAG.equals("MainActivity"))
        {
            MainActivity parent = (MainActivity)getActivity();
            parent.addToHist(new HistoryEntry(oper1String,oper2String,functionString,resultString));
        }
    }

    @Override
    public void onClick(View v) {
        if(x.getText().toString().isEmpty()||y.getText().toString().isEmpty())
        {
            this.toastResult(getString(R.string.error));
        }
        else
        {
            sum(x.getText().toString(),y.getText().toString());
        }
    }

    public void  setTag(String TAG)
    {
        parentTAG = TAG;
    }
}