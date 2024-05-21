/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author James Chen
 * La classe PaginaIncidencies hereta de PaginaBitacola i
 * conté una llista de String descrivint incidències, segons es va explicar a
 * l'Apartat 2.4.3.
 */
public class PaginaIncidencies extends PaginaBitacola implements Serializable{
    int dia;
    ArrayList<String> llistaIncidencies;

    /**
     *
     * @param dia
     */
    public PaginaIncidencies(int dia){
    this.llistaIncidencies = new ArrayList<>(); 
    this.dia = dia;
    }
        
    /**
     *
     * @return
     */
    public int getDia() {
        return dia;
    }

    /**
     *
     * @param dia
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getLlistaIncidencies() {
        return llistaIncidencies;
    }

    /**
     *
     * @param llistaIncidencies
     */
    public void setLlistaIncidencies(ArrayList<String> llistaIncidencies) {
        this.llistaIncidencies = llistaIncidencies;
    }
    
    /**
     *
     * @param dia
     * @param llistaIncidencies
     */
    public PaginaIncidencies(int dia, ArrayList<String> llistaIncidencies){
        this.dia = dia;
        this.llistaIncidencies = llistaIncidencies;
    }
    
    /**
     *
     * @param descIncidencia
     */
    public void afegeixIncidencia(String descIncidencia){
        llistaIncidencies.add(descIncidencia);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        String mensaje = "";
        if(llistaIncidencies.isEmpty()) return "# Pagina Incidencies\nNo hi ha incidencies";
        for(String it: llistaIncidencies){
            mensaje += "- Descripcio Incidencia: " + it + "\n";
        }
        return "# Pagina Incidencies\n- Dia: " + getDia() + "\n" + mensaje;
    }
}
