package com.example.prot_1.model.db.tracking;

import java.util.ArrayList;

public class DBControllerTracking implements DBControllerTrackingInterface {

    private static final String TAG = "DBControllerTracking";
    DBTrackingInterface db;


    public DBControllerTracking(){
        db = new DBTracking();
    }

    @Override
    public ArrayList<Integer> getMyIds(){
        ArrayList<Integer> myIds;
        String cmd = "";
        cmd += "SELECT id FROM myIds";
        myIds = db.selectIdData(cmd);
        return myIds;
    }

    @Override
    public ArrayList<Integer> getEncounteredIds(){
        ArrayList<Integer> encounteredIds;
        String cmd = "";
        cmd += "SELECT id FROM encounteredIds";
        encounteredIds = db.selectIdData(cmd);
        return encounteredIds;
    }

    @Override
    public void deleteMyOldIds(){
        String cmd = "";
        cmd += "DELETE FROM myIds WHERE time <= DATETIME('now', '-30 days')";
        db.deleteData(cmd);
    }

    @Override
    public void writeNewMyId(int id){
        String cmd = "";
        cmd += "INSERT INTO myIds (id, time) VALUES ('" + id + "', CURRENT_TIMESTAMP)";
        db.insertData(cmd);
    }

    @Override
    public void deleteOldEncounteredIds(){
        String cmd = "";
        cmd += "DELETE FROM encounteredIds WHERE time <= DATETIME('now', '-30 days')";
        db.deleteData(cmd);
    }
    @Override
    public void writeNewEncounteredIds(ArrayList<Integer> ids){
        for(int id: ids){
            String cmd = "";
            cmd += "INSERT INTO encounteredIds (id, time) VALUES ('" + id + "', CURRENT_TIMESTAMP)";
            db.insertData(cmd);
        }
    }

    @Override
    public void writeFriendsId(int id){
        String cmd = "";
        cmd += "INSERT INTO friendsIds (id, time) VALUES ('" + id + "', CURRENT_TIMESTAMP)";
        db.insertData(cmd);
    }

    @Override
    public void deleteOldFreindsId(){
        String cmd = "";
        cmd += "DELETE FROM friendsIds WHERE time <= DATETIME('now', '-30 days')";
        db.deleteData(cmd);
    }

}
