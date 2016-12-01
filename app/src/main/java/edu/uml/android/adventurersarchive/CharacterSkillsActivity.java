package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import edu.uml.android.adventurersarchive.character.CharacterInfo;
import edu.uml.android.adventurersarchive.character.Skill;
import edu.uml.android.adventurersarchive.info.DiceRoller;

/**
 * Created by Darin on 11/9/2016.
 */
public class CharacterSkillsActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_skills);

        final GlobalState state = (GlobalState) getApplicationContext();
        CharacterInfo me = state.getCharacter();
        if(me != null) {
            setupSkill(me,Skill.SkillType.ACROBATICS,
                       R.id.skills_acrobatics_save_box,
                       R.id.skills_acrobatics_text_underscore);
            setupSkill(me,Skill.SkillType.ANIMAL_HANDLING,
                    R.id.skills_animal_handling_save_box,
                    R.id.skills_animal_handling_text_underscore);
            setupSkill(me,Skill.SkillType.ARCANA,
                    R.id.skills_arcana_save_box,
                    R.id.skills_arcana_text_underscore);
            setupSkill(me,Skill.SkillType.ATHLETICS,
                    R.id.skills_athletics_save_box,
                    R.id.skills_athletics_text_underscore);
            setupSkill(me,Skill.SkillType.DECEPTION,
                    R.id.skills_deception_save_box,
                    R.id.skills_deception_text_underscore);
            setupSkill(me,Skill.SkillType.HISTORY,
                    R.id.skills_history_save_box,
                    R.id.skills_history_text_underscore);
            setupSkill(me,Skill.SkillType.INSIGHT,
                    R.id.skills_insight_save_box,
                    R.id.skills_insight_text_underscore);
            setupSkill(me,Skill.SkillType.INTIMIDATION,
                    R.id.skills_intimidation_save_box,
                    R.id.skills_intimidation_text_underscore);
            setupSkill(me,Skill.SkillType.INVESTIGATION,
                    R.id.skills_investigation_save_box,
                    R.id.skills_investigation_text_underscore);
            setupSkill(me,Skill.SkillType.MEDICINE,
                    R.id.skills_medicine_save_box,
                    R.id.skills_medicine_text_underscore);
            setupSkill(me,Skill.SkillType.NATURE,
                    R.id.skills_nature_save_box,
                    R.id.skills_nature_text_underscore);
            setupSkill(me,Skill.SkillType.PERCEPTION,
                    R.id.skills_perception_save_box,
                    R.id.skills_perception_text_underscore);
            setupSkill(me,Skill.SkillType.PERFORMANCE,
                    R.id.skills_performance_save_box,
                    R.id.skills_performance_text_underscore);
            setupSkill(me,Skill.SkillType.PERSUASION,
                    R.id.skills_persuasion_save_box,
                    R.id.skills_persuasion_text_underscore);
            setupSkill(me,Skill.SkillType.RELIGION,
                    R.id.skills_religion_save_box,
                    R.id.skills_religion_text_underscore);
            setupSkill(me,Skill.SkillType.SLEIGHT_OF_HAND,
                    R.id.skills_soh_save_box,
                    R.id.skills_soh_text_underscore);
            setupSkill(me,Skill.SkillType.STEALTH,
                    R.id.skills_stealth_save_box,
                    R.id.skills_stealth_text_underscore);
            setupSkill(me,Skill.SkillType.SURVIVAL,
                    R.id.skills_survival_save_box,
                    R.id.skills_survival_text_underscore);
        }
    }

    private void setupSkill(CharacterInfo me, Skill.SkillType type, final int boxID, final int textID) {
        CheckBox prof = (CheckBox) findViewById(boxID);
        prof.setChecked(me.getSkill(type).isTrained());
        prof.setOnCheckedChangeListener(profChanged);

        TextView bonus = (TextView) findViewById(textID);
        int b = me.getSkill(type).getBonus(me);
        bonus.setText(String.valueOf(b));
        bonus.setPaintFlags(bonus.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private CheckBox.OnCheckedChangeListener profChanged = new CheckBox.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            CharacterInfo me = ((GlobalState) getApplicationContext()).getCharacter();
            Skill.SkillType sk;
            int textID = 0;

            switch(buttonView.getId()) {
                case R.id.skills_acrobatics_save_box:
                    sk = Skill.SkillType.ACROBATICS;
                    textID = R.id.skills_acrobatics_text_underscore;
                    break;
                case R.id.skills_animal_handling_save_box:
                    sk = Skill.SkillType.ANIMAL_HANDLING;
                    textID = R.id.skills_animal_handling_text_underscore;
                    break;
                case R.id.skills_arcana_save_box:
                    sk = Skill.SkillType.ARCANA;
                    textID = R.id.skills_arcana_text_underscore;
                    break;
                case R.id.skills_athletics_save_box:
                    sk = Skill.SkillType.ATHLETICS;
                    textID = R.id.skills_athletics_text_underscore;
                    break;
                case R.id.skills_deception_save_box:
                    sk = Skill.SkillType.DECEPTION;
                    textID = R.id.skills_deception_text_underscore;
                    break;
                case R.id.skills_history_save_box:
                    sk = Skill.SkillType.HISTORY;
                    textID = R.id.skills_history_text_underscore;
                    break;
                case R.id.skills_insight_save_box:
                    sk = Skill.SkillType.INSIGHT;
                    textID = R.id.skills_insight_text_underscore;
                    break;
                case R.id.skills_intimidation_save_box:
                    sk = Skill.SkillType.INTIMIDATION;
                    textID = R.id.skills_intimidation_text_underscore;
                    break;
                case R.id.skills_investigation_save_box:
                    sk = Skill.SkillType.INVESTIGATION;
                    textID = R.id.skills_investigation_text_underscore;
                    break;
                case R.id.skills_medicine_save_box:
                    sk = Skill.SkillType.MEDICINE;
                    textID = R.id.skills_medicine_text_underscore;
                    break;
                case R.id.skills_nature_save_box:
                    sk = Skill.SkillType.NATURE;
                    textID = R.id.skills_nature_text_underscore;
                    break;
                case R.id.skills_perception_save_box:
                    sk = Skill.SkillType.PERCEPTION;
                    textID = R.id.skills_perception_text_underscore;
                    break;
                case R.id.skills_performance_save_box:
                    sk = Skill.SkillType.PERFORMANCE;
                    textID = R.id.skills_performance_text_underscore;
                    break;
                case R.id.skills_persuasion_save_box:
                    sk = Skill.SkillType.PERSUASION;
                    textID = R.id.skills_persuasion_text_underscore;
                    break;
                case R.id.skills_religion_save_box:
                    sk = Skill.SkillType.RELIGION;
                    textID = R.id.skills_religion_text_underscore;
                    break;
                case R.id.skills_soh_save_box:
                    sk = Skill.SkillType.SLEIGHT_OF_HAND;
                    textID = R.id.skills_soh_text_underscore;
                    break;
                case R.id.skills_stealth_save_box:
                    sk = Skill.SkillType.STEALTH;
                    textID = R.id.skills_stealth_text_underscore;
                    break;
                case R.id.skills_survival_save_box:
                    sk = Skill.SkillType.SURVIVAL;
                    textID = R.id.skills_survival_text_underscore;
                    break;
                default:
                    sk = Skill.SkillType.ACROBATICS;
                    textID = R.id.skills_acrobatics_text_underscore;
                    break;
            }

            me.getSkill(sk).setTrained(isChecked);
            TextView txt = (TextView) findViewById(textID);
            txt.setText(String.valueOf(me.getSkill(sk).getBonus(me)));
        }
    };

    public void rollSkill(View v) {
        CharacterInfo me = ((GlobalState) getApplicationContext()).getCharacter();
        int bonus = 0;
        String name = "";

        switch(v.getId()) {
            case R.id.skills_acrobatics_button:
                name = Skill.SkillType.ACROBATICS.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.ACROBATICS).getBonus(me);
                break;
            case R.id.skills_animal_handling_button:
                name = Skill.SkillType.ANIMAL_HANDLING.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.ANIMAL_HANDLING).getBonus(me);
                break;
            case R.id.skills_arcana_button:
                name = Skill.SkillType.ARCANA.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.ARCANA).getBonus(me);
                break;
            case R.id.skills_athletics_button:
                name = Skill.SkillType.ATHLETICS.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.ATHLETICS).getBonus(me);
                break;
            case R.id.skills_deception_button:
                name = Skill.SkillType.DECEPTION.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.DECEPTION).getBonus(me);
                break;
            case R.id.skills_history_button:
                name = Skill.SkillType.HISTORY.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.HISTORY).getBonus(me);
                break;
            case R.id.skills_insight_button:
                name = Skill.SkillType.INSIGHT.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.INSIGHT).getBonus(me);
                break;
            case R.id.skills_intimidation_button:
                name = Skill.SkillType.INTIMIDATION.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.INTIMIDATION).getBonus(me);
                break;
            case R.id.skills_investigation_button:
                name = Skill.SkillType.INVESTIGATION.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.INVESTIGATION).getBonus(me);
                break;
            case R.id.skills_medicine_button:
                name = Skill.SkillType.MEDICINE.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.MEDICINE).getBonus(me);
                break;
            case R.id.skills_nature_button:
                name = Skill.SkillType.NATURE.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.NATURE).getBonus(me);
                break;
            case R.id.skills_perception_button:
                name = Skill.SkillType.PERCEPTION.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.PERCEPTION).getBonus(me);
                break;
            case R.id.skills_performance_button:
                name = Skill.SkillType.PERFORMANCE.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.PERFORMANCE).getBonus(me);
                break;
            case R.id.skills_persuasion_button:
                name = Skill.SkillType.PERSUASION.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.PERSUASION).getBonus(me);
                break;
            case R.id.skills_religion_button:
                name = Skill.SkillType.RELIGION.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.RELIGION).getBonus(me);
                break;
            case R.id.skills_soh_button:
                name = Skill.SkillType.SLEIGHT_OF_HAND.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.SLEIGHT_OF_HAND).getBonus(me);
                break;
            case R.id.skills_stealth_button:
                name = Skill.SkillType.STEALTH.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.STEALTH).getBonus(me);
                break;
            case R.id.skills_survival_button:
                name = Skill.SkillType.SURVIVAL.toString() + " ";
                bonus = me.getSkill(Skill.SkillType.SURVIVAL).getBonus(me);
                break;
            default: break;
        }

        int roll = DiceRoller.roll(20) + bonus;
        Toast.makeText(v.getContext(), (name + "Roll: " + roll), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        GlobalState state = (GlobalState) getApplicationContext();
        state.saveCharacter(this);
    }
}
