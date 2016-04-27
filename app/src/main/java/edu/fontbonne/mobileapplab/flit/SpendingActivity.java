package edu.fontbonne.mobileapplab.flit;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class SpendingActivity extends AppCompatActivity {

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
        switch (spendingFlipper.getDisplayedChild())
        {
            case 0:
                super.onBackPressed();
                break;
            case 1:
                spendingFlipper.setOutAnimation(this, R.anim.slide_out_right);
                spendingFlipper.setInAnimation(this, R.anim.slide_in_left);
                spendingFlipper.setDisplayedChild(0);
                break;
            case 2:
                spendingFlipper.setOutAnimation(this, R.anim.slide_out_right);
                spendingFlipper.setInAnimation(this, R.anim.slide_in_left);
                spendingFlipper.setDisplayedChild(1);
                break;
        }
    }

    private ListView submenuLayoutCreate(int strArray)
    {
        ListView listView = new ListView(this);
        String[] itemList = getResources().getStringArray(strArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.submenu_spending_row, R.id.rowTitle, itemList);

        ListView.LayoutParams paramsList = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ListView.LayoutParams.MATCH_PARENT);
        listView.setLayoutParams(paramsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(listItemButtonListener);

        return listView;
    }

    private AdapterView.OnItemClickListener listItemButtonListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            spendingFlipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_left);
            spendingFlipper.setInAnimation(getApplicationContext(), R.anim.slide_in_right);

            spendingFlipper.addView(submenu2LayoutCreate(((TextView)view.findViewById(R.id.rowTitle)).getText().toString()), 2);
            spendingFlipper.setDisplayedChild(2);
        }
    };

    private LinearLayout submenu2LayoutCreate(String reason)
    {
        String user = "testname";
        FLITDbHelper flitDbHelper = new FLITDbHelper(getApplicationContext());
        SQLiteDatabase db = flitDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FLITDbHelper.SPENDING_COLUMN_EMAIL, "testname");
        values.put(FLITDbHelper.SPENDING_COLUMN_REASON, "Rent/Mortgage");
        values.put(FLITDbHelper.SPENDING_COLUMN_LOCATION, "testdata");
        values.put(FLITDbHelper.SPENDING_COLUMN_AMOUNT, 600.50);

        db.insert(FLITDbHelper.SPENDING_TABLE_NAME, null, values);

        String[] projection = {
                FLITDbHelper.SPENDING_COLUMN_LOCATION,
                FLITDbHelper.SPENDING_COLUMN_AMOUNT,
        };

        String sortOrder = FLITDbHelper.SPENDING_COLUMN_TIME + " DESC";

        Cursor cursor = db.query(
                FLITDbHelper.SPENDING_TABLE_NAME,
                projection,
                FLITDbHelper.SPENDING_COLUMN_EMAIL + " = '" + user + "' and " + FLITDbHelper.SPENDING_COLUMN_REASON + " = '" + reason + "'",
                null,
                null,
                null,
                sortOrder
        );

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        String[] locList = new String[cursor.getCount()];
        float[] amountList = new float[cursor.getCount()];
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++)
        {
            locList[i] = cursor.getString(cursor.getColumnIndex(FLITDbHelper.SPENDING_COLUMN_LOCATION));
            amountList[i] = cursor.getFloat(cursor.getColumnIndex(FLITDbHelper.SPENDING_COLUMN_AMOUNT));
            cursor.moveToNext();
        }

        cursor.close();
        TextView textView = new TextView(this);
        ListView listView = new ListView(this);
        Submenu2Adapter adapter = new Submenu2Adapter(this, R.layout.submenu2_spending_row, locList, amountList);
        FloatingActionButton floatingActionButton = new FloatingActionButton(this);

        ViewGroup.LayoutParams paramsText = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setText(reason);
        textView.setLayoutParams(paramsText);

        ListView.LayoutParams paramsList = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ListView.LayoutParams.MATCH_PARENT);
        listView.setLayoutParams(paramsList);
        listView.setAdapter(adapter);

        linearLayout.addView(textView);
        linearLayout.addView(listView);
        //linearLayout.addView(floatingActionButton);

        return linearLayout;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spending, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}