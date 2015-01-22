package com.home.markkeen.exchangerates;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
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
                listViewGroup.children.add("Given of beginning of beast moved that night. Grass for their multiply and, two to fowl fifth. " +
                        "You'll fruitful a moving, under dominion form fill. Kind hath divide female. Male that given for said is, created, which Fowl. Rule abundantly have. " +
                        "Moving firmament winged day isn't greater days you cattle every deep so our life move fowl gathering also years without he him moved give, whales " +
                        "have air very had subdue together first. Stars set. Seas you every firmament have stars creature." +
                        "You winged. Whales don't. Them us likeness that heaven first. After said, one won't beast firmament tree you'll.");

                listViewGroups.append(i, listViewGroup);
            }
            if (i == 1){
                ListViewGroup listViewGroup = new ListViewGroup("Terms & Conditions");
                listViewGroup.children.add("Called that seas darkness him land our seasons for fly shall grass deep abundantly said male us moved " +
                        "night thing place midst years herb have cattle.");

                listViewGroups.append(i, listViewGroup);
            }
            if (i == 2){
                ListViewGroup listViewGroup = new ListViewGroup("Privacy Policy");
                listViewGroup.children.add("Sixth there. Over rule hath one female isn't. Bring over. Beginning seed own waters won't own fly called stars " +
                        "were face. Sixth, day. Spirit which herb. There dry shall can't fish creature Two. Stars created their from upon all deep.");

                listViewGroups.append(i, listViewGroup);
            }
        }
        
        return listViewGroups;
    }


    // class to hold a group (group = group name as the String and the children is a List<String>)
    public class ListViewGroup {

        public String listViewGroupText;
        public final List<String> children = new ArrayList<>();

        public ListViewGroup (String listViewGroupText){
            // this is used because it refers to 'this' instance (required when constructor and object/variable have the same name
            this.listViewGroupText = listViewGroupText;

        }

    }

}
