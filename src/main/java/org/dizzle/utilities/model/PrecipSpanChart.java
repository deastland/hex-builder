package org.dizzle.utilities.model;

public class PrecipSpanChart {

	private PrecipitationAmount lowPrecip;
	private PrecipitationAmount midPrecip;
	private PrecipitationAmount highPrecip;
	
	public PrecipSpanChart() {}
	
	public PrecipSpanChart(int low, int mid, int high) {
		this.lowPrecip = PrecipitationAmount.getPrecipFromNumber(low);
		this.midPrecip = PrecipitationAmount.getPrecipFromNumber(mid);
		this.highPrecip = PrecipitationAmount.getPrecipFromNumber(high);
	}
	
	public PrecipitationAmount getLowPrecip() {
		return lowPrecip;
	}
	
	public void setLowPrecip(PrecipitationAmount lowPrecip) {
		this.lowPrecip = lowPrecip;
	}
	
	public PrecipitationAmount getMidPrecip() {
		return midPrecip;
	}
	
	public void setMidPrecip(PrecipitationAmount midPrecip) {
		this.midPrecip = midPrecip;
	}
	
	public PrecipitationAmount getHighPrecip() {
		return highPrecip;
	}
	
	public void setHighPrecip(PrecipitationAmount highPrecip) {
		this.highPrecip = highPrecip;
	}
	
	@Override
	public String toString() {
		StringBuffer retStr = new StringBuffer("");
		
		retStr.append(" ----------- PRECIPITATION SPAN CHART ----------").append("\n");
		retStr.append("Low Precip: ").append(this.lowPrecip).append("\n");
		retStr.append("Mid Precip: ").append(this.midPrecip).append("\n");
		retStr.append("High Precip: ").append(this.highPrecip).append("\n");
		
		
		return retStr.toString();
	}
	
}
