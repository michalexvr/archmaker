/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseRegistros;

import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class test {

    public static void main(String argc[]){

        String Fecha = "aaaa-mm-dd hh:mm:ss";

        System.out.println(Fecha.substring(0, 16));

/*        ArrayList<registro> asdf = new ArrayList();
        asdf.add(new registro("unidad",0.2,"00"));
        asdf.add(new registro("unidad",0.3,"01"));
        asdf.add(new registro("unidad",0.4,"02"));

        for(int i=0; i<asdf.size(); i++){
            System.out.println(asdf.get(i).getValor());
        }

        registro reg = new registro("unidad",0.0,"01");
        for(int i=0; i<asdf.size(); i++){
            registro aux = reg;
            reg = asdf.get(i);
            asdf.set(i, aux);
        }
        asdf.add(reg);

        System.out.println("******************");
        for(int i=0; i<asdf.size(); i++){
            System.out.println(asdf.get(i).getValor());
        }*/

    }
/*
    public ArrayList intercalar(registro reg,int indice){
        //registro reg = new registro("unidad",0.0,"01");
        for(int i=indice; i<asdf.size(); i++){
            registro aux = reg;
            reg = asdf.get(i);
            asdf.set(i, aux);
        }
        asdf.add(reg);
    }*/
}
