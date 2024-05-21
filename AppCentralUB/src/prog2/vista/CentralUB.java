/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;
import java.io.Serializable;
import java.util.Scanner;
import prog2.adaptador.Adaptador;

/**
 *
 * @author dortiz
 * CentralUB: aquesta classe dona accés a tota la funcionalitat
 * de la central nuclear. Es proporciona només el codi amb algunes constants i
 * la funcionalitat necessària per a nalitzar un dia i obtenir de manera
 * aleatòria la demanda de potència elèctrica del següent.
 */
public class CentralUB implements Serializable{


    public final static float DEMANDA_MAX = 1600;
    public final static float DEMANDA_MIN = 200;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 600;
    public final static long VAR_NORM_SEED = 123;
    
    
    /** Generador aleatori de la demanda de potència **/
    private VariableNormal variableNormal;
    
    /** Demanda de potència del dia actual **/
    private float demandaPotencia;
    
    /*Variable de classe adaptador*/
    private Adaptador adaptador;
    /* Constructor*/

    /**
     *
     */

    public CentralUB() {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();
        
        try {
            // Afegir codi adicional si fos necessari:
            this.adaptador = new Adaptador();
        } catch (CentralUBException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        System.out.println("La demanda de potencia electrica avui es de " + demandaPotencia + " unitats");

        // Completar
        System.out.println("MENU PRINCIPAL");
        
        Scanner sc = new Scanner(System.in);
        enum MenuPrincipal{OP1, OP2, OP3, OP4, OP5, OP6, OP7, OP8, OP9, OPSORTIR}
        MenuPrincipal[] opcionsPrincipals = MenuPrincipal.values(); //Converteix l'enum en un array de constants
        Menu<MenuPrincipal> menu = new Menu<>("MENU PRINCIPAL",opcionsPrincipals); //Defineix el menu amb el nom d'estacio i l'array d'opcions
        
        String[] descripcionsPrincipal = {
                                               "Gestio Barres de Control",
                                               "Gestio Reactor",
                                               "Gestio Sistema Refrigeracio", 
                                               "Mostrar Estat Central",
                                               "Mostrar Bitacola", 
                                               "Mostrar Incidencies", 
                                               "Finalitzar Dia", 
                                               "Guardar Dades", 
                                               "Carrega Dades", 
                                               "Sortir"};
               
        
        
        menu.setDescripcions(descripcionsPrincipal);
        MenuPrincipal op;
        do{
            menu.mostrarMenu(); //Mostrar menu
            op = menu.getOpcio(sc);
            
            switch(op){
                
                case OP1:
                    subMenuBarres(sc);
                    break;
                
                case OP2:
                    subMenuReactor(sc);
                    break;
                
                case OP3:
                    subMenuRefrigeracio(sc);
                    break;
                    
                case OP4:
                    try{
                        System.out.println(adaptador.mostrarEstatCentral(demandaPotencia));
                    }catch (CentralUBException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                    
                case OP5:
                    try{
                        System.out.println(adaptador.mostrarBitacola());
                    }catch (CentralUBException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                    
                case OP6:
                    try{
                        System.out.println(adaptador.mostrarIncidencias());
                    }catch (CentralUBException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                    
                case OP7:
                    finalitzaDia();
                    break;
                    
                case OP8:
                    try{
                        adaptador.guardaDades("CentralUB.dat");

                    }catch (CentralUBException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                    
                case OP9:
                    try{
                        adaptador.carregaDades("CentralUB.dat");
                    }catch (CentralUBException ex){
                        System.out.println(ex.getMessage());
                    }
                    
                    break;

                case OPSORTIR:
                    System.out.println("Sortint del menu principal");
                    break;
            }
        }while(op != MenuPrincipal.OPSORTIR);
    }
    
    public void subMenuBarres(Scanner sc){
        enum MenuSecundari{OP1, OP2, OPSORTIR}
        MenuSecundari[] opcionsSecundaris = MenuSecundari.values(); //Converteix l'enum en un array de constants
        Menu<MenuSecundari> menu = new Menu<>("MENU DE BARRES DE CONTROL",opcionsSecundaris); //Defineix el menu amb el nom d'estacio i l'array d'opcions
        String[] descripcionsBarres={"Obtenir Insersio Barres", "Establir Insercio Barres", "Sortir"};
        menu.setDescripcions(descripcionsBarres);
        MenuSecundari op;

        do{
            menu.mostrarMenu();
            op = menu.getOpcio(sc);
            
            switch(op){
                case OP1:
                    System.out.println(adaptador.getBarres());
                    break;
                    
                case OP2:
                    System.out.println("Entra el percentatge d'insercio de barres (0 - 100):");
                    float insercioBarres = sc.nextFloat();
                    
                    if(insercioBarres == 100){
                        try {
                            adaptador.insertarBarres(insercioBarres);
                            adaptador.desactivarReactor();
                            System.out.println("Reaccio nuclear aturada per insecio de barres del 100%");
                            break;
                        } catch (CentralUBException ex) {
                            ex.getMessage();
                        }
                    }else{
                        try {
                        adaptador.insertarBarres(insercioBarres);
                        break;
                        } catch (CentralUBException ex) {
                        System.out.println(ex.getMessage());
                        }
                    }
                    break;
                    
                 case OPSORTIR:
                    System.out.println("Sortint al menu principal");
                    break;
            }
        }while(op != MenuSecundari.OPSORTIR);

    }
    
    /**
     *
     * @param sc
     */
    public void subMenuReactor(Scanner sc){
        enum MenuSecundari{OP1, OP2, OP3, OPSORTIR}
        MenuSecundari[] opcionsSecundaris = MenuSecundari.values(); //Converteix l'enum en un array de constants
        Menu<MenuSecundari> menu = new Menu<>("MENU DE REACTOR",opcionsSecundaris); //Defineix el menu amb el nom d'estacio i l'array d'opcions
        String[] descripcionsReactor={"Activar Reactor", "Desactivar Reactor", "Mostrar Estat", "Sortir"};
        menu.setDescripcions(descripcionsReactor);
        MenuSecundari op;

        do{
            menu.mostrarMenu();
            op = menu.getOpcio(sc);
            
            switch(op){
                case OP1:
                    try {
                        adaptador.activarReactor();
                        System.out.println("Reactor Activat");
                        
                    } catch (CentralUBException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                    
                case OP2:
                    try {
                        adaptador.desactivarReactor();
                        System.out.println("Reactor Desactivat");
                        
                    } catch (CentralUBException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                    
                case OP3:
                    try {
                        System.out.println(adaptador.mostrarEstatReactor());
                        
                    } catch (CentralUBException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;                    
                    
                 case OPSORTIR:
                    System.out.println("Sortint al menu principal");
                    break;
            }
        }while(op != MenuSecundari.OPSORTIR);

    }
    
    /**
     *
     * @param sc
     */
    public void subMenuRefrigeracio(Scanner sc){
        enum MenuSecundari{OP1, OP2, OP3, OPSORTIR}
        MenuSecundari[] opcionsSecundaris = MenuSecundari.values(); //Converteix l'enum en un array de constants
        Menu<MenuSecundari> menu = new Menu<>("MENU DE REFRIGERACIO",opcionsSecundaris); //Defineix el menu amb el nom d'estacio i l'array d'opcions
        String[] descripcionsRefrigeracio={"Activar Bomba", "Desactivar Bomba ", "Mostrar Estat", "Sortir"};
        menu.setDescripcions(descripcionsRefrigeracio);
        MenuSecundari op;
        int numero;

        do{
            menu.mostrarMenu();
            op = menu.getOpcio(sc);
            
            
            switch(op){
                case OP1:
                    try {
                        System.out.println("Entra numero de la bomba (0-3):");
                        numero = sc.nextInt();
                        adaptador.activarBomba(numero);
                        System.out.println("Bomba " + numero +" Activada");
                        
                    } catch (CentralUBException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                    
                case OP2:
                    try {
                        System.out.println("Entra numero de la bomba (0-3):");
                        numero = sc.nextInt();
                        adaptador.desactivarBomba(numero);
                        System.out.println("Bomba "+ numero + " Desactivada");
                        
                    } catch (CentralUBException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                    
                case OP3:
                    try {
                        System.out.println(adaptador.mostrarEstatBombes());
                        
                    } catch (CentralUBException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;                    
                    
                 case OPSORTIR:
                    System.out.println("Sortint al menu principal");
                    break;
            }
        }while(op != MenuSecundari.OPSORTIR);

    }
    
    private float generaDemandaPotencia(){
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else
            if (valor < DEMANDA_MIN)
                return DEMANDA_MIN;
            else
                return valor;
    }
    
    private void finalitzaDia() {
        // Finalitzar dia i imprimir informacio de la central
        String info = adaptador.finalitzaDia(demandaPotencia);
        System.out.println(info);
        System.out.println("Dia finalitzat\n");
        
        // Generar i mostrar nova demanda de potencia
        demandaPotencia = generaDemandaPotencia();
        System.out.println("La demanda de potencia electrica avui es de " + demandaPotencia + " unitats");
    }
}
