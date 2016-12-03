package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import edu.uml.android.adventurersarchive.character.CharacterInfo;
import edu.uml.android.adventurersarchive.character.Coins;
import edu.uml.android.adventurersarchive.character.Equipment;

public class EquipmentDetailsActivity extends AppCompatActivity {
    private CharacterInfo myCharacter;
    private boolean newStuff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_details);
        if (myCharacter == null) {
            final GlobalState state = (GlobalState) getApplicationContext();
            myCharacter = state.getCharacter();
        }

        // assume yes for now
        newStuff = true;

    }

    public void setEquipment(View v){
        Equipment theEquipment;

        EditText tempEdit = (EditText) findViewById(R.id.equipment_name);
        String name = (tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.quantity);
        int q = Integer.parseInt(tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.description);
        String desc = (tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.weight);
        double w = Double.parseDouble(tempEdit.getText().toString());
        CheckBox tempCheck = (CheckBox) findViewById(R.id.equippable);
        boolean eqble = tempCheck.isChecked();
        boolean isEq = false;
        if (eqble) {
            tempCheck = (CheckBox) findViewById(R.id.isequipped);
            isEq = tempCheck.isChecked();
        }
        tempCheck = (CheckBox) findViewById(R.id.attunable);
        boolean atble = tempCheck.isChecked();
        boolean isAt = false;
        if (atble) {
            tempCheck = (CheckBox) findViewById(R.id.isattuned);
            isAt = tempCheck.isChecked();
        }
        tempEdit = (EditText) findViewById(R.id.value);
        double g = Double.parseDouble(tempEdit.getText().toString());
        Coins val = new Coins();
        val.add(g);

        if (newStuff) {
            theEquipment = new Equipment(w, name, desc, eqble, isEq, q, atble, isAt, val);
            if ((myCharacter != null) && (myCharacter.getEquipment() != null)) {
                myCharacter.getEquipment().add(theEquipment);
            }
        }
        // add edit case here


        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();

    }

 }
