package com.example.prot_1.model.data;

import android.util.Log;

import java.sql.Timestamp;
import java.util.ArrayList;

public class MessageDataList {

    private static final String TAG = "MessagesDataListObj";
    private Timestamp changedAt;
    private ArrayList<MessageData> messageList;


    public MessageDataList() {
        this.changedAt = new Timestamp(System.currentTimeMillis());;
        messageList = new ArrayList<>();
    }

    public MessageDataList(ArrayList<MessageData> mDataList) {
        this.changedAt = new Timestamp(System.currentTimeMillis());;
        messageList = new ArrayList<>();
        messageList.addAll(mDataList);
    }

    public Timestamp getChangedAt() {
        return changedAt;
    }

    public int getMessageListCount(){
        return messageList.size();
    }

    public void addElement(MessageData newMessage){
        if(newMessage != null && !checkIfAlreadyInList(newMessage)) {
            messageList.add(newMessage);
            changedAt.setTime(System.currentTimeMillis());
        }
    }

    public void addElementList(ArrayList<MessageData> mDataList){
        for (MessageData msg: mDataList){
            addElement(msg);
        }
    }

    public void addElementList(MessageDataList dataList){
        ArrayList<MessageData> mDataList = dataList.getListOfElements();
        for (MessageData msg: mDataList){
            addElement(msg);
        }
    }

    public boolean contains(MessageData msg){
        if(messageList.contains(msg)){
            return true;
        }
        return false;
    }

    public MessageData getElementAt(int position){
        return messageList.get(position);
    }

    public ArrayList<MessageData> getListOfElements(){
        return messageList;
    }

    public void removeElement(MessageData msg){
        messageList.remove(msg);
        changedAt.setTime(System.currentTimeMillis());
    }

    public void removeElementAt(int position){
        messageList.remove(position);
    }

    public void clearMessageList(){
        messageList.clear();
        changedAt.setTime(System.currentTimeMillis());
    }

    public void removeAlikeElement(MessageData msg){
        //Log.i(TAG, "removeAlikeElement: called");
        for(MessageData messageInList: messageList){
            //Log.i(TAG, "removeAlikeElement: " + msg.getHeader().getTitle() + " == " + messageInList.getHeader().getTitle());
            if(checkIfEqual(msg, messageInList)){
                //Log.i(TAG, "removeAlikeElement: found element for removement, title: " + messageInList.getHeader().getTitle());
                messageList.remove(messageInList);
            }
        }
    }

    public boolean checkIfEqual(MessageData msg1, MessageData msg2){
        if(msg1.getHeader().getTitle().equals(msg2.getHeader().getTitle()) && msg1.getHeader().getAuthor().equals(msg2.getHeader().getAuthor())){
            return true;
        }
        return false;
    }

    public boolean checkIfAlreadyInList(MessageData msg){
        for(MessageData messageInList: messageList){
            if(checkIfEqual(msg, messageInList)){
                return true;
            }
        }
        return false;
    }

    public void logTitle(){
        for (MessageData msg: messageList) {
            Log.i(TAG, "logTitle: titel = " + msg.getHeader().getTitle());
        }
    }




}
