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
 * El generador de vapor transmet el calor transportat
 * pel sistema de refrigeraci ó (input) ns al circuit secundari (output) amb una
 * determinada eficiència, xada en 0.8 al nostre cas. Es considera 30◦ la
 * temperatura ambient.
 */
public class GeneradorVapor implements InComponent, Serializable{
    private boolean activat;
    
    /**
     *
     */
    public GeneradorVapor(){}
    
    
    /**
     * Getters i setters d'activat
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
        if(!this.isActivat()) setActivat(true);
    }
       
    /**
     * Desactiva el component.
     */
    @Override
    public void desactiva(){
        if(this.isActivat()) setActivat(false);
    }
    
    /**
     * Revisa el component. Com a resultat de la revisió, podria podria sorgir 
     * una incidència que s'ha de registrar dins d'una pàgina d'incidències.
     * @param p Objecte de tipus PaginaIncidencies.
     */
    @Override
    public void revisa (PaginaIncidencies p){
        if(!this.isActivat()) p.afegeixIncidencia("Generador vapor: Activat = " + this.isActivat());
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
        return 25f;
    }
    
    /**
     * Calcula l'output del component donat l'input. La manera de calcular
     * l'output està descrita a la Figura 2 de l'enunciat de la pràctica.
     * @param input Input que rep el component.
     * @return
     */
    @Override
    public float calculaOutput(float input){
        float output;
        if(this.isActivat()) output = input * 0.8f;
        else{ output = 30f; }
        return output;
    }
}
