package org.dizzle.utilities;

import org.dizzle.utilities.actions.TimeManager;
import org.dizzle.utilities.model.Feature;
import org.dizzle.utilities.model.FeatureType;
import org.dizzle.utilities.model.Hex;
import org.dizzle.utilities.model.TerrainType;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        TimeManager timeManager = new TimeManager();
        
        // Create a basic Hex.
        Hex testHex = new Hex(04, 01);
        testHex.setTerrainType(TerrainType.FOREST);
        // Add a feature to the Hex.
        Feature feature = new Feature();
        feature.setName("Impact Crater");
        feature.setType(FeatureType.Landmark);
        feature.setVisibility(1);
        
        testHex.getFeatures().add(feature);
       
        // Add another feature to the hex.
        Feature feature2 = new Feature();
        feature2.setName("Roc Nest");
        feature2.setType(FeatureType.Lair);
        feature2.setVisibility(0);
        
        testHex.getFeatures().add(feature2);

        Feature feature3 = new Feature();
        feature3.setName("Ruins");
        feature3.setType(FeatureType.Dungeon);
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
        	System.out.println("The hexes are adjacent!");
        }
    }
}
