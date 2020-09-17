package com.mahmoudjoe333.miwok.logic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mahmoudjoe333.miwok.R;

import java.util.List;


public class WordAdapter extends ArrayAdapter<word> {
    int color;
    Context appContext;
    public MediaPlayer mediaPlayer;
    public WordAdapter(@NonNull Context context, @NonNull List<word> objects, int color) {
        super(context, 0, objects);
        this.color=color;
        this.appContext=context;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View list_item = convertView;

        if (list_item == null) {
            list_item = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        word word = getItem(position);

        (list_item.findViewById(R.id.contain)).setBackgroundResource(color);

        TextView eng = list_item.findViewById(R.id.EngWord);
        eng.setText(word.getEng());

        TextView miwok = list_item.findViewById(R.id.miwokWord);
        miwok.setText(word.getMiwok());

        ImageView img = list_item.findViewById(R.id.img);
        if(word.getImg()!=null) {
            img.setImageResource(word.getImg());
            img.setVisibility(View.VISIBLE);
            ((ImageView)list_item.findViewById(R.id.play)).setVisibility(View.VISIBLE);

            list_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mediaPlayer!=null){
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }
                    mediaPlayer = MediaPlayer.create(appContext,word.getSong());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            if(mp!=null){
                                mp.release();
                                mp=null;
                                Toast.makeText(appContext, word.getEng()+"  finished", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

        }
        else {
            img.setVisibility(View.GONE);
            ((ImageView) list_item.findViewById(R.id.play)).setVisibility(View.GONE);
        }
        return list_item;
    }

}