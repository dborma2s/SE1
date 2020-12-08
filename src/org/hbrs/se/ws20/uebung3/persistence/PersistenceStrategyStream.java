package org.hbrs.se.ws20.uebung3.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceStrategyStream<Member> implements PersistenceStrategy<Member> {

    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private List<Member> newListe = null;

    @Override
    public void openConnection() throws PersistenceException {

        try {
                ois = new ObjectInputStream(new FileInputStream("members.ser"));
        } catch(IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Connection not found!");
        }

    }

    @Override
    public void closeConnection() throws PersistenceException {

        try {
                ois.close();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Connection not found!");
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> member) throws PersistenceException {

        try{
            oos = new ObjectOutputStream(new FileOutputStream("members.ser"));
            for(Member m : member) {
                oos.writeObject(m);
            }
            oos.close();

        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.SaveFailure, "Save failure!");
        }
    }



    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     */
    public List<Member> load() throws PersistenceException {

        try {
        newListe = new ArrayList<>();
        openConnection();
            while(true){
                Object obj = ois.readObject();
                newListe.add((Member) obj);
            }
        } catch (EOFException e) {
            System.out.println("End of Stream");
        } catch (IOException | ClassNotFoundException e){
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "Load failure!");
        }
        closeConnection();
        return newListe;
    }
}
