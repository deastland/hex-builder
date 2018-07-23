package org.dizzle.utilities.model;

public class TempSpanChart {

	private TempRange lowRange;
	private TempRange midRange;
	private TempRange highRange;
	
	public TempSpanChart() {}
	
	public TempSpanChart(TempRange low, TempRange mid, TempRange high) {
		this.lowRange = low;
		this.midRange = mid;
		this.highRange = high;
	}
	
	public TempRange getLowRange() {
		return lowRange;
	}
	public void setLowRange(TempRange lowRange) {
		this.lowRange = lowRange;
	}
	public TempRange getMidRange() {
		return midRange;
	}
	public void setMidRange(TempRange midRange) {
		this.midRange = midRange;
	}
	public TempRange getHighRange() {
		return highRange;
	}
	public void setHighRange(TempRange highRange) {
		this.highRange = highRange;
	}

	@Override
	public String toString() {
		StringBuffer retStr = new StringBuffer("");
		
		retStr.append(" ----------------- TEMP SPAN CHART ---------------").append("\n");
		retStr.append("Low Range: ").append(this.lowRange).append("\n");
		retStr.append("Mid Range: ").append(this.midRange).append("\n");
		retStr.append("High Range: ").append(this.highRange).append("\n");
		
		return retStr.toString();
	}

	
}
