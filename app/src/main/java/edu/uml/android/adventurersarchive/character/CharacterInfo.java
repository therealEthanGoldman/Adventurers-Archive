package edu.uml.android.adventurersarchive.character;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import edu.uml.android.adventurersarchive.info.DiceRoller;

/**
 * Created by Darin on 11/3/2016.
 */
public class CharacterInfo implements Serializable {
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
    public int getProficiency() {
        // Calculates proficiency bonus based on the player's level.
        return (2 + (int)((cLevel - 1) / 4));
    }

    private int cExperience;
    public int getCharacterExp() { return cExperience; }
    public void setCharacterExp(int e) { cExperience = e; }

    private CharacterAlignment cAlignment;
    public CharacterAlignment getCharacterAlign() { return cAlignment; }
    public void setCharacterAlign(CharacterAlignment a) { cAlignment = a; }

    private int cInspiration;
    public int getInspiration() { return cInspiration; }
    public void setInspiration(int i) { cInspiration = i; }

    private AbilityScore [] cAbilityScores;
    public AbilityScore [] getAbilityScores() { return cAbilityScores; }
    public AbilityScore getAbilityScore(AbilityScore.Scores score) {
        for(AbilityScore sc : cAbilityScores) {
            if(sc.getScoreType() == score) return sc;
        }
        return null;
    }
    public void setAbilityScore(AbilityScore.Scores score, int val) {
        AbilityScore abil = getAbilityScore(score);
        abil.setScoreValue(val);
    }
    public int getInitiative() {
        return getAbilityScore(AbilityScore.Scores.DEXTERITY).getScoreModifier();
    }
    public int rollInitiative() {
        return getAbilityScore(AbilityScore.Scores.DEXTERITY).getScoreModifier() + DiceRoller.roll(20);
    }

    private int cHealthCurrent;
    private int cHealthMaximum;
    private int cHitDiceSize;
    private int cHitDiceCount;
    public int getCurrentHealth() { return cHealthCurrent; }
    public void setCurrentHealth(int h) { cHealthCurrent = h; }
    public int getMaximumHealth() { return cHealthMaximum; }
    public void setMaximumHealth(int h) { cHealthMaximum = h; }
    public int getHitDiceSize() { return cHitDiceSize; }
    public void setHitDiceSize(int d) { cHitDiceSize = d; }
    public int getHitDiceCount() { return cHitDiceCount; }
    public void setHitDiceCount(int c) { cHitDiceCount = c; }

    private int [] cDeathSaves;
    public int getDeathSuccesses() { return cDeathSaves[0]; }
    public int getDeathFailures() { return cDeathSaves[1]; }
    public void addDeathSuccess() { if(cDeathSaves[0] < 3) cDeathSaves[0]++; }
    public void addDeathFailure() { if(cDeathSaves[1] < 3) cDeathSaves[1]++; }
    public void resetDeathSaves() {
        cDeathSaves[0] = 0;
        cDeathSaves[1] = 0;
    }

    private int cArmorClass;
    public int getArmorClass() { return cArmorClass; }
    public void setArmorClass(int ac) { cArmorClass = ac; }

    private int cSpeed;
    public int getSpeed() { return cSpeed; }
    public void setSpeed(int s) { cSpeed = s; }

    private Skill [] cSkills;
    public Skill [] getSkills() { return cSkills; }
    public Skill getSkill(Skill.SkillType skill) {
        for(Skill sk : cSkills) {
            if(sk.getSkillType() == skill) return sk;
        }
        return null;
    }

    public CharacterInfo(String n, CharacterRace r, CharacterClass c, CharacterAlignment a, int l) {
        cName = n;
        cRace = r;
        cClass = c;
        cLevel = l;
        cExperience = 0;
        cAlignment = a;

        cHealthCurrent = 0;
        cHealthMaximum = 0;
        cHitDiceSize = CharacterClass.getHitDie(cClass);
        cHitDiceCount = cLevel;

        cArmorClass = 0;

        cDeathSaves = new int[2];
        resetDeathSaves();

        cInspiration = 0;
        cSpeed = 30;

        cAbilityScores = new AbilityScore[] {new AbilityScore(AbilityScore.Scores.STRENGTH, 10),
                                             new AbilityScore(AbilityScore.Scores.DEXTERITY, 10),
                                             new AbilityScore(AbilityScore.Scores.CONSTITUTION, 10),
                                             new AbilityScore(AbilityScore.Scores.INTELLIGENCE, 10),
                                             new AbilityScore(AbilityScore.Scores.WISDOM, 10),
                                             new AbilityScore(AbilityScore.Scores.CHARISMA, 10)};

        cSkills = new Skill[] {new Skill(Skill.SkillType.ACROBATICS),
                               new Skill(Skill.SkillType.ANIMAL_HANDLING),
                               new Skill(Skill.SkillType.ARCANA),
                               new Skill(Skill.SkillType.ATHLETICS),
                               new Skill(Skill.SkillType.DECEPTION),
                               new Skill(Skill.SkillType.HISTORY),
                               new Skill(Skill.SkillType.INSIGHT),
                               new Skill(Skill.SkillType.INTIMIDATION),
                               new Skill(Skill.SkillType.INVESTIGATION),
                               new Skill(Skill.SkillType.MEDICINE),
                               new Skill(Skill.SkillType.NATURE),
                               new Skill(Skill.SkillType.PERCEPTION),
                               new Skill(Skill.SkillType.PERFORMANCE),
                               new Skill(Skill.SkillType.PERSUASION),
                               new Skill(Skill.SkillType.RELIGION),
                               new Skill(Skill.SkillType.SLEIGHT_OF_HAND),
                               new Skill(Skill.SkillType.STEALTH),
                               new Skill(Skill.SkillType.SURVIVAL)};
    }

    public static final int [] LEVELS = {300,900,2700,6500,14000,
                                         23000,34000,48000,64000,85000,
                                         100000,120000,140000,165000,195000,
                                         225000,265000,305000,355000};
    public static int getNextLevelExp(int level) {
        if(level <= 0) return 0;
        if(level >= 20) level = 19;
        return CharacterInfo.LEVELS[level - 1];
    }
}
