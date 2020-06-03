package com.example.prot_1.model.db.tracking;

import java.util.ArrayList;

interface DBTrackingInterface {

    ArrayList<Integer> selectIdData(String cmd);

    void insertData(String cmd);

    void deleteData(String cmd);

    void dropData(String cmd);
}
