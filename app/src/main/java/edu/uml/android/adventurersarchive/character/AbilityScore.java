package edu.uml.android.adventurersarchive.character;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Darin on 11/3/2016.
 */
public class AbilityScore implements Parcelable {
    private Scores type;

    protected AbilityScore(Parcel in) {
        type = in.readParcelable(Scores.class.getClassLoader());
        value = in.readInt();
    }

    public static final Creator<AbilityScore> CREATOR = new Creator<AbilityScore>() {
        @Override
        public AbilityScore createFromParcel(Parcel in) {
            return new AbilityScore(in);
        }

        @Override
        public AbilityScore[] newArray(int size) {
            return new AbilityScore[size];
        }
    };

    public Scores getScoreType() { return type; }

    private int value;
    public int getScoreValue() { return value; }
    public void setScoreValue(int v) { value = v; }
    public int getScoreModifier() {
        return ((value - 10) / 2);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(type, 0);
        dest.writeInt(value);
    }

    public enum Scores implements Parcelable {
        STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA;

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(ordinal());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Scores> CREATOR = new Creator<Scores>() {
            @Override
            public Scores createFromParcel(Parcel in) {
                return Scores.values()[in.readInt()];
            }

            @Override
            public Scores[] newArray(int size) {
                return new Scores[size];
            }
        };
    }

    public AbilityScore(Scores t, int v) {
        type = t;
        value = v;
    }
}
