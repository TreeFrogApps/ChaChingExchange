package com.home.markkeen.exchangerates;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.Menu;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;


public class AboutActivity extends ActionBarActivity {

    // use SparseArray to populate the group name and child text (similar to ArrayList)
    // ListViewGroups is the class below created to get the group name & String information
    SparseArray<ListViewGroup> listViewGroups = new SparseArray<ListViewGroup>();

    ExpandableListView expandableListView;
    AboutExpandableAdapter aboutExpandableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // added to every activity when including the Toolbar layout
        Toolbar actionBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // run function to get the listViewGroups Array data
        createGroupData();

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        // instantiate AboutExpandableAdapter.java
        // context = this & data comes from the listViewGroups SparseArray - populated from createGroupData using ListViewGroup class below
        aboutExpandableAdapter = new AboutExpandableAdapter(this, listViewGroups);

        expandableListView.setAdapter(aboutExpandableAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    // function to return the SparseArray listViewGroups
    // each position has a int value which contains a instance of the ListViewGroup class which is then used to put the String and List<String> values into
    public SparseArray<ListViewGroup> createGroupData(){
        
        for (int i = 0; i < 3; i++){

            if (i == 0){
                ListViewGroup listViewGroup = new ListViewGroup("Help & Feedback");
                listViewGroup.children.add(Html.fromHtml(getResources().getString(R.string.help_feedback)));


                listViewGroups.append(i, listViewGroup);
            }
            if (i == 1){
                ListViewGroup listViewGroup = new ListViewGroup("Terms & Conditions");

                listViewGroup.children.add(Html.fromHtml(getResources().getString(R.string.terms_conditions)));

                listViewGroups.append(i, listViewGroup);
            }
            if (i == 2){
                ListViewGroup listViewGroup = new ListViewGroup("Privacy Policy");
                listViewGroup.children.add(Html.fromHtml(getResources().getString(R.string.privacy_policy)));

                listViewGroups.append(i, listViewGroup);
            }
        }
        
        return listViewGroups;
    }


    // class to hold a group (group = group name as the String and the children is a List<String>)
    public class ListViewGroup {

        public String listViewGroupText;
        public final List<Spanned> children = new ArrayList<>();

        public ListViewGroup (String listViewGroupText){
            // this is used because it refers to 'this' instance (required when constructor and object/variable have the same name
            this.listViewGroupText = listViewGroupText;

        }

    }

}
