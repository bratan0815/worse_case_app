package com.example.prot_1.model.db.message;

import com.example.prot_1.model.data.MessageData;
import com.example.prot_1.model.data.MessageDataList;

/**
 * takes request with or without objects and converts them to sql command strings
 * which where then send to the db interface.
 * returns requested objects
 */
public interface DBControllerMessageInterface {
    /**
     * Send an suitable command to the db interface
     * for returning every saved message.
     * returns the complete list of saved messages
     *
     * @return MessageDataList obj
     */
    MessageDataList getData();

    /**
     * Takes a single Message and converts it to an String with the
     * suitable commands for the sql db for inserting data.
     * Send these String to the db Interface
     *
     * @param msg single MessageData obj
     */
    void writeData(MessageData msg);

    void writeData(MessageDataList msgList);

    /**
     * Takes a single Message and converts it to an String with the
     * suitable commands for deleting every obj which is like the
     * MessageData obj in the db. Send these String to the
     * db Interface
     *
     * @param msg single MessageData obj
     */
    void deleteData(MessageData msg);

    void deleteData(MessageDataList msgList);
}
