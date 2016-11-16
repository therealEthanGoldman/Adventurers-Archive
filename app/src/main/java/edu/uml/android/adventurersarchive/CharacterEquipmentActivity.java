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


    public class coins {
        private int cp;
        private int sp;
        private int ep;
        private int gp;
        private int pp;

        public coins() {
            cp = 0;
            sp = 0;
            ep = 0;
            gp = 0;
            pp = 0;
        }

        public coins(int c, int s, int e, int g, int p) {
            cp = c;
            sp = s;
            ep = e;
            gp = g;
            pp = p;
            adjust();
        }

        public double valueInGold() {
            return (pp*10.0 + gp*1.0 + ep*0.5 + 0.1*sp + 0.01*cp);
        }

        private void adjust() {
            int tmp = 0;

            if (cp >= 10) {
                sp += cp / 10;
                cp = cp%10;
            } else if (cp < 0) {
                tmp = -1*(cp / 10) + 1;
                sp -= tmp;
                cp += 10*tmp;
            }
            if (sp >= 5) {
                ep += sp / 5;
                sp = sp%5;
            } else if (sp < 0) {
                tmp = -1*(sp / 5) + 1;
                ep -= tmp;
                sp += 5*tmp;
            }
            if (ep >= 2) {
                gp += ep / 2;
                ep = ep%2;
            } else if (ep < 0) {
                tmp = -1*(ep / 2) + 1;
                gp -= tmp;
                ep += 2*tmp;
            }
            if (gp >= 10) {
                pp += gp / 10;
                gp = gp%10;
            } else if (gp < 0) {
                tmp = -1*(gp / 10) + 1;
                pp -= tmp;
                gp += 10*tmp;
            }
        }

        public void add(coins loot) {
            cp += loot.cp;
            sp += loot.sp;
            ep += loot.ep;
            gp += loot.gp;
            pp += loot.pp;
            adjust();
        }

        public boolean take(coins payment) {
            if (valueInGold() >= payment.valueInGold()) {
                cp -= payment.cp;
                sp -= payment.sp;
                ep -= payment.ep;
                gp -= payment.gp;
                pp -= payment.pp;
                adjust();
                return true;
            } else {
                return false;
            }
        }

        public boolean takeValue(double payValue) {
            if (valueInGold() >= payValue) {
                cp -= payValue*100;
                adjust();
                return true;
            } else {
                return false;
            }
        }
    }
}

