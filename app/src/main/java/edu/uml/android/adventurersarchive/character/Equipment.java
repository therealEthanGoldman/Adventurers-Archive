package edu.uml.android.adventurersarchive.character;

/**
 * Created by Golg on 11/16/2016.
 */

public class Equipment
{
    public int id;
    public double wt;
    public String name;
    public String description;
    public boolean isEquiptable, isEquipped;
    public int quanity;
    public boolean attuneable;
    public Coins value;
    private static int lastUsedId = 0;


    Equipment(){
        wt = 0;
        name = "Blank Slate";
        description = "This item is a default";
        isEquiptable = false;
        isEquipped = false;
        quanity = 0;
        attuneable = false;
        value = new Coins( );
        lastUsedId++;
        id = lastUsedId;
    }

    Equipment( double weight, String newname, String Newdesc, boolean equiptable, int howmuch, boolean attuned, Coins kaching)
    {
        wt = weight;
        name = newname;
        description = Newdesc;
        isEquiptable = equiptable;
        isEquipped = false;
        quanity = howmuch;
        attuneable = attuned;
        value = kaching;
        lastUsedId++;
        id = lastUsedId;
    }

    void toggleEquiped( boolean val){
        if ( isEquiptable == true)
            isEquipped = val;
    }



}

