package fr.uvsq.cprog;

import java.io.Serializable;

public class ER implements Serializable {
    private int ner;
    private String er;
    private static int i=0;

    public ER(String er)
    {
        i += 1;
        this.ner = i;
        this.er = er;
    }

    public int getNer() {
        return ner;
    }

    public String getEr() {
        return er;
    }

    @Override
    public String toString() {
        return String.format("{ner: %d, er: '%s'}", ner, er);
    }
    
}
