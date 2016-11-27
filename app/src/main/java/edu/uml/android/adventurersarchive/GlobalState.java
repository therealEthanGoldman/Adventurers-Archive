package edu.uml.android.adventurersarchive;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.uml.android.adventurersarchive.character.CharacterInfo;

/**
 * Created by Darin on 11/16/2016.
 */
public class GlobalState extends Application {
    private final String LOG_TAG = this.getClass().getSimpleName();

    private CharacterInfo me;
    public CharacterInfo getCharacter() { return me; }
    public void setCharacter(CharacterInfo info) { me = info; }

    public void saveCharacter(Context context) {
        String filename = me.getCharacterName().replace(' ', '_') + ".aci";

        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try {
            fout = new FileOutputStream(filename, false);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(me);
            Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT);

            if(oos != null) oos.close();
        } catch(Exception e) {
            Log.e(LOG_TAG, "ERROR: Problem saving character to file => " + filename);
            Toast.makeText(context, "Error saving character...", Toast.LENGTH_SHORT);
            e.printStackTrace();
        }
    }

    public boolean loadCharacter(Context context, String filename) {
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        try {
            fin = new FileInputStream(filename);
            ois = new ObjectInputStream(fin);
            me = (CharacterInfo) ois.readObject();
            Toast.makeText(context, "Loaded!", Toast.LENGTH_SHORT);

            if(ois != null) ois.close();
            return true;
        } catch(Exception e) {
            Log.e(LOG_TAG, "ERROR: Problem loading character from file => " + filename);
            Toast.makeText(context, "Error loading character...", Toast.LENGTH_SHORT);
            e.printStackTrace();
        }

        return false;
    }
}
