import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class AnnotationManagerTest {
    private static final String USER_HOME = System.getProperty("user.home");
    private static final String TEST_DIRECTORY_PATH = USER_HOME + "/testDirectory";
    private static File testDirectory;
    private static AnnotationManager testAnnotation;

    @BeforeAll
    static void setUp() throws IOException {
        // Création du répertoire de test dans le répertoire personnel de l'utilisateur
        testDirectory = new File(AnnotationManagerTest.TEST_DIRECTORY_PATH);
        if (!testDirectory.exists()) {
            assertTrue(testDirectory.mkdir(), "Le répertoire de test n'a pas pu être créé.");
        }

        // Création de fichiers de test dans ce répertoire
        new File(AnnotationManagerTest.TEST_DIRECTORY_PATH + "/file1.txt").createNewFile();
        new File(AnnotationManagerTest.TEST_DIRECTORY_PATH + "/file2.txt").createNewFile();

        // Création d'une instance de AnnotationManager
        testAnnotation = new AnnotationManager(AnnotationManagerTest.TEST_DIRECTORY_PATH);
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
    void testAddAnnotation() {
        testAnnotation.addAnnotation(1, "Annotation 1");
        String[] annotations = testAnnotation.getAnnotations(1);
        assertEquals(Arrays.asList("Annotation 1"), Arrays.asList(annotations),
                "L'annotation n'a pas été correctement ajoutée.");
    }

    @Test
    void testAddAnnotationWithConcatenation() {
        testAnnotation.addAnnotation(1, "Annotation 1.0");
        String[] annotations = testAnnotation.getAnnotations(1);
        assertEquals(Arrays.asList("Annotation 1 Annotation 1.0"), Arrays.asList(annotations),
                "L'annotation n'a pas été correctement ajoutée.");
    }

    @Test
    void testRemoveAnnotation() {
        testAnnotation.addAnnotation(2, "Annotation 2");
        testAnnotation.removeAnnotation(2);
        String[] annotations = testAnnotation.getAnnotations(2);
        assertEquals(0, annotations.length, "L'annotation n'a pas été correctement supprimée.");
    }

    @Test
    void testGetAnnotations() {
        testAnnotation.addAnnotation(3, "Annotation 3");
        String[] annotations = testAnnotation.getAnnotations(3);
        assertEquals(Arrays.asList("Annotation 3"), Arrays.asList(annotations),
                "Les annotations récupérées ne sont pas correctes.");
    }
}
