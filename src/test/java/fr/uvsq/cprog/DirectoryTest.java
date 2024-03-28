package fr.uvsq.cprog;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class DirectoryTest {
    private static final String USER_HOME = System.getProperty("user.home");
    private static final String TEST_DIRECTORY_PATH = USER_HOME + "/testDirectory";
    private static File testDirectory;

    @BeforeAll
    static void setUp() throws IOException {
        // Création du répertoire de test dans le répertoire personnel de l'utilisateur
        testDirectory = new File(DirectoryTest.TEST_DIRECTORY_PATH);
        if (!testDirectory.exists()) {
            assertTrue(testDirectory.mkdir(), "Le répertoire de test n'a pas pu être créé.");
        }

        // Création de fichiers de test dans ce répertoire
        new File(DirectoryTest.TEST_DIRECTORY_PATH + "/file1.txt").createNewFile();
        new File(DirectoryTest.TEST_DIRECTORY_PATH + "/file2.txt").createNewFile();
    }

    @AfterAll
    static void tearDown() throws IOException {
        // Supprimer le répertoire de test et son contenu
        Files.walk(testDirectory.toPath())
            .sorted(Comparator.reverseOrder()) // Supprimer d'abord les fichiers dans les sous-répertoires
            .map(Path::toFile)
            .forEach(File::delete);

        // Supprimer le repertoire de test lui-même
        testDirectory.delete();
        
        // Vérifier si le répertoire a été supprimé
        assertFalse(testDirectory.exists(), "Le répertoire de test et son contenu n'ont pas été supprimés.");
    }

    @Test
    void loadFileItemsShouldCorrectlyLoadFiles() {
        Directory directory = new Directory(TEST_DIRECTORY_PATH);
        assertEquals(2, directory.getFiles().size(), "Le nombre de fichiers chargés est incorrect.");
    }

    @Test
    void getFileByNERShouldReturnCorrectFileItem() {
        Directory directory = new Directory(TEST_DIRECTORY_PATH);
        FileItem file1 = directory.getFileByNER(2);
        assertNotNull(file1, "Le FileItem n'a pas été trouvé par son NER.");
        assertTrue(file1.getAbsolutePath().contains("file1.txt"), "Le FileItem récupéré ne correspond pas au fichier attendu.");
    }

    @Test
    void printFileNamesWithNERShouldPrintFileNamesWithNER() {
        // Créer un répertoire de test et y ajouter des fichiers
        Directory directory = new Directory(TEST_DIRECTORY_PATH);

        // Capturer la sortie standard dans un flux de sortie
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Appeler la méthode pour afficher les noms de fichiers avec leurs NER
        directory.printFileNamesWithNER();

        // Récupérer la sortie imprimée
        String printedOutput = outContent.toString().trim();

        // Vérifier si la sortie contient les noms de fichiers avec leurs NER
        assertTrue(printedOutput.contains("NER: 2, Nom du fichier: file1.txt"), "La sortie imprimée ne contient pas le nom de fichier avec son NER correspondant.");
        assertTrue(printedOutput.contains("NER: 1, Nom du fichier: file2.txt"), "La sortie imprimée ne contient pas le nom de fichier avec son NER correspondant.");
    }

    // Ajoutez plus de tests au besoin
}
