package edu.uml.android.adventurersarchive.character;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Darin on 11/3/2016.
 */
public class CharacterInfo implements Parcelable {
    private String cName;
    public String getCharacterName() { return cName; }
    public void setCharacterName(String n) { cName = n; }

    private CharacterRace cRace;
    public CharacterRace getCharacterRace() { return cRace; }
    public void setCharacterRace(CharacterRace r) { cRace = r; }

    private CharacterClass cClass;
    public CharacterClass getCharacterClass() { return cClass; }
    public void setCharacterClass(CharacterClass c) { cClass = c; }

    private int cLevel;
    public int getCharacterLevel() { return cLevel; }
    public void setCharacterLevel(int l) { cLevel = l; }

    private AbilityScore [] cAbilityScores;
    public AbilityScore [] getAbilityScores() { return cAbilityScores; }
    public void setAbilityScores(AbilityScore [] scores) { cAbilityScores = scores; }
    public void setAbilityScore(AbilityScore score, int val) {
        for(int i = 0; i < cAbilityScores.length; i++) {
            if(cAbilityScores[i] == score) cAbilityScores[i].setScoreValue(val);
        }
    }

    public CharacterInfo(String n, CharacterRace r, CharacterClass c, int l) {
        cName = n;
        cRace = r;
        cClass = c;
        cLevel = l;

        cAbilityScores = new AbilityScore[] {new AbilityScore(AbilityScore.Scores.STRENGTH, 10),
                                             new AbilityScore(AbilityScore.Scores.DEXTERITY, 10),
                                             new AbilityScore(AbilityScore.Scores.CONSTITUTION, 10),
                                             new AbilityScore(AbilityScore.Scores.INTELLIGENCE, 10),
                                             new AbilityScore(AbilityScore.Scores.WISDOM, 10),
                                             new AbilityScore(AbilityScore.Scores.CHARISMA, 10)};
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cName);
        dest.writeParcelable(cRace, 0);
        dest.writeParcelable(cClass, 0);
        dest.writeInt(cLevel);
        dest.writeTypedArray(cAbilityScores, 0);
    }

    public static final Creator<CharacterInfo> CREATOR = new Creator<CharacterInfo>() {
        @Override
        public CharacterInfo createFromParcel(final Parcel source) {
            String n = source.readString();
            CharacterRace r = (CharacterRace) source.readParcelable(CharacterRace.class.getClassLoader());
            CharacterClass cl = (CharacterClass) source.readParcelable(CharacterClass.class.getClassLoader());
            int lv = source.readInt();

            CharacterInfo ch = new CharacterInfo(n, r, cl, lv);

            AbilityScore [] sc = source.createTypedArray(AbilityScore.CREATOR);
            ch.setAbilityScores(sc);

            return ch;
        }

        @Override
        public CharacterInfo[] newArray(final int size) {
            return new CharacterInfo[size];
        }
    };
}
