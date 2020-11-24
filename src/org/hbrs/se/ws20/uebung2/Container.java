package org.hbrs.se.ws20.uebung2;

import org.hbrs.se.ws20.uebung3.persistence.PersistenceException;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategyStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Verwaltung der Objekte
public class Container {
    private List<Member> list = new ArrayList<>();
    //einzige Instanz der Klasse
    private static Container firstInstance = null;

    private PersistenceStrategy<Member> strategy = null;
    private Container(){}

     public void addMember(Member member) throws ContainerException {

        for (Member mem : list) {
            if (mem.getID().equals(member.getID())) {
                throw new ContainerException(member);
            }
        }
        list.add(member);
        System.out.println(member.toString() + " konnte hinzugefuegt werden");

    }
    /* zu FA2 statement
    1. Ein gutes Exception Handling dient dazu, die Fehlerursache zu analysieren und zu beseitigen.
    2. Der Fehler ist nicht direkt erkennbar. Es erschwert dem Tester somit möglich auftretende Fehler zu testen.*/
    public String deleteMember(Integer id)
    {
        Iterator<Member> iter = list.iterator();
        while (iter.hasNext()) {
            Member mem = iter.next();
            if (mem.getID().equals(id)) {
                iter.remove();
                return "ID "+id + " wurde entfernt.";
            }        }
        return "ID " +id + " nicht vorhanden.";
    }

    public int size()
    {
        return list.size();
    }

    public static Container getInstance()
    {
        if(firstInstance==null)
        {
            //Instanz kann nur erstellt werden, wenn noch keine exisitiert
            //wenn die Instanz nicht gebraucht wird, dann wird sie niemals erstellt
            firstInstance = new Container();
        }
        return firstInstance;
    }
    //Ablage Member Objekte auf Datenspeicher
    public void store() throws PersistenceException{
        strategy.save(list);

        }
    //Befinden sich zum Zeitpunkt des Ladens Member-Objekte in der Liste, so sollen diese
    //einfach überschrieben werden.
    public void load() throws PersistenceException
    {
        try {
            list = strategy.load();
        }
        catch (ClassNotFoundException e)
        {
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "Laden fehlgeschlagen");
        }
        catch(IOException e)
        {
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "Laden fehlgeschlagen");
        }
        catch (NullPointerException e){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie!");
        }
    }
    public void setStrategy(PersistenceStrategy<Member> strategy) {
        this.strategy = strategy;
    }
    public List<Member> getCurrentList()
    {
        return list;
    }

  }
