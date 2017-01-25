package edu.uml.android.adventurersarchive.character;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Darin on 11/3/2016.
 */
public enum CharacterClass implements Serializable {
    BARBARIAN("Barbarian", new AbilityScore.Scores[] {AbilityScore.Scores.STRENGTH, AbilityScore.Scores.CONSTITUTION}),
    BARD("Bard", new AbilityScore.Scores[] {AbilityScore.Scores.DEXTERITY, AbilityScore.Scores.CHARISMA}),
    CLERIC("Cleric", new AbilityScore.Scores[] {AbilityScore.Scores.WISDOM, AbilityScore.Scores.CHARISMA}),
    DRUID("Druid", new AbilityScore.Scores[] {AbilityScore.Scores.INTELLIGENCE, AbilityScore.Scores.WISDOM}),
    FIGHTER("Fighter", new AbilityScore.Scores[] {AbilityScore.Scores.STRENGTH, AbilityScore.Scores.CONSTITUTION}),
    MONK("Monk", new AbilityScore.Scores[] {AbilityScore.Scores.STRENGTH, AbilityScore.Scores.DEXTERITY}),
    PALADIN("Paladin", new AbilityScore.Scores[] {AbilityScore.Scores.WISDOM, AbilityScore.Scores.CHARISMA}),
    RANGER("Ranger", new AbilityScore.Scores[] {AbilityScore.Scores.STRENGTH, AbilityScore.Scores.DEXTERITY}),
    ROGUE("Rogue", new AbilityScore.Scores[] {AbilityScore.Scores.DEXTERITY, AbilityScore.Scores.INTELLIGENCE}),
    SORCERER("Sorcerer", new AbilityScore.Scores[] {AbilityScore.Scores.CONSTITUTION, AbilityScore.Scores.CHARISMA}),
    WARLOCK("Warlock", new AbilityScore.Scores[] {AbilityScore.Scores.WISDOM, AbilityScore.Scores.CHARISMA}),
    WIZARD("Wizard", new AbilityScore.Scores[] {AbilityScore.Scores.INTELLIGENCE, AbilityScore.Scores.WISDOM});

    private String name;
    private AbilityScore.Scores [] proficiencies;
    public AbilityScore.Scores [] getProficiencies() { return proficiencies; }

    CharacterClass(String n, AbilityScore.Scores [] scores) {
        name = n;
        proficiencies = scores;
    }

    @Override
    public String toString() {
        return name;
    }

    public static CharacterClass getCharacterClass(String name) {
        for(CharacterClass c : CharacterClass.values()) {
            if(c.toString().equals(name)) return c;
        }

        return null;
    }

    public static int getHitDie(CharacterClass cc) {
        switch(cc) {
            case BARBARIAN: return 12;
            case FIGHTER:
            case PALADIN:
            case RANGER: return 10;
            case BARD:
            case CLERIC:
            case DRUID:
            case MONK:
            case ROGUE:
            case WARLOCK: return 8;
            case SORCERER:
            case WIZARD:
            default: return 6;
        }
    }

    public static boolean abilityIsProficient(CharacterClass cc, AbilityScore.Scores score) {
        for(AbilityScore.Scores sc : cc.getProficiencies()) {
            if(score.equals(sc)) return true;
        }
        return false;
    }
}
