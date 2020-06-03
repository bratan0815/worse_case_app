package com.example.prot_1.model.db.message;

import android.util.Log;

import com.example.prot_1.model.data.MessageData;
import com.example.prot_1.model.data.MessageDataList;

public class DBControllerMessage implements DBControllerMessageInterface {
    private static final String TAG = "DBControllerMsg";

    DBMessageInterface db;
    public DBControllerMessage(){
        Log.i(TAG, "DBControllerMsg: bevore new DB()");
        db = new DBMessage();
        if(db == null){
            Log.i(TAG, "DBControllerMsg: constructor db == null");
        } else {
            Log.i(TAG, "DBControllerMsg: db instance created");
        }
    }
/*
    public DBControllerMessage(DBMessage newDB){
        db = newDB;
    }
 */
    public DBControllerMessage(DBMessageInterface dbI){
        db = dbI;
    }

    @Override
    public MessageDataList getData(){
        String cmd = "";
        cmd += "SELECT * FROM messages";
        return db.selectMessageData(cmd);
    }

    @Override
    public void writeData(MessageData msg){
        Log.i(TAG, "writeData: call");
        String cmd = "";
        cmd += "INSERT INTO messages (title, author, description, text, icon) VALUES ('" + msg.getHeader().getTitle() + "', '" + msg.getHeader().getAuthor() + "', '" + msg.getDescription() + "', '" + msg.getText() + "', " + msg.getIconNumber() + ")";
        try {
            db.insertData(cmd);
            Log.i(TAG, "writeData: success");
        } catch (Exception e){
            e.printStackTrace();
            Log.i(TAG, "writeData(msg): failed");

        }
    }

    @Override
    public void writeData(MessageDataList msgList){
        for(MessageData msg: msgList.getListOfElements()){
            String cmd = "";
            cmd += "INSERT INTO messages (title, author, description, text, icon) VALUES ('" + msg.getHeader().getTitle() + "', '" + msg.getHeader().getAuthor() + "', '" + msg.getDescription() + "', '" + msg.getText() + "', " + msg.getIconNumber() + ")";
            db.insertData(cmd);
        }
    }

    @Override
    public void deleteData(MessageData msg){
        Log.i(TAG, "deleteData: called");
        String cmd = "";
        cmd += "DELETE FROM messages WHERE title = '" + msg.getHeader().getTitle() + "' AND author = '" + msg.getHeader().getAuthor() + "'";
        db.deleteData(cmd);
    }
    @Override
    public void deleteData(MessageDataList msgList){

    }
}
