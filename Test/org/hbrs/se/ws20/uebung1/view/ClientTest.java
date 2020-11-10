package org.hbrs.se.ws20.uebung1.view;

import org.hbrs.se.ws20.uebung1.control.Factory;
import org.hbrs.se.ws20.uebung1.control.Translator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Client c = null;

    private ClientTest(){
        c = new Client();
    }
    @Test
    void display()
    {
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
        test.display();
    }
}