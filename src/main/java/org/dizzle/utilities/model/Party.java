package org.dizzle.utilities.model;

import org.dizzle.utilities.misc.DieRoller;

/**
 * This class will represent the adventuring party as it pertains to hex crawling.
 * A party can be moving, rushing, searching, camping, etc.
 * Each will have an effect on what happens in a given hex over a watch.
 * 
 * @author deastland
 *
 */
public class Party {
	
	private Hex currentHex;				// The hex in which the party is currently residing.
	private TravelMode travelMode;		// The party current party 'stance' (stationary, slow move, fast move, etc.)
	
	private int navigatorSkill;			// The survival bonus for the character navigating.
	private int quartermasterSkill;		// Some skill might be needed for the quartermaster.
	private int scoutSkill;				// The perception bonus for the character scouting.
	
	public int checkNavigateSkill() {
		return DieRoller.rollD20() + navigatorSkill;
	}
	
	public int checkQuartermasterSkill() {
		return DieRoller.rollD20() + quartermasterSkill;
	}
	
	public int checkScoutSkill() {
		return DieRoller.rollD20() + scoutSkill;
	}
	
	////////// GETTERS & SETTERS ///////////////
	
	public TravelMode getTravelMode() {
		return travelMode;
	}
	
	public void setTravelMode(TravelMode travelMode) {
		this.travelMode = travelMode;
	}
	
	public int getNavigatorSkill() {
		return navigatorSkill;
	}
	
	public void setNavigatorSkill(int navigatorSkill) {
		this.navigatorSkill = navigatorSkill;
	}
	
	public int getQuartermasterSkill() {
		return quartermasterSkill;
	}
	
	public void setQuartermasterSkill(int quartermasterSkill) {
		this.quartermasterSkill = quartermasterSkill;
	}
	
	public int getScoutSkill() {
		return scoutSkill;
	}
	
	public void setScoutSkill(int scoutSkill) {
		this.scoutSkill = scoutSkill;
	}

	public Hex getCurrentHex() {
		return currentHex;
	}

	public void setCurrentHex(Hex currentHex) {
		this.currentHex = currentHex;
	}


	
}
