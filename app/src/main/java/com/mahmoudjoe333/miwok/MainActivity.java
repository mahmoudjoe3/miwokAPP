package com.mahmoudjoe333.miwok;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mahmoudjoe333.miwok.ui.AllActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bTNNumber)
    Button bTNNumber;
    @BindView(R.id.bTNFamily)
    Button bTNFamily;
    @BindView(R.id.bTNColors)
    Button bTNColors;
    @BindView(R.id.bTNPhrases)
    Button bTNPhrases;
    @BindView(R.id.bTNSound)
    Button bTNSound;
    String tag = "LifeCycleEvents";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.v(tag, "In the onCreate() event");
    }
    //life cycle property
    public void onStart()
    {
        super.onStart();
        Log.v(tag, "In the onStart() event");
    }
    public void onRestart()
    {
        super.onRestart();
        Log.v(tag, "In the onRestart() event");
    }
    public void onResume()
    {
        super.onResume();
        Log.v(tag, "In the onResume() event");
    }
    public void onPause()
    {
        super.onPause();
        Log.v(tag, "In the onPause() event");
    }
    public void onStop()
    {
        super.onStop();
        Log.v(tag, "In the onStop() event");
    }
    public void onDestroy()
    {
        super.onDestroy();
        Log.v(tag, "In the onDestroy() event");
    }

    @OnClick({R.id.bTNNumber, R.id.bTNFamily, R.id.bTNColors, R.id.bTNPhrases, R.id.bTNSound})
    public void onViewClicked(View view) {
        Intent intent = null;
        String i = "";
        switch (view.getId()) {
            case R.id.bTNNumber:
                intent = new Intent(this, AllActivity.class);
                i="NumberActivity";
                break;

            case R.id.bTNFamily:
                intent = new Intent(this, AllActivity.class);
                i="familyMembersActivity";
                break;

            case R.id.bTNColors:
                intent = new Intent(this, AllActivity.class);
                i="colorsActivity";
                break;

            case R.id.bTNPhrases:
                intent = new Intent(this, AllActivity.class);
                i="PhrasesActivity";
                break;

            case R.id.bTNSound:
                playMedia(Uri.parse("https://soundcloud.com/ommarkammal/100a"));
                break;
        }

        assert intent != null;
        if (intent.resolveActivity(getPackageManager()) != null) {
            intent.putExtra(AllActivity.MAIN_ACTIVITY_KEY, i);
            startActivity(intent);
        }
    }

    public void playMedia(Uri file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(file);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}