package edu.uml.android.adventurersarchive;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import edu.uml.android.adventurersarchive.character.CharacterInfo;

/**
 * Created by Darin on 11/29/2016.
 */
public class LoadCharacterActivity extends AppCompatActivity {
    private LoadCharacterAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_load);

        ListView characterList = (ListView) findViewById(R.id.character_list);

        mAdapter = new LoadCharacterAdapter(this, new ArrayList<CharacterInfo>());
        characterList.setAdapter(mAdapter);

        List<CharacterInfo> characters = findCharacters();
        if((characters != null) && !characters.isEmpty()) mAdapter.addAll(characters);
    }

    private List<CharacterInfo> findCharacters() {
        List<CharacterInfo> characters = new ArrayList<>();
        GlobalState state = (GlobalState) getApplicationContext();

        String [] files = fileList();
        List<String> filenames = new ArrayList<>();
        for(String file : files) {
            if(file.endsWith(".aci")) filenames.add(file);
        }

        for(String file : filenames) {
            characters.add(state.loadCharacter(LoadCharacterActivity.this, file));
        }

        return characters;
    }
}
