package fr.uvsq.cprog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

/**
 * Unit test for simple App.
 */
public class RepTest 
{ 
    // Arrange
    REP gdf = new REP(FileUtils.getUserDirectoryPath());

    /**
     * Rigorous Test :-)
     */

    @Test
    public void TestOfMkdirMethod()
    {
        // Act
        String res = gdf.mkdir_method("Doc");

        String res2 = "Dossier crée avec succès\n";

        // Assert
        assertEquals(res, res2);
    }

    @Test
    public void TestOfTouchMethod()
    {
        // Act
        String res = gdf.touch_method("file.txt");

        String res2 = "Fichier crée avec succès\n";

        // Assert
        assertEquals(res, res2);
    }

    @Test
    public void TestAjouterNote()
    {
        // Act
        boolean res = gdf.ajouterNote(1000, "fichier note");
        // Assert
        assertEquals(res, false);
    }

    @Test
    public void TestRetireNote()
    {
        // Act
        boolean res = gdf.retireNote(1000);
        // Assert
        assertEquals(res, false);
    }

    // @Test
    // public void TestVisu()
    // {
    //     // Act

    //     // Assert

    // }

    @Test
    public void TestDescendre()
    {
        // Act
        // Assert
        assertEquals(gdf.descendre(1), null);
    }

    @Test
    public void TestRemonter() throws InterruptedException
    {
        Thread.sleep(4000);
        // Act
        String res = gdf.mkdir_method("Fol");
        gdf = new REP(FileUtils.getUserDirectoryPath() + "\\Fol");
        // Assert
        assertEquals(gdf.remonter().getCurrentFolderPath(), FileUtils.getUserDirectoryPath());
    }
   
    
    @Test
    public void TestFind() {
        // Act
        String res = gdf.find("file5");
        // Assert
        assertEquals(res, "Fichier non trouvé !\n");
    }
    @Test
    public void tesCopy(){
        // Act
        Path res = gdf.copy(567);
        // Assert
        assertEquals(res, null);
    }
    @Test
    public void TestPast(){
        // Act
        String res = gdf.past(path);
        // Assert
        assertEquals("Element collé.\n", res);
    }
   
}   
