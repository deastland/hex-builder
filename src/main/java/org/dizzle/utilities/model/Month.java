package org.dizzle.utilities.model;

public enum Month {

	Lewfrang(1),
	Whelfrang(2),
	Hjarnfrang(3),
	Lewsprig(4),
	Whelsprig(5),
	Hjarnsprig(6),
	Lewvarn(7),
	Whelvarn(8),
	Hjarnvarn(9),
	Lewstaag(10),
	Whelstaag(11),
	Hjarnstaag(12);
	
	private int monthNumber;
	
	public int getMonthNumber() {
		return this.monthNumber;
	}
	
	private Month(int monthNo) {
		this.monthNumber = monthNo;
	}
	
	/*
	 * Given a number value, return the ENUM month for that number. (i.e. 3 returns Hjarnfrang)
	 */
	public static Month getMonthFromNumber(int value) {
		for (Month m : Month.values()) {
			if (m.getMonthNumber() == value) {
				return m;
			}
		}
		
		return null;
	}
}
