package fr.uvsq.cprog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

/**
 * Unit test for simple App.
 */
public class ERTest 
{  
    /**
     * Rigorous Test :-)
     */
    @Test
    public void TestAddNote()
    {
        // Arrange
        ER er = new ER("document");
        // Act
        er.addNote("this is document");
        String res = er.getNote();
        String res2 = "this is document";
        // Assert
        assertEquals(res, res2);
    }

    // @Test
    // public void TestDeleteNote()
    // {

    // }
    
}
