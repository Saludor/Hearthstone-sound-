package com.example.testlogin.hearthstonesound;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by testLogin on 25.05.2015.
 */


public class Jana extends ActionBarActivity {


private SoundPool mSoundPool;
private AssetManager mAssetManager;
private int m1Sound, m2Sound, m3Sound, m4Sound, m5Sound,m6Sound;
private int mCountLoadedSound;
private Context mContext;
private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jana);
        setTitle("Джайна");

        mContext = this;

        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        mAssetManager = getAssets();

        // получим идентификаторы
        m1Sound = loadSound("m1.ogg");
        m2Sound = loadSound("m2.ogg");
        m3Sound = loadSound("m3.ogg");
        m4Sound = loadSound("m6.ogg");
        m5Sound = loadSound("m5.ogg");
        m6Sound = loadSound("m4.ogg");

        Button cowImageButton = (Button) this.findViewById(R.id.Button1);
        cowImageButton.setOnClickListener(onClickListener);

        Button chickenImageButton = (Button) this.findViewById(R.id.Button2);
        chickenImageButton.setOnClickListener(onClickListener);

        Button catImageButton = (Button) this.findViewById(R.id.Button3);
        catImageButton.setOnClickListener(onClickListener);

        Button duckImageButton = (Button) this.findViewById(R.id.Button4);
        duckImageButton.setOnClickListener(onClickListener);

        Button sheepImageButton = (Button) this.findViewById(R.id.Button5);
        sheepImageButton.setOnClickListener(onClickListener);

        Button dogImageButton = (Button) this.findViewById(R.id.Button6);
        dogImageButton.setOnClickListener(onClickListener);


    }

View.OnClickListener onClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Button1:
                playSound(m2Sound);
                break;
            case R.id.Button2:
                playSound(m3Sound);
                break;
            case R.id.Button3:
                playSound(m5Sound);
                break;
            case R.id.Button4:
                playSound(m4Sound);
                break;
            case R.id.Button5:
                playSound(m6Sound);
                break;
            case R.id.Button6:
                playSound(m1Sound);
                break;
        }
    }
};

    private void playSound(int sound) {
        if (sound > 0)
            mSoundPool.play(sound, 1, 1, 1, 0, 1);
    }

    private int loadSound(String fileName) {
        AssetFileDescriptor afd = null;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}