package org.hbrs.se.ws20.uebung2;

//Checked Exception
public class ContainerException extends Exception {

    public ContainerException(Member member)
    {
        super("Das Member-Objekt mit der ID "+ member.getID()+ " ist bereits vorhanden!");
    }


}
