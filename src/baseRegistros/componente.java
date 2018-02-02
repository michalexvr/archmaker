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
public class componente {
    protected ArrayList<registro> registros;
    protected int id;

    public componente(ArrayList regs, int Id){
        registros = regs;
        id = Id;
    }

    public void insertar(registro reg,int indice){
        //registro reg = new registro("unidad",0.0,"01");
        for(int i=indice; i<registros.size(); i++){
            registro aux = reg;
            reg = registros.get(i);
            registros.set(i, aux);
        }
        registros.add(reg);
    }

    public void eliminar(int indice){
        registros.remove(indice);
    }

    public ArrayList getRegistros(){
        return registros;
    }

    public int getId(){
        return id;
    }

}
