package com.example.android.miwok;

/**
 * Created by Thoma on 04.03.2017.
 *
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 * Objects of this class will have 3 properties, defaultTranslation, miwokTranslation
 * and imageResourceId
 */

public class Word {

    //Defualt translation for the word
    private String mDefaultTranslation;

    //Miwok translation for the word
    private String mMiwokTranslation;

    //Image
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    //Audio
    private int mAudioResourceID;

    /**
     * A constructor that creates a new Word object.
     *
     * @param defaultTranslation In this case, this is the English translation of the word.
     * @param miwokTranslation The miwok translation of the word.
     */
    public Word(String defaultTranslation, String miwokTranslation, int audio){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceID = audio;
    }

    /**
     * Second constructor that creates a new Word object,
     * this one can also store the path to a image for use with a word.
     *
     * @param defaultTranslation In this case, this is the English translation of the word.
     * @param miwokTranslation The miwok translation of the word.
     * @param imageResourceId Resource id for the image that will be used with the word.
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audio){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceID = audio;
    }

    /*
    Get the English translation of the word
    */
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    /*
    Get the Miwok translation of the word
     */
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    /*
    Get the image for the word
    */
    public int getImageResourceID(){
        return mImageResourceId;
    }

    /*
    Returns true if it have a image, and false if it is no image
     */
    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /*
    Get the audio for the word
     */
    public int getAudioResourceID(){
        return mAudioResourceID;
    }



}
