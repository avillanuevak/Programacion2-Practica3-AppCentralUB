/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author dortiz
 * VariableNormal: aquesta classe es fa servir dins de la classe
 * CentralUB per generar la demanda de potència elèctrica de cada nou dia. Tot
 * el codi necessari està implementat al codi base i per tant no la farem servir
 * directament.
 */
public class VariableNormal implements Serializable{
    private Random random;
    private float mean;
    private float standardDeviation;

    /**
     *
     * @param mean
     * @param standardDeviation
     * @param seed
     */
    public VariableNormal(float mean, float standardDeviation, long seed) {
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.random = new Random(seed);
    }

    /**
     *
     * @return
     */
    public float seguentValor() {
        return (float) mean + standardDeviation * (float) random.nextGaussian();
    }

}
