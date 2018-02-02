/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package procesolocal;

import Utils.archivo;
import Utils.commonUtils;
import Utils.fechaUtil;
import Utils.logFile;
import baseRegistros.registroControl;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xion
 */
public class cargaControl {

    public static void generaCarga(String fecha, String root)
    {
       // boolean Estado=true;
        archivo arch = new archivo(root);
        //
          if(root!=null)
          {
            try {
                    //System.out.println(logFile.getLastLineaControl());
                    //System.exit(0);
                    String anioControl=fecha.substring(0,4);
                    String mesControl= fecha.substring(5,7);
                    String diaControl= fecha.substring(8,10);
                    int diaCorte=1;
                    boolean Control=true;
                    int NDiasMes= fechaUtil.getNumDayTheMonth(mesControl); /*para verificar si los datos del mes estan al dia */
                    //ftp.cd(fechaUtil.getNombreMes(Integer.parseInt(mesControl)));
                    /*
                        En esta parte vamos a mirar si los dias y los archivos generados estan todos cargados al sistema.
                     */
                    logFile.addReg("Comienza el tratamiento de la carga de archivos al ftp "+fecha);
                    logFile.addReg("Pregunta al archivo local las fechas de la ultima captura de datos");
                    if(logFile.getLastLineaControl()!=null)
                    {
                        //
                        String anioArchivo= logFile.getLastLineaControl().substring(0,4);
                        String mesArchivo= logFile.getLastLineaControl().substring(5,7);
                        String diaArchivo= logFile.getLastLineaControl().substring(8,10);
                        if(anioArchivo.equals(anioControl) && mesArchivo.equals(mesControl))
                        {
                            if(Integer.parseInt(diaArchivo)< fechaUtil.getNumDayTheMonth(mesArchivo))
                            {
                                diaCorte=Integer.parseInt(diaArchivo)+1;
                                Control=false;
                            }
                            
                        }
                        else
                        {
                            logFile.addReg("El mes o el anio no concuerda con la fecha actual");
                        }
                    }else{diaCorte=1;}
                   for(int firtDay=diaCorte;firtDay<=Integer.parseInt(diaControl);firtDay++)
                    {
                        String control=fechaUtil.getDateFirtMonth(mesControl,anioControl);
                        String fechaAux="";//=anioControl+"-"+mesControl+"-"+Integer.toString(firtDay);
                        if(firtDay<10)
                        {
                            fechaAux=anioControl+"-"+mesControl+"-"+"0"+Integer.toString(firtDay);
                        }
                        else
                        {
                            fechaAux=anioControl+"-"+mesControl+"-"+Integer.toString(firtDay);
                        }
                      
                            if(!arch.searchArchivos(fechaAux+".csv"))
                                {
                                //System.out.println(firtDay+"_"+fechaAux);
                                    registroControl.generateArchivo(fechaAux,root);
                                    //logFile.addReg("Cargando archivos"+fecha);
                                    /* FALTA CONTROLAR LA CAPTURA ASUMIENTO QUE ES EL PRIMER MES DEL AÑO*/
                                    logFile.addReg("Cargando reportes desde: "+fechaAux+" a FTP");

                                    
                           
                                            logFile.addReg("Se comienza ha subir el archivo al FTP");
                                    //        ftp.uploadFile(commonUtils.getPath()+"temp"+File.separator+fechaAux+".csv",fechaAux+".csv");
                                    //ftp.uploadFile(commonUtils.getPath()+"temp"+File.separator+unidades.get(i).getNombre()+".csv", unidades.get(i).getNombre()+" "+fechaInicio+" - "+lastDayWeek+".csv");

                           
                                    //logFile.addReg("Se corto al conexión al intentar subir el archivo al FTP");
                            

                                    //System.out.print("OK..");
                            //}
                                    logFile.addReg("Carga de Archivos OK");
                           
                            //logFile.ControlHora(fechaMediaCorte);
                                    
                                }
                              else
                                {
                                    logFile.addReg("El archivo "+fechaAux+".csv "+"ya existe en el ftp");
                                }
                     
                        
                    }
                    logFile.addReg("La conexion se cierra");
                     logFile.ControlHora(fecha);
                    
                /* if(!FtpDir.ExitArhivoInDirectory(ftp.list(),fecha+".csv"))
                 {
                    registroControl.generateArchivo(fecha);
                    
                            logFile.addReg("Cargando reportes desde: "+fecha+" a FTP");
                        
                            ftp.cdUp();//quedo en el directorio 2011
                            ftp.cdUp();//ahora que en la raiz
                                if(!FtpDir.Exist(anioControl, ftp))
                                {          ftp.mkdir(anioControl);
                                        System.out.println("creo un año nuevo"+anioControl);
                                }
                            ftp.cd(anioControl);

                            String month = fechaUtil.getNombreMes(Integer.parseInt(mesControl));

                                if(!FtpDir.Exist(month,ftp))
                                {
                                    System.out.println("Creo el directorio del mes nuevo:"+month);
                                    ftp.mkdir(month);
                                }
                                else
                                {
                                   logFile.addReg("Creando directorio del mes");
                                }


                            ftp.cd(month);
                                

                                ftp.uploadFile(commonUtils.getPath()+"temp"+File.separator+fecha+".csv",fecha+".csv");
                            //ftp.uploadFile(commonUtils.getPath()+"temp"+File.separator+unidades.get(i).getNombre()+".csv", unidades.get(i).getNombre()+" "+fechaInicio+" - "+lastDayWeek+".csv");


                            //System.out.print("OK..");
                        //}
                            logFile.addReg("Carga de Archivos OK");
                            //logFile.ControlHora(fechaMediaCorte);
                            ftp.cdUp();
                            ftp.cdUp();
                            ftp.cdUp();
                }
                else
                {
                    logFile.addReg("El archivo de hoy ya esta generado"+fecha);
                }*/
                //registroControl.generateArchivo(fecha);
            } catch (Exception ex) {
                logFile.addReg("Problemas con el seguimiento de los datos cargaControl,"+ex);
                ex.printStackTrace();
            }

          }
         
    }
    
}
