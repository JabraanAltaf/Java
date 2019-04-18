import java.util.*;

public class BreakCaesar {
	static char ch,c;
	static int total = 0;
	static double freq,n;
	static double diff;
	static String str;
	static double val = 0.0;
	static double[] knownFreq = 	//Frequencies for Alphabet English
	   {0.0855, 0.0160, 0.0316, 0.0387, 0.1210,
		0.0218, 0.0209, 0.0496, 0.0733, 0.0022,
		0.0081, 0.0421, 0.0253, 0.0717, 0.0747,
		0.0207, 0.0010, 0.0633, 0.0673, 0.0894,
		0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011};
	
// Main Method	
    public static void main(String[] args) {
     try {
            String str = args[0]; //Reads input from command
			System.out.print(str + "\n");
			System.out.println("---------------------------------");
			System.out.println(decrypt(str,rightshift(str))); //Prints out decrypted text
    }
	
    catch(ArrayIndexOutOfBoundsException e){ //If array not filled (i.e. no input), then an error message will be printed.
        System.out.println("Oops, you have not given enough parameters!");
		System.out.println("\nUsage: java BreakCaesar " + "string");
    }
	
	}
  public static int rightshift(String str) {
	//Creating Array Lists
	ArrayList<Double> numbers = new ArrayList<Double>();
	ArrayList<Double> finalscores = new ArrayList<Double>();

	//Working out total number of letters in the input	
	for (int j = 0; j < str.length(); j++) {
	if (Character.isLetter(str.charAt(j))) {
    total++;
	}
	}
	
	char[] plaintext = str.toCharArray(); // Array which will hold decrypted text
	// Loop for all possible shifts			
	for (int shift1 = 0; shift1 < 26; shift1 ++) {
		// Decrypts each character in accordance to shift number
		for (int i =0;i < str.length();i++) {
			if (str.charAt(i) - 'A' <= 25  && str.charAt(i) - 'A' >= 0 &&  Character.isLetter(str.charAt(i))) { // Checks if uppercase
					char newChar = (char)((str.charAt(i) - 'A' + shift1) % 26 + 'A');
					plaintext[i] = newChar;
					}
			else if (str.charAt(i) - 'a' <= 25 && str.charAt(i) - 'a' >= 0 && Character.isLetter(str.charAt(i))) { // Checks if lowercase
				char newChar = (char)((str.charAt(i) - 'a' + shift1) % 26 + 'a');
				plaintext[i] = newChar;
					}
			else {
				plaintext[i] = str.charAt(i);
		     }
			 }
	
	// Loop to work out frequencies of the letters 	 
	for(c='a'; c <='z'; c++)
        {
            double count = 0.0;
            for(int a=0; a< str.length(); a++)
            {
                ch = plaintext[a];
                if(ch == c)
                {
                    count++;
				}
			}
			n = count/total;
			numbers.add(n); // Adds each frequency to an array list
		}

		double chi = 0.0;
	//Working out chi-squared score	
	for (int e = 0; e < 26; e++) {
		diff = numbers.get(e) - knownFreq[e]; //Difference of frequencies
		val=Math.pow(diff,2.0)/(knownFreq[e]);
		chi = chi + val;
		}
		finalscores.add(chi);//Add chi-squared total for a particular shift to finalscores array list
		numbers.clear(); // Clearing 'numbers' array list after every shift
	}

	// Finding which gives the smallest chi-squared value (closest to English)
	double smallest = finalscores.get(0);
	int loc = 0;
		for(int r = 0; r < finalscores.size(); r++ ){
			if (finalscores.get(r) < smallest) {
				smallest = finalscores.get(r);
				loc = r;
			}
			}	
		int Shift = loc; 
		
		return Shift;
	}
	
	
	// Method for decrypting cipher text with the shift which had the lowest chi-squared value	
	public static char[] decrypt(String str,int Shift) {
	char[] newtext = str.toCharArray(); //Creating array which will hold each new character
	for (int i =0;i < str.length();i++) {
			if (str.charAt(i) - 'A' <= 25  && str.charAt(i) - 'A' >= 0 &&  Character.isLetter(str.charAt(i))) { //Checks if uppercase
					char newChar = (char)((str.charAt(i) - 'A' + Shift) % 26 + 'A');
					newtext[i] = newChar;//Adds character to plaintext array
					}
			else if (str.charAt(i) - 'a' <= 25 && str.charAt(i) - 'a' >= 0 && Character.isLetter(str.charAt(i))) { //Checks if lowercase
				char newChar = (char)((str.charAt(i) - 'a' + Shift) % 26 + 'a');
				newtext[i] = newChar; //Adds character to plaintext array
					}
			else {
				newtext[i] = str.charAt(i); // Leaves non-english letters as they are.
		     }
			 }
			 return newtext; //Returns array of characters
	}
}