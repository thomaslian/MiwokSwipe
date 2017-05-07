package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

    private MediaPlayer mMediaPlayer;

    private AudioManager am;

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //Create an ArrayList of Word objects
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Father", "Epe", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("Mother", "Eta", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("Son", "Angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("Daughter", "Tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("Older brother", "Taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("Younger brother", "Chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("Older sister", "Tete", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("Younger sister", "Kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("Grandmother", "Ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("Grandfather", "Paapa", R.drawable.family_grandfather, R.raw.family_grandfather));


        //Create an WordAdapter, whose data source is a list of Word.
        //The adapter knows how to create list items for each item in the list.
        WordAdapter wordAdapter = new WordAdapter(getActivity(), words, R.color.category_family);

        //Get the listView and set the adapter for the listView
        ListView listView = (ListView) rootView.findViewById(R.id.activity_numbers);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                int result = am.requestAudioFocus(afChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                Word word = words.get(position);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceID());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });

        return rootView;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
