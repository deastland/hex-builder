package org.dizzle.utilities.misc;

import org.dizzle.utilities.model.Encounter;
import org.dizzle.utilities.model.EncounterCreature;
import org.dizzle.utilities.model.EncounterLocation;
import org.dizzle.utilities.model.Party;
import org.dizzle.utilities.model.TravelMode;

public class SwampEncounter {
	
	private int encounterChance = 16;	// Encounters occur on a 1 on 1d8 (approx 16 percent).
	private int locationChance = 50;	// Encounter location 50% of the time.
	
	
	private EncounterCreature[] encounterTable = {};

	private Encounter rollEncounter(Party party) {
		return getEncounter(party, 0);
	}
	
	// Get encounter, where the chance of an encounter is modified by a percentage.
	private Encounter getEncounter(Party party, int modifier) {
		
		// Encounter table

		//TODO: Handle logic for automatically encountering a location if it and the party
		// are both on HIGHWAY or RIVER location in the hex.

		int encounterRoll = DieRoller.rollPercentile();
		
		// If we roll over the encounter chance, no encounter occurs. 
		if (encounterRoll > this.encounterChance + modifier) {
			return null;
		}
		
		// We've determined that there is an encounter. So let's see what it is.
		
		// First determine if it is a location encounter.
		if (DieRoller.rollPercentile() <= this.locationChance) {

			// It is a location encounter!
			EncounterLocation location = party.getCurrentHex().getFeatures().get(0);
			
			// Stationary parties can not encounter locations.
			if (party.getTravelMode() == TravelMode.STATIONARY) {
				return null;
			}

			// If the location is 'hidden', another locationChance roll must be made.
			// If the roll fails, then no encounter occurs.
			if (location.getVisibility() < 0 && DieRoller.rollPercentile() > this.locationChance) {
				return null;
			}
			
			// We've run the gauntlet of die rolls and still have a location encounter.
			return location;
			
		}
		
		// We don't have a location encounter. Instead we have a creature encounter.
		encounterTable = SwampEncounterTable.getEncounterTable();
		
		// Roll on the table, using the size of the table for the 'die'.
		int dieRoll = DieRoller.rollAny(encounterTable.length) - 1;	// The -1 is because the array is 0 based but die always go from 1-n.
		EncounterCreature thisEncounter = encounterTable[dieRoll];
		

		
		return null;
	}

	public int getLocationChance() {
		return locationChance;
	}

	public void setLocationChance(int locationChance) {
		this.locationChance = locationChance;
	}
	
}
