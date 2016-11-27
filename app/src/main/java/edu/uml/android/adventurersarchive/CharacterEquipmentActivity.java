package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import edu.uml.android.adventurersarchive.character.CharacterInfo;
import edu.uml.android.adventurersarchive.character.Coins;
import edu.uml.android.adventurersarchive.character.Equipment;

/**
 * Created by Darin on 11/6/2016.
 */
public class CharacterEquipmentActivity extends AppCompatActivity {
    private CharacterInfo myCharacter;
    public int totalweight;

    private List<Equipment> equipments;
    private Coins totalValue;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_equipment);

        Intent intent = getIntent();
        myCharacter = intent.getParcelableExtra("character");
        if (myCharacter == null) {
            final GlobalState state = (GlobalState) getApplicationContext();
            myCharacter = state.getCharacter();
        }
        refreshCoins();

    }

    private void refreshCoins() {
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
}

