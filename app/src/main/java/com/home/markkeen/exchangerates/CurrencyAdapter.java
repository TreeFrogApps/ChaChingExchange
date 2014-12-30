package com.home.markkeen.exchangerates;

import android.content.Context;
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

public class CurrencyAdapter extends BaseAdapter {

    private ArrayList<CurrencyActivity.ListData> arrayListData = new ArrayList<CurrencyActivity.ListData>();
    Context context;
    LayoutInflater inflater;

    public CurrencyAdapter(Context context, ArrayList<CurrencyActivity.ListData> arrayListData) {

        this.arrayListData = arrayListData;
        this.context = context;
        inflater = LayoutInflater.from(this.context);

    }

    private static class ViewHolder {
        protected ImageView flagType;
        protected TextView currencyCode;
        protected TextView currencyType;
        protected ToggleButton switchOnOff;

    }

    ArrayList<Integer> positions;

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
            viewHolder.switchOnOff.setChecked(true);

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

            viewHolder.switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!isChecked){



                    for (int i = 0; i < positionsToRemove.length; i++){

                        if (position == i) {

                            positionsToRemove[i] = position;
                            Log.v("REMOVED POSITION", String.valueOf(position));
                            Log.v("REMOVED POSITION", String.valueOf(positionsToRemove[i]));

                        }
                    }

                    for (int i = 0; i < positionsToRemove.length; i++){


                            Log.v("POSITION", String.valueOf(positionsToRemove[i]));


                    }

                }
            }
        });


        return convertView;
    }
}
