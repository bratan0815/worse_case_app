package com.example.prot_1.model.data;

import java.sql.Timestamp;
import java.util.ArrayList;

public class MessageListDummy {
    private ArrayList<MessageData> messageList;
    private MessageDataList msgDataList;

    public MessageListDummy(){
        messageList = new ArrayList<>();
        setDummyData();
        msgDataList = new MessageDataList(messageList);
    }


    public ArrayList<MessageData> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<MessageData> messageList) {
        this.messageList = messageList;
    }



    private void setDummyData(){
        int iconNumber = 0;
        Timestamp tStamp = new Timestamp(System.currentTimeMillis());
        MessageHeader messageHeader = new MessageHeader("Forge and more Forge", "", tStamp, tStamp);
        String des = "how to forge items";
        String txt = "Step 1: Find a Blacksmith!" + "\n" + "Step 2: Start With a Blank. " + "\n" + "Step 3 Heat it. " + "\n" + "Step 4: Squish It! " +
                "\n" + "Step 5: Squish It MORE! " + "\n" + "Step 6: Hammer time! " + "\n" + "Step 7: Final Product!";
        MessageData msgData = new MessageData(messageHeader, des, txt, 2);

        MessageHeader messageHeader1 = new MessageHeader("How to not get zombie", "", tStamp, tStamp);
        String des1 = "survival guide for spreewald";
        String txt1 = "Bereits aus der Frühgeschichte gibt es Hinweise darauf, dass die Menschen glaubten und fürchteten, die Toten könnten zurückkehren und möglicherweise den Lebenden Leid antun. So wurden in verschiedenen Kulturen Gräber vorgefunden, in denen die Leichen Verstorbener gefesselt oder von Holzpfählen durchbohrt waren. In Sierra Leone tritt diese Methode noch vereinzelt auf. Unklar ist allerdings, ob dies allein dem Zweck diente, die Rückkehr von Verstorbenen zu verhindern, oder ob es eine besondere Form der Hinrichtung für Verbrecher gewesen ist.\n" +
                "\n" +
                "Noch bis ins 18. Jahrhundert herrschte auch unter der mitteleuropäischen Bevölkerung große Angst vor der Wiederkehr Verstorbener.[6] So war es eine Aufgabe der Totenwache, einen vermeintlichen Verstorbenen zu erschlagen, falls er sich etwa vom Totenbett erheben sollte. Dies konnte durchaus vorkommen, da Methoden, um den Tod festzustellen, unzuverlässiger waren als heute.[7]\n" +
                "\n" +
                "Die unheimliche Figur sowie ihr Name Zombie zogen in die Kulturgeschichte der Vereinigten Staaten ein, während Haiti von 1915 bis 1934 unter US-amerikanischer Besatzung stand. Der aus dem Kreolischen (zonbi = Gespenst, Totengeist) herrührende Begriff Zombie wurde in den 1920er Jahren durch das Buch The Magic Island des Abenteuerschriftstellers W. B. Seabrook[8] sowie die dadurch inspirierten US-amerikanischen Kinofilme und Comics populär, als das Phänomen und der ihm zugrunde liegende Scheintod noch nicht ins Bewusstsein der Allgemeinheit eingedrungen waren.[9]";
        MessageData msgData1 = new MessageData(messageHeader1, des1, txt1, 1);

        MessageHeader messageHeader2 = new MessageHeader("Tales of a Frog", "", tStamp, tStamp);
        String des2 = "tale of a giant shy frog";
        String txt2 = "In the tale, a spoiled princess reluctantly befriends the Frog Prince, whom she met after dropping a gold ball into a pond, and he retrieves it for her in exchange for her friendship. The Frog Prince magically transforms into a handsome prince. In the original Grimm version of the story, the frog's spell was broken when the princess threw it against the wall, while in modern versions the transformation is triggered by the princess kissing the frog.[5]\\n\" +\n" +
                "            \"\\n\" +\n" +
                "            \"In other early versions, it was sufficient for the frog to spend the night on the princess' pillow.[6]\\n\" +\n" +
                "            \"\\n\" +\n" +
                "            \"The frog prince also has a loyal servant named Henry (or Harry) who had three iron bands affixed around his heart to prevent it from breaking in his sadness over his master's curse. When the frog prince transforms into his human form Henry's overwhelming happiness causes all three bands to break, freeing his heart from its bonds.[7]";
        MessageData msgData2 = new MessageData(messageHeader2, des2, txt2, 0);
        messageList.add(msgData);
        messageList.add(msgData1);
        messageList.add(msgData2);

    }

    public MessageDataList getMsgDataList() {
        return msgDataList;
    }

    public void setMsgDataList(MessageDataList msgDataList) {
        this.msgDataList = msgDataList;
    }
}
