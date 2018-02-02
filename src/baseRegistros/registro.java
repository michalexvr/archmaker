/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseRegistros;

/**
 *
 * @author michael
 */
public class registro {
    protected String unidad;
    protected double valor;
    protected String momento;

    public registro(String Unidad, double Valor, String Momento){
        unidad = Unidad;
        valor = Valor;
        momento = Momento;
    }

    public String getUnidad(){
        return unidad;
    }

    public double getValor(){
        return valor;
    }

    public String getMomento(){
        return momento;
    }
}
