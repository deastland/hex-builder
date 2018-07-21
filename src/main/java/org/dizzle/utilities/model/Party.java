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
	
	private int id;
	private String name;
	
	//TODO: Determine if currentHex should be an actual Hex object or just a set of coordinates.
	
	private Hex currentHex;						// The hex in which the party is currently residing.
	private TravelMode travelMode;				// The party current party 'stance' (stationary, foraging, fast, etc.)
	private SpecialVenue specialVenue = null;	// If the party is on a highway or river or other special travel way.
	private double hexMilesTraveled;			// The number of travel miles accumulated in the current hex. It takes 12 to exit a hex.
	
	private int baseSpeed = 30;					// The base ground speed of the slowest party member.
	
	private int navigatorSkill;					// The survival bonus for the character navigating.
	private int quartermasterSkill;				// Some skill might be needed for the quartermaster.
	private int scoutSkill;						// The perception bonus for the character scouting.
	
	// Functional Methods
	
	public int rollNavigateSkill() {
		return DieRoller.rollD20() + navigatorSkill;
	}
	
	public int rollQuartermasterSkill() {
		return DieRoller.rollD20() + quartermasterSkill;
	}
	
	public int rollScoutSkill() {
		return DieRoller.rollD20() + scoutSkill;
	}
	
	public boolean checkNavigation(int target) {
		return rollNavigateSkill() >= target;
	}
	
	public boolean checkQuartermaster(int target) {
		return rollQuartermasterSkill() >= target;
	}
	
	public void enterNewHex(Hex hex) {
		this.currentHex = hex;
		this.hexMilesTraveled = 0;
	}
	
	// Based on the move speed of the party, calculate how many miles per watch it can travel.
	public double getMilesPerWatch() {
		
		return this.baseSpeed * 0.4;
	}
	
	public void addHexMilesTraveled(double addedMiles) {
		this.hexMilesTraveled += addedMiles;
	}
	
	////////// CONSTRUCTORS /////////////////
	
	public Party() {};
	
	public Party(String name) {
		this.name = name;
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

	public int getBaseSpeed() {
		return baseSpeed;
	}

	public void setBaseSpeed(int baseSpeed) {
		this.baseSpeed = baseSpeed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHexMilesTraveled() {
		return hexMilesTraveled;
	}

	public void setHexMilesTraveled(double hexMilesTraveled) {
		this.hexMilesTraveled = hexMilesTraveled;
	}

	public SpecialVenue getSpecialVenue() {
		return specialVenue;
	}

	public void setSpecialVenue(SpecialVenue specialVenue) {
		this.specialVenue = specialVenue;
	}

	///////////////////// MAIN FOR TESTING //////////////////////////////
	public static void main(String[] args) {
		
		Party party = new Party();
		party.setBaseSpeed(30);
		
		System.out.println("Party base speed: " + party.getBaseSpeed());
		System.out.println(("Party map speed per watch: " + party.getMilesPerWatch()));
	}
	
	@Override
	public String toString() {
		StringBuffer retStr = new StringBuffer("");
		
		retStr.append(" ------------------- PARTY -------------------\n");
		retStr.append("Name: ").append(name).append("\n");
		retStr.append("CurrentHex: ").append(currentHex.getLocation()).append("\n");
		retStr.append("Travel Mode: ").append(this.travelMode).append("\n");
		retStr.append("Hex-miles traveled: ").append(hexMilesTraveled).append("\n");
		
		return retStr.toString();
	}

}
