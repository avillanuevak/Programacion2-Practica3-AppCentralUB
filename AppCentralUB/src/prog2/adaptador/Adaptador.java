/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.adaptador;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import prog2.model.Dades;
import java.io.Serializable;
import prog2.vista.CentralUBException;
import prog2.model.*;
/**
    [La classe Adaptador és el mediador entre la vista i el model.
 * Concretament, conecta la classe Dades del model i la classe CentralUB de la vista.
 * Aquesta classe també conté els mètodes de persistència de dades.]}
 * 
 * @author Albert Villanueva
 * La classe Adaptador és el mediador entre la vista i el model.
 * Concretament, conecta la classe Dades del model i la classe CentralUB de la vista.
 * Aquesta classe també conté els mètodes de persistència de dades.
 */
public class Adaptador implements Serializable{
    private Dades dades;
    
    /**
     *
     * @throws CentralUBException
     */
    public Adaptador() throws CentralUBException{
        dades = new Dades();
    }
    
    
    /**
     * Mètodes de suport per connectar dades amb adaptador
     * @param b
     * @throws prog2.vista.CentralUBException
     */
    
    
    public void insertarBarres(float b) throws CentralUBException{
        dades.setInsercioBarres(b);
    }
    
    /**
     *
     * @return
     */
    public float getBarres(){ 
        return dades.getInsercioBarres();
    }
    
    /**
     *
     * @throws CentralUBException
     */
    public void activarReactor() throws CentralUBException{
        dades.activaReactor();
    }
    
    /**
     *
     * @throws CentralUBException
     */
    public void desactivarReactor() throws CentralUBException{
        dades.desactivaReactor();
    }
    
    /**
     *
     * @return
     * @throws CentralUBException
     */
    public String mostrarEstatReactor() throws CentralUBException{
        return dades.mostraReactor().toString();
    }
    
    /**
     *
     * @param id
     * @throws CentralUBException
     */
    public void activarBomba(int id) throws CentralUBException{
        dades.activaBomba(id);
    }
    
    /**
     *
     * @param id
     * @throws CentralUBException
     */
    public void desactivarBomba(int id) throws CentralUBException{
        dades.desactivaBomba(id);
    }
    
    /**
     *
     * @return
     * @throws CentralUBException
     */
    public String mostrarEstatBombes() throws CentralUBException{
        return dades.mostraSistemaRefrigeracio().toString();
    }

    /**
     *
     * @param potencia
     * @return
     * @throws CentralUBException
     */
    public String mostrarEstatCentral(float potencia) throws CentralUBException{
        return dades.mostraEstat(potencia).toString();
    }

    /**
     *
     * @return
     * @throws CentralUBException
     */
    public String mostrarBitacola () throws CentralUBException{
        return dades.mostraBitacola().toString();
    }
    
    /**
     *
     * @return
     * @throws CentralUBException
     */
    public String mostrarIncidencias () throws CentralUBException{
        if(dades.mostraIncidencies() == null){
            return "No hi han incidencies";
        }
        return dades.mostraIncidencies().toString();
    }
    
    /**
     * Mètodes de persistència de dades
     * @param camiDesti
     * @throws prog2.vista.CentralUBException
     */
    
    public void guardaDades(String camiDesti) throws CentralUBException {
    try {
        File fitxer = new File(camiDesti);
        FileOutputStream fout = new FileOutputStream(fitxer);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(dades); 
        
        System.out.println("Arxiu guardat");
        
        oos.close();
        fout.close();
    } catch (IOException e) {
        throw new CentralUBException("Error: No s'ha pogut guardar l'arxiu.\n" +e.getMessage());
    }
}

    /**
     *
     * @param camiOrigen
     * @throws CentralUBException
     */
    public void carregaDades(String camiOrigen) throws CentralUBException {
    try {
        File fitxer = new File(camiOrigen);
        FileInputStream fin = new FileInputStream(fitxer);
        ObjectInputStream ois = new ObjectInputStream(fin);
        
        Object obj = ois.readObject();
        if (obj instanceof Dades) {
            dades = (Dades) obj;
            System.out.println("Arxiu recuperat.");
        } else {
            throw new CentralUBException("Error: No s'ha pogut carregar l'arxiu en un objecte dades.");
        }
 
        ois.close();
        fin.close();
    } catch (ClassNotFoundException e) {
        throw new CentralUBException("Error: No s'ha pogut carregar.\n" +e.getMessage());
    } catch (FileNotFoundException e) {
        throw new CentralUBException("Error: Arxiu no trobat.\n" +e.getMessage());
    } catch (IOException e) {
        throw new CentralUBException("Error: Problema de lectura de l'arxiu.\n" +e.getMessage());
    }
}
    
    /**
     *
     * @param demandaPotencia
     * @return
     */
    public String finalitzaDia(float demandaPotencia){
        return dades.finalitzaDia(demandaPotencia).toString();
    }
}
