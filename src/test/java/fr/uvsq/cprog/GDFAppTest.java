package fr.uvsq.cprog;

import static org.junit.Assert.*;

import org.junit.Test;

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
        String[] splitInput = {"ls"};
        // Act
        String res = GDFApp.execute(splitInput);
        String res2 = "Ner Name\n" 
        + "--- ----\n"
        + "\nC:\\Users\\HP\\Home>";
        // Assert
        assertEquals(res, res2);
    }
    
}
