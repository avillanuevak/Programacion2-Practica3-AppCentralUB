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
 * El reactor rep com a input el grau d'inserció de
 * les barres de control en percentatge i genera com a output una determinada
 * quantitat de graus Celsius que es transmet a l'aigua del circuit primari.
 */
public class Reactor implements InComponent, Serializable{
    
    private float temperaturaReactor;
    private boolean activat;
    private float insercioBarres;
    
    /**
     *
     */
    public Reactor(){}

    /**
     * Getters i setters de les variables de reactor
     * 
     * @param temperaturaReactor
     */
    public void setTemperaturaReactor(float temperaturaReactor) {
        this.temperaturaReactor = temperaturaReactor;
    }
    
    /**
     *
     * @return
     */
    public float getTemperaturaReactor() {
        return temperaturaReactor;
    }

    /**
     *
     * @return
     */
    public float getInsercioBarres() {
        return insercioBarres;
    }

    /**
     *
     * @param insercioBarres
     */
    public void setInsercioBarres(float insercioBarres) {
        this.insercioBarres = insercioBarres;
    }
    
    /**
     *
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
        if(getTemperaturaReactor() > 1000f){
            throw new CentralUBException("Error: No es pot activar el reactor mentre tingui una temperatura superior a 1000 graus.");        
        }
        else{
            setActivat(true);
        }
    }
    
    /**
     * Desactiva el component.
     */
    @Override
    public void desactiva(){
        setActivat(false);
    }
    /**
     * Revisa el component. Com a resultat de la revisió, podria podria sorgir 
     * una incidència que s'ha de registrar dins d'una pàgina d'incidències.
     * @param p Objecte de tipus PaginaIncidencies.
     */
    @Override
    public void revisa (PaginaIncidencies p){
        if(calculaOutput(insercioBarres) > 1000f){
            this.desactiva();
            p.afegeixIncidencia("Reactor: Activat = " + isActivat() + ", Temperatura: " + getTemperaturaReactor() + ", Insercio Barres = " + 
                    getInsercioBarres());
        }
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
        float costOperatiu = 0;
        if(this.isActivat()) costOperatiu += 30;
        return costOperatiu;
    }
    
    /**
     * Calcula l'output del component donat l'input. La manera de calcular
     * l'output està descrita a la Figura 2 de l'enunciat de la pràctica.
     * @param input Input que rep el component. Grau d'insercio barres
     * @return
     */
    @Override
    public float calculaOutput(float input){
        if(0f > input || 100f < input) try {
            throw new CentralUBException("Error: El grau d'insercio de barres ha de ser un percentatge entre 0 i 100.");
        } catch (CentralUBException ex) {
            ex.getMessage();
        }
        float output;
        if(!this.isActivat()) output = getTemperaturaReactor();
        else{
            output = (getTemperaturaReactor() + (100 - input) * 10);
        }
        return output;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Reactor:" + " Insercio de barres=" + getInsercioBarres() + ", TemperaturaReactor=" + getTemperaturaReactor() + ", Activat=" + isActivat();
    }       
}
