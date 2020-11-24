package org.hbrs.se.ws20.uebung2;

import org.hbrs.se.ws20.solutions.uebung1.view.Client;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceException;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategyStream;

public class Main {

    public static void main(String[] args) throws ContainerException {

        Container container = Container.getInstance();
        MemberView view = new MemberView();
        Client cl = new Client();
        cl.erstelleMember(container, view);
    }
}
