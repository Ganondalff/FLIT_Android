package edu.fontbonne.mobileapplab.flit;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class BudgetActivity extends Activity {

    ViewFlipper budgetFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        budgetFlipper = (ViewFlipper) findViewById(R.id.budget_flipper);

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

            budgetFlipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_left);
            budgetFlipper.setInAnimation(getApplicationContext(), R.anim.slide_in_right);

            switch (v.getId())
            {
                case R.id.main_home:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.home_array), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_food:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.food_array), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_health:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.health_array), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_transport:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.transport_array), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_debts:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.debts_array), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_entertainment:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.entertainment_array), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_clothing:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.clothing_array), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_savings:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.savings_array), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_emergency:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.emergency_array), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
            }
        }
    };

    @Override
    public void onBackPressed()
    {
        if(budgetFlipper.getDisplayedChild() == 0)
            super.onBackPressed();
        else
        {
            budgetFlipper.setOutAnimation(this, R.anim.slide_out_right);
            budgetFlipper.setInAnimation(this, R.anim.slide_in_left);
            budgetFlipper.setDisplayedChild(0);
        }
    }

    private ListView submenuLayoutCreate(int strArray)
    {
        ListView listView = new ListView(this);
        String[] itemList = getResources().getStringArray(strArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.submenu_budget_row, R.id.rowTitle, itemList);

        ListView.LayoutParams paramsList = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ListView.LayoutParams.MATCH_PARENT);
        listView.setLayoutParams(paramsList);
        listView.setAdapter(adapter);

        return listView;
    }
}
