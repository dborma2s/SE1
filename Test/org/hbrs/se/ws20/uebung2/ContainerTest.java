package org.hbrs.se.ws20.uebung2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    //Container Objekt zum Aufruf der Methoden in der Klasse Container
    Container c = new Container();
    //Member Objekte
    Member m0 = new MemberPraxis(0);
    Member m1 = new MemberPraxis(4);
    Member m2 = new MemberPraxis(3);

    @BeforeEach
    public void setup()
    {
        //positiver Test für addMember, hinzufuegen von Members unterschiedliche ID
        try {
            c.addMember(m0);
            c.addMember(m1);
            c.addMember(m2);
        }
        catch(ContainerException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void addMember()
    {
        //negativer Test, hinzufuegen von Members identische ID
        try {
            c.addMember(m0);
            }
        catch(ContainerException e)
        {
            e.printStackTrace();
        }
    }


    @Test
    void deleteMember()
    {
        //Positivtest, lösche ID 3, array wird kleiner
        c.deleteMember(3);
        assertEquals(2,c.size());
        //Negativtest, lösche nicht vorhandene ID 10
        assertEquals("ID 10 nicht vorhanden.",c.deleteMember(10));
            }

    @Test
    void dump()
    {
        System.out.println("Ausgabe der Liste");
        c.dump();
    }

    @Test
    void size() {
        //Liste mit 3 Eintraegen
        assertEquals(3,c.size());
        c.deleteMember(3);
        assertEquals(2,c.size());
    }

}