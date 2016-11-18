package edu.uml.android.adventurersarchive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import edu.uml.android.adventurersarchive.character.CharacterInfo;
import edu.uml.android.adventurersarchive.character.Equipment;

/**
 * Created by Darin on 11/6/2016.
 */
public class CharacterEquipmentActivity extends AppCompatActivity {
    private CharacterInfo myCharacter;
    public int totalweight;

    List<Equipment> equipments;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_equipment);

        Intent intent = getIntent();
        myCharacter = intent.getParcelableExtra("character");



    }


}

