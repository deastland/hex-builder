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
	FAST(200),
	EXPLORE(50),
	FORAGING(50),
	STATIONARY(0),
	CAREFUL_EXPLORE(25);
	
	int speedModifier;
	
	public int getSpeedModifier() {
		return this.speedModifier;
	}
	
	private TravelMode(int speedModifier) {
		this.speedModifier = speedModifier;
	}
}
