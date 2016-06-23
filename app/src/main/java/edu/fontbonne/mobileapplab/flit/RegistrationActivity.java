package edu.fontbonne.mobileapplab.flit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText nameEditText = (EditText) findViewById(R.id.registration_name_box);
        final EditText emailEditText = (EditText) findViewById(R.id.registration_email_box);
        final EditText passwordEditText = (EditText) findViewById(R.id.registration_password_box);
        final EditText confirmEditText = (EditText) findViewById(R.id.registration_confirm_password_box);

        final Spinner spinner = (Spinner) findViewById(R.id.registration_reason_spinner);
        final TableRow otherTableRow = (TableRow) findViewById(R.id.registration_other_row);
        final EditText otherEditText = (EditText) findViewById(R.id.registration_other_box);
        Button submitButton = (Button) findViewById(R.id.registration_submit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.registration_reason_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        otherTableRow.setVisibility(View.GONE);
                        otherEditText.setText("");
                        break;
                    case 1:
                        otherTableRow.setVisibility(View.GONE);
                        otherEditText.setText("");
                        break;
                    case 2:
                        otherTableRow.setVisibility(View.GONE);
                        otherEditText.setText("");
                        break;
                    case 3:
                        otherTableRow.setVisibility(View.VISIBLE);
                        break;
                    default:
                        otherTableRow.setVisibility(View.GONE);
                        otherEditText.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] userdata = new String[6];

                String nameString = nameEditText.getText().toString();
                String emailString = emailEditText.getText().toString();
                String reasonString;
                String passwordString = passwordEditText.getText().toString();
                String confirmString = confirmEditText.getText().toString();

                switch (spinner.getSelectedItemPosition())
                {
                    case 0:
                        reasonString = getResources().getStringArray(R.array.registration_reason_array)[0];
                        break;
                    case 1:
                        reasonString = getResources().getStringArray(R.array.registration_reason_array)[1];
                        break;
                    case 2:
                        reasonString = getResources().getStringArray(R.array.registration_reason_array)[2];
                        break;
                    case 3:
                        reasonString = otherEditText.getText().toString();
                        break;
                    default:
                        reasonString = getResources().getStringArray(R.array.registration_reason_array)[0];
                }

                userdata[0] = nameString;
                userdata[1] = emailString;
                userdata[2] = reasonString;
                userdata[4] = passwordString;
                userdata[5] = confirmString;

                if (emailString.endsWith("@fontbonne.edu"))
                    userdata[3] = "1";
                else
                    userdata[3] = "0";

                if (passwordString.equals(confirmString))
                    userdata[4] = passwordString;
                else {
                    Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    return;
                }

                new RegisterTask().execute(userdata);
            }
        });
    }

    private class RegisterTask extends AsyncTask<String[], Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(String[]... params) {
            HttpURLConnection connection = null;
            String[] data = params[0];
            String result = null;
            try {
                URL url = new URL("http://primetechconsult.com/REST_PHP/insert_user.php");
                connection = (HttpURLConnection)url.openConnection();
                connection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("name", data[0])
                        .appendQueryParameter("email", data[1])
                        .appendQueryParameter("reason", data[2])
                        .appendQueryParameter("is_student", data[3])
                        .appendQueryParameter("password", data[4])
                        .appendQueryParameter("registration_date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
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
            if(result) {
                Toast.makeText(getApplicationContext(), "Registration complete!", Toast.LENGTH_SHORT).show();
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Registration failed. Try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
