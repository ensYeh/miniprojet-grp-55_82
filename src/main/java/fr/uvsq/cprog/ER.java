package fr.uvsq.cprog;

import java.io.Serializable;

public class ER implements Serializable {
    private int ner;
    private String er;
    private String note;
    private static int I = 0;

    public ER (String er) {
        I += 1;
        this.ner = I;
        this.er = er;
        this.note = "";
    }

    public int getNer() {
        return this.ner;
    }

    public String getEr() {
        return this.er;
    }

    public String getNote() {
        return this.note;
    }

    public void addNote(String notation) {
        this.note = this.note + notation;
    }

    public void deleteNote() {
        this.note = "";
    }

    @Override
    public String toString() {
        return String.format("{ner: %d, er: '%s', note: '%s'}", this.ner, this.er, this.note);
    }  
}
