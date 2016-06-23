package edu.fontbonne.mobileapplab.flit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoggedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        Button budgetButton = (Button) findViewById(R.id.logged_in_budget_button);
        Button spendingButton = (Button) findViewById(R.id.logged_in_spending_button);

        budgetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent budgetIntent = new Intent(getApplicationContext(), BudgetActivity.class);
                startActivity(budgetIntent);
            }
        });

        spendingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent spendingIntent = new Intent(getApplicationContext(), SpendingActivity.class);
                startActivity(spendingIntent);
            }
        });
    }
}
