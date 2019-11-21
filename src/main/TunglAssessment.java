package main;

import java.util.*;

public class TunglAssessment {

	public static void main(String[] args) {
		System.out.println(numberOfCommonWords("I like apples and bananas and also pizza with pineapple.",
				"I prefer pizza without pineapple.")); //3 common words
		System.out.println(NSEW2("WEWNES"));
		System.out.println(longestStrictPalindrome("bob likes racecars"));
	}

	/*
	 * Write a function that displays the longest strict palindrome in a string. A
	 * strict palindrome reads exactly the same backwards as it does forwards, and
	 * respects all characters and letter case. E.g. "racecar" is a strict
	 * palindrome but "Racecar" and "race car" are not. If there is more than one
	 * palindrome with the longest length, then your code should display the first
	 * of those palindromes.
	 * 
	 * For example:
	 * 
	 * "bob has a racecar" returns "racecar" "bob has a racecar and a bike" returns
	 * "a racecar a" "anna arrived at noon" returns "anna"
	 */
	public static String longestStrictPalindrome(String input) {
		char[] c = input.toCharArray();
		String out = "";

		for (int start = 0; start < input.length() - 1; start++) {
			for (int i = start; i < input.length(); i++) {
				String s = input.substring(start, i + 1);
				if ((s.equals(reverseString(s))) && (s.length() > out.length())) {
					out = s;
				}
			}
		}
		return out;
	}

	public static String reverseString(String s) {
		char[] c = s.toCharArray();
		String out = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			out += c[i];
		}
		return out;
	}

	// You are given an input string that controls the movement of a robot, each
	// character instructing it to move one step north, south, east or west. E.g.
	// "NW" moves the robot one step north and then one step west, or "SE" moves the
	// robot one step south and one step east. Write a function that counts the
	// number of locations that the robot visits more than once, including possibly
	// its starting point. You should ignore any characters that are not capital N,
	// S, E or W.
	public static int NSEW2(String input) {
		int x = 0, y = 0;
		char[] c1 = input.toCharArray();
		List<Position> locationList = new ArrayList<Position>();

		// init at position 0
		locationList.add(new Position(0, 0));

		for (int i = 0; i < input.length(); i++) {
			if (isCharValidDirection(c1[i])) {
				if (c1[i] == 'N') {
					y++;
				} else if (c1[i] == 'S') {
					y--;
				} else if (c1[i] == 'W') {
					x--;
				} else if (c1[i] == 'E') {
					x++;
				}

				Position p = new Position(x, y);
				locationList.add(p);
			}
		}

		List<Position> duplicateLocationList = new ArrayList<Position>();
		// count the number of unique duplicate positions
		int counter = 0;
		for (int i = 0; i < locationList.size(); i++) {
			Position p = locationList.get(i);
			locationList.remove(i);
			if (locationList.contains(p)) {
				if (!duplicateLocationList.contains(p)) {
					duplicateLocationList.add(p);
				}
			}
			locationList.add(i, p);
		}
		counter = duplicateLocationList.size();
		return counter;
	}

	public static boolean isCharValidDirection(char c) {
		if (c == 'N' || c == 'S' || c == 'W' || c == 'E') {
			return true;
		}
		return false;
	}

	/*
	 * Write a function that given two strings returns the number of words that
	 * appear in both strings. A word is any string that contains upper/lowercase
	 * letters, hyphens, or single quotes.
	 */
	public static int numberOfCommonWords(String input1, String input2) {
		// Your code goes here
		char[] c1 = input1.toCharArray();
		char[] c2 = input2.toCharArray();

		// Creating two lists, each list contains words of respective string
		List<String> l1 = new ArrayList<String>();
		List<String> l2 = new ArrayList<String>();
		String word = "";

		for (int i = 0; i < input1.length(); i++) {
			if (isPartOfWord(c1[i])) {
				word += c1[i];
				// end of string, add to list of words
				if (i == input1.length() - 1) {
					l1.add(word);
					word = "";
				}

			} else {
				l1.add(word);
				word = "";
			}
		}

		word = "";
		for (int i = 0; i < input2.length(); i++) {
			if (isPartOfWord(c2[i])) {
				word += c2[i];
				// end of string, add to list of words
				if (i == input2.length() - 1) {
					l2.add(word);
					word = "";
				}
			} else {
				l2.add(word);
				word = "";
			}
		}

		// retain the common elements in l1
		l1.retainAll(l2);

		// eliminate duplicates
		List<String> commonList = new ArrayList<String>();
		for (int i = 0; i < l1.size(); i++) {
			String s = l1.get(i);
			if (!commonList.contains(s)) {
				commonList.add(s);
			}
		}
		return commonList.size();

	}

	public static boolean isPartOfWord(char c) {
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == '\'') || (c == '-')) {
			// is in alphabet
			return true;
		}
		return false;
	}

	public static String stringReverse(String input) {
		String output = "";
		char[] c = input.toCharArray();
		for (int i = input.length() - 1; i >= 0; i--) {
			output += c[i];
		}
		return output;
	}

	
	//coding assessment preparation example
	public static int factorial(int input) {
		int result = 1;
		for (int i = 1; i <= input; i++) {
			result = result * i;
		}
		return result;
	}

	public static int factorialRecursive(int input) {
		if (input < 2) {
			return 1;
		}
		int result = input * factorialRecursive(input - 1);
		return result;
	}
}

class Position {
	public String key;
	public int x;
	public int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		this.key = String.valueOf(y) + "," + String.valueOf(x);
	}

	@Override
	public boolean equals(Object v) {
		if (v == null)
			return false;
		if (v == this)
			return true; // if both pointing towards same object on heap
		boolean retVal = false;

		if (v instanceof Position) {
			Position ptr = (Position) v;
			retVal = ptr.hashCode() == this.hashCode();
		}

		return retVal;
	}

	@Override
	public int hashCode() {
		int hash = key.hashCode();
		return hash;
	}
}
