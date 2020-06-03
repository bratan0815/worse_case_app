package com.example.prot_1.control;

import com.example.prot_1.model.db.tracking.DBControllerTracking;
import com.example.prot_1.model.db.tracking.DBControllerTrackingInterface;

import java.util.ArrayList;
import java.util.Random;

public class TrackingManager {

    private static final String TAG = "TrackingManager";
    private static TrackingManager instance;
    int myCurrentId;
    ArrayList<Integer> myIds;
    ArrayList<Integer> friendsIds;
    ArrayList<Integer> encounteredIds;
    ArrayList<Integer> receivedIds;
    DBControllerTrackingInterface dbController;


    private TrackingManager(){
        dbController = new DBControllerTracking();
        myIds = new ArrayList<>();
        friendsIds = new ArrayList<>();
        encounteredIds = new ArrayList<>();
        receivedIds = new ArrayList<>();
        getMyIdsFromDB();
        if(myIds.size() < 30){
            fillMyIds();
        }
        myCurrentId = myIds.get(0);
    }

    public static TrackingManager getInstance(){
        if(instance == null){
            instance = new TrackingManager();
        }
        return instance;
    }


    public void saveReceivedIds(ArrayList<Integer> ids){
        receivedIds.addAll(ids);
        writeIdsToDB();
    }

    public int getOneOfMyIds(){
        return 0;
    }

    public ArrayList<Integer> getEncounteredIds(){
        return encounteredIds;
    }

    private void getMyIdsFromDB(){
        myIds.addAll(dbController.getMyIds());
    }

    private void writeIdsToDB(){
        dbController.writeNewEncounteredIds(encounteredIds);
    }

    private void fillMyIds(){
        while (myIds.size() < 30){
            int newId = generateNewId();
            myIds.add(newId);
            dbController.writeNewMyId(newId);
        }
    }

    private int generateNewId(){
        return new Random().nextInt(99999999);
    }

}
