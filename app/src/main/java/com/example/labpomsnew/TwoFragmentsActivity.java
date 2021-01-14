package com.example.labpomsnew;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class TwoFragmentsActivity extends AppCompatActivity {

    FragmentStringMessege fragmentStringMessege;
    FragmentMathCalc fragmentMathCalc;
    public static final String TAG = "Two";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_fragments);

        fragmentStringMessege = new FragmentStringMessege();
        fragmentStringMessege.setTag(TAG);
        fragmentMathCalc = new FragmentMathCalc();
        fragmentMathCalc.setTag(TAG);
        FrameLayout fragmentSlotMath = findViewById(R.id.fragmentSlotMathCalc);
        FrameLayout fragmentSlotString = findViewById(R.id.fragmentSlotStringMessege);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(fragmentSlotMath!=null)
        {
            fragmentTransaction.replace(R.id.fragmentSlotMathCalc,fragmentMathCalc,FragmentMathCalc.TAG);
        }
        if(fragmentSlotString!=null)
        {
            fragmentTransaction.replace(R.id.fragmentSlotStringMessege,fragmentStringMessege,FragmentStringMessege.TAG);
        }
        fragmentTransaction.commit();
    }
}