package org.hbrs.se.ws20.uebung1.view.test;

import org.hbrs.se.ws20.uebung1.view.Client;

class ClientTest {

    private Client c = null;

    private ClientTest(){
        c = new Client();
    }

    private void displayTest() {

        System.out.println("Positive Tests: ");
        //positive Tests
        for(int i = 1; i < 11; i++){
            c.display(i);
        }

        System.out.println("Negative Tests: ");
        //negative Tests
        c.display(0);
        c.display(11);
        c.display(-1);
        c.display(-10);
        c.display(130);
        c.display(1303532532);
        c.display(-324323233);
    }

    public static void main(String[] args){
        ClientTest test = new ClientTest();
        test.displayTest();
    }
}