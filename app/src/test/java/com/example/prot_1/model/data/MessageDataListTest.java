package com.example.prot_1.model.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageDataListTest {

    private static MessageListDummy msgListDataDummy;
    private static MessageDataList msgList;

    @Before
    public void initialize(){
        msgListDataDummy = new MessageListDummy();
        msgList = new MessageDataList(msgListDataDummy.getMessageList());
    }

    @Test
    public void messageDataListConstructor(){
        MessageDataList msgTestList = new MessageDataList();
        assertTrue(msgTestList.getListOfElements() != null);
        assertEquals(msgTestList.getMessageListCount(), 0);
    }

    @Test
    public void checkIfEqual() {
        assertTrue(msgList.checkIfEqual(msgListDataDummy.getMessageList().get(0), msgListDataDummy.getMessageList().get(0)));
        assertFalse(msgList.checkIfEqual(msgListDataDummy.getMessageList().get(0), msgListDataDummy.getMessageList().get(1)));
    }

    @Test
    public void removeAlikeElement() {
        MessageData dataObj = msgListDataDummy.getMessageList().get(0);
        msgList.removeAlikeElement(dataObj);
        assertFalse(msgList.getListOfElements().contains(dataObj));
    }

    @Test
    public void checkIfAlreadyInList() {
        MessageData dataObj = msgListDataDummy.getMessageList().get(0);
        assertEquals(msgList.checkIfAlreadyInList(dataObj), true);
        msgList.removeElement(dataObj);
        assertEquals(msgList.checkIfAlreadyInList(dataObj), false);

    }
}