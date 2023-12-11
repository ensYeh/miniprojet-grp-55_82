package fr.uvsq.cprog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;


/**
 * Unit test for simple App.
 */
public class GDFReaderTest 
{ 
    // Arrange
    GDFReader gdf = new GDFReader("C:\\Users\\HP\\Home");

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
        boolean res = gdf.ajouterNote(1, "fichier note");
        // Assert
        assertEquals(res, false);
    }    
    
}
