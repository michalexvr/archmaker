/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;
import Utils.fechaHora;
import archmakertransoceanica.*;

/**
 *
 * @author michael
 */
public class fechaUtil {

    public static String getNombreMes(int indice){
        String meses[] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        if(indice >0 && indice <=12)
            return meses[indice-1];
        return null;
    }

    public static String getNumeroMes(String mes){
        String meses[] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        for(int i=0; i<meses.length; i++){
            if(meses[i].equals(mes)){
                int indice = i+1;
                if(indice<10) return "0"+indice;
                else return Integer.toString(indice);
            }
        }
        return null;
    }

    public static String invertir(String fecha){
        int i = fecha.indexOf("-");
        int l = fecha.lastIndexOf("-");

        if(i>0 && l>0 && i<l)
            return(fecha.substring(l+1)+"-"+fecha.substring(i+1,l)+"-"+fecha.substring(0,i));
        return null;
    }

    public static long getFechaToSegundos(String fecha)
    {
        long ano = Long.parseLong(fecha.substring(0, fecha.indexOf("-")));
        int mes = Integer.parseInt(fecha.substring(fecha.indexOf("-")+1, fecha.lastIndexOf("-")));
        int dia = Integer.parseInt(fecha.substring(fecha.lastIndexOf("-")+1));

        System.out.println("anio: "+(ano*365*24*3600));
        return (dia*24*3600)+getMesToSegundos(mes)+(ano*365*24*3600);
    }

    public static long getMesToSegundos(int mes)
    {
        int meses[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        long sum = 0;
        mes = mes - 1;
        if(mes > 0)
         {
           for(int i=mes-1; i>=0; i--) sum+=meses[i];
         }
        System.out.println("mes: "+sum*24*3600);
        return sum*24*3600;
    }

    public static String getMonthNext(String mes)
    {
        String nextMes;
        if(Integer.parseInt(getNumeroMes(getNombreMes(Integer.parseInt(mes))))<12)
        {
            nextMes=getNumeroMes(getNombreMes(Integer.parseInt(mes)+1));
        }
        else
        {
            nextMes=getNumeroMes("Enero");
        }
        return nextMes;
    }
    public static String getDayNext(String dia)
    {
        String nextDay = null;
         if(Integer.parseInt(dia)<getNumDayTheMonth(fechaHora.getMonthNow()))
         {
            nextDay= Integer.toString(Integer.parseInt(dia)+1);
         }
         else
         {
           nextDay=dia;
         }

        return nextDay;
    }
    public static String getDateBack(String fecha)
    {
        String backDay=null,fechabackDay=null;
        String anio= fecha.substring(0,4);
        String mes= fecha.substring(5,7);
        String dia= fecha.substring(8,10);
        if(Integer.parseInt(dia)<=getNumDayTheMonth(mes))
        {
            if(Integer.parseInt(dia)<10)
                {
                    if(Integer.parseInt(dia)>1)
                    {
                        if(Integer.parseInt(dia)==9)
                        {
                            backDay=Integer.toString(Integer.parseInt(dia)-1);
                            fechabackDay=anio+"-"+mes+"-"+backDay;
                        }
                        else
                        {
                            backDay="0"+Integer.toString(Integer.parseInt(dia)-1);
                            fechabackDay=anio+"-"+mes+"-"+backDay;
                        }
                    }else{ fechabackDay=fecha;}
                }
            else
                {
                    backDay=Integer.toString(Integer.parseInt(dia)-1);
                    fechabackDay=anio+"-"+mes+"-"+backDay;
                }
        }
        return fechabackDay;
    }
    public static String getDateFirtMonth(String mes,String anio)
    {
        String firtdate= anio+"-"+mes+"-"+"01";
        return firtdate;
    }
    public static String getDateNext(String fecha)
    {
        String backDay=null,fechabackDay=null;
        String anio= fecha.substring(0,4);
        String mes= fecha.substring(5,7);
        String dia= fecha.substring(8,10);
        if(Integer.parseInt(dia)<getNumDayTheMonth(mes))
        {
            if(Integer.parseInt(dia)<10)
                {
                    if(Integer.parseInt(dia)==9)
                    {
                        backDay=Integer.toString(Integer.parseInt(dia)+1);
                        fechabackDay=anio+"-"+mes+"-"+backDay;
                    }
                    else
                    {
                        backDay="0"+Integer.toString(Integer.parseInt(dia)+1);
                        fechabackDay=anio+"-"+mes+"-"+backDay;
                    }
                }
            else
                {
                    backDay=Integer.toString(Integer.parseInt(dia)+1);
                    fechabackDay=anio+"-"+mes+"-"+backDay;
                }
        }
        return fechabackDay;
    }
    public static int getNumDayTheMonth(String mes)
    {
        int meses[] = {31,28,31,30,31,30,31,31,30,31,30,31};

        return meses[Integer.parseInt(mes)-1];
    }
    public static String getYearNow(String fechaNow)
    {
        String anio= fechaNow.substring(0,4);
        return anio;
    }
public static String[] getFechasTheStop(String fechaNow)
{
    String fecha[] = new String[5];
    String anio= fechaNow.substring(0,4);
    String mes= fechaNow.substring(5,7);
    String dia= fechaNow.substring(8,10);
    
    if(Integer.parseInt(dia)>1)
    {
        fecha[0]=anio+"-"+mes+"-"+"01";
        fecha[1]=anio+"-"+mes+"-"+"06";
    }
    if(Integer.parseInt(dia)>6)
    {
        fecha[2]=anio+"-"+mes+"-"+"12";
    }
    if(Integer.parseInt(dia)>12)
    {
        fecha[3]=anio+"-"+mes+"-"+"18";
    }
    if(Integer.parseInt(dia)>18)
    {
        fecha[4]=anio+"-"+mes+"-"+"24";
    }
    if(Integer.parseInt(dia)>24)
    {
        fecha[5]=anio+"-"+mes+"-"+getNumDayTheMonth(mes);
    }
    return fecha;
}
    public static void main(String argv[]){
        //System.out.println("2011-02-02: "+getFechaToSegundos("2011-02-02"));
        //System.out.println("2011-01-22: "+getFechaToSegundos("2011-01-22"));
        //System.out.println("Diferencia en segundos: "+(getFechaToSegundos("2011-02-02")-getFechaToSegundos("2011-01-22")));
        //long a1=getFechaToSegundos("2011-03-29");
        //long a2=a1+(20*3600)+(41*60)+36;
        //a1 = a1+(20*3600)+(51*60)+49;
        //System.out.println(getYearNow(fechaHora.getNow()));
        if("2011-06-15".compareTo("2011-06-14")>0)
        {
            System.out.println("Es mayor la primera fecha");
        }
        
            System.out.println(getDateFirtMonth("05","2011"));
        
        //System.out.println(a1-a2);
        /*
        Double d1=10.16;
        Double d2=10.15;
        
        Double res = ((a1*d1)-(a2*d2))/(a1-a2);

        System.out.println(a1*d1);
        System.out.println(a2*d2);
        System.out.println((a1*d1)-(a2*d2));        

        System.out.println(res);
        System.out.println(res.longValue());
         * */

         //System.out.println(fechaUtil.getNombreMes(02));
         //System.out.println(fechaUtil.getNumeroMes("Mayo"));


    }

}
