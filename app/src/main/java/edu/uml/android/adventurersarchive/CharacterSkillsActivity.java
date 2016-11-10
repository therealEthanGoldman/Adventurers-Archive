package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.uml.android.adventurersarchive.character.CharacterInfo;

/**
 * Created by Darin on 11/9/2016.
 */
public class CharacterSkillsActivity extends AppCompatActivity {
    private CharacterInfo myCharacter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_skills);

        Intent intent = getIntent();

        myCharacter = (CharacterInfo) intent.getParcelableExtra("character");
    }
}
