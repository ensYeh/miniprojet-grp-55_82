import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AnnotationManager {
    private final String path;
    private static final String NOTES_FILE = "notes";

    public AnnotationManager(String path) {
        this.path = path;
    }

    public void addAnnotation(int ner, String annotation) {
        Map<Integer, String> annotationData = loadAnnotationData();
        if (annotationData.get(ner) != null) {
            annotation = annotationData.get(ner) + " " + annotation; // Concaténer les notes existantes avec la nouvelle note
        }
        annotationData.put(ner, annotation);
        saveAnnotationData(annotationData);
    }


    public void removeAnnotation(int ner) {
        Map<Integer, String> annotationData = loadAnnotationData();
        annotationData.remove(ner);
        saveAnnotationData(annotationData);
    }

    public String[] getAnnotations(int ner) {
        Map<Integer, String> annotationData = loadAnnotationData();
        return annotationData.containsKey(ner) ? new String[]{annotationData.get(ner)} : new String[]{};
    }

    private Map<Integer, String> loadAnnotationData() {
        Map<Integer, String> annotationData = new HashMap<>();
        File notesFile = new File(path + "/" + NOTES_FILE);
        if (!notesFile.exists()) {
            try {
                // Créer un nouveau fichier "notes" s'il n'existe pas
                notesFile.createNewFile();
                // Sauvegarder un Map vide dans le fichier
                saveAnnotationData(annotationData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(notesFile))) {
                annotationData = (Map<Integer, String>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return annotationData;
    }


    private void saveAnnotationData(Map<Integer, String> annotationData) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + "/" + NOTES_FILE))) {
            oos.writeObject(annotationData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
