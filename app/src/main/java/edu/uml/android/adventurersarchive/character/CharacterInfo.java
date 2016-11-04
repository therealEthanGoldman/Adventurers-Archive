package edu.uml.android.adventurersarchive.character;

/**
 * Created by Darin on 11/3/2016.
 */
public class CharacterInfo {
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

    public CharacterInfo(String n, CharacterRace r, CharacterClass c, int l, AbilityScore [] s) {
        cName = n;
        cRace = r;
        cClass = c;
        cLevel = l;

        cAbilityScores = s;
    }
}
