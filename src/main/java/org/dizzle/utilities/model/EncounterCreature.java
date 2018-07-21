package org.dizzle.utilities.model;

import org.dizzle.utilities.misc.DieRoller;

public class EncounterCreature extends Encounter {
	
	private String numberEncountered;
	private int percentLair;
	private int percentTracks;	// This could be traces of any kind. See MelancholiesAndMirth for (spoor/tracks/traces)
	
	private String spoor;
	private String tracks;
	private String traces1;
	private String traces2;

	//////// GETTERS AND SETTERS //////////
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
	
	@Override
	public String toString() {
		StringBuffer retStr = new StringBuffer("");
		
		retStr.append(" ------------------- CREATURE ENCOUNTER -------------------\n");
		retStr.append("Monster: ").append(this.name).append("\n");
		retStr.append("Number Encountered: ").append(this.numberEncountered).append("\n");
		retStr.append("Actual number: ").append(DieRoller.rollDice(this.numberEncountered)).append("\n");
		retStr.append("Chance of lair: ").append(this.percentLair).append("\n");
		retStr.append("Chance of tracks: ").append(this.percentTracks).append("\n");
		
		return retStr.toString();
	}

}
