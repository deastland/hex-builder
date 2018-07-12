package org.dizzle.utilities.misc;

import org.dizzle.utilities.model.Encounter;
import org.dizzle.utilities.model.Party;
import org.dizzle.utilities.model.TravelMode;

public class PlainsEncounter {
	
	private int encounterChance = 1;	// Encounters occur on a 1 on 1d8.
	
	
	private String[] encounterTable = {};

	private Encounter rollEncounter(Party party) {
		return rollEncounter(party, 0);
	}
	
	private Encounter rollEncounter(Party party, int modifier) {
		
		// Lizardmen
		Encounter lizardmanEncounter = new Encounter();
		lizardmanEncounter.setName("Lizardmen");
		lizardmanEncounter.setNumberEncountered("1d8");
		lizardmanEncounter.setPercentLair(30);
		lizardmanEncounter.setPercentTracks(50);
		
		// Giant leech
		Encounter leechEncounter = new Encounter();
		leechEncounter.setName("Giant Leech");
		leechEncounter.setNumberEncountered("1d3");
		leechEncounter.setPercentLair(0);
		leechEncounter.setPercentTracks(0);
		
		// Tree trolls
		Encounter treeTrollEncounter = new Encounter();
		treeTrollEncounter.setName("Tree Troll");
		treeTrollEncounter.setNumberEncountered("1d2");
		treeTrollEncounter.setPercentLair(40);
		treeTrollEncounter.setPercentTracks(50);
		
		// Encounter table
		Encounter[] encTable = new Encounter[8];
		encTable[0] = lizardmanEncounter;
		encTable[1] = lizardmanEncounter;
		encTable[2] = lizardmanEncounter;
		encTable[3] = lizardmanEncounter;
		encTable[4] = leechEncounter;
		encTable[5] = leechEncounter;
		encTable[6] = leechEncounter;
		encTable[7] = treeTrollEncounter;
		
		
		int encounterRoll = DieRoller.rollD8();
		
		// Only if we roll the encounter chance (or less) 
		if (encounterRoll <= this.encounterChance + modifier) {
			return null;
		}
		
		// We've determined that there is an encounter. So let's see what it is.
		
		
		// Party isn't moving, so they can't encounter a lair.
		if (party.getTravelMode().equals(TravelMode.STATIONARY)) {
			
		}
		
		
		
		return null;
	}
	
}
