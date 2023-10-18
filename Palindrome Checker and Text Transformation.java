/*

FICHIER TP 1 - B

Auteur : Daniel Kessler
Cours : IFT 1170
But du Programme :

- Pour les quatre phrases : les afficher, 
les transformer en majuscules puis garder que des lettres. 
Afficher la phrase transformee

Les phrases sont :
- "Laval"
- "Montreal"
- "Tu l'as trop ecrase, Cesar, ce Port Salut!"
- "Et la marine va, papa, venir a Malte"

 */

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
		

/*

EXECUTION :

La phrase telle quelle : Laval
La phrase transformee : LAVAL
La phrase est palindrome

La phrase telle quelle : Montreal
La phrase transformee : MONTREAL
La phrase n'est pas palindrome

La phrase telle quelle : Tu l'as trop ecrase, Cesar, ce Port Salut!
La phrase transformee : TULASTROPECRASECESARCEPORTSALUT
La phrase est palindrome

La phrase telle quelle : Et la marine va, papa, venir a Malte
La phrase transformee : ETLAMARINEVAPAPAVENIRAMALTE
La phrase est palindrome

 */

