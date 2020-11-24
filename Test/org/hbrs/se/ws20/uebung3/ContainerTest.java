package org.hbrs.se.ws20.uebung3;

import org.hbrs.se.ws20.solutions.uebung1.view.Client;
import org.hbrs.se.ws20.uebung2.*;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceException;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest
{
    Container c = Container.getInstance();
    MemberView memberView = new MemberView();
    Member m0 = null;
    Member m1 = null;
    Member m2 = null;


    @BeforeEach
    void setup() throws ContainerException {
        m0 = new MemberPraxis(0);
        m1 = new MemberPraxis(4);
        m2 = new MemberPraxis(2);

        c.addMember(m0);
        c.addMember(m1);
        c.addMember(m2);
    }


    @Test
        void TestMongoDBopenConnection()  {
            PersistenceStrategyMongoDB persistenceStrategyMongoDB = new PersistenceStrategyMongoDB();
            Assertions.assertThrows(UnsupportedOperationException.class, () -> {persistenceStrategyMongoDB.load();});
        }
    @Test
        void TestStream() throws ContainerException {

            PersistenceStrategyStream persistenceStrategy = new PersistenceStrategyStream();
            c.setStrategy(persistenceStrategy);



            try {
                c.store();
                c.load();
                memberView.dump(c.getCurrentList());
                System.out.println("---------------------------------------------");
                c.deleteMember(0);
                c.deleteMember(4);
                System.out.println("---------------------------------------------");
                memberView.dump(c.getCurrentList());
                System.out.println("---------------------------------------------");
                c.load();
                System.out.println("---------------------------------------------");
                memberView.dump(c.getCurrentList());
            }
            catch (PersistenceException e)
            {
                System.out.println(e.getMessage());
            }


        }
    @Test
    void deleteMember() throws ContainerException {

        //Positivtest, lösche ID 3, array wird kleiner
        System.out.println(c.deleteMember(4));
        assertEquals(2,c.size());
        //Negativtest, lösche nicht vorhandene ID 10
        assertEquals("ID 3 nicht vorhanden.",c.deleteMember(3));
    }


    @Test
    void testLoadException(){
        c.setStrategy(null);
        try {
            c.load();
        } catch(PersistenceException e){
            assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet,e.getExceptionTypeType());
        }
    }

    @Test
    void dump() throws ContainerException {
        System.out.println("Die Ausgabe lautet");
        memberView.dump(c.getCurrentList());
    }


}