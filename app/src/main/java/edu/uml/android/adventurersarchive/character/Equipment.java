package edu.uml.android.adventurersarchive.character;

import java.io.Serializable;

/**
 * Created by Golg on 11/16/2016.
 */

public class Equipment implements Serializable {
    public int id;
    public double wt;
    public String name;
    public String description;
    public boolean isEquippable, isEquipped;
    public int quanity;
    public boolean attuneable;
    public Coins value;
    private static int lastUsedId = 0;


    public Equipment(){
        wt = 0;
        name = "Blank Slate";
        description = "This item is a default";
        isEquippable = false;
        isEquipped = false;
        quanity = 0;
        attuneable = false;
        value = new Coins( );
        lastUsedId++;
        id = lastUsedId;
    }

    public Equipment( double weight, String newname, String Newdesc, boolean equiptable, int howmuch, boolean attuned, Coins kaching)
    {
        wt = weight;
        name = newname;
        description = Newdesc;
        isEquippable = equiptable;
        isEquipped = false;
        quanity = howmuch;
        attuneable = attuned;
        value = kaching;
        lastUsedId++;
        id = lastUsedId;
    }

    public void toggleEquiped( boolean val){
        if ( isEquippable == true)
            isEquipped = val;
    }



}

