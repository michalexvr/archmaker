/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archmaker20;

import Utils.archivo;
import Utils.logFile;
import java.io.File;



/**
 *
 * @author michael
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

            System.out.println(System.getProperty("java.class.path").substring(System.getProperty("java.class.path").lastIndexOf(File.pathSeparator)+1));
            System.exit(0);

            logFile.addReg("Se inicia la aplicacion");
            //logFile.addReg("Se inicia el proceso de emision");
            //baseRegistros.registroControl.generateArchivo("2011-12-23", "/home/xion/gonzalo.csv");
            procesolocal.procesoGenerador.generateProcess();
            logFile.addReg("se cierra el proceso");
           // logFile.ControlHora("2011-12-20");
            //System.out.println();
            //System.exit(0);
       // procesoInicio p = new procesoInicio(); //(inicia el proceso)
        //archivo directorio = new archivo("/home/xion/Gonzalo/Diciembre");
        // archivo directorio = new archivo(); //new File("/home/xion/Gonzalo/Diciembre");
              //directorio.creteDirectory("/home/xion/Gonzalo/Diciembre");
         /*String root ="/home/xion/Gonzalo/ftpcontrol/" ;
         String anio ="2011";
         if(directorio.isDirectory(root+anio+"/"))
         {
             System.out.println("esta");
         }else{System.out.println("no esta");}
       if(directorio.searchDirectory(root+anio+"/")){ System.out.println("esta");}
        else
        {   //System.out.println(root+anio+"/");
            //directorio.creteDirectory(root+anio+"/");
        }
         archivo arch = new archivo(root+anio);
         System.out.println(arch.searchArchivos("2011-07-03.csv"));*/
         //System.out.println(directorio.getLastDirectoy());
         //p.start();
        //TrayIcons.generateTray(p);
    }

}
