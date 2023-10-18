public class TP1A {

	// Fonction pour afficher le contenu des deux tableaux.
	static void afficher(char[] poste, int[] nbCafe) {
		System.out.printf("    Poste   nbCafe\n\n");
		for (int i = 0; i < poste.length; i++) {
			System.out.printf("%d) %5c %5d\n", i, poste[i], nbCafe[i]);
		}
	}

	
	// Fonction pour compter le nombre d'employes selon leur poste

	static int compteur(char statutVoulu, char poste[], int nbPers) {
		int compteur = 0;
		for (int i = 0; i < nbPers; i++) {
			if (poste[i] == statutVoulu) {
				compteur++;
			}
		}
		return compteur;
	}
	
	// Fonction pour determiner le nombre d'operateurs consommant 3 tasses de cafe ou plus

	static int combienEntier(int entier[], char statutVoulu, char statut[], int nbElem, int borne) {
		int combien = 0;
		for(int i = 0; i < nbElem; i++) {
			if(entier[i] >= borne && statutVoulu == statut[i]) {
				combien++;
			}
		}
		return combien;
	}
	
	// Fonction pour trouver la consommation moyenne de cafe des programmeurs
	
	static int moyenne(char statutVoulu, char poste[], int nbPers, int[] nbCafe) {
		int consoMoy = 0, nbProg = 0;
		for (int i = 0; i < nbPers; i++) {
			if (poste[i] == statutVoulu)
				if(poste[i] == 'P') {
					nbProg++;
				consoMoy += nbCafe[i];
			}
		}
		return consoMoy / nbProg;
	}
	
	
	// Fonction pour determiner la consommation maximale de cafe des analystes
	
	static int max(char statutVoulu, char poste[], int nbPers, int[] tableau) {
		int plusGrand = tableau[0];
		for (int i = 1; i < nbPers; i++) {
			if (poste[i] == statutVoulu) {
				if (tableau[i] > plusGrand) {
					plusGrand = tableau[i]; }
			}
		}
		return plusGrand;
	}
	
	
	// Fonction pour determiner la consommation minimale de cafe des operateurs
	
		static int min(char statutVoulu, char poste[], int nbPers, int[] tableau) {
			int plusGrand = tableau[0];
			for (int i = 1; i < nbPers; i++) {
				if (poste[i] == statutVoulu) {
					if (tableau[i] < plusGrand) {
						plusGrand = tableau[i]; }
				}
			}
			return plusGrand;
		}
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Tableaux

		char[] poste = { 'P', 'P', 'O', 'P', 'A', 'A', 'O', 'A', 'P' };
		int[] nbCafe = { 3, 1, 4, 0, 4, 2, 2, 5, 1 };
		int nbEmp = poste.length;
		final int BORNE = 3;

		// 1. Affichage du contenu des deux tableaux

		System.out.printf("Affichage du contenu des deux tableaux\n\n");
		afficher(poste, nbCafe);

		
		// 2. Affichage du nombre de :
		
		// a) Operateurs
		System.out.printf("\nIl y a %d operateur(s).\n", compteur('O', poste, nbEmp));
		
		// b) Analystes
		System.out.printf("Il y a %d analyste(s).\n", compteur('A', poste, nbEmp));
		
		// c) Secretaires
		System.out.printf("Il y a %d secretaire(s).\n", compteur('S', poste, nbEmp));
		
		// d) Le nombre de programmeurs consommant 3 tasses ou plus
		System.out.printf("Il y a %d programmeur(s) qui consomme(n"
				+ "t) 3 tasses de cafe ou plus.\n", combienEntier(nbCafe, 'P', poste, nbEmp, BORNE));
		
		
		// Affichage de la consommation moyenne de cafe des programmeurs
		
		System.out.printf("\nLa consommation moyenne de cafe des programmeurs est de %d tasse(s).\n", moyenne('P', poste, nbEmp, nbCafe));
		
		
		// Affichage de la consommation maximale de cafe des analystes
		
		System.out.printf("La consommation maximale de cafe parmi les analystes est de %d tasse(s).\n", max('A', poste, nbEmp, nbCafe));
		
		
		// Affichage de la consommation minimale de cafe des operateurs
		
		System.out.printf("La consommation minimale de cafe parmi les operateurs est de %d tasse(s).\n", min('O', poste, nbEmp, nbCafe));
	
		
	}

}
