package org.dizzle.utilities.model;

/**
 * On any given watch, a party will be in one of these modes.
 * This will effect what, if any, encounters may be had in a hex.
 * 
 * It may have other effects such as odds of getting lost or exhaustion accumulation.
 * 
 * @author deastland
 *
 */
public enum TravelMode {

	CAREFUL(50),
	STANDARD(100),
	FAST(150),
	EXPLORE(50),
	STATIONARY(0);
	
	int value;
	
	public int getValue() {
		return this.value;
	}
	
	private TravelMode(int value) {
		this.value = value;
	}
}
