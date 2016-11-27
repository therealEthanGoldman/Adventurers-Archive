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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import edu.uml.android.adventurersarchive.character.AbilityScore;
import edu.uml.android.adventurersarchive.character.CharacterAlignment;
import edu.uml.android.adventurersarchive.character.CharacterClass;
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
                //       The listener should update anything relating to level (number of hit die, etc)
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

            { // Set the inspiration and proficiency bonus values.
                EditText insp = (EditText) findViewById(R.id.sheet_inspiration_input);
                insp.setText(String.valueOf(myCharacter.getInspiration()));
                insp.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        state.getCharacter().setInspiration(Integer.parseInt(s.toString()));
                    }
                });

                TextView prof = (TextView) findViewById(R.id.sheet_proficiency_label);
                int bonus = myCharacter.getProficiency();
                prof.setText(((bonus < 0)?"":"+") + String.valueOf(bonus));
            } // End inspiration and proficiency bonus set.

            { // Set the character ability scores and modifiers.
                setupAbilityScore(R.id.sheet_strength_input, R.id.sheet_str_mod_label,
                                  R.id.sheet_str_save_label, R.id.sheet_str_save_box,
                                  state, AbilityScore.Scores.STRENGTH);
                setupAbilityScore(R.id.sheet_dexterity_input, R.id.sheet_dex_mod_label,
                                  R.id.sheet_dex_save_label, R.id.sheet_dex_save_box,
                                  state, AbilityScore.Scores.DEXTERITY);
                setupAbilityScore(R.id.sheet_constitution_input, R.id.sheet_con_mod_label,
                                  R.id.sheet_con_save_label, R.id.sheet_con_save_box,
                                  state, AbilityScore.Scores.CONSTITUTION);
                setupAbilityScore(R.id.sheet_intelligence_input, R.id.sheet_int_mod_label,
                                  R.id.sheet_int_save_label, R.id.sheet_int_save_box,
                                  state, AbilityScore.Scores.INTELLIGENCE);
                setupAbilityScore(R.id.sheet_wisdom_input, R.id.sheet_wis_mod_label,
                                  R.id.sheet_wis_save_label, R.id.sheet_wis_save_box,
                                  state, AbilityScore.Scores.WISDOM);
                setupAbilityScore(R.id.sheet_charisma_input, R.id.sheet_cha_mod_label,
                                  R.id.sheet_cha_save_label, R.id.sheet_cha_save_box,
                                  state, AbilityScore.Scores.CHARISMA);
            } // End ability score set.

            { // Set AC value.
                EditText ac = (EditText) findViewById(R.id.sheet_ac_input);
                ac.setText(String.valueOf(myCharacter.getArmorClass()));
                ac.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        state.getCharacter().setArmorClass(Integer.parseInt(s.toString()));
                    }
                });
            } // End AC set.

            { // Set Initiative field.
                TextView init = (TextView) findViewById(R.id.sheet_init_label);
                int bonus = myCharacter.getInitiative();
                init.setText("Initiative: " + ((bonus < 0)?"":"+") + String.valueOf(bonus));
            } // End Initiative set.

            { // Set Speed field.
                EditText spd = (EditText) findViewById(R.id.sheet_speed_input);
                spd.setText(String.valueOf(myCharacter.getSpeed()));
                spd.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        state.getCharacter().setSpeed(Integer.parseInt(s.toString()));
                    }
                });
            } // End Speed set.

            { // Set character vital fields.
                EditText curr = (EditText) findViewById(R.id.sheet_hp_curr_input);
                curr.setText(String.valueOf(myCharacter.getCurrentHealth()));
                curr.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        state.getCharacter().setCurrentHealth(Integer.parseInt(s.toString()));
                    }
                });

                EditText max = (EditText) findViewById(R.id.sheet_hp_max_input);
                max.setText(String.valueOf(myCharacter.getMaximumHealth()));
                max.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        state.getCharacter().setMaximumHealth(Integer.parseInt(s.toString()));
                    }
                });

                TextView hds = (TextView) findViewById(R.id.sheet_hd_label);
                hds.setText("HD: d" + String.valueOf(myCharacter.getHitDiceSize()));

                EditText hdc = (EditText) findViewById(R.id.sheet_hd_curr_input);
                hdc.setText(String.valueOf(myCharacter.getHitDiceCount()));
                hdc.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        state.getCharacter().setHitDiceCount(Integer.parseInt(s.toString()));
                    }
                });

                TextView hdt = (TextView) findViewById(R.id.sheet_hd_total);
                hdt.setText("/ " + String.valueOf(myCharacter.getCharacterLevel()));
            } // End character vitals set.

            { // Set death save indicators.
                RadioButton [] successes = {(RadioButton) findViewById(R.id.sheet_success_1),
                                            (RadioButton) findViewById(R.id.sheet_success_2),
                                            (RadioButton) findViewById(R.id.sheet_success_3)};
                RadioButton [] failures = {(RadioButton) findViewById(R.id.sheet_fail_1),
                                           (RadioButton) findViewById(R.id.sheet_fail_2),
                                           (RadioButton) findViewById(R.id.sheet_fail_3)};

                int s = myCharacter.getDeathSuccesses();
                int f = myCharacter.getDeathFailures();

                for(int i = 0; i < s; i++) { successes[i].setChecked(true); }
                for(int j = 0; j < f; j++) { failures[j].setChecked(true); }
            } // End death save indicator set.
        }
    }

    private void setupAbilityScore(final int inputID, final int modID, final int saveID, final int saveBoxID,
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

                    TextView modText = (TextView) findViewById(modID);
                    int mod = state.getCharacter().getAbilityScore(score).getScoreModifier();
                    modText.setText("Mod: " + ((mod < 0)?"":"+") + mod);

                    TextView saveText = (TextView) findViewById(saveID);
                    boolean prof = CharacterClass.abilityIsProficient(state.getCharacter().getCharacterClass(), score);
                    int save = mod + ((prof)?state.getCharacter().getProficiency():0);
                    saveText.setText("Save: " + ((save < 0)?"":"+") + save);

                    if(score.equals(AbilityScore.Scores.DEXTERITY)) {
                        TextView init = (TextView) findViewById(R.id.sheet_init_label);
                        int bonus = state.getCharacter().getInitiative();
                        init.setText("Initiative: " + ((bonus < 0)?"":"+") + String.valueOf(bonus));
                    }
                }
            }
        });

        TextView modText = (TextView) findViewById(modID);
        int mod = state.getCharacter().getAbilityScore(score).getScoreModifier();
        modText.setText("Mod: " + ((mod < 0)?"":"+") + mod);

        TextView saveText = (TextView) findViewById(saveID);
        boolean prof = CharacterClass.abilityIsProficient(state.getCharacter().getCharacterClass(), score);
        int save = mod + ((prof)?state.getCharacter().getProficiency():0);
        saveText.setText("Save: " + ((save < 0)?"":"+") + save);

        CheckBox saveBox = (CheckBox) findViewById(saveBoxID);
        saveBox.setChecked(prof);
    }
}
