package org.dizzle.utilities.model;

public enum Season {
	SPRING(4),
	SUMMER(5),
	FALL(4),
	WINTER(3);
	
	private int sunsetWatch;	// The watch when night starts.
	
	public int getSunsetWatch() {
		return this.sunsetWatch;
	}
	
	private Season(int sunsetWatch) {
		this.sunsetWatch = sunsetWatch;
	}
}
