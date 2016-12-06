package edu.uml.android.adventurersarchive.character;

import android.provider.Settings;

import java.io.Serializable;

import edu.uml.android.adventurersarchive.GlobalState;

/**
 * Created by Darin on 11/27/2016.
 */
public class Skill implements Serializable {

    public static String toString(SkillType type){
        switch(type){
            case ACROBATICS: return "Acrobatics";
            case ATHLETICS: return "Athletics";
            case SLEIGHT_OF_HAND: return "Sleight of Hand";
            case STEALTH: return "Stealth";
            case ANIMAL_HANDLING: return "Animal Handling";
            case INSIGHT: return "Insight";
            case MEDICINE: return "Medicine";
            case PERCEPTION: return "Perception";
            case SURVIVAL: return "Survival";
            case DECEPTION: return "Deception";
            case INTIMIDATION: return "Intimidation";
            case PERFORMANCE: return "Performance";
            case PERSUASION: return "Persuasion";
            case ARCANA: return "Arcana";
            case HISTORY: return "History";
            case INVESTIGATION: return "Investigation";
            case NATURE: return "Nature";
            case RELIGION: return "Religion";
            default: return "default";
        }
    }
    private SkillType type;
    public SkillType getSkillType() { return type; }

    public AbilityScore.Scores getAbility() { return SkillType.getAbility(type); }

    private boolean trained;
    public boolean isTrained() { return trained; }
    public void setTrained(boolean t) { trained = t; }

    public int getBonus(CharacterInfo me) {
        int abil = me.getAbilityScore(getAbility()).getScoreModifier();
        if(isTrained()) abil += me.getProficiency();
        return abil;
    }

    Skill(SkillType st) {
        type = st;
        trained = false; // False by default. Set manually.
    }

    public enum SkillType implements Serializable {
        ACROBATICS,
        ANIMAL_HANDLING,
        ARCANA,
        ATHLETICS,
        DECEPTION,
        HISTORY,
        INSIGHT,
        INTIMIDATION,
        INVESTIGATION,
        MEDICINE,
        NATURE,
        PERCEPTION,
        PERFORMANCE,
        PERSUASION,
        RELIGION,
        SLEIGHT_OF_HAND,
        STEALTH,
        SURVIVAL;

        public static AbilityScore.Scores getAbility(SkillType st) {
            switch(st) {
                case ATHLETICS: return AbilityScore.Scores.STRENGTH;
                case ACROBATICS:
                case SLEIGHT_OF_HAND:
                case STEALTH: return AbilityScore.Scores.DEXTERITY;
                case ANIMAL_HANDLING:
                case INSIGHT:
                case MEDICINE:
                case PERCEPTION:
                case SURVIVAL: return AbilityScore.Scores.WISDOM;
                case DECEPTION:
                case INTIMIDATION:
                case PERFORMANCE:
                case PERSUASION: return AbilityScore.Scores.CHARISMA;
                case ARCANA:
                case HISTORY:
                case INVESTIGATION:
                case NATURE:
                case RELIGION:
                default: return AbilityScore.Scores.INTELLIGENCE;
            }
        }
    }
}
