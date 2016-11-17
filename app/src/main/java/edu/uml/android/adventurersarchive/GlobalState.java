package edu.uml.android.adventurersarchive;

import android.app.Application;

import edu.uml.android.adventurersarchive.character.CharacterInfo;

/**
 * Created by Darin on 11/16/2016.
 */
public class GlobalState extends Application {
    private CharacterInfo me;
    public CharacterInfo getCharacter() { return me; }
    public void setCharacter(CharacterInfo info) { me = info; }
}
