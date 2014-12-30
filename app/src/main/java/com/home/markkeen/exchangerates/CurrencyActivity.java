package com.home.markkeen.exchangerates;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class CurrencyActivity extends ActionBarActivity {

    ListView listview;
    ArrayList<ListData> arrayListData = new ArrayList<ListData>();
    int[] flags;
    String[] currencyCode;
    String[] currency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        invalidateOptionsMenu();

        populatedListData();

        CurrencyAdapter currencyAdapter = new CurrencyAdapter(this, arrayListData);

        currencyAdapter.hasStableIds();

        listview = (ListView) findViewById(R.id.listViewCurrency);
        listview.setAdapter(currencyAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);


        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_currency, menu);
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

        public int getFlagNumberId(){
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

        public String getCountry(){
            return country;
        }

        public void setCountry(String country){
            this.country = country;
        }
    }
}
