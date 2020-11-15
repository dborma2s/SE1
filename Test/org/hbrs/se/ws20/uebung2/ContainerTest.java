package org.hbrs.se.ws20.uebung2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    //Container Objekt zum Aufruf der Methoden in der Klasse Container
    Container c = new Container(1);
    //Member Objekt
    Member m0 = new Container(0);
    Member m1 = new Container(4);
    Member m2 = new Container(3);

    Member m3 = new Container(1);
    Member m4 = new Container(2);
    Member m5 = new Container(2);
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
            System.out.println(e.toString());
        }
    }

    @Test
    void addMember()
    {
        //negativer Test, hinzufuegen von Members identische ID
        try {
            c.addMember(m3);
            c.addMember(m4);
            c.addMember(m5);
        }
        catch(ContainerException e)
        {
            System.out.println(e.toString());
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
    void dump() {
        c.dump();
            }

    @Test
    void size() {
        //leere Liste
        assertEquals(0,c.size());
    }

}