package edu.uml.android.adventurersarchive;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.uml.android.adventurersarchive.character.CharacterInfo;
import edu.uml.android.adventurersarchive.character.Coins;
import edu.uml.android.adventurersarchive.character.Equipment;

/**
 * Created by Darin on 11/6/2016.
 */
public class CharacterEquipmentActivity extends ListActivity {
    private CharacterInfo myCharacter;
    public int totalweight;

    private List<Equipment> equipments;
    private Coins totalValue;

    // For the cursor adapter, specify which columns go into which views
    final String[] fromColumns = {"quant", "name", "isequip", "isattuned"};
    final int[] toViews = {R.id.quantity, R.id.equipname, R.id.isequipped, R.id.isattuned}; // The TextView in simple_list_item_1
    SimpleAdapter mAdapter;

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
        updateEquipmentList();
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
    public void openUpdateCash(View v) {
        // TODO: Re-enable this once Equipment activity is complete.
        Intent intent = new Intent(this, UpdateCashActivity.class);
        startActivityForResult(intent,1);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if(resultCode == RESULT_OK){
                refreshCoins();
            }
            if (resultCode == RESULT_CANCELED) {
                //Do nothing?
            }
        }
        if (requestCode == 2) {

            if(resultCode == RESULT_OK){
                updateEquipmentList();
            }
            if (resultCode == RESULT_CANCELED) {
                //Do nothing?
            }
        }
    }//onActivityResult=

    private void updateEquipmentList() {
        if ((null != myCharacter) && (null != myCharacter.getEquipment())) {
            equipments = myCharacter.getEquipment();
            ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
            for (Equipment equipment : equipments)
            {
                HashMap<String,String> hashMap=new HashMap<>();  // create the hashmap to store the data
                hashMap.put("id", equipment.id+"");
                hashMap.put("quant", equipment.quanity+"");
                hashMap.put("name", equipment.name+"");
                if (equipment.isEquippable) {
                    if (equipment.isEquipped) {
                        hashMap.put("isequip", getResources().getString(R.string.equipped));
                    } else {
                        hashMap.put("isequip", getResources().getString(R.string.unequipped));
                    }
                } else {
                    hashMap.put("isequip", getResources().getString(R.string.notequippable));
                }
                if (equipment.isAttunable){
                    hashMap.put("isAttunable", getResources().getString(R.string.attuned));
                } else {
                    hashMap.put("notattuneable", getResources().getString(R.string.notattunable));
                }
                arrayList.add(hashMap);
            }

            // Create an empty adapter we will use to display the loaded data.
            // We pass null for the cursor, then update it in onLoadFinished()
            mAdapter = new SimpleAdapter(this,
                    arrayList,
                    R.layout.equipment_list_item,
                    fromColumns, toViews);
            setListAdapter(mAdapter);

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        GlobalState state = (GlobalState) getApplicationContext();
        state.saveCharacter(this);
    }

    public void onAddLootClicked(View v) {
        // TODO: Re-enable this once Equipment activity is complete.
        Intent intent = new Intent(this, EquipmentDetailsActivity.class);
        startActivityForResult(intent,2);

    }
}

