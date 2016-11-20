package edu.uml.android.adventurersarchive.character;

import edu.uml.android.adventurersarchive.CharacterEquipmentActivity;

/**
 * Created by Golg on 11/16/2016.
 */

public class Equipment
{
    double wt;
    String name;
    String description;
    boolean isEquiptable, isEquiped;
    int quanity;
    boolean attunable;
    Coins value;

    Equipment(){
        wt = 0;
        name = "Blank Slate";
        description = "This item is a default";
        isEquiptable = false;
        isEquiped = false;
        quanity = 0;
        attunable = false;
        value = new Coins( );
    }

    Equipment( double weight, String newname, String Newdesc, boolean equiptable, int howmuch, boolean attuned, Coins kaching)
    {
        wt = weight;
        name = newname;
        description = Newdesc;
        isEquiptable = equiptable;
        isEquiped = false;
        quanity = howmuch;
        attunable = attuned;
        value = kaching;
    }

    void toggleEquiped( boolean val){
        if ( isEquiptable == true)
            isEquiped = val;
    }



}

