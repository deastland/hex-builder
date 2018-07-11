package org.dizzle.utilities;

import org.dizzle.utilities.model.Feature;
import org.dizzle.utilities.model.Hex;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Hex testHex = new Hex(04, 01);
        
        Feature feature = new Feature();
        feature.setName("Ruins");
        
        testHex.getFeatures().add(feature);
       
        Feature feature2 = new Feature();
        feature2.setName("Lair");
        
        testHex.getFeatures().add(feature2);
        
        System.out.println(testHex);
    }
}
