package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        //Create an ArrayList of Word objects
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Red", "Wetetti", R.drawable.color_red));
        words.add(new Word("Mustard yellow", "Chiwiite", R.drawable.color_mustard_yellow));
        words.add(new Word("Dusty yellow", "Topiise", R.drawable.color_dusty_yellow));
        words.add(new Word("Green", "Chokokki", R.drawable.color_green));
        words.add(new Word("Brown", "Takaakki", R.drawable.color_brown));
        words.add(new Word("Gray", "Topoppi", R.drawable.color_gray));
        words.add(new Word("Black", "Kululli", R.drawable.color_black));
        words.add(new Word("White", "Kelelli", R.drawable.color_white));


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
