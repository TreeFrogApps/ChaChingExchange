package com.home.markkeen.exchangerates;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class AboutExpandableAdapter extends BaseExpandableListAdapter {

    private final SparseArray<AboutActivity.ListViewGroup> listViewDetailSparseArray;
    public LayoutInflater layoutInflater;
    public Context context;

    public AboutExpandableAdapter(Context context, SparseArray<AboutActivity.ListViewGroup> listViewDetailSparseArray) {
        this.context = context;
        this.listViewDetailSparseArray = listViewDetailSparseArray;
        layoutInflater = LayoutInflater.from(this.context);

    }

    @Override
    public int getGroupCount() {
        return listViewDetailSparseArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listViewDetailSparseArray.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listViewDetailSparseArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listViewDetailSparseArray.get(groupPosition).children.get(childPosition);
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }



    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null){

            convertView = layoutInflater.inflate(R.layout.activity_about_listview_group, null);
        }

        AboutActivity.ListViewGroup listViewDetailPosition = (AboutActivity.ListViewGroup) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(listViewDetailPosition.listViewGroupText);
        ((CheckedTextView) convertView).setChecked(isExpanded);


        // set the drawable icon for each 'position' in relation to the SparseArray positions
        Drawable expandableListViewDrawable;

        switch (groupPosition){

            case 0: expandableListViewDrawable = parent.getContext().getResources().getDrawable(R.drawable.ic_help_fb);
                    ((CheckedTextView) convertView).setCompoundDrawablesWithIntrinsicBounds(expandableListViewDrawable, null, null, null);
                    break;

            case 1: expandableListViewDrawable = parent.getContext().getResources().getDrawable(R.drawable.ic_tc);
                ((CheckedTextView) convertView).setCompoundDrawablesWithIntrinsicBounds(expandableListViewDrawable, null, null, null);
                break;

            case 2: expandableListViewDrawable = parent.getContext().getResources().getDrawable(R.drawable.ic_privacy);
                ((CheckedTextView) convertView).setCompoundDrawablesWithIntrinsicBounds(expandableListViewDrawable, null, null, null);
                break;

            default: break;
        }

        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        // String which gets the child text in relation to the associated SparseArray position (ListViewGroup String name) List<String> (text for child position)
        final String children = (String) getChild(groupPosition, childPosition);
        TextView childTextView;

        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.activity_about_listview_detail, null);

        }

        childTextView = (TextView) convertView.findViewById(R.id.expandableListViewText);
        childTextView.setText(children);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
