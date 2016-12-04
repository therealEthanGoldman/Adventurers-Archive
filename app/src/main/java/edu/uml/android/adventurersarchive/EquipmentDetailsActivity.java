package edu.uml.android.adventurersarchive;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
    private Equipment theEquipment;


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
        theEquipment = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Integer value = extras.getInt("pos");
            if (value != null) {
                newStuff = false;
                loadEquipment(value);
            }

        }
    }

    public void setEquipment(View v){
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
        else if (theEquipment != null)
        {
            theEquipment.name = name;
            theEquipment.quantity = q;
            theEquipment.description = desc;
            theEquipment.wt = w;
            theEquipment.isEquippable = eqble;
            theEquipment.isEquipped = isEq;
            theEquipment.isAttunable = atble;
            theEquipment.isAttuned = isAt;
            theEquipment.value = val;
        }
        // add edit case here
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();

    }

    public void loadEquipment(int pos){
        if((myCharacter != null) && ((theEquipment = myCharacter.getEquipment().get(pos))!= null)) {
            EditText tempEdit = (EditText) findViewById(R.id.equipment_name);
            tempEdit.setText(theEquipment.name);
            tempEdit = (EditText) findViewById(R.id.quantity);
            tempEdit.setText(Integer.toString(theEquipment.quantity));
            tempEdit = (EditText) findViewById(R.id.description);
            tempEdit.setText(theEquipment.description);
            tempEdit = (EditText) findViewById(R.id.weight);
            tempEdit.setText(Double.toString(theEquipment.wt));
            CheckBox tempCheck = (CheckBox) findViewById(R.id.equippable);
            tempCheck.setChecked(theEquipment.isEquippable);
            tempCheck = (CheckBox) findViewById(R.id.isequipped);
            tempCheck.setChecked(theEquipment.isEquipped);
            tempCheck = (CheckBox) findViewById(R.id.attunable);
            tempCheck.setChecked(theEquipment.isAttunable);
            tempCheck = (CheckBox) findViewById(R.id.isattuned);
            tempCheck.setChecked(theEquipment.isAttuned);
            tempEdit = (EditText) findViewById(R.id.value);
            tempEdit.setText(Double.toString(theEquipment.value.valueInGold()));
        }
    }

    public void trash( View v){
        if (theEquipment != null){

            myCharacter.getEquipment().remove(theEquipment);
            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);
            finish();
        }

    }

    public void onTrashClicked(final View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                trash(v);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setMessage(R.string.sure);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

 }
