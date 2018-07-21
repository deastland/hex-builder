package org.dizzle.utilities.model;

public enum GroundCondition {

	CLEAR(100),
	SNOW(50),
	DEEP_SNOW(50),
	MUD(25);
	
	private int speedModifier;
	
	public int getSpeedModifier() {
		return this.speedModifier;
	}
	
	private GroundCondition(int speedMod) {
		this.speedModifier = speedMod;
	}
}
