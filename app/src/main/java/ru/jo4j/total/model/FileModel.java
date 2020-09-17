package ru.jo4j.total.model;

public class FileModel {

    private String absolutePath;
    private String name;
    private Boolean directory;

    public FileModel(String absolutePath, String name, Boolean isDirectory) {
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
}
