package edu.fontbonne.mobileapplab.flit;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
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

    private RelativeLayout submenuLayoutCreate(int strArray)
    {
        Resources res = getResources();
        String[] itemList = res.getStringArray(strArray);

        RelativeLayout relativeLayout = new RelativeLayout(this);

        relativeLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.background_edit));
        relativeLayout.setPadding(res.getDimensionPixelSize(R.dimen.activity_horizontal_margin), res.getDimensionPixelSize(R.dimen.activity_vertical_margin), res.getDimensionPixelSize(R.dimen.activity_horizontal_margin), res.getDimensionPixelSize(R.dimen.activity_vertical_margin));

        TextView[] spendingItemText = new TextView[itemList.length];
        EditText[] spendingItemInput = new EditText[itemList.length];

        for(int i = 0; i < itemList.length; i++)
        {
            spendingItemText[i] = new TextView(this);
            spendingItemInput[i] = new EditText(this);

            spendingItemText[i].setId(View.generateViewId());
            spendingItemInput[i].setId(View.generateViewId());

            spendingItemText[i].setText(itemList[i]);
            spendingItemText[i].setTextSize(20);
            spendingItemText[i].setPadding(0, 15, 0, 15);

            spendingItemInput[i].setHint("Enter Amount Here");
            spendingItemInput[i].setTextSize(20);
            spendingItemInput[i].setPadding(10, 15, 10, 15);

            RelativeLayout.LayoutParams paramsText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams paramsInput = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            paramsInput.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            if(i>0)
            {
                paramsText.addRule(RelativeLayout.BELOW, spendingItemText[i - 1].getId());
                paramsInput.addRule(RelativeLayout.BELOW, spendingItemText[i - 1].getId());
            }

            relativeLayout.addView(spendingItemText[i], paramsText);
            relativeLayout.addView(spendingItemInput[i], paramsInput);
        }

        return relativeLayout;
    }
}
