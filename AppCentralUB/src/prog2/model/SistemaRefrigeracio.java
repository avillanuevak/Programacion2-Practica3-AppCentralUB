/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.Iterator;
import prog2.vista.CentralUBException;


/**
 *
 * @author Albert
 * El sistema de refrigeració té com a input el calor generat pel
 * reactor i genera com a output la quantitat de calor que és capaç de
 * transportar des del reactor ns al generador de vapor. Aquesta quantitat depèn
 * de la quantitat de bombes refrigerants que estiguin activades. A la nostra
 * central hi haurà un total de 4 bombes refrigerants.
 */
public class SistemaRefrigeracio implements InComponent, Serializable{
    
    private ArrayList<BombaRefrigerant> sistemaRefrigeracio;
    
    /**
     *
     */
    public SistemaRefrigeracio(){
        this.sistemaRefrigeracio = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public ArrayList<BombaRefrigerant> getSistemaRefrigeracio() {
        return sistemaRefrigeracio;
    }

    /**
     *
     * @param sistemaRefrigeracio
     */
    public void setSistemaRefrigeracio(ArrayList<BombaRefrigerant> sistemaRefrigeracio) {
        this.sistemaRefrigeracio = sistemaRefrigeracio;
    }
    
   /**
     * Activa el component.El mètode llançarà
        una excepció en determinades situacions explicades
        a la Taula 1 de l'enunciat de la pràctica.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void activa() throws CentralUBException{
        Iterator<BombaRefrigerant> it = sistemaRefrigeracio.iterator();
        while(it.hasNext()){
            if(!it.next().getForaDeServei()) it.next().activa();
            else{throw new CentralUBException("Error: La bomba refrigerant " + it.next().getId() + " esta fora de servei.");}
        }
    }
       
    /**
     * Desactiva el component.
     */
    @Override
    public void desactiva(){
        Iterator<BombaRefrigerant> it = sistemaRefrigeracio.iterator();
        while(it.hasNext()){
            it.next().desactiva();
        }
    }
    
    /**
     * Revisa el component. Com a resultat de la revisió, podria podria sorgir 
     * una incidència que s'ha de registrar dins d'una pàgina d'incidències.
     * @param p Objecte de tipus PaginaIncidencies.
     */
    @Override
    public void revisa (PaginaIncidencies p){
        Iterator<BombaRefrigerant>it = sistemaRefrigeracio.iterator();
        while(it.hasNext()){
            it.next().revisa(p);
        }
    }
    
    /**
     * Obté el cost operatiu del component.El cost operatiu depèn de si el 
     * component està activat. Si no està activat el cost és zero.
     * Si està activat, tindrà un cost que es pot consultar a la Taula 1 de 
     * l'enunciat de la pràctica.
     * @return 
     */
    @Override
    public float getCostOperatiu(){
        float costOperatiu = 0;
        Iterator<BombaRefrigerant>it = sistemaRefrigeracio.iterator();
        while(it.hasNext()){
            if(it.next().getActivat()) costOperatiu += 125;
        }
        return costOperatiu;
    }
    
    /**
     * Calcula l'output del component donat l'input.La manera de calcular
     * l'output està descrita a la Figura 2 de l'enunciat de la pràctica.
     * @param input Input que rep el component.
     * @return 
     */
    @Override
    public float calculaOutput(float input){
        int bombesActivades = 0;
        for (BombaRefrigerant bomba : sistemaRefrigeracio) {
            if(bomba.getActivat()) bombesActivades += 1;
        }
        return (float) min(input, 250 * bombesActivades);
    }
    
    /**
     * Funcio que afegeix una bomba refrigerant al sistema de refrigeració
     * si el seu identificador no és trobat a la llista.
     * @param b 
     */
    public void afegirBomba(BombaRefrigerant b){
    boolean repetit = false;
    if (sistemaRefrigeracio.isEmpty()) {
        sistemaRefrigeracio.add(b);
    } else {
        for (BombaRefrigerant bomba : sistemaRefrigeracio) {
            if (bomba.getId() == b.getId()) {
                repetit = true;
                break;
            }
        }
        if (!repetit) {
            sistemaRefrigeracio.add(b);
        }
    }
    }
    
    /**
     *
     * @return
     */
    public int bombesActives(){
        int bombesActives = 0;
        for(BombaRefrigerant bomba : sistemaRefrigeracio){
            if(bomba.getActivat()) bombesActives += 1;
        }
        return bombesActives;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(BombaRefrigerant bomba : sistemaRefrigeracio){
            sb.append(bomba.toString());
        }
        
        return sb.toString();
    }
}
