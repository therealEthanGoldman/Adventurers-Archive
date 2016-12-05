package edu.uml.android.adventurersarchive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import edu.uml.android.adventurersarchive.database.DBHelper;
import edu.uml.android.adventurersarchive.info.Spell;

/**
 * Created by Darin on 12/5/2016.
 */
public class DisplaySpellActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_spell);

        Bundle bundle = getIntent().getExtras();
        String spellName = bundle.getString("spell");

        GlobalState state = (GlobalState) getApplicationContext();
        if((spellName != null) && !spellName.isEmpty()) setTitle(spellName);

        DBHelper db = state.getDatabase();
        Spell sp = db.getSpellByName(spellName);

        if(sp != null) {
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
}
