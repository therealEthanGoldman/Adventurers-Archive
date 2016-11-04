package edu.uml.android.adventurersarchive.character;

/**
 * Created by Darin on 11/3/2016.
 */
public class AbilityScore {
    private Scores type;
    public Scores getScoreType() { return type; }

    private int value;
    public int getScoreValue() { return value; }
    public void setScoreValue(int v) { value = v; }
    public int getScoreModifier() {
        return ((value - 10) / 2);
    }

    public enum Scores {
        STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA;
    }

    public AbilityScore(Scores t, int v) {
        type = t;
        value = v;
    }
}
