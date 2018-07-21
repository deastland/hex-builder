package org.dizzle.utilities.model;

public enum VisibilityCondition {

	CLEAR(100),
	OBSCURED(50);
	
	private int speedModifier;
	
	public int getSpeedModifier() {
		return this.speedModifier;
	}
	
	private VisibilityCondition(int mod) {
		this.speedModifier = mod;
	}
}
