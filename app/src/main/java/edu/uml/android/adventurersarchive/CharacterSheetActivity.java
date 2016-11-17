package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.uml.android.adventurersarchive.character.AbilityScore;
import edu.uml.android.adventurersarchive.character.CharacterInfo;

/**
 * Created by Darin on 11/6/2016.
 */
    public class CharacterSheetActivity extends AppCompatActivity {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_character_sheet);

            final GlobalState state = (GlobalState) getApplicationContext();
            CharacterInfo myCharacter = state.getCharacter();
            if(myCharacter != null) {
            { // Set the character name.
                TextView nameText = (TextView) findViewById(R.id.sheet_name_label);
                nameText.setText("Character Name: " + myCharacter.getCharacterName());
            } // End name set.

            { // Set the character race.
                TextView raceText = (TextView) findViewById(R.id.sheet_race_label);
                raceText.setText("Character Race: " + myCharacter.getCharacterRace().toString());
            } // End race set.

            { // Set the character class.
                TextView classText = (TextView) findViewById(R.id.sheet_class_label);
                String text = "Character Class: " + myCharacter.getCharacterClass().toString() + " "
                                                  + myCharacter.getCharacterLevel();
                classText.setText(text);
                // TODO: Set a listener on this label so the user can tap it to enter their level.
            } // End class set.

            { // Set the experience level.


                TextView expText = (TextView) findViewById(R.id.sheet_exp_next_label);
                int level = myCharacter.getCharacterLevel();
                expText.setText("/ " + CharacterInfo.getNextLevelExp(level));
            } // End experience set.

            { // Set the character alignment.
                Spinner alignSpinner = (Spinner) findViewById(R.id.sheet_align_spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.alignment_array,
                                                                    R.layout.support_simple_spinner_dropdown_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                alignSpinner.setAdapter(adapter);
                alignSpinner.setSelection(myCharacter.getCharacterAlign().ordinal());
                // TODO: Set listener on spinner to update character alignment when item selected.
            } // End alignment set.

            { // Set the character ability scores and modifiers.
                EditText strInput = (EditText) findViewById(R.id.sheet_strength_input);
                strInput.setText(String.valueOf(myCharacter.getAbilityScore(AbilityScore.Scores.STRENGTH)
                                                           .getScoreValue()));
                strInput.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(!s.toString().isEmpty() && s.toString().matches("\\d+")) {
                            state.getCharacter().setAbilityScore(AbilityScore.Scores.STRENGTH,
                                    Integer.parseInt(s.toString()));
                            TextView mod = (TextView) findViewById(R.id.sheet_str_mod_label);
                            mod.setText("Mod: " + state.getCharacter().getAbilityScore(AbilityScore.Scores.STRENGTH)
                                    .getScoreModifier());
                        }
                    }
                });

                TextView modText = (TextView) findViewById(R.id.sheet_str_mod_label);
                modText.setText("Mod: " + myCharacter.getAbilityScore(AbilityScore.Scores.STRENGTH)
                                                     .getScoreModifier());
            } // End ability score set.
        }
    }
}
