package org.dizzle.utilities.actions;

import org.dizzle.utilities.misc.EncounterGenerator;
import org.dizzle.utilities.model.Encounter;
import org.dizzle.utilities.model.EncounterCreature;
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
public class WatchManager {
	
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
		int terrainSpeedModifier = hex.getTerrainType().getSpeedModifier();

		// Encounter Check. This should, based on the terrain type, roll for encounter chance and then
		// if there is an encounter, specify the encounter itself.
		switch(hex.getTerrainType()) {
		case SWAMP:
			System.out.println("Do swamp stuff");
			EncounterGenerator swampEncounter = new EncounterGenerator();
			watchEncounter = swampEncounter.rollEncounter(party);
			break;
		case MOOR:
			System.out.println("Do moor stuff");
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
		case UPSTREAM:
			System.out.println("Do upstream stuff");
			break;
		case DOWNSTREAM:
			System.out.println("Do downstream stuff");
			break;
		case TUNDRA:
			System.out.println("Do highway stuff");
			break;
		case VOLCANIC:
			break;
		default:
			break;
		}
		
		incrementWatch();
		
		return watchEncounter;
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
	
	public EncounterCreature checkForEncounter(Party party) {
		EncounterCreature enc = null;
		
		
		
		return enc;
	}

	public static void main(String[] args) {
		WatchManager watchManager = new WatchManager();
		
	}
	
	@Override
	public String toString() {
		StringBuffer retStr = new StringBuffer();
		
		retStr.append("WATCH -- Day: ").append(this.day).append(". Watch: ").append(watch).append("\n");
		
		return retStr.toString();
	}
	
}
