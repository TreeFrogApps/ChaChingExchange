package com.home.markkeen.exchangerates;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends ArrayAdapter<HashMap<String, String>> {

    private final Context context;
    private final ArrayList<HashMap<String, String>> flagAndCurrencyList;
    private  MainActivity mainActivity;

    private static class ViewHolder {
        protected ImageView flagType;
        protected TextView convertedCurrencyCode;
        protected TextView convertedCurrencyAmount;
        protected TextView convertedCurrencyType;
        protected Button context_menu;

    }


    public CustomAdapter(Context context, ArrayList<HashMap<String, String>> flagAndCurrencyList) {
        super(context, R.layout.list_view_results, flagAndCurrencyList);

        this.context = context;
        this.flagAndCurrencyList = flagAndCurrencyList;
        this.mainActivity = new MainActivity();



    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent){




        HashMap<String, String> flagAndCurrencyItem = flagAndCurrencyList.get(position);

        final ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view

        //viewHolder is used to reuse views that are cache rather than redrawing - offers better performance


        // do if the view has never been viewed/seen before
        if (convertView == null) {

            viewHolder = new ViewHolder();

            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.list_view_results, parent, false);

            viewHolder.flagType = (ImageView) convertView.findViewById(R.id.flagType);
            viewHolder.convertedCurrencyAmount = (TextView) convertView.findViewById(R.id.convertedCurrencyAmount);
            viewHolder.convertedCurrencyCode = (TextView) convertView.findViewById(R.id.convertedCurrencyCode);
            viewHolder.convertedCurrencyType = (TextView) convertView.findViewById(R.id.convertedCurrencyType);
            viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);

            // store the information in a tag
            convertView.setTag(viewHolder);

        } else {

            // if the view has been seen before use view lookup cache stored in tag
            viewHolder = (ViewHolder) convertView.getTag();
        }


        String name = flagAndCurrencyItem.get("flagType");
        int id = getContext().getResources().getIdentifier(name, "drawable", context.getPackageName());
        Drawable flagImage = getContext().getResources().getDrawable(id);

        // Populate the data into the template view using the data object
        viewHolder.flagType.setBackground(flagImage);
        viewHolder.convertedCurrencyAmount.setText(flagAndCurrencyItem.get("finalConvertedAmountText"));
        viewHolder.convertedCurrencyCode.setText(flagAndCurrencyItem.get("currencyCode"));
        viewHolder.convertedCurrencyType.setText(flagAndCurrencyItem.get("currencyType"));
        viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);

        viewHolder.context_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View convertView) {


                final CharSequence[] menuItems = {"Pin currency", "Remove", "Move to top"};

                AlertDialog.Builder contextMenu = new AlertDialog.Builder(getContext());

                contextMenu.setItems(menuItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getContext(), menuItems[which], Toast.LENGTH_SHORT).show();

                    }
                });




            }
        });


        return convertView;
    }

}

