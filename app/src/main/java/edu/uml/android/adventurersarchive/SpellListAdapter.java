package edu.uml.android.adventurersarchive;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import edu.uml.android.adventurersarchive.info.Spell;

/**
 * Created by Darin on 11/8/2016.
 */
public class SpellListAdapter extends BaseExpandableListAdapter {
    private List<String> groupHeaders;
    private Map<String, List<Spell>> groupItems;

    public SpellListAdapter(List<String> gHeaders, Map<String, List<Spell>> gItems) {
        groupHeaders = gHeaders;
        groupItems = gItems;
    }

    @Override
    public int getGroupCount() {
        return groupHeaders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groupItems.get(groupHeaders.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupHeaders.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupItems.get(groupHeaders.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String header = (String) getGroup(groupPosition);

        TextView spellHeaderText = (TextView) convertView.findViewById(R.id.spell_list_header);
        spellHeaderText.setText(header);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String itemName = ((Spell) getChild(groupPosition, childPosition)).getSpellName();

        TextView spellNameText = (TextView) convertView.findViewById(R.id.spell_name);
        spellNameText.setText(itemName);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
