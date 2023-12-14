package fr.uvsq.cprog;

import java.io.*;
import java.util.*;

import com.google.common.io.*;
public class REP {
    private File currentFolder;
    private File notes;
    

    REP (String path)
    {
        this.currentFolder = new File(path);
        this.notes = new File(path + "\\notes.ser");
        try {
            if (!this.notes.exists()){
                this.notes.createNewFile();
                this.serializedNote();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
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

    public void serializedNote(ArrayList<ER> ers)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.notes))) {
            oos.writeObject(ers);
            oos.close();
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<ER> deserializedNote()
    {
        ArrayList<ER> ers = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.notes))) {
            ers = (ArrayList<ER>) ois.readObject();
            ois.close();
        } catch(ClassNotFoundException e){
            serializedNote();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return ers;
    }

    public String mkdir_method(String name)
    {
        File theDir = new File(this.currentFolder.getAbsolutePath()+"\\"+name);
        if (!theDir.exists()){
            theDir.mkdirs();
            ArrayList<ER> ers = this.deserializedNote();
            ER er = new ER(name);
            ers.add(er);
            this.serializedNote(ers);
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
                ArrayList<ER> ers = this.deserializedNote();
                ER er = new ER(name);
                ers.add(er);
                this.serializedNote(ers);  
            }else{
                return "Ce fichier existe deja !\n";
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return "Fichier crée avec succès\n";

    }

    public String ls_method() 
    {
        String result="Ner Name\n"
        + "--- ----\n";

        ArrayList<ER> ers = this.deserializedNote();

        for(int i=0; i < ers.size(); i++){
            result += ers.get(i).getNer() + " " + ers.get(i).getEr() + " " + ers.get(i).getNote() +"\n";
        }
            
        return result;
    }

    public boolean ajouterNote(int ner, String note)
    {
        ArrayList<ER> ers = deserializedNote();
        for(int i=0; i < ers.size(); i++){
            if(ers.get(i).getNer() == ner){
                ers.get(i).addNote(note);

                this.serializedNote(ers);
                return true;
            }
        }
        return false;
    }

    public boolean retireNote(int ner)
    {
        ArrayList<ER> ers = deserializedNote();
        for(int i=0; i < ers.size(); i++){
            if(ers.get(i).getNer() == ner){
                ers.get(i).deleteNote();
                this.serializedNote(ers);
                return true;
            }
        }
        return false;
    }

    public REP remonter()
    {
        String path = this.currentFolder.getParent();
        REP gdfParent = new REP(path);
        return gdfParent;
    }

    public REP descendre(int ner)
    {
        ArrayList<ER> ers = deserializedNote();
        REP gdfChild = null;
        for(int i=0; i < ers.size() ; i++){
            if(ers.get(i).getNer() == ner){
                File theDir = new File(this.getCurrentFolderPath() + "\\" + ers.get(i).getEr());
                if(theDir.isDirectory()){
                    String path = this.getCurrentFolderPath() + "\\" + ers.get(i).getEr();
                    gdfChild = new REP(path);
                }
                break;
            }
        }
        return gdfChild;
    }

    public String visu(int ner)
    {
        String result = null;
        ArrayList<ER> ers = deserializedNote();
        for(int i=0; i < ers.size() ; i++){
            if(ers.get(i).getNer() == ner){
                File theFile = new File(this.getCurrentFolderPath() + "\\" + ers.get(i).getEr());
                if(!theFile.isDirectory()){

                    String ext = ers.get(i).getEr().split("\\.")[1];
                    if(ext.equals("txt")){
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
                    }else{
                        result = "Length : "+ Long.toString(theFile.length()) + "\n";
                    }
                    
                }
                
            }
        }

        return result;
    }

    public File search(File directory, String fileName)
    {
        File myfile = null;

        // get all the files from a directory
        File[] fList = directory.listFiles();
        ArrayList<File> files = new  ArrayList<File>();
        if (fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    myfile =this.search(file, fileName);
                }
            }
        }

        for (File file : files) {
            if(file.getName().equals(fileName)) {
                myfile = file;
                break;
            }
        }

        return myfile;
    }

    public String find(String name)
    {
        String result= "";
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

    public File copy(int ner)
    {
        File file = null;
        ArrayList<ER> listElement = this.deserializedNote();
        for (int i = 0; i<listElement.size(); i++){
            if(listElement.get(i).getNer() == ner) {
                file = new File(listElement.get(i).getEr());
                return file;   
            }
            break;
        }

        return file;

    }
    
    
}
