package edu.uml.android.adventurersarchive.character;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Darin on 11/10/2016.
 */
public enum CharacterAlignment implements Parcelable {
    LG("Lawful Good"), NG("Neutral Good"), CG("Chaotic Good"),
    LN("Lawful Neutral"), TN("True Neutral"), CN("Chaotic Neutral"),
    LE("Lawful Evil"), NE("Neutral Evil"), CE("Chaotic Evil");

    String name;

    CharacterAlignment(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CharacterAlignment> CREATOR = new Creator<CharacterAlignment>() {
        @Override
        public CharacterAlignment createFromParcel(Parcel in) {
            return CharacterAlignment.values()[in.readInt()];
        }

        @Override
        public CharacterAlignment[] newArray(int size) {
            return new CharacterAlignment[size];
        }
    };

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

    CharacterAlignment(String n) {
        name = n;
    }
}
