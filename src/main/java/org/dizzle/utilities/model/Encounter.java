package org.dizzle.utilities.model;

public class Encounter {
	
	private String name;
	private String numberEncountered;
	private int percentLair;
	private int percentTracks;	// This could be traces of any kind. See MelancholiesAndMirth for (spoor/tracks/traces)
	
	private String spoor;
	private String tracks;
	private String traces1;
	private String traces2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumberEncountered() {
		return numberEncountered;
	}

	public void setNumberEncountered(String numberEncountered) {
		this.numberEncountered = numberEncountered;
	}

	public int getPercentLair() {
		return percentLair;
	}

	public void setPercentLair(int percentLair) {
		this.percentLair = percentLair;
	}

	public int getPercentTracks() {
		return percentTracks;
	}

	public void setPercentTracks(int percentTracks) {
		this.percentTracks = percentTracks;
	}

	public String getSpoor() {
		return spoor;
	}

	public void setSpoor(String spoor) {
		this.spoor = spoor;
	}

	public String getTracks() {
		return tracks;
	}

	public void setTracks(String tracks) {
		this.tracks = tracks;
	}

	public String getTraces1() {
		return traces1;
	}

	public void setTraces1(String traces1) {
		this.traces1 = traces1;
	}

	public String getTraces2() {
		return traces2;
	}

	public void setTraces2(String traces2) {
		this.traces2 = traces2;
	}
	
	

}
