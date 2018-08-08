package org.dizzle.utilities.model;

public enum PrecipitationAmount {

	NONE(0),
	TRACE(1),
	LIGHT(2),
	MODERATE(3),
	HEAVY(4),
	DOWNPOUR(5);

	private int value;
	
	public int getValue() {
		return this.value;
	}
	
	private PrecipitationAmount(int value) {
		this.value = value;
	}
	
	/*
	 * Given a number value, return the ENUM month for that number. (i.e. 3 returns MODERATE)
	 */
	public static PrecipitationAmount getPrecipFromNumber(int value) {
		for (PrecipitationAmount pa : PrecipitationAmount.values()) {
			if (pa.getValue() == value) {
				return pa;
			}
		}
		
		return null;
	}

}