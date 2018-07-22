package org.dizzle.utilities;

import org.dizzle.utilities.actions.TimeManager;
import org.dizzle.utilities.model.EncounterLocation;
import org.dizzle.utilities.model.GroundCondition;
import org.dizzle.utilities.model.LocationType;
import org.dizzle.utilities.model.Hex;
import org.dizzle.utilities.model.Party;
import org.dizzle.utilities.model.Season;
import org.dizzle.utilities.model.SpecialVenue;
import org.dizzle.utilities.model.TerrainType;
import org.dizzle.utilities.model.TravelMode;
import org.dizzle.utilities.model.VisibilityCondition;
import org.dizzle.utilities.model.WeatherCondition;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//////////////////// TIME MANAGER TEST ////////////////////////////
    	
    	// This is the calendar, the watch tracker, the encounter generator, 
    	// the weather maker, and the travel tracker for the hex map.
        TimeManager timeManager = new TimeManager();
        timeManager.setSeason(Season.SPRING);
        timeManager.setVisibilityCondition(VisibilityCondition.CLEAR);
        timeManager.setWeatherCondition(WeatherCondition.CLEAR);
        timeManager.setGroundCondition(GroundCondition.CLEAR);
        
        System.out.println(timeManager);
        
        ////////////////// CREATE TEST HEX /////////////////////////////////
        
        // Create a basic Hex.
        Hex testHex = new Hex(04, 01);
        testHex.setTerrainType(TerrainType.SWAMP);
        // Add a feature to the Hex.
        EncounterLocation feature = new EncounterLocation();
        feature.setName("Impact Crater");
        feature.setType(LocationType.Landmark);
        feature.setVisibility(1);
        
        testHex.getFeatures().add(feature);
       
        // Add another feature to the hex.
        EncounterLocation feature2 = new EncounterLocation();
        feature2.setName("Roc Nest");
        feature2.setType(LocationType.Lair);
        feature2.setVisibility(0);
        
        testHex.getFeatures().add(feature2);

        EncounterLocation feature3 = new EncounterLocation();
        feature3.setName("Ruins");
        feature3.setType(LocationType.Dungeon);
        feature3.setSpecialVenue(SpecialVenue.HIGHWAY);
        feature3.setVisibility(0);
        
        testHex.getFeatures().add(feature3);
        
        // Inspect the Hex.
        System.out.println(testHex);

        // Create a second Hex.
        Hex otherHex = new Hex(04,02);
        
        System.out.println("Hex 1 location: " + testHex.getLocation());
        System.out.println("Hex 2 location: " + otherHex.getLocation());
        
        // Check for adjacency.
        if (testHex.isAdjacentTo(otherHex)) {
        	System.out.println("The hexes are adjacent!\n");
        }
        
        ////////////////////// CREATE TEST PARTY ////////////////////////////////
        
        Party party = new Party();
        party.setBaseSpeed(30);
        party.setCurrentHex(testHex);
        party.setName("Hex Hellions");
        party.setTravelMode(TravelMode.STANDARD);
        
        System.out.println("Party start point:\n" + party);
        /////////////// HAVE THE PARTY EXPEND A WATCH AND CHECK FOR ENCOUNTERS /////////////////
        
        System.out.println("About to try to have an encounter...");
        
        System.out.println("Encounter is: \n" + timeManager.passWatch(party));
        
        System.out.println("Party end point: \n" + party);
    }
}
