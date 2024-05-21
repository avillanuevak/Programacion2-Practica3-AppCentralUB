/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import java.io.Serializable;

/**
 *
 * @author Albert
 * S'ha de crear la classe CentralUBException dins del paquet
 * prog2.vista. Aquesta classe serà utilitzada per gestionar excepcions, tal com
 * s'explica més avall.
 */
public class CentralUBException extends Exception implements Serializable{

    /**
     *
     * @param message
     */
    public CentralUBException(String message) {
        super(message);
    }
}
