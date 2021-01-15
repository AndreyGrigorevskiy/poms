package com.example.labpomsnew.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.labpomsnew.R;
import com.example.labpomsnew.model.HistoryEntry;
import com.example.labpomsnew.viewmodel.HistoryFacade;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    Button buttonSwith;
    Button buttonTF;

    FrameLayout fragmentSlot;




    FragmentStringMessege fragmentStringMessege;
    FragmentMathCalc fragmentMathCalc;


    Button history;


    private int state;
    private final int NUMBER_STATE = 1;
    private final int STRING_STATE = 2;



    private boolean flag = false;

    public static final String HISTORY_KEY = "hist";

    private ArrayList<HistoryItem> historyArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentStringMessege = new FragmentStringMessege();
        fragmentMathCalc = new FragmentMathCalc();

        history = (Button) findViewById(R.id.buttonSwitchFragment);
        registerForContextMenu(history);

        fragmentSlot = findViewById(R.id.fragmentSlot);

        if(fragmentSlot!=null)
        {
            state = NUMBER_STATE;
            setNumberFragment();
        }



        buttonSwith = (Button) findViewById(R.id.buttonSwitchFragment);
        buttonSwith.setOnClickListener(this);
        buttonTF = (Button) findViewById(R.id.buttonTwoFragments);
        buttonTF.setOnClickListener(this);

        historyArray = new ArrayList<>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.open_file_activity:
                Intent intentFile = new Intent(this, FileActivity.class);
                startActivity(intentFile);
                break;
            case R.id.open_DB_activity:
                Intent intentDB = new Intent(this, DataBaseActivity.class);
                startActivity(intentDB);
                break;
            case R.id.settings:
                Intent intentSPA = new Intent(this, SharedPreferencesActivity.class);
                startActivity(intentSPA);
                break;
            case R.id.anim:
                Intent intentAnim = new Intent(this, AnimationActivity.class);
                startActivity(intentAnim);
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(HISTORY_KEY,historyArray);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null &&  savedInstanceState.containsKey(HISTORY_KEY))
            historyArray = savedInstanceState.getParcelableArrayList(HISTORY_KEY);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId())
        {
            case R.id.buttonSwitchFragment:
                menu.add(0,0,0,R.string.history);
                menu.add(0,1,0,R.string.ServicesAndIntentions);

        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case 0:
                Intent intent = new Intent(this, HistoryActivity.class);
                intent.putParcelableArrayListExtra(HISTORY_KEY,historyArray);
                startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent(this, ServicesAndIntentionsActivity.class);
                startActivity(intent1);
                break;
        }

        return super.onContextItemSelected(item);
    }


    private void setFragment(Fragment fragment, Fragment fragmentSecond)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentSlot,fragment,fragment.getTag());
        fragmentTransaction.commit();
    }

    private void setStringFragment(){
        state = STRING_STATE;
        setFragment(fragmentStringMessege,fragmentMathCalc);

    }

    private void setNumberFragment()
    {
        state = NUMBER_STATE;
        setFragment(fragmentMathCalc,fragmentStringMessege);

    }

    public void changeFragment()
    {

        if(state == NUMBER_STATE)
            setStringFragment();
        else if(state == STRING_STATE)
        {
            setNumberFragment();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.buttonSwitchFragment:
                changeFragment();
                break;
            case R.id.buttonTwoFragments:
                Intent intent = new Intent(this, TwoFragmentsActivity.class);
                startActivity(intent);
                break;
        }

    }

    public void addToHist(HistoryItem newItem){
        historyArray.add(newItem);

        DataBaseManager dataBaseManager = new DataBaseManager(this);
        dataBaseManager.open();
        dataBaseManager.insert(newItem);
        dataBaseManager.close();
    }

    public void addToHist(HistoryEntry newItem){
        HistoryFacade.addItem(getBaseContext(), newItem);
    }
}
