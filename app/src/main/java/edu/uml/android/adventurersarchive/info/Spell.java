package edu.uml.android.adventurersarchive.info;

import java.io.Serializable;

/**
 * Created by Darin on 11/8/2016.
 */
public class Spell implements Serializable {
    private int id;
    public int getID() { return id; }
    public void setID(int newID) { id = newID; }

    private String spellName;
    public String getSpellName() { return spellName; }
    public void setSpellName(String name) { spellName = name; }

    private int spellLevel;
    public int getSpellLevel() { return spellLevel; }
    public void setSpellLevel(int level) { spellLevel = level; }
    public String getLevelString() {
        String result = String.valueOf(spellLevel);

        switch(spellLevel) {
            case 0:
                result += "-level";
                break;
            case 1:
                result += "st-level";
                break;
            case 2:
                result += "nd-level";
                break;
            case 3:
                result += "rd-level";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            default:
                result += "th-level";
                break;
        }

        return result;
    }

    private String spellSchool;
    public String getSpellSchool() { return spellSchool; }
    public void setSpellSchool(String school) { spellSchool = school; }

    private String castingTime;
    public String getCastingTime() { return castingTime; }
    public void setCastingTime(String ct) { castingTime = ct; }

    private String spellRange;
    public String getSpellRange() { return spellRange; }
    public void setSpellRange(String range) { spellRange = range; }

    private String spellComponents;
    public String getSpellComponents() { return spellComponents; }
    public void setSpellComponents(String components) { spellComponents = components; }

    private String spellDuration;
    public String getSpellDuration() { return spellDuration; }
    public void setSpellDuration(String duration) { spellDuration = duration; }

    private String spellClasses;
    public String getSpellClasses() { return spellClasses; }
    public void setSpellClasses(String classes) { spellClasses = classes; }

    private String spellDescription;
    public String getSpellDescription() { return spellDescription; }
    public void setSpellDescription(String description) { spellDescription = description; }
    public void addToDescription(String description) { spellDescription += description; }

    public Spell() {
        id = 0;
        spellName = "";
        spellLevel = 0;
        spellSchool = "";
        castingTime = "";
        spellRange = "";
        spellComponents = "";
        spellDuration = "";
        spellClasses = "";
        spellDescription = "";
    }

    public Spell(int newID, String sName, int sLevel, String sSchool,
                 String cTime, String sRange, String sComponents,
                 String sDuration, String sClasses, String sDescription) {
        id = newID;
        spellName = sName;
        spellLevel = sLevel;
        spellSchool = sSchool;
        castingTime = cTime;
        spellRange = sRange;
        spellComponents = sComponents;
        spellDuration = sDuration;
        spellClasses = sClasses;
        spellDescription = sDescription;
    }

    public String toString() {
        String result = "";

        result += spellName + "\n";
        result += ((spellLevel == 0)?"Cantrip":("" + spellLevel + "-level"));
        result += "(" + spellClasses + ")\n\n";
        result += "Casting Time: " + castingTime + "\n";
        result += "Range: " + spellRange + "\n";
        result += "Components: " + spellComponents + "\n";
        result += "Duration: " + spellDuration + "\n\n";
        result += spellDescription;

        return result;
    }
}
