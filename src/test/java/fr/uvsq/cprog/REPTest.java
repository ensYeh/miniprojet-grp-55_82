package fr.uvsq.cprog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

/**
 * Unit test for simple App.
 */
public class RepTest {
    // Arrange
    Rep gdf = new Rep(FileUtils.getUserDirectoryPath());

    /**
     * Test de la méthode créer repertoire.
     */
    @Test
    public void TestOfMkdirMethod() {
        // Act
        String res = gdf.mkdir_method("Doc");

        String res2 = "Dossier crée avec succès\n";

        // Assert
        assertEquals(res, res2);
    }

    /**
     * Test de la méthode créer fichier.
     */
    @Test
    public void TestOfTouchMethod() {
        // Act
        String res = gdf.touch_method("file.txt");

        String res2 = "Fichier crée avec succès\n";

        // Assert
        assertEquals(res, res2);
    }

    /**
     * Test de la méthode ajouter note.
     */
    @Test
    public void TestAjouterNote() {
        // Act
        boolean res = gdf.ajouterNote(1000, "fichier note");
        // Assert
        assertEquals(res, false);
    }

    /**
     * Test de la méthode retire note.
     */
    @Test
    public void TestRetireNote() {
        // Act
        boolean res = gdf.retireNote(1000);
        // Assert
        assertEquals(res, false);
    }

    // @Test
    // public void TestVisu()
    // {
    // // Act

    // // Assert

    // }

    /**
     * Test de la méthode changer de répertoire.
     */
    @Test
    public void TestDescendre() {
        // Act
        // Assert
        assertEquals(gdf.descendre(1), null);
    }

    /**
     * Test de la méthode remonter au répertoire parent.
     */
    @Test
    public void TestRemonter() throws InterruptedException {
        // Act
        String res = gdf.mkdir_method("Fol");
        gdf = new Rep(FileUtils.getUserDirectoryPath() + "\\Fol");
        // Assert
        assertEquals(gdf.remonter().getCurrentFolderPath(), FileUtils.getUserDirectoryPath());
    }

}
