package com.home.markkeen.exchangerates;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    EditText amountEditText;
    Spinner currencyFromSpinner;
    Spinner currencyToSpinner;
    TextView exchangeAmountTextView;
    ImageView flagBase;
    int[] flags = {
            R.drawable.flag_ic_aud_00,
            R.drawable.flag_ic_bgn_01,
            R.drawable.flag_ic_brl_02,
            R.drawable.flag_ic_cad_03,
            R.drawable.flag_ic_chf_04,
            R.drawable.flag_ic_cny_05,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
            R.drawable.flag_ic_holder,
    };

    static String getRatesURLA = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22";
    static String getGetRatesURLB = "%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
    String getRatesLatest;
    String getAmount;
    double getAmountDouble;

    String currencyFromType;
    String currencyToType;
    String currencyFromSubsting;
    String currencyToSubsting;

    static String currencyFromWeb = "";
    static double currencyFromWebDouble = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar actionBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(actionBar);

        amountEditText = (EditText) findViewById(R.id.amountEditText);
        currencyFromSpinner = (Spinner) findViewById(R.id.spinnerCurrencyFrom);
       // currencyToSpinner = (Spinner) findViewById(R.id.spinnerCurrencyTo);
       // exchangeAmountTextView = (TextView) findViewById(R.id.exchangeAmountTextView);
        flagBase = (ImageView) findViewById(R.id.flag_base);


        addItemExchangeRateFromSpinner();
        addItemExchangeRateToSpinner();


        setExchangeAmountOnTextChangeListener();

        new MyAsyncTask();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public void addItemExchangeRateFromSpinner() {

        currencyFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        currencyFromType = (currencyFromSpinner.getSelectedItem().toString());
                        currencyFromSubsting = currencyFromType.substring(0, 3);
                        flagBase.setImageResource(flags[position]);


                Log.v("SELECT FROM SPINNER ", currencyFromSubsting);


                getAmount = amountEditText.getText().toString();

                if (!getAmount.equals("")) {

                    new MyAsyncTask().execute();

                } else {
                    exchangeAmountTextView.setText("0.00");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addItemExchangeRateToSpinner() {

        currencyToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                currencyToType = (currencyToSpinner.getSelectedItem().toString());

                currencyToSubsting = currencyToType.substring(0, 3);

                Log.v("SELECT TO SPINNER ", currencyToSubsting);

                getAmount = amountEditText.getText().toString();

                if (!getAmount.equals("")) {

                    new MyAsyncTask().execute();

                } else {
                    exchangeAmountTextView.setText("0.00");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void setExchangeAmountOnTextChangeListener() {

        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                getAmount = amountEditText.getText().toString();

                if (!getAmount.equals("")) {

                    new MyAsyncTask().execute();

                } else {
                    exchangeAmountTextView.setText("0.00");
                }

            }
        });

    }


    private class MyAsyncTask extends AsyncTask<String, String, String>

    {


        @Override
        protected String doInBackground(String... params) {

            // HTTP Client that supports streaming uploads and downloads
            DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());

            // Define that I want to use the POST method to grab data from
            // the provided URL

            getRatesLatest = getRatesURLA + currencyFromSubsting + currencyToSubsting + getGetRatesURLB;

            HttpPost httpPost = new HttpPost(getRatesLatest);


            Log.v("HTTPS Address ", getRatesLatest);

            // Web service used is defined
            httpPost.setHeader("Content-type", "application/json");

            // Used to read data from the URL
            InputStream inputStream = null;

            // Will hold all the data gathered from the URL
            String result = null;

            try {

                // Get a response if any from the web service
                HttpResponse response = httpClient.execute(httpPost);

                // The content from the requested URL along with headers, etc.
                HttpEntity entity = response.getEntity();

                // Get the main content from the URL
                inputStream = entity.getContent();

                Log.v("INPUT STREAM ", inputStream.toString());

                // JSON is UTF-8 by default
                // BufferedReader reads data from the InputStream until the Buffer is full
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);

                //Store the data
                StringBuilder theStringBuilder = new StringBuilder();

                String line = null;

                while ((line = reader.readLine()) != null) {

                    theStringBuilder.append(line + "\n");
                }

                // Store the complete data in result
                result = theStringBuilder.toString();


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Close the InputStream when you're done with it
            finally {

                try {
                    if (inputStream != null) inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            // Holds Key Value pairs from a JSON source
            JSONObject jsonObject;

            try {


                Log.v("JSONParser RESULT ", result);


                // Get the root JSONObject
                jsonObject = new JSONObject(result);

                // Get the JSON object named query
                JSONObject queryJSONObject = jsonObject.getJSONObject("query");

                // Get the JSON object named results inside of the query object
                JSONObject resultsJSONObject = queryJSONObject.getJSONObject("results");

                // Get the JSON object named quote inside of the results object
                JSONObject currencyJSONObject = resultsJSONObject.getJSONObject("rate");


                currencyFromWeb = currencyJSONObject.getString("Rate");

                Log.v("CURRENCY FROM WEB ", currencyFromWeb);


                // NOT REQUIRED - LOGGING ONLY (see all retrieved data items)
                // GET ARRAY DATA
                JSONArray queryArray = currencyJSONObject.names();

                ArrayList<String> list = new ArrayList<String>();

                for (int i = 0; i < queryArray.length(); i++) {

                    list.add(queryArray.getString(i));
                }

                for (String item : list) {

                    Log.v("JSON ARRAY ITEMS ", item);

                }

                // END OF GET ARRAY DATA

                // Gets the first item in the JSONObject
                JSONArray objectArray = resultsJSONObject.names();

                // Prints out that first item in the JSONObject
                Log.v("JSON NEXT NODE ", objectArray.getString(0));


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            if (!getAmount.equals("")) {


                currencyFromWebDouble = Double.parseDouble(currencyFromWeb);

                getAmountDouble = Double.parseDouble(getAmount);

                Double finalAmount;

                finalAmount = getAmountDouble * currencyFromWebDouble;

                exchangeAmountTextView.setText(String.format("%.02f", finalAmount));

            } else {
                exchangeAmountTextView.setText("0.00");
            }


        }

    }
}


