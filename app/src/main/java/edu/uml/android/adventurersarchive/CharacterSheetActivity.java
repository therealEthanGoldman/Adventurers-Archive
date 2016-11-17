package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.uml.android.adventurersarchive.character.AbilityScore;
import edu.uml.android.adventurersarchive.character.CharacterAlignment;
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
                EditText expInput = (EditText) findViewById(R.id.sheet_exp_input);
                expInput.setText(String.valueOf(myCharacter.getCharacterExp()));
                expInput.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(!s.toString().isEmpty() && s.toString().matches("\\d+")) {
                            state.getCharacter().setCharacterExp(Integer.parseInt(s.toString()));
                        }
                    }
                });

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
                alignSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        CharacterAlignment align = CharacterAlignment.getCharacterAlign(parent.getItemAtPosition(position)
                                .toString());
                        state.getCharacter().setCharacterAlign(align);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });
            } // End alignment set.

            { // Set the character ability scores and modifiers.
                setupAbilityScore(R.id.sheet_strength_input, R.id.sheet_str_mod_label,
                                  state, AbilityScore.Scores.STRENGTH);
                setupAbilityScore(R.id.sheet_dexterity_input, R.id.sheet_dex_mod_label,
                                  state, AbilityScore.Scores.DEXTERITY);
                setupAbilityScore(R.id.sheet_constitution_input, R.id.sheet_con_mod_label,
                                  state, AbilityScore.Scores.CONSTITUTION);
                setupAbilityScore(R.id.sheet_intelligence_input, R.id.sheet_int_mod_label,
                                  state, AbilityScore.Scores.INTELLIGENCE);
                setupAbilityScore(R.id.sheet_wisdom_input, R.id.sheet_wis_mod_label,
                                  state, AbilityScore.Scores.WISDOM);
                setupAbilityScore(R.id.sheet_charisma_input, R.id.sheet_cha_mod_label,
                                  state, AbilityScore.Scores.CHARISMA);
            } // End ability score set.
        }
    }

    private void setupAbilityScore(final int inputID, final int modID,
                                   final GlobalState state, final AbilityScore.Scores score) {
        EditText dexInput = (EditText) findViewById(inputID);
        dexInput.setText(String.valueOf(state.getCharacter().getAbilityScore(score).getScoreValue()));
        dexInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty() && s.toString().matches("\\d+")) {
                    state.getCharacter().setAbilityScore(score, Integer.parseInt(s.toString()));
                    TextView mod = (TextView) findViewById(modID);
                    mod.setText("Mod: " + state.getCharacter().getAbilityScore(score).getScoreModifier());
                }
            }
        });

        TextView modText = (TextView) findViewById(modID);
        modText.setText("Mod: " + state.getCharacter().getAbilityScore(score).getScoreModifier());
    }
}
