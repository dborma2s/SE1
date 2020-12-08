package org.hbrs.se.ws20.uebung1.control.test;
import org.hbrs.se.ws20.uebung1.control.Factory;
import org.hbrs.se.ws20.uebung1.control.GermanTranslator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class GermanTranslatorTest {

    @Test
    void translateNumberTest() {
        GermanTranslator translator = (GermanTranslator) Factory.createTranslator();
        assertEquals("eins", translator.translateNumber(1));
        assertEquals("zehn", translator.translateNumber(10));
        assertEquals("Übersetzung der Zahl 0 nicht möglich mit Version 1.0", translator.translateNumber(0));
        assertEquals("Übersetzung der Zahl -1 nicht möglich mit Version 1.0", translator.translateNumber(-1));
        assertEquals("Übersetzung der Zahl -10 nicht möglich mit Version 1.0", translator.translateNumber(-10));
        assertEquals("Übersetzung der Zahl 11 nicht möglich mit Version 1.0", translator.translateNumber(11));
        assertEquals("Übersetzung der Zahl 130 nicht möglich mit Version 1.0", translator.translateNumber(130));
    }
}