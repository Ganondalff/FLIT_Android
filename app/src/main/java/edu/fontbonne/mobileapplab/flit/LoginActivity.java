package edu.fontbonne.mobileapplab.flit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends Activity {

    String emailString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText emailEditText = (EditText) findViewById(R.id.login_email_box);
        final EditText passwordEditText = (EditText) findViewById(R.id.login_password_box);

        Button submit = (Button) findViewById(R.id.login_btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailString = emailEditText.getText().toString();
                String passwordString = passwordEditText.getText().toString();

                new LoginTask().execute(emailString, passwordString);
            }
        });
    }

    private class LoginTask extends AsyncTask<String, Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(String... params) {
            HttpURLConnection connection = null;
            String result = null;
            try {
                URL url = new URL("http://primetechconsult.com/REST_PHP/login_user.php");
                connection = (HttpURLConnection)url.openConnection();
                connection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("email", params[0])
                        .appendQueryParameter("password", params[1]);
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
                SharedPreferences login = getSharedPreferences("LoginEmail", 0);
                SharedPreferences.Editor editor = login.edit();
                editor.putString("email", emailString);
                editor.commit();

                Toast.makeText(getApplicationContext(), "Login complete!", Toast.LENGTH_SHORT).show();
                Intent loggedInIntent = new Intent(getApplicationContext(), LoggedInActivity.class);
                startActivity(loggedInIntent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Login failed. Check info and try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
