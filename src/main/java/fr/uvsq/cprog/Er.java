package fr.uvsq.cprog;

import java.io.Serializable;

/**
 * La Class Er représente le format de sérialisation,
 * et contient des méthodes qui gére les annotations
 * des éléments d'un répertoire.
 * 
 */
public class Er implements Serializable {
    /**
     * Les attributs.
     *  
     */
    private int ner;
    private String er;
    private String note;
    private static final long serialVersionUID = 6529685098267757690L;

    /** 
     * Constructeur permet d'initialiser l'objet Er.
     * 
     */
    public Er(int ner, String er) {
        this.ner = ner;
        this.er = er;
        this.note = "";
    }

    /**
     * Cette méthode retourne le Ner de l'objet.
     * 
     */
    public int getNer() {
        return this.ner;
    }

    /**
     * Cette méthode retourne le nom de l'objet.
     * 
     */
    public String getEr() {
        return this.er;
    }

    /**
     * Cette méthode retourne la note de l'objet.
     * 
     */
    public String getNote() {
        return this.note;
    }

    /**
     * Cette méthode permet d'ajouter une note au note existant.
     * 
     */
    public void addNote(String notation) {
        this.note = this.note + notation;
    }

    /**
     * Cette méthode permet suppprimer le note de l'objet.
     * 
     */
    public void deleteNote() {
        this.note = "";
    }

    /**
     * Méthode toString.
     * 
     */
    @Override
    public String toString() {
        return String.format("{ner: %d, er: '%s', note: '%s'}", this.ner, this.er, this.note);
    }
}
