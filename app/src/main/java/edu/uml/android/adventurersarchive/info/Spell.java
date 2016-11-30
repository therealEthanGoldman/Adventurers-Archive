package edu.uml.android.adventurersarchive.info;

import java.io.Serializable;

/**
 * Created by Darin on 11/8/2016.
 */
public class Spell implements Serializable {
    private String spellName;
    public String getSpellName() { return spellName; }

    private int spellLevel;
    public int getSpellLevel() { return spellLevel; }

    private String spellSchool;
    public String getSpellSchool() { return spellSchool; }

    private String castingTime;
    public String getCastingTime() { return castingTime; }

    private String spellRange;
    public String getSpellRange() { return spellRange; }

    private String spellComponents;
    public String getSpellComponents() { return spellComponents; }

    private String spellDuration;
    public String getSpellDuration() { return spellDuration; }

    private String spellDescription;
    public String getSpellDescription() { return spellDescription; }

    public Spell(String sName, int sLevel, String sSchool, String cTime, String sRange,
                 String sComponents, String sDuration, String sDescription) {
        spellName = sName;
        spellLevel = sLevel;
        spellSchool = sSchool;
        castingTime = cTime;
        spellRange = sRange;
        spellComponents = sComponents;
        spellDuration = sDuration;
        spellDescription = sDescription;
    }
}
