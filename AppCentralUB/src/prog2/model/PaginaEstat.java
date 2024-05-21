/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;

/**
 *
 * @author Albert Villanueva
 * La classe PaginaEstat hereta de PaginaBitacola i ha
 * de tenir el atributs necessaris segons el que es va a explicar a l'Apartat
 * 2.4.2. El atributs s'han d'inicialitzar usant el constructor. La demanda de
 * potència satisfeta es pot calcular a partir de la resta d'atributs.
 */
public class PaginaEstat extends PaginaBitacola implements Serializable{

    int dia;
    float demandaPotEnergetica;
    float insercioBarres;
    float oReactor;
    float oSistemaRefrigeracio;
    float oGeneradorVapor;
    float oTurbina;
    float demandaSatisfeta; 

    /**
     *
     */
    public PaginaEstat() {}

    /**
     *
     * @param dia
     * @param demandaPotEnergetica
     * @param insercioBarres
     * @param oReactor
     * @param oSistemaRefrigeracio
     * @param oGeneradorVapor
     * @param oTurbina
     */
    public PaginaEstat(int dia, float demandaPotEnergetica, float insercioBarres, float oReactor, float oSistemaRefrigeracio, float oGeneradorVapor, float oTurbina) {
        this.dia = dia;
        this.demandaPotEnergetica = demandaPotEnergetica;
        this.insercioBarres = insercioBarres;
        this.oReactor = oReactor;
        this.oSistemaRefrigeracio = oSistemaRefrigeracio;
        this.oGeneradorVapor = oGeneradorVapor;
        this.oTurbina = oTurbina;
        this.demandaSatisfeta = (oTurbina/demandaPotEnergetica) * 100 ;
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
    public float getDemandaPotEnergetica() {
        return demandaPotEnergetica;
    }

    /**
     *
     * @param demandaPotEnergetica
     */
    public void setDemandaPotEnergetica(float demandaPotEnergetica) {
        this.demandaPotEnergetica = demandaPotEnergetica;
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
    public float getoReactor() {
        return oReactor;
    }

    /**
     *
     * @param oReactor
     */
    public void setoReactor(float oReactor) {
        this.oReactor = oReactor;
    }

    /**
     *
     * @return
     */
    public float getoSistemaRefrigeracio() {
        return oSistemaRefrigeracio;
    }

    /**
     *
     * @param oSistemaRefrigeracio
     */
    public void setoSistemaRefrigeracio(float oSistemaRefrigeracio) {
        this.oSistemaRefrigeracio = oSistemaRefrigeracio;
    }

    /**
     *
     * @return
     */
    public float getoGeneradorVapor() {
        return oGeneradorVapor;
    }

    /**
     *
     * @param oGeneradorVapor
     */
    public void setoGeneradorVapor(float oGeneradorVapor) {
        this.oGeneradorVapor = oGeneradorVapor;
    }

    /**
     *
     * @return
     */
    public float getoTurbina() {
        return oTurbina;
    }

    /**
     *
     * @param oTurbina
     */
    public void setoTurbina(float oTurbina) {
        this.oTurbina = oTurbina;
    }

    /**
     *
     * @return
     */
    public float getDemandaSatisfeta() {
        return demandaSatisfeta;
    }

    /**
     *
     * @param demandaSatisfeta
     */
    public void setDemandaSatisfeta(float demandaSatisfeta) {
        this.demandaSatisfeta = demandaSatisfeta;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return """
               # Pagina Estat
               - Dia: """ + getDia() +"\n"
                + "- Demanda de potencia: " + getDemandaPotEnergetica()+ "\n"
                + "- Insercio Barres: " + getInsercioBarres() + "%\n"
                + "- Output Reactor: "+ getoReactor() +" Graus\n"
                + "- Output Sistema de Refrigeracio: "+ getoSistemaRefrigeracio() +" Graus\n"
                + "- Output Generador de Vapor: "+ getoGeneradorVapor() +" Graus\n"
                + "- Output Turbina: "+ getoTurbina() +" Unitats de Potencia\n"
                + "- Demanda de Potencia Satisfeta: " + getDemandaSatisfeta() +" %\n";
    }
}
