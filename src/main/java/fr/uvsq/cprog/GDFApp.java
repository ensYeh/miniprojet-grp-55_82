package fr.uvsq.cprog;

import java.util.Scanner;


/**
 * La Class GDFApp représente l'interface 
 * utilisateur de base et gère l'interaction 
 * entre l'utilisateur et le gestionnaire de fichiers
 *
 */
public class GDFApp {
    public static GDFReader gdf = new GDFReader();

    public static int ner;
    
    public static void main(String[] args)
    {
        System.out.println("C:\\Users\\HP\\Home" + ">");
        while(true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] splitInput = input.split(" ");
            System.out.println(execute(splitInput));
        }
    }

    public static String execute(String[] cmd)
    {
        String result ="";
        
        if(cmd[0].equals("ls")) {
            result += gdf.ls_method();
        
        }else if(cmd[0].equals("mkdir")) {
            result = gdf.mkdir_method(cmd[1]);
        }else if(cmd[0].equals("touch")) {
            result = gdf.touch_method(cmd[1]);
        }else if(cmd[0].equals("..")){
            gdf = gdf.remonter();
        }else if(cmd[1].equals(".")){
            ner = Integer.parseInt(cmd[0]);
            if(gdf.descendre(ner) != null){
                gdf = gdf.descendre(ner);
            }else{
                result += "Cet element n'existe pas ou NER ne désigne pas un repertoire !\n";
            }
        }else if(cmd[1].equals("visu")){
            ner = Integer.parseInt(cmd[0]);
            if(gdf.visu(ner) != null){
                result += gdf.visu(ner);
            }else{
                result += "Cet element n'existe pas ou NER ne désigne pas un fichier !\n";
            }
        }else{
            result += "saisir une autre commande\n";
        }

        result += "\n" + gdf.getCurrentFolderPath() +">";

        return result;
    }
}
