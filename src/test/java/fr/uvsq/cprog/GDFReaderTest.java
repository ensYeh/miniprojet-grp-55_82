package fr.uvsq.cprog;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class GDFReaderTest 
{  
    /**
     * Rigorous Test :-)
     */
    @Test
    public void TestOfMkdirMethod()
    {
        // Arrange 
        GDFReader gdf = new GDFReader();

        // Act
        String res = gdf.mkdir_method("Doc");

        String res2 ="Ce dossier existe deja !\n";

        // Assert
        assertEquals(res, res2);
    }

    @Test
    public void TestOfTouchMethod()
    {
        // Arrange 
        GDFReader gdf = new GDFReader();

        // Act
        String res = gdf.touch_method("file.txt");

        String res2 = "Ce fichier existe deja !\n";

        // Assert
        assertEquals(res, res2);
    }

    @Test
    public void TestOfLsMethod()
    {
        // Arrange 
        GDFReader gdf = new GDFReader();

        // Act
        String res = gdf.ls_method();
        String res2 = "Ner Name\n"
        + "--- ----\n"
        + "1 Doc\n";

        // Assert
        assertEquals(res, res2);
    }
    
}
