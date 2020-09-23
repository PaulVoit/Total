package ru.jo4j.total.model;

import android.content.Context;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

public class FileModel {

    private File mFile;
    private String absolutePath;
    private String name;
    private Boolean directory;
    private Date date;

    public FileModel(File file, Date date, String absolutePath, String name, Boolean isDirectory) {
        mFile = file;
        this.date = date;
        this.absolutePath = absolutePath;
        this.name = name;
        this.directory = isDirectory;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDirectory() {
        return directory;
    }

    public void setDirectory(Boolean directory) {
        this.directory = directory;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public File getFile() {
        return mFile;
    }

    public String getFormattedModificationDate(Context c) {
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(c);
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(c);
        Date date = new Date(mFile.lastModified());
        return dateFormat.format(date) + " " + timeFormat.format(date);
    }
}
