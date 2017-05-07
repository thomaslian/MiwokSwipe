package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Thoma on 05.03.2017.
 * <p>
 * WordAdapter is an ArrayAdapter that can provide the layout for each list based on a data source,
 * which is a list of Word objects
 */

public class WordAdapter extends ArrayAdapter<Word> {

    int mBackgroundColor;

    /**
     * This is our own custom constructor (it does not mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want to
     * populate into the lists
     *
     * @param context   The current context. Used to inflate the layout file.
     * @param arrayList A list of Word objects to display in a list.
     */
    public WordAdapter(Activity context, ArrayList<Word> arrayList, int backgroundColor) {
        //Here, we initialize the ArrayAdapter's internal storage for teh context and the list.
        //The second argument is used when the ArrayAdapter is populating a single TextView.
        //Because this is a custom adapter for two TextViews, the adapter is not going to use this
        //second argument, so it can be any value. Here, we used 0.
        super(context, 0, arrayList);

        mBackgroundColor = backgroundColor;
    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that
     *                    should be displayed in list item view
     * @param convertView The recycled view to populate
     * @param parent      The parent ViewGroup that is used for inflation
     * @return The View for the position in the AdapterView
     */
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Check if the existing view is being reused. If not, inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the Word object located at this position in the list
        final Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml with the ID miwok_word_text_view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_word_text_view);
        //Get the miwok translation from the current Word object and set this text on the TextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        TextView englishTextView =
                (TextView) listItemView.findViewById(R.id.english_word_text_view);

        englishTextView.setText(currentWord.getDefaultTranslation());

        //Find the ImageView in the list_item.xml with the id "image_view"
        ImageView image = (ImageView) listItemView.findViewById(R.id.image_view);

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mBackgroundColor);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        //If the Word object has a image...
        if (currentWord.hasImage()) {
            //Get the image from the current Word object and set the resource to this ImageView
            image.setImageResource(currentWord.getImageResourceID());
            //Make sure the view is visible
            image.setVisibility(View.VISIBLE);
        } else {
            //Hide the image view if there is no image from the Word object
            image.setVisibility(View.GONE);
        }


        //Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
