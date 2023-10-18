public class TP1B {
	
	
	static void affichertout(String phrase) {
		System.out.printf("La phrase telle quelle : ");
		System.out.println(phrase);
		
		phrase = phrase.replace(",", "");
		phrase = phrase.replace(" ", "");
		phrase = phrase.replace("'", "");
		phrase = phrase.replace("!", "");

		
		System.out.println("La phrase transformee : " + phrase.toUpperCase());
		
		if (estPalindrome(phrase)) {
			System.out.printf("La phrase est palindrome\n\n");
		} else {
			System.out.printf("La phrase n'est pas palindrome\n\n");
		}
	}
	
	static String envers(String phrase) {
		String envers = "";
		for(int i = phrase.length() - 1; i >= 0; i--) {
			char unCar = phrase.charAt(i);
			envers += unCar;
		}
		return envers;
	}
	
	static boolean estPalindrome(String phrase) {
		return phrase.equalsIgnoreCase(envers(phrase));
	}

	
	public static void main(String[] args) {
		
		String[] phrase = { "Laval", "Montreal",
				"Tu l'as trop ecrase, Cesar, ce Port Salut!",
				"Et la marine va, papa, venir a Malte" };
		
		for(int i = 0; i < phrase.length; i++) {
			affichertout(phrase[i]);
		}	
	}	
}
