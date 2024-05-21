/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;

/**
 *
 * @author Albert Villanueva
 * Aquesta classe també hereta de PaginaBitacola i ha
 * de tenir el atributs necessaris segons el que es va a explicar a l'Apartat
 * 2.4.1. El atributs s'han d'inicialitzar usant el constructor.
 */
public class PaginaEconomica extends PaginaBitacola implements Serializable{
    private int dia;
    private float beneficis, costOperatiu, penalitzacioExcessProduccio, guanysAcumulats;
    
    /**
     *
     * @param dia
     * @param beneficis
     * @param costOperatiu
     * @param penalitzacioExcessProduccio
     * @param guanysAcumulats
     */
    public PaginaEconomica(int dia, float beneficis, float costOperatiu, float penalitzacioExcessProduccio, float guanysAcumulats){
        this.dia = dia;
        this.beneficis = beneficis;
        this.costOperatiu = costOperatiu;
        this.penalitzacioExcessProduccio = penalitzacioExcessProduccio;
        this.guanysAcumulats = guanysAcumulats;
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
     * @return
     */
    public float getBeneficis() {
        return beneficis;
    }

    /**
     *
     * @return
     */
    public float getCostOperatiu() {
        return costOperatiu;
    }

    /**
     *
     * @return
     */
    public float getPenalitzacioExcessProduccio() {
        return penalitzacioExcessProduccio;
    }

    /**
     *
     * @return
     */
    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return """
               # Pagina Economica
               - Dia: """ + getDia() +
               "\n- Beneficis: " + getBeneficis() + " Unitats Economiques" +
               "\n- Penalitzacio Excess Produccio: " + getPenalitzacioExcessProduccio() + " Unitats Economiques" +
               "\n- Cost Operatiu: " + getCostOperatiu() + " Unitats Economiques" +
               "\n- Guanys Acumulats: " + getGuanysAcumulats() + " Unitats Economiques\n";
    }
}
