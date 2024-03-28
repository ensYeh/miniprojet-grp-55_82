package fr.uvsq.cprog;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AnnotationManager {
    private Map<String, String> annotations; // Map<FilePath, Annotation>

    public AnnotationManager() {
        this.annotations = new HashMap<>();
        loadAnnotationsFromFile(); // Charge les annotations à partir du fichier "notes" lors de la création de l'objet
    }

    public void addAnnotation(String element, String annotation) {
        annotations.put(element, annotation);
        saveAnnotationsToFile(); // Enregistre les annotations dans le fichier "notes" après l'ajout
    }

    public void removeAnnotation(String filePath) {
        annotations.remove(filePath);
        saveAnnotationsToFile(); // Enregistre les annotations dans le fichier "notes" après la suppression
    }

    public String getAnnotation(String filePath) {
        return annotations.get(filePath);
    }

    private void loadAnnotationsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("notes"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    annotations.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAnnotationsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("notes"))) {
            for (Map.Entry<String, String> entry : annotations.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
