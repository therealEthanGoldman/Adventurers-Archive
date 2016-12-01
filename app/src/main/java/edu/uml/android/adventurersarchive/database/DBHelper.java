package edu.uml.android.adventurersarchive.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Darin on 12/1/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "Spellbook.db";
    public static final String SPELLS_TABLE_NAME = "spells";
    public static final String SPELLS_COLUMN_ID = "id";
    public static final String SPELLS_COLUMN_NAME = "name";
    public static final String SPELLS_COLUMN_LEVEL = "level";
    public static final String SPELLS_COLUMN_SCHOOL = "school";
    public static final String SPELLS_COLUMN_TIME = "time";
    public static final String SPELLS_COLUMN_RANGE = "range";
    public static final String SPELLS_COLUMN_COMPONENTS = "components";
    public static final String SPELLS_COLUMN_DURATION = "duration";
    public static final String SPELLS_COLUMN_CLASSES = "classes";
    public static final String SPELLS_COLUMN_DESCRIPTION = "description";
    public static final String SPELLS_COLUMN_ROLL = "roll";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Database already exists. Ignore this.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Ignore this for now. Probably don't need it for anything.
    }

    public Cursor getData(int id) {
        // Select spell with the given ID.
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(("SELECT * FROM " + SPELLS_TABLE_NAME
                          + " WHERE " + SPELLS_COLUMN_ID + "=" + id), null);
    }

    public int deleteSpell(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(SPELLS_TABLE_NAME, (SPELLS_COLUMN_ID + " = ? "),
                         new String[] { String.valueOf(id) });
    }

    public ArrayList<String> getAllSpellNames() {
        ArrayList<String> spells = new ArrayList<String>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(("SELECT * FROM " + SPELLS_TABLE_NAME), null);

        res.moveToFirst();
        while (!res.isAfterLast()) {
            spells.add(res.getString(res.getColumnIndex(SPELLS_COLUMN_NAME)));
            res.moveToNext();
        }
        return spells;
    }

    public ArrayList<String> getSpellNamesByLevel(int level) {
        ArrayList<String> spells = new ArrayList<String>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(("SELECT * FROM " + SPELLS_TABLE_NAME + " WHERE "
                                + SPELLS_COLUMN_LEVEL + "=" + level), null);

        res.moveToFirst();
        while (!res.isAfterLast()) {
            spells.add(res.getString(res.getColumnIndex(SPELLS_COLUMN_NAME)));
            res.moveToNext();
        }
        return spells;
    }
}
