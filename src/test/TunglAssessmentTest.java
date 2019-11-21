package test;
import static org.junit.Assert.*;
import org.junit.Test;
import main.TunglAssessment;


public class TunglAssessmentTest {

	@Test
	public void testNumberOfCommonWords() {
		int expected = 3;
	    int actual = TunglAssessment.numberOfCommonWords("I like apples and bananas and also pizza with pineapple.",
				"I prefer pizza without pineapple.");
	 
	    assertEquals(expected, actual);
	}
	
	@Test
	public void testNSEWSameLocation() {
		int expected = 2;
	    int actual = TunglAssessment.NSEW2("WEWNES");
	 
	    assertEquals(expected, actual);
	}
	
	@Test
	public void testLongestStrictPalindrome() {
		String expected = "racecar";
	    String actual = TunglAssessment.longestStrictPalindrome("bob likes racecars");
	 
	    assertEquals(expected, actual);
	}

}
