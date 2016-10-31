package com.example.testlogin.hearthstonesound;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Cardscards extends ActionBarActivity {
    private TextView cardName;
    private Button btnSnd1, btnSnd2, btnSnd3;
    private ImageView cardView;
     private String name, sound1, sound2, sound3,img;
    private int id;
    private SoundPool mSoundPool;
    private int msound1, msound2, msound3;
    private int mCountLoadedSound;
    private Context mContext;
    private ProgressDialog mProgressDialog;
    private AssetManager mAssetManager;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardscards);
        setTitle("Карты");
        cardName = (TextView) findViewById(R.id.textViewCardName);
        Bundle b = getIntent().getExtras();
        id = Integer.parseInt(b.getString("id"));
        name = b.getString("name");
        sound1 = b.getString("sound1");
        sound2 = b.getString("sound2");
        sound3 = b.getString("sound3");
        img = b.getString("img")+".png";
        cardName.setText(name);
        btnSnd1 = (Button) findViewById(R.id.btnSnd1);
        btnSnd2 = (Button) findViewById(R.id.btnSnd2);
        cardView = (ImageView) findViewById(R.id.imageView);
        btnSnd1.setText(sound1);
        btnSnd2.setText(sound2);
        layout = (RelativeLayout) findViewById(R.id.layout);
//        layout.setBack
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        mAssetManager = getAssets();

        mContext = this;

        // получим идентификаторы
        msound1 = loadSound(sound1+".mp3");
        msound2 = loadSound(sound2+".mp3");
        msound3 = loadSound(sound3+".mp3");
        showImg(img);
    }

    private void showImg(String imgName){
//        try {
//            FileInputStream in = new FileInputStream(mAssetManager.open(imgName));
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inSampleSize = 10; //Downsample by 10x
//            Bitmap bitmap;
//            bitmap = BitmapFactory.decodeStream(in, null, options);
//            cardView.setImageBitmap(bitmap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        InputStream bitmap=null;

        try {
            bitmap=getAssets().open(imgName);
            Bitmap bit=BitmapFactory.decodeStream(bitmap);
            cardView.setImageBitmap(bit);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bitmap!=null)
                try {
                    bitmap.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


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

    public void clickBtnSnd1(View view) {
        playSound(msound1);
    }

    public void clickBtnSnd2(View view) {
        playSound(msound2);
    }

    public void clickBtnSnd3(View view){
        playSound(msound3);
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







