package edu.uml.android.adventurersarchive;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.List;
import java.util.Map;

import edu.uml.android.adventurersarchive.info.Spell;

/**
 * Created by Darin on 11/8/2016.
 */
public class SpellListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> groupHeaders;
    private Map<String, List<Spell>> groupItems;

    public SpellListAdapter(Context c, List<String> gHeaders, Map<String, List<Spell>> gItems) {
        context = c;
        groupHeaders = gHeaders;
        groupItems = gItems;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupItems.get(groupHeaders.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.spell_list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.spell_name);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(groupItems.isEmpty()) return 0;
        return groupItems.get(groupHeaders.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupHeaders.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        if(groupHeaders.isEmpty()) return 0;
        return groupHeaders.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.spell_list_group_item, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.group_item_name);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
