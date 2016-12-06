package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import edu.uml.android.adventurersarchive.database.DBHelper;
import edu.uml.android.adventurersarchive.info.Spell;

/**
 * Created by Darin on 12/5/2016.
 */
public class DisplaySpellActivity extends AppCompatActivity {
    private int spellID = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_spell);

        Bundle bundle = getIntent().getExtras();
        String spellName = bundle.getString("spell");

        GlobalState state = (GlobalState) getApplicationContext();
        if((spellName != null) && !spellName.isEmpty()) setTitle(spellName);

        DBHelper db = state.getDatabase();
        final Spell sp = db.getSpellByName(spellName);

        if(sp != null) {
            spellID = sp.getID();

            TextView level = (TextView) findViewById(R.id.spell_display_level);
            level.setText(sp.getLevelString());

            TextView classes = (TextView) findViewById(R.id.spell_display_classes);
            classes.setText("(" + sp.getSpellClasses() + ")");

            TextView time = (TextView) findViewById(R.id.spell_display_time);
            time.setText(sp.getCastingTime());

            TextView range = (TextView) findViewById(R.id.spell_display_range);
            range.setText(sp.getSpellRange());

            TextView components = (TextView) findViewById(R.id.spell_display_components);
            components.setText(sp.getSpellComponents());

            TextView duration = (TextView) findViewById(R.id.spell_display_duration);
            duration.setText(sp.getSpellDuration());

            TextView description = (TextView) findViewById(R.id.spell_display_description);
            description.setText(sp.getSpellDescription());
        } else {
            Toast.makeText(this, "Error loading spell...", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.spell_details_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        GlobalState state = (GlobalState) getApplicationContext();
        if(item.getItemId() == R.id.spell_menu_add_remove) {
            if(state.getCharacter().knowsSpell(spellID)) state.getCharacter().forgetSpell(spellID);
            else state.getCharacter().learnSpell(spellID);
        } else if(item.getItemId() == R.id.spell_menu_prepare_cast) {
            if(state.getCharacter().knowsSpell(spellID)) {
                if(state.getCharacter().isPrepared(spellID)) state.getCharacter().cast(spellID);
                else state.getCharacter().prepare(spellID);
            } else {
                Toast.makeText(this, "You don't know that spell!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e(getClass().getSimpleName(), "ERROR: What did you break now?");
        }

        return true;
    }
}
