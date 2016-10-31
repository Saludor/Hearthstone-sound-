package com.example.testlogin.hearthstonesound;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class MainActivity22Activity extends ActionBarActivity {
    private DBSchemaHelper dbSch;
    private static final String TAG ="cards" ;
    private static final boolean D = true;
    ArrayAdapter<CardsRecord> mDBAdapter;
    private ListView cardsView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards);
        dbSch = new DBSchemaHelper(this);
        mDBAdapter = new ArrayAdapter<CardsRecord>(this, android.R.layout.simple_list_item_1);
        cardsView = (ListView)findViewById(R.id.listView2);
        cardsView.setAdapter(mDBAdapter);
        cardsView.setOnItemClickListener(mCardsClickListener);
        showCards();
        setTitle("Карты");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity22, menu);
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

    public void clickBtn5(View v) {

    }

    public void clickBtn7(View v) {

    }

    public void clickBtn6(View v) {

        BufferedReader reader = null;
        dbSch.emptyTable(CardsTable.TABLE_NAME);
        try {
            Charset windows1251 = Charset.forName("windows-1251");
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("cards1.txt"), windows1251));

            // do reading, usually loop until end of file reading
            String mLine = reader.readLine();
            while (mLine != null) {
                //process line
                splitSound(mLine);


                mLine = reader.readLine();
            }
        } catch (IOException e) {
            //log the exception
            Log.e("file", e.getMessage());
        } catch (Exception e) {
            Log.e("file", e.getMessage());
        } finally {
           if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }

        }

    }

    public void splitSound(String line) {
        String sound3 = "", sound2 = "";
        String img = "";
        int pos = line.indexOf(",");
        String name = line.substring(0, pos);
        int pos1 = line.indexOf(",", pos + 1);
        String sound1 = line.substring(pos + 1, pos1);
        pos = line.indexOf(",", pos1 + 1);
        if (pos > pos1) {
            sound2 = line.substring(pos1 + 1, pos);
            pos1 = line.indexOf(",", pos + 1);
            if (pos1 > pos) {
                sound3 = line.substring(pos + 1, pos1);
                img = line.substring(pos1 + 1);
            } else {
                sound3 = line.substring(pos + 1);
            }
        } else {
            sound2 = line.substring(pos1 + 1);
        }
        dbSch.addCard(name, sound1, sound2, sound3, img );

    }
    public void splitSound1(String line){
        String[] names = line.split(",");


    }
    private void showCards() {
        SQLiteDatabase sqdb = dbSch.getReadableDatabase();
        Cursor c = null;
        CardsRecord rec;
        String query;
        query = "SELECT * FROM " + CardsTable.TABLE_NAME +
                " ORDER BY " + CardsTable.NAME + " ASC";

        int cntr = 0;
        try {
            c = sqdb.rawQuery(query, null);
            mDBAdapter.clear();
            while (c.moveToNext()) {
                rec = new CardsRecord(c);
                if (D) Log.d(TAG, "name = " + rec.name);
                mDBAdapter.add(rec);
                cntr++;
            }
        } catch (Exception e) {
            if (D) Log.e(TAG, "Exception: " + e.getMessage());
        } finally {
//            caption.setText(String.format("%s; %s%d", familyName, getString(R.string.measurements), cntr));
            if (c != null) c.close();
//            invalidateOptionsMenu();
        }
    }
    // The on-click listener for ListViews
    private AdapterView.OnItemClickListener mCardsClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            CardsRecord rec;
            rec = mDBAdapter.getItem(arg2);
            if (D) Log.d(TAG, "nm=" + rec.id);
            showCardsShowActivity(rec);
        }
    };

    private void showCardsShowActivity(CardsRecord record) {
        Intent intent = new Intent(this, Cardscards.class);
        Bundle b = new Bundle();
        b.putString("id", Integer.toString(record.id));
        b.putString("name", record.name);
        b.putString("sound1", record.sound1);
        b.putString("sound2", record.sound2);
        b.putString("sound3", record.sound3);
        b.putString("img", record.img);
        intent.putExtras(b);
//        startActivity(intent);
        startActivity(intent);
    }


}
