package com.home.markkeen.exchangerates;

import android.content.Context;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import android.content.SharedPreferences;
=======
>>>>>>> context_menu_on_listview
=======
import android.content.Intent;
import android.content.SharedPreferences;
>>>>>>> currencies_activity
=======
>>>>>>> context_menu_on_listview
=======
>>>>>>> context_menu_on_listview
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import android.widget.Toast;
import android.widget.ToggleButton;
=======
>>>>>>> context_menu_on_listview
=======
>>>>>>> currencies_activity
=======
>>>>>>> context_menu_on_listview
=======
>>>>>>> context_menu_on_listview

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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.Arrays;
=======
>>>>>>> context_menu_on_listview
=======
import java.util.Arrays;
>>>>>>> currencies_activity
=======
>>>>>>> context_menu_on_listview
=======
>>>>>>> context_menu_on_listview
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    EditText amountEditText;
    Spinner currencyFromSpinner;
    ImageView flagBase;

    int[] flags = {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> currencies_activity
            // holder flag for 'Choose a base currency' position in Select currency from web spinner
            R.drawable.flag_ic_00_empty,
            R.drawable.flag_ic_aud_00, R.drawable.flag_ic_bgn_01, R.drawable.flag_ic_brl_02,
            R.drawable.flag_ic_cad_03, R.drawable.flag_ic_chf_04, R.drawable.flag_ic_cny_05,
            R.drawable.flag_ic_czk_06, R.drawable.flag_ic_dkk_07, R.drawable.flag_ic_eur_08,
            R.drawable.flag_ic_gbp_09, R.drawable.flag_ic_hkd_10, R.drawable.flag_ic_hrk_11,
            R.drawable.flag_ic_huf_12, R.drawable.flag_ic_idr_13, R.drawable.flag_ic_ils_14,
            R.drawable.flag_ic_inr_15, R.drawable.flag_ic_jpy_16, R.drawable.flag_ic_krw_17,
            R.drawable.flag_ic_ltl_18, R.drawable.flag_ic_mxn_19, R.drawable.flag_ic_nok_20,
            R.drawable.flag_ic_nzd_21, R.drawable.flag_ic_php_22, R.drawable.flag_ic_pln_23,
            R.drawable.flag_ic_ron_24, R.drawable.flag_ic_rub_25, R.drawable.flag_ic_sek_26,
            R.drawable.flag_ic_sgd_27, R.drawable.flag_ic_thb_28, R.drawable.flag_ic_try_29,
            R.drawable.flag_ic_usd_30, R.drawable.flag_ic_zar_31,
<<<<<<< HEAD
    };

    SharedPreferences sharedPreferences;

    private CustomAdapter customAdapter;
=======
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
<<<<<<< HEAD
    };

    private CustomAdapter customAdapter;

<<<<<<< HEAD
>>>>>>> context_menu_on_listview
=======
    };

    SharedPreferences sharedPreferences;

    private CustomAdapter customAdapter;
>>>>>>> currencies_activity
=======
>>>>>>> context_menu_on_listview
=======
    };

    private CustomAdapter customAdapter;

>>>>>>> context_menu_on_listview
    private ListView listView;

    static String getRatesURLA = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22";
    static String getGetRatesURLB = "%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
    String getRatesLatest;
    String getRatesFinal;
    String getAmount;
    double getAmountAsDouble;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

    String[] items;
    int[] positionsToRemove;
    String removedPositions;

    String[] pinnedItems;
    int[] pinnedPositions;
    String pinnedPositionsToKeep;

    double[] convertedAmount = new double[32];
    double[] finalConvertedAmount = new double[32];
=======
    double [] convertedAmount = new double[32];
    double [] finalConvertedAmount = new double[32];
>>>>>>> context_menu_on_listview
=======
    String[] items;
    int[] positionsToRemove;
    String removedPositions;
    double[] convertedAmount = new double[32];
    double[] finalConvertedAmount = new double[32];
>>>>>>> currencies_activity
=======
    double [] convertedAmount = new double[32];
    double [] finalConvertedAmount = new double[32];
>>>>>>> context_menu_on_listview
=======
    double [] convertedAmount = new double[32];
    double [] finalConvertedAmount = new double[32];
>>>>>>> context_menu_on_listview
    String[] rateArray = new String[32];
    String[] finalConvertedAmountText = new String[32];
    String[] currency;
    String[] flag;
    String[] currencyCode;
    String currencyFromType;
    String currencyFromSubsting;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    ToggleButton menuPinToggleButton;
    ImageView pinToggle;

    // int's for saving position of the listView
    int indexPosition;
    int top;
=======
    int test = 0;
=======
    int test = 0;

    ArrayList<HashMap<String, String>> flagAndCurrencyList = new ArrayList<HashMap<String, String>>();
>>>>>>> context_menu_on_listview

    ArrayList<HashMap<String, String>> flagAndCurrencyList = new ArrayList<HashMap<String, String>>();
>>>>>>> context_menu_on_listview

    ArrayList<HashMap<String, String>> flagAndCurrencyList = new ArrayList<HashMap<String, String>>();
=======
=======
    int test = 0;

    ArrayList<HashMap<String, String>> flagAndCurrencyList = new ArrayList<HashMap<String, String>>();
>>>>>>> context_menu_on_listview

    ArrayList<HashMap<String, String>> flagAndCurrencyList = new ArrayList<HashMap<String, String>>();
>>>>>>> currencies_activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // added to every activity when including the Toolbar layout
        Toolbar actionBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(actionBar);

        amountEditText = (EditText) findViewById(R.id.amountEditText);
        currencyFromSpinner = (Spinner) findViewById(R.id.spinnerCurrencyFrom);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

        flagBase = (ImageView) findViewById(R.id.flag_base);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_MULTI_PROCESS);

        // get shared prefs for pinned positions string (shared preferences were initialised onCreate all these key pairs come under "MyPrefs"
        pinnedPositionsToKeep = sharedPreferences.getString("PINNED_POSITIONS_TO_KEEP", "");

=======

        flagBase = (ImageView) findViewById(R.id.flag_base);

>>>>>>> context_menu_on_listview
=======

        flagBase = (ImageView) findViewById(R.id.flag_base);

>>>>>>> currencies_activity
=======

        flagBase = (ImageView) findViewById(R.id.flag_base);

>>>>>>> context_menu_on_listview
=======

        flagBase = (ImageView) findViewById(R.id.flag_base);

>>>>>>> context_menu_on_listview
        addItemExchangeRateFromSpinner();

        setExchangeAmountOnTextChangeListener();

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        populatedArrayList();

        pinToggle = (ImageView) findViewById(R.id.list_view_pin);

        // create instance of customAdapter which extends ArrayAdapter (CustomAdapter.java)
        customAdapter = new CustomAdapter(getApplication(), flagAndCurrencyList);
=======
        populatedArrayList();

        // create instance of customAdapter which extends ArrayAdapter (CustomAdapter.java)
        customAdapter = new CustomAdapter(getApplication(), flagAndCurrencyList);


>>>>>>> currencies_activity
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(customAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

<<<<<<< HEAD
=======
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_MULTI_PROCESS);
>>>>>>> currencies_activity
        removedPositions = sharedPreferences.getString("POSITIONS_TO_REMOVE", "");

        if (removedPositions.contains("[")) {

            // function to change string which contains list of number in format [1,2,3,4] back to int array
            // remove [ ] from beginning of string

            items = removedPositions.substring(1, removedPositions.length() - 1).split(",");
            positionsToRemove = new int[items.length];

            for (int i = 0; i < items.length; i++) {

                positionsToRemove[i] = Integer.parseInt(items[i].trim());
            }

            for (int i = 0; i < positionsToRemove.length; i++) {
                Log.v("SAVED POSITIONS", String.valueOf(positionsToRemove[i]));
            }

        }

        items = new String[0];
<<<<<<< HEAD
        customAdapter.clear();
        populatedArrayList();
=======

        customAdapter.clear();

        populatedArrayList();

>>>>>>> currencies_activity
        customAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
<<<<<<< HEAD
=======
=======
>>>>>>> context_menu_on_listview
=======
>>>>>>> context_menu_on_listview

        new MyAsyncTask();
>>>>>>> context_menu_on_listview


        populatedArrayList();


        // create instance of customAdapter which extends ArrayAdapter (CustomAdapter.java)
        customAdapter = new CustomAdapter(getApplication(), flagAndCurrencyList);
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(customAdapter);
=======
>>>>>>> currencies_activity


        populatedArrayList();


        // create instance of customAdapter which extends ArrayAdapter (CustomAdapter.java)
        customAdapter = new CustomAdapter(getApplication(), flagAndCurrencyList);
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(customAdapter);


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

        // inflate my special menu toggle button, add item to menu, then set the layout xml file i've created to inflate into that menu posotion
        MenuItem menuPinToggle = menu.findItem(R.id.menu_pin_toggle_id);
        menuPinToggle.setActionView(R.layout.menu_pin_toggle_layout);

        // initialise the ToggleButton which is in the menuItem, which has my inflated layout (which the button is in)
        menuPinToggleButton = (ToggleButton) menu.findItem(R.id.menu_pin_toggle_id).getActionView().findViewById(R.id.menu_main_activity_pin_toggle);

        menuPinToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (menuPinToggleButton.isChecked()) {

                    // get the listview first visible position (i will get the index position and not a partial position)
                    indexPosition = listView.getFirstVisiblePosition();
                    // to factor the listview for a partial scrolled position this works out the difference between
                    // index item 0 and current top view - set when going back to  pinToggle OFF
                    top = listView.getChildAt(0).getTop();


                    // get shared prefs for pinned positions string (shared preferences were initialised onCreate all these key pairs come under "MyPrefs"
                    pinnedPositionsToKeep = sharedPreferences.getString("PINNED_POSITIONS_TO_KEEP", "");

                    if (pinnedPositionsToKeep.contains("[")) {

                        // function to change string which contains list of number in format [1,2,3,4] back to int array
                        // remove [ ] from beginning of string

                        pinnedItems = pinnedPositionsToKeep.substring(1, pinnedPositionsToKeep.length() - 1).split(",");
                        pinnedPositions = new int[pinnedItems.length];

                        for (int i = 0; i < pinnedItems.length; i++) {

                            pinnedPositions[i] = Integer.parseInt(pinnedItems[i].trim());
                        }

                        for (int i = 0; i < pinnedPositions.length; i++) {
                            Log.v("SAVED POSITIONS", String.valueOf(pinnedPositions[i]));
                        }
                    }

                    else {

                        pinnedPositions = new int[32];
                    }
                        customAdapter.clear();
                        populatedArrayList();
                        customAdapter.notifyDataSetChanged();
                }

                if (!menuPinToggleButton.isChecked()) {

                    Toast.makeText(getApplication(), "unchecked", Toast.LENGTH_SHORT).show();

                    customAdapter.clear();
                    populatedArrayList();
                    customAdapter.notifyDataSetChanged();

                    // get the previous listView position
                    listView.setSelectionFromTop(indexPosition, top);
                }
                pinnedItems = new String[0];
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_choose_currencies) {

         //   Intent intent = new Intent(this, CurrencyActivity.class);
         //  startActivity(intent);
         //   return true;
        }
        if (id == R.id.action_reset_pinned) {

        // reset the pinnedPositions int[]
        pinnedPositions = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        // remove the save pinned positions string from shared Prefs
        sharedPreferences.edit().remove("PINNED_POSITIONS_TO_KEEP").apply();

        // reset the pinnedPositionsToKeep string from the customAdapter
        customAdapter.pinnedPositionsToKeep = "";

        // reset the positionsToPin int[] from the customAdapter
        customAdapter.positionsToPin = new int[32];

        customAdapter.clear();

        populatedArrayList();
        customAdapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_choose_currencies){
            return true;
        }
        if (id == R.id.action_choose_currencies) {

            Intent intent = new Intent(this, CurrencyActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_choose_currencies){
            return true;
        }
        if (id == R.id.action_choose_currencies){
            return true;
        }

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void addItemExchangeRateFromSpinner() {

        currencyFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                // set flag according to spinner position and currency/country
                flagBase.setImageResource(flags[position]);
=======
=======
>>>>>>> context_menu_on_listview
=======
>>>>>>> context_menu_on_listview
                currencyFromType = (currencyFromSpinner.getSelectedItem().toString());
                currencyFromSubsting = currencyFromType.substring(0, 3);
                flagBase.setImageResource(flags[position]);

>>>>>>> context_menu_on_listview

                // position 0 is the initial 'Choose a base currency' - no action required, only do if it is not that one!
                if (!currencyFromSpinner.getSelectedItem().toString().equals("Choose a base currency")) {
=======
                // set flag according to spinner position and currency/country
                flagBase.setImageResource(flags[position]);

                // position 0 is the initial 'Choose a base currency' - no action required, only do if it is not that one!
                if (!currencyFromSpinner.getSelectedItem().toString().equals("Choose a base currency")) {

>>>>>>> currencies_activity

<<<<<<< HEAD
<<<<<<< HEAD
                    currencyFromType = (currencyFromSpinner.getSelectedItem().toString());
                    currencyFromSubsting = currencyFromType.substring(0, 3);
=======
              if (!getAmount.equals("")) {
=======
              if (!getAmount.equals("")) {

                    getAmountAsDouble = Double.parseDouble(getAmount);
>>>>>>> context_menu_on_listview

                    getAmountAsDouble = Double.parseDouble(getAmount);
>>>>>>> context_menu_on_listview

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                    currencyFromType = (currencyFromSpinner.getSelectedItem().toString());
                    currencyFromSubsting = currencyFromType.substring(0, 3);
=======
>>>>>>> currencies_activity

<<<<<<< HEAD

                    Log.v("SELECT FROM SPINNER ", currencyFromSubsting);


                    getAmount = amountEditText.getText().toString();

                    Log.v("GET AMOUNT", getAmount);

                    if (!getAmount.equals("")) {

                        getAmountAsDouble = Double.parseDouble(getAmount);

                        new MyAsyncTask().execute();

                    } else {

                        getAmount = "0.00";

                        getAmountAsDouble = Double.parseDouble(getAmount);

                        new MyAsyncTask().execute();

                    }
                }
<<<<<<< HEAD
=======
                getAmount = amountEditText.getText().toString();

              if (!getAmount.equals("")) {

                    getAmountAsDouble = Double.parseDouble(getAmount);

                    new MyAsyncTask().execute();

                } else {

                  getAmount = "0.00";

                  getAmountAsDouble = Double.parseDouble(getAmount);

                  new MyAsyncTask().execute();
              }

>>>>>>> context_menu_on_listview
=======
>>>>>>> currencies_activity
=======
                } else {

                  getAmount = "0.00";

                  getAmountAsDouble = Double.parseDouble(getAmount);

                  new MyAsyncTask().execute();
              }

>>>>>>> context_menu_on_listview
=======
                } else {

                  getAmount = "0.00";

                  getAmountAsDouble = Double.parseDouble(getAmount);

                  new MyAsyncTask().execute();
              }

>>>>>>> context_menu_on_listview

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

                // check if a base currency has been set otherwise http: requests will be done against a 'null' currency - inefficient
                if (!currencyFromSpinner.getSelectedItem().toString().equals("Choose a base currency")) {
<<<<<<< HEAD

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                    getAmount = amountEditText.getText().toString();
=======
               if (!getAmount.equals("")) {
=======
               if (!getAmount.equals("")) {
=======
               if (!getAmount.equals("")) {

                   getAmountAsDouble = Double.parseDouble(getAmount);
>>>>>>> context_menu_on_listview

                   getAmountAsDouble = Double.parseDouble(getAmount);
>>>>>>> context_menu_on_listview

<<<<<<< HEAD
                   getAmountAsDouble = Double.parseDouble(getAmount);
>>>>>>> context_menu_on_listview

<<<<<<< HEAD
                    if (!getAmount.equals("")) {

<<<<<<< HEAD
                        getAmountAsDouble = Double.parseDouble(getAmount);

                        new MyAsyncTask().execute();

                    } else {

=======

                    getAmount = amountEditText.getText().toString();

                    if (!getAmount.equals("")) {

                        getAmountAsDouble = Double.parseDouble(getAmount);

                        new MyAsyncTask().execute();

                    } else {

>>>>>>> currencies_activity
                        getAmount = "0.00";

                        getAmountAsDouble = Double.parseDouble(getAmount);

                        new MyAsyncTask().execute();
                    }
                }
=======
=======
>>>>>>> context_menu_on_listview
=======
>>>>>>> context_menu_on_listview
                } else {

                   getAmount = "0.00";

                   getAmountAsDouble = Double.parseDouble(getAmount);

                   new MyAsyncTask().execute();
               }
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> context_menu_on_listview
=======
>>>>>>> context_menu_on_listview
=======
>>>>>>> context_menu_on_listview

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
            getRatesLatest = getRatesURLA;

            // loop round all the country codes concatenating into one big URL string
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> currencies_activity
            for (int i = 0; i < currencyCode.length; i++) {

                getRatesLatest = getRatesLatest + currencyFromSubsting + currencyCode[i] + "%22%2C%22";

            }

            // remove last "%22%2C%22" reassigning the string using substring to minus 9 characters
            getRatesLatest = getRatesLatest.substring(0, getRatesLatest.length() - 9);

            getRatesFinal = getRatesLatest + getGetRatesURLB;
<<<<<<< HEAD
=======
            for (int i = 0; i < currencyCode.length; i++){

                getRatesLatest = getRatesLatest + currencyFromSubsting + currencyCode[i] + "%22%2C%22";

            }

            // remove last "%22%2C%22" reassigning the string using substring to minus 9 characters
            getRatesLatest = getRatesLatest.substring(0, getRatesLatest.length() - 9);

            getRatesFinal = getRatesLatest + getGetRatesURLB;
>>>>>>> context_menu_on_listview


            HttpPost httpPost = new HttpPost(getRatesFinal);


<<<<<<< HEAD
=======
            for (int i = 0; i < currencyCode.length; i++){
=======


            HttpPost httpPost = new HttpPost(getRatesFinal);
>>>>>>> currencies_activity

                getRatesLatest = getRatesLatest + currencyFromSubsting + currencyCode[i] + "%22%2C%22";

            }

            // remove last "%22%2C%22" reassigning the string using substring to minus 9 characters
            getRatesLatest = getRatesLatest.substring(0, getRatesLatest.length() - 9);

            getRatesFinal = getRatesLatest + getGetRatesURLB;


            HttpPost httpPost = new HttpPost(getRatesFinal);

<<<<<<< HEAD

>>>>>>> context_menu_on_listview
=======
>>>>>>> currencies_activity
=======
>>>>>>> context_menu_on_listview
=======
            for (int i = 0; i < currencyCode.length; i++){

                getRatesLatest = getRatesLatest + currencyFromSubsting + currencyCode[i] + "%22%2C%22";

            }

            // remove last "%22%2C%22" reassigning the string using substring to minus 9 characters
            getRatesLatest = getRatesLatest.substring(0, getRatesLatest.length() - 9);

            getRatesFinal = getRatesLatest + getGetRatesURLB;


            HttpPost httpPost = new HttpPost(getRatesFinal);


>>>>>>> context_menu_on_listview
            Log.v("HTTPS Address ", getRatesFinal);

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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> context_menu_on_listview
=======
>>>>>>> context_menu_on_listview


                // Get the JSON array named rate inside of the results object
                JSONArray jsonArray = resultsJSONObject.getJSONArray("rate");
                int arrayLength = jsonArray.length();

                for (int i = 0; i < arrayLength; i++) {

                    JSONObject currencyJSONObject = jsonArray.getJSONObject(i);

                    rateArray[i] = currencyJSONObject.getString("Rate");

                    Log.v("CURRENCY FROM WEB ", rateArray[i]);
                }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> context_menu_on_listview

            } catch (JSONException e) {
                e.printStackTrace();
            }

<<<<<<< HEAD
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            for (int i = 0; i < rateArray.length; i++) {

                convertedAmount[i] = Double.parseDouble(rateArray[i]);

                finalConvertedAmount[i] = (convertedAmount[i] * getAmountAsDouble);
            }

            for (int i = 0; i < rateArray.length; i++) {

                finalConvertedAmountText[i] = String.valueOf((String.format("%.02f", finalConvertedAmount[i])));

                Log.v("FINAL CONVERTED AMOUNT FOR UPDATING LIST VIEW", finalConvertedAmountText[i]);
=======
=======
=======

            return result;
        }

        @Override
        protected void onPostExecute(String result) {



            for (int i = 0; i < rateArray.length; i++){

                convertedAmount[i] = Double.parseDouble(rateArray[i]);
>>>>>>> context_menu_on_listview

                finalConvertedAmount[i] = (convertedAmount[i] * getAmountAsDouble);
            }

<<<<<<< HEAD
                // Get the JSON array named rate inside of the results object
                JSONArray jsonArray = resultsJSONObject.getJSONArray("rate");
                int arrayLength = jsonArray.length();

                for (int i = 0; i < arrayLength; i++) {

                    JSONObject currencyJSONObject = jsonArray.getJSONObject(i);

                    rateArray[i] = currencyJSONObject.getString("Rate");

                    Log.v("CURRENCY FROM WEB ", rateArray[i]);
                }
=======

            } catch (JSONException e) {
                e.printStackTrace();
=======
            for (int i = 0; i < rateArray.length; i++){

                finalConvertedAmountText[i] = String.valueOf((String.format("%.02f",finalConvertedAmount[i])));

                Log.v("FINAL CONVERTED AMOUNT FOR UPDATING LISTVIEW", finalConvertedAmountText[i]);
>>>>>>> context_menu_on_listview
            }

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(amountEditText.getWindowToken(), 0);

            customAdapter.clear();

            populatedArrayList();

<<<<<<< HEAD
=======
            customAdapter.notifyDataSetChanged();
>>>>>>> context_menu_on_listview

            // after initial populated list change test to 1, so that each time from now on it updates
            // the amount to convert from the text, and spinner input (see 'if / else' statements)
            // the only way to update the query/list is to change the input amount & spinner
            test = 1;

<<<<<<< HEAD
            for (int i = 0; i < rateArray.length; i++){

                convertedAmount[i] = Double.parseDouble(rateArray[i]);

                finalConvertedAmount[i] = (convertedAmount[i] * getAmountAsDouble);
            }
>>>>>>> context_menu_on_listview

            for (int i = 0; i < rateArray.length; i++){

                finalConvertedAmountText[i] = String.valueOf((String.format("%.02f",finalConvertedAmount[i])));

                Log.v("FINAL CONVERTED AMOUNT FOR UPDATING LISTVIEW", finalConvertedAmountText[i]);
            }

<<<<<<< HEAD
            return result;
        }
=======
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(amountEditText.getWindowToken(), 0);

            customAdapter.clear();
>>>>>>> context_menu_on_listview

            populatedArrayList();

<<<<<<< HEAD
            for (int i = 0; i < rateArray.length; i++) {

                convertedAmount[i] = Double.parseDouble(rateArray[i]);

                finalConvertedAmount[i] = (convertedAmount[i] * getAmountAsDouble);
            }

            for (int i = 0; i < rateArray.length; i++) {

                finalConvertedAmountText[i] = String.valueOf((String.format("%.02f", finalConvertedAmount[i])));
>>>>>>> currencies_activity

                Log.v("FINAL CONVERTED AMOUNT FOR UPDATING LIST VIEW", finalConvertedAmountText[i]);
            }

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(amountEditText.getWindowToken(), 0);

            customAdapter.clear();

            populatedArrayList();

<<<<<<< HEAD
=======
            customAdapter.notifyDataSetChanged();
>>>>>>> currencies_activity

        }

<<<<<<< HEAD
            for (int i = 0; i < rateArray.length; i++){

                convertedAmount[i] = Double.parseDouble(rateArray[i]);

                finalConvertedAmount[i] = (convertedAmount[i] * getAmountAsDouble);
            }

            for (int i = 0; i < rateArray.length; i++){

                finalConvertedAmountText[i] = String.valueOf((String.format("%.02f",finalConvertedAmount[i])));

                Log.v("FINAL CONVERTED AMOUNT FOR UPDATING LISTVIEW", finalConvertedAmountText[i]);
>>>>>>> context_menu_on_listview
            }

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(amountEditText.getWindowToken(), 0);
<<<<<<< HEAD

            customAdapter.clear();

            populatedArrayList();

            customAdapter.notifyDataSetChanged();

        }

    }


    public ArrayList<HashMap<String, String>> populatedArrayList() {

        flag = new String[]{
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
                "flag_ic_zar_31"};
        Arrays.sort(flag);

        currencyCode = new String[]{
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
                "ZAR"};
        Arrays.sort(currencyCode);

        currency = new String[]{
                "00 Australian Dollar",
                "01 Bulgarian Lev",
                "02 Brazilian Real",
                "03 Canadian Dollar",
                "04 Swiss Franc",
                "05 Chinese Yuan",
                "06 Czech Koruna",
                "07 Danish Krone",
                "08 Euro",
                "09 British Pound",
                "10 Hong Kong Dollar",
                "11 Croatian Kuna",
                "12 Hungarian Forint",
                "13 Indonesian Rupiah",
                "14 Israeli Shekel",
                "15 Indian Rupee",
                "16 Japanese Yen",
                "17 Korean Won",
                "18 Lithuanian Litas",
                "19 Mexican Peso",
                "20 Norwegian Krone",
                "21 New Zealand Dollar",
                "22 Philippine Peso",
                "23 Polish NEW Zloty",
                "24 Romanian Leu",
                "25 Russian Rouble",
                "26 Swedish Krona",
                "27 Singapore Dollar",
                "28 Thai Baht",
                "29 New Turkish Lira",
                "30 United States Dollar",
                "31 South African Rand"};
        Arrays.sort(currency);

        for (int i = 0; i < flag.length; i++) {

            HashMap<String, String> currencyFlagList = new HashMap<String, String>();

            currencyFlagList.put("flagType", flag[i]);
            currencyFlagList.put("currencyCode", currencyCode[i]);
            currencyFlagList.put("currencyType", currency[i]);
            currencyFlagList.put("finalConvertedAmountText", finalConvertedAmountText[i]);


            flagAndCurrencyList.add(currencyFlagList);

            Log.v("CURRENCY Code", currencyCode[i]);
            Log.v("CURRENCY TYPE", currency[i]);
            Log.v("FLAG TYPE", flag[i]);

        }

        // Remove positions from currencyActivity from retrieved int[] positionsToRemove from onResume() and from menuPinToggle
        // Make sure there is something in the String passed from CurrencyActivity otherwise null pointer exception (try/catch)
        // Reverse loop from 31 to 0 (32 positions), otherwise positions in the ArrayList<HashMap> flagAndCurrencyList reshuffle in lower indexes before higher ones!

        try {
            for (int i = 31; i >= 0; i--) {

                // check to see if the menuToggle is checked
                if (menuPinToggleButton.isChecked()) {

                    if (pinnedPositions[i] == 0) {

                        flagAndCurrencyList.remove(i);
                    }

                }

                // if the menuToggle is NOT checked
                else {

                    if (positionsToRemove[i] != 0) {

                        // remove position - minus 1 because array has ALL zero's in 32 holders (default),
                        // so + 1 was added when storing it originally from CurrencyAdapter, then into SharedPreferences
                        flagAndCurrencyList.remove((positionsToRemove[i] - 1));
                    }
                }
            }

        } catch (NullPointerException e) {
=======

            customAdapter.clear();

            populatedArrayList();

            customAdapter.notifyDataSetChanged();

            // after initial populated list change test to 1, so that each time from now on it updates
            // the amount to convert from the text, and spinner input (see 'if / else' statements)
            // the only way to update the query/list is to change the input amount & spinner
            test = 1;



        }


    }

=======
            customAdapter.notifyDataSetChanged();

            // after initial populated list change test to 1, so that each time from now on it updates
            // the amount to convert from the text, and spinner input (see 'if / else' statements)
            // the only way to update the query/list is to change the input amount & spinner
            test = 1;



        }


    }

>>>>>>> context_menu_on_listview
=======


        }


    }

>>>>>>> context_menu_on_listview


    public ArrayList<HashMap<String, String>> populatedArrayList() {

        flag = new String[]{
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
                "flag_ic_zar_31"};

        currencyCode = new String[] {
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
                "ZAR"};

        currency = new String[] {
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
                "South African Rand"};



        for (int i = 0; i < flag.length; i++) {

            HashMap<String, String> currencyFlagList = new HashMap<String, String>();

            currencyFlagList.put("flagType", flag[i]);
            currencyFlagList.put("currencyCode", currencyCode[i]);
            currencyFlagList.put("currencyType", currency[i]);

            // add the returned values from the http query, only if the populated rate array is the same
            // test value is for first time run, initially set to zero, so on startup the 'else' statement is true
            // but once an Amount has been entered, the if statement becomes 'true' - otherwise MyAsyncTask will have null pointer exception
            if (test != 0) {
                currencyFlagList.put("finalConvertedAmountText", finalConvertedAmountText[i]);
            } else {

                currencyFlagList.put("finalConvertedAmountText", (finalConvertedAmountText[i] = "0.00"));
            }

            flagAndCurrencyList.add(currencyFlagList);
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> context_menu_on_listview
=======
    }


    public ArrayList<HashMap<String, String>> populatedArrayList() {

        flag = new String[]{
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
                "flag_ic_zar_31"};

        Arrays.sort(flag);

        currencyCode = new String[]{
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
                "ZAR"};

        Arrays.sort(currencyCode);

        currency = new String[]{
                "00 Australian Dollar",
                "01 Bulgarian Lev",
                "02 Brazilian Real",
                "03 Canadian Dollar",
                "04 CH Francs",
                "05 Chinese Yuan",
                "06 Czech Koruna",
                "07 Danish Krone",
                "08 Euro",
                "09 British Pound",
                "10 Hong Kong Dollar",
                "11 Croatian Kuna",
                "12 Hungarian Forint",
                "13 Indonesian Rupiah",
                "14 Israeli Shekel",
                "15 Indian Rupee",
                "16 Japanese Yen",
                "17 Korean Won",
                "18 Lithuanian Litas",
                "19 Mexican Peso",
                "20 Norwegian Krone",
                "21 New Zealand Dollar",
                "22 Philippine Peso",
                "23 Polish NEW Zloty",
                "24 Romanian Leu",
                "25 Russian Rouble",
                "26 Swedish Krona",
                "27 Singapore Dollar",
                "28 Thai Baht",
                "29 New Turkish Lira",
                "30 United States Dollar",
                "31 South African Rand"};

        Arrays.sort(currency);


        for (int i = 0; i < flag.length; i++) {

            HashMap<String, String> currencyFlagList = new HashMap<String, String>();

            currencyFlagList.put("flagType", flag[i]);
            currencyFlagList.put("currencyCode", currencyCode[i]);
            currencyFlagList.put("currencyType", currency[i]);

            // add the returned values from the http query, only if the populated rate array is the same

                currencyFlagList.put("finalConvertedAmountText", finalConvertedAmountText[i]);


            flagAndCurrencyList.add(currencyFlagList);

            Log.v("CURRENCY Code", currencyCode[i]);
            Log.v("CURRENCY TYPE", currency[i]);
            Log.v("FLAG TYPE", flag[i]);
=======
>>>>>>> context_menu_on_listview

=======

>>>>>>> context_menu_on_listview
            Log.v("CURRENCY Code", currencyCode[i]);
            Log.v("CURRENCY TYPE", currency[i]);
            Log.v("FLAG TYPE", flag[i]);
            Log.v("RATE", finalConvertedAmountText[i]);
        }

<<<<<<< HEAD
<<<<<<< HEAD
        // Remove positions from currencyActivity from retrieved int[] positionsToRemove from onResume()
        // Make sure there is something in the String passed from CurrencyActivity otherwise null pointer exception
        // as the PositionsToRemove array won't have been initialised i.e. int[] myInt = new int[32]
        // Reverse loop from 31 to 0 (32 positions), otherwise positions in the ArrayList<HashMap> flagAndCurrencyList reshuffle in lower indexes before higher ones!
        try {
            for (int i = 31; i >= 0; i--) {

                if (positionsToRemove[i] != 0) {

                    // remove position - minus 1 because array has ALL zero's in 32 holders (default),
                    // so + 1 was added when storing it originally from CurrencyAdapter, then into SharedPreferences
                    flagAndCurrencyList.remove((positionsToRemove[i] - 1));
                }
            }

        } catch (NullPointerException e) {
>>>>>>> currencies_activity

            Log.v("CURRENCY Code", currencyCode[i]);
            Log.v("CURRENCY TYPE", currency[i]);
            Log.v("FLAG TYPE", flag[i]);
            Log.v("RATE", finalConvertedAmountText[i]);
        }
<<<<<<< HEAD
        return flagAndCurrencyList;
    }

=======


        return flagAndCurrencyList;


        return flagAndCurrencyList;

    }

<<<<<<< HEAD

>>>>>>> context_menu_on_listview
=======
>>>>>>> currencies_activity
=======

        return flagAndCurrencyList;


    }


>>>>>>> context_menu_on_listview
=======

        return flagAndCurrencyList;


    }


>>>>>>> context_menu_on_listview
}



