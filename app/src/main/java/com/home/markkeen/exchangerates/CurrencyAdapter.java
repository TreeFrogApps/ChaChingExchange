package com.home.markkeen.exchangerates;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;

public class CurrencyAdapter extends BaseAdapter {

    private ArrayList<CurrencyActivity.ListData> arrayListData = new ArrayList<CurrencyActivity.ListData>();
    Context context;
    LayoutInflater inflater;


    //  sharedPreferences
    SharedPreferences sharedPreferences;

    public CurrencyAdapter(Context context, ArrayList<CurrencyActivity.ListData> arrayListData) {

        this.arrayListData = arrayListData;
        this.context = context;
        inflater = LayoutInflater.from(this.context);

        // initialise preferences
        this.sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_MULTI_PROCESS);

        // if any currencies have been removed previously then this will call sharedPreferences to get the list
        // if its never been used then the 'if (removedPositions.contains("["))' statement is not fulfilled and is skipped
        String removedPositions;
        String[] items;
        removedPositions = sharedPreferences.getString("POSITIONS_TO_REMOVE", "");


        if (removedPositions.contains("["))  {

            // function to change string which contains list of number in format [1,2,3,4] back to int array
            // remove [ ] from beginning of string
            // Rest of code to set switch on/off according to the result positionsToRemove is below in getView when setting the switchOnOff
            items = removedPositions.substring(1, removedPositions.length() - 1).split(",");
            positionsToRemove = new int[items.length];

            for (int i = 0; i < items.length; i++ ){

                positionsToRemove[i] = Integer.parseInt(items[i].trim());
            }

            for (int i = 0; i < positionsToRemove.length; i++) {
                Log.v("SAVED POSITIONS", String.valueOf(positionsToRemove[i]));
            }

        }

        items = new String[0];

    }

    private static class ViewHolder {
        protected ImageView flagType;
        protected TextView currencyCode;
        protected TextView currencyType;
        protected ToggleButton switchOnOff;

    }

    int[] positionsToRemove = new int[32];

    @Override
    public int getCount() {
        return arrayListData.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // forces no recycle of view
    @Override
    public int getViewTypeCount() {
        //Count=Size of ArrayList.
        return arrayListData.size();
    }

    // forces no recycle of view
    @Override
    public int getItemViewType(int position) {

        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if(convertView == null){

            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.activity_currency_listview, parent, false);

            viewHolder.flagType = (ImageView) convertView.findViewById(R.id.flagTypeCurrencyActivity);
            viewHolder.currencyCode = (TextView) convertView.findViewById(R.id.currencyCodeCurrencyActivity);
            viewHolder.currencyType = (TextView) convertView.findViewById(R.id.currencyTypeCurrencyActivity);
            viewHolder.switchOnOff = (ToggleButton) convertView.findViewById(R.id.setOnOffCurrencyActivity);

            // store the information in a tag
            convertView.setTag(viewHolder);
        } else {
            // if the view has been seen before use view lookup cache stored in tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.flagType.setImageResource(arrayListData.get(position).getFlagNumberId());
        viewHolder.currencyCode.setText(arrayListData.get(position).getCountryCode());
        viewHolder.currencyType.setText(arrayListData.get(position).getCountry());
        viewHolder.switchOnOff = (ToggleButton) convertView.findViewById(R.id.setOnOffCurrencyActivity);

        // set the switch according to the sharedPreferences and returned int array 'positionsToRemove'
        // uss nullPointerException try/catch just in case (may not be needed)
        try {
              if (positionsToRemove[position] != 0){

                    viewHolder.switchOnOff.setChecked(false);
                }

        } catch (NullPointerException e){

        }

            viewHolder.switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (!isChecked){

                    for (int i = 0; i < positionsToRemove.length; i++){

                        if (position == i) {

                            positionsToRemove[i] = (position + 1);

                            Log.v("REMOVED POSITION", String.valueOf(position));
                            Log.v("REMOVED POSITION", String.valueOf(positionsToRemove[i]));
                        }
                    }

                } else {

                    for (int i = 0; i < positionsToRemove.length; i++){

                        if (position == i) {

                            positionsToRemove[i] = 0;
                        }
                    }

                }

                for (int i = 0; i < positionsToRemove.length; i++) {
                    Log.v("POSITION", String.valueOf(positionsToRemove[i]));

                }


                // Cannot store int array in SharedPreferences - must be converted to String format
                String positionsToString = Arrays.toString(positionsToRemove);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                // Get SharedPreferences and store as a string - NO NEED TO CLEAR WHEN SWAPPING ACTIVITIES!
                // It is overwritten each time 'putString' 'apply' is used (not appended as its a concatenated single string!)
                editor.putString("POSITIONS_TO_REMOVE", positionsToString);
                editor.apply();

                }
            });

        return convertView;
    }
}
