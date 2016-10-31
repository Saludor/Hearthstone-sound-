package com.example.testlogin.hearthstonesound;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by testLogin on 24.06.2015.
 */
public class DBSchemaHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cards";
    private static final int DATABASE_VERSION = 7;
    private static final String TAG = "DB";
    private static final boolean D = true;

    public DBSchemaHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    DBSchemaHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE DB TABLES
        createCardsTables(db);
        if (D) Log.d(TAG, "Tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DropTable(db, CardsTable.TABLE_NAME);
        createCardsTables(db);
    }

    private void createCardsTables(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + CardsTable.TABLE_NAME +
                    " (" + CardsTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + CardsTable.NAME + " TEXT,"
                    + CardsTable.SOUND1 + " TEXT,"
                    + CardsTable.IMAGE + " TEXT,"
                    + CardsTable.SOUND3 + " TEXT,"
                    + CardsTable.SOUND2 + " TEXT);");
        } catch (Exception e) {
            Log.e("CreateFamilyTable", e.getMessage());
        }
    }



    public void DropTable(SQLiteDatabase db, String tableName) {

    }

    public long addCard(String name, String sound1, String sound2, String sound3, String img){
        long result = 0;
        try {
            ContentValues cv = new ContentValues();
            cv.put(CardsTable.NAME, name);
            cv.put(CardsTable.SOUND1, sound1);
            cv.put(CardsTable.SOUND2, sound2);
            cv.put(CardsTable.SOUND3, sound3);
            cv.put(CardsTable.IMAGE, img);

            SQLiteDatabase sd = getWritableDatabase();
            result = sd.insert(CardsTable.TABLE_NAME, CardsTable.NAME, cv);
        } catch (Exception e) {
            if (D) Log.e(TAG, "addCardsItem exception = " + e.getMessage());
        }
        if (result < 0) Log.d(TAG, "addCardsItem - Error");
        return result;


    }


    public boolean emptyTable(String tableName) {
        SQLiteDatabase sd = getWritableDatabase();
        int result = 0;
        try {
            result = sd.delete(tableName, "1", null);
        } catch (Exception e) {
            Log.e("LOG_TAG", e.getMessage());
        }
        return (result > 0);
    }

}