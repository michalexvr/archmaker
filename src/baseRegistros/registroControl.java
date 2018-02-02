/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseRegistros;

import BD.ConsultaBD;
import Utils.archivo;
//import Utils.commonUtils;
import BD.baseDatos;
import Utils.commonUtils;
import Utils.fechaHora;
import Utils.logFile;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author michael
 */
public class registroControl {
    public static void generateArchivo(String fecha,String ruta){
        //archivo arch = new archivo(commonUtils.getPath()+"sensorestemp.csv");
        //String path = commonUtils.getPath()+"temp"+File.separator;
        //String fecha=fechaHora.getToday();
        //archivo arch = new archivo("/home/michael/Desktop/sensorestemp.csv");
        archivo arch = new archivo(ruta+fecha+".csv");
        ConsultaBD base = new ConsultaBD();
        ArrayList<componente> comps = new ArrayList();
        int idMargin = 0;
        StringBuilder s = new StringBuilder("unit\t");

        try{

            //Rescate de datos desde la BD

            ResultSet r = base.retDatos("SELECT idSensor, idControlCentral FROM sensor ORDER BY idSensor ASC");
            String Unidad="";
            while(r.next()){
                int id = r.getInt(1);
                int idcc = r.getInt(2);
                ArrayList<registro> regs = new ArrayList();

                //ResultSet r2 = base.retDatos("SELECT registroSensor,tipoRegistro,unidadRegistro,momentoRegistro FROM detalleSensor WHERE momentoRegistro >= DATE(NOW()) AND IdSensor = "+id+" ORDER BY momentoRegistro ASC");
                //ResultSet r2 = base.retDatos("SELECT registroSensor,tipoRegistro,unidadRegistro,momentoRegistro FROM detalleSensor WHERE momentoRegistro >= DATE_SUB(DATE(NOW()),INTERVAL 1 DAY) AND IdSensor = "+id+" ORDER BY momentoRegistro ASC");
                ResultSet r2 = base.retDatos("SELECT registroSensor,tipoRegistro,unidadRegistro,momentoRegistro FROM detalleSensor WHERE DATE(momentoRegistro) = '"+fecha+"' AND IdSensor = "+id+" ORDER BY momentoRegistro ASC");
                boolean isFirst = true;
                boolean hayData = false;
                
                while(r2.next()){
                    hayData = true;
                    if(isFirst){
                        if(r2.getString(3).compareTo("&deg;C")==0)
                        {
                            Unidad="C";
                            s.append(Unidad).append("\t");
                        }else{
                            s.append(r2.getString(3)).append("\t");
                        }
                        //if(id==129){ System.out.println(id+", "+r2.getString(3)); }
                        //System.out.println(id+", "+r2.getString(3));
                        isFirst = false;
                    }
                    regs.add(new registro(r2.getString(3),r2.getDouble(1),r2.getString(4)));
                }
                if(!hayData){
                    s.append("No Data").append("\t");
                }
                comps.add(new componente(regs,idcc));
            }

            idMargin = comps.size();// {1,2} => size 2, {1,2} => concat => {1,2,(size+1),(size+2)} => {1,2,3,4}

            r = base.retDatos("SELECT idEquipo, idControlCentral FROM equipo ORDER BY idEquipo ASC");

            while(r.next()){
                int id = r.getInt(1);
                int idcc = r.getInt(2);
                ArrayList<registro> regs = new ArrayList();

                //ResultSet r2 = base.retDatos("SELECT registroEquipo,tipoRegistro,unidadRegistro,momentoRegistro FROM detalleEquipo WHERE momentoRegistro >= DATE(NOW()) AND IdEquipo = "+id+" ORDER BY momentoRegistro ASC");
                //ResultSet r2 = base.retDatos("SELECT registroEquipo,tipoRegistro,unidadRegistro,momentoRegistro FROM detalleEquipo WHERE momentoRegistro >= DATE_SUB(DATE(NOW()),INTERVAL 1 DAY) AND IdEquipo = "+id+" ORDER BY momentoRegistro ASC");
                ResultSet r2 = base.retDatos("SELECT registroEquipo,tipoRegistro,unidadRegistro,momentoRegistro FROM detalleEquipo WHERE DATE(momentoRegistro) = '"+fecha+"' AND IdEquipo = "+id+" ORDER BY momentoRegistro ASC");
                boolean isFirst = true;
                boolean hayData = false;
                while(r2.next()){
                    hayData = true;
                    if(isFirst){
                      if(r2.getString(3).compareTo("&deg;C")==0)
                        {
                            Unidad="C";
                            s.append(Unidad).append("\t");
                        }else{
                        s.append(r2.getString(3)).append("\t");

                        }
                        isFirst = false;
                    }
                    regs.add(new registro(r2.getString(3),r2.getDouble(1),r2.getString(4)));
                }

                if(!hayData){
                    s.append("No Data").append("\t");
                }

               //comps.add(new componente(regs,id+idMargin));
               comps.add(new componente(regs,idcc));
            }

        }catch(Exception e){
            //System.out.println("Excepcion: "+e);
            logFile.addReg("Excepcion: "+e);
        }

            //procesamiento de encabezados

            StringBuilder nombres = new StringBuilder("timestamp\t");
            for(int i=0; i<comps.size(); i++){
                int ID = comps.get(i).getId();
                //if(idMargin<ID)
                if(idMargin<i)
                    nombres.append("equipo ").append(ID).append("\t");
                else
                    nombres.append("sensor ").append(ID).append("\t");
            }
            s.append("\n");
            s.append(nombres);
            arch.escribir(s.toString());

            //procesamiento de datos
            int repeats = comps.get(0).getRegistros().size();

            //System.out.println(repeats);
/*
            ArrayList<registro> regists_ = comps.get(3).getRegistros();
            for(int i=0; i<regists_.size(); i++)
                System.out.println(regists_.get(i).getMomento()+" "+regists_.get(i).getValor());
            System.exit(0);
 */
            
            String FechaHoraReferencia = "";
            for(int i=0; i<repeats;i++){
                StringBuilder str = new StringBuilder();
                for(int j=0; j<comps.size(); j++){
                    ArrayList<registro> registros_ = comps.get(j).getRegistros();
                    //System.out.println(registros_.size());
                    
                    if(j==0){
                        FechaHoraReferencia = registros_.get(i).getMomento();
                        str.append(FechaHoraReferencia).append("\t");
                        //str = registros_.get(i).getMomento()+"\t";
                    }
                    if(registros_.size() > i){
                        //str = str+registros_.get(i).getValor()+"\t";
                        //System.out.println("FechaHoraReferencia: "+FechaHoraReferencia);
                        //System.out.println("Momento: "+registros_.get(i).getMomento());
                        if(FechaHoraReferencia.substring(0, 16).equals(registros_.get(i).getMomento().substring(0, 16)))
                            str.append(registros_.get(i).getValor()).append("\t");
                        else{
                            //if(j==3) System.out.println(i+" "+FechaHoraReferencia+" != "+registros_.get(i).getMomento()+" "+registros_.get(i).getValor());
                            str.append("no value\t");
                            if(FechaHoraReferencia.substring(0, 16).compareTo(registros_.get(i).getMomento().substring(0, 16)) < 0)
                            comps.get(j).insertar(new registro("",0.0,""), i);
                            else
                                comps.get(j).eliminar(i);
                        }
                    }
                    else str.append("no value\t");//str = str+"no value\t";//
                }
                //str+="\n";//s.append("\n");
                //System.out.println("i= "+i+", "+str);
                arch.escribirLinea(str.toString());
                //arch.escribirLinea(s.toString());
            }

           //System.out.println("OK!!");
            //arch.escribirLinea(str.toString());
/*
        try{
            ResultSet r = base.retDatos("SELECT idSensor,registroSensor,tipoRegistro,unidadRegistro,momentoRegistro FROM detalleSensor WHERE momentoRegistro >= DATE(NOW()) AND IdSensor = 1 ORDER BY momentoRegistro ASC");

            //boolean first = false;
            StringBuilder s = new StringBuilder();

            ArrayList a = new ArrayList();
            while(r.next()){
                //a.add(r.getInt(1));
                //a.add(r.getInt(2));
                //a.add(r.getInt(4));
                //a.add(r.getInt(5));
                //System.out.println(r.getInt(1)+","+r.getDouble(2)+","+r.getString(4)+", "+r.getString(5));
                //asdf+=(r.getInt(1)+","+r.getDouble(2)+","+r.getString(4)+", "+r.getString(5)+"\n");
                s.append(r.getInt(1));
                s.append("\t");
                s.append(r.getDouble(2));
                s.append("\t");
                s.append(r.getString(4));
                s.append("\t");
                s.append(r.getString(5));
                s.append("\n");
            }
            arch.escribir(s.toString());
            r = base.retDatos("SELECT idEquipo,registroEquipo,tipoRegistro,unidadRegistro,momentoRegistro FROM detalleEquipo WHERE momentoRegistro >= DATE(NOW()) ORDER BY momentoRegistro ASC");
            while(r.next()){
                //a.add(r.getInt(1));
                //a.add(r.getInt(2));
                //a.add(r.getInt(4));
                //a.add(r.getInt(5));
                //System.out.println(r.getInt(1)+","+r.getDouble(2)+","+r.getString(4)+", "+r.getString(5));
            }
            //System.out.println("hi there");
        }catch(Exception e){
            logFile.addReg("Excepcion: "+e);
        }*/
    }
}
