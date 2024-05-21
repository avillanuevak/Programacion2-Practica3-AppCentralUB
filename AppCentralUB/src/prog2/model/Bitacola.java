/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Albert Villanueva
 * La classe Bitacola ha de denir un atribut de tipus
 * ArrayList per guardar objectes de tipus PaginaBitacola. A més, la classe ha
 * d'implementar la interfície InBitacola i denir el mètode toString que generi
 * una representació com a String de totes les pàgines de la bitàcola separades
 * per salts de línia ('\n').
 */
public class Bitacola implements InBitacola, Serializable{
    
    /**
     *
     */
    public ArrayList<PaginaBitacola> llistaPaginaBitacola;
    
    /**
     *
     */
    public Bitacola(){
        this.llistaPaginaBitacola = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public ArrayList<PaginaBitacola> getLlistaPaginaBitacola() {
        return llistaPaginaBitacola;
    }

    /**
     *
     * @param llistaPaginaBitacola
     */
    public void setLlistaPaginaBitacola(ArrayList<PaginaBitacola> llistaPaginaBitacola) {
        this.llistaPaginaBitacola = llistaPaginaBitacola;
    }
    
    
    /**
     * Afegeix una pagina a la bitàcola.
     * @param p Objecte de tipus PaginaBitacola
     */
    @Override
    public void afegeixPagina(PaginaBitacola p){
        getLlistaPaginaBitacola().add(p);
    }
    
    /**
     * Obté una llista amb totes les pàgines d'incidències contingudes dins 
     * de la bitàcola
     * @return 
     */
    @Override
    public List<PaginaIncidencies> getIncidencies() {
        List<PaginaIncidencies> llistaIncidencies = new ArrayList<>();

        for (PaginaBitacola pb : getLlistaPaginaBitacola()) {
            if (pb instanceof PaginaIncidencies) {
                llistaIncidencies.add((PaginaIncidencies) pb);
            }
        }
        return llistaIncidencies;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        String bitacolaString = "";
        for(PaginaBitacola it : llistaPaginaBitacola) bitacolaString += it.toString() + "\n";
        
        return bitacolaString;
    }
}
