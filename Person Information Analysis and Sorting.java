/*

FICHIER TP 1 - C

Auteur : Daniel Kessler
Cours : IFT 1170
But du Programme :

Utilisation de la classe personne.
Faire un tableau de 6 personnes en affichant leur date de naissance 
ainsi que leur consommation de tasses de cafe.

Ensuite :
- Afficher le contnu du tableau
- Determiner et afficher les informations de la personne qui consomme le moins de cafe
- Reduire de 1 tasse la consommation des personnes qui consomment du cafe.
- Trier le tableau selon l'annee de naissance et reafficher le tableau.
- Compter et afficher le nombre de peronnes nees aprees 1990.

 */

class Personne {
	
	private String naissance; // format "jj/mm//annee"
	private int nbCafe; // nombre de tasses de cafe consommees par jour
	
	// Premier constructeur
	public Personne (String naissance, int nbCafe) {
		this.naissance = naissance;
		this.nbCafe = nbCafe;
	}
	
	// Deuxieme constructeur
	public Personne (String naissance) {
		this.naissance = naissance;
		nbCafe = 1;
	}
	
	// Methodes d'acces pour la naissance
	public int getMois() {
		return Integer.parseInt(naissance.substring(3, 5));
	}
	
	public int getJour() {
		return Integer.parseInt(naissance.substring(0, 2));
	}

	public int getAnnee() {
		return Integer.parseInt(naissance.substring(6));
	}
	
	public String getNaissance() {
		return naissance;
	}
	
	// Methode d'acces a la consommation de cafe par jour.
	public void setNbCafe(int nbCafe) {
		this.nbCafe = nbCafe;
	}
	
	public int getNbCafe() {
		return nbCafe;
	}
	
	// Methode de modification pour la consommation de cafe
	public void setConsoCafe(int nouvCafe) {
		nbCafe = nouvCafe;
	}
		
	// Fonction pour afficher les informations d'une personne choisie
	public void infoPers(String en_tete)
	{
		System.out.printf(en_tete);
		System.out.printf("	Naissance : %d/%d/%d\n", getJour(), getMois(), getAnnee());
		System.out.printf("	Cafe 	  : %d tasse(s) de cafe par jour\n", getNbCafe());
		System.out.printf("\n");
	}
}

	



public class TP1C {
	
	// Fonction pour afficher le tableau des 6 personnes
	
	static void afficher(Personne[] pers, int nbPers) {
		System.out.printf("	Indice	      Tableau pers\n");
		System.out.printf("-------------------------------------------------------------------\n");

		for (int i = 0; i < nbPers; i++) {
			if (pers[i].getNbCafe() > 1) {
				System.out.printf("	%3d %20s %15s tasses\n", i, pers[i].getNaissance(), pers[i].getNbCafe());
			}

			else if (pers[i].getNbCafe() == 1) {
				System.out.printf("	%3d %20s %15s tasse (par défaut)\n", i, pers[i].getNaissance(),
						pers[i].getNbCafe());
			}

			else if (pers[i].getNbCafe() == 0) {
				System.out.printf("	%3d %20s %15s tasse\n", i, pers[i].getNaissance(), pers[i].getNbCafe()); }
		}
	}
	
	// Fonction pour déterminer la consomation minimale de café.
		static Personne consMinCafe(Personne[] tableau, int nbPers) {
			Personne plusPetit = tableau[0];
			for (int i = 0; i < nbPers; i++) {
				if (tableau[i].getNbCafe() < plusPetit.getNbCafe()) {
					plusPetit = tableau[i];
				}
			}
			return plusPetit;
		}
	
	/* Méthode de modification - réduction de 1 tasse de café 
	par jour pour les personnes qui consomment du café. */
	static void moins(Personne[] personnes) { 
		for(int i = 0; i < personnes.length; i++) {
			if(personnes[i].getNbCafe() > 0) {
				personnes[i].setNbCafe(personnes[i].getNbCafe() - 1); }
		}
	}
	
	// Fonction pour trier le tableau selon l'annee de naissance
	
	static void permuter(Personne[] tab, int i, int indMin) {
		Personne tempo = tab[i];
		tab[i] = tab[indMin];
		tab[indMin] = tempo;
	}
	
	static void trier(Personne[] pers, int nbPers) {
		for (int i = 0; i < nbPers - 1; i++) {
			int indMin = i;
			for (int j = i + 1; j < nbPers; j++) {
				if (pers[j].getAnnee() < pers[indMin].getAnnee()) {
					indMin = j;
				}
				
				if (indMin != i) // permuteur
				{
					permuter(pers, i, indMin);
				}
			}
		}
	}
	
	// Fonction pour trouver le nombre de personnes nées dans une annee choisie.
		static int anneeVoulue (Personne[] pers, int nbPers, int anneeDonnee) {
			int compteur = 0;
			for (int i = 0; i < nbPers; i++) {
				if (pers[i].getAnnee() == anneeDonnee) {
					compteur++; 
				}
			}
			return compteur;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Information des six personnes
		
		Personne[] pers = { new Personne("16/05/1992", 2),
							new Personne("02/01/1990", 1),
							new Personne("23/05/1990", 5),
							new Personne("19/02/1985", 0),
							new Personne("30/05/1991", 2),
							new Personne("31/01/1990", 4) };
		int nbPers = pers.length;
		
		// 1. Affichage du tableau initial
		
		System.out.printf("\nAffichage du contenu du tableau au debut :\n\n");
		afficher(pers, nbPers);
		
		// 2. Affichage de la personne qui consomme le moins de cafe
		
		consMinCafe(pers, nbPers).infoPers("\n\n\nInformations de la personne qui consomme le moins de cafe :\n\n");
		
		// 3. Réduire de 1 tasse la consommation de café des personnes en consomment et re-afficher le tableau.
		
		System.out.printf("\nReaffichage du contenu du tableau après la modification.\n");
		System.out.printf("Réduction de 1 tasse de café pour les personnes qui consomment :\n\n");
		moins(pers);
		afficher(pers, nbPers);
		
		// 4. Reaffichage du tableau apres le tri en fonction de l'annee de naissance
		
		System.out.printf("\n\nAffichage du contenu du tableau apres le tri en fonction de l'annee de naissance :\n\n");
		
		trier(pers, nbPers);
		
		afficher(pers, nbPers);
				
		// 5. Comptage et affichage des personnes nees apres 1990
				
		System.out.printf("\n\nIl y a %d personne(s) nées en 1990.\n", anneeVoulue(pers, nbPers, 1990));

		
	}

}


/* 

EXECUTION :


Affichage du contenu du tableau au debut :

	Indice	      Tableau pers
-------------------------------------------------------------------
	  0           16/05/1992               2 tasses
	  1           02/01/1990               1 tasse (par défaut)
	  2           23/05/1990               5 tasses
	  3           19/02/1985               0 tasse
	  4           30/05/1991               2 tasses
	  5           31/01/1990               4 tasses



Informations de la personne qui consomme le moins de cafe :

	Naissance : 19/2/1985
	Cafe 	  : 0 tasse(s) de cafe par jour


Reaffichage du contenu du tableau après la modification.
Réduction de 1 tasse de café pour les personnes qui consomment :

	Indice	      Tableau pers
-------------------------------------------------------------------
	  0           16/05/1992               1 tasse (par défaut)
	  1           02/01/1990               0 tasse
	  2           23/05/1990               4 tasses
	  3           19/02/1985               0 tasse
	  4           30/05/1991               1 tasse (par défaut)
	  5           31/01/1990               3 tasses


Affichage du contenu du tableau apres le tri en fonction de l'annee de naissance :

	Indice	      Tableau pers
-------------------------------------------------------------------
	  0           19/02/1985               0 tasse
	  1           23/05/1990               4 tasses
	  2           02/01/1990               0 tasse
	  3           31/01/1990               3 tasses
	  4           30/05/1991               1 tasse (par défaut)
	  5           16/05/1992               1 tasse (par défaut)


Il y a 3 personne(s) nées en 1990.

*/