/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import prog2.vista.CentralUBException;

/**
 *
 * @author dortiz
 * La classe Dades és la classe principal del paquet del model,
 * atès que conté i gestiona totes les dades de l'aplicació. Els mètodes
 * proveïts per Dades seran utilitzades per la classe Adaptador. No hauria de
 * ser necessari crear mètodes addicionals dins de Dades a més del constructor,
 * els mètodes de InDades i els mètodes de servei declarats al codi base:
 * actualitzaEstat i actualitzaEconomia.
 */
public class Dades implements InDades, Serializable{

    /**
     *
     */
    public final static long  VAR_UNIF_SEED = 123;

    /**
     *
     */
    public final static float GUANYS_INICIALS = 0;

    /**
     *
     */
    public final static float PREU_UNITAT_POTENCIA = 1;

    /**
     *
     */
    public final static float PENALITZACIO_EXCES_POTENCIA = 200;

    // Afegir atributs:
    
    
    private VariableUniforme variableUniforme;
    private float insercioBarres;
    private Reactor reactor;
    private SistemaRefrigeracio sistemaRefrigeracio;
    private GeneradorVapor generadorVapor;
    private Turbina turbina;
    private Bitacola bitacola;
    private int dia;
    private float guanysAcumulats;
    
    /**
     *
     * @throws CentralUBException
     */
    public Dades() throws CentralUBException{
        // Inicialitza Atributs
        this.variableUniforme = new VariableUniforme(VAR_UNIF_SEED);
        this.insercioBarres = 100;
        this.reactor = new Reactor();
        this.sistemaRefrigeracio = new SistemaRefrigeracio();
        this.generadorVapor = new GeneradorVapor();
        this.generadorVapor.activa();
        this.turbina = new Turbina();
        this.turbina.activa();
        this.bitacola = new Bitacola();
        this.dia = 1;
        this.guanysAcumulats = GUANYS_INICIALS;
        
        // Afegeix bombes refrigerants
        BombaRefrigerant b0 = new BombaRefrigerant(variableUniforme, 0);
        BombaRefrigerant b1 = new BombaRefrigerant(variableUniforme, 1);
        BombaRefrigerant b2 = new BombaRefrigerant(variableUniforme, 2);
        BombaRefrigerant b3 = new BombaRefrigerant(variableUniforme, 3);
        
        this.sistemaRefrigeracio.afegirBomba(b0);
        this.sistemaRefrigeracio.afegirBomba(b1);
        this.sistemaRefrigeracio.afegirBomba(b2);
        this.sistemaRefrigeracio.afegirBomba(b3);
    }
    
    /**
     * Actualitza l'economia de la central. Genera una pàgina econòmica a 
     * partir de la demanda de potencia actual. Aquesta pàgina econòmica inclou 
     * beneficis, penalització per excès de potència, costos operatius y 
     * guanys acumulats.
     * @param demandaPotencia Demanda de potència actual.
     */
    
    
    private PaginaEconomica actualitzaEconomia(float demandaPotencia){

        /*Beneficis*/
        float output, penalitzacio = 0, beneficis;
        float outputRaw = turbina.calculaOutput(demandaPotencia);
        
        output = (outputRaw >= demandaPotencia) ? demandaPotencia : outputRaw;
        beneficis = output * PREU_UNITAT_POTENCIA;
        
        /*Penalitzacio*/
        if(output > demandaPotencia) penalitzacio = PENALITZACIO_EXCES_POTENCIA;
        
        /*Costos Operatius*/
        float costosOperatius = 0;
        
        if(reactor.isActivat()) costosOperatius += reactor.getCostOperatiu();
        if(generadorVapor.isActivat()) costosOperatius += generadorVapor.getCostOperatiu();
        if(turbina.isActivat()) costosOperatius += turbina.getCostOperatiu();
        costosOperatius += sistemaRefrigeracio.getCostOperatiu();
                
        
        /*Guanys Acumulats*/
        guanysAcumulats += beneficis - penalitzacio - costosOperatius;
        
        /*Crear pagina economica*/
        PaginaEconomica paginaEconomica = new PaginaEconomica(dia, beneficis, costosOperatius, penalitzacio, guanysAcumulats);
        
        return paginaEconomica;
    }
    
    /**
     * Actualitza l'estat de la central. El mètodo ha de establir la nova
     * temperatura del reactor i revisar els components de la central. Si
     * es troben incidències, s'han de registrar en la pàgina d'incidències
     * que es proporciona com a paràmetre d'entrada.
     * @param paginaIncidencies Pàgina d'incidències.
     */
    private void actualitzaEstatCentral(PaginaIncidencies paginaIncidencies) {
        /*Establir nova temperatura del reactor*/
        float novaTemp;
        if(reactor.getTemperaturaReactor() - 250 * sistemaRefrigeracio.bombesActives() < 30f) novaTemp = 30f;
        else{
            novaTemp = reactor.getTemperaturaReactor() - 250 * sistemaRefrigeracio.bombesActives();
            reactor.setTemperaturaReactor(novaTemp);
        }

        /*Revisar Components central*/
        reactor.revisa(paginaIncidencies);
        sistemaRefrigeracio.revisa(paginaIncidencies);
        generadorVapor.revisa(paginaIncidencies);
        turbina.revisa(paginaIncidencies);   
    }
    
    /**
     *
     * @param demandaPotencia
     * @return
     */
    @Override
    public Bitacola finalitzaDia(float demandaPotencia) {
        // Actualitza economia
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);
        
        // Actualitza estat central i registra incidencies
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        actualitzaEstatCentral(paginaIncidencies);
        
        // Genera pàgina d'estat
        PaginaEstat paginaEstat = mostraEstat(demandaPotencia);
        
        // Incrementa dia
        dia += 1;
        
        // Guarda pàgines de bitacola
        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);
        
        // Retorna pàgines
        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);
        return bitacolaDia;
    }

    
    /**
     * Retorna el grau d'inserció de les barres de control en percentatge.
     * @return 
     */
    @Override
    public float getInsercioBarres(){
        return insercioBarres;
    }
    
    /**
     * Estableix el grau d'inserció de les barres de control en percentatge.
     * @param insercioBarres Percentatge d'inserció de les barres de control.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void setInsercioBarres(float insercioBarres) throws CentralUBException{
        this.insercioBarres = insercioBarres;
        reactor.setInsercioBarres(insercioBarres);           
    }
     
    /**
     * Activa el reactor de la central.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void activaReactor() throws CentralUBException{
        reactor.activa();
    }

    /**
     * Desactiva el reactor de la central.
     */
    @Override
    public void desactivaReactor(){
        reactor.desactiva();
    }
    
    /**
     * Retorna l'objecte que contè el reactor de la central.
     * @return 
     */
    @Override
    public Reactor mostraReactor(){
        return reactor;
    }
    
    /**
     * Activa una bomba refrigerant amb Id donat com a paràmetre.
     * @param id Identificador de la bomba que es vol activar.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void activaBomba(int id) throws CentralUBException{
        for(BombaRefrigerant b : sistemaRefrigeracio.getSistemaRefrigeracio()){
            if(b.getId() == id) b.activa();
        }
    }
    
    /**
     * Desactiva una bomba refrigerant amb Id donat com a paràmetre.
     * @param id Identificador de la bomba que es vol activar.
     */
    @Override
    public void desactivaBomba(int id){
        for(BombaRefrigerant b : sistemaRefrigeracio.getSistemaRefrigeracio()){
            if(b.getId() == id) b.desactiva();
        }
    }
    
    /**
     * Retorna l'objecte que contè el sistema de refrigeració de la central.
     * @return 
     */
    @Override
    public SistemaRefrigeracio mostraSistemaRefrigeracio(){
        return sistemaRefrigeracio;
    }
    
    /**
     * Retorna la potència generada per la central. Aquesta potència es 
     * l'output de la turbina. Es pot consultar la Figura 2 a l'enunciat per
     * veure els detalls.
     * @return
     */
    @Override
    public float calculaPotencia(){
        return turbina.calculaOutput(generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(getInsercioBarres()))));
    }
    
    /**
     * Retorna una pàgina de estat per a la configuració actual de la central.
     * Amb aquest propòsit és necessari coneixer la demanda de potència actual.
     * @param demandaPotencia Demanda de potència actual.
     * @return
     */
    @Override
    public PaginaEstat mostraEstat(float demandaPotencia){
        float outReactor = reactor.calculaOutput(getInsercioBarres());
        float outRefrigeracio = sistemaRefrigeracio.calculaOutput(outReactor);
        float outGeneradorVapor = generadorVapor.calculaOutput(outRefrigeracio);
        float outTurbina = turbina.calculaOutput(outGeneradorVapor);
        
        PaginaEstat paginaEstat = new PaginaEstat(dia, demandaPotencia, getInsercioBarres(), outReactor,
                                outRefrigeracio, outGeneradorVapor, outTurbina);
                                
        return paginaEstat;
    }
      
    /**
     * Retorna la bitacola de la central.
     * @return
     */
    @Override
    public Bitacola mostraBitacola(){
           return this.bitacola;
    }
    
    /**
     * Retorna una llista amb totes les pàgines d'incidències de la bitàcola de
     * la central.
     * @return
     */
@Override
    public List<PaginaIncidencies> mostraIncidencies() {
        List<PaginaIncidencies> llistaPaginaIncidencies = new ArrayList<>(); 
        if (bitacola.getIncidencies() == null) return llistaPaginaIncidencies;
        else {
            for (PaginaIncidencies pi : bitacola.getIncidencies()) {
                llistaPaginaIncidencies.add(pi);
            }
        }

        return llistaPaginaIncidencies;
    }
    
    /**
     *
     * @return
     */
    public int mostraDia(){
        return this.dia;
    }
}