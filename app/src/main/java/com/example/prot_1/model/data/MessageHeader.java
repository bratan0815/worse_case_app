package com.example.prot_1.model.data;

import java.sql.Timestamp;

public class MessageHeader {
    private String title;
    private String author;
    private Timestamp createdAt;
    private Timestamp receivedAt;

    public MessageHeader(String title, String author, Timestamp createdAt, Timestamp receivedAt) {
        this.title = title;
        this.author = author;
        this.createdAt = createdAt;
        this.receivedAt = receivedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Timestamp receivedAt) {
        this.receivedAt = receivedAt;
    }
}
