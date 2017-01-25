package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.uml.android.adventurersarchive.character.CharacterInfo;
import edu.uml.android.adventurersarchive.character.Coins;

public class UpdateCashActivity extends AppCompatActivity {
    private CharacterInfo myCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cash);

        if (myCharacter == null) {
            final GlobalState state = (GlobalState) getApplicationContext();
            myCharacter = state.getCharacter();
        }

        if (null != myCharacter) {
            Coins myCoins = myCharacter.getMoney();
            TextView nameText = (TextView) findViewById(R.id.cp_value);
            nameText.setText(""+myCoins.getCp());
            TextView nameText2 = (TextView) findViewById(R.id.sp_value);
            nameText2.setText(myCoins.getSp()+"");
            TextView nameText3 = (TextView) findViewById(R.id.ep_value);
            nameText3.setText(myCoins.getEp()+"");
            TextView nameText4 = (TextView) findViewById(R.id.gp_value);
            nameText4.setText(myCoins.getGp()+"");
            TextView nameText5 = (TextView) findViewById(R.id.pp_value);
            nameText5.setText(myCoins.getPp()+"");
        }
    }
    public void addcash(View v){
        EditText tempEdit = (EditText) findViewById(R.id.new_cp);
        int c = Integer.parseInt(tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.new_sp);
        int s = Integer.parseInt(tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.new_ep);
        int e = Integer.parseInt(tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.new_gp);
        int g = Integer.parseInt(tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.new_pp);
        int p = Integer.parseInt(tempEdit.getText().toString());
        Coins newcoins = new Coins(c,s,e,g,p);
        myCharacter.getMoney().add(newcoins);
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();

    }

    public void subtractcash(View v){
        EditText tempEdit = (EditText) findViewById(R.id.new_cp);
        int c = Integer.parseInt(tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.new_sp);
        int s = Integer.parseInt(tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.new_ep);
        int e = Integer.parseInt(tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.new_gp);
        int g = Integer.parseInt(tempEdit.getText().toString());
        tempEdit = (EditText) findViewById(R.id.new_pp);
        int p = Integer.parseInt(tempEdit.getText().toString());
        Coins newcoins = new Coins(c,s,e,g,p);
        if (myCharacter.getMoney().take(newcoins))
        {
            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);
            finish();
        }
        else {
           /* Intent returnIntent = new Intent();
            setResult(RESULT_CANCELED, returnIntent);
            finish()*/
            Toast.makeText(this, getResources().getString(R.string.overspend), Toast.LENGTH_LONG).show();
        }
    }
}
