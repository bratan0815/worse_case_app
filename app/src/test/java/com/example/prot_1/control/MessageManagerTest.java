package com.example.prot_1.control;

import com.example.prot_1.model.data.MessageData;
import com.example.prot_1.model.data.MessageListDummy;
import com.example.prot_1.model.db.message.DBControllerMessage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


public class MessageManagerTest {

    private static MessageListDummy msgListDataDummy;

    @Before
    public void instantiateDBCOntroller(){
        msgListDataDummy = new MessageListDummy();
    }



    @Test
    public void getInstance() {
        DBControllerMessage dbController = Mockito.mock(DBControllerMessage.class);
        assertTrue(MessageManager.getInstance(dbController) != null);

    }

    @Test
    public void getViewMessageList() {
        DBControllerMessage dbController = Mockito.mock(DBControllerMessage.class);
        MessageManager msgManager = MessageManager.getInstance(dbController);
        assertTrue(MessageManager.getViewMessageList() != null);
    }

    @Test
    public void getSendMessageList() {
        DBControllerMessage dbController = Mockito.mock(DBControllerMessage.class);
        MessageManager msgManager = MessageManager.getInstance(dbController);
        assertTrue(MessageManager.getSendMessageList() != null);

    }

    @Test
    public void getReceivedMessageList() {
        DBControllerMessage dbController = Mockito.mock(DBControllerMessage.class);
        MessageManager msgManager = MessageManager.getInstance(dbController);
        assertTrue(MessageManager.getReceivedMessageList() != null);

    }
    @Test
    public void writeMessageToDB(){
        DBControllerMessage dbController = Mockito.mock(DBControllerMessage.class);
        MessageManager msgManager = MessageManager.getInstance(dbController);
        MessageData msg = msgListDataDummy.getMessageList().get(0);
        doNothing().when(dbController).writeData(msg); assertTrue(true);

    }

    @Test
    public void deleteFromDB(){
        DBControllerMessage dbController = Mockito.mock(DBControllerMessage.class);
        MessageManager msgManager = MessageManager.getInstance(dbController);
        MessageData msg = msgListDataDummy.getMessageList().get(0);
        doNothing().when(dbController).deleteData(msg); assertTrue(true);
        MessageManager.deleteFromDB(msg);
    }

    @Test
    public void getDataFromDB(){
        DBControllerMessage dbController = Mockito.mock(DBControllerMessage.class);
        MessageManager msgManager = MessageManager.getInstance(dbController);
        assertTrue(MessageManager.getViewMessageList().getMessageListCount() == 0);
        when(dbController.getData()).thenReturn(msgListDataDummy.getMsgDataList());
        msgManager.updateData();
        assertTrue(MessageManager.getViewMessageList().getMessageListCount() > 0);
    }

}