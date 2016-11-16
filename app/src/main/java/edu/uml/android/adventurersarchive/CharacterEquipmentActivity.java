package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.uml.android.adventurersarchive.character.CharacterInfo;

/**
 * Created by Darin on 11/6/2016.
 */
public class CharacterEquipmentActivity extends AppCompatActivity {
    private CharacterInfo myCharacter;
    int totalweight;

    public class Equipment
    {
        double wt;
        String name;
        String description;
        boolean isEquiptable, isEquiped;
        int quanity;
        boolean attunable;
        coins value;

        Equipment(){
            wt = 0;
            name = "Blank Slate";
            description = "This item is a default";
            isEquiptable = false;
            isEquiped = false;
            quanity = 0;
            attunable = false;
            value = new coins( );
        }

        Equipment( double weight, String newname, String Newdesc, boolean equiptable, int howmuch, boolean attuned, coins kaching)
        {
            wt = weight;
            totalweight += weight;
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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_equipment);

        Intent intent = getIntent();
        myCharacter = intent.getParcelableExtra("character");



    }


    public class coins{
        int cp, sp, ep, gp, pp;

        coins()
        {
            cp = 0;
            sp = 0;
            ep = 0;
            gp = 0;
            pp = 0;
        }

        coins( int c, int s, int e, int g, int p)
        {
            cp = c;
            sp = s;
            ep = e;
            gp = g;
            pp = p;

            convert();

        }
        void convert(){
            while (cp > 10)
            {
                cp = cp - 10;
                sp++;
            }
            while (sp > 5)
            {
                sp = sp - 5;
                ep++;
            }
            while (ep > 2)
            {
                ep = ep - 2;
                gp++;
            }
            while (gp > 10)
            {
                gp = gp - 10;
                pp++;
            }
        }
        void set( coins newv)
        {
            cp = newv.cp;
            sp = newv.sp;
            ep = newv.ep;
            gp = newv.gp;
            pp = newv.pp;

            convert();

        }
        void add(coins loot)
        {
            cp = cp + loot.cp;
            sp = sp + loot.sp;
            ep = ep + loot.ep;
            gp = gp + loot.gp;
            pp = pp + loot.pp;
            convert();
        }

        boolean take(coins cost)
        {
            cp = cp - cost.cp;

            sp = sp - cost.sp;
            ep = ep - cost.ep;
            gp = gp - cost.gp;
            pp = pp - cost.pp;

            return negconvert();
        }

        boolean negconvert()
        {
            while (cp < 0)
            {
                sp--;
                while (sp <0 )
                {
                    ep--;
                    while (ep < 0)
                    {
                        gp--;
                        while(gp < 0)
                        {
                            pp--;
                            if (pp < 0)
                                return false;
                            gp = gp + 10;
                        }
                        ep = ep + 2;
                    }
                    sp = sp + 5;
                }
                cp = cp + 10;
            }
            return true;
        }
    }
}

