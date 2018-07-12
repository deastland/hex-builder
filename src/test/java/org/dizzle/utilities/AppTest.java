package org.dizzle.utilities;

import org.dizzle.utilities.model.Hex;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testHex() {
    	Hex hex1 = new Hex(1,1);
    	Hex hex2 = new Hex(1,2);
    	
    	assertTrue(hex1.isAdjacentTo(hex2));
    }
}
