package org.dizzle.utilities.model;

public enum Month {

	/*
	 * The month names are compound words that combine 
	 * a name for the season (frang, sprig, varn, staag)
	 * with a name for high/mid/low (lew, whel, hjarn).
	 * 
	 */	
	Lewfrang(1, Season.WINTER),
	Whelfrang(2, Season.WINTER),
	Hjarnfrang(3, Season.WINTER),
	Lewsprig(4, Season.SPRING),
	Whelsprig(5, Season.SPRING),
	Hjarnsprig(6, Season.SPRING),
	Lewvarn(7, Season.SUMMER),
	Whelvarn(8, Season.SUMMER),
	Hjarnvarn(9, Season.SUMMER),
	Lewstaag(10, Season.FALL),
	Whelstaag(11, Season.FALL),
	Hjarnstaag(12, Season.FALL);
	
	private int monthNumber;
	private Season season;
	
	public int getMonthNumber() {
		return this.monthNumber;
	}
	
	public Season getSeason() {
		return this.season;
	}
	
	private Month(int monthNo, Season season) {
		this.monthNumber = monthNo;
		this.season = season;
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
