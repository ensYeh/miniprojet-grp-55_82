package fr.uvsq.cprog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Directory {
    private String path;
    private List<FileItem> files;

    public Directory(String path) {
        this.path = path;
        this.files = new ArrayList<>();
        loadFileItems();
    }

    public void loadFileItems() {
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            File[] fileList = directory.listFiles();
            if (fileList != null) {
                int ner = 1;
                for (File file : fileList) {
                    files.add(new FileItem(file.getAbsolutePath(), ner++));
                }
            }
        } else {
            System.err.println("Le répertoire spécifié n'existe pas ou n'est pas un répertoire valide.");
        }
    }

    public void addFile(FileItem file) {
        files.add(file);
    }

    public String getpath() {
        return path;
    }

    public List<FileItem> getFiles() {
        return files;
    }

    public FileItem getFileByNER(int ner) {
        // Supposons que NER commence à partir de 1
        if (ner >= 1 && ner <= files.size()) {
            return files.get(ner - 1);
        }
        return null;
    }

    // Méthode pour afficher les noms associés aux NER
    public void printFileNamesWithNER() {
        for (FileItem file : files) {
            System.out.println("NER: " + file.getNer() + ", Nom du fichier: " + file.getName());
        }
    }

}
