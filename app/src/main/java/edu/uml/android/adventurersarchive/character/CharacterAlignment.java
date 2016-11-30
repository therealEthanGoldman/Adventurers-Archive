package edu.uml.android.adventurersarchive.character;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Darin on 11/10/2016.
 */
public enum CharacterAlignment implements Serializable {
    LG("Lawful Good"), NG("Neutral Good"), CG("Chaotic Good"),
    LN("Lawful Neutral"), TN("True Neutral"), CN("Chaotic Neutral"),
    LE("Lawful Evil"), NE("Neutral Evil"), CE("Chaotic Evil");

    String name;

    CharacterAlignment(String n) {
        name = n;
    }

    public String toString() { return name; }
    public static CharacterAlignment getCharacterAlign(String name) {
        for(CharacterAlignment a : CharacterAlignment.values()) {
            if(a.toString().equals(name)) return a;
        }

        return null;
    }

    public static int getArrayIndex(CharacterAlignment a) {
        return a.ordinal();
    }
}
