package com.example.labpomsnew;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class FragmentStringMessege extends Fragment implements View.OnClickListener {

    public static final String TAG ="MessageTAG";
    private String parentTAG = "MainActivity";
    Button buttonEnter;

    EditText name;
    EditText lastName;


    public FragmentStringMessege()
    {}

    public static FragmentStringMessege newInstance(){
        return new FragmentStringMessege();
    }

    @Override
    public void onCreate(Bundle saveInstanceState) { super.onCreate(saveInstanceState); }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_string_messege,container,false);


        name = (EditText)view.findViewById(R.id.editTextTextPersonName);
        lastName = (EditText)view.findViewById(R.id.editTextTextPersonLastName);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        buttonEnter = getActivity().findViewById(R.id.buttonEnter);
        buttonEnter.setOnClickListener(this);

    }

    public void helloToast()
    {
        String makeText = getString(R.string.hello) +" " +name.getText().toString() + " " + lastName.getText().toString() + "\n" + getString(R.string.welcome);
        Toast toast = Toast.makeText(getContext(),makeText,Toast.LENGTH_SHORT);
        addHistoryItem(name.getText().toString(),lastName.getText().toString(), "hello", makeText);
        toast.show();

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
            parent.addToHist(new HistoryItem(oper1String, oper2String, functionString, resultString));

        }
    }


    @Override
    public void onClick(View v) {
        helloToast();
    }

    public void  setTag(String TAG)
    {
        parentTAG = TAG;
    }

}