package com.example.prot_1.model.data;

public class MessageData {

    private MessageHeader header;
    private String description;
    private String text;
    //0 for message, 1 for news, 2 for guides
    private int iconNumber;

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    private int idNumber;

    public MessageData(MessageHeader header, String description, String text, int iconNumber) {
        this.header = header;
        this.description = description;
        this.text = text;
        this.iconNumber = iconNumber;
    }

    public MessageHeader getHeader() {
        return header;
    }

    public void setHeader(MessageHeader header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIconNumber() {
        return iconNumber;
    }

    public void setIconNumber(int iconNumber) {
        this.iconNumber = iconNumber;
    }
}
