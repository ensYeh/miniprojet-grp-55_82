package fr.uvsq.cprog;

import java.io.Serializable;

public class ER implements Serializable {
    private int ner;
    private String er;
    private String note = "";
    private static int i=0;

    public ER(String er)
    {
        i += 1;
        this.ner = i;
        this.er = er;
    }

    public int getNer() {
        return this.ner;
    }

    public String getEr() {
        return this.er;
    }

    public String getNote(){
        return this.note;
    }

    public void setNote(String note){
        this.note = note;
    }

    @Override
    public String toString() {
        return String.format("{ner: %d, er: '%s', note: '%s'}", ner, er, note);
    }
    
}
