package fr.uvsq.cprog;

import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public void copy(FileItem source, String destination) {
        // Implémentation de la copie de fichier
    }

    public void cut(FileItem source, String destination) {
        // Implémentation de la coupe de fichier
    }

    public void paste(Directory directory, String newName) {
        // Implémentation de la commande past
    }

    public Directory getParentDirectory(Directory directory) {
        // Implémentation pour obtenir le répertoire parent
        return null;
    }

    public Directory enterDirectory(FileItem directory) {
        // Implémentation pour entrer dans un répertoire
        return null;
    }

    public void createDirectory(Directory parentDirectory, String name) {
        // Implémentation pour créer un répertoire
    }

    public void visualizeTextFile(FileItem file) {
        // Implémentation pour visualiser le contenu d'un fichier texte
    }

    public void displayFileSize(FileItem file) {
        // Implémentation pour afficher la taille d'un fichier
    }

    public List<FileItem> findFile(Directory directory, String name) {
        // Implémentation pour trouver un fichier dans un répertoire et ses sous-répertoires
        return null;
    }

    public void displayFoundFiles(List<FileItem> foundFiles) {
        // Implémentation pour afficher les fichiers trouvés
    }
}
