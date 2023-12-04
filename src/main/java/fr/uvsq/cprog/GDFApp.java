package fr.uvsq.cprog;

import java.util.Scanner;


/**
 * La Class GDFApp représente l'interface 
 * utilisateur de base et gère l'interaction 
 * entre l'utilisateur et le gestionnaire de fichiers
 *
 */
public class GDFApp {
    
    public static void main(String[] args)
    {
        System.out.println("C:\\Users\\HP\\Home" + ">");
        while(true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            String[] splitInput = input.split(";");
            System.out.println(execute(splitInput));
        }
    }

    public static String execute(String[] cmd)
    {
        GDFReader gdf = new GDFReader();
        String result ="";
        
        if(cmd[0].equals("ls")) {
            result += gdf.ls_method();
        
        }else if(cmd[0].equals("mkdir")) {
            result = gdf.mkdir_method(cmd[1]);
        }else if(cmd[0].equals("touch")) {
            result = gdf.touch_method(cmd[1]);
        }else{
            result += "saisir une autre commande" + "\n";
        }

        result += gdf.getCurrentFolderPath() +">";

        return result;
    }
}
