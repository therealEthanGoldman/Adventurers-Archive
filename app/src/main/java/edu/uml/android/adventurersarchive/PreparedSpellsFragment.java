package edu.uml.android.adventurersarchive;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uml.android.adventurersarchive.character.CharacterInfo;
import edu.uml.android.adventurersarchive.info.Spell;

/**
 * Created by Darin on 11/8/2016.
 */
public class PreparedSpellsFragment extends Fragment {
    private SpellListAdapter adapter;
    private List<String> groupHeaders;
    private Map<String, List<String>> groupItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.spell_list, container, false);

        groupHeaders = new ArrayList<String>();
        groupItems = new HashMap<String, List<String>>();

        CharacterInfo myCharacter = ((GlobalState) getActivity().getApplicationContext()).getCharacter();
        prepareGroups(myCharacter);

        adapter = new SpellListAdapter(getContext(), groupHeaders, groupItems);
        ExpandableListView expView = (ExpandableListView) rootView.findViewById(R.id.spell_list);
        expView.setAdapter(adapter);

        expView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO: Launch activity to show detailed information about the spell.
                return false;
            }
        });

        return rootView;
    }

    private void prepareGroups(CharacterInfo myCharacter) {
        // TODO: Take spells in player's prepared list and populate the groups and their items.
        if(myCharacter != null) {
            for(int i = 0; i < 10; i++) {
                groupHeaders.add(String.valueOf(i));
            }
        }
    }
}
