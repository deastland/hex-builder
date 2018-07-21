package org.dizzle.utilities.misc;

import java.util.ArrayList;
import java.util.List;

import org.dizzle.utilities.model.EncounterCreature;

public class SwampEncounterTable {

	public static EncounterCreature[] getEncounterTable() {
		List<EncounterCreature> encounterList = new ArrayList<EncounterCreature>();
		
		// Lizardmen
		EncounterCreature lizardmanEncounter = new EncounterCreature();
		lizardmanEncounter.setName("Lizardmen");
		lizardmanEncounter.setNumberEncountered("1d8");
		lizardmanEncounter.setPercentLair(30);
		lizardmanEncounter.setPercentTracks(50);
		encounterList.add(lizardmanEncounter);
		encounterList.add(lizardmanEncounter);
		encounterList.add(lizardmanEncounter);
		
		// Giant leech
		EncounterCreature leechEncounter = new EncounterCreature();
		leechEncounter.setName("Giant Leech");
		leechEncounter.setNumberEncountered("1d3");
		leechEncounter.setPercentLair(0);
		leechEncounter.setPercentTracks(0);
		encounterList.add(leechEncounter);
		
		// Tree trolls
		EncounterCreature treeTrollEncounter = new EncounterCreature();
		treeTrollEncounter.setName("Tree Troll");
		treeTrollEncounter.setNumberEncountered("1d2");
		treeTrollEncounter.setPercentLair(40);
		treeTrollEncounter.setPercentTracks(50);
		encounterList.add(treeTrollEncounter);
		encounterList.add(treeTrollEncounter);
		
		// Zombies
		EncounterCreature zombieEncounter = new EncounterCreature();
		zombieEncounter.setName("Zombie");
		zombieEncounter.setNumberEncountered("2d4");
		zombieEncounter.setPercentLair(25);
		zombieEncounter.setPercentTracks(50);
		encounterList.add(zombieEncounter);
		encounterList.add(zombieEncounter);
		encounterList.add(zombieEncounter);
		
		EncounterCreature[] encTable = (EncounterCreature[])encounterList.toArray();

		return encTable;
	}
}
