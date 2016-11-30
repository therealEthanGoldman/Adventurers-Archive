package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import edu.uml.android.adventurersarchive.character.CharacterAlignment;
import edu.uml.android.adventurersarchive.character.CharacterClass;
import edu.uml.android.adventurersarchive.character.CharacterInfo;
import edu.uml.android.adventurersarchive.character.CharacterRace;

/**
 * Created by Darin on 11/4/2016.
 */
public class CreateCharacterActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_create);

        { // Initialize and set the race spinner adapter.
            Spinner raceChooser = (Spinner) findViewById(R.id.race_spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.race_array,
                    R.layout.support_simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            raceChooser.setAdapter(adapter);
        } // End initialization of race spinner adapter.

        { // Initialize and set the class spinner adapter.
            Spinner classChooser = (Spinner) findViewById(R.id.class_spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.class_array,
                    R.layout.support_simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            classChooser.setAdapter(adapter);
        } // End initialization of class spinner adapter.

        { // Initialize and set the alignment spinner adapter.
            Spinner alignChooser = (Spinner) findViewById(R.id.alignment_spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.alignment_array,
                    R.layout.support_simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            alignChooser.setAdapter(adapter);
        } // End initialization of alignment spinner adapter.
    }

    public void submitCharacter(View v) {
        Intent intent = new Intent(this, CharacterMainActivity.class);

        String n = ((EditText) findViewById(R.id.name_input)).getText().toString();

        Spinner raceSpinner = (Spinner) findViewById(R.id.race_spinner);
        CharacterRace r = CharacterRace.getCharacterRace(raceSpinner.getSelectedItem().toString());

        Spinner classSpinner = (Spinner) findViewById(R.id.class_spinner);
        CharacterClass c = CharacterClass.getCharacterClass(classSpinner.getSelectedItem().toString());

        Spinner alignSpinner = (Spinner) findViewById(R.id.alignment_spinner);
        CharacterAlignment a = CharacterAlignment.getCharacterAlign(alignSpinner.getSelectedItem().toString());

        int l = Integer.parseInt(((EditText) findViewById(R.id.level_input)).getText().toString());

        CharacterInfo ch = new CharacterInfo(n, r, c, a, l);
        GlobalState state = (GlobalState) getApplicationContext();
        state.setCharacter(ch);
        state.saveCharacter(v.getContext());

        Toast.makeText(this, ("Creating " + n + "..."), Toast.LENGTH_SHORT).show();

        startActivity(intent);

        finish(); // So the user doesn't return to this screen upon pressing BACK from CharacterMainActvitiy.
    }
}
