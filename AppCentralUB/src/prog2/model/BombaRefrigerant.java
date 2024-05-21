/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import prog2.vista.CentralUBException;

/**
 *
 * @author Albert Villanueva
 * La classe BombaRefrigerant ha d'implementar la
 * interfície InBombaRefrigerant. Cada bomba refrigerant ha de tenir un identi
 * cador numèric i dos booleans per indicar si està activada o no i si està fora
 * de servei. El constructor de la classe ha de rebre l'identicador i un
 * objecte de tipus VariableUniforme.
 */
public class BombaRefrigerant implements InBombaRefrigerant, Serializable{
    
    private final VariableUniforme variableUniforme;
    private final int id;
    private boolean activada, foraDeServei;
    
    /**
     *
     * @param variableUniforme
     * @param id
     */
    public BombaRefrigerant(VariableUniforme variableUniforme, int id){
        this.variableUniforme = variableUniforme;
        this.id = id;
    }
    
    /**
     * Mètode getter que retorna l'id de la bomba refrigerant
     * @return 
     */
    @Override
    public int getId(){
        return id;
    }
    
    
    /**
     * Activa la bomba refrigerant.El mètode llançarà una excepció si la bomba 
        està fora de servei i en aquest cas la bomba no es podrà activar.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void activa() throws CentralUBException{
        if(!foraDeServei){
            if(activada) throw new CentralUBException("Error: La bomba que s'intenta activar ja esta activada.");
            else{
                this.activada = true;
            }
        }
        else{
            throw new CentralUBException("Error: La bomba esta fora de servei.");
        }
    }
    
    /**
     * Desactiva la bomba refrigerant.
     */
    @Override
    public void desactiva(){
        if(activada) this.activada = false;
    }
    
    /**
     * Retorna true si la bomba refrigerant està activada.
     * @return 
     */
    @Override
    public boolean getActivat(){
        return activada;
    }
    
    /**
     * Revisa la bomba refrigerant. Es farà servir l'objecte de tipus 
     * VariableUniforme per determinar si la bomba es queda fora de servei. En 
     * cas afirmatiu, s'haurà de registrar la situació dins d'una pàgina 
     * d'incidències.
     * @param p Objecte de tipus PaginaIncidencies per a registrar si la bomba 
     * es queda fora de servei.
     */
    @Override
    public void revisa (PaginaIncidencies p){
        if(variableUniforme.seguentValor() < 20){
            desactiva();
            p.afegeixIncidencia("Bomba refrigerant. ID: " + getId() + ", Activada: " + getActivat() + ", En servei: " + getForaDeServei());
        }
    }
    
    /**
     * Retorna true si la bomba refrigerant està fora de servei.
     * @return 
     */
    @Override
    public boolean getForaDeServei(){
        return foraDeServei;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return "- Bomba Refrigerant: Id = " + this.getId() + ", Activitat = " + this.getActivat() + ", Fora de servei = " + this.getForaDeServei() + "\n";
    }
}
