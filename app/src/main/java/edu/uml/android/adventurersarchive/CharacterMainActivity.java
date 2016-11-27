package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.uml.android.adventurersarchive.character.CharacterInfo;

/**
 * Created by Darin on 11/4/2016.
 */
public class CharacterMainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_main);

        GlobalState state = (GlobalState) getApplicationContext();
        setTitle(state.getCharacter().getCharacterName());
    }

    public void openCharacterSheet(View v) {
        Intent intent = new Intent(this, CharacterSheetActivity.class);
        startActivity(intent);
    }

    public void openEquipment(View v) {
        // TODO: Re-enable this once Equipment activity is complete.
        Intent intent = new Intent(this, CharacterEquipmentActivity.class);
        startActivity(intent);

    }

    public void openSpellbook(View v) {
        Intent intent = new Intent(this, CharacterSpellbookActivity.class);
        startActivity(intent);
    }

    public void openSkills(View v) {
        // TODO: Re-enable this once Skills activity is complete.
        /*Intent intent = new Intent(this, CharacterSkillsActivity.class);
        startActivity(intent);*/
    }
}
