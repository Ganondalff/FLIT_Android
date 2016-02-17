package edu.fontbonne.mobileapplab.flit;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
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
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_food:
                    budgetFlipper.setDisplayedChild(2);
                    break;
                case R.id.main_health:
                    budgetFlipper.setDisplayedChild(3);
                    break;
                case R.id.main_transport:
                    budgetFlipper.setDisplayedChild(4);
                    break;
                case R.id.main_debts:
                    budgetFlipper.setDisplayedChild(5);
                    break;
                case R.id.main_entertainment:
                    budgetFlipper.setDisplayedChild(6);
                    break;
                case R.id.main_clothing:
                    budgetFlipper.setDisplayedChild(7);
                    break;
                case R.id.main_savings:
                    budgetFlipper.setDisplayedChild(8);
                    break;
                case R.id.main_emergency:
                    budgetFlipper.setDisplayedChild(9);
                    break;
            }
        }
    };

    @Override
    public void onBackPressed()
    {
        if (budgetFlipper.getDisplayedChild() == 0)
            super.onBackPressed();
        else
        {
            budgetFlipper.setOutAnimation(this, R.anim.slide_out_right);
            budgetFlipper.setInAnimation(this, R.anim.slide_in_left);
            budgetFlipper.setDisplayedChild(0);
        }
    }
}
