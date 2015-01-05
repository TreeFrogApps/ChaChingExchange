package com.home.markkeen.exchangerates;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CustomAdapter extends ArrayAdapter<HashMap<String, String>> {

    int[] positionsToPin = new int[32];
    String[] pinnedItems;

    MainActivity mainActivity;
    SharedPreferences sharedPreferences;
    String pinnedPositionsToKeep;
    String removedPositions;
    private final Context context;
    private final ArrayList<HashMap<String, String>> flagAndCurrencyList;

    private static class ViewHolder {
        protected ImageView flagType;
        protected TextView convertedCurrencyCode;
        protected TextView convertedCurrencyAmount;
        protected TextView convertedCurrencyType;
        protected ImageView pinToggle;
        protected Button context_menu;

    }


    public CustomAdapter(Context context, ArrayList<HashMap<String, String>> flagAndCurrencyList) {
        super(context, R.layout.list_view_results, flagAndCurrencyList);

        // initialise preferences
        this.sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_MULTI_PROCESS);
        this.context = context;
        this.flagAndCurrencyList = flagAndCurrencyList;

        removedPositions = sharedPreferences.getString("POSITIONS_TO_REMOVE", "");

        mainActivity = new MainActivity();

        // get shared prefs for pinned positions string (shared preferences were initialised onCreate all these key pairs come under "MyPrefs"
        pinnedPositionsToKeep = sharedPreferences.getString("PINNED_POSITIONS_TO_KEEP", "");
    }

    // denotes the listView size
    @Override
    public int getViewTypeCount() {
        //Count=Size of ArrayList.
        return flagAndCurrencyList.size();
    }

    // forces no recycle of view, as the listView size has been established above
    // used to stop duplicate changes to  individual listItem resource changes
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        final HashMap<String, String> flagAndCurrencyItem = flagAndCurrencyList.get(position);

        final ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view

        //viewHolder is used to reuse views that are cache rather than redrawing - offers better performance


        // do if the view has never been viewed/seen before
        if (convertView == null) {

            viewHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
        viewHolder.convertedCurrencyType.setText(flagAndCurrencyItem.get("currencyType").substring(3));
        viewHolder.pinToggle = (ImageView) convertView.findViewById(R.id.list_view_pin);
        viewHolder.context_menu = (Button) convertView.findViewById(R.id.context_menu);

        if (positionsToPin.length > flagAndCurrencyList.size()) {

            viewHolder.pinToggle.setBackground(getContext().getResources().getDrawable(R.drawable.pin_button_on));

        } else {

            viewHolder.pinToggle.setBackground(getContext().getResources().getDrawable(R.drawable.pin_button_off));
        }

        // context menu for each row (3 dots menu)
        // set setOnClickListener for button
        viewHolder.context_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View convertView) {

                //create popUpMenu (context menu)
                Context style = new ContextThemeWrapper(getContext(), R.style.PopUpMenu);

                PopupMenu popUpMenu = new PopupMenu(style, convertView);

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

                                if (positionsToPin.length > flagAndCurrencyList.size()) {

                                    // this state happens only when the pinToggleButton is ON - do not want ability to 'pin' when this state is enabled,
                                    // will only enable when list contains ALL currencies but then it doesn't matter!

                                } else {

                                    if (pinnedPositionsToKeep.contains("[")){

                                        // function to change string which contains list of number in format [1,2,3,4] back to int array
                                        // remove [ ] from beginning of string

                                        pinnedItems = pinnedPositionsToKeep.substring(1, pinnedPositionsToKeep.length() - 1).split(",");
                                        positionsToPin = new int[pinnedItems.length];

                                        for (int i = 0; i < pinnedItems.length; i++) {

                                            positionsToPin[i] = Integer.parseInt(pinnedItems[i].trim());
                                        }

                                        for (int i = 0; i < positionsToPin.length; i++) {
                                            Log.v("SAVED POSITIONS", String.valueOf(positionsToPin[i]));
                                        }

                                    }
                                    // loop through entire list checking to pinned positions
                                    for (int i = 0; i < positionsToPin.length; i++) {

                                        if (position == i) {

                                            positionsToPin[i] = (position + 1);
                                        }
                                    }


                                    for (int i = 0; i < positionsToPin.length; i++) {
                                        Log.v("POSITION", String.valueOf(positionsToPin[i]));
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

                                Toast.makeText(getContext(), "Set as base selected", Toast.LENGTH_SHORT).show();


                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popUpMenu.show();
            }
        });

        return convertView;
    }

}