package edu.fontbonne.mobileapplab.flit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = (Button) findViewById(R.id.main_login_button);
        Button registerButton = (Button) findViewById(R.id.main_register_button);
        Button budgetButton = (Button) findViewById(R.id.main_budget_button);
        Button spendingButton = (Button) findViewById(R.id.main_spending_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(registerIntent);
            }
        });

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
