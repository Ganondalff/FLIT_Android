package edu.fontbonne.mobileapplab.flit;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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

    private LinearLayout submenuLayoutCreate(int strArray)
    {
        Resources res = getResources();
        String[] itemList = res.getStringArray(strArray);

        LinearLayout linearLayout = new LinearLayout(this);

        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //linearLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.background_edit));
        linearLayout.setPadding(res.getDimensionPixelSize(R.dimen.activity_horizontal_margin), res.getDimensionPixelSize(R.dimen.activity_vertical_margin), res.getDimensionPixelSize(R.dimen.activity_horizontal_margin), res.getDimensionPixelSize(R.dimen.activity_vertical_margin));

        for(int i = 0; i < itemList.length; i++)
            linearLayout.addView(subMenuRowCreate(itemList[i], linearLayout));

        return linearLayout;
    }

    private RelativeLayout subMenuRowCreate(String itemString, final LinearLayout parent)
    {
        RelativeLayout relativeLayout = new RelativeLayout(this);

        TextView spendingItemText = new TextView(this);
        EditText spendingItemInputLocation = new EditText(this);
        EditText spendingItemInputAmount = new EditText(this);

        spendingItemText.setId(View.generateViewId());
        spendingItemInputLocation.setId(View.generateViewId());
        spendingItemInputAmount.setId(View.generateViewId());

        spendingItemText.setText(itemString);
        spendingItemText.setTextSize(20);
        spendingItemText.setPadding(0, 15, 0, 15);

        spendingItemInputLocation.setHint("Location");
        spendingItemInputLocation.setTextSize(20);
        spendingItemInputLocation.setPadding(10, 15, 10, 15);

        spendingItemInputAmount.setHint("Amount");
        spendingItemInputAmount.setTextSize(20);
        spendingItemInputAmount.setPadding(10, 15, 10, 15);

        RelativeLayout.LayoutParams paramsText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams paramsInputLocation = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams paramsInputAmount = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        paramsInputLocation.addRule(RelativeLayout.LEFT_OF, spendingItemInputAmount.getId());
        paramsInputAmount.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        if(itemString.equals("Other"))
        {
            View.OnClickListener plusListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.addView(subMenuRowCreate("Other", parent));
                }
            };

            TextView plusButton = new TextView(this);
            plusButton.setId(View.generateViewId());
            plusButton.setText("+");
            plusButton.setTextSize(20);
            plusButton.setPadding(0, 15, 0, 15);
            plusButton.setOnClickListener(plusListener);

            RelativeLayout.LayoutParams paramsPlus = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            paramsPlus.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            paramsInputAmount.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            paramsInputAmount.addRule(RelativeLayout.LEFT_OF, plusButton.getId());

            relativeLayout.addView(plusButton, paramsPlus);
        }

        relativeLayout.addView(spendingItemText, paramsText);
        relativeLayout.addView(spendingItemInputLocation, paramsInputLocation);
        relativeLayout.addView(spendingItemInputAmount, paramsInputAmount);

        return relativeLayout;
    }
}
