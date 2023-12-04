package fr.uvsq.cprog;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


// import org.json.*;
// import org.json.JSONArray;


public class GDFReader {
    private File currentFolder;
    private File notes;
    

    public GDFReader()
    {
        this.currentFolder = new File("C:\\Users\\HP\\Home");
        this.notes = new File(this.currentFolder.getAbsolutePath() + "\\notes.ser");
        this.serializedNote();
    }

    public GDFReader(String path)
    {
        this.currentFolder = new File(path);
        this.notes = new File(this.currentFolder.getAbsolutePath() + "\\notes.ser");
        this.serializedNote();
    }

    public String getCurrentFolderPath()
    {
        return this.currentFolder.getAbsolutePath();
    }

    // On monte les fichiers déjà existant dans le fichier notes.ser avec l'association du NER pour chaque ER
    public void serializedNote()
    {
        String[] contenus = this.currentFolder.list();
        ArrayList<ER> elements = new ArrayList<ER>();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.notes))) {
            for(String contenu : contenus){
                ER er = new ER(contenu);
                elements.add(er);
                
            }

            oos.writeObject(elements);
            oos.close();
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String mkdir_method(String name)
    {
        File theDir = new File(this.currentFolder.getAbsolutePath()+"\\"+name);
        if (!theDir.exists()){
            theDir.mkdirs();
            this.serializedNote();
            return "Dossier crée avec succès\n";
        }else{
            return "Ce dossier existe deja !\n";
        }

    }
    
    public String touch_method(String name)
    {
        File theFile = new File(this.currentFolder.getAbsolutePath()+"\\"+name);
        try {
            if (!theFile.exists()){
                theFile.createNewFile();
                this.serializedNote();
                
            }else{
                return "Ce fichier existe deja !\n";
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return "Fichier crée avec succès\n";

    }

    public String ls_method() {
        
        String result="Ner Name\n"
        + "--- ----\n";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.notes))) {
            ArrayList<ER> ers = (ArrayList<ER>) ois.readObject();

            for(int i=0; i < ers.size(); i++){
                result += ers.get(i).getNer() + " " + ers.get(i).getEr() +"\n";
            }
            
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return result;
    }

    
    
}
