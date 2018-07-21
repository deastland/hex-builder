package org.dizzle.utilities.model;

public enum WeatherCondition {
	CLEAR(100),
	HOT(75),
	COLD(75),
	HURRICANE(10),
	OBSCURED(50),		// fog, night, etc.
	SNOW(50),
	DEEP_SNOW(50),
	STORM(75),
	HEAVY_STORM(50);
	
	private int speedModifier;
	
	public int getSpeedModifier() {
		return this.speedModifier;
	}
	
	private WeatherCondition(int speedModifier) {
		this.speedModifier = speedModifier;
	}
}
