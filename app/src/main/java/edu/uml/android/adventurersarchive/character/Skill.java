package edu.uml.android.adventurersarchive.character;

import java.io.Serializable;

/**
 * Created by Darin on 11/27/2016.
 */
public class Skill implements Serializable {
    private SkillType type;
    public SkillType getSkillType() { return type; }

    public AbilityScore.Scores getAbility() { return SkillType.getAbility(type); }

    private boolean trained;
    public boolean isTrained() { return trained; }
    public void setTrained(boolean t) { trained = t; }

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
