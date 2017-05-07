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
public class NumbersFragment extends Fragment {

    private MediaPlayer mMediaPlayer;

    private AudioManager am;
    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
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
    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //Create an ArrayList of Word objects
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("One", "Lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("Two", "Otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("Three", "Tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("Four", "Oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("Five", "Massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("Six", "Temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("Seven", "Kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("Eight", "Kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("Nine", "Wo´e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("Ten", "Na´aacha", R.drawable.number_ten, R.raw.number_ten));


        //Create an WordAdapter, whose data source is a list of Word.
        //The adapter knows how to create list items for each item in the list.
        WordAdapter wordAdapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

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
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
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

            am.abandonAudioFocus(afChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }
}