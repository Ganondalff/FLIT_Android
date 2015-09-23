package edu.fontbonne.mobileapplab.flit;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;

public class RegistrationActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Spinner spinner = (Spinner) findViewById(R.id.registration_reason_spinner);
        final TableRow tableRow = (TableRow) findViewById(R.id.registration_other_row);
        final EditText editText = (EditText) findViewById(R.id.registration_other_box);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.registration_reason_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        tableRow.setVisibility(View.GONE);
                        editText.setText("");
                        break;
                    case 1:
                        tableRow.setVisibility(View.GONE);
                        editText.setText("");
                        break;
                    case 2:
                        tableRow.setVisibility(View.GONE);
                        editText.setText("");
                        break;
                    case 3:
                        tableRow.setVisibility(View.VISIBLE);
                        break;
                    default:
                        tableRow.setVisibility(View.GONE);
                        editText.setText("");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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
