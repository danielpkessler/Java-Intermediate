/*

FICHIER TP 2 - A

Auteur : Daniel Kessler
Cours : IFT 1170
But du Programme :

Ecrire une version recursive pour afficher verticalement un entier strictement superieur a 0.
En suite, ecrire une verion iterative pour effectuer la meme tache.
Les donnees a traiter sont :
n = 2731 et n = 41376

 */


public class TP3A {
	
	// Fonction pour afficher verticalement avec une fonction recursive.
	
	public static void Recursive(int n) {
        if (n < 10) {
            System.out.println(n);
        } else {
            Recursive(n / 10);
            System.out.println(n % 10);
        }
    }
	
	// Fonction pour afficher verticalement avec une fonction iterative
	
	public static void Iterative(int n) {
        String nString = Integer.toString(n);
        for (int i = 0; i < nString.length(); i++) {
            System.out.println(nString.charAt(i));
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	int x = 2731;
	int y = 41376;
	String n1 = String.valueOf(x);
	String n2 = String.valueOf(y);
	
	System.out.println("Version récursive pour n = 2731 et n = 41376 :\n");
    Recursive(Integer.parseInt(n1));
    System.out.println("\net\n");
    Recursive(Integer.parseInt(n2));
    
    System.out.println("\n\nVersion itérative pour n = 2731 et n = 41376 :\n");
    Iterative(Integer.parseInt(n1));
    System.out.println("\net\n");
    Iterative(Integer.parseInt(n2));
	
	}

}



/* 

EXECUTION :

Affichage en version recursive pour n = 2731 et n = 41376 :

2
7
3
1

et

4
1
3
7
6


Affichage en version iterative pour n = 2731 et n = 41376 :

2
7
3
1

et

4
1
3
7
6

*/

