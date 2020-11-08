package org.hbrs.se.ws20.uebung1.control;

public class Factory {

    public static Translator createGermanTranslator()
    {
        return new GermanTranslator();

    }
}
