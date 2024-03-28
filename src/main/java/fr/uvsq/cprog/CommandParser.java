package fr.uvsq.cprog;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    private FileManager fileManager;
    private Directory currentDirectory;
    private AnnotationManager annotationManager;

    public CommandParser() {
        this.fileManager = new FileManager();
        this.annotationManager = new AnnotationManager();
        // Initialise le répertoire courant avec le répertoire racine du système de fichiers
        this.currentDirectory = new Directory("/"); // Supposons que "/" soit le répertoire racine
    }

    public void parseCommand(String command) {
        String[] parts = command.split(" ");
        String ner = null;
        String action = null;
        String name = null;

        if (parts.length >= 1 && !parts[0].isEmpty()) {
            ner = parts[0];
        }
        if (parts.length >= 2 && !parts[1].isEmpty()) {
            action = parts[1];
        }
        if (parts.length >= 3 && !parts[2].isEmpty()) {
            name = parts[2];
        }

        switch (action) {
            case "copy":
                if (ner != null) {
                    int nerIndex = Integer.parseInt(ner);
                    FileItem fileToCopy = currentDirectory.getFileByNER(nerIndex);
                    if (fileToCopy != null) {
                        fileManager.copy(fileToCopy, name + "-copy");
                    }
                }
                break;

            case "cut":
                if (ner != null) {
                    int nerIndex = Integer.parseInt(ner);
                    FileItem fileToCut = currentDirectory.getFileByNER(nerIndex);
                    if (fileToCut != null) {
                        fileManager.cut(fileToCut, name);
                    }
                }
                break;

            case "past":
                fileManager.paste(currentDirectory, name);
                break;

            case "..":
                // Aller au répertoire parent
                currentDirectory = fileManager.getParentDirectory(currentDirectory);
                break;

            case ".":
                if (ner != null) {
                    int nerIndex = Integer.parseInt(ner);
                    FileItem directoryToEnter = currentDirectory.getFileByNER(nerIndex);
                    if (directoryToEnter != null && directoryToEnter.isDirectory()) {
                        currentDirectory = fileManager.enterDirectory(directoryToEnter);
                    }
                }
                break;

            case "mkdir":
                if (name != null) {
                    fileManager.createDirectory(currentDirectory, name);
                }
                break;

            case "visu":
                if (ner != null) {
                    int nerIndex = Integer.parseInt(ner);
                    FileItem fileToVisualize = currentDirectory.getFileByNER(nerIndex);
                    if (fileToVisualize != null) {
                        if (!fileToVisualize.isDirectory()) {
                            fileManager.visualizeTextFile(fileToVisualize);
                        } else {
                            fileManager.displayFileSize(fileToVisualize);
                        }
                    }
                }
                break;

            case "find":
                if (name != null) {
                    List<FileItem> foundFiles = fileManager.findFile(currentDirectory, name);
                    fileManager.displayFoundFiles(foundFiles);
                }
                break;

            default:
                System.out.println("Commande non reconnue.");
                break;
        }
    }
}