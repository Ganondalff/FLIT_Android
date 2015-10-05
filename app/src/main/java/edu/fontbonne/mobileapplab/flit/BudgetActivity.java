package edu.fontbonne.mobileapplab.flit;

import android.os.Bundle;
import android.app.Activity;

public class BudgetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
