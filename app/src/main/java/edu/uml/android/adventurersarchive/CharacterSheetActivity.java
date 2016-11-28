package edu.uml.android.adventurersarchive;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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
                final TextView classText = (TextView) findViewById(R.id.sheet_class_label);
                String text = "Character Class: " + myCharacter.getCharacterClass().toString() + " "
                                                  + myCharacter.getCharacterLevel();
                classText.setText(text);
                classText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CharacterSheetActivity.this);
                        builder.setTitle("Enter Character Level");

                        final EditText input = new EditText(CharacterSheetActivity.this);
                        input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                        builder.setView(input);

                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String s = input.getText().toString();
                                if(!s.isEmpty() && s.matches("\\d+")) {
                                    int l = Integer.parseInt(s);
                                    state.getCharacter().setCharacterLevel(l);
                                    String text = "Character Class: "
                                            + state.getCharacter().getCharacterClass().toString() + " "
                                            + state.getCharacter().getCharacterLevel();
                                    classText.setText(text);

                                    TextView expText = (TextView) findViewById(R.id.sheet_exp_next_label);
                                    int level = state.getCharacter().getCharacterLevel();
                                    expText.setText("/ " + CharacterInfo.getNextLevelExp(level));

                                    TextView prof = (TextView) findViewById(R.id.sheet_proficiency_label);
                                    int bonus = state.getCharacter().getProficiency();
                                    prof.setText("Proficiency Bonus: " + ((bonus < 0)?"":"+") + String.valueOf(bonus));

                                    state.getCharacter().setHitDiceCount(level);

                                    EditText hdc = (EditText) findViewById(R.id.sheet_hd_curr_input);
                                    hdc.setText(String.valueOf(state.getCharacter().getHitDiceCount()));

                                    TextView hdt = (TextView) findViewById(R.id.sheet_hd_total);
                                    hdt.setText("/ " + String.valueOf(level));
                                }
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.show();
                    }
                });
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
                        if(!s.toString().isEmpty() && s.toString().matches("\\d+")) {
                            state.getCharacter().setInspiration(Integer.parseInt(s.toString()));
                        }
                    }
                });

                TextView prof = (TextView) findViewById(R.id.sheet_proficiency_label);
                int bonus = myCharacter.getProficiency();
                prof.setText("Proficiency Bonus: " + ((bonus < 0)?"":"+") + String.valueOf(bonus));
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
                        if(!s.toString().isEmpty() && s.toString().matches("\\d+")) {
                            state.getCharacter().setArmorClass(Integer.parseInt(s.toString()));
                        }
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
                        if(!s.toString().isEmpty() && s.toString().matches("\\d+")) {
                            state.getCharacter().setSpeed(Integer.parseInt(s.toString()));
                        }
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
                        if(!s.toString().isEmpty() && s.toString().matches("\\d+")) {
                            state.getCharacter().setCurrentHealth(Integer.parseInt(s.toString()));
                        }
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
                        if(!s.toString().isEmpty() && s.toString().matches("\\d+")) {
                            state.getCharacter().setMaximumHealth(Integer.parseInt(s.toString()));
                        }
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
                        if(!s.toString().isEmpty() && s.toString().matches("\\d+")) {
                            state.getCharacter().setHitDiceCount(Integer.parseInt(s.toString()));
                        }
                    }
                });

                TextView hdt = (TextView) findViewById(R.id.sheet_hd_total);
                hdt.setText("/ " + String.valueOf(myCharacter.getCharacterLevel()));
            } // End character vitals set.

            { // Set death save indicators.
                resetDeathSaves();
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

    public void resetDeathSaveButton(View v) {
        GlobalState state = (GlobalState) getApplicationContext();
        state.getCharacter().resetDeathSaves();
        resetDeathSaves();
    }

    private void resetDeathSaves() {
        final GlobalState state = (GlobalState) getApplicationContext();

        final CheckBox [] successes = {(CheckBox) findViewById(R.id.sheet_success_1),
                                       (CheckBox) findViewById(R.id.sheet_success_2),
                                       (CheckBox) findViewById(R.id.sheet_success_3)};
        final CheckBox [] failures = {(CheckBox) findViewById(R.id.sheet_fail_1),
                                      (CheckBox) findViewById(R.id.sheet_fail_2),
                                      (CheckBox) findViewById(R.id.sheet_fail_3)};

        for(int i = 0; i < 3; i++) {
            successes[i].setOnCheckedChangeListener(null);
            failures[i].setOnCheckedChangeListener(null);
        }

        for(int i = 0; i < 3; i++) {
            successes[i].setEnabled(false);
            successes[i].setChecked(false);
            failures[i].setEnabled(false);
            failures[i].setChecked(false);
        }

        for(int i = 0; i < 3; i++) {
            successes[i].setOnCheckedChangeListener(deathListener);
            failures[i].setOnCheckedChangeListener(deathListener);
        }

        int s = state.getCharacter().getDeathSuccesses();
        int f = state.getCharacter().getDeathFailures();

        for(int i = 0; i < s; i++) {
            successes[i].setChecked(true);
        }

        for(int j = 0; j < f; j++) {
            failures[j].setChecked(true);
        }

        if(!successes[0].isEnabled()) successes[0].setEnabled(true);
        if(!failures[0].isEnabled()) failures[0].setEnabled(true);
    }

    private CheckBox.OnCheckedChangeListener deathListener = new CheckBox.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            GlobalState state = (GlobalState) getApplicationContext();
            final CheckBox [] successes = {(CheckBox) findViewById(R.id.sheet_success_1),
                                           (CheckBox) findViewById(R.id.sheet_success_2),
                                           (CheckBox) findViewById(R.id.sheet_success_3)};
            final CheckBox [] failures = {(CheckBox) findViewById(R.id.sheet_fail_1),
                                          (CheckBox) findViewById(R.id.sheet_fail_2),
                                          (CheckBox) findViewById(R.id.sheet_fail_3)};

            switch(buttonView.getId()) {
                case R.id.sheet_success_1:
                    if(isChecked) {
                        state.getCharacter().setDeathSuccesses(1);
                        successes[1].setEnabled(true);
                    } else {
                        state.getCharacter().setDeathSuccesses(0);
                        successes[1].setEnabled(false);
                    }
                    successes[1].setChecked(false);
                    successes[2].setEnabled(false);
                    successes[2].setChecked(false);
                    break;
                case R.id.sheet_success_2:
                    if(isChecked) {
                        state.getCharacter().setDeathSuccesses(2);
                        successes[2].setEnabled(true);
                    } else {
                        state.getCharacter().setDeathSuccesses(1);
                        successes[2].setEnabled(false);
                    }
                    successes[2].setChecked(false);
                    break;
                case R.id.sheet_success_3:
                    if(isChecked) {
                        state.getCharacter().setDeathSuccesses(3);
                    } else {
                        state.getCharacter().setDeathSuccesses(2);
                    }
                    break;
                case R.id.sheet_fail_1:
                    if(isChecked) {
                        state.getCharacter().setDeathFailures(1);
                        failures[1].setEnabled(true);
                    } else {
                        state.getCharacter().setDeathFailures(0);
                        failures[1].setEnabled(false);
                    }
                    failures[1].setChecked(false);
                    failures[2].setEnabled(false);
                    failures[2].setChecked(false);
                    break;
                case R.id.sheet_fail_2:
                    if(isChecked) {
                        state.getCharacter().setDeathFailures(2);
                        failures[2].setEnabled(true);
                    } else {
                        state.getCharacter().setDeathFailures(1);
                        failures[2].setEnabled(false);
                    }
                    failures[2].setChecked(false);
                    break;
                case R.id.sheet_fail_3:
                    if(isChecked) {
                        state.getCharacter().setDeathFailures(3);
                    } else {
                        state.getCharacter().setDeathFailures(2);
                    }
                    break;
            }
        }
    };
}
