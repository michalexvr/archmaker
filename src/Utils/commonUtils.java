/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import java.io.File;

/**
 *
 * @author michael
 */
public class commonUtils {
    public static void pausa(long segundos)
    {
        try
        {
            Thread.sleep(segundos * 1000L);
        }
        catch(InterruptedException e)
        {
            logFile.addReg((new StringBuilder()).append("Error de interrupcion de pausa: ").append(e).toString());
            e.printStackTrace();
        }
    }

   public static String getPath(){
        String dir = System.getProperty("java.class.path").substring(System.getProperty("java.class.path").lastIndexOf(File.pathSeparator)+1);

        if(dir.lastIndexOf(File.separator)>=0){
            dir = dir.substring(0,dir.lastIndexOf(File.separator)+1);
        }else{
            dir = "";
        }
        return dir;
    }

}
