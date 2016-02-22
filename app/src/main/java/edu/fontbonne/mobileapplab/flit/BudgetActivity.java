package edu.fontbonne.mobileapplab.flit;

import android.content.res.Resources;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
        if (budgetFlipper.getDisplayedChild() == 0)
            super.onBackPressed();
        else
        {
            budgetFlipper.setOutAnimation(this, R.anim.slide_out_right);
            budgetFlipper.setInAnimation(this, R.anim.slide_in_left);
            budgetFlipper.setDisplayedChild(0);
        }
    }

    private RelativeLayout submenuLayoutCreate(int strArray)
    {
        Resources res = getResources();
        String[] itemList = res.getStringArray(strArray);

        RelativeLayout relativeLayout = new RelativeLayout(this);

        relativeLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.background_edit));
        relativeLayout.setPadding(res.getDimensionPixelSize(R.dimen.activity_horizontal_margin), res.getDimensionPixelSize(R.dimen.activity_vertical_margin), res.getDimensionPixelSize(R.dimen.activity_horizontal_margin), res.getDimensionPixelSize(R.dimen.activity_vertical_margin));

        TextView[] budgetItemText = new TextView[itemList.length];
        EditText[] budgetItemInput = new EditText[itemList.length];

        for(int i = 0; i < itemList.length; i++)
        {
            budgetItemText[i] = new TextView(this);
            budgetItemInput[i] = new EditText(this);

            budgetItemText[i].setId(View.generateViewId());
            budgetItemInput[i].setId(View.generateViewId());

            budgetItemText[i].setText(itemList[i]);
            budgetItemText[i].setTextSize(20);
            budgetItemText[i].setPadding(0, 15, 0, 15);

            budgetItemInput[i].setHint("Enter Budget Here");
            budgetItemInput[i].setTextSize(20);
            budgetItemInput[i].setPadding(10, 15, 10, 15);
            budgetItemInput[i].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

            RelativeLayout.LayoutParams paramsText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams paramsInput = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            paramsInput.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            if(i>0)
            {
                paramsText.addRule(RelativeLayout.BELOW, budgetItemText[i - 1].getId());
                paramsInput.addRule(RelativeLayout.BELOW, budgetItemText[i - 1].getId());
            }

            relativeLayout.addView(budgetItemText[i], paramsText);
            relativeLayout.addView(budgetItemInput[i], paramsInput);
        }

        return relativeLayout;
    }
}
