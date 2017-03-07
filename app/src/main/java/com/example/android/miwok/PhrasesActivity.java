package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        //Create an ArrayList of Word objects
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Where are you going?", "Minto wuksus"));
        words.add(new Word("What is your name?", "Tinna oyaase'ne"));
        words.add(new Word("My name is...", "Oyaaset..."));
        words.add(new Word("How are you feeling?", "Michekses?"));
        words.add(new Word("I´m feeling good.", "Kuchi achit"));
        words.add(new Word("Are you coming?", "Eenes´aa?"));
        words.add(new Word("Yes, I´m coming.", "Hee´ eenem"));
        words.add(new Word("I´m coming.", "Eenem"));
        words.add(new Word("Let´s go.", "Yoowutis"));
        words.add(new Word("Come here.", "Anni´nem"));


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
