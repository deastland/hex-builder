package org.dizzle.utilities.model;

/**
 * This will be used for the temperature tables to generate weather.
 * There will be 26 sets of high/low temps which can be assigned to different weather zones and terrain types depending on season.
 * 
 * @author Zeus
 *
 */
public class TempRange {
	
	private int high;
	private int low;
	
	public TempRange() {}
	
	public TempRange(int hi, int lo) {
		this.high = hi;
		this.low = lo;
	}
	
	public void reset(int hi, int lo) {
		this.high = hi;
		this.low = lo;
	}
	
	public int getHigh() {
		return high;
	}
	
	public void setHigh(int high) {
		this.high = high;
	}
	
	public int getLow() {
		return low;
	}
	
	public void setLow(int low) {
		this.low = low;
	}
	
	@Override
	public String toString() {
		return this.high + "|" + this.low;
	}

}
