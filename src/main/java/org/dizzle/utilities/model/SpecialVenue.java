package org.dizzle.utilities.model;

public enum SpecialVenue {

	HIGHWAY(100),
	TRAIL(75),
	RIVER_UPSTREAM(75),
	RIVER_DOWNSTREAM(125),
	AIR(100);
	
	private int speedModifier;
	
	public int getSpeedModifier() {
		return this.speedModifier;
	}
	
	private SpecialVenue(int mod) {
		this.speedModifier = mod;
	}
}
