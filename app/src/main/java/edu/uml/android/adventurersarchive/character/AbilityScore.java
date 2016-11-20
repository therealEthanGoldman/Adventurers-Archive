package edu.uml.android.adventurersarchive.character;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Darin on 11/3/2016.
 */
public class AbilityScore {
    private Scores type;
    public Scores getScoreType() { return type; }

    private int value;
    public int getScoreValue() { return value; }
    public void setScoreValue(int v) { value = v; }
    public int getScoreModifier() { return ((int)(value / 2) - 5); }

    private boolean proficient;
    public boolean isProficient() { return proficient; }
    public void setProficient(boolean p) { proficient = p; }

    public AbilityScore(Scores t, int v) {
        type = t;
        value = v;
    }

    public enum Scores {
        STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA;
    }
}
