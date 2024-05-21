/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;

/**
 *
 * @author Albert Villanueva
 * Aquesta classe implementa la classe base d'altres
 * tres classes usades per representar pàgines de bitàcola (veure Apartat 2.4).
 * La classe PaginaBitacola conté un atribut de tipus enter per guardar el
 * nombre de dia al que es refereix la pàgina. Aquest atribut s'ha
 * d'inicialitzar en el constructor.
 */
public class PaginaBitacola implements Serializable{
    
    private int dia;
    
    /**
     *Inicialitza el primer dia
     */
    public PaginaBitacola(){
        this.dia = 1;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return "";
    }
}
