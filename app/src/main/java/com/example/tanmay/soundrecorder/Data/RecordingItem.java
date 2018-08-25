package com.example.tanmay.soundrecorder.Data;

// Object to store data of one particular recording
public class RecordingItem {

    // Properties of RecordingItem
    private String name;/* Name of the file */
    private String filePath;/* Path of the file */
    private int ID;/* ID of the file in the database */
    private int length;/*  Length of the recording*/
    private long time;/* Time of recording */

    public RecordingItem(String name, String filePath, int ID, int length, long time) {
        this.name = name;
        this.filePath = filePath;
        this.ID = ID;
        this.length = length;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
