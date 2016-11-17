package edu.uml.android.adventurersarchive.character;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Darin on 11/3/2016.
 */
public enum CharacterRace {
    DWARF("Dwarf"), ELF("Elf"),
    HALFLING("Halfling"), HUMAN("Human"),
    DRAGONBORN("Dragonborn"), GNOME("Gnome"),
    HALFORC("Half-Orc"), HALFELF("Half-Elf"),
    TIEFLING("Tiefling");

    private String name;

    CharacterRace(String n) {
        name = n;
    }

    @Override
    public String toString() {
        return name;
    }

    public static CharacterRace getCharacterRace(String name) {
        for(CharacterRace r : CharacterRace.values()) {
            if(r.toString().equals(name)) return r;
        }

        return null;
    }
}
