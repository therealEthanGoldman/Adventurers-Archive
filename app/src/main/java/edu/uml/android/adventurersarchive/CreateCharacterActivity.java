package edu.uml.android.adventurersarchive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Darin on 11/4/2016.
 */
public class CreateCharacterActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_create_01);

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
    }
}
