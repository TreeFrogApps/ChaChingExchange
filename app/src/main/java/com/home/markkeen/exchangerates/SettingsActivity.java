package com.home.markkeen.exchangerates;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.ArrayList;


public class SettingsActivity extends ActionBarActivity {

    SwitchCompat switchToggle;
    SharedPreferences sharedPreferences;
    String settingsRemovedPositionsString;

    public int[] settingsFlags;
    public String[] settingsCountryCode;
    public String[] settingsCountry;
    ListView settingsListView;
    SettingsAdapter settingsAdapter;
    ArrayList<ListData> settingsListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // added to every activity when including the Toolbar layout
        Toolbar toolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolBar);

        settingsListData = new ArrayList<>();

        // perform method to populate the ListData
        settingsPopulatedListData();

        // set the SettingsAdapter putting the populated ListData into it
        settingsAdapter = new SettingsAdapter(this, settingsListData);
        // initialise the ListView
        settingsListView = (ListView) findViewById(R.id.listViewSettings);
        // set the listView with the Adapter
        settingsListView.setAdapter(settingsAdapter);

        // get shared prefs to check if there is something there -  if so set the button off
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_MULTI_PROCESS);

        // Alert dialog to let user know that changing any currencies will reset/clear existing pinned currencies
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Please note");
            alertDialogBuilder.setMessage("When changing any results currency any pinned currencies will automatically be cleared");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);

        MenuItem pin_button = menu.findItem(R.id.menu_pin_button_id_settings);
        pin_button.setActionView(R.layout.menu_pin_button);

        switchToggle = (SwitchCompat) menu.findItem(R.id.menu_pin_button_id_settings).getActionView().findViewById(R.id.menu_pin_toggle_button);

        settingsRemovedPositionsString = sharedPreferences.getString("POSITIONS TO REMOVE", "");
        Log.v("SETTINGS POSITIONS TO STRING", settingsRemovedPositionsString);

        // set the master switch to 'on' of the sharesPreference string is set to 'all on', or if it doesn't hold anything, either first time use or string is reset
        if (settingsRemovedPositionsString.equals("[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]") || settingsRemovedPositionsString.equals("")){
            switchToggle.setChecked(true);
        } else {
            switchToggle.setChecked(false);
        }

        switchToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    // reset pinned currencies as something has been changed to the main results list
                    MainActivity.resetPinnedCurrencies();

                    // set the string to have all buttons on
                    settingsRemovedPositionsString = "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";

                    // save that string in sharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("POSITIONS TO REMOVE", settingsRemovedPositionsString);
                    editor.apply();


                }

                if (!isChecked){

                    // reset pinned currencies as something has been changed to the main results list
                    MainActivity.resetPinnedCurrencies();

                    // set the string to have all buttons off
                    settingsRemovedPositionsString = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32]";

                    // save that string in sharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("POSITIONS TO REMOVE", settingsRemovedPositionsString);
                    editor.apply();

                }

                // run method to get current listView position, then update buttons all on/off depending on switch position by initialising the settingsAdapter again
                updateAdapter();

            }
        });

        return true;
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

        // get the listView first visible position (i will get the index position and not a partial position
        int indexPosition = settingsListView.getFirstVisiblePosition();

        // to factor the listView for a partial scrolled position this works out the difference between index item 0 and current top view
        int top = settingsListView.getChildAt(0).getTop();

        settingsAdapter = new SettingsAdapter(this, settingsListData);
        settingsListView.setAdapter(settingsAdapter);

        settingsListView.setSelectionFromTop(indexPosition, top);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about_settings) {

            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
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
