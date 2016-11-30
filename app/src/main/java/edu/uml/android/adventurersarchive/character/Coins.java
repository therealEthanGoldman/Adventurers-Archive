package edu.uml.android.adventurersarchive.character;

import java.io.Serializable;

/**
 * Created by Golg on 11/16/2016.
 */

public class Coins implements Serializable {
    private int cp;
    public  int getCp() {return cp; };
    private int sp;
    public  int getSp() {return sp; };
    private int ep;
    public  int getEp() {return ep; };
    private int gp;
    public  int getGp() {return gp; };
    private int pp;
    public  int getPp() {return pp; };


    public Coins() {
        cp = 0;
        sp = 0;
        ep = 0;
        gp = 0;
        pp = 0;
    }

    public Coins(int c, int s, int e, int g, int p) {
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

    public void add(Coins loot) {
        cp += loot.cp;
        sp += loot.sp;
        ep += loot.ep;
        gp += loot.gp;
        pp += loot.pp;
        adjust();
    }

    public void setCoins(Coins loot) {
        cp = loot.cp;
        sp = loot.sp;
        ep = loot.ep;
        gp = loot.gp;
        pp = loot.pp;
        adjust();
    }

    public boolean take(Coins payment) {
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

