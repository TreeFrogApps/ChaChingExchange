package com.home.markkeen.exchangerates;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SettingsAdapter extends BaseAdapter{

    private ArrayList<SettingsActivity.ListData> settingsListData = new ArrayList<SettingsActivity.ListData>();

    Context context;
    LayoutInflater inflater;

    String settingsRemovedPositionsString;
    String[] settingsRemovedPositionsStringArray;
    int[] settingsRemovedPosotionsIntArray = new int[32];

    SharedPreferences sharedPreferences;

    public SettingsAdapter(Context context, ArrayList<SettingsActivity.ListData> settingsListData){

        this.settingsListData = settingsListData;
        this.context = context;
        inflater = LayoutInflater.from(this.context);

        // run method to return a int[] of positions set as off
        // when program is first run this will not contain anything as no currencies
        // have been 'switched off
        settingsSwitchOnOff();
    }

    public int[] settingsSwitchOnOff(){

        // initialise preferences
        this.sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_MULTI_PROCESS);
        // get the sharedPreference string
        settingsRemovedPositionsString = sharedPreferences.getString("POSITIONS TO REMOVE", "");

        return settingsRemovedPosotionsIntArray;
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
    public View getView(int position, View convertView, ViewGroup parent) {

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

            viewHolder.switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });



        return convertView;
    }
}
