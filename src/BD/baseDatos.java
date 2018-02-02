/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BD;

/**
 *
 * @author michael venegas Destin Chile Ltda.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author michael
 */
public class baseDatos {
    protected String bd;
    protected String login;
    protected String password;
    protected String url;

    public baseDatos(){
        bd = "reportSisT";
        login = "root";
        password = "kuelule";
        url = "jdbc:mysql://localhost/"+bd;
    }

    public baseDatos(String base, String usuario, String pass, String link){
        bd = base;
        login = usuario;
        password = pass;
        url = link;
    }

   public ResultSet retDatos(String consulta) throws Exception {{

        Connection conn = null;

        try {

             Class.forName("com.mysql.jdbc.Driver").newInstance();

             conn = DriverManager.getConnection(url,login,password);
             if (conn != null) {
             //System.out.println("Conexión a base de datos "+url+" ... Ok");

             Statement st = conn.createStatement();

             ResultSet res = st.executeQuery(consulta);


             //conn.close();
             return res;
         }
      }
      catch(SQLException ex) {
         System.out.println("Hubo un problema en retDatos() al intentar conectarse con la base de datos "+url+": "+ex);
         //logFile.addReg("Error de conexion en retDatos: "+ex);
      }
      catch(ClassNotFoundException ex) {
         System.out.println(ex);
      }
      return null;
    }
   }


   public int contarDatos(String consulta) throws Exception {{

       int lectura;

        Connection conn = null;

        try {

             Class.forName("com.mysql.jdbc.Driver").newInstance();

             conn = DriverManager.getConnection(url,login,password);

             if (conn != null) {
             //.out.println("Conexión a base de datos "+url+" ... Ok");

             Statement st = conn.createStatement();
             //ResultSet res = st.executeQuery("SELECT * FROM Horario");
             ResultSet res = st.executeQuery(consulta);
             int i=1;
             lectura=0;
             while(res.next()){
                lectura=Integer.parseInt(res.getString(i));//res.getString("Num_Sala")+"\n";
                //System.out.println(lectura);
                i++;
             }

             //lectura=res.getInt(1);



             conn.close();
             return lectura;
         }
      }
      catch(SQLException ex) {
         System.out.println("Hubo un problema en contarDatos() al intentar conectarse con la base de datos "+url);
         System.out.println("el error es este: "+ex);
         //logFile.addReg("Error en contarDatos: "+ex);
      }
      catch(ClassNotFoundException ex) {
         System.out.println(ex);
      }
      return 0;
    }
   }


   public String devDatos(String consulta, int campos) throws Exception {{

       String lectura=new String();

        Connection conn = null;

           try {

             Class.forName("com.mysql.jdbc.Driver").newInstance();

             conn = DriverManager.getConnection(url,login,password);

             if (conn != null) {
             //System.out.println("Conexión a base de datos "+url+" ... Ok");

             Statement st = conn.createStatement();
             //ResultSet res = st.executeQuery("SELECT * FROM Horario");

            ResultSet res1;
            
            res1 = st.executeQuery(consulta);
            while(res1.next()){
                for(int i=0; i<campos; i++){
                    lectura+=res1.getString(i)+",";
                }
                lectura+="\n";
            }
             conn.close();
             return lectura;
         }
      }
      catch(SQLException ex) {
         System.out.println("Hubo un problema en devDatos al intentar conectarse con la base de datos "+url);
         //logFile.addReg("Error en devDatos: "+ex);
      }
      catch(ClassNotFoundException ex) {
         System.out.println(ex);
      }
      return null;
    }
   }

      public ArrayList devArrayList(String consulta, String campo) throws Exception {{

        //String lectura=new String();
        ArrayList rl = new ArrayList();
        Connection conn = null;

        //if(campo.compareTo("Nom_Asignatura")==0 || campo.compareTo("Cod_Asignatura")==0)
        //combo.addItem("Liberar esta sala");

        try {

             Class.forName("com.mysql.jdbc.Driver").newInstance();

             conn = DriverManager.getConnection(url,login,password);

             if (conn != null) {
             //System.out.println("Conexión a base de datos "+url+" ... Ok");

             Statement st = conn.createStatement();
             //ResultSet res = st.executeQuery("SELECT * FROM Horario");

             ResultSet res = st.executeQuery(consulta);
             //int i=0;
             while(res.next()){
                //lectura+=res.getString("Cod_Asignatura")+" : "+res.getString("Nom_Asignatura")+".\n";
                rl.add(res.getString(campo));//combo.addItem(res.getString(campo));//.add(res.getString("Nom_Asignatura"));
             }


             conn.close();
             return rl;
         }
      }
      catch(SQLException ex) {
         System.out.println("Hubo un problema en devArrayList al intentar conectarse con la base de datos "+url);
         //logFile.addReg("Error de devArrayList: "+ex);
      }
      catch(ClassNotFoundException ex) {
         System.out.println(ex);
      }
      return null;
    }
   }

   public String ejecutarSentencia(String consulta) throws Exception {{

        String error="ok";

        Connection conn = null;

        try {

             Class.forName("com.mysql.jdbc.Driver").newInstance();

             conn = DriverManager.getConnection(url,login,password);

             if (conn != null) {
             //System.out.println("Conexión a base de datos "+url+" ... Ok");

             Statement st = conn.createStatement();
             //ResultSet res = st.executeQuery("SELECT * FROM Horario");
             st.execute(consulta);

             conn.close();

             }
      }catch(SQLException ex) {
         System.out.println("Hubo un problema en ejecutarSentencia() al intentar conectarse con la base de datos "+url);
         System.out.println("el error es este: "+ex);
         //logFile.addReg("Error de ejecucion SQL: "+ex);
         error = ex.getMessage();
      }
      catch(ClassNotFoundException ex) {
         System.out.println(ex);
      }
      return error;
    }
   }

}
