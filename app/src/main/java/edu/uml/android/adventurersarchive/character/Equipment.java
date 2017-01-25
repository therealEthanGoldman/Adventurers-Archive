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
    public int quantity;
    public boolean isAttunable, isAttuned;
    public Coins value;
    private static int lastUsedId = 0;


    public Equipment(){
        wt = 0;
        name = "Blank Slate";
        description = "This item is a default";
        isEquippable = false;
        isEquipped = false;
        quantity = 0;
        isAttunable = false;
        isAttuned = false;
        value = new Coins( );
        lastUsedId++;
        id = lastUsedId;
    }

    public Equipment( double weight, String newname, String Newdesc, boolean equiptable, boolean equipped, int howmuch, boolean attunable, boolean attuned, Coins kaching)
    {
        wt = weight;
        name = newname;
        description = Newdesc;
        isEquippable = equiptable;
        isEquipped = equipped;
        quantity = howmuch;
        isAttunable = attunable;
        isAttuned = attuned;
        value = kaching;
        lastUsedId++;
        id = lastUsedId;
    }

    public void toggleEquiped( boolean val){
        if ( isEquippable == true)
            isEquipped = val;
    }

    public void toggleAttuned( boolean val){
        if (isAttuned == true)
            isAttuned = val;
    }


}

