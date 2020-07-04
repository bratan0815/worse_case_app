package com.example.prot_1.control;

import android.util.Log;

import com.example.prot_1.model.data.MessageData;
import com.example.prot_1.model.data.MessageDataList;
import com.example.prot_1.model.data.MessageHeader;
import com.example.prot_1.model.data.MessageListDummy;
import com.example.prot_1.model.db.message.DBControllerMessageInterface;
import com.example.prot_1.model.db.message.DBControllerMessage;

import java.sql.Timestamp;

/**
 * takes requests from MessageFragment (Komponent) - get, save, delete Messages
 * most important is the vieMessageList obj which holds the Messages for the
 * recyclerView and the ViewMessageActivity.
 * MessageManager is the only object which can send requests to the db
 * with the dbController obj.
 */

public class MessageManager {

    private static final String TAG = "MessageManager";
    private static MessageManager instance;

    private static MessageDataList viewMessageList;                 //list of Messages which is used for the recyclerView
    private static MessageDataList sendMessageList;                 //networkmanager would request these list for sending messages
    private static MessageDataList receivedMessageList;             //networkmanager would save received messages here
    private static DBControllerMessageInterface dbController;


    private MessageManager(DBControllerMessage dbControllerI){
        viewMessageList = new MessageDataList();
        sendMessageList = new MessageDataList();
        receivedMessageList = new MessageDataList();
        dbController = dbControllerI;
        getDataFromDB();
    }

    /**
     * creates the MessageManager instance if needed
     * and returns the instance
     *
     * @return only instance of the MessageManager
     */
    public static MessageManager getInstance(){
        if(instance == null){
            instance = new MessageManager(new DBControllerMessage());
        }
        return instance;
    }

    public static MessageManager getInstance(DBControllerMessage dbControllerI){
        if(instance == null){
            instance = new MessageManager(dbControllerI);
        }
        return instance;
    }

    /**
     * gives the possibility to change Data bevore view, or restrict data
     *
     * @return MessageDataList obj for recyclerView and ViewActivity
     */
    public MessageDataList getData(){
        if(viewMessageList.getMessageListCount() == 0){
            greetDummy();
            //setMoreDummyData();
            //spamSomeDummys();
        }
        return viewMessageList;
    }

    /**
     * refresh the data in the lists
     * work in progress
     */
    public void updateData(){
        getDataFromDB();
    }

    /**
     * gets all Message Elements of the db
     * and adds them to viewMessageList
     */
    private void getDataFromDB(){
        try {
            viewMessageList.addElementList(dbController.getData());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void writeData(){
        //write currentMessageList
        //dbController.writeData(saveInDBMessageList);
    }

    public static void writeMessageToDB(MessageData msg){
        dbController.writeData(msg);
    }

    public static void addToSendList(MessageData msg){
        sendMessageList.addElement(msg);
    }

    public static void addToSendList(MessageDataList msgList){
        sendMessageList.addElementList(msgList.getListOfElements());
    }

    public static void deleteData(MessageData messageData){
        Log.i(TAG, "deleteData: called");
        deleteInLists(messageData, viewMessageList);
        deleteInLists(messageData, sendMessageList);
        deleteInLists(messageData, receivedMessageList);
    }

    public static void deleteFromDB(MessageData messageData){
        dbController.deleteData(messageData);
    }

    private static void deleteInLists(MessageData msg, MessageDataList msgList){
        if(msgList.contains(msg)){
            msgList.removeElement(msg);
        }
    }

    public static MessageDataList getViewMessageList() {
        return viewMessageList;
    }

    public static MessageDataList getSendMessageList() {
        return sendMessageList;
    }

    public static MessageDataList getReceivedMessageList() {
        return receivedMessageList;
    }





    private void greetDummy(){
        if(viewMessageList.getMessageListCount() == 0) {
            Timestamp tStamp = new Timestamp(System.currentTimeMillis());
            MessageHeader messageHeader = new MessageHeader("Hello Desperate One", "Apocalypse", tStamp, tStamp);
            String des = "Best Wishes and suggestion";
            String txt = "Step 1: Survive!" + "\n" + "Step 2: Repeat Step one!";
            MessageData msgData = new MessageData(messageHeader, des, txt, 0);
            viewMessageList.addElement(msgData);
        }
    }

    private void spamSomeDummys(){
        int counter = 15;
        Timestamp tStamp = new Timestamp(System.currentTimeMillis());
        for(int i = 0; i < counter; i++){
            MessageHeader messageHeader = new MessageHeader("Hello Desperate One number " + i, "Apocalypse", tStamp, tStamp);
            String des = "Best Wishes and suggestion";
            String txt = "Step 1: Survive!" + "\n" + "Step 2: Repeat Step one!";
            MessageData msgData = new MessageData(messageHeader, des, txt, 0);
            viewMessageList.addElement(msgData);
        }
    }

    private void setMoreDummyData(){
        MessageListDummy dummyMsg = new MessageListDummy();
        viewMessageList.addElementList(dummyMsg.getMessageList());
    }
}
