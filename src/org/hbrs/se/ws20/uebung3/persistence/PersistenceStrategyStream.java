package org.hbrs.se.ws20.uebung3.persistence;

import org.hbrs.se.ws20.uebung2.MemberPraxis;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceStrategyStream<Member> implements PersistenceStrategy<Member> {
     ObjectOutputStream oos =null;
    //zum laden
    ObjectInputStream ois = null;
     FileInputStream fis = null;
     List<Member> newListe =  null;
    @Override
    public void openConnection() throws PersistenceException {
        try {
            //fis = new FileInputStream("C:\\Users\\Dennis-PC\\Desktop\\uebung3\\uebung3.txt");
            fis = new FileInputStream("uebung3.txt");
            ois = new ObjectInputStream(fis);
        }
        catch(IOException e)
        {
            System.out.printf(e.getMessage());
        }

    }

    @Override
    public void closeConnection() throws PersistenceException {
        try {
            ois.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> member) throws PersistenceException  {
        try {
            oos = new ObjectOutputStream(new FileOutputStream("uebung3.txt"));

            for(Member mem: member) {
                oos.writeObject(mem);
                System.out.println("Erfolgreich in die File geschrieben");
                }
            oos.close();
        }
        catch(IOException e)
        {
            System.out.printf(e.getMessage());
        }

    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     */
    public List<Member> load() throws PersistenceException, IOException, ClassNotFoundException {
        // Some Coding hints ;-)
        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // List<...> newListe =  null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        // fis = new FileInputStream( " a location to a file" );
        // ois = new ObjectInputStream(fis);

        // Reading and extracting the list (try .. catch ommitted here)
        // Object obj = ois.readObject();

        // if (obj instanceof List<?>) {
        //       newListe = (List) obj;
        // return newListe
        // and finally close the streams (guess where this could be...?)



            newListe = new ArrayList<>();
            openConnection();

            try
            {
            while(true)
            {
                Object obj = ois.readObject();
                newListe.add((Member) obj);
            }}
            catch (EOFException e) {
                System.out.println("Stream zu Ende");
            } catch (IOException|ClassNotFoundException e){
                throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "Laden fehlgeschlagen");
            }
            closeConnection();

        return newListe;

    }
}
