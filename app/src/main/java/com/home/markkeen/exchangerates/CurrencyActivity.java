package com.home.markkeen.exchangerates;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class CurrencyActivity extends ActionBarActivity {

    ListView listview;
    ArrayList<ListData> arrayListData = new ArrayList<ListData>();
    int[] flags;
    String[] currencyCode;
    String[] currency;
    String positionsToString;

    SwitchCompat switchToggle;
    SharedPreferences sharedPreferences;
    //CurrencyAdapter currencyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        // added to every activity when including the Toolbar layout
        Toolbar toolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolBar);

        populatedListData();

       // currencyAdapter = new CurrencyAdapter(this, arrayListData);

        listview = (ListView) findViewById(R.id.listViewCurrency);
       // listview.setAdapter(currencyAdapter);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_MULTI_PROCESS);

    }

    public void updateAdapter() {

        /*
        ListView.getFirstVisiblePosition() returns the top visible list item.
        But this item may be partially scrolled out of view, and if you want to
        restore the exact scroll position of the list you need to get this offset.
        So ListView.getChildAt(0) returns the View for the top list item, and then View.getTop()
        returns its relative offset from the top of the ListView. Then, to restore the ListView's
        scroll position, we call ListView.setSelectionFromTop() with the index of the item we want
        and an offset to position its top edge from the top of the ListView.
         */

        // get the listview first visible position (i will get the index position and not a partial position
        int indexPosition = listview.getFirstVisiblePosition();

        // to factor the listview for a partial scrolled position this works out the difference between index item 0 and current top view
        int top = listview.getChildAt(0).getTop();

        //currencyAdapter = new CurrencyAdapter(this, arrayListData);
        //listview.setAdapter(currencyAdapter);

        listview.setSelectionFromTop(indexPosition, top);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);


        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_currency, menu);

        MenuItem pin_button = menu.findItem(R.id.menu_pin_button_id);
        pin_button.setActionView(R.layout.menu_pin_button);

        switchToggle = (SwitchCompat) menu.findItem(R.id.menu_pin_button_id).getActionView().findViewById(R.id.menu_pin_toggle_button);


        // get shared prefs to check if there is something there -  if so set the button off
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_MULTI_PROCESS);
        String removedPositions;

        removedPositions = sharedPreferences.getString("POSITIONS_TO_REMOVE", "");

        if (removedPositions.contains("[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]")) {

            switchToggle.setChecked(true);

        } else {

            switchToggle.setChecked(false);
        }


        switchToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    positionsToString = "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]";

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    // Get SharedPreferences and store as a string - NO NEED TO CLEAR WHEN SWAPPING ACTIVITIES!
                    // It is overwritten each time 'putString' 'apply' is used (not appended as its a concatenated single string!)
                    editor.putString("POSITIONS_TO_REMOVE", positionsToString);
                    editor.apply();
                }

                if (!isChecked) {

                    positionsToString = "[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32]";

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    // Get SharedPreferences and store as a string - NO NEED TO CLEAR WHEN SWAPPING ACTIVITIES!
                    // It is overwritten each time 'putString' 'apply' is used (not appended as its a concatenated single string!)
                    editor.putString("POSITIONS_TO_REMOVE", positionsToString);
                    editor.apply();
                }

                updateAdapter();
            }

        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.action_settings2:

                Toast.makeText(getApplicationContext(), "settings pressed", Toast.LENGTH_SHORT).show();

                return true;

            default:
                break;

        }


        return super.onOptionsItemSelected(item);
    }


    public void populatedListData() {

        flags = new int[]{
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
        };

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


        currency = new String[]{
                "Australian Dollar",
                "Bulgarian Lev",
                "Brazilian Real",
                "Canadian Dollar",
                "Swiss Franc",
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


        // loop to populate ListData listData
        for (int i = 0; i < flags.length; i++) {

            // instantiate ListData
            ListData listData = new ListData();

            // populate listData each position [i]
            listData.setFlagNumberId(flags[i]);
            listData.setCountryCode(currencyCode[i]);
            listData.setCountry(currency[i]);

            // add listData for position[i] into ArrayList arrayListData
            arrayListData.add(listData);

            Log.v("CURRENCY Code", currencyCode[i]);
            Log.v("CURRENCY TYPE", currency[i]);

        }

    }


    // use getters and setters to get each array item
    public class ListData {

        int flagNumberId;
        String countryCode;
        String country;

        public int getFlagNumberId() {
            return flagNumberId;
        }

        public void setFlagNumberId(int flagNumberId) {
            this.flagNumberId = flagNumberId;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}
