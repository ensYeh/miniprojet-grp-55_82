package fr.uvsq.cprog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

/**
 * Unit test for class Er.
 */
public class ErTest {
    // Arrange
    Er er = new Er(1, "document");

    /**
     * Test de la méthode ajouter note.
     */
    @Test
    public void TestAddNote() {
        // Act
        er.addNote("this is document");
        String res = er.getNote();
        String res2 = "this is document";
        // Assert
        assertEquals(res, res2);
    }

    /**
     * Test de la méthode retourner note.
     */
    @Test
    public void TestGetNote() {
        // Act
        String res = er.getNote();
        String res2 = "";
        // Assert
        assertEquals(res, res2);
    }

    /**
     * Test de la méthode supprimer note.
     */
    @Test
    public void TestDeleteNote() {
        // Act
        er.deleteNote();
        String res = er.getNote();
        String res2 = "";
        // Assert
        assertEquals(res, res2);
    }

    /**
     * Test de la méthode toString.
     */
    @Test
    public void TestToString() {
        // Act
        String res = er.toString();
        String res2 = "{ner: 1, er: 'document', note: ''}";
        // Assert
        assertEquals(res, res2);
    }

}
