/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package procesolocal;
import Utils.archivo;
import Utils.fechaHora;
import Utils.fechaUtil;
import Utils.logFile;



/**
 *
 * @author Gonzalo
 * /home/ftpcontrol/anio/mes/(archivos)
 */
public class procesoGenerador {
 
  
    public static void generateProcess(){//
	boolean estado=true;
        String root="/home/xion/Gonzalo/ftpcontrol/";//"/home/ftpcontrol/";  //"/home/xion/Gonzalo/ftpcontrol/";
        ///home/ftpcontrol/anio/mes/(archivos)
        try {
            while(estado)
                {
                            //SunFtpWrapper ftp = new SunFtpWrapper();
                            //String serverName = "201.238.196.246";
                            archivo directorio = new archivo();
                           
                            if (directorio.isDirectory(root))
                            {
				
				try {

                                    //System.out.println("Inicio en el estado");
                                        //buscamos el ultimo directorio en la raiz

                                        String anio ="";// directorio.listDirectory(fechaUtil.getYearNow(fechaHora.getNow()));
                                        
                                        String fechaActual=fechaHora.getToday();
                                        String mesActual=fechaUtil.getNombreMes(Integer.parseInt(fechaHora.getMonthNow()));
                                        String anioActual = " ";
                                        try
                                        {
                                            /*anio actual */
                                            anioActual=fechaActual.substring(0,4);
                                        }
                                        catch(Exception ex)
                                        {
                                            logFile.addReg("problemas con el subDeañioProcesoStart "+ex);
                                        }
                                        /*
                                         * verificamos si el directorio del anio actual esta.
                                         *
                                         */
                                        if(directorio.searchDirectory(anioActual))
                                        {
                                             //System.out.println("Paso al directorio del anio Actual");
                                            //hay un directorio en la raiz por lo que ingresamos en el ultimo que es el año
                                            String rootAm = root+anioActual+"/"+mesActual+"/";
                                                                //
                                            //verificamos si hay directorios dentro del año, que clasifican los meses
                                           if(directorio.searchDirectory(rootAm)) //(mes !=null && mesActual.equals(mes))
                                           {
                                               /*
                                                    si esta el directorio del mes ahora se ejecuta cargaControl(fechaActual,root)
                                                */
                                              // System.out.println("Paso al directorio del mes"+rootAm);
                                                    logFile.addReg("procesGenerador"+"_"+fechaActual);
                                                    cargaControl.generaCarga(fechaActual,rootAm);
                                                    estado=false;
                                                    
                                                
                                            }
                                           else
                                           {
                                                  
                                                directorio.creteDirectory(rootAm);
                                                 /*
                                                    Se crea el directorio mes y se ejecuta cargaControl(fechaActual,root)
                                                */
                                                
                                                     logFile.addReg("procesGenerador"+"_"+"Pasa y se genera un nuevo mes"+"_"+fechaActual);
                                                    // System.out.println("Paso al directorio del mes"+rootAm);
                                                    cargaControl.generaCarga(fechaActual,rootAm);
                                                    estado=false;
                                                    //esta el año creado pero no hay meses ni dias por lo que se llama al metodo que genera reportes indicando al primer dia del año
                                                    //String date = archmakertransoceanica.fechaHora.getBeforeDay(Integer.parseInt(anio), 01, 01);
                                                    //reporteControl.generaReporte(date,ftp);
                                            }
                                        }
                                        else
                                        {
                                            //el directorio esta vacio por lo que se llama al metodo sin parametros y el poblara el ftp con todo lo que encuentre en el server.
                                            logFile.addReg("El anio es igual a null: "+anio);
                                            logFile.addReg("Entonces creamos el directorio del año nuevo");
                                            directorio.creteDirectory(root+anioActual+"/");
                                            /*
                                             * ejecturar la cargaControl, donde se traspasa la fechaActual y
                                             * la ruta del directorio donde se generara los archivos
                                             */
                                            //reporteControl.generaReporte(fechaActual,ftp);
                                            //reporteControl.generaReporte(ftp);
                                        }
				} catch (Exception ex) {
				
                                        logFile.addReg("Falla en procesoGenerador "+ex);
                                        
                                        
				} finally {
					
				}
			} else {
				/* El directorio raiz no esta creado s
                                 * si pasa esto crea el directorio raiz y luego
                                 * hace los tratamientos sigueintes
                                 */
                                directorio.creteDirectory(root);
                                logFile.addReg("Problemas con el directorio Raiz ftpcontrol");
                                //estado=false;
			}

                        try
                        {
                            //logFile.ControlHora(fechaHora.getNow());
                        }catch(Exception Ex){/*da lo mismo por ahora*/}
			//System.out.println("Listo");
                        if(!estado)
                        {
                            logFile.addReg("Listo");
                            
                        }
                    }
		} catch(Exception ex) {
                        logFile.addReg("Falla general en el procesoGenerador"+ex);
		
		}
    }

}


/* Urgente
 * Bomba IC Renning: tem(ºC),caudal(gpm) falta
 * Renning falta
 * chiller todos falta temperatura setiado, contrastado con la temperatura del edificio 
 * Intercambiadores de calor todos falta
 * Pozo falta
 * Tablero
 * UMA 1 y 2  Todas falta // tem Aire y agua retorno y salida, consumo energetico,
 *
 * Zona, interior, exterior y equipos_--__--__--__--__--__--__---> equipos-->-->informacion asociada a equipos-->pantalla y luego generar la opcion de compara con otro elemento.
 */