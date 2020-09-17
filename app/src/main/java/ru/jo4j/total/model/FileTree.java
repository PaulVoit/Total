package ru.jo4j.total.model;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTree implements ITree {

    private File rootDirectory;

    public FileTree() {
        rootDirectory = Environment.getExternalStorageDirectory();
    }

    @Override
    public FileModel getRootDir() {
        return new FileModel(rootDirectory.getAbsolutePath(), rootDirectory.getName(), rootDirectory.isDirectory());
    }

    @Override
    public FileModel getParentByChild(String childAbsolutePath) {
        File child = new File(childAbsolutePath);
        File parent = child.getParentFile();
        if (parent != null) {
            return new FileModel(parent.getAbsolutePath(), parent.getName(), parent.isDirectory());
        } else {
            return null;
        }
    }

    @Override
    public List<FileModel> getChildrenByParent(String absolutePath) {
        File parent = new File(absolutePath);
        if (!parent.isDirectory()) {
            return null;
        }
        File[] children = new File(absolutePath).listFiles();
        List<FileModel> childrenList = new ArrayList<>();
        for (File child :
                children) {
            childrenList.add(new FileModel(child.getAbsolutePath(), child.getName(), child.isDirectory()));
        }
        return childrenList;
    }
}
