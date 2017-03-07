package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        //Create an ArrayList of Word objects
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Father", "Epe", R.drawable.family_father));
        words.add(new Word("Mother", "Eta", R.drawable.family_mother));
        words.add(new Word("Son", "Angsi", R.drawable.family_son));
        words.add(new Word("Daughter", "Tune", R.drawable.family_daughter));
        words.add(new Word("Older brother", "Taachi", R.drawable.family_older_brother));
        words.add(new Word("Younger brother", "Chalitti", R.drawable.family_younger_brother));
        words.add(new Word("Older sister", "Tete", R.drawable.family_older_sister));
        words.add(new Word("Younger sister", "Kolliti", R.drawable.family_younger_sister));
        words.add(new Word("Grandmother", "Ama", R.drawable.family_grandmother));
        words.add(new Word("Grandfather", "Paapa", R.drawable.family_grandfather));


        //Create an WordAdapter, whose data source is a list of Word.
        //The adapter knows how to create list items for each item in the list.
        WordAdapter wordAdapter = new WordAdapter(this, words);

        //Get the listView and set the adapter for the listView
        ListView listView = (ListView) findViewById(R.id.activity_numbers);
        listView.setAdapter(wordAdapter);

        //Check if all the words is added to the array
        Log.v("NumbersActivity", "All numbers in words ArrayList: " + words);
    }
}
