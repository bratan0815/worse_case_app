package com.example.prot_1.model.db.message;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.prot_1.model.data.MessageData;
import com.example.prot_1.model.data.MessageDataList;
import com.example.prot_1.model.data.MessageHeader;

import java.sql.Timestamp;

public class DBMessage implements DBMessageInterface {

    private static final String TAG = "DBMessage";
   // SQLiteDatabase myDatabase;
    //SQLiteDatabase.CursorFactory factory;
    SQLiteDatabase myDB;


    public DBMessage(){
        //myDB = MainActivity.myDatabase;
        myDB = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.prot_1/databases/Message.db", null);
        Log.i(TAG, "onCreate: DB openorcreate success");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS messages (title VARCHAR, author VARCHAR, description VARCHAR, text VARCHAR, icon INT(1))");
        Log.i(TAG, "onCreate: DB exists an has table");
    }

    public DBMessage(SQLiteDatabase myMocDB){
        myDB = myMocDB;
    }





    @Override
    public MessageDataList selectMessageData(String cmd){
        MessageDataList msgList = new MessageDataList();

        try {
            Cursor cursor = myDB.rawQuery(cmd, null);
            int titleIndex = cursor.getColumnIndex("title");
            int authorIndex = cursor.getColumnIndex("author");
            int descriptionIndex = cursor.getColumnIndex("description");
            int textIndex = cursor.getColumnIndex("text");
            int iconIndex = cursor.getColumnIndex("icon");

            if(cursor.moveToFirst()){
                do {
                    Timestamp tmstmp = new Timestamp(System.currentTimeMillis());
                    MessageHeader msgHeader = new MessageHeader(cursor.getString(titleIndex), cursor.getString(authorIndex), tmstmp, tmstmp);
                    MessageData msg = new MessageData(msgHeader, cursor.getString(descriptionIndex), cursor.getString(textIndex), cursor.getInt(iconIndex));
                    msgList.addElement(msg);
                    cursor.moveToNext();
                } while (cursor.moveToNext());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return msgList;
    }

    @Override
    public void insertData(String cmd){
        Log.i(TAG, "insertData: call");
        myDB.execSQL(cmd);
    }

    @Override
    public void dropData(String cmd){
        Log.i(TAG, "dropData: called");
        myDB.execSQL(cmd);

    }

    public void deleteData(String cmd){
        Log.i(TAG, "deleteData: called");
        myDB.execSQL(cmd);
    }

}
