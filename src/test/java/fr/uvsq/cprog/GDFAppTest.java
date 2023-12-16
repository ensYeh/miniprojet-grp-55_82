package fr.uvsq.cprog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

/**
 * Unit test for simple App.
 */
public class GdfAppTest {
    /**
     * Rigorous Test 
     */
    @Test
    public void TestOfExecuteMethod() {
        GdfApp.setCurrentRep(FileUtils.getUserDirectoryPath());
        // Arrange
        String[] splitInput = { "mkdir", "abdi" };
        // Act
        String res = GdfApp.execute(splitInput);
        String res2 = "Dossier crée avec succès\n";
        // Assert
        assertEquals(res, res2);
    }

}
