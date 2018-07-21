package org.dizzle.utilities.actions;

import org.dizzle.utilities.misc.EncounterGenerator;
import org.dizzle.utilities.model.Encounter;
import org.dizzle.utilities.model.EncounterCreature;
import org.dizzle.utilities.model.Hex;
import org.dizzle.utilities.model.Party;
import org.dizzle.utilities.model.Season;
import org.dizzle.utilities.model.WeatherCondition;

/**
 * This object will calculate what happens during a watch in a hex.
 * It could return no encounter (most likely)
 * It could return a Feature of that hex.
 * It could return an encounter from the encounter table for this hex.
 * 
 * @author deastland
 *
 */
public class TimeManager {
	
	// There are six 4-hour watches in a day. A day starts with sunrise, rather than on a clock.
	// A typical day.
	// Watch 1: Day - Break Camp
	// Watch 2: Day - Travel
	// Watch 3: Day - Travel
	// Watch 4: Night - Set Camp/Rest
	// Watch 5: Night - Camp/Rest
	// Watch 6: Night - Camp/Rest 
	private int day = 1;
	private int watch = 1;
	private Season season = Season.SPRING;
	private WeatherCondition weather = WeatherCondition.CLEAR;
	
	//TODO: Seasons? Summer have 4 day watches. Winter have 4 night watches.

	/////////// Fucntional methods //////////////
	
	public Encounter passWatch(Party party) {

		// IF party is moving then...
		// Calculate party move speed.
		// ENCOUNTER CHECK
		// Add move speed to "distance traveled in hex"
		//   If hex distance > 12
		//     enter new hex
		//   else
		//     no change in hex.
		
		Hex hex = party.getCurrentHex();
		Encounter watchEncounter = null;

		// Calculate party speed

		// Roll for an encounter. Take into account the terrain and venue of the party.
		EncounterGenerator encounterGenerator = new EncounterGenerator();
		watchEncounter = encounterGenerator.rollEncounter(party);
		
		// Tick of the watch clock.
		incrementWatch();
		
		// Calculate miles traveled.
		// Start off assuming the party is moving through the base terrain.
		double speedMultiplier = hex.getTerrainType().getSpeedModifier() * .01;
		
		System.out.println("Base speed mod for hex: " + speedMultiplier);
		
		// Multiply by the type of travel (fast, careful, explore, etc.)
		speedMultiplier *= party.getTravelMode().getSpeedModifier() * .01;
		
		System.out.println("Speed mod after party travel mode: " + speedMultiplier);
		
		// If traveling in a special venue, override the speed multiplier.
		if (party.getSpecialVenue() != null) {
			speedMultiplier = party.getSpecialVenue().getSpeedModifier() * .01;
		}
		
		// Weather modifier.
		speedMultiplier *= weather.getSpeedModifier() * .01;
		
		// Figure out distance added to total hex miles traveled and add it to the party.
		double addedDistance = party.getMilesPerWatch() * speedMultiplier;
		party.addHexMilesTraveled(addedDistance);
		
		//TODO: Eventually we will roll for at what point in a watch the encounter happened and pro-rate the distance. Maybe.
		
		return watchEncounter;
	}
	
	public boolean isDay() {
		return watch < season.getSunsetWatch();
	}

	///////////// GETTERS AND SETTERS /////////////////
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getWatch() {
		return watch;
	}

	public void setWatch(int watch) {
		this.watch = watch;
	}
	
	public void incrementWatch() {
		this.watch++;
		if (this.watch > 6) {
			this.day++;
			this.watch = 1;
		}
	}
	
	public EncounterCreature checkForEncounter(Party party) {
		EncounterCreature enc = null;
		
		
		
		return enc;
	}

	public static void main(String[] args) {
		//TODO: Maybe some testing here?
		
	}
	
	@Override
	public String toString() {
		StringBuffer retStr = new StringBuffer();
		
		retStr.append("WATCH -- Day: ").append(this.day).append(". Watch: ").append(watch).append("\n");
		
		return retStr.toString();
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public WeatherCondition getWeather() {
		return weather;
	}

	public void setWeather(WeatherCondition weather) {
		this.weather = weather;
	}
	
}
