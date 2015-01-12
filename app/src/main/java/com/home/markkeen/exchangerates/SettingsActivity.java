package com.home.markkeen.exchangerates;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class SettingsActivity extends ActionBarActivity {

    SwitchCompat switchToggle;

    public int[] settingsFlags;
    public String[] settingsCountryCode;
    public String[] settingsCountry;
    public ArrayList<ListData> settingsListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // added to every activity when including the Toolbar layout
        Toolbar toolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolBar);

        settingsListData = new ArrayList<ListData>();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);

        MenuItem pin_button = menu.findItem(R.id.menu_pin_button_id);
        pin_button.setActionView(R.layout.menu_pin_button);

        switchToggle = (SwitchCompat) menu.findItem(R.id.menu_pin_button_id).getActionView().findViewById(R.id.menu_pin_toggle_button);
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

    public void settingsPopulatedListData(){

        settingsFlags = new int[]{
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

        settingsCountryCode = new String[] {
                "AUD","BGN", "BRL","CAD","CHF","CNY","CZK","DKK","EUR","GBP","HKD","HRK",
                "HUF","IDR","ILS","INR","JPY","KRW","LTL","MXN","NOK","NZD","PHP","PLN",
                "RON","RUB","SEK","SGD","THB","TRY","USD","ZAR"
        };

        settingsCountry = new String[]{
                "Australian Dollar","Bulgarian Lev","Brazilian Real","Canadian Dollar",
                "Swiss Franc","Chinese Yuan","Czech Koruna","Danish Krone","Euro",
                "British Pound","Hong Kong Dollar","Croatian Kuna","Hungarian Forint",
                "Indonesian Rupiah","Israeli Shekel","Indian Rupee","Japanese Yen",
                "Korean Won","Lithuanian Litas","Mexican Peso","Norwegian Krone",
                "New Zealand Dollar","Philippine Peso","Polish NEW Zloty","Romanian Leu",
                "Russian Rouble","Swedish Krona","Singapore Dollar","Thai Baht",
                "New Turkish Lira","United States Dollar","South African Rand"
        };


        for (int i = 0; i < settingsFlags.length; i++){

            // instantiate ListData class
            ListData listData = new ListData();

            // 'Set' the values for each of the arrays at position 'i'
            listData.setFlagNumberId(settingsFlags[i]);
            listData.setCountryCodeId(settingsCountryCode[i]);
            listData.setCountryId(settingsCountry[i]);

            // add listData (position 'i') to the ArrayList
            // ArrayList will hold all the data for the listView
            settingsListData.add(listData);
        }
    }



        // create ListData CLASS with getters and setters to 'Get' and 'Set'
        // data in the listView (create ListData Array from class below)
        public class ListData{

            int flagNumberId;
            String countryCodeId;
            String countryId;

            public int getFlagNumberId(){
                return flagNumberId;
            }
            public void setFlagNumberId(int flagNumberId){
                this.flagNumberId = flagNumberId;
            }
            public String getCountryCodeId(){
                return countryCodeId;
            }
            public void setCountryCodeId(String countryCodeId){
                this.countryCodeId = countryCodeId;
            }
            public String getCountryId(){
                return countryId;
            }
            public void setCountryId(String countryId) {
                this.countryId = countryId;
            }
        }



}
