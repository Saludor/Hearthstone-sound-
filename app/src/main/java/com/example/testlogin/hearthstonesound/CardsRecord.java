package com.example.testlogin.hearthstonesound;

import android.database.Cursor;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by testLogin on 27.06.2015.
 */
public class CardsRecord {

    public int id, id_external, id_family;
    public String name, sound1, sound2, sound3, img;


    /**
     * Constructor.
     */
    public CardsRecord() {
        id = 0;
    }

    public CardsRecord(Cursor c) {
        int colid = c.getColumnIndex(CardsTable.ID);
        id = c.getInt(colid);
        colid = c.getColumnIndex(CardsTable.NAME);
        name = c.getString(colid);
        colid = c.getColumnIndex(CardsTable.SOUND1);
        sound1 = c.getString(colid);
        colid = c.getColumnIndex(CardsTable.SOUND2);
        sound2 = c.getString(colid);
        colid = c.getColumnIndex(CardsTable.SOUND3);
        sound3 = c.getString(colid);
        colid = c.getColumnIndex(CardsTable.IMAGE);
        img = c.getString(colid);


    }

    public String toString() {
//		return id_external + " " + id_family;
//        return String.format("%s / %s : %s",name , sound1, sound2);
        return name;
    }


}
