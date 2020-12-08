package org.hbrs.se.ws20.uebung2;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceException;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Container {

    //private HashSet<Member> map = new HashSet<>();

    //Fuer Uebung 3 wird eine Liste gefordert
    private List<Member> list = new ArrayList<>();
    private static Container reference = null;
    private PersistenceStrategy<Member> strategy = null;

    private Container(){}

    public void setStrategy(PersistenceStrategy<Member> strategy) {
        this.strategy = strategy;
    }

    public static Container createContainer(){

        if(reference == null){
            reference = new Container();
        }
        return reference;
    }

    public void addMember(Member member) throws ContainerException{
        if(contains(member)){
            ContainerException exception = new ContainerException();
            exception.addId(member.getID());
            throw exception;
        }
        list.add(member);
    }

    private boolean contains(Member r) {
        Integer ID = r.getID();
        for ( Member rec : list) {
            if ( rec.getID().intValue() == ID.intValue() ) {
                return true;
            }
        }
        return false;
    }

    //Beantwortung Aufgabe 2.FA2
    //1. Fehlerhandling ist nicht direkt erkennbar für andere Entwickler (genaues reinlesen)
    //2. Fehlerbehandlung kann nicht weiterdeligiert werden wie z.B. bei geprüften Exceptions
    //3. Erschwertes Testen, da nicht auf Exceptions geprüft werden kann, sondern immer der eigene Fehlercode überprüft werden muss!
    public String deleteMember(Integer id){
        for(Member m : list){
            if(m.getID().equals(id)){
                list.remove(m);
                return "Remove of Member " + id + " successfull!";
            }
        }
        return "Remove of Member " + id + " not successfull!";
    }

    public int size(){
        return list.size();
    }

    public List<Member> getCurrentList(){
        return list;
    }

    public void store() throws PersistenceException {
        try {
            strategy.save(list);
        } catch(NullPointerException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No strategy found!");
        }
    }

    public void load() throws PersistenceException {
        try{
            this.list = strategy.load();
        } catch (IOException | ClassNotFoundException e){
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "Load failure!");
        } catch (NullPointerException e){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No strategy found!");
        }
    }

    //Helper method for tests
    public void resetContainer(){
        this.list = new ArrayList<>();
    }


}
