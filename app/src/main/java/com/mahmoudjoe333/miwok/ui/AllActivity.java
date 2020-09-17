package com.mahmoudjoe333.miwok.ui;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.mahmoudjoe333.miwok.R;
import com.mahmoudjoe333.miwok.logic.WordAdapter;
import com.mahmoudjoe333.miwok.logic.word;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllActivity extends AppCompatActivity {
    public static final String MAIN_ACTIVITY_KEY="com.mahmoudjoe333.miwok.MAIN_ACTIVITY_KEY";
    private int color;
    @BindView(R.id.list)
    GridView rootView;

    WordAdapter adapter;
    AudioManager mAudioManager;
    MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        ButterKnife.bind(this);


        ArrayList<word> words = new ArrayList<word>( getList( getIntent().getStringExtra(MAIN_ACTIVITY_KEY) ) );
        adapter=new WordAdapter(this,words,color);
        mMediaPlayer=adapter.mediaPlayer;

        rootView.setAdapter(adapter);

        mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        // Request audio focus for playback
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                // Use the music stream.
                AudioManager.STREAM_MUSIC,
                // Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            // Start playback
        }
    }
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // Permanent loss of audio focus
                // Pause playback immediately
                releaseMediaPlayer();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // Pause playback
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // Your app has been granted audio focus again
                mMediaPlayer.start();
            }
        }
    };

    public void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if(mMediaPlayer!=null) {
            mMediaPlayer.release();
            mMediaPlayer=null;
            // Abandon audio focus when playback complete
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    private List<word> getList(String theIntent) {

        if(theIntent.equals("NumberActivity")) {
            setTitle("Number");
            color=R.color.Num;
            return Arrays.asList(new word("one", "lutti",R.drawable.number_one, R.raw.number_one)
                    , new word("two", "otiiko",R.drawable.number_two, R.raw.number_two)
                    , new word("three", "tolookosu",R.drawable.number_three, R.raw.number_three)
                    , new word("four", "oyyisa",R.drawable.number_four, R.raw.number_four)
                    , new word("five", "massokka",R.drawable.number_five, R.raw.number_five)
                    , new word("sex", "temmokka",R.drawable.number_six, R.raw.number_six)
                    , new word("seven", "kenekaku",R.drawable.number_seven, R.raw.number_seven)
                    , new word("eight", "kawinta",R.drawable.number_eight, R.raw.number_eight)
                    , new word("nine", "wo’e",R.drawable.number_nine, R.raw.number_nine)
                    , new word("ten", "na’aacha",R.drawable.number_ten, R.raw.number_ten));
        }

        else if(theIntent.equals("familyMembersActivity")) {
            setTitle("Family Member");
            color=R.color.Fam;
            return Arrays.asList(new word("father", "әpә",R.drawable.family_father, R.raw.family_father)
                    , new word("mother", "әṭa",R.drawable.family_mother, R.raw.family_mother)
                    , new word("son", "angsi",R.drawable.family_son, R.raw.family_son)
                    , new word("daughter", "tune",R.drawable.family_daughter, R.raw.family_daughter)
                    , new word("older brother", "taachi",R.drawable.family_older_brother, R.raw.family_older_brother)
                    , new word("younger brother", "chalitti",R.drawable.family_younger_brother, R.raw.family_younger_brother)
                    , new word("older sister", "teṭe",R.drawable.family_older_sister, R.raw.family_older_sister)
                    , new word("younger sister", "kolliti",R.drawable.family_younger_sister, R.raw.family_younger_sister)
                    , new word("grandmother", "ama",R.drawable.family_grandmother, R.raw.family_grandmother)
                    , new word("grandfather", "paapa",R.drawable.family_grandfather, R.raw.family_grandfather));
        }

        else if(theIntent.equals("colorsActivity")) {
            setTitle("Colors");
            color=R.color.col;
            return Arrays.asList(new word("red", "weṭeṭṭi",R.drawable.color_red, R.raw.color_red)
                    , new word("green", "chokokki",R.drawable.color_green, R.raw.color_green)
                    , new word("brown", "ṭakaakki",R.drawable.color_brown, R.raw.color_brown)
                    , new word("gray", "ṭopoppi",R.drawable.color_gray, R.raw.color_gray)
                    , new word("black", "kululli",R.drawable.color_black, R.raw.color_black)
                    , new word("white", "kelelli",R.drawable.color_white, R.raw.color_white)
                    , new word("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow)
                    , new word("mustard yellow", "chiwiiṭә",R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        }

        else {
            setTitle("Phrase");
            color=R.color.phrase;
            return Arrays.asList(
                    new word("Where are you going?", "minto wuksus")
                    , new word("What is your name?", "tinnә oyaase'nә")
                    , new word("My name is...", "oyaaset...")
                    , new word("How are you feeling?", "michәksәs?")
                    , new word("I’m feeling good.", "kuchi achit")
                    , new word("Are you coming?", "әәnәs'aa?")
                    , new word("Yes, I’m coming.", "hәә’ әәnәm")
                    , new word("I’m coming.", "әәnәm")
                    , new word("Let’s go.", "yoowutis")
                    , new word("Come here.", "әnni'nem"));
        }

    }
}