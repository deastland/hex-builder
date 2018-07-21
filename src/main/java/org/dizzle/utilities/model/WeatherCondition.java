package org.dizzle.utilities.model;

public enum WeatherCondition {
	CLEAR(100),
	WARM(100),
	HOT(75),
	COOL(100),
	COLD(75),
	STORM(75),
	HEAVY_STORM(50),
	HURRICANE(10),
	OBSCURED(50);		// fog, night, etc.
	
	private int speedModifier;
	
	public int getSpeedModifier() {
		return this.speedModifier;
	}
	
	private WeatherCondition(int speedModifier) {
		this.speedModifier = speedModifier;
	}
}
