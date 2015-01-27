package com.home.markkeen.exchangerates;


import android.content.Context;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import android.graphics.drawable.Drawable;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import android.view.LayoutInflater;
<<<<<<< HEAD
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
=======
=======
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
>>>>>>> currencies_activity
=======
import android.view.LayoutInflater;
>>>>>>> context_menu_on_listview
=======
import android.view.LayoutInflater;
>>>>>>> context_menu_on_listview
=======
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
>>>>>>> Features
=======
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
>>>>>>> currencies_activity
=======
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
>>>>>>> currencies_activity
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import android.widget.Toast;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> context_menu_on_listview
=======
>>>>>>> currencies_activity
=======
>>>>>>> context_menu_on_listview
=======
>>>>>>> context_menu_on_listview

import java.util.ArrayList;
=======

import java.util.ArrayList;
import java.util.Arrays;
>>>>>>> Features
=======
import android.widget.Toast;

import java.util.ArrayList;
>>>>>>> currencies_activity
=======
import android.widget.Toast;

import java.util.ArrayList;
>>>>>>> currencies_activity
import java.util.HashMap;

public class CustomAdapter extends ArrayAdapter<HashMap<String, String>> {

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    private final Context context;
    private final ArrayList<HashMap<String, String>> flagAndCurrencyList;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    private  MainActivity mainActivity;
=======
    private MainActivity mainActivity;
>>>>>>> context_menu_on_listview
=======
    private MainActivity mainActivity;
>>>>>>> currencies_activity
=======
    private MainActivity mainActivity;
>>>>>>> context_menu_on_listview
=======
    private MainActivity mainActivity;
>>>>>>> context_menu_on_listview
=======
    int[] positionsToPin;
    String[] pinnedItems;

    boolean pinToggleOn;
    String currencyCode;

    SharedPreferences sharedPreferences;
    String pinnedPositionsToKeep;
    private final Context context;
    private final ArrayList<HashMap<String, String>> flagAndCurrencyList;
>>>>>>> Features
=======
    private final Context context;
    private final ArrayList<HashMap<String, String>> flagAndCurrencyList;
    private MainActivity mainActivity;
>>>>>>> currencies_activity
=======
    private final Context context;
    private final ArrayList<HashMap<String, String>> flagAndCurrencyList;
    private MainActivity mainActivity;
>>>>>>> currencies_activity

    private static class ViewHolder {
        protected ImageView flagType;
        protected TextView convertedCurrencyCode;
        protected TextView convertedCurrencyAmount;
        protected TextView convertedCurrencyType;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        protected ImageView context_menu;
=======
        protected Button context_menu;
>>>>>>> context_menu_on_listview
=======
        protected Button context_menu;
>>>>>>> currencies_activity
=======
        protected Button context_menu;
>>>>>>> context_menu_on_listview
=======
        protected Button context_menu;
>>>>>>> context_menu_on_listview
=======
        protected TextView currencyRateText;
        protected ImageView pinToggle;
        protected Button context_menu;
>>>>>>> Features
=======
        protected Button context_menu;
>>>>>>> currencies_activity
=======
        protected Button context_menu;
>>>>>>> currencies_activity

    }


    public CustomAdapter(Context context, ArrayList<HashMap<String, String>> flagAndCurrencyList) {
        super(context, R.layout.list_view_results, flagAndCurrencyList);

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> currencies_activity
=======
>>>>>>> currencies_activity
        this.context = context;
        this.flagAndCurrencyList = flagAndCurrencyList;
        this.mainActivity = new MainActivity();


<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
        // initialise preferences
        this.sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_MULTI_PROCESS);
        this.context = context;
        this.flagAndCurrencyList = flagAndCurrencyList;
>>>>>>> Features

    }


<<<<<<< HEAD

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
=======
=======
>>>>>>> currencies_activity
=======
>>>>>>> context_menu_on_listview
=======
>>>>>>> context_menu_on_listview
=======
>>>>>>> currencies_activity
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> context_menu_on_listview
=======
>>>>>>> currencies_activity
=======
>>>>>>> context_menu_on_listview
=======
>>>>>>> context_menu_on_listview


        HashMap<String, String> flagAndCurrencyItem = flagAndCurrencyList.get(position);
=======
=======
    }


>>>>>>> currencies_activity
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


<<<<<<< HEAD
        final HashMap<String, String> flagAndCurrencyItem = flagAndCurrencyList.get(position);
>>>>>>> Features
=======


        HashMap<String, String> flagAndCurrencyItem = flagAndCurrencyList.get(position);
>>>>>>> currencies_activity
=======
        HashMap<String, String> flagAndCurrencyItem = flagAndCurrencyList.get(position);
>>>>>>> currencies_activity

        final ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view

        //viewHolder is used to reuse views that are cache rather than redrawing - offers better performance


        // do if the view has never been viewed/seen before
        if (convertView == null) {

            viewHolder = new ViewHolder();

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
=======
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
>>>>>>> context_menu_on_listview
=======
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
>>>>>>> currencies_activity
=======
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
>>>>>>> context_menu_on_listview
=======
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
>>>>>>> context_menu_on_listview
=======
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
>>>>>>> Features
=======
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
>>>>>>> currencies_activity
=======
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
>>>>>>> currencies_activity

            convertView = inflater.inflate(R.layout.list_view_results, parent, false);

            viewHolder.flagType = (ImageView) convertView.findViewById(R.id.flagType);
            viewHolder.convertedCurrencyAmount = (TextView) convertView.findViewById(R.id.convertedCurrencyAmount);
            viewHolder.convertedCurrencyCode = (TextView) convertView.findViewById(R.id.convertedCurrencyCode);
            viewHolder.convertedCurrencyType = (TextView) convertView.findViewById(R.id.convertedCurrencyType);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            viewHolder.context_menu = (ImageView) convertView.findViewById(R.id.context_menu);
=======
            viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);
>>>>>>> context_menu_on_listview
=======
            viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);
>>>>>>> currencies_activity
=======
            viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);
>>>>>>> context_menu_on_listview
=======
            viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);
>>>>>>> context_menu_on_listview
=======
            viewHolder.currencyRateText = (TextView) convertView.findViewById(R.id.currencyRateText);
            viewHolder.pinToggle = (ImageView) convertView.findViewById(R.id.list_view_pin);
            viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);

            // check the menuPinToggleState and put in relevant pin on / off
            if (pinToggleOn == true)
                viewHolder.pinToggle.setBackground(getContext().getResources().getDrawable(R.drawable.pin_button_on));
            else {

                viewHolder.pinToggle.setBackground(getContext().getResources().getDrawable(R.drawable.pin_button_off));
            }
>>>>>>> Features
=======
            viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);
>>>>>>> currencies_activity

=======
            viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);

>>>>>>> currencies_activity
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        viewHolder.convertedCurrencyAmount.setText("0.00");
        viewHolder.convertedCurrencyCode.setText(flagAndCurrencyItem.get("currencyCode"));
        viewHolder.convertedCurrencyType.setText(flagAndCurrencyItem.get("currencyType"));
        viewHolder.context_menu = (ImageView) convertView.findViewById(R.id.context_menu);

=======
        viewHolder.convertedCurrencyAmount.setText(flagAndCurrencyItem.get("finalConvertedAmountText"));
        viewHolder.convertedCurrencyCode.setText(flagAndCurrencyItem.get("currencyCode"));
        viewHolder.convertedCurrencyType.setText(flagAndCurrencyItem.get("currencyType"));
=======
        viewHolder.convertedCurrencyAmount.setText(flagAndCurrencyItem.get("finalConvertedAmountText"));
        viewHolder.convertedCurrencyCode.setText(flagAndCurrencyItem.get("currencyCode"));
        viewHolder.convertedCurrencyType.setText(flagAndCurrencyItem.get("currencyType").substring(3));
>>>>>>> currencies_activity
=======
        viewHolder.convertedCurrencyAmount.setText(flagAndCurrencyItem.get("finalConvertedAmountText"));
        viewHolder.convertedCurrencyCode.setText(flagAndCurrencyItem.get("currencyCode"));
        viewHolder.convertedCurrencyType.setText(flagAndCurrencyItem.get("currencyType"));
>>>>>>> context_menu_on_listview
=======
        viewHolder.convertedCurrencyAmount.setText(flagAndCurrencyItem.get("finalConvertedAmountText"));
        viewHolder.convertedCurrencyCode.setText(flagAndCurrencyItem.get("currencyCode"));
        viewHolder.convertedCurrencyType.setText(flagAndCurrencyItem.get("currencyType"));
>>>>>>> context_menu_on_listview
        viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);

=======
        viewHolder.convertedCurrencyAmount.setText(flagAndCurrencyItem.get("finalConvertedAmountText"));
        viewHolder.convertedCurrencyCode.setText(flagAndCurrencyItem.get("currencyCode"));
        viewHolder.convertedCurrencyType.setText(flagAndCurrencyItem.get("currencyType").substring(3));
        viewHolder.currencyRateText.setText(flagAndCurrencyItem.get("rateAmountText"));
        viewHolder.pinToggle = (ImageView) convertView.findViewById(R.id.list_view_pin);
        viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);

        // check the menuPinToggleState and put in relevant pin on / off
        if (pinToggleOn == true)
            viewHolder.pinToggle.setBackground(getContext().getResources().getDrawable(R.drawable.pin_button_on));
        else {

            viewHolder.pinToggle.setBackground(getContext().getResources().getDrawable(R.drawable.pin_button_off));
        }

>>>>>>> Features
=======
=======
>>>>>>> currencies_activity
        viewHolder.convertedCurrencyAmount.setText(flagAndCurrencyItem.get("finalConvertedAmountText"));
        viewHolder.convertedCurrencyCode.setText(flagAndCurrencyItem.get("currencyCode"));
        viewHolder.convertedCurrencyType.setText(flagAndCurrencyItem.get("currencyType").substring(3));
        viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);

<<<<<<< HEAD
>>>>>>> currencies_activity
=======
>>>>>>> currencies_activity
        // context menu for each row (3 dots menu)
        // set setOnClickListener for button
        viewHolder.context_menu.setOnClickListener(new View.OnClickListener() {
            @Override
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            public void onClick(View convertView) {

                //create popUpMenu (context menu)
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                PopupMenu popUpMenu = new PopupMenu(getContext(), convertView);
=======
                Context style = new ContextThemeWrapper(getContext(), R.style.PopUpMenu);

                PopupMenu popUpMenu = new PopupMenu(style, convertView);
>>>>>>> currencies_activity
=======
                PopupMenu popUpMenu = new PopupMenu(getContext(), convertView);
>>>>>>> context_menu_on_listview
=======
                PopupMenu popUpMenu = new PopupMenu(getContext(), convertView);
>>>>>>> context_menu_on_listview
=======
            public void onClick(final View convertView) {

                //create popUpMenu (context menu)
                Context style = new ContextThemeWrapper(getContext(), R.style.PopUpMenu);

                PopupMenu popUpMenu = new PopupMenu(style, convertView);
>>>>>>> Features
=======
=======
>>>>>>> currencies_activity
            public void onClick(View convertView) {

                //create popUpMenu (context menu)
                Context style = new ContextThemeWrapper(getContext(), R.style.PopUpMenu);

                PopupMenu popUpMenu = new PopupMenu(style, convertView);
<<<<<<< HEAD
>>>>>>> currencies_activity
=======
>>>>>>> currencies_activity

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

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> currencies_activity
=======
>>>>>>> currencies_activity
                                Toast.makeText(getContext(), "Pin currency selected", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.menu_remove:

                                Toast.makeText(getContext(), "Remove selected", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.menu_move_to_top:

                                Toast.makeText(getContext(), "Move to top selected", Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
<<<<<<< HEAD
=======
                                if (pinToggleOn == true) {
                                    // this state happens only when the pinToggleButton is ON - do not want ability to 'pin' when this state is enabled,
                                    // will only enable when list contains ALL currencies but then it doesn't matter!

                                } else {

                                    // initialise positionsToPin in case the 'if' statement is invalid and skipped
                                    // otherwise nullPointerException
                                    positionsToPin = new int[flagAndCurrencyList.size()];

                                    // get shared prefs for pinned positions string (shared preferences were initialised onCreate all these key pairs come under "MyPrefs"
                                    pinnedPositionsToKeep = sharedPreferences.getString("PINNED_POSITIONS_TO_KEEP", "");

                                    if (pinnedPositionsToKeep.contains("[")) {

                                        Log.v("SAVED PREFERENCE STRING", pinnedPositionsToKeep);

                                        // function to change string which contains list of number in format [1,2,3,4] back to int array
                                        // remove [ ] from beginning of string

                                        pinnedItems = pinnedPositionsToKeep.substring(1, pinnedPositionsToKeep.length() - 1).split(",");
                                        positionsToPin = new int[pinnedItems.length];

                                        for (int i = 0; i < pinnedItems.length; i++) {

                                            positionsToPin[i] = Integer.parseInt(pinnedItems[i].trim());
                                        }

                                        for (int i = 0; i < positionsToPin.length; i++) {
                                            Log.v("SAVED PINNED POSITIONS", String.valueOf(positionsToPin[i]));
                                        }

                                    }
                                    // loop through entire list checking to pinned positions
                                    for (int i = 0; i < positionsToPin.length; i++) {

                                        if (position == i) {

                                            positionsToPin[i] = (position + 1);
                                        }
                                    }

                                    for (int i = 0; i < positionsToPin.length; i++) {
                                        Log.v("POSITION TO ADD TO PIN LIST", String.valueOf(positionsToPin[i]));
                                    }

                                    // Cannot store int array in SharedPreferences - must be converted to String format
                                    String positionsToString = Arrays.toString(positionsToPin);

                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    // Get SharedPreferences and store as a string - NO NEED TO CLEAR WHEN SWAPPING ACTIVITIES!
                                    // It is overwritten each time 'putString' 'apply' is used (not appended as its a concatenated single string!)
                                    editor.putString("PINNED_POSITIONS_TO_KEEP", positionsToString);
                                    editor.apply();

                                }
                                return true;

                            case R.id.menu_swap_to_base_currency:

                                // Toast.makeText(getContext(), "Set as base selected", Toast.LENGTH_SHORT).show();

                                currencyCode = viewHolder.convertedCurrencyCode.getText().toString();

                                // pass in the 3 letter currency code for the appropriate listItem
                                // DIRECTLY reference the method as it is static
                                // any reference inside the method to variables used elsewhere also have to be classed as static
                                // variables declared in the class don't
                                MainActivity.swapBaseCurrency(currencyCode);

>>>>>>> Features
=======
>>>>>>> currencies_activity
=======
>>>>>>> currencies_activity
                                return true;
                            default:
                                return false;
                        }
                    }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> currencies_activity
=======
>>>>>>> currencies_activity


                });

                popUpMenu.show();

            }


        });
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> context_menu_on_listview
=======
>>>>>>> currencies_activity
=======
>>>>>>> context_menu_on_listview
=======
>>>>>>> context_menu_on_listview
=======
                });
                popUpMenu.show();
            }
        });
>>>>>>> Features
=======
>>>>>>> currencies_activity
=======
>>>>>>> currencies_activity

        return convertView;
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
}

=======
}
>>>>>>> context_menu_on_listview
=======
}
>>>>>>> currencies_activity
=======
}
>>>>>>> context_menu_on_listview
=======
}
>>>>>>> context_menu_on_listview
=======
}
>>>>>>> Features
=======
}
>>>>>>> currencies_activity
=======
}
>>>>>>> currencies_activity
