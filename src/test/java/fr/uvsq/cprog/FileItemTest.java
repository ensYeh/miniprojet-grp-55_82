package fr.uvsq.cprog;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class FileItemTest {
    private static final String USER_HOME = System.getProperty("user.home");
    private static final String TEST_DIRECTORY_PATH = USER_HOME + "/testFile";
    private static final String TEST_FILE_NAME = "testFileItem.txt";
    private static File testDirectory;
    private FileItem fileItem;
    private int nerTest = 10;

    @BeforeEach
    void setUp() throws IOException {
        // Création du répertoire de test dans le répertoire personnel de l'utilisateur
        testDirectory = new File(FileItemTest.TEST_DIRECTORY_PATH);
        if (!testDirectory.exists()) {
            assertTrue(testDirectory.mkdir(), "Le répertoire de test n'a pas pu être créé.");
        }
        
        // Initialiser FileItem avec le chemin du fichier normal
        fileItem = new FileItem(FileItemTest.TEST_FILE_NAME, nerTest);

        // Créer un fichier normal pour les tests
        fileItem.createNewFile();
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
    void testGetNer() {
        assertEquals(nerTest, fileItem.getNer(), "Le NER récupéré doit être égal à celui attribué lors de l'initialisation.");
    }

    @Test
    void testFileExists() {
        assertTrue(fileItem.exists(), "Le fichier temporaire devrait exister.");
    }

    // Vous pouvez ajouter plus de tests pour couvrir d'autres aspects si nécessaire
}
