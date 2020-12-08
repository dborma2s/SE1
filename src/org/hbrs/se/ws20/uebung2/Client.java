package org.hbrs.se.ws20.uebung2;

public class Client {

    public void createMembers(Container container, MemberView view) throws ContainerException {

        for(int i = 0; i < 100; i++){
            container.addMember(new MemberImplemented(i));
        }
        view.dump(container.getCurrentList());
    }
}
