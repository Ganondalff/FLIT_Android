package edu.fontbonne.mobileapplab.flit;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.json.JSONArray;
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

public class SpendingActivity extends AppCompatActivity {

    ViewFlipper spendingFlipper;
    MenuItem spendingActionAdd;
    //MenuItem spendingActionSave;

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
                    getSupportActionBar().setTitle(R.string.main_home);
                    break;
                case R.id.main_food:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.food_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    getSupportActionBar().setTitle(R.string.main_food);
                    break;
                case R.id.main_health:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.health_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    getSupportActionBar().setTitle(R.string.main_health);
                    break;
                case R.id.main_transport:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.transport_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    getSupportActionBar().setTitle(R.string.main_transport);
                    break;
                case R.id.main_debts:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.debts_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    getSupportActionBar().setTitle(R.string.main_debts);
                    break;
                case R.id.main_entertainment:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.entertainment_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    getSupportActionBar().setTitle(R.string.main_entertainment);
                    break;
                case R.id.main_clothing:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.clothing_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    getSupportActionBar().setTitle(R.string.main_clothing);
                    break;
                case R.id.main_savings:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.savings_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    getSupportActionBar().setTitle(R.string.main_savings);
                    break;
                case R.id.main_emergency:
                    spendingFlipper.addView(submenuLayoutCreate(R.array.emergency_array), 1);
                    spendingFlipper.setDisplayedChild(1);
                    getSupportActionBar().setTitle(R.string.main_emergency);
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
                getSupportActionBar().setTitle(R.string.title_activity_spending);
                break;
            case 2:
                spendingFlipper.setOutAnimation(this, R.anim.slide_out_right);
                spendingFlipper.setInAnimation(this, R.anim.slide_in_left);
                spendingFlipper.setDisplayedChild(1);
                spendingActionAdd.setVisible(false);
                //spendingActionSave.setVisible(false);
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

            TextView loadText = new TextView(getApplicationContext());
            loadText.setText("Loading...");
            loadText.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.black));
            loadText.setGravity(Gravity.CENTER_HORIZONTAL);
            spendingFlipper.addView(loadText, 2);
            spendingFlipper.setDisplayedChild(2);

            SharedPreferences user = getSharedPreferences("LoginEmail", 0);

            new SpendingRetrieveTask().execute(user.getString("email", null), ((TextView)view.findViewById(R.id.rowTitle)).getText().toString());

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spending, menu);

        spendingActionAdd = menu.findItem(R.id.spending_action_add);
        //spendingActionSave = menu.findItem(R.id.spending_action_save);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.spending_action_add) {
            return true;
        }
        if (id == R.id.spending_action_save) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class SpendingRetrieveTask extends AsyncTask<String, Void, Pair<JSONObject, String>>
    {
        @Override
        protected Pair<JSONObject, String> doInBackground(String... params) {
            HttpURLConnection connection = null;
            JSONObject result = null;
            try {
                URL url = new URL("http://primetechconsult.com/REST_PHP/get_spending.php");
                connection = (HttpURLConnection)url.openConnection();
                connection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("email", params[0]);
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
            LinearLayout linearLayout = new LinearLayout(getApplicationContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            JSONArray spendingArray;

            try
            {
                spendingArray = result.first.getJSONArray("spending");

                String[] locList = new String[spendingArray.length()];
                float[] amountList = new float[spendingArray.length()];

                for(int i = 0; i < spendingArray.length(); i++)
                {
                    locList[i] = spendingArray.getJSONObject(i).getString("location");
                    amountList[i] = (float)spendingArray.getJSONObject(i).getDouble("amount");
                }

                ListView listView = new ListView(getApplicationContext());
                Submenu2Adapter adapter = new Submenu2Adapter(getApplicationContext(), R.layout.submenu2_spending_row, locList, amountList);

                getSupportActionBar().setTitle(result.second);

                ListView.LayoutParams paramsList = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ListView.LayoutParams.MATCH_PARENT);
                listView.setLayoutParams(paramsList);
                listView.setAdapter(adapter);

                linearLayout.addView(listView);

                spendingFlipper.addView(linearLayout, 2);
                Log.i("Info", "View added.");
                spendingFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        spendingFlipper.getInAnimation().setAnimationListener(null);
                        spendingFlipper.setOutAnimation(null);
                        spendingFlipper.setInAnimation(null);
                        spendingFlipper.setDisplayedChild(2);
                        Log.i("Info", "Child set.");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                spendingActionAdd.setVisible(true);
                //spendingActionSave.setVisible(true);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

}