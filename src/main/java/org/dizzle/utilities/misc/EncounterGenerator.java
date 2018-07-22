package org.dizzle.utilities.misc;

import java.util.List;

import org.dizzle.utilities.model.Encounter;
import org.dizzle.utilities.model.EncounterCreature;
import org.dizzle.utilities.model.EncounterLocation;
import org.dizzle.utilities.model.Party;
import org.dizzle.utilities.model.SpecialVenue;
import org.dizzle.utilities.model.TravelTerrainType;
import org.dizzle.utilities.model.TravelMode;

public class EncounterGenerator {
	
	private int encounterChance = 16;	// Encounters occur on a 1 on 1d8 (approx 16 percent).
	private int locationChance = 50;	// Encounter location 50% of the time.
	private EncounterCreature[] encounterTable = {};

	public Encounter rollEncounter(Party party) {
		return getEncounter(party, 0);
	}
	
	// Get encounter, where the chance of an encounter is modified by a percentage.
	public Encounter getEncounter(Party party, int modifier) {
		
		// If the party is traveling in EXPLORE mode, the chance of an encounter is doubled.
		if (party.getTravelMode() == TravelMode.EXPLORE || party.getTravelMode() == TravelMode.CAREFUL_EXPLORE) {
			modifier += this.encounterChance;
		}
		
		// If the party is traveling a special venue (highway, river, etc.) then it automatically encounters 
		// a location that is on that venue.
		if (party.getSpecialVenue() != null) {
			EncounterLocation specialLocation = getEncounterLocation(party.getCurrentHex().getFeatures(), party.getSpecialVenue());
			return specialLocation;
		}

		int encounterRoll = DieRoller.rollPercentile();
		
		// If we roll over the encounter chance, no encounter occurs. 
		if (encounterRoll > this.encounterChance + modifier) {
			return null;
		}
		
		// We've determined that there is an encounter. So let's see what it is.
		
		// First determine if it is a location encounter.
		if ((DieRoller.rollPercentile()) <= this.locationChance) {

			// It is a location encounter!
			EncounterLocation location = party.getCurrentHex().getFeatures().get(0);
			
			// Stationary parties can not encounter locations.
			if (party.getTravelMode() == TravelMode.STATIONARY) {
				return null;
			}

			// If the location is 'hidden', another locationChance roll must be made.
			// If the roll fails, then no encounter occurs.
			if (location.getVisibility() < 0 && (DieRoller.rollPercentile()) > this.locationChance) {
				return null;
			}
			
			// We've run the gauntlet of die rolls and still have a location encounter.
			return location;
			
		}
		
		// Not a location encounter. So check for creature encounter.
		
		// If the party is traveling cautiously, there is a 50% chance that a creature encounter won't happen.
		if (party.getTravelMode() == TravelMode.CAREFUL || party.getTravelMode() == TravelMode.CAREFUL_EXPLORE) {
			if (DieRoller.rollPercentile() > 50) {
				return null;
			}
		}
		
		// Roll on the appropriate table and return the creature encountered.
		EncounterCreature theEncounter = rollCreatureEncountered(party.getCurrentHex().getTerrainType());

		// If the party is moving, check to see if they discover a lair or tracks.
		if (party.getTravelMode() != TravelMode.STATIONARY) {
			// Tracks?
			if (DieRoller.rollPercentile() <= theEncounter.getPercentTracks()) {
				theEncounter.setEvidence(true);
			// Lair?
			} else if (DieRoller.rollPercentile() <= theEncounter.getPercentLair()) {
				theEncounter.setLair(true);
			}
		}
		
		return theEncounter;
	}

	public int getLocationChance() {
		return locationChance;
	}

	public void setLocationChance(int locationChance) {
		this.locationChance = locationChance;
	}
	
	// Given a special venue, return the location at that venue. If none exists, return null.
	private EncounterLocation getEncounterLocation(List<EncounterLocation> locations, SpecialVenue partyTravelVenue) {
		EncounterLocation returnLocation = null;
		
		for (EncounterLocation location : locations) {
			if (location.getSpecialVenue() == partyTravelVenue) {
				return location;
			}
		}
		
		return returnLocation;
	}
	
	private EncounterCreature rollCreatureEncountered(TravelTerrainType terrainType) {
		EncounterCreature returnEncounter = null;

		switch(terrainType) {
		case BADLAND:
			break;
		case DESERT:
			break;
		case RIVER:
			break;
		case FOREST:
			break;
		case GRASSLAND:
			break;
		case HILL:
			break;
		case JUNGLE:
			break;
		case MOOR:
			break;
		case MOUNTAIN:
			break;
		case SEA:
			break;
		case SWAMP:
			// Okay, roll to see what creature encounter we have.
			encounterTable = SwampEncounterTable.getEncounterTable();
			break;
		case TUNDRA:
			break;
		case VOLCANIC:
			break;
		case WATER:
			break;
		default:
			break;
		
		}
		
		
		// Roll on the table, using the size of the table for the 'die' size.
		int dieRoll = DieRoller.rollAny(encounterTable.length) - 1;	// The -1 is because the array is 0 based but die always go from 1-n.
		returnEncounter = encounterTable[dieRoll];

		return returnEncounter;
	}
	
}
