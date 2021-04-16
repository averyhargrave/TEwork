package com.techelevator;

import org.junit.*;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

/**
 * MorningExercisesTest
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // optional - tells jUnit to list the tests alphabetically
public class LectureTest {  // Class to hold all of the unit tests that we need
	// Instantiate the lecture code that we used
    private Lecture exercises = new Lecture(); // define an object containing the methods to be tested
    // always be private
    
    @Test
    public void testReturnNotOne() { // testing the returnNotOne() method in the lecture code
    	                // message if test fails,      expected,   method to test
        assertNotEquals("Value returned shouldn't be one", 1, exercises.returnNotOne());
    // check to see if the value returned from the returnNotOne method is NotEqual 1
    }

    @Test
    public void testReturnNotHalf() { // testing the returnNotHalf() method in the lecture code
    	// when checking double values for equals/not equals, a fudge factor is specified due to imprecision of doubles
    	//                                              .44999996 instead of .45
    	// fudge factor - consider the double values equal if the difference is no more than the fudge factor
    					//  message if test fails,      expected,  method to test,    fudge factor
    	assertNotEquals("Value returned shouldn't be 0.5", 0.5, exercises.returnNotHalf(), 0.001);
    }

    @Test
    public void testReturnName() {
        assertNotNull("Value returned should be your name", exercises.returnName());
    }

    @Test
    public void testReturnDoubleOfTwo() {
        assertTrue("Value returned should be a double", Double.class.isInstance(exercises.returnDoubleOfTwo()));
        assertEquals("Value returned should still equal two", 2.0, exercises.returnDoubleOfTwo(), 0);
    }

    @Test
    public void testReturnNameOfLanguage() {
        Object result = exercises.returnNameOfLanguage();
        assertEquals("Value should equal the name of the programming language you're learning", "Java", result);
    }

    @Test
    public void testReturnTrueFromIf() {
        assertTrue("If statement should return true", exercises.returnTrueFromIf());
    }

    @Test
    public void testReturnTrueWhenOneEqualsOne() {
        assertTrue("If statement should return true", exercises.returnTrueWhenOneEqualsOne());
    }

    @Test
    public void testReturnWhenGreaterThanFive() {
        assertTrue("We should return true when parameter is greater than five",
                exercises.returnTrueWhenGreaterThanFive(6));
        assertFalse("We should return false when parameter is five", exercises.returnTrueWhenGreaterThanFive(5));
        assertFalse("We should return false when parameter is smaller than five",
                exercises.returnTrueWhenGreaterThanFive(4));
    }

    @Test
    public void testReturnWhenGreaterThanFiveInOneLine() {
        assertTrue("We should return true when parameter is greater than five",
                exercises.returnTrueWhenGreaterThanFiveInOneLine(6));
        assertFalse("We should return false when parameter is five",
                exercises.returnTrueWhenGreaterThanFiveInOneLine(5));
        assertFalse("We should return false when parameter is smaller than five",
                exercises.returnTrueWhenGreaterThanFiveInOneLine(4));
    }

    @Test
    public void testReturnNumberAfterAddThreeAndAddFive() {
        assertEquals("We should add three and five when both are true", 9,
                exercises.returnNumberAfterAddThreeAndAddFive(1, true, true));
        assertEquals("We should add three when passed true false", 4,
                exercises.returnNumberAfterAddThreeAndAddFive(1, true, false));
        assertEquals("We should add five when passed false true", 6,
                exercises.returnNumberAfterAddThreeAndAddFive(1, false, true));
        assertEquals("We should return the original number when both are false", 1,
                exercises.returnNumberAfterAddThreeAndAddFive(1, false, false));
    }

    @Test
    public void testReturnFizzIfThree() {
        assertEquals("Fizz", exercises.returnFizzIfThree(3));
        assertEquals("", exercises.returnFizzIfThree(6));
    }

    @Test
    public void testReturnFizzIfThreeUsingTernary() {
        assertEquals("Fizz", exercises.returnFizzIfThreeUsingTernary(3));
        assertEquals("", exercises.returnFizzIfThreeUsingTernary(6));
    }

    @Test
    public void testReturnFizzOrBuzzOrNothing() {
        assertEquals("Fizz", exercises.returnFizzOrBuzzOrNothing(3));
        assertEquals("Buzz", exercises.returnFizzOrBuzzOrNothing(5));
        assertEquals("", exercises.returnFizzOrBuzzOrNothing(15));
    }

    @Test
    public void testReturnAdultOrMinor() {
        assertEquals("Adult", exercises.returnAdultOrMinor(20));
        assertEquals("Adult", exercises.returnAdultOrMinor(18));
        assertEquals("Minor", exercises.returnAdultOrMinor(17));
    }

    @Test
    public void testReturnAdultOrMinorAgain() {
        assertEquals("Adult", exercises.returnAdultOrMinorAgain(20));
        assertEquals("Adult", exercises.returnAdultOrMinorAgain(18));
        assertEquals("Minor", exercises.returnAdultOrMinorAgain(17));
    }

    @Test
    public void testReturnAdultOrMinorOrTeen() {
        assertEquals("Adult", exercises.returnAdultOrMinorOrTeen(18));
        assertEquals("Teen", exercises.returnAdultOrMinorOrTeen(17));
        assertEquals("Teen", exercises.returnAdultOrMinorOrTeen(13));
        assertEquals("Minor", exercises.returnAdultOrMinorOrTeen(12));

    }
}