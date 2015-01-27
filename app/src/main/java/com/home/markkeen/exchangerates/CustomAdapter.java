package com.home.markkeen.exchangerates;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
<<<<<<< HEAD
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
=======
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
>>>>>>> context_menu_on_listview

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends ArrayAdapter<HashMap<String, String>> {

    private final Context context;
    private final ArrayList<HashMap<String, String>> flagAndCurrencyList;
<<<<<<< HEAD
    private  MainActivity mainActivity;
=======
    private MainActivity mainActivity;
>>>>>>> context_menu_on_listview

    private static class ViewHolder {
        protected ImageView flagType;
        protected TextView convertedCurrencyCode;
        protected TextView convertedCurrencyAmount;
        protected TextView convertedCurrencyType;
<<<<<<< HEAD
        protected ImageView context_menu;
=======
        protected Button context_menu;
>>>>>>> context_menu_on_listview

    }


    public CustomAdapter(Context context, ArrayList<HashMap<String, String>> flagAndCurrencyList) {
        super(context, R.layout.list_view_results, flagAndCurrencyList);

        this.context = context;
        this.flagAndCurrencyList = flagAndCurrencyList;
        this.mainActivity = new MainActivity();


<<<<<<< HEAD

    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
=======
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
>>>>>>> context_menu_on_listview


        HashMap<String, String> flagAndCurrencyItem = flagAndCurrencyList.get(position);

        final ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view

        //viewHolder is used to reuse views that are cache rather than redrawing - offers better performance


        // do if the view has never been viewed/seen before
        if (convertView == null) {

            viewHolder = new ViewHolder();

<<<<<<< HEAD
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
=======
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
>>>>>>> context_menu_on_listview

            convertView = inflater.inflate(R.layout.list_view_results, parent, false);

            viewHolder.flagType = (ImageView) convertView.findViewById(R.id.flagType);
            viewHolder.convertedCurrencyAmount = (TextView) convertView.findViewById(R.id.convertedCurrencyAmount);
            viewHolder.convertedCurrencyCode = (TextView) convertView.findViewById(R.id.convertedCurrencyCode);
            viewHolder.convertedCurrencyType = (TextView) convertView.findViewById(R.id.convertedCurrencyType);
<<<<<<< HEAD
            viewHolder.context_menu = (ImageView) convertView.findViewById(R.id.context_menu);
=======
            viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);
>>>>>>> context_menu_on_listview

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
<<<<<<< HEAD
        viewHolder.convertedCurrencyAmount.setText("0.00");
        viewHolder.convertedCurrencyCode.setText(flagAndCurrencyItem.get("currencyCode"));
        viewHolder.convertedCurrencyType.setText(flagAndCurrencyItem.get("currencyType"));
        viewHolder.context_menu = (ImageView) convertView.findViewById(R.id.context_menu);

=======
        viewHolder.convertedCurrencyAmount.setText(flagAndCurrencyItem.get("finalConvertedAmountText"));
        viewHolder.convertedCurrencyCode.setText(flagAndCurrencyItem.get("currencyCode"));
        viewHolder.convertedCurrencyType.setText(flagAndCurrencyItem.get("currencyType"));
        viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);

        // context menu for each row (3 dots menu)
        // set setOnClickListener for button
        viewHolder.context_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View convertView) {

                //create popUpMenu (context menu)
                PopupMenu popUpMenu = new PopupMenu(getContext(), convertView);

                // inflate my context menu xml layout
                MenuInflater inflater = popUpMenu.getMenuInflater();
                inflater.inflate(R.menu.listview_popup_menu, popUpMenu.getMenu());

                // setOnMenuItemClickListener
                // perform task on each menu item click
                popUpMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_pin_currency:

                                Toast.makeText(getContext(), "Pin currency selected", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.menu_remove:

                                Toast.makeText(getContext(), "Remove selected", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.menu_move_to_top:

                                Toast.makeText(getContext(), "Move to top selected", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }


                });

                popUpMenu.show();

            }


        });
>>>>>>> context_menu_on_listview

        return convertView;
    }

<<<<<<< HEAD
}

=======
}
>>>>>>> context_menu_on_listview
