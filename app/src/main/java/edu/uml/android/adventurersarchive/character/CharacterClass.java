package edu.uml.android.adventurersarchive.character;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Darin on 11/3/2016.
 */
public enum CharacterClass {
    BARBARIAN("Barbarian"), BARD("Bard"),
    CLERIC("Cleric"), DRUID("Druid"),
    FIGHTER("Fighter"), MONK("Monk"),
    PALADIN("Paladin"), RANGER("Ranger"),
    ROGUE("Rogue"), SORCERER("Sorcerer"),
    WARLOCK("Warlock"), WIZARD("Wizard");

    private String name;

    CharacterClass(String n) {
        name = n;
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
}
