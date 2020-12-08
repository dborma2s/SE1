package org.hbrs.se.ws20.prototype.uebung4.model;

public class ContainerException extends Exception {
	
	private String modus;
	private Integer id;
	
	public ContainerException(String s ) {
		super ( s );
	}

	@Override
	public void printStackTrace() {
		if (this.id  != null) {
			System.out.println("Das User-Objekt mit der ID " + this.id + " ist bereits vorhanden!");
		} else {
			System.out.println(this.getMessage());
		}
	} 

	public void addID(Integer id) {
		this.id = id;
	}


}
