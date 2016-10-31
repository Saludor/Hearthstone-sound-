package com.example.testlogin.hearthstonesound;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity2Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        setTitle("Герои");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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

    public void clickBtn2(View v) {
        Intent intent = new Intent(this, Jana.class);
        startActivity(intent);
    }

    public void clickBtn(View v) {
        Intent intent = new Intent(this, Tral.class);
        startActivity(intent);
    }

    public void clickBtn3(View v) {
        Intent intent = new Intent(this, Valira.class);
        startActivity(intent);
    }

    public void clickBtn4(View v) {
        Intent intent = new Intent(this, Gar.class);
        startActivity(intent);
    }

    public void clickBtn5(View v) {
        Intent intent = new Intent(this, Rexar.class);
        startActivity(intent);
    }

    public void clickBtn6(View v) {
        Intent intent = new Intent(this, Anduin.class);
        startActivity(intent);
    }

    public void clickBtn7(View v) {
        Intent intent = new Intent(this, Uter.class);
        startActivity(intent);
    }

    public void clickBtn8(View v) {
        Intent intent = new Intent(this, Malf.class);
        startActivity(intent);
    }
    public void clickBtn9(View v) {
        Intent intent = new Intent(this, Guldan.class);
        startActivity(intent);
    }
    public void clickBtn10(View v) {
        Intent intent = new Intent(this, Magni.class);
        startActivity(intent);
    }
    public void clickBtn11(View v) {
        Intent intent = new Intent(this, Mediv.class);
        startActivity(intent);
    }
    public void clickBtn12(View v) {
        Intent intent = new Intent(this, Aleria.class);
        startActivity(intent);
    }
}



