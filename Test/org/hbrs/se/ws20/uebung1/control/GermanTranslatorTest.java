package org.hbrs.se.ws20.uebung1.control;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {

    @org.junit.jupiter.api.Test
    void translateNumber() {
        GermanTranslator translator = (GermanTranslator) Factory.createGermanTranslator();
        assertEquals("eins", translator.translateNumber(1));
        assertEquals("zehn", translator.translateNumber(10));
        assertEquals("Übersetzung der Zahl 0 nicht möglich mit Version 1.0", translator.translateNumber(0));
        assertEquals("Übersetzung der Zahl -1 nicht möglich mit Version 1.0", translator.translateNumber(-1));
        assertEquals("Übersetzung der Zahl -10 nicht möglich mit Version 1.0", translator.translateNumber(-10));
        assertEquals("Übersetzung der Zahl 11 nicht möglich mit Version 1.0", translator.translateNumber(11));
        assertEquals("Übersetzung der Zahl 130 nicht möglich mit Version 1.0", translator.translateNumber(130));
    }
}