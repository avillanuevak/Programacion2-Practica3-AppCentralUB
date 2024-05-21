/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author dortiz
 * VariableUniforme: aquesta classe es fa servir per determinar
 * aleatoriament si una bomba refrigerant estarà fora de servei al començar un
 * nou dia. Només es declara un objecte d'aquesta classe dins del constructor de
 * la classe Dades i es proporciona com a paràmetre al constructor de la classe
 * BombaRefrigerant. La classe BombaRefrigerant utilitza el mètode seguentValor
 * per generar un nombre aleatori que determinarà si la bomba es queda fora de
 * servei.
 */
public class VariableUniforme implements Serializable{
    private Random random;

    /**
     *
     * @param seed
     */
    public VariableUniforme(long seed) {
        this.random = new Random(seed);
    }

    /**
     *
     * @return
     */
    public int seguentValor() {
        return random.nextInt(100);
    }
}
