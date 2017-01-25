package edu.uml.android.adventurersarchive;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.*;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.uml.android.adventurersarchive.character.CharacterInfo;

/**
 * Created by Darin on 11/29/2016.
 */
public class LoadCharacterAdapter extends ArrayAdapter<CharacterInfo> {
    public LoadCharacterAdapter(Context context, List<CharacterInfo> characters) {
        super(context, 0, characters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.character_list_item, parent, false);
        }

        final GlobalState state = (GlobalState) getContext().getApplicationContext();
        final CharacterInfo currCharacter = getItem(position);

        if(currCharacter != null) {
            TextView name = (TextView) listItemView.findViewById(R.id.load_character_name);
            name.setText(currCharacter.getCharacterName());

            TextView description = (TextView) listItemView.findViewById(R.id.load_character_description);
            description.setText(currCharacter.getCharacterRace()
                        + " " + currCharacter.getCharacterClass()
                        + " " + currCharacter.getCharacterLevel());

            Button loadButton = (Button) listItemView.findViewById(R.id.load_character_button);
            loadButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), ("Loading " + currCharacter.getCharacterName() + "..."),
                                   Toast.LENGTH_SHORT).show();
                    state.setCharacter(currCharacter);
                    Intent intent = new Intent(getContext(), CharacterMainActivity.class);
                    getContext().startActivity(intent);
                    ((AppCompatActivity) getContext()).finish();
                }
            });

            Button deleteButton = (Button) listItemView.findViewById(R.id.delete_character_button);
            deleteButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("Are you sure?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getContext(),
                                            ("Deleting " + currCharacter.getCharacterName()),
                                            Toast.LENGTH_SHORT).show();
                                    v.getContext().deleteFile(currCharacter.getFilename());
                                    LoadCharacterAdapter.this.remove(currCharacter);
                                }
                            })
                            .setNegativeButton("No", null).show();

                }
            });
        }

        return listItemView;
    }
}