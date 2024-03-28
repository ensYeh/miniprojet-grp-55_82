package fr.uvsq.cprog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit test for simple App.
 */
public class GdfAppTest {
    /**
     * Rigorous Test for those commands that do not require NER.
     */
    /*static Stream<List<String>> commandArgumentsProvider() {
        return Stream.of(
            Arrays.asList("mkdir DocTest", "Dossier crée avec succès\n"),
            Arrays.asList("touch FileTes", "Fichier crée avec succès\n"),
            Arrays.asList("..", FileUtils.getUserDirectoryPath())
        );
    }

    @ParameterizedTest
    @MethodSource("commandArgumentsProvider")
    public void TestOfExecuteMethodWithNotNer(List<String> commandAndResult) {
        // Extraire
        String saisie = commandAndResult.get(0); 
        String exceptedResult = commandAndResult.get(1);
        // Arrange
        GdfApp.setCurrentRep(FileUtils.getUserDirectoryPath());
        String[] splitInput = saisie.split(" ");
        if (splitInput[0].equals("..")) {
            // Act specific for command ".."
            Rep gdf = new Rep(FileUtils.getUserDirectoryPath());
            String res = gdf.mkdir_method("TestFol");
            GdfApp.setCurrentRep(FileUtils.getUserDirectoryPath() + "\\TestFol");
            String res2 = GdfApp.execute(splitInput);
            // Assert
            assertEquals(GdfApp.currentRep.getCurrentFolderPath(), exceptedResult);

        } else {
            // Act
            String res = GdfApp.execute(splitInput);
            // Assert
            assertEquals(res, exceptedResult);
        }
    }*/

}
