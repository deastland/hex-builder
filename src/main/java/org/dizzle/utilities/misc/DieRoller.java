package org.dizzle.utilities.misc;

import org.dizzle.utilities.model.RollType;

/**
 * Utility for rolling dice.
 * 
 * @author deastland
 *
 */
public class DieRoller {

	///////////////////////////
	// This will take in a string describing the type of roll.
	// It will start with the number of dice, then a 'd', then the type of dice.
	// This can be followed by a '-' or '+' and then a number (for modifiers).
	// Once this is parsed, the proper dice will be rolled and the result calculated and returned.
	//
	// Example strings: '2d6', '1d20+2', '3d10 - 1'
	///////////////////////////
	public static int rollDice(String rollFormula) {
		int result = 0;
		
		int numDice = 0;
		int dieType = 0;
		int rollModifier = 0;
		
		// This should have element 0 as the number of die.
		// Element 1 will contain the rest.
		String[] firstSplit = rollFormula.split("d");
		numDice = Integer.parseInt(firstSplit[0]);
		
		if (firstSplit[1].indexOf('+') > -1) {
			rollModifier = Integer.parseInt(firstSplit[1].split("\\+")[1].trim());
		}

		if (firstSplit[1].indexOf('-') > -1) {
			rollModifier = 0 - Integer.parseInt(firstSplit[1].split("\\-")[1].trim());
		}
		
		if (rollModifier == 0) {
			dieType = Integer.parseInt(firstSplit[1].trim());
		} else if (rollModifier > 0) {
			dieType = Integer.parseInt(firstSplit[1].split("\\+")[0].trim());
		} else if (rollModifier < 0) {
			dieType = Integer.parseInt(firstSplit[1].split("\\-")[0].trim());
		}
		
		// Roll each die and add to the result.
		for (int i=0; i < numDice; i++) {
			int roll = rollDie(dieType);
			result += roll;
		}
		
		result += rollModifier;
		
		
		return result;
	}
	
	// Roll a result from 1-6
	public static int rollD6() {
		return rollDie(6);
	}
	
	public static int rollD20() {
		return rollDie(20);
	}
	
	public static int rollD20(RollType rollType) {
		switch(rollType) {
		case STANDARD: 
			return rollD20();
		case ADVANTAGE:
			return rollD20Advantage();
		case DISADVANTAGE:
			return rollD20Disadvantage();
		default:
			return 0;
		}
	}
	
	public static int rollD20Advantage() {
		int roll1 = rollDie(20);
		int roll2 = rollDie(20);
		
		if (roll1 > roll2) {
			return roll1;
		}
		
		return roll2;
	}
	
	public static int rollD20Disadvantage() {
		int roll1 = rollDie(20);
		int roll2 = rollDie(20);
		
		if (roll1 < roll2) {
			return roll1;
		}
		
		return roll2;
	}
	
	public static int rollD8() {
		return rollDie(8);
	}
	
	public static int rollD12() {
		return rollDie(12);
	}
	
	public static int rollD10() {
		return rollDie(10);
	}
	
	public static int rollD100() {
		return rollDie(100);
	}
	
	public static int rollPercentile() {
		return rollD100();
	}
	
	// Roll a result from 1-n
	private static int rollDie(int dieSize) {
		return (int)(Math.random() * dieSize + 1);
	}
	
	// Main for testing.
	public static void main(String[] args) {
		
		String theRoll = "2d6 - 1";
		
		System.out.println("Rolling " + theRoll + ": " + rollDice(theRoll));
		
		theRoll = "1d10";

		System.out.println("Rolling " + theRoll + ": " + rollDice(theRoll));
	
		theRoll = "3d8+8";
		System.out.println("Rolling " + theRoll + ": " + rollDice(theRoll));
	
	}
}
