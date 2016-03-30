package edu.fontbonne.mobileapplab.flit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class SpendingActivity extends Activity {

    ViewFlipper spendingFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending);

        spendingFlipper = (ViewFlipper) findViewById(R.id.spending_flipper);

        TextView mainHomeButton = (TextView) findViewById(R.id.main_home);
        TextView mainFoodButton = (TextView) findViewById(R.id.main_food);
        TextView mainHealthButton = (TextView) findViewById(R.id.main_health);
        TextView mainTransportButton = (TextView) findViewById(R.id.main_transport);
        TextView mainDebtsButton = (TextView) findViewById(R.id.main_debts);
        TextView mainEntertainmentButton = (TextView) findViewById(R.id.main_entertainment);
        TextView mainClothingButton = (TextView) findViewById(R.id.main_clothing);
        TextView mainSavingsButton = (TextView) findViewById(R.id.main_savings);
        TextView mainEmergencyButton = (TextView) findViewById(R.id.main_emergency);

        mainHomeButton.setOnClickListener(mainButtonListener);
        mainFoodButton.setOnClickListener(mainButtonListener);
        mainHealthButton.setOnClickListener(mainButtonListener);
        mainTransportButton.setOnClickListener(mainButtonListener);
        mainDebtsButton.setOnClickListener(mainButtonListener);
        mainEntertainmentButton.setOnClickListener(mainButtonListener);
        mainClothingButton.setOnClickListener(mainButtonListener);
        mainSavingsButton.setOnClickListener(mainButtonListener);
        mainEmergencyButton.setOnClickListener(mainButtonListener);
    }

    private View.OnClickListener mainButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            spendingFlipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_left);
            spendingFlipper.setInAnimation(getApplicationContext(), R.anim.slide_in_right);

            switch (v.getId())
            {
                case R.id.main_home:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.home_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    addPlus();
                    break;
                case R.id.main_food:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.food_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_health:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.health_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_transport:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.transport_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_debts:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.debts_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_entertainment:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.entertainment_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_clothing:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.clothing_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_savings:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.savings_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_emergency:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.emergency_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    break;
            }
        }
    };

    @Override
    public void onBackPressed()
    {
        if (spendingFlipper.getDisplayedChild() == 0)
            super.onBackPressed();
        else
        {
            spendingFlipper.setOutAnimation(this, R.anim.slide_out_right);
            spendingFlipper.setInAnimation(this, R.anim.slide_in_left);
            spendingFlipper.setDisplayedChild(0);
        }
    }

    private ListView submenuLayoutCreate(int strArray)
    {
        ListView listView = new ListView(this);
        String[] itemList = getResources().getStringArray(strArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.submenu_row, R.id.rowTitle, itemList);

        ListView.LayoutParams paramsList = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ListView.LayoutParams.MATCH_PARENT);
        listView.setLayoutParams(paramsList);
        listView.setAdapter(adapter);

        return listView;
    }

    // not working
    private void addPlus()
    {
        ListView list = (ListView)spendingFlipper.getChildAt(1);
        for(int i = 0; i < list.getChildCount(); i++)
        {
            if(((TextView)((RelativeLayout)list.getChildAt(i)).getChildAt(1)).getText().equals("Other"))
            {
                View.OnClickListener plusListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View newRow = getLayoutInflater().inflate(R.layout.submenu_row, null);
                        ((ListView)spendingFlipper.getChildAt(1)).addView(newRow);
                        ((TextView)((RelativeLayout)newRow).getChildAt(1)).setText("Other");
                        addPlus();
                    }
                };

                RelativeLayout rowView = (RelativeLayout) list.getChildAt(i);
                rowView.getChildAt(3).setOnClickListener(plusListener);
                rowView.getChildAt(3).setVisibility(View.VISIBLE);
            }
        }
    }
}