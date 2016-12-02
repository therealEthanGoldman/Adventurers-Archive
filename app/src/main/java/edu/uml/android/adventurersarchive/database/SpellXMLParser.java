package edu.uml.android.adventurersarchive.database;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.uml.android.adventurersarchive.R;
import edu.uml.android.adventurersarchive.info.Spell;

/**
 * Created by Darin on 12/1/2016.
 */
public class SpellXMLParser {
    private static int XML_ID = R.xml.phb_spells;

    public static List<Spell> parseSpells(Context context) {
        List<Spell> spells = new ArrayList<>();
        Resources res = context.getResources();
        XmlResourceParser xrp = res.getXml(XML_ID);

        try {
            xrp.next();
            int type = xrp.getEventType();
            Spell sp = null;
            String currTag = "";
            while(type != XmlPullParser.END_DOCUMENT) {
                if(type == XmlPullParser.START_TAG) {
                    if(xrp.getName().equals("spell")) sp = new Spell();
                    currTag = xrp.getName();
                } else if(type == XmlPullParser.END_TAG) {
                    currTag = "";
                    if(xrp.getName().equals("spell")) spells.add(sp);
                } else if(type == XmlPullParser.TEXT) {
                    String text = xrp.getText();
                    if(currTag.equals("name")) sp.setSpellName(text);
                    else if(currTag.equals("level")) sp.setSpellLevel(Integer.parseInt(text));
                    else if(currTag.equals("school")) sp.setSpellSchool(text);
                    else if(currTag.equals("time")) sp.setCastingTime(text);
                    else if(currTag.equals("range")) sp.setSpellRange(text);
                    else if(currTag.equals("components")) sp.setSpellComponents(text);
                    else if(currTag.equals("duration")) sp.setSpellDuration(text);
                    else if(currTag.equals("classes")) sp.setSpellClasses(text);
                    else if(currTag.equals("text")) sp.addToDescription(text);
                }
                type = xrp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return spells;
    }
}
