package edu.uml.android.adventurersarchive;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.List;
import java.util.Map;

import edu.uml.android.adventurersarchive.info.Spell;

/**
 * Created by Darin on 11/8/2016.
 */
public class FullSpellbookFragment extends Fragment {
    private SpellListAdapter adapter;
    private List<String> groupHeaders;
    private Map<String, List<Spell>> groupItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.spell_list, container, false);

        prepareGroups();
        adapter = new SpellListAdapter(groupHeaders, groupItems);
        ExpandableListView expView = (ExpandableListView) rootView.findViewById(R.id.spell_list);
        expView.setAdapter(adapter);

        expView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO: Launch activity to show detailed information about the spell.
                return false;
            }
        });

        return rootView;
    }

    private void prepareGroups() {
        // TODO: Take all spells in database and populate the groups and their items.
    }
}
