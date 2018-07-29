package org.dizzle.utilities.model;

public enum ClimaticRegion {
	ARCTIC(20),
	SUBARCTIC(30),
	TEMPERATE(45),
	SUBTROPICAL(45),
	TROPICAL(20);
	
	int maxWindSpeed;
	
	public int getMaxWindSpeed() {
		return this.maxWindSpeed;
	}
	
	private ClimaticRegion(int maxSpeed) {
		this.maxWindSpeed = maxSpeed;
	}
}
