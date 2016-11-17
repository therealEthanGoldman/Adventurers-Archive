package edu.uml.android.adventurersarchive;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import edu.uml.android.adventurersarchive.character.CharacterInfo;

/**
 * Created by Darin on 11/8/2016.
 */
public class SpellbookTabAdapter extends FragmentPagerAdapter {
    public SpellbookTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) return new PreparedSpellsFragment();
        else if(position == 1) return new MySpellbookFragment();
        else if(position == 2) return new FullSpellbookFragment();
        else return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) return "Prepared";
        else if(position == 1) return "My Spells";
        else if(position == 2) return "All Spells";
        else return "";
    }
}
