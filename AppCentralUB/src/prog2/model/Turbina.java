/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import prog2.vista.CentralUBException;

/**
 *
 * @author Albert
 * La turbina converteix els graus del generador de vapor en
 * unitats de potència. Això només es podrà fer si la temperatura generada com a
 * output pel generador de vapor es superior a 100 graus (la temperatura de
 * vaporització de l'aigua).
 */
public class Turbina implements InComponent, Serializable{
    
    /**
     *
     */
    public boolean activat;
    
    /**
     *
     */
    public Turbina(){}
    
    
    /**
     * Getters i setter d'activat
     * @return 
     */
    public boolean isActivat() {
        return activat;
    }

    /**
     *
     * @param activat
     */
    public void setActivat(boolean activat) {
        this.activat = activat;
    }
    
    /**
     * Activa el component.El mètode llançarà
        una excepció en determinades situacions explicades
        a la Taula 1 de l'enunciat de la pràctica.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void activa() throws CentralUBException{
        if(!this.isActivat()) this.setActivat(true);
    }
       
    /**
     * Desactiva el component.
     */
    @Override
    public void desactiva(){
        if(this.isActivat()) this.setActivat(false); 
    }
    
    /**
     * Revisa el component. Com a resultat de la revisió, podria podria sorgir 
     * una incidència que s'ha de registrar dins d'una pàgina d'incidències.
     * @param p Objecte de tipus PaginaIncidencies.
     */
    @Override
    public void revisa (PaginaIncidencies p){
        if(!this.isActivat()) p.afegeixIncidencia("Turbina: Activat = " + this.isActivat());
    }
    
    /**
     * Obté el cost operatiu del component. El cost operatiu depèn de si el 
     * component està activat. Si no està activat el cost és zero.
     * Si està activat, tindrà un cost que es pot consultar a la Taula 1 de 
     * l'enunciat de la pràctica.
     * @return
     */
    @Override
    public float getCostOperatiu(){
        return 20f;
    }
    
    /**
     * Calcula l'output del component donat l'input. La manera de calcular
     * l'output està descrita a la Figura 2 de l'enunciat de la pràctica.
     * @param input Input que rep el component.
     * @return
     */
    @Override
    public float calculaOutput(float input){
        float output = 0;
        if(this.isActivat() && input < 100) output = 0;
        if(this.isActivat() && input >= 100) output = 2 * input;
        return output;
    }
    
}
