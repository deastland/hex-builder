package org.dizzle.utilities.actions;

import org.dizzle.utilities.misc.DieRoller;
import org.dizzle.utilities.misc.EncounterGenerator;
import org.dizzle.utilities.model.Encounter;
import org.dizzle.utilities.model.GroundCondition;
import org.dizzle.utilities.model.Hex;
import org.dizzle.utilities.model.Month;
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
	private int year = 1;															// Year
	private Month month = Month.Hjarnfrang;											// Month of the year
	private int dayOfMonth = 1;														// Day of the month (1st, 14th, 30th, etc.)
	private int campaignDay = 1;													// Days into the campaign, in total.
	private int watch = 1;															// The current watch (six watches a day)
	private Season season = Season.SPRING;											// The current season
	private WeatherCondition weatherCondition = WeatherCondition.CLEAR;				// What is the weather like, in general?
	private GroundCondition groundCondition = GroundCondition.CLEAR;				// Is the ground covered in snow? Muddy?
	private VisibilityCondition visibilityCondition = VisibilityCondition.CLEAR;	// How easily can a person see
	
	
	
	private int weatherModifier = 0;	// This can increase if the chance for bad weather increases. Goes back to zero when CLEAR/WARM/COOL.
	
	
	/////////// Functional methods //////////////
	
	public Encounter passWatch(Party party) {
		
		/*
		 * 1) Roll for encounter.
		 * 2) Increment the watch (4 hours)
		 * 3) Calculate movement
		 *   - Adjust for visibility
		 *   - Adjust for weather
		 *   - Adjust for terrain
		 *   
		 * 4) Add miles to party move.
		 */

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
		// End of day? Go to the next day.
		if (this.watch > 6) {
			incrementCampaignDay();
			this.watch = 1;
		}
	}
	
	public void incrementCampaignDay() {
		this.campaignDay++;
		this.dayOfMonth++;
		// End of month? Go to the next month.
		if (this.dayOfMonth > 30) {
			int monthCounter = month.getMonthNumber();
			// End of year? Go to the next year.
			if (monthCounter < 12) {
				monthCounter++;
			} else {
				monthCounter = 1;
				this.year++;
			}
			
			this.month = Month.getMonthFromNumber(monthCounter);
			this.dayOfMonth = 1;
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

		// Base chance of weather turning worse is 10%. If you miss the roll, weather gets better.
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
				// Chance to transition to COLD, STORM, HEAVY STORM, BLIZZARD
				switch(weatherCondition) {
				case BLIZZARD:
					break;
				case CLEAR:
					break;
				case COLD:
					weatherCondition = WeatherCondition.STORM;
					inclimentChance += 10;
					break;
				case COOL:
					weatherCondition = WeatherCondition.COLD;
					inclimentChance += 10;
					break;
				case HEAVY_STORM:
					weatherCondition = WeatherCondition.BLIZZARD;
					break;
				case FOG:
					break;
				case STORM:
					weatherCondition = WeatherCondition.HEAVY_STORM;
					break;
				case WARM:
					break;
				default:
					break;
				
				}
			}
			
			// Calculate chance that any incliment weather is happening.
			break;
		default:
			break;
		
		}
		
		
		return weatherCondition;
	}
	
	///////////// GETTERS AND SETTERS /////////////////

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

	public WeatherCondition getWeatherCondition() {
		return weatherCondition;
	}

	public void setWeatherCondition(WeatherCondition weatherCondition) {
		this.weatherCondition = weatherCondition;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public int getCampaignDay() {
		return campaignDay;
	}

	public void setCampaignDay(int campaignDay) {
		this.campaignDay = campaignDay;
	}

	public static void main(String[] args) {
		//TODO: Maybe some testing here?
		
		Month myMonth = Month.getMonthFromNumber(3);
		
		System.out.println("Month: " + myMonth);
		
	}
	
	@Override
	public String toString() {
		StringBuffer retStr = new StringBuffer();
		
		retStr.append(" ------------------- TIME MANAGER -------------------\n");
		retStr.append("WATCH -- Day: ").append(this.campaignDay).append(". Watch: ").append(watch).append("\n");
		retStr.append("Weather: ").append(this.weatherCondition).append("\n");
		retStr.append("Visibilty: ").append(this.visibilityCondition).append("\n");
		retStr.append("Ground: ").append(this.groundCondition).append("\n");
		
		return retStr.toString();
	}

}
