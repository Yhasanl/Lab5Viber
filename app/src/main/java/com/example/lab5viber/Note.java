package com.example.lab5viber;

public class Note {
    private long id;
    private String title;
    private String content;
    private String storageType;

    public Note(long id, String title, String content, String storageType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.storageType = storageType;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getStorageType() { return storageType; }
}