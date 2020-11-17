package org.hbrs.se.ws20.uebung2;

import java.util.ArrayList;
import java.util.Iterator;

//Verwaltung der Objekte
public class Container {
    private ArrayList<Member> list = new ArrayList<>();

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
            if (mem.getID() == id) {
                iter.remove();
                return "ID "+id + " wurde entfernt.";
            }        }
           return "ID " +id + " nicht vorhanden.";
    }

    public void dump()
    {
        for (Member mem : list) {

            System.out.println(mem.toString());
        }
    }

    public int size()
    {
        return list.size();
    }


}
