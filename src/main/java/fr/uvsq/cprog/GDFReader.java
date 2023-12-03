package fr.uvsq.cprog;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.json.*;


public class GDFReader {
    private File currentFolder;
    private File notes;
    private int ner = 0;
    

    public GDFReader()
    {
        this.currentFolder = new File("C:\\Users\\HP\\Home");
        this.notes = new File(this.currentFolder.getAbsolutePath() + "\\notes.json");
        this.upgradeNote();
    }

    public GDFReader(String path)
    {
        this.currentFolder = new File(path);
        this.notes = new File(this.currentFolder.getAbsolutePath() + "\\notes.json");
        this.upgradeNote();
    }

    // On monte les fichiers déjà existant dans le fichier Notes.json avec l'association du NER pour chaque ER
    private void upgradeNote()
    {
        String[] contenus = currentFolder.list();

        JSONArray notesJson = new JSONArray();
        for(String contenu : contenus){
            JSONObject item = new JSONObject();
            ner += 1;
            item.put("NER", ner);
            item.put("ER", contenu);
            notesJson.put(item);
        }

        try {
            FileWriter myWriter = new FileWriter(this.notes);
            String json = notesJson.toString();
            myWriter.write(json);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String mkdir_method(String name)
    {
        File theDir = new File(currentFolder.getAbsolutePath()+"\\"+name);
        if (!theDir.exists()){
            theDir.mkdirs();
        }else{
            return "Ce dossier existe deja !\n";
        }
        try{
            String contenuNotes = new String(Files.readAllBytes(Paths.get(this.notes.getAbsolutePath())));
            JSONArray jsonArray = new JSONArray(contenuNotes);
            ner += 1;
            JSONObject item = new JSONObject();
            item.put("NER", ner);
            item.put("ER", theDir.getName());
            jsonArray.put(item);

            FileWriter myWriter = new FileWriter(this.notes);
            String jsonString = jsonArray.toString();
            myWriter.write(jsonString);
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return ls_method();

    }
    
    public String touch_method(String name)
    {
        File theFile = new File(currentFolder.getAbsolutePath()+"\\"+name);
        try {
            if (!theFile.exists()){
                theFile.createNewFile();
            }else{
                return "Ce fichier existe deja !\n";
            }

            String contenuNotes = new String(Files.readAllBytes(Paths.get(this.notes.getAbsolutePath())));
            JSONArray jsonArray = new JSONArray(contenuNotes);
            ner += 1;
            JSONObject item = new JSONObject();
            item.put("NER", ner);
            item.put("ER", theFile.getName());
            jsonArray.put(item);

            FileWriter myWriter = new FileWriter(this.notes);
            String jsonString = jsonArray.toString();
            myWriter.write(jsonString);
            myWriter.close();


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return ls_method();
    }

    public String ls_method() {
        
        String result="Ner Name\n"
        + "--- ----\n";
        String contenuNotes;

        try{
            contenuNotes = new String(Files.readAllBytes(Paths.get(this.notes.getAbsolutePath())));
            JSONArray jsonArray = new JSONArray(contenuNotes);

            for(int i=0; i < jsonArray.length(); i++){
                JSONObject item = jsonArray.getJSONObject(i);
                result += item.get("NER") + " " + item.get("ER") +"\n";
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return result;
    }

    
    
}
