package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uml.android.adventurersarchive.character.CharacterInfo;
import edu.uml.android.adventurersarchive.database.DBHelper;
import edu.uml.android.adventurersarchive.database.SpellXMLParser;
import edu.uml.android.adventurersarchive.info.Spell;

/**
 * Created by Darin on 11/8/2016.
 */
public class FullSpellbookFragment extends Fragment {
    private static int REQUEST_CODE = 3;

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

        expView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(FullSpellbookFragment.this.getActivity(),
                                           DisplaySpellActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("spell", (String) adapter.getChild(groupPosition, childPosition));

                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_CODE);

                return true;
            }
        });

        return rootView;
    }

    private void prepareGroups(CharacterInfo myCharacter) {
        GlobalState state = (GlobalState) getActivity().getApplicationContext();
        DBHelper db = state.getDatabase();

        if(myCharacter != null) {
            for(int i = 0; i < 10; i++) {
                groupHeaders.add(String.valueOf(i));
                List<String> items = db.getSpellNamesByClass(i, state.getCharacter().getCharacterClass());
                groupItems.put(String.valueOf(i), items);
            }
        }
    }
}
