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
import android.widget.ListView;
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
import java.util.HashMap;


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
            R.drawable.flag_ic_czk_06,
            R.drawable.flag_ic_dkk_07,
            R.drawable.flag_ic_eur_08,
            R.drawable.flag_ic_gbp_09,
            R.drawable.flag_ic_hkd_10,
            R.drawable.flag_ic_hrk_11,
            R.drawable.flag_ic_huf_12,
            R.drawable.flag_ic_idr_13,
            R.drawable.flag_ic_ils_14,
            R.drawable.flag_ic_inr_15,
            R.drawable.flag_ic_jpy_16,
            R.drawable.flag_ic_krw_17,
            R.drawable.flag_ic_ltl_18,
            R.drawable.flag_ic_mxn_19,
            R.drawable.flag_ic_nok_20,
            R.drawable.flag_ic_nzd_21,
            R.drawable.flag_ic_php_22,
            R.drawable.flag_ic_pln_23,
            R.drawable.flag_ic_ron_24,
            R.drawable.flag_ic_rub_25,
            R.drawable.flag_ic_sek_26,
            R.drawable.flag_ic_sgd_27,
            R.drawable.flag_ic_thb_28,
            R.drawable.flag_ic_try_29,
            R.drawable.flag_ic_usd_30,
            R.drawable.flag_ic_zar_31,
    };

    private CustomAdapter customAdapter;

    private ListView listView;





    static String getRatesURLA = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22";
    static String getGetRatesURLB = "%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
    String getRatesLatest;
    String getAmount;
    String[] rateArray;
    double getAmountDouble;

    String currencyFromType;
    String currencyFromSubsting;
    String currencyToSubsting;

    static String currencyFromWeb = "";
    static double[] currencyFromWebDouble;


    ArrayList<HashMap<String, String>> flagAndCurrencyList = new ArrayList<HashMap<String, String>>();



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
        //addItemExchangeRateToSpinner();

       setExchangeAmountOnTextChangeListener();


        new MyAsyncTask();

        populatedArrayList();

        // create instance of customAdapter which extends ArrayAdapter (CustomAdapter.java)
        customAdapter = new CustomAdapter(getApplication(), flagAndCurrencyList);
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(customAdapter);

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

           /*     if (!getAmount.equals("")) {

                    new MyAsyncTask().execute();

                } else {
                    exchangeAmountTextView.setText("0.00");
                }
            */
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

            /*    if (!getAmount.equals("")) {

                    new MyAsyncTask().execute();

                } else {
                    exchangeAmountTextView.setText("0.00");
                }
            */
            }
        });

    }





    public ArrayList<HashMap<String, String>> populatedArrayList() {

        String[] flag = {
                "flag_ic_aud_00",
                "flag_ic_bgn_01",
                "flag_ic_brl_02",
                "flag_ic_cad_03",
                "flag_ic_chf_04",
                "flag_ic_cny_05",
                "flag_ic_czk_06",
                "flag_ic_dkk_07",
                "flag_ic_eur_08",
                "flag_ic_gbp_09",
                "flag_ic_hkd_10",
                "flag_ic_hrk_11",
                "flag_ic_huf_12",
                "flag_ic_idr_13",
                "flag_ic_ils_14",
                "flag_ic_inr_15",
                "flag_ic_jpy_16",
                "flag_ic_krw_17",
                "flag_ic_ltl_18",
                "flag_ic_mxn_19",
                "flag_ic_nok_20",
                "flag_ic_nzd_21",
                "flag_ic_php_22",
                "flag_ic_pln_23",
                "flag_ic_ron_24",
                "flag_ic_rub_25",
                "flag_ic_sek_26",
                "flag_ic_sgd_27",
                "flag_ic_thb_28",
                "flag_ic_try_29",
                "flag_ic_usd_30",
                "flag_ic_zar_31" };

        String[] currencyCode = {
                "AUD",
                "BGN",
                "BRL",
                "CAD",
                "CHF",
                "CNY",
                "CZK",
                "DKK",
                "EUR",
                "GBP",
                "HKD",
                "HRK",
                "HUF",
                "IDR",
                "ILS",
                "INR",
                "JPY",
                "KRW",
                "LTL",
                "MXN",
                "NOK",
                "NZD",
                "PHP",
                "PLN",
                "RON",
                "RUB",
                "SEK",
                "SGD",
                "THB",
                "TRY",
                "USD",
                "ZAR" };

        String[] currency = {
                "Australian Dollar",
                "Bulgarian Lev",
                "Brazilian Real",
                "Canadian Dollar",
                "CH Francs",
                "Chinese Yuan",
                "Czech Koruna",
                "Danish Krone",
                "Euro",
                "British Pound",
                "Hong Kong Dollar",
                "Croatian Kuna",
                "Hungarian Forint",
                "Indonesian Rupiah",
                "Israeli Shekel",
                "Indian Rupee",
                "Japanese Yen",
                "Korean Won",
                "Lithuanian Litas",
                "Mexican Peso",
                "Norwegian Krone",
                "New Zealand Dollar",
                "Philippine Peso",
                "Polish NEW Zloty",
                "Romanian Leu",
                "Russian Rouble",
                "Swedish Krona",
                "Singapore Dollar",
                "Thai Baht",
                "New Turkish Lira",
                "United States Dollar",
                "South African Rand" };



        for (int i = 0; i < flag.length; i++){

            HashMap<String, String> currencyFlagList = new HashMap<String, String>();

            currencyFlagList.put("flagType", flag[i]);
            currencyFlagList.put("currencyCode", currencyCode[i]);
            currencyFlagList.put("currencyType", currency[i]);

            flagAndCurrencyList.add(currencyFlagList);

            Log.v("CURRENCY Code", currencyCode[i]);
            Log.v("CURRENCY TYPE", currency[i]);
            Log.v("FLAG TYPE", flag[i]);
        }


        return flagAndCurrencyList;
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

                // Get the JSON object named rate inside of the results object
               // JSONObject currencyJSONObject = resultsJSONObject.getJSONObject("rate");


                // Get the JSON array named rate inside of the results object
                JSONArray jsonArray = resultsJSONObject.getJSONArray("rate");
                int arrayLength = jsonArray.length();

                for (int i = 0; i < arrayLength; i++){

                     JSONObject currencyJSONObject = jsonArray.getJSONObject(i);

                    rateArray[i] = currencyJSONObject.getString("Rate");

                    Log.v("CURRENCY FROM WEB ", rateArray[i]);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            if (!getAmount.equals("")) {

                for (int i = 0; i < rateArray.length; i++) {

                    currencyFromWebDouble[i] = Double.parseDouble(rateArray[i]);

                }


            }

        }

    }
}


