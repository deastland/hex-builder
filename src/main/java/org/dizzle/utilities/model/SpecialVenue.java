package org.dizzle.utilities.model;

public enum SpecialVenue {

	HIGHWAY(100),
	TRAIL(75),
	RIVER(100),
	AIR(100);
	
	private int speedModifier;
	
	public int getSpeedModifier() {
		return this.speedModifier;
	}
	
	private SpecialVenue(int mod) {
		this.speedModifier = mod;
	}
}
