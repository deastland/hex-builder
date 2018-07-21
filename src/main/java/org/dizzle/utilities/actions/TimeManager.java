package org.dizzle.utilities.actions;

import org.dizzle.utilities.model.Encounter;
import org.dizzle.utilities.model.Hex;
import org.dizzle.utilities.model.Party;

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

	public void passWatch(Party party) {

		Hex hex = party.getCurrentHex();
		
		switch(hex.getTerrainType()) {
		case SWAMP:
			System.out.println("Do swamp stuff");
			break;
		case FOREST:
			System.out.println("Do forest stuff");
			break;
		case GRASSLAND:
			System.out.println("Do grasslands stuff");
			break;
		case HILL:
			System.out.println("Do hills stuff");
			break;
		case MOUNTAIN:
			System.out.println("Do mountain stuff");
			break;
		case DESERT:
			System.out.println("Do desert stuff");
			break;
		case JUNGLE:
			System.out.println("Do jungle stuff");
			break;
		case BADLAND:
			System.out.println("Do badland stuff");
			break;
		case SEA:
			System.out.println("Do sea stuff");
			break;
		case WATER:
			System.out.println("Do water stuff");
			break;
		default:
			System.out.println("I have no idea where you are!");
			break;
		}
		
		incrementWatch();
		
	}

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
	
	public Encounter checkForEncounter(Party party) {
		Encounter enc = null;
		
		
		
		return enc;
	}

	public static void main(String[] args) {
		TimeManager timeManager = new TimeManager();
		
	}
	
}
