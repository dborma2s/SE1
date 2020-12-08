package org.hbrs.se.ws20.uebung2;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceException;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ContainerTest {

    //Test with SingletonPattern
    private final Container c = Container.createContainer();
    private final PersistenceStrategyStream<Member> stream = new PersistenceStrategyStream<>();
    private final MemberView view =  new MemberView();
    private MemberImplemented m1 = new MemberImplemented(2142141);
    private MemberImplemented m2 = new MemberImplemented(42141241);
    private MemberImplemented m3 = new MemberImplemented(12412412);

    @BeforeEach
    public void setup() throws ContainerException {
        c.addMember(m1);
        c.addMember(m2);
        c.addMember(m3);
    }

    @AfterEach
    public void end(){
        c.resetContainer();
    }

    @Test
    public void testMongoDB(){

        c.setStrategy(new PersistenceStrategyMongoDB<>());
        try {
            c.store();
        } catch(PersistenceException e){
            assertEquals(PersistenceException.ExceptionType.ImplementationNotAvailable, e.getExceptionTypeType());
        }
        try {
            c.load();
        } catch(PersistenceException e){
            assertEquals(PersistenceException.ExceptionType.ImplementationNotAvailable, e.getExceptionTypeType());
        }
    }

    @Test
    public void testStoreLoad() throws PersistenceException, ContainerException {
        //Set PersistenceStrategyStream
        c.setStrategy(stream);
        System.out.println("Show current objects (3):\n");
        //Test output of new Method dump in MemberView
        view.dump(c.getCurrentList());
        //add new Member
        System.out.println("\nAdd new object...\n");
        c.addMember(new MemberImplemented(32423));
        //Store m1, m2, m3 in "members.ser"
        System.out.println("Store 4 objects to file\n ");
        c.store();
        System.out.println("Loading objects from File back... \n");
        //Load list from file
        c.load();
        List<Member> list = c.getCurrentList();
        //show different output as before
        System.out.println("Show objects from file (4)\n");
        view.dump(list);
        System.out.println("\nDelete two objects from list");
        c.deleteMember(2142141);
        c.deleteMember(32423);
        System.out.println("\nShow list again (2 Members)");
        view.dump(c.getCurrentList());
        System.out.println("\nLoad old list from file (4 Members)");
        c.load();
        view.dump(c.getCurrentList());
    }

    @Test
    public void testStoreException(){
        c.setStrategy(null);
        try {
            c.store();
        } catch(PersistenceException e){
            assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet, e.getExceptionTypeType());
        }

        c.setStrategy(stream);
        c.resetContainer();
        try {
            c.store();
        } catch(PersistenceException e) {
            assertEquals(PersistenceException.ExceptionType.SaveFailure, e.getExceptionTypeType());
        }
    }

    @Test
    public void testLoadException(){
        c.setStrategy(null);
        try {
            c.load();
        } catch(PersistenceException e){
            assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet,e.getExceptionTypeType());
        }
    }

    @Test
    void addMember() {

        assertThrows(ContainerException.class, () -> c.addMember(m1));
        assertThrows(ContainerException.class, () -> c.addMember(m2));
        assertThrows(ContainerException.class, () -> c.addMember(m3));
        System.out.println("addMember method console test: ");
        try {
            c.addMember(m1);
        } catch (ContainerException e) {
            e.printStackTrace();
        }
        try {
            c.addMember(m2);
        } catch (ContainerException e) {
            e.printStackTrace();
        }
        try {
            c.addMember(m3);
        } catch (ContainerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteMember() {

        assertEquals("Remove of Member 462352323 not successfull!", c.deleteMember(462352323));
        assertEquals("Remove of Member 2142141 successfull!", c.deleteMember(2142141));
        assertEquals("Remove of Member 42141241 successfull!", c.deleteMember(42141241));
        assertEquals("Remove of Member 12412412 successfull!", c.deleteMember(12412412));
        assertEquals("Remove of Member 46235232 not successfull!", c.deleteMember(46235232));
    }

    @Test
    void dump() {
        System.out.println("dump method console test:");
        view.dump(c.getCurrentList());
    }

    @Test
    void size() {
        assertEquals(3, c.size());
        c.deleteMember(2142141);
        assertEquals(2, c.size());
        c.deleteMember(12412412);
        assertEquals(1, c.size());
        c.deleteMember(42141241);
        assertEquals(0, c.size());
    }

    @Test
    void testComplete() throws ContainerException {
        //This Method contains the same tests as above, this is just a coherant demonstration of all tests together
        c.resetContainer();
        //test for empty store
        assertEquals(0, c.size());

        //add Members
        c.addMember(m1);
        c.addMember(m2);
        c.addMember(m3);

        //test size again
        assertEquals(3, c.size());

        //test adding same Object
        assertThrows(ContainerException.class, () -> c.addMember(m1));
        //test size again
        assertEquals(3, c.size());
        //remove objects
        assertEquals("Remove of Member 2142141 successfull!", c.deleteMember(2142141));
        assertEquals("Remove of Member 12412412 successfull!", c.deleteMember(12412412));
        //try to remove non existing object
        assertEquals("Remove of Member 46235232 not successfull!", c.deleteMember(46235232));
        //test size again
        assertEquals(1, c.size());
        //print all objects
        System.out.println("test complete: ");
        //c.dump();
        //print stackstrace of ContainerException
        try {
            c.addMember(m2);
        } catch (ContainerException e) {
            e.printStackTrace();
        }
        //remove last object
        assertEquals("Remove of Member 42141241 successfull!", c.deleteMember(42141241));
        //check again size
        assertEquals(0, c.size());




    }
}