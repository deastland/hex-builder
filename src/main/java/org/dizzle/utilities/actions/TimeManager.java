package org.dizzle.utilities.actions;

import org.dizzle.utilities.misc.DieRoller;
import org.dizzle.utilities.misc.EncounterGenerator;
import org.dizzle.utilities.model.Encounter;
import org.dizzle.utilities.model.GroundCondition;
import org.dizzle.utilities.model.Hex;
import org.dizzle.utilities.model.Party;
import org.dizzle.utilities.model.Season;
import org.dizzle.utilities.model.TravelMode;
import org.dizzle.utilities.model.VisibilityCondition;
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
	private WeatherCondition weatherCondition = WeatherCondition.CLEAR;
	private GroundCondition groundCondition = GroundCondition.CLEAR;
	private VisibilityCondition visibilityCondition = VisibilityCondition.CLEAR;
	private int weatherModifier = 0;	// This can increase if the chance for bad weather increases. Goes back to zero when CLEAR/WARM/COOL.
	
	
	/////////// Functional methods //////////////
	
	public Encounter passWatch(Party party) {

		// IF party is moving then...
		// 1) Calculate party move speed.
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
		
		// Visibility modifier.
		speedMultiplier *= visibilityCondition.getSpeedModifier() * .01;
		
		// Ground condition modifier.
		speedMultiplier *= groundCondition.getSpeedModifier() * .01;
		
		// Weather modifier.
		speedMultiplier *= weatherCondition.getSpeedModifier() * .01;
		
		// If party isn't moving, no distance can be traveled.
		if (party.getTravelMode() == TravelMode.STATIONARY) {
			speedMultiplier = 0;
		}
		
		// Figure out distance added to total hex miles traveled and add it to the party.
		double addedDistance = party.getMilesPerWatch() * speedMultiplier;
		party.addHexMilesTraveled(addedDistance);
		
		//TODO: Eventually we will roll for at what point in a watch the encounter happened and pro-rate the distance. Maybe.
		
		return watchEncounter;
	}
	
	public boolean isDay() {
		return watch < season.getSunsetWatch();
	}

	public void incrementWatch() {
		this.watch++;
		if (this.watch > 6) {
			this.day++;
			this.watch = 1;
		}
	}
	
	/**
	 * Calculate the kind of weather currently taking place.
	 * 
	 * @param inclimentModifier - Percent increase in the chance of incliment weather.
	 * @return
	 */
	public WeatherCondition getCurrentWeather(int inclimentModifier) {
		WeatherCondition weatherCondition = null;

		// Base chance of weather turning worse is 10%.
		double inclimentChance = 10 + inclimentModifier;
		
		switch(season) {
		case FALL:
			// The base weather condition for this season. 
			weatherCondition = WeatherCondition.CLEAR;
			break;
		case SPRING:
			// The base weather condition for this season.
			weatherCondition = WeatherCondition.CLEAR;
			
			break;
		case SUMMER:
			// The base weather condition for this season.
			weatherCondition = WeatherCondition.WARM;
			break;
		case WINTER:
			// The base weather condition for this season.
			weatherCondition = WeatherCondition.COOL;
			
			// Weather gets worse if roll below.
			if (DieRoller.rollPercentile() <= inclimentChance) {
				
			}
			
			// Calculate chance that any incliment weather is happening.
			break;
		default:
			break;
		
		}
		
		
		return weatherCondition;
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
	
	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
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

	public int getWeatherModifier() {
		return weatherModifier;
	}

	public void setWeatherModifier(int weatherModifier) {
		this.weatherModifier = weatherModifier;
	}

	public GroundCondition getGroundCondition() {
		return groundCondition;
	}

	public void setGroundCondition(GroundCondition groundCondition) {
		this.groundCondition = groundCondition;
	}

	public VisibilityCondition getVisibilityCondition() {
		return visibilityCondition;
	}

	public void setVisibilityCondition(VisibilityCondition visibilityCondition) {
		this.visibilityCondition = visibilityCondition;
	}

}
