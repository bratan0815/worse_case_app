package com.example.prot_1.model.db.message;

import com.example.prot_1.model.data.MessageDataList;

/**
 * takes sql strings and sends them directly to the db.
 * returns MessageDataList objects if requested.
 */
interface DBMessageInterface {
    /**
     * cmd String would be taken and without changing send to the db with an rawQuery.
     * cmd requests a set of Messages, which would be returnet as MessageData objs
     * in an MessageDataList obj.
     *
     * @param cmd full sql command string
     * @return MessageDataList obj with requested Messages
     */
    MessageDataList selectMessageData(String cmd);

    /**
     * cmd String would be taken and without changing send to the db.
     * inserts what ever is writen in the string.
     *
     * @param cmd full sql command string
     */
    void insertData(String cmd);

    void dropData(String cmd);

    /**
     * cmd String would be taken and without changing send to the db.
     * deletes what ever obj is writen in the string.
     *
     * @param cmd full sql command string
     */
    void deleteData(String cmd);
}
