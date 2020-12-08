package org.hbrs.se.ws20.uebung2;

public class Main {

    public static void main(String[] args) throws ContainerException {
            Container container = Container.createContainer();
            MemberView view = new MemberView();
            Client c = new Client();
            c.createMembers(container, view);
    }
}
