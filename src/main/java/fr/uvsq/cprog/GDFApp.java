package fr.uvsq.cprog;

import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import java.io.File;


/**
 * La Class GDFApp représente l'interface 
 * utilisateur de base et gère l'interaction 
 * entre l'utilisateur et le gestionnaire de fichiers
 *
 */
public class GdfApp {
    public static Rep currentRep = null;
    public static int ner;
    
    public static void main(String[] args)
    {
        currentRep = new Rep(args[0]);
        System.out.println("Si vous avez besoin d'aider taper la commande <help> !");
        while(true){
            System.out.println("\n" + currentRep.getCurrentFolderPath() + ner + ">");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] splitInput = input.split(" ");
            if (splitInput[0].equals("exit"))   break;
            System.out.println(execute(splitInput));
        }
    }

    public static void setCurrentRep(String path)
    {
        Rep gdfChange = new Rep(path);
        currentRep = gdfChange; 
    }

    public static String execute(String[] cmd)
    {
        String result ="";
        
        if(cmd.length == 1 && cmd[0].equals("ls")) {
            result += currentRep.ls_method();
        }else if(cmd.length == 2 && cmd[0].equals("mkdir")) {
            result = currentRep.mkdir_method(cmd[1]);
        }else if(cmd.length == 2 && cmd[0].equals("touch")) {
            result = currentRep.touch_method(cmd[1]);
        }else if(cmd.length == 1 && cmd[0].equals("..")){
            currentRep = currentRep.remonter();
        }
        else if(cmd.length == 2 && cmd[1].equals(".")){
            try
            {
                ner = Integer.parseInt(cmd[0]);
                if(currentRep.descendre(ner) != null){
                    currentRep = currentRep.descendre(ner);
                }else{
                    result += "Cet element n'existe pas ou NER ne désigne pas un repertoire !\n";
                }
            } catch (NumberFormatException ex)
            {
                result += "Ner doit être un nombre entier";
            }
        }else if(cmd.length == 1 && cmd[0].equals(".")){
            if(currentRep.descendre(ner) != null){
                currentRep = currentRep.descendre(ner);
            }else{
                result += "Cet element n'existe pas ou NER ne désigne pas un repertoire !\n";
            }
        }else if(cmd.length == 2 && cmd[1].equals("visu")){
            try
            {
                ner = Integer.parseInt(cmd[0]);
                if(currentRep.visu(ner) != null){
                    result += currentRep.visu(ner);
                }else{
                    result += "Cet element n'existe pas ou NER ne désigne pas un fichier !\n";
                }
            } catch (NumberFormatException ex)
            {
                result += "Ner doit être un nombre entier";
            }
        } else if(cmd.length == 1 && cmd[0].equals("visu")){
            if(currentRep.visu(ner) != null){
                result += currentRep.visu(ner);
            }else{
                result += "Cet element n'existe pas ou NER ne désigne pas un fichier !\n";
            }
        } else if (cmd.length == 2 && cmd[0].equals("find")) {
            result += currentRep.find(cmd[1]);
        } else if (cmd.length == 2 && cmd[1].equals("-")) {
            try {
                ner = Integer.parseInt(cmd[0]);
                if(currentRep.retireNote(ner)){
                    result += "Note supprimé\n";
                }else{
                    result += "Ce NER n'existe pas !\n";
                }
            } 
            catch (NumberFormatException ex) {
                result += "Ner doit être un entier";
            }
        } else if (cmd.length == 1 && cmd[0].equals("-")) {
            if(currentRep.retireNote(ner)){
                result += "Note supprimé\n";
            }else{
                result += "Ce NER n'existe pas !\n";
            }
        } else if(cmd.length > 2 && cmd[1].equals("+")){
            try {
                ner = Integer.parseInt(cmd[0]);
                String concat = String.join(" ", cmd);
                String[] concatSplit = concat.split("\"");
                if(currentRep.ajouterNote(ner, concatSplit[1])){
                    result += "Note ajouté\n";
                }else{
                    result += "Ce NER n'existe pas !\n";
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                result += "Veuillez saisir le text entre guillemets";
            }catch (NumberFormatException ex) {
                result += "Ner doit être un entier";
            }
        } else if(cmd.length > 1 && cmd[0].equals("+")){
            try {
                String concat = String.join(" ", cmd);
                String[] concatSplit = concat.split("\"");
                if(currentRep.ajouterNote(ner, concatSplit[1])){
                    result += "Note ajouté\n";
                }else{
                    result += "Ce NER n'existe pas !\n";
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                result += "Veuillez saisir le text entre guillemets";
            }
        } else if(cmd[0].equals("help")){
            result += "Le saisie doit être sous la forme [<NER>] [<Commande>] [<Nom>].\n"
            + "Les crochets signifient \"optionnel\"\n"
            + "-[<Ner>] doit être un entier qui fait reference à un élément du repertoire courant.\n"
            + "-Voici les [<commande>] a utilisé sont :\n"
            + "\t- [<NER>] cut : permet de copier et couper un élément\n"
            + "\t- [<NER>] copy : permet de copier un élément\n"
            + "\t- past : permet de coller un élément\n"
            + "\t- .. : permet de remonter au répertoire parent\n"
            + "\t- [<NER>] . : permet de entre dans un répertoire à condition que le NER désigne un répertoire\n"
            + "\t- mkdir [<Nom>] : permet de créer un répertoire\n"
            + "\t- touch [<Nom>] : permet de créer un fichier\n"
            + "\t- [<NER>] visu : permet de voir le contenu d’un fichier texte. Si le fichier n’est pas de type texte, vous afficherez sa taille \n"
            + "\t- find [<Nom>] : permet de recherche dans toutes les sous répertoires du répertoire courant, le(s) fichier(s) et les affiches\n"
            + "\t- ls : permet de lister les éléments du répertoire courant\n"
            + "\t- [<NER>] + \"texte\" : le texte est ajouté ou concaténé au texte existant sur l’ER !!! NB: le texte doit être entre guillemets\n"
            +"\t- [<NER>] - : permet de retire tout le texte associé à l’ER\n"
            + "-Exemple :\n"
            + " - 2 copy : permet de copie l'élément 2\n"
            + " - mkdir mydoc: permet de créer un répertoire nommé <mydoc>\n"
            + " - 4 + \"this is notebook\" : permet d'ajouter ou concaténer au texte existant sur l’élément 4\n";
        } else {
            result += "Saisie incorrect, si vous avez besoin d'aide taper la commande <help> !\n";
        }

        return result;
    }
}
