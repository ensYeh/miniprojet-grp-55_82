package fr.uvsq.cprog;

<<<<<<< Updated upstream
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
=======
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
>>>>>>> Stashed changes

/**
 * La Class Rep représente le répertoire courant
 * et contient des méthodes qui permet de faires des traitements
 * précise sur un répertoire, par exemple créer des fichier.
 *
 */
public class Rep {
    /**
     * Les attributs.
     */
    private File currentFolder;
    private File notes;

    /**
     * Constructeur.
     */
    Rep(String path) {
        this.currentFolder = new File(path);
        this.notes = new File(path + "\\notes.ser");
        try {
            if (!this.notes.exists()) {
                this.notes.createNewFile();
                this.serializedNote();
            } else if (this.notes.exists()) {
                this.updateSerializedNote();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Cette méthode retourner le chemin du répertoire.
     * 
     */
    public String getCurrentFolderPath() {
        return this.currentFolder.getAbsolutePath();
    }

    /**
     * Cette méthode permet de serialiser 
     * les éléments déjà existant dans le répertoire.
     * 
     */
    public void serializedNote() {
        String[] contenus = this.currentFolder.list();
        ArrayList<Er> elements = new ArrayList<Er>();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.notes))) {
            int ner = 0;
            for (String contenu : contenus) {
                ner += 1;
                Er er = new Er(ner, contenu);
                elements.add(er);
            }

            oos.writeObject(elements);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Cette méthode permet de permet de sérialiser 
     * une liste des objet Er données.
     * 
     */
    public void serializedNote(ArrayList<Er> ers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.notes))) {
            oos.writeObject(ers);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Cette méthode permet de mettre à jour le fichier sérialiser.
     */
    public void updateSerializedNote() {
        String[] contenus = this.currentFolder.list();
        try {
            if (!this.notes.exists()) {
                this.notes.createNewFile();
                this.serializedNote();
            } else if (contenus.length > this.deserializedNote().size()) {
                ArrayList<Er> ers = this.deserializedNote();
                for (String er : contenus) {
                    boolean contains = false;
                    for (Er element : ers) {
                        if (er.equals(element.getEr())) {
                            contains = true;
                        }
                    }

                    if (!contains) {
                        Er addEr = new Er(ers.size() + 1, er);
                        ers.add(addEr);
                    } 
                }
                this.serializedNote(ers);
            } else if (contenus.length < this.deserializedNote().size()) {
                ArrayList<Er> ers = this.deserializedNote();
                ArrayList<Er> deleteEr = new ArrayList<Er>();
                for (Er element : ers) {
                    boolean contains = false;
                    for (String er : contenus) {
                        if (!er.equals(element.getEr())) {
                            contains = true;
                        }
                    }

                    if (contains) {
                        deleteEr.add(element);
                    } 
                }
                for (Er er : deleteEr) {
                    ers.remove(er);
                }
                this.serializedNote(ers);
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Cette méthode permet de desérialiser le fichie note
     * qui contient les objets sérialiser dans le fichier note.ser.
     * 
     */
    public ArrayList<Er> deserializedNote() {
        ArrayList<Er> ers = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.notes))) {
            ers = (ArrayList<Er>) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException e) {
            serializedNote();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return ers;
    }

    /**
     * Cette méthode permet de créer un répertoire dans le répertoire courant.
     * 
     */
    public String mkdir_method(String name) {
        File theDir = new File(this.currentFolder.getAbsolutePath() + "\\" + name);
        if (!theDir.exists()) {
            theDir.mkdirs();
            ArrayList<Er> ers = this.deserializedNote();
            Er er = new Er(ers.get(ers.size() - 1).getNer() + 1, name);
            ers.add(er);
            this.serializedNote(ers);
            return "Dossier crée avec succès\n";
        } else {
            return "Ce dossier existe deja !\n";
        }

    }

    /**
     * Cette méthode permet de créer un fichier dans le répertoire courant.
     * 
     */
    public String touch_method(String name) {
        File theFile = new File(this.currentFolder.getAbsolutePath() + "\\" + name);
        try {
            if (!theFile.exists()) {
                theFile.createNewFile();
                ArrayList<Er> ers = this.deserializedNote();
                Er er = new Er(ers.get(ers.size() - 1).getNer() + 1, name);
                ers.add(er);
                this.serializedNote(ers);
            } else {
                return "Ce fichier existe deja !\n";
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return "Fichier crée avec succès\n";

    }

    /**
     * En utilisant la méthode desérialisation, 
     * cette méthode permet de lister les éléments du répertoire courant.
     * 
     */
    public String ls_method() {
        this.updateSerializedNote();
        ArrayList<Er> ers = this.deserializedNote();

        String result = "Ner Name\n"
                + "--- ----\n";

        for (int i = 0; i < ers.size(); i++) {
            result += ers.get(i).getNer() + " " + ers.get(i).getEr() 
                + " " + ers.get(i).getNote() + "\n";
        }

        return result;
    }

    /**
     * Cette méthode permet d'ajouter ou concaténer une note à 
     * un élément donnée du répertoire courant.
     * 
     */
    public boolean ajouterNote(int ner, String note) {
        this.updateSerializedNote();
        ArrayList<Er> ers = deserializedNote();
        for (int i = 0; i < ers.size(); i++) {
            if (ers.get(i).getNer() == ner) {
                ers.get(i).addNote(note);

                this.serializedNote(ers);
                return true;
            }
        }
        return false;
    }

    /**
     * Cette méthode permet de retire le note d'un élément
     * donnée du répertoire courant.
     * 
     */
    public boolean retireNote(int ner) {
        this.updateSerializedNote();
        ArrayList<Er> ers = deserializedNote();
        for (int i = 0; i < ers.size(); i++) {
            if (ers.get(i).getNer() == ner) {
                ers.get(i).deleteNote();
                this.serializedNote(ers);
                return true;
            }
        }
        return false;
    }

    /**
     * Cette méthode permet de rémonter au répertoire parent du répertoire courant.
     * 
     */
    public Rep remonter() {
        String path = this.currentFolder.getParent();
        Rep gdfParent = new Rep(path);
        return gdfParent;
    }

    /**
     * Cette méthode permet de changer répertoire.
     * 
     */
    public Rep descendre(int ner) {
        this.updateSerializedNote();
        ArrayList<Er> ers = deserializedNote();
        Rep gdfChild = null;
        for (int i = 0; i < ers.size(); i++) {
            if (ers.get(i).getNer() == ner) {
                File theDir = new File(this.getCurrentFolderPath() + "\\" + ers.get(i).getEr());
                if (theDir.isDirectory()) {
                    String path = this.getCurrentFolderPath() + "\\" + ers.get(i).getEr();
                    gdfChild = new Rep(path);
                }
                break;
            }
        }
        return gdfChild;
    }

    /**
     * Cette méthode permet d'afficher le contenu d'un fichier.
     * 
     */
    public String visu(int ner) {
        String result = null;
        this.updateSerializedNote();
        ArrayList<Er> ers = deserializedNote();
        for (int i = 0; i < ers.size(); i++) {
            if (ers.get(i).getNer() == ner) {
                File theFile = new File(this.getCurrentFolderPath() + "\\" + ers.get(i).getEr());
                if (!theFile.isDirectory()) {

                    String ext = ers.get(i).getEr().split("\\.")[1];
                    if (ext.equals("txt")) {
                        try {
                            BufferedReader reader = new BufferedReader(new FileReader(theFile));
                            String line;
                            result = "";
                            while ((line = reader.readLine()) != null) {
                                result += line + "\n";
                            }

                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        result = "Length : " + Long.toString(theFile.length()) + "\n";
                    }

                }

            }
        }

        return result;
    }

    /**
     * Cette méthode permet de recherche un fichier dans répertoire 
     * et dans tous ces sous-répertoire.
     * 
     */
    public File search(File directory, String fileName) {
        File myfile = null;

        // get all the files from a directory
        File[] fileList = directory.listFiles();
        ArrayList<File> files = new ArrayList<File>();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    myfile = this.search(file, fileName);
                }
            }
        }

        for (File file : files) {
            if (file.getName().equals(fileName)) {
                myfile = file;
                break;
            }
        }

        return myfile;
    }

    /**
     * En utilisant la méthode search précedent,
     * si le fichier est trouvé alors cette méthode l'affiche.
     * 
     */
    public String find(String name) {
        String result = "";
        if (this.search(this.currentFolder, name) == null) {
            return "Fichier non trouvé !\n";
        }

        File file = this.search(this.currentFolder, name);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            result = "";
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Cette méthode permet de copie un fichier.
     * 
     */
    public Path copy(int ner) {
        ArrayList<Er> listElement = this.deserializedNote();
        for (int i = 0; i<listElement.size(); i++){
            if (listElement.get(i).getNer() == ner) {
                File copiedFile = new File(this.currentFolder.getAbsolutePath() + "\\" + listElement.get(i).getEr());
                return copiedFile.toPath();
            }
        }

        return null;
    }

    /**
     * Cette méthode permet de coller un fichier dans le répertoire courant.
     */
    public String past(Path copiedEr) {
        
        if (copiedEr != null) {
            try {
                Files.copy( copiedEr, 
                    (new File(this.currentFolder + "\\" + copiedEr.getFileName().toString())).toPath(), 
                    StandardCopyOption.REPLACE_EXISTING);
                return "Element collé.\n";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Aucun Element est copié.\n";

    }

    /**
     * Cette méthode permet de couper un fichier.
     * 
     */
    public Path cut(int ner) {
        if (this.copy(ner) != null) {
           Path copied = this.copy(ner);
           this.copy(ner).toFile().delete();
           return copied;
        }
<<<<<<< Updated upstream
        return null;
    }   
    
}
=======

    }  
   
}   

>>>>>>> Stashed changes
