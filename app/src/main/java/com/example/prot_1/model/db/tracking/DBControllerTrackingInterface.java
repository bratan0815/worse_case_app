package com.example.prot_1.model.db.tracking;

import java.util.ArrayList;

public interface DBControllerTrackingInterface {
    /**
     * Send an suitable command to the db interface
     * for returning every of my saved ids.
     * returns the complete list of saved ids
     *
     * @return ArrayList<Integer> of my saved ids
     */
    ArrayList<Integer> getMyIds();

    /**
     * send an sql command string to the db interface
     * and get in return an arraylist of ids which where
     * encountered and saved.
     *
     * @return arraylist of encountered ids
     */
    ArrayList<Integer> getEncounteredIds();

    /**
     * send an delete command to the db interface
     * for deleting every of my ids which is
     * older than 29 days.
     */
    void deleteMyOldIds();

    /**
     * send an insert sql string to the db interface
     * for inserting one new id for me.
     *
     * @param id new id for me
     */
    void writeNewMyId(int id);

    /**
     * send sql string to db interface for
     * deleting every encountered id which is
     * older than 29days.
     */
    void deleteOldEncounteredIds();

    /**
     * sends commands to the db to save new encountered ids
     *
     * @param ids arraylist of encountered ids
     */
    void writeNewEncounteredIds(ArrayList<Integer> ids);

    void writeFriendsId(int id);

    void deleteOldFreindsId();
}
