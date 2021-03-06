package com.home.treefrogapps.ChaChingExchange;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    public static boolean offlineState;
    static EditText amountEditText;
    static Spinner currencyFromSpinner;
    static String[] spinnerCurrencyList;
    static ArrayAdapter<String> spinnerArray;
    static ImageView flagBase;
    static SharedPreferences sharedPreferences;
    static CustomAdapter customAdapter;
    static ListView listView;
    static String[] pinnedItems;
    static int[] pinnedPositions;
    static String pinnedPositionsToKeep;
    static ToggleButton menuPinToggleButton;
    static ArrayList<HashMap<String, String>> flagAndCurrencyList = new ArrayList<HashMap<String, String>>();
    static TextView resultsDate;
    static TextView resultsTime;
    private static String date;
    private static String time;
    private static String getRatesURLA = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22";
    private static String getGetRatesURLB = "%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
    int[] flags = {
            // holder flag for 'Choose a base currency' position in Select currency from web spinner
            R.drawable.flag_ic_00_empty,
            R.drawable.flag_ic_aud_00, R.drawable.flag_ic_bgn_01, R.drawable.flag_ic_brl_02,
            R.drawable.flag_ic_cad_03, R.drawable.flag_ic_chf_04, R.drawable.flag_ic_cny_05,
            R.drawable.flag_ic_czk_06, R.drawable.flag_ic_dkk_07, R.drawable.flag_ic_eur_08,
            R.drawable.flag_ic_gbp_09, R.drawable.flag_ic_hkd_10, R.drawable.flag_ic_hrk_11,
            R.drawable.flag_ic_huf_12, R.drawable.flag_ic_idr_13, R.drawable.flag_ic_ils_14,
            R.drawable.flag_ic_inr_15, R.drawable.flag_ic_jpy_16, R.drawable.flag_ic_krw_17,
            R.drawable.flag_ic_mxn_18, R.drawable.flag_ic_nok_19, R.drawable.flag_ic_nzd_20,
            R.drawable.flag_ic_php_21, R.drawable.flag_ic_pln_22, R.drawable.flag_ic_ron_23,
            R.drawable.flag_ic_rub_24, R.drawable.flag_ic_sek_25, R.drawable.flag_ic_sgd_26,
            R.drawable.flag_ic_thb_27, R.drawable.flag_ic_try_28, R.drawable.flag_ic_twd_29,
            R.drawable.flag_ic_usd_30, R.drawable.flag_ic_zar_31,
    };
    // string to hold the sharedPreference "POSITIONS TO REMOVE"
    String settingsRemovedPositionsString;
    // string array to hold the split string
    String[] settingsRemovedPositionsStringArray;
    // int array to hold the positions to remove, don't initialise with a size
    // otherwise array out of bounds issue - try / catch in populatedList method will
    // sort because it doesn't have a value on first start
    int[] settingsRemovedPositionsIntArray;
    double[] convertedAmount = new double[32];
    double[] finalConvertedAmount = new double[32];
    String[] currency;
    String[] flag;
    String[] currencyCode;
    String currencyFromType;
    String currencyFromSubsting;
    ImageView pinToggle;
    Animation flagAnimation;
    // int's for saving position of the listView
    int indexPosition;
    int top;
    private boolean pinToggleSavedState;
    private String getRatesLatest;
    private String getRatesFinal;
    private String getAmount;
    private double getAmountAsDouble;
    private String[] rateArray = new String[32];
    private String[] finalRateArray = new String[32];
    private String[] finalConvertedAmountText = new String[32];
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(ImageDownloadService.IMAGE_DOWNLOAD_COMPLETE)) {
                try {

                    FileInputStream fileInputStream = context.openFileInput("downloaded_graph_single.png");

                    final Dialog dialogBuilder = new Dialog(context, R.style.myDialogWindowAnimation);
                    dialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialogBuilder.setContentView(R.layout.main_image_dialog);
                    dialogBuilder.setCancelable(true);

                    ImageView graphImageView = (ImageView) dialogBuilder.findViewById(R.id.dialogImageView);
                    Button okButton = (Button) dialogBuilder.findViewById(R.id.okButton);
                    okButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialogBuilder.dismiss();
                        }
                    });

                    graphImageView.setImageBitmap(BitmapFactory.decodeStream(fileInputStream));
                    dialogBuilder.show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (intent.getAction().equals(ImagesDownloadService.IMAGES_DOWNLOAD_COMPLETE)) {
                try {

                    FileInputStream fileInputStream1d = context.openFileInput("downloaded_graph_00.png");
                    FileInputStream fileInputStream5d = context.openFileInput("downloaded_graph_01.png");
                    FileInputStream fileInputStream1y = context.openFileInput("downloaded_graph_02.png");


                    final Dialog dialogBuilder = new Dialog(context, R.style.myDialogWindowAnimation);
                    dialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialogBuilder.setContentView(R.layout.main_images_dialog);
                    dialogBuilder.setCancelable(true);

                    ImageView graphImageView1d = (ImageView) dialogBuilder.findViewById(R.id.dialogImageView1d);
                    ImageView graphImageView5d = (ImageView) dialogBuilder.findViewById(R.id.dialogImageView5d);
                    ImageView graphImageView1y = (ImageView) dialogBuilder.findViewById(R.id.dialogImageView1y);

                    Button okButton = (Button) dialogBuilder.findViewById(R.id.imagesOKButton);
                    okButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialogBuilder.dismiss();
                        }
                    });

                    graphImageView1d.setImageBitmap(BitmapFactory.decodeStream(fileInputStream1d));
                    graphImageView5d.setImageBitmap(BitmapFactory.decodeStream(fileInputStream5d));
                    graphImageView1y.setImageBitmap(BitmapFactory.decodeStream(fileInputStream1y));
                    dialogBuilder.show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // added to every activity when including the Toolbar layout
        Toolbar actionBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(actionBar);

        amountEditText = (EditText) findViewById(R.id.amountEditText);
        resultsDate = (TextView) findViewById(R.id.results_date);
        resultsTime = (TextView) findViewById(R.id.results_time);

        spinnerCurrencyList = new String[]{
                "Choose a base currency", "AUD\t\tAustralian Dollar", "BGN\t\tBulgarian Lev",
                "BRL\t\tBrazilian Real", "CAD\t\tCanadian Dollar", "CHF\t\tSwiss Franc",
                "CNY\t\tChinese Yuan", "CZK\t\tCzech Koruna", "DKK\t\tDanish Krone",
                "EUR\t\tEuro", "GBP\t\tBritish Pound", "HKD\t\tHong Kong Dollar",
                "HRK\t\tCroatian Kuna", "HUF\t\tHungarian Forint", "IDR\t\tIndonesian Rupiah",
                "ILS\t\tIsraeli Shekel", "INR\t\tIndian Rupee", "JPY\t\tJapanese Yen",
                "KRW\t\tSouth Korean Won", "MXN\t\tMexican Peso", "NOK\t\tNorwegian Krone",
                "NZD\t\tNew Zealand Dollar", "PHP\t\tPhilippine Peso", "PLN\t\tPolish NEW Zloty",
                "RON\t\tRomanian Leu", "RUB\t\tRussian Rouble", "SEK\t\tSwedish Krona",
                "SGD\t\tSingapore Dollar", "THB\t\tThai Baht", "TRY\t\tNew Turkish Lira",
                "TWD\t\tTaiwan Dollar", "USD\t\tUnited States Dollar", "ZAR\t\tSouth African Rand"};

        currencyFromSpinner = (Spinner) findViewById(R.id.spinnerCurrencyFrom);
        spinnerArray = new ArrayAdapter<String>(this, R.layout.spinner_view, R.id.spinner_list_item, spinnerCurrencyList);
        currencyFromSpinner.setAdapter(spinnerArray);

        flagBase = (ImageView) findViewById(R.id.flag_base);
        flagAnimation = AnimationUtils.loadAnimation(this, R.anim.flag_scale);


        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_MULTI_PROCESS);
        // get shared prefs for pinned positions string (shared preferences were initialised onCreate all these key pairs come under "MyPrefs"
        pinnedPositionsToKeep = sharedPreferences.getString("PINNED_POSITIONS_TO_KEEP", "");

        addItemExchangeRateFromSpinner();

        setExchangeAmountOnTextChangeListener();

        pinToggle = (ImageView) findViewById(R.id.list_view_pin);

        // create instance of customAdapter which extends ArrayAdapter (CustomAdapter.java)
        customAdapter = new CustomAdapter(getApplication(), flagAndCurrencyList);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(customAdapter);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ImageDownloadService.IMAGE_DOWNLOAD_COMPLETE);
        intentFilter.addAction(ImagesDownloadService.IMAGES_DOWNLOAD_COMPLETE);

        registerReceiver(broadcastReceiver, intentFilter);
    }

    // NB: THIS IS CALLED WHEN THE ACTIVITY IS FIRST OPENED AFTER ONCREATE & ONSTART
    // THIS MEANS THAT THE FULL FLAGANDCURRNCYLIST IS ALWAYS INITIALLY POPULATED
    // HOWEVER IF ANY HAVE BEEN REMOVED THIS WILL BE REPLACED DURING ONRESUME THIS MEANS
    // WHEN SHARED PREFERENCES IS CALLED ANY CHANGES WILL STILL BE THERE EVEN WHEN APP WAS CLOSED
    @Override
    protected void onResume() {
        super.onResume();

        // get settings sharedPreferences string
        settingsRemovedPositionsString = sharedPreferences.getString("POSITIONS TO REMOVE", "");

        // if it contains anything (it will always as soon as a switch is turned on/off for the first time
        if (settingsRemovedPositionsString.contains("[")) {

            // function to change string which contains list of number in format [1,2,3,4] back to int array
            // remove [ ] from beginning of string

            settingsRemovedPositionsStringArray = settingsRemovedPositionsString.substring(1, settingsRemovedPositionsString.length() - 1).split(",");
            settingsRemovedPositionsIntArray = new int[settingsRemovedPositionsStringArray.length];

            for (int i = 0; i < settingsRemovedPositionsStringArray.length; i++) {

                settingsRemovedPositionsIntArray[i] = Integer.parseInt(settingsRemovedPositionsStringArray[i].trim());
            }

            Log.v("SAVED POSITIONS", Arrays.toString(settingsRemovedPositionsIntArray));

        }

        customAdapter.clear();

        // get the updated list
        populatedArrayList();
        customAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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

        // get the sharedPreference for the ToggleButton state (if nothing exists then 'false' is default)
        pinToggleSavedState = sharedPreferences.getBoolean("PIN CHECKED STATE", false);

        // set the ToggleButton state according to the state in the sharedPreference
        menuPinToggleButton.setChecked(pinToggleSavedState);

        if (menuPinToggleButton.isChecked()) {

            customAdapter.pinToggleOn = true;
            customAdapter.clear();
            populatedArrayList();
            customAdapter.notifyDataSetChanged();
        } else {

            customAdapter.pinToggleOn = false;
            customAdapter.clear();
            populatedArrayList();
            customAdapter.notifyDataSetChanged();
        }

        menuPinToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // save the checked state of the ToggleButton
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("PIN CHECKED STATE", menuPinToggleButton.isChecked());
                editor.apply();

                if (menuPinToggleButton.isChecked() && flagAndCurrencyList.size() != 0) {

                    // get the listView first visible position (i will get the index position and not a partial position)
                    indexPosition = listView.getFirstVisiblePosition();
                    // to factor the listView for a partial scrolled position this works out the difference between
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

                        Log.v("SAVED POSITIONS", Arrays.toString(pinnedPositions));

                    } else {
                        pinnedPositions = new int[32];

                    }
                    customAdapter.pinToggleOn = true;
                    customAdapter.clear();
                    populatedArrayList();
                    customAdapter.notifyDataSetChanged();
                }

                if (!menuPinToggleButton.isChecked()) {

                    customAdapter.pinToggleOn = false;
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

    public static void resetPinnedCurrencies() {

        // reset the pinToggle, if this is not done there is a outOfBounds error
        menuPinToggleButton.setChecked(false);
        // update customAdapter to tell is the toggles state for the pin image
        customAdapter.pinToggleOn = false;
        // reset the pinnedPositions int[]
        pinnedPositions = new int[flagAndCurrencyList.size()];

        // remove the save pinned positions string from shared Prefs
        sharedPreferences.edit().remove("PINNED_POSITIONS_TO_KEEP").apply();

        // reset the pinnedPositionsToKeep string from the customAdapter
        customAdapter.pinnedPositionsToKeep = "";

        // reset the positionsToPin int[] from the customAdapter
        customAdapter.positionsToPin = new int[flagAndCurrencyList.size()];

        customAdapter.clear();
    }

    public static void swapBaseCurrency(String currency) {

        String spinnerItem;

        for (int i = 0; i < currencyFromSpinner.getCount(); i++) {

            spinnerItem = currencyFromSpinner.getItemAtPosition(i).toString();

            if (currency.equals(spinnerItem.substring(0, 3))) {

                currencyFromSpinner.setSelection(i);
            }
        }
    }

    // create method to check connection status before executing MyAsyncTask
    // return true if available
    public static boolean checkConnection(Context context) {

        // system service connectivity manager
        // include in manifest : <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
        ConnectivityManager checkNetworkStatus = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // network info will get all the status
        NetworkInfo networkInfo = checkNetworkStatus.getActiveNetworkInfo();

        // check that the state is 'connected' (either wifi or phone network - only 1 connection type
        // can exist at the same time
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;

        } else {

            currencyFromSpinner.setSelection(0);

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View customToast = layoutInflater.inflate(R.layout.custom_toast, null);
            TextView customToastTextView = (TextView) customToast.findViewById(R.id.customToastTextView);
            customToastTextView.setText("Internet Unavailable - Offline Mode only");

            Toast toast = new Toast(context);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(customToast);
            toast.show();
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.action_offline_mode:

                if (item.isChecked()) {
                    // set a boolean to 'false' to use in other functions

                    offlineState = false;

                    item.setChecked(false);
                } else {
                    // set a boolean to 'true' to use in other functions
                    offlineState = true;
                    item.setChecked(true);
                }
                return true;

            case R.id.action_clear_cached:
                //get sharedPreferences to clear ALL cached results
                SharedPreferences.Editor editor = sharedPreferences.edit();

                for (int i = 1; i < spinnerCurrencyList.length; i++) {

                    // use the spinner string array for cycling through sharedPrefs
                    // check if that 'key' is present, if so remove it
                    String code = spinnerCurrencyList[i].substring(0, 3);

                    if (sharedPreferences.contains(code)) {
                        editor.remove(code);
                        editor.apply();
                    }


                }

                // update listView to update icon (will try to get a null or "" sharedPref key
                currencyFromSpinner.setSelection(0);
                customAdapter.clear();
                finalConvertedAmountText = new String[32];
                finalRateArray = new String[32];
                populatedArrayList();
                customAdapter.notifyDataSetChanged();
                resultsDate.setText("");
                resultsTime.setText("");

                return true;

            case R.id.action_reset_pinned:

                // run method to clear pinned positions
                resetPinnedCurrencies();

                // get updated list and re-populate customerAdapter
                populatedArrayList();
                customAdapter.notifyDataSetChanged();
                return true;


            case R.id.action_settings:

                Intent intentSettings = new Intent(this, SettingsActivity.class);
                startActivity(intentSettings);
                return true;


            case R.id.action_about:

                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addItemExchangeRateFromSpinner() {

        currencyFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // set flag according to spinner position and currency/country
                flagBase.setImageResource(flags[position]);
                flagBase.startAnimation(flagAnimation);

                // hide the keyboard if visible
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(amountEditText.getWindowToken(), 0);


                // position 0 is the initial 'Choose a base currency' - no action required, only do if it is not that one!
                if (!currencyFromSpinner.getSelectedItem().toString().equals("Choose a base currency")) {

                    currencyFromType = (currencyFromSpinner.getSelectedItem().toString());
                    currencyFromSubsting = currencyFromType.substring(0, 3);

                    Log.v("SELECT FROM SPINNER ", currencyFromSubsting);

                    getAmount = amountEditText.getText().toString();

                    Log.v("GET AMOUNT", getAmount);

                    if (!getAmount.equals("")) {

                        getAmountAsDouble = Double.parseDouble(getAmount);

                        // if the menu 'offline mode' is checked then get the sharedPreferences 'result' (if there is one)
                        if (offlineState) {

                            String offlineResult = sharedPreferences.getString(currencyFromSpinner.getSelectedItem().toString().substring(0, 3), "not available");
                            // pass the offlineResult into onPostExecute - handle for "not available" there
                            new MyAsyncTask().onPostExecute(offlineResult);
                        }
                        // check the internet connection and run MyAsyncTask if available
                        else if (checkConnection(getApplicationContext())) {
                            new MyAsyncTask().execute();
                        }

                    } else {

                        getAmount = "0.00";

                        getAmountAsDouble = Double.parseDouble(getAmount);

                        // if the menu 'offline mode' is checked then get the sharedPreferences 'result' (if there is one)
                        if (offlineState) {


                            String offlineResult =
                                    sharedPreferences.getString(currencyFromSpinner.getSelectedItem().toString().substring(0, 3), "not available");

                            // pass the offlineResult into onPostExecute - handle for "not available" there
                            new MyAsyncTask().onPostExecute(offlineResult);
                        }

                        // check the internet connection and run MyAsyncTask if available
                        else if (checkConnection(getApplicationContext())) {
                            new MyAsyncTask().execute();
                        }
                    }
                } else {

                    customAdapter.clear();
                    finalConvertedAmountText = new String[32];
                    finalRateArray = new String[32];
                    populatedArrayList();
                    customAdapter.notifyDataSetChanged();
                    resultsDate.setText("");
                    resultsTime.setText("");
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

                // check if a base currency has been set otherwise http: requests will be done against a 'null' currency - inefficient
                if (!currencyFromSpinner.getSelectedItem().toString().equals("Choose a base currency")) {

                    getAmount = amountEditText.getText().toString();

                    if (!getAmount.equals("")) {

                        getAmountAsDouble = Double.parseDouble(getAmount);

                        // no need to do internet lookup if a currency has been chosen
                        // just pass in the 'result' from the saved currency lookup
                        // into the onPostExecute function from saved prefs

                        // get shared preferences result for each currency - handle not available (offline mode) in onPostExecute
                        String result = sharedPreferences.getString(currencyFromSpinner.getSelectedItem().toString().substring(0, 3), "not available");

                        new MyAsyncTask().onPostExecute(result);

                    } else {

                        getAmount = "0.00";
                        getAmountAsDouble = Double.parseDouble(getAmount);

                        // get shared preferences result for each currency - handle not available (offline mode) in onPostExecute
                        String result = sharedPreferences.getString(currencyFromSpinner.getSelectedItem().toString().substring(0, 3), "not available");

                        new MyAsyncTask().onPostExecute(result);
                    }
                }
            }
        });

    }

    public ArrayList<HashMap<String, String>> populatedArrayList() {

        flag = new String[]{
                "flag_ic_aud_00", "flag_ic_bgn_01", "flag_ic_brl_02", "flag_ic_cad_03", "flag_ic_chf_04", "flag_ic_cny_05", "flag_ic_czk_06",
                "flag_ic_dkk_07", "flag_ic_eur_08", "flag_ic_gbp_09", "flag_ic_hkd_10", "flag_ic_hrk_11", "flag_ic_huf_12", "flag_ic_idr_13",
                "flag_ic_ils_14", "flag_ic_inr_15", "flag_ic_jpy_16", "flag_ic_krw_17", "flag_ic_mxn_18", "flag_ic_nok_19", "flag_ic_nzd_20",
                "flag_ic_php_21", "flag_ic_pln_22", "flag_ic_ron_23", "flag_ic_rub_24", "flag_ic_sek_25", "flag_ic_sgd_26",
                "flag_ic_thb_27", "flag_ic_try_28", "flag_ic_twd_29", "flag_ic_usd_30", "flag_ic_zar_31"};
        Arrays.sort(flag);

        currencyCode = new String[]{
                "AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "HKD", "HRK", "HUF", "IDR",
                "ILS", "INR", "JPY", "KRW", "MXN", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "TWD", "USD", "ZAR"};
        Arrays.sort(currencyCode);

        currency = new String[]{
                "00 Australian Dollar", "01 Bulgarian Lev", "02 Brazilian Real", "03 Canadian Dollar", "04 Swiss Franc", "05 Chinese Yuan",
                "06 Czech Koruna", "07 Danish Krone", "08 Euro", "09 British Pound", "10 Hong Kong Dollar", "11 Croatian Kuna", "12 Hungarian Forint",
                "13 Indonesian Rupiah", "14 Israeli Shekel", "15 Indian Rupee", "16 Japanese Yen", "17 South Korean Won", "18 Mexican Peso",
                "19 Norwegian Krone", "20 New Zealand Dollar", "21 Philippine Peso", "22 Polish NEW Zloty", "23 Romanian Leu", "24 Russian Rouble",
                "25 Swedish Krona", "26 Singapore Dollar", "27 Thai Baht", "28 New Turkish Lira", "29 Taiwan Dollar", "30 United States Dollar", "31 South African Rand"};
        Arrays.sort(currency);

        for (int i = 0; i < flag.length; i++) {

            HashMap<String, String> currencyFlagList = new HashMap<>();

            currencyFlagList.put("flagType", flag[i]);
            currencyFlagList.put("currencyCode", currencyCode[i]);
            currencyFlagList.put("currencyType", currency[i]);
            currencyFlagList.put("finalConvertedAmountText", finalConvertedAmountText[i]);
            currencyFlagList.put("rateAmountText", finalRateArray[i]);


            flagAndCurrencyList.add(currencyFlagList);

            Log.v("CURRENCY Code", currencyCode[i]);
            Log.v("CURRENCY TYPE", currency[i]);
            Log.v("FLAG TYPE", flag[i]);

        }

        // Remove positions from settingsActivity from retrieved int[] settingsRemovedPositionsIntArray from onResume() and from menuPinToggle
        // Make sure there is something in the String passed from SettingsActivity otherwise null pointer exception (try/catch)
        // Reverse loop from 31 to 0 (32 positions), otherwise positions in the ArrayList<HashMap> flagAndCurrencyList reshuffle in lower indexes before higher ones!
        try {
            for (int i = (flagAndCurrencyList.size() - 1); i >= 0; i--) {

                if (settingsRemovedPositionsIntArray[i] != 0) {

                    // remove position - minus 1 because array has ALL zero's in 32 holders (default),
                    // so + 1 was added when storing it originally from CurrencyAdapter, then into SharedPreferences
                    flagAndCurrencyList.remove((settingsRemovedPositionsIntArray[i] - 1));
                }

            }

        } catch (NullPointerException e) {

        }

        // any 'Positions To Remove' from the settings activity been done so we have the new
        // flagAndCurrencyList size, so anything that remove using the 'pinned positions' int array
        // will be in relation to the correct result currencies list
        try {
            for (int i = (flagAndCurrencyList.size() - 1); i >= 0; i--) {

                // check to see if the menuToggle is checked
                if (menuPinToggleButton.isChecked()) {

                    if (pinnedPositions[i] == 0) {

                        flagAndCurrencyList.remove(i);
                    }
                }
            }

        } catch (NullPointerException e) {

        }
        return flagAndCurrencyList;
    }

    private class MyAsyncTask extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {

            progressDialog.setCancelable(true);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog);

        }

        @Override
        protected String doInBackground(String[] params) {

            String result = null;
            InputStream inputStream = null;

            // Define that I want to use the POST method to grab data from
            // the provided URL
            getRatesLatest = getRatesURLA;

            // loop round all the country codes concatenating into one big URL string
            for (String aCurrencyCode : currencyCode) {
                getRatesLatest = getRatesLatest + currencyFromSubsting + aCurrencyCode + "%22%2C%22";
            }

            // remove last "%22%2C%22" reassigning the string using substring to minus 9 characters
            getRatesLatest = getRatesLatest.substring(0, getRatesLatest.length() - 9);

            getRatesFinal = getRatesLatest + getGetRatesURLB;

            Log.v("HTTPS Address ", getRatesFinal);

            try {

                URL url = new URL(getRatesFinal);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");

                connection.setRequestProperty("Content-length", "0");
                connection.setUseCaches(false);
                connection.setAllowUserInteraction(false);
                connection.setConnectTimeout(20000); // 20 seconds
                connection.setReadTimeout(20000); // 20 seconds
                connection.connect();

                switch (connection.getResponseCode()) {

                    case 200:
                    case 201:
                    case 202:
                        inputStream = connection.getInputStream();

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
                        inputStream.close();
                        break;

                }



            } catch (IOException e) {
                Log.e("Error", "Error" + e);
                return null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            progressDialog.dismiss();

            // Holds Key Value pairs from a JSON source
            JSONObject jsonObject;

            if (result != null) {

                //  if the result string has been passed from offline mode check if offline information is available
                if (result.equals("not available")) {

                    // clear the currency adapter and change any populated text fields containing data
                    // back to nothing before doing populatedArrayList and notifyDataSetChanged
                    customAdapter.clear();
                    finalConvertedAmountText = new String[32];
                    finalRateArray = new String[32];
                    populatedArrayList();
                    customAdapter.notifyDataSetChanged();
                    currencyFromSpinner.setSelection(0);
                    resultsDate.setText("");
                    resultsTime.setText("");

                    LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View customToast = layoutInflater.inflate(R.layout.custom_toast, null);
                    TextView customToastTextView = (TextView) customToast.findViewById(R.id.customToastTextView);
                    customToastTextView.setText("Offline Data Not Available");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(customToast);
                    toast.show();

                } else {
                    try {

                        Log.v("JSONParser RESULT ", result);

                        // put the returned String result into sharedPreferences using the currency 3 letter code
                        // as the sharedPreferences key
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(currencyFromSpinner.getSelectedItem().toString().substring(0, 3), result);
                        editor.apply();

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

                        for (int i = 0; i < arrayLength; i++) {

                            JSONObject currencyJSONObject = jsonArray.getJSONObject(i);

                            rateArray[i] = currencyJSONObject.getString("Rate");

                            Log.v("CURRENCY FROM WEB ", rateArray[i]);
                        }

                        date = jsonArray.getJSONObject(0).getString("Date");
                        time = jsonArray.getJSONObject(0).getString("Time");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < rateArray.length; i++) {

                        // parse the String rateArray to double
                        convertedAmount[i] = Double.parseDouble(rateArray[i]);

                        finalConvertedAmount[i] = (Double.valueOf(new DecimalFormat("#.###").format(convertedAmount[i])) * getAmountAsDouble);
                    }

                    for (int i = 0; i < rateArray.length; i++) {

                        // convert back the rate but to 3 decimal places in String format
                        finalRateArray[i] = String.valueOf((String.format("%.03f", convertedAmount[i])).replaceAll("0*$", "").replaceAll("\\.$", ""));
                        // convert the rate amount to string 2 decimal places
                        finalConvertedAmountText[i] = String.valueOf((String.format("%.02f", finalConvertedAmount[i])));

                        Log.v("FINAL CONVERTED AMOUNT", finalConvertedAmountText[i]);
                    }

                    customAdapter.clear();
                    populatedArrayList();
                    customAdapter.notifyDataSetChanged();
                    resultsDate.setText(date);
                    resultsTime.setText(time);
                }
            } else {
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customToast = layoutInflater.inflate(R.layout.custom_toast, null);
                TextView customToastTextView = (TextView) customToast.findViewById(R.id.customToastTextView);
                customToastTextView.setText("Error - Connection Timeout");

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(customToast);
                toast.show();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        menuPinToggleButton.setChecked(false);
        // save the checked state of the ToggleButton
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("PIN CHECKED STATE", menuPinToggleButton.isChecked());
        editor.apply();

        offlineState = false;
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ImageDownloadService.IMAGE_DOWNLOAD_COMPLETE);
        intentFilter.addAction(ImagesDownloadService.IMAGES_DOWNLOAD_COMPLETE);
        registerReceiver(broadcastReceiver, intentFilter);
    }

}



