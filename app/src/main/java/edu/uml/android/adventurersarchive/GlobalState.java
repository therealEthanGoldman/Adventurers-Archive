package edu.uml.android.adventurersarchive;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import edu.uml.android.adventurersarchive.character.CharacterInfo;
import edu.uml.android.adventurersarchive.database.DBHelper;
import edu.uml.android.adventurersarchive.info.Spell;

/**
 * Created by Darin on 11/16/2016.
 */
public class GlobalState extends Application {
    private final String LOG_TAG = this.getClass().getSimpleName();

    private CharacterInfo me;
    public CharacterInfo getCharacter() { return me; }
    public void setCharacter(CharacterInfo info) { me = info; }

    private DBHelper db;
    public DBHelper getDatabase() { return db; }
    public void setDatabase(DBHelper d) { db = d; }
    public void createDatabaseFromData(List<Spell> spells) {
        if(db.isEmpty()) db.insertSpells(spells);
    }

    public void saveCharacter(Context context) {
        String filename = me.getFilename();

        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try {
            fout = context.openFileOutput(filename, MODE_PRIVATE);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(me);
            //Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show();

            if(oos != null) oos.close();
        } catch(Exception e) {
            Log.e(LOG_TAG, "ERROR: Problem saving character to file => " + filename);
            Toast.makeText(context, "Error saving character...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public CharacterInfo loadCharacter(Context context, String filename) {
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        CharacterInfo myCharacter = null;
        try {
            fin = context.openFileInput(filename);
            ois = new ObjectInputStream(fin);
            myCharacter = (CharacterInfo) ois.readObject();

            if(ois != null) ois.close();
        } catch(Exception e) {
            Log.e(LOG_TAG, "ERROR: Problem loading character from file => " + filename);
            Toast.makeText(context, "Error loading character...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return myCharacter;
    }
}
