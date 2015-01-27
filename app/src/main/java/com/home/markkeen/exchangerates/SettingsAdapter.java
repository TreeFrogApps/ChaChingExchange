package com.home.markkeen.exchangerates;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class SettingsAdapter extends BaseAdapter{

    private ArrayList<SettingsActivity.ListData> settingsListData = new ArrayList<SettingsActivity.ListData>();

    Context context;
    LayoutInflater inflater;

    // string to hold the sharedPreference "POSITIONS TO REMOVE"
    String settingsRemovedPositionsString;
    // string array to hold the split string
    String[] settingsRemovedPositionsStringArray;
    // int array to hold the positions to remove, 0 = keep (switch is on) / position number = remove (switch id off)
    int[] settingsRemovedPositionsIntArray = new int[32];

    SharedPreferences sharedPreferences;

    public SettingsAdapter(Context context, ArrayList<SettingsActivity.ListData> settingsListData){

        this.settingsListData = settingsListData;
        this.context = context;
        inflater = LayoutInflater.from(this.context);

        // run method to return a int[] of positions set as off
        // when program is first run this will not contain anything as no currencies
        // have been 'switched off' put into it the int array to return the int array updated
        settingsSwitchOnOff();
    }

    public int[] settingsSwitchOnOff(){

        // initialise preferences
        this.sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_MULTI_PROCESS);
        // get the sharedPreference string
        settingsRemovedPositionsString = sharedPreferences.getString("POSITIONS TO REMOVE", "");

        // change from a string into a int array
        // only do if there is something in the string array, otherwise settingsRemovedPositionsIntArray will have 32 zero's (has not changed from initialisation)
        if (settingsRemovedPositionsString.contains("[")){

            settingsRemovedPositionsStringArray = settingsRemovedPositionsString.substring(1, settingsRemovedPositionsString.length() -1).split(",");

            settingsRemovedPositionsIntArray = new int[settingsRemovedPositionsStringArray.length];

            for (int i = 0; i < settingsRemovedPositionsStringArray.length; i++){

                settingsRemovedPositionsIntArray[i] = Integer.parseInt(settingsRemovedPositionsStringArray[i].trim());

                Log.v("POSITIONS TO REMOVE FROM FLAG AND CURRENCY LIST", String.valueOf(settingsRemovedPositionsIntArray[i]));
            }
        }
        return settingsRemovedPositionsIntArray;
    }


    private static class ViewHolder{
        protected ImageView flagType;
        protected TextView currencyCode;
        protected TextView currencyType;
        protected SwitchCompat switchOnOff;
    }

    @Override
    public int getCount() {
        return settingsListData.size();
    }

    @Override
    public Object getItem(int position) {
        return settingsListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // forces no recycle of view
    @Override
    public int getViewTypeCount() {
        //Count=Size of ArrayList.
        return settingsListData.size();
    }

    // forces no recycle of view
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.activity_settings_listview, parent, false);

            viewHolder.flagType = (ImageView) convertView.findViewById(R.id.flagTypeSettingsActivity);
            viewHolder.currencyCode = (TextView) convertView.findViewById(R.id.currencyCodeSettingsActivity);
            viewHolder.currencyType = (TextView) convertView.findViewById(R.id.currencyTypeSettingsActivity);
            viewHolder.switchOnOff = (SwitchCompat) convertView.findViewById(R.id.setOnOffSettingsActivity);

            // store the information in a tag
            convertView.setTag(viewHolder);

        } else {

            // if the view has been seen before use view lookup cache stored in tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.flagType.setImageResource(settingsListData.get(position).getFlagNumberId());
        viewHolder.currencyCode.setText(settingsListData.get(position).getCountryCodeId());
        viewHolder.currencyType.setText(settingsListData.get(position).getCountryId());
        viewHolder.switchOnOff = (SwitchCompat) convertView.findViewById(R.id.setOnOffSettingsActivity);

        // set the switch on/off according to the settingsRemovedPositionsIntArray if it has never been used before it will be on (true)
        // because it will be 0 by default, and if it has been used already switched on and off it value you will be set from the returned
        // int array from settingsSwitchOnOff() method above (zero = on / position number = off)
        if (settingsRemovedPositionsIntArray[position] == 0){

            viewHolder.switchOnOff.setChecked(true);
        } else {

            viewHolder.switchOnOff.setChecked(false);
        }

        viewHolder.switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // place the position (either as 0 = on, or position number = off)
                // into settingsRemovedPositionsIntArray
                if (isChecked){

                    // reset pinned currencies as something has been changed to the main results list
                    MainActivity.resetPinnedCurrencies();

                    settingsRemovedPositionsIntArray[position] = 0;
                    Log.v("SETTINGS ADDED POSITION", String.valueOf(position));
                }
                else {

                    // reset pinned currencies as something has been changed to the main results list
                    MainActivity.resetPinnedCurrencies();

                    settingsRemovedPositionsIntArray[position] = (position + 1);
                    Log.v("SETTINGS REMOVED POSITION", String.valueOf(position + 1));
                }

                // store the int array holding all data about positions to keep/remove in a string (can't save int arrays in sharedPreferences)
                String settingsPositionsToString = Arrays.toString(settingsRemovedPositionsIntArray);
                // see the string list (should have 32 entries, either 0 or the position number
                Log.v("SETTINGS POSITIONS TO STRING", settingsPositionsToString);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                // Get SharedPreferences and store as a string - NO NEED TO CLEAR WHEN SWAPPING ACTIVITIES!
                // It is overwritten each time 'putString' 'apply' is used (not appended as its a concatenated single string!)
                editor.putString("POSITIONS TO REMOVE", settingsPositionsToString);
                editor.apply();
            }
        });


        return convertView;
    }
}
