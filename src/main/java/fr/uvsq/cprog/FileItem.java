package fr.uvsq.cprog;

import java.io.File;

public class FileItem extends File{
    private int ner;

    public FileItem(String pathName, int ner) {
        super(pathName);
        this.ner = ner;
    }

    public int getNer() {
        return ner;
    }
}
