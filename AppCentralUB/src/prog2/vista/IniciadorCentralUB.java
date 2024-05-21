/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Albert Villanueva
 * Conté la classe principal de l'aplicació i està
 * ubicada al paquet prog2.vista. Declara un objecte de la classe CentralUB i
 * crida a mètode gestioCentralUB esmentat anteriorment.
 */
public class IniciadorCentralUB implements Serializable{
    
    /**
     *
     * @param args
     * @throws CentralUBException
     * @throws IOException
     */
    public static void main(String[] args)throws CentralUBException, IOException{
        
        // Crea un objecte centralUB
        CentralUB centralUB = new CentralUB();
        
        // Comença la gestió de la centralUB
        centralUB.gestioCentralUB();
    }
}


