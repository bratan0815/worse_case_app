package com.example.prot_1.model.db.message;

import com.example.prot_1.model.data.MessageData;
import com.example.prot_1.model.data.MessageDataList;
import com.example.prot_1.model.data.MessageListDummy;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

class DBControllerMessageTest {
    //DBControllerMessage dbController;
    //DBMessageInterface dbI = Mockito.mock(DBMessage.class);
    MessageListDummy msgListDataDummy;



    @Test
    void getData() {
        DBMessageInterface dbI = Mockito.mock(DBMessage.class);

        DBControllerMessage dbController = new DBControllerMessage(dbI);
        MessageDataList msgList;
        try {
            when(dbI.selectMessageData("SELECT * FROM messages")).thenReturn(msgListDataDummy.getMsgDataList());
            msgList = dbController.getData();
            assertTrue(msgList.equals(msgListDataDummy.getMsgDataList()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void writeData() {
        DBMessageInterface dbI = Mockito.mock(DBMessage.class);

        DBControllerMessage dbController = new DBControllerMessage(dbI);
        MessageData msg = msgListDataDummy.getMessageList().get(0);
        MessageData msgFalse = msgListDataDummy.getMessageList().get(1);
        try{
            doAnswer(new Answer() {
                @Override
                public Object answer(InvocationOnMock invocation) throws Throwable {
                    assertTrue(true);
                    return null;
                }
            }).when(dbController).writeData(msg);
            dbController.writeData(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    void testWriteData() {
    }

    @Test
    void deleteData() {
        DBMessageInterface dbI = Mockito.mock(DBMessage.class);

        DBControllerMessage dbController = new DBControllerMessage(dbI);
        MessageData msg = msgListDataDummy.getMessageList().get(0);
        MessageData msgFalse = msgListDataDummy.getMessageList().get(1);
        try{
            doAnswer(new Answer() {
                @Override
                public Object answer(InvocationOnMock invocation) throws Throwable {
                    assertTrue(true);
                    return null;
                }
            }).when(dbController).deleteData(msg);
            dbController.deleteData(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteData() {
    }
}