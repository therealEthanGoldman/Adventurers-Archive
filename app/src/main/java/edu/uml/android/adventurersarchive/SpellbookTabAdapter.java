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
    private CharacterInfo myCharacter;

    public SpellbookTabAdapter(CharacterInfo ch, FragmentManager fm) {
        super(fm);
        myCharacter = ch;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("character", myCharacter);
        if(position == 0) {
            PreparedSpellsFragment prepared = new PreparedSpellsFragment();
            prepared.setArguments(bundle);
            return prepared;
        } else if(position == 1) {
            MySpellbookFragment myspellbook = new MySpellbookFragment();
            myspellbook.setArguments(bundle);
            return myspellbook;
        } else if(position == 2) {
            FullSpellbookFragment fullspell = new FullSpellbookFragment();
            fullspell.setArguments(bundle);
            return fullspell;
        }
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
