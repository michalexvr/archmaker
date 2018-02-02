/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BD;

import Utils.fechaHora;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xion
 */
public class capturaData {

    private ConsultaBD consulta;
    private String nombre="";
    private String equipo="";
    private String data="";
    private String fechaRegistro="";
    private String fechaActual="";
    private ResultSet res;
     boolean estado=false;
     int idSensor=0,idEquipo=0;
    public capturaData()
    {
        consulta = new ConsultaBD();
    }
    public  void setFiltro(String nombre,String equipo,String recinto,String data,String unidadEl,String fechaRegistro,String url)
    {
        
        
       String tipoRe="N/A";
       /* if(nombre.equals("SetPoint") && !consulta.checkData("Select * from sensor,sensorSource where sensor.idSensor=sensorSource.idSensor && sensorSource.urlFuente='"+url+"'"))
        {
           consulta.ejecutarSentencia("Insert into sensor(nombreSensor,tipoSensor,pisoSensor,alaSensor,sectorSensor,recintoSensor,areaSensor) values('"+nombre+"','"+nombre+"','"+recinto+"','N/A','N/A','N/A','N/A') ");
           res= consulta.retDatos("Select idSensor from sensor Order by IdSensor desc Limit 1");
            try {
                while (res.next()) {
                    idSensor= Integer.parseInt(res.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(capturaData.class.getName()).log(Level.SEVERE, null, ex);
            }
           consulta.ejecutarSentencia("Insert into sensorSource(urlFuente,idSensor) values('"+url+"',"+idSensor+")");
           
        }
        else
        {
            if(consulta.checkData("Select * from sensor,sensorSource where sensor.idSensor=sensorSource.idSensor && sensorSource.urlFuente='"+url+"'"))
            {
                System.out.println(nombre+" "+equipo);
                if(unidadEl.equals("ºC")){tipoRe="Temperatura";}
                consulta.ejecutarSentencia("Insert into detalleSensor(registroSensor,tipoRegistro,unidadRegistro,momentoRegistro,momentoIngreso,idSensor) values("+Double.parseDouble(data)+",'"+tipoRe+"','"+unidadEl+"','"+fechaHora.getNow()+"','"+fechaRegistro+"',"+idSensor+")");
            }
        }*/
       /*if(nombre.equals("Sensor") && !consulta.checkData("Select * from sensor,sensorSource where sensor.idSensor=sensorSource.idSensor && sensorSource.urlFuente='"+url+"'") )
       {
            consulta.ejecutarSentencia("Insert into sensor(nombreSensor,tipoSensor,pisoSensor,alaSensor,sectorSensor,recintoSensor,areaSensor) values('"+nombre+"','"+nombre+"','"+recinto+"','N/A','N/A','N/A','N/A') ");
           res= consulta.retDatos("Select idSensor from sensor Order by IdSensor desc Limit 1");
            try {
                while (res.next()) {
                    idSensor= Integer.parseInt(res.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(capturaData.class.getName()).log(Level.SEVERE, null, ex);
            }
           consulta.ejecutarSentencia("Insert into sensorSource(urlFuente,idSensor) values('"+url+"',"+idSensor+")");

       }
       else
       {
        if(consulta.checkData("Select * from sensor,sensorSource where sensor.idSensor=sensorSource.idSensor && sensorSource.urlFuente='"+url+"'"))
            {
                System.out.println(nombre+" "+equipo);
                if(unidadEl.equals("ºC")){tipoRe="Temperatura";}
                consulta.ejecutarSentencia("Insert into detalleSensor(registroSensor,tipoRegistro,unidadRegistro,momentoRegistro,momentoIngreso,idSensor) values("+Double.parseDouble(data)+",'"+tipoRe+"','"+unidadEl+"','"+fechaHora.getNow()+"','"+fechaRegistro+"',"+idSensor+")");
            }
       }*/

       if(nombre.equals("Medidor") && !consulta.checkData("Select * from equipo,equipoSource where equipo.idEquipo=equipoSource.idEquipo && equipoSource.urlFuente='"+url+"'"))
       {
        consulta.ejecutarSentencia("Insert into equipo(nombreEquipo,urlIcono) values('"+nombre+"','N/A') ");
           res= consulta.retDatos("Select idEquipo from equipo Order by IdEquipo desc Limit 1");
            try {
                while (res.next()) {
                    idEquipo= Integer.parseInt(res.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(capturaData.class.getName()).log(Level.SEVERE, null, ex);
            }
           consulta.ejecutarSentencia("Insert into equipoSource(urlFuente,idEquipo) values('"+url+"',"+idEquipo+")");

       }
    else
        {
           if(consulta.checkData("Select * from equipo,equipoSource where equipo.idEquipo=equipoSource.idEquipo && equipoSource.urlFuente='"+url+"'"))
           {
               
                 System.out.println(nombre+" "+equipo);
                if(unidadEl.equals("KWH")){tipoRe="Consumo";}
                consulta.ejecutarSentencia("Insert into detalleEquipo(registroEquipo,tipoRegistro,unidadRegistro,momentoRegistro,momentoIngreso,idEquipo) values("+Double.parseDouble(data)+",'"+tipoRe+"','"+unidadEl+"','"+fechaHora.getNow()+"','"+fechaRegistro+"',"+idEquipo+")");
           }
        }
    }
}
