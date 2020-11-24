package org.hbrs.se.ws20.solutions.uebung1.view;

import org.hbrs.se.ws20.solutions.uebung1.control.Translator;
import org.hbrs.se.ws20.solutions.uebung1.control.factory.TranslatorFactory;
import org.hbrs.se.ws20.uebung2.Container;
import org.hbrs.se.ws20.uebung2.ContainerException;
import org.hbrs.se.ws20.uebung2.MemberPraxis;
import org.hbrs.se.ws20.uebung2.MemberView;

public class Client {

	/*
	 * Methode zur Ausgabe einer Zahl auf der Console
	 */
	public void display( int aNumber ){
		// In dieser Methode soll die Methode translateNumber 
		// mit dem übergegebenen Wert der Variable aNumber 
		// aufgerufen werden.
		// Strenge Implementierung gegen das Interface Translator gewuenscht!

		// Referenz aktiv beziehen
		Translator translator = TranslatorFactory.createGermanTranslator(); // new GermanTranslator();
		String result = translator.translateNumber( aNumber );

		System.out.println("Das Ergebnis der Berechnung: " +
				"[das Ergebnis an dieser Stelle]" + result );

		translator = TranslatorFactory.createEnglishTranslator();
		result = translator.translateNumber( aNumber );

		// System.out.println("Ergebnis auf Englisch: " + result );

	}

	public void erstelleMember(Container container, MemberView v) throws ContainerException {

		for(int i = 0; i < 9; i++){
			container.addMember(new MemberPraxis(i));
		}
		v.dump(container.getCurrentList());
	}
}




