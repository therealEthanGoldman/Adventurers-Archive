package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
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
 * Created by Darin on 11/6/2016.
 */
public class CharacterSpellbookActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_spellbook);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        SpellbookTabAdapter adapter = new SpellbookTabAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        GlobalState state = (GlobalState) getApplicationContext();
        state.setDatabase(new DBHelper(this));
        List<Spell> spells = SpellXMLParser.parseSpells(this);
        state.createDatabaseFromData(spells);
        Log.i(getClass().getSimpleName(), ("INFO: Database has " +
                state.getDatabase().numberOfRows() + " entries!"));
    }

    @Override
    public void onPause() {
        super.onPause();
        GlobalState state = (GlobalState) getApplicationContext();
        state.saveCharacter(this);
    }
}
