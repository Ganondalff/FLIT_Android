package edu.fontbonne.mobileapplab.flit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BudgetActivity extends AppCompatActivity {

    ViewFlipper budgetFlipper;
    MenuItem budgetActionSave;
    ListView budgetRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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
                    budgetFlipper.addView(submenuLayoutCreate(R.array.home_array, R.string.main_home), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_food:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.food_array, R.string.main_food), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_health:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.health_array, R.string.main_health), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_transport:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.transport_array, R.string.main_transport), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_debts:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.debts_array, R.string.main_debts), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_entertainment:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.entertainment_array, R.string.main_entertainment), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_clothing:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.clothing_array, R.string.main_clothing), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_savings:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.savings_array, R.string.main_savings), 1);
                    budgetFlipper.setDisplayedChild(1);
                    break;
                case R.id.main_emergency:
                    budgetFlipper.addView(submenuLayoutCreate(R.array.emergency_array, R.string.main_emergency), 1);
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
            getSupportActionBar().setTitle(R.string.title_activity_budget);
            budgetActionSave.setVisible(false);
        }
    }

    private TextView submenuLayoutCreate(int strArray, int strCategory)
    {
        TextView loadText = new TextView(getApplicationContext());
        loadText.setText("Loading...");
        loadText.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.black));
        loadText.setGravity(Gravity.CENTER_HORIZONTAL);

        SharedPreferences user = getSharedPreferences("LoginEmail", 0);

        new BudgetRetrieveTask().execute(user.getString("email", null), getResources().getString(strCategory));

        return loadText;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget, menu);

        budgetActionSave = menu.findItem(R.id.budget_action_save);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.budget_action_save) {
            SharedPreferences user = getSharedPreferences("LoginEmail", 0);
            List<String> saveData = new ArrayList<>();
            saveData.add(user.getString("email", null));
            saveData.add(getSupportActionBar().getTitle().toString());

            for(int i = 0; i < budgetRows.getChildCount(); i++)
                saveData.add(((EditText)budgetRows.getChildAt(i).findViewById(R.id.rowAmount)).getText().toString());

            new BudgetSaveTask().execute(saveData);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class BudgetRetrieveTask extends AsyncTask<String, Void, Pair<JSONObject, String>>
    {
        @Override
        protected Pair<JSONObject, String> doInBackground(String... params) {
            HttpURLConnection connection = null;
            JSONObject result = null;
            try {
                URL url = new URL("http://primetechconsult.com/REST_PHP/get_budget.php");
                connection = (HttpURLConnection)url.openConnection();
                connection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("email", params[0])
                        .appendQueryParameter("category", params[1]);
                String query = builder.build().getEncodedQuery();
                OutputStream out = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                out.close();

                InputStream in = connection.getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line).append('\n');
                }
                result = new JSONObject(total.toString());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null)
                    connection.disconnect();
            }

            return new Pair<>(result, params[1]);
        }

        @Override
        protected void onPostExecute(Pair<JSONObject, String> result)
        {
            JSONObject budgetResult;

            try
            {
                budgetResult = result.first.getJSONObject("budget");

                String[] budgetNames = new String[budgetResult.length() - 1];
                float[] budgetAmounts = new float[budgetResult.length() - 1];

                Log.i("email", budgetResult.getString("email"));
                if((budgetResult.getString("email") != null) && (!budgetResult.getString("email").equals("null")))
                {
                    for(int i = 1; i < budgetResult.length(); i++) {
                        budgetNames[i-1] = budgetResult.names().getString(i);
                        budgetAmounts[i-1] = (float) budgetResult.getDouble(budgetNames[i-1]);
                    }
                }
                else
                {
                    for(int i = 1; i < budgetResult.length(); i++)
                        budgetNames[i-1] = budgetResult.names().getString(i);
                }

                budgetRows = new ListView(getApplicationContext());
                SubmenuBudgetAdapter adapter = new SubmenuBudgetAdapter(getApplicationContext(), R.layout.submenu_budget_row, budgetNames, budgetAmounts);

                getSupportActionBar().setTitle(result.second);

                ListView.LayoutParams paramsList = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ListView.LayoutParams.MATCH_PARENT);
                budgetRows.setLayoutParams(paramsList);
                budgetRows.setAdapter(adapter);

                budgetFlipper.addView(budgetRows, 1);
                budgetFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        budgetFlipper.getInAnimation().setAnimationListener(null);
                        budgetFlipper.setOutAnimation(null);
                        budgetFlipper.setInAnimation(null);
                        budgetFlipper.setDisplayedChild(1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                budgetActionSave.setVisible(true);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

    private class BudgetSaveTask extends AsyncTask<List<String>, Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(List<String>... params) {
            HttpURLConnection connection = null;
            String result = null;
            try {
                URL url = new URL("http://primetechconsult.com/REST_PHP/set_budget.php");
                connection = (HttpURLConnection)url.openConnection();
                connection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("email", params[0].get(0))
                        .appendQueryParameter("category", params[0].get(1));

                for(int i = 0; i < (params[0].size() - 2); i++)
                    builder.appendQueryParameter("entry" + (i + 1), params[0].get(i + 2));

                String query = builder.build().getEncodedQuery();
                OutputStream out = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                out.close();

                InputStream in = connection.getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line);
                }
                result = total.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if(connection != null)
                    connection.disconnect();
            }

            return result.equals("success");
        }

        @Override
        protected void onPostExecute(Boolean result)
        {
            if(result)
                Toast.makeText(getApplicationContext(), "Save complete!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Save failed.", Toast.LENGTH_SHORT).show();
        }
    }
}
