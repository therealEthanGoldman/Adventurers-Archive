package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import edu.uml.android.adventurersarchive.character.CharacterInfo;
import edu.uml.android.adventurersarchive.character.Coins;

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
/*        EditText tempEdit = (EditText) findViewById(R.id.equipment_name);
        String name = (tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.quantity);
        int q = Integer.parseInt(tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.description);
        String desc = (tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.weight;
        double w = Integer.parseInt(tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.equippable);
        boolean eq = Integer.parseInt(tempEdit.getText().toString());
        Coins newcoins = new Coins(c,s,e,g,p);
        myCharacter.getMoney().add(newcoins);
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
*/
    }

    public void onSaveClicked(View v) {

    }
}
