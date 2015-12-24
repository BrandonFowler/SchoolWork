
//Brandon Fowler
//This contains a series of tests for the Fraction class
//These tests were given in assignment 3 description

package fraction;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IllegalArgument {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void shouldThrowIllegalArgumentException() {
	    expectedEx.expect(IllegalArgumentException.class);
	    expectedEx.expectMessage("The denominator with the value of 0 is not permitted");
	    Fraction test = new Fraction(1,0);
	}
	
	
	@Test
	public void shouldThrowNullPointerExceptionAdd() {
	    expectedEx.expect(NullPointerException.class);
	    expectedEx.expectMessage("Cannot perform math operations on a null fraction object!");
	    Fraction test = new Fraction(1,1);
	    Fraction test2 = null;
	    test.add(test2);
	    }

	@Test
	public void shouldThrowNullPointerExceptionMultiply() {
	    expectedEx.expect(NullPointerException.class);
	    expectedEx.expectMessage("Cannot perform math operations on a null fraction object!");
	    Fraction test = new Fraction(1,1);
	    Fraction test2 = null;
	    test.multiply(test2);
	}
}
