package fr.uvsq.cprog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

/**
 * Unit test for simple App.
 */
public class GDFAppTest 
{  
    /**
     * Rigorous Test :-)
     */
    @Test
    public void TestOfExecuteMethod()
    {
        // // Arrange 
        String[] splitInput = {"mkdir", "abdi"};
        // Act
        String res = GDFApp.execute(splitInput);
        String res2 = "Dossier crée avec succès\n";
        // Assert
        assertEquals(res, res2);
    }
    
}
