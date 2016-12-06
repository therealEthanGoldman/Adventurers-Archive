package edu.uml.android.adventurersarchive.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.uml.android.adventurersarchive.character.CharacterClass;
import edu.uml.android.adventurersarchive.info.Spell;

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

    public DBHelper(Context context) { super(context, DATABASE_NAME, null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE spells " +
                        "(id integer primary key, " +
                        "name text, " +
                        "level text, " +
                        "school text, " +
                        "time text, " +
                        "range text, " +
                        "components text, " +
                        "duration text, " +
                        "classes text, " +
                        "description text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS spells");
        onCreate(db);
    }

    public boolean checkDatabase() {
        SQLiteDatabase db = getReadableDatabase();
        return (db != null);
    }

    public boolean insertSpell(Spell sp) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", sp.getSpellName());
        contentValues.put("level", sp.getSpellLevel());
        contentValues.put("school", sp.getSpellSchool());
        contentValues.put("time", sp.getCastingTime());
        contentValues.put("range", sp.getSpellRange());
        contentValues.put("components", sp.getSpellComponents());
        contentValues.put("duration", sp.getSpellDuration());
        contentValues.put("classes", sp.getSpellClasses());
        contentValues.put("description", sp.getSpellDescription());

        db.insert("spells", null, contentValues);

        return true;
    }

    public boolean insertSpells(List<Spell> spells) {
        SQLiteDatabase db = getWritableDatabase();

        int sc = 0;
        for(Spell sp : spells) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", sp.getSpellName());
            contentValues.put("level", sp.getSpellLevel());
            contentValues.put("school", sp.getSpellSchool());
            contentValues.put("time", sp.getCastingTime());
            contentValues.put("range", sp.getSpellRange());
            contentValues.put("components", sp.getSpellComponents());
            contentValues.put("duration", sp.getSpellDuration());
            contentValues.put("classes", sp.getSpellClasses());
            contentValues.put("description", sp.getSpellDescription());
            long result = db.insert("spells", null, contentValues);
            if(result == -1) Log.i(getClass().getSimpleName(), ("INFO: Error inserting spell => " +
                                                                sp.getSpellName()));
            else sc++;
        }

        Log.i(getClass().getSimpleName(), ("INFO: Spells written => " + sc));

        return true;
    }

    public Cursor getData(int id) {
        // Select spell with the given ID.
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(("SELECT * FROM " + SPELLS_TABLE_NAME
                          + " WHERE " + SPELLS_COLUMN_ID + "=" + id), null);
    }

    public int numberOfRows() {
        SQLiteDatabase db = getReadableDatabase();
        return ((int) DatabaseUtils.queryNumEntries(db, SPELLS_TABLE_NAME));
    }

    public boolean isEmpty() {
        return (numberOfRows() == 0);
    }

    public int deleteSpell(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(SPELLS_TABLE_NAME, (SPELLS_COLUMN_ID + " = ? "),
                         new String[] { String.valueOf(id) });
    }

    public String getSpellNameByID(int id) {
        String sp = "";

        Cursor res = getData(id);

        res.moveToFirst();
        while(!res.isAfterLast()) {
            sp = res.getString(res.getColumnIndex(SPELLS_COLUMN_NAME));
            res.moveToNext();
        }

        return sp;
    }

    public Spell getSpellByID(int id) {
        Spell sp = new Spell();

        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(("SELECT * FROM " + SPELLS_TABLE_NAME + " WHERE "
                                + SPELLS_COLUMN_ID + "=" + id), null);

        res.moveToFirst();
        while(!res.isAfterLast()) {
            sp.setID(id);
            sp.setSpellName(res.getString(res.getColumnIndex(SPELLS_COLUMN_NAME)));
            sp.setSpellLevel(Integer.parseInt(res.getString(res.getColumnIndex(SPELLS_COLUMN_LEVEL))));
            sp.setSpellSchool(res.getString(res.getColumnIndex(SPELLS_COLUMN_SCHOOL)));
            sp.setCastingTime(res.getString(res.getColumnIndex(SPELLS_COLUMN_TIME)));
            sp.setSpellRange(res.getString(res.getColumnIndex(SPELLS_COLUMN_RANGE)));
            sp.setSpellComponents(res.getString(res.getColumnIndex(SPELLS_COLUMN_COMPONENTS)));
            sp.setSpellDuration(res.getString(res.getColumnIndex(SPELLS_COLUMN_DURATION)));
            sp.setSpellClasses(res.getString(res.getColumnIndex(SPELLS_COLUMN_CLASSES)));
            sp.setSpellDescription(res.getString(res.getColumnIndex(SPELLS_COLUMN_DESCRIPTION)));
            res.moveToNext();
        }

        return sp;
    }

    public Spell getSpellByName(String name) {
        Spell sp = new Spell();

        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(("SELECT * FROM " + SPELLS_TABLE_NAME + " WHERE "
                                + SPELLS_COLUMN_NAME + "=\"" + name + "\""), null);

        res.moveToFirst();
        while(!res.isAfterLast()) {
            sp.setID(Integer.parseInt(res.getString(res.getColumnIndex(SPELLS_COLUMN_ID))));
            sp.setSpellName(name);
            sp.setSpellLevel(Integer.parseInt(res.getString(res.getColumnIndex(SPELLS_COLUMN_LEVEL))));
            sp.setSpellSchool(res.getString(res.getColumnIndex(SPELLS_COLUMN_SCHOOL)));
            sp.setCastingTime(res.getString(res.getColumnIndex(SPELLS_COLUMN_TIME)));
            sp.setSpellRange(res.getString(res.getColumnIndex(SPELLS_COLUMN_RANGE)));
            sp.setSpellComponents(res.getString(res.getColumnIndex(SPELLS_COLUMN_COMPONENTS)));
            sp.setSpellDuration(res.getString(res.getColumnIndex(SPELLS_COLUMN_DURATION)));
            sp.setSpellClasses(res.getString(res.getColumnIndex(SPELLS_COLUMN_CLASSES)));
            sp.setSpellDescription(res.getString(res.getColumnIndex(SPELLS_COLUMN_DESCRIPTION)));
            res.moveToNext();
        }

        return sp;
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

    public ArrayList<String> getSpellNamesByClass(int level, CharacterClass cl) {
        ArrayList<String> spells = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(("SELECT * FROM " + SPELLS_TABLE_NAME + " WHERE "
                                + SPELLS_COLUMN_LEVEL + "=" + level + " AND "
                                + SPELLS_COLUMN_CLASSES + " LIKE \'%" + cl.toString() + "%\'"), null);

        res.moveToFirst();
        while (!res.isAfterLast()) {
            spells.add(res.getString(res.getColumnIndex(SPELLS_COLUMN_NAME)));
            res.moveToNext();
        }

        return spells;
    }

    public ArrayList<String> getSpellNamesByIDList(int level, CharacterClass cl, List<Integer> ids) {
        ArrayList<String> spells = new ArrayList<>();
        String inClause = ids.toString().replace('[', '(').replace(']', ')');

        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(("SELECT * FROM " + SPELLS_TABLE_NAME + " WHERE "
                                + SPELLS_COLUMN_LEVEL + "=" + level + " AND "
                                + SPELLS_COLUMN_ID + " IN " + inClause), null);

        res.moveToFirst();
        while(!res.isAfterLast()) {
            spells.add(res.getString(res.getColumnIndex(SPELLS_COLUMN_NAME)));
            res.moveToNext();
        }

        return spells;
    }
}
