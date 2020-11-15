package org.hbrs.se.ws20.uebung2;

import java.util.ArrayList;
import java.util.Iterator;

public class Container implements Member{
    static ArrayList<Member> list = new ArrayList<>();
    private int id;

    public Container(int id)
    {
        this.id = id;
    }

    public Integer getID() {
        return this.id;
    }

    public static void main(String[] args)  {
        Container c = new Container(1);

        Member m0 = new Container(0);
        Member m1 = new Container(4);
        Member m2 = new Container(3);
        Member m3 = new Container(2);
        Member m4 = new Container(5);

        try {
            c.addMember(m0);
            c.addMember(m1);
            c.addMember(m2);
            c.addMember(m3);
            c.addMember(m4);
        }
        catch(ContainerException e)
        {
            System.out.println(e.toString());
        }

        System.out.println(c.deleteMember(2));

        System.out.println("------------------------");
        System.out.println("Ausgabe Liste");
        c.dump();
        System.out.println("------------------------");
        System.out.println("Anzahl aktuell abgespeicherter Objekte "+c.size());

        /*catch(ContainerException e)
        {
            e.getMessage();
        }*/
        //System.out.println(list.toString());
    }

    public void addMember(Member member) throws ContainerException {

        for (Member mem : list) {
            if (mem.getID().equals(member.getID())) {
                throw new ContainerException(member);
            }
        }
        list.add(member);
        System.out.println(member.toString() + " konnte hinzugefuegt werden");

    }
    public String deleteMember(int id)
    {
        Iterator<Member> iter = list.iterator();
        while (iter.hasNext()) {
            Member mem = iter.next();
            if (mem.getID() == id) {
                iter.remove();
                return id + " wurde entfernt";
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

    public String toString()
    {
        return "Member (ID = " +id + ")";
    }
}
