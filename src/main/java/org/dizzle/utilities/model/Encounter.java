package org.dizzle.utilities.model;

/**
 * Parties can encounter creatures or locations in a given hex.
 * Features are only ever encountered if a party is moving.
 * 
 * @author Zeus
 *
 */
public class Encounter {

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
