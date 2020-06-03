package com.example.prot_1.model.db.tracking;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

class DBTracking implements DBTrackingInterface {

    private static final String TAG = "DBTracking";
    SQLiteDatabase myDB;

    public DBTracking(){
        myDB = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.prot_1/databases/Tracking.db", null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS myIds (id INT, time TIMESTAMP)");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS friendsIds (id INT, time TIMESTAMP)");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS encounteredIds (id INT, time TIMESTAMP)");
    }

    public ArrayList<Integer> selectIdData(String cmd){
        ArrayList<Integer> result = new ArrayList<>();

        return result;
    }

    public void insertData(String cmd){
        myDB.execSQL(cmd);
    }

    public void deleteData(String cmd){
        myDB.execSQL(cmd);
    }

    public void dropData(String cmd){
        myDB.execSQL(cmd);
    }

}
