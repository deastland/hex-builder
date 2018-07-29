package org.dizzle.utilities.misc;

import org.dizzle.utilities.dao.RegionTemperatureDao;
import org.dizzle.utilities.model.ClimaticRegion;
import org.dizzle.utilities.model.Month;
import org.dizzle.utilities.model.TempRange;
import org.dizzle.utilities.model.TempSpanChart;
import org.dizzle.utilities.model.WeatherTerrainType;

public class WeatherGenerator {

	// INFO ABOUT WHERE WE ARE
	
	
	
	// Internal values
	private TempRange[] possibleTempSpans = new TempRange[3];
	private int currentTempRange = 2;
	private boolean chancePrecipitation = false;
	private int streakCounter = 0;
	private boolean inStreak = false;
	private int windSpeed = 0;
	
	public void nextWeatherDay(ClimaticRegion climaticRegion, WeatherTerrainType weatherTerrainType, Month month) {
		// Get the chart of possible temps.
		pullTempSpanChart(climaticRegion, weatherTerrainType, month);
		
		// Using the current temp possibilities, calculate temp, wind, and precipitation.
		calculateTempRange(climaticRegion);
	}
	
	/**
	 * Uses the day-to-day change logic (Wilderness Survival Guide p. 109) to get the days temp range.
	 * @param tempSpanChart - A chart containing the high/mid/low temp spans for this time of year.
	 */
	private void calculateTempRange(ClimaticRegion climaticRegion) {
		
		int dieRoll1 = DieRoller.rollD6();
		int dieRoll2 = DieRoller.rollD6();
		int result = dieRoll1 + dieRoll2;
		int stepChange = 0;
		
		switch(result) {
		case(2):
			stepChange = -3;
			this.chancePrecipitation = true;
			break;
		case(3):
			stepChange = -2;
			changeWindSpeed(15, climaticRegion);
			this.chancePrecipitation = false;
			break;
		case(4):
			stepChange = -2;
			changeWindSpeed(10, climaticRegion);
			this.chancePrecipitation = true;
			break;
		case(5):
			stepChange = -1;
			changeWindSpeed(15, climaticRegion);
			this.chancePrecipitation = false;
			break;
		case(6):
			stepChange = -1;
			changeWindSpeed(10, climaticRegion);
			this.chancePrecipitation = true;
			
			// If a cold wave, roll of 6 is treated like a roll of 8.
			if (this.currentTempRange == 0 && inStreak) {
				stepChange = 1;
			}
			break;
		case(7):
			// If we are at max temp, treat a roll of 7 like a roll of 6.
			// If we are at min temp, treat a roll of 7 like a roll of 8.
			if (this.currentTempRange == 2) {
				stepChange = -1;
				this.chancePrecipitation = true;
			} else if (this.currentTempRange == 0) {
				stepChange = 1;
				this.chancePrecipitation = true;
			}
			break;
		case(8):
			stepChange = 1;
			this.chancePrecipitation = true;
			changeWindSpeed(-10, climaticRegion);

			// If a heat wave, roll of 8 treated like roll of 6.
			if (this.currentTempRange == 2 && inStreak) {
				stepChange = -1;
			}
			break;
		case(9):
			stepChange = 1;
			changeWindSpeed(-15, climaticRegion);
			this.chancePrecipitation = false;
			break;
		case(10):
			stepChange = 2;
			changeWindSpeed(-10, climaticRegion);
			this.chancePrecipitation = true;
			break;
		case(11):
			stepChange = 2;
			changeWindSpeed(-15, climaticRegion);
			this.chancePrecipitation = false;
			break;
		case(12):
			stepChange = 3;
			this.chancePrecipitation = true;
			break;
		default:
			break;
		}
		
		
		// Modify the temperature range as indicated in the variation roll above.
		// But don't go out of the possible range (0-2).
		currentTempRange += stepChange;
		if (currentTempRange < 0) {
			currentTempRange = 0;
		}
		
		if (currentTempRange > 2) {
			currentTempRange = 2;
		}
		
		// Check to see if we have a hot/cold weather streak ongoing.
		if (currentTempRange == 0 || currentTempRange == 2) {
			streakCounter++;
		} else {
			streakCounter = 0;
		}
		
		// If we have 2 consecutive days at min/max, we are in a streak.
		this.inStreak = streakCounter > 1;
		
	}
	
	/**
	 * For a given climatic regoin, terrain type, and month - pull the low, mid, and high temp range.
	 * 
	 * @param climaticRegion - The climatic region currently being checked (arctic, temperate, etc.)
	 * @param weatherTerrainType - The terrain type (desert, forest, etc.)
	 * @param month - The month of the year, which also gives us the season.
	 */
	private void pullTempSpanChart(ClimaticRegion climaticRegion, WeatherTerrainType weatherTerrainType, Month month) {

		RegionTemperatureDao tempDao = new RegionTemperatureDao();
		
		TempSpanChart todaysChart = tempDao.getTempSpanChart(climaticRegion, weatherTerrainType, month);
		
		this.possibleTempSpans[0] = todaysChart.getLowRange();
		this.possibleTempSpans[1] = todaysChart.getMidRange();
		this.possibleTempSpans[2] = todaysChart.getHighRange();
		
	}
	
	private void changeWindSpeed(int value, ClimaticRegion climaticRegion) {
		int tempWindSpeed = this.windSpeed;
		
		tempWindSpeed += value;
		if (tempWindSpeed > climaticRegion.getMaxWindSpeed()) {
			tempWindSpeed = climaticRegion.getMaxWindSpeed();
		} 
		
		if (tempWindSpeed < 0) {
			tempWindSpeed = 0;
		}
		
		this.windSpeed = tempWindSpeed;
		
	}
	
	@Override
	public String toString() {
		StringBuilder retStr = new StringBuilder("");
		
		retStr.append(" --------------------------------- WEATHER MANAGER -------------------------------------\n");
		retStr.append("Today's TempSpan: ").append(this.possibleTempSpans[this.currentTempRange].toString()).append("\n");
		retStr.append("Today's Windspeed: ").append(this.windSpeed).append("\n");
		
		return retStr.toString();
	}
}
