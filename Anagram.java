import java.util.*;

class Anagram {

	
	public static String sortLetters(String text) {
	
		char tempArr[] = text.toCharArray();
		
		for (int counter = 0; counter < tempArr.length; counter++) {
			if(Character.isLetter(tempArr[counter]) && Character.isUpperCase(tempArr[counter])){
				tempArr[counter] = Character.toLowerCase(tempArr[counter]);
			}
		}
		Arrays.sort(tempArr);
		
		return new String(tempArr); 
	}
	
	public static void checkMatch(String text, String text2) {
		
		System.out.println("Word is : " + text);
		System.out.println("Word is : " + text2);
		
		if (text.equals(text2)){
			System.out.println("Phrase is an anagram");
		}
		
		else {
			System.out.println("Phrase is not an anagram");
		}
	}
	
	
	public static void main (String [] args) {
		String text = sortLetters("Payment received");
		String text2 = sortLetters("Everycentpaid me");
		
		System.out.println(text);
		System.out.println("\n");
		System.out.println(text2);
		
		checkMatch(text, text2);
		
		//System.out.println(checkMatch(text, text2));
		
		// Check for match
		
	}



}