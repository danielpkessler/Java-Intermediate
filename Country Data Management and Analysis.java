/*

FICHIER TP 2 - B

Auteur : Daniel Kessler
Cours : IFT 1170
But du Programme :

Lire et remplir fichier "pays.txt".
1. Compter et retourner le nombre effectif de pays lus.
2. Afficher les 10 premiers pays lus dans le tableau (avec la definition toSring).
3. Modifier la population de l'Allemgane (fois 10) et afficher 
les 8 premiers pays lus apres cette modification.
4. Afficher les pays qui ont un nom identique au nom de leur capitale.
5. Faire un tri rapide selon le nom (ordre alphabetique) 
des pays et afficher les 10 premiers pays lus.
6. Chercher en utilisant la recherche dichotomique et afficher les informations de :
Canada, France, Japon et Mexique.
7. Creer un fichier appele "Europe.txt" qui contient uniquement les pays d'Europe, 
faire de meme pour un fichier intitule "Asie.txt".

 */

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

class Pays {
	int continentCode;
	private String nom;
	private String capitale;
	int superficie;
	int population;

	public Pays(int continentCode, String nom, String capitale, int superficie, int population) {
		this.continentCode = continentCode;
		this.nom = nom;
		this.capitale = capitale;
		this.superficie = superficie;
		this.population = population;
	}

	public int getContinentCode() {
		return continentCode;
	}

	public String getNom() {
		return nom;
	}

	public String getCapitale() {
		return capitale;
	}

	public int getSuperficie() {
		return superficie;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String toString() {
		return nom + "\ncapitale   : " + capitale + " \nsuperficie : " + superficie + " km2 \npopulation : "
				+ population + " habitants\n";
	}

	static int lireRemplir(String nomFichier, Pays[] pays) throws IOException {
		boolean existeFichier = true;
		int n = 0;

		FileReader fr = null;
		try {
			fr = new FileReader(nomFichier);
		} catch (java.io.FileNotFoundException erreur) {
			System.out.println("Probleme d'ouvrir le fichier " + nomFichier);
			existeFichier = false;
		}
		try (BufferedReader entree = new BufferedReader(fr)) {
			boolean finFichier = false;
			while (!finFichier) {
				String uneLigne = entree.readLine();
				if (uneLigne == null)
					finFichier = true;
				else {
					int continentCode = Integer.parseInt(uneLigne.substring(0, 1).trim());
					String nom = uneLigne.substring(1, 33).trim(); // On utilise trim() pour enlever les espaces en trop
					String capitale = uneLigne.substring(36, 60).trim();
					int superficie = Integer.parseInt(uneLigne.substring(62, 70).trim());
					int population = Integer.parseInt(uneLigne.substring(75, 85).trim());
					pays[n++] = new Pays(continentCode, nom, capitale, superficie, population);
				}
			}
		}
		return n;
	}

	// Fonction pour afficher les 10 premiers pays trouves
	static void afficher(Pays[] pays, int nbPays, String message) {
		for (int i = 0, indice = 1; i < nbPays && indice <= 10; i++, indice++) {
			System.out.printf("%3d) %s\n", indice, pays[i]);
		}
	}

	// Fonctions pour faire un tri rapide en fonction du nom des pays
	static int partitionner(Pays[] pays, int debut, int fin) {
		int g = debut, d = fin;
		String valPivot = pays[debut].getNom();
		Pays tempo;

		do {
			while (g <= d && (pays[g].getNom().compareTo(valPivot) <= 0))
				g++;
			while (pays[d].getNom().compareTo(valPivot) > 0)
				d--;
			if (g < d) {
				tempo = pays[g];
				pays[g] = pays[d];
				pays[d] = tempo;
			}
		} while (g <= d);

		tempo = pays[debut];
		pays[debut] = pays[d];
		pays[d] = tempo;
		return d;
	}

	static void quickSort(Pays[] pays, int gauche, int droite) {
		if (droite > gauche) {
			int indPivot = partitionner(pays, gauche, droite);
			quickSort(pays, gauche, indPivot - 1);
			quickSort(pays, indPivot + 1, droite);
		}
	}

	// Chercher un pays dans le tableau avec la recherche dichotomique
	public static Pays rechercheDichotomique(Pays[] tableauPays, int nbPays, String nomRecherche) {
		int debut = 0;
		int fin = nbPays - 1;
		Pays paysTrouve = null; // Pays vide

		while (debut <= fin) {
			int milieu = (debut + fin) / 2;
			int comparaison = nomRecherche.compareTo(tableauPays[milieu].getNom());
			if (comparaison == 0) {
				paysTrouve = tableauPays[milieu];
				break;
			} else if (comparaison < 0) {
				fin = milieu - 1;
			} else {
				debut = milieu + 1;
			}
		}
		return paysTrouve;
	}

	static void creerTexte(Pays[] tableauPays, int nbPays, String nomACreer) throws IOException {

		boolean probleme = false;
		FileWriter fw = null;
		try {
			fw = new FileWriter(nomACreer);
		} catch (java.io.FileNotFoundException erreur) {
			System.out.println("Problème de préparer l'écriture\n");
			probleme = true;
		}
		if (!probleme) {
			System.out.println("\nDébut de la création du fichier");
			PrintWriter aCreer = new PrintWriter(fw);

			for (int i = 0; i < nbPays; i++) {
				aCreer.printf(" %d) %s", (i + 1), tableauPays[i]);
			}

			aCreer.close();
			System.out.println("Fin de la création du fichier " + nomACreer + "");
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Pays[] tableauPays = new Pays[250];
		int nbPays = 0;
		try {
			nbPays = lireRemplir("/Users/dpkessler/Desktop/Pays.txt", tableauPays);
		} catch (IOException e) {
			System.out.println("Erreur lors de la lecture du fichier");
		}

		// 1. Lire le fichier, remplir un tableau de pays, compter puis retourner le
		// nombre effectif de pays lus
		String recherche = "/Users/dpkessler/Desktop/Pays.txt";
		String[] pays = new String[1]; // Tableau pour stocker les pays lus
		int compteur = 0; // Compteur de pays lus

		try (BufferedReader reader = new BufferedReader(new FileReader(recherche))) {
			String line;
			while ((line = reader.readLine()) != null) {
				compteur++;
			}
		} catch (IOException e) {
		}

		System.out.printf("1. Il y a ");
		System.out.println(compteur + " pays lus dans le fichier Pays.txt\n\n");

		// 2. Afficher les 10 premiers pays
		System.out.println("2. Affichage de l'information des 10 premiers pays lus avant le tri :\n");
		for (int i = 0; i < 10 && i < nbPays; i++) {
			System.out.println((i + 1) + ") " + tableauPays[i].toString());
		}

		// 3. Modifier la population de l'Allemagne et réafficher les 8 premiers pays
		for (int i = 0; i < nbPays; i++) {
			if (tableauPays[i].getNom().equals("Allemagne")) {
				tableauPays[i].setPopulation(tableauPays[i].getPopulation() * 10);
			}
		}
		System.out.println(
				"\n\n3. Information des 8 premiers pays après modification de la population de l'Allemagne :\n");
		for (int i = 0; i < 8 && i < nbPays; i++) {
			System.out.println((i + 1) + ") " + tableauPays[i].toString());
		}

		// 4. Afficher les pays dont le nom est identique à la capitale
		System.out.println("\n\n4. Information des pays dont le nom est identique à la capitale :\n");
		for (int i = 0; i < nbPays; i++) {
			if (tableauPays[i].getNom().equals(tableauPays[i].getCapitale())) {
				System.out.println(tableauPays[i].toString());
			}
		}

		// 5. Trier (rapide) selon les noms des pays et afficher les 10 premiers pays
		// lus
		// après le tri
		System.out.println("\n\n5. Information des 10 premiers pays trouves apres le tri, en fonction du nom :\n");
		quickSort(tableauPays, 0, nbPays - 1);
		afficher(tableauPays, tableauPays.length, "");

		/* 6. Chercher (en utilisant la recherche dichotomique) puis afficher les pays
		 * suivants : - Canada, France, Japon, Mexique */
		Pays paysCanada = rechercheDichotomique(tableauPays, nbPays, "CANADA");
		Pays paysFrance = rechercheDichotomique(tableauPays, nbPays, "FRANCE");
		Pays paysJapon = rechercheDichotomique(tableauPays, nbPays, "JAPON");
		Pays paysMexique = rechercheDichotomique(tableauPays, nbPays, "MEXIQUE");

		System.out.println(
				"\n\n6. Recherche dichotomique et affichage du Canada, de la France, de Japon et le Mexique :\n");
		if (paysCanada != null) {
			System.out.println(paysCanada.toString());
		} else {
			System.out.println("Le pays Canada n'a pas été trouvé.");
		}

		if (paysFrance != null) {
			System.out.println(paysFrance.toString());
		} else {
			System.out.println("Le pays France n'a pas été trouvé.");
		}

		if (paysJapon != null) {
			System.out.println(paysJapon.toString());
		} else {
			System.out.println("Le pays Japon n'a pas été trouvé.");
		}

		if (paysMexique != null) {
			System.out.println(paysMexique.toString());
		} else {
			System.out.println("Le pays Mexique n'a pas été trouvé.");
		}

		// 7. Créer le fichier texte Europe.txt qui contient seulement les pays d'Europe
		Pays[] tableauPaysAsie = new Pays[250];
		int nbPaysAsie = 0;
		Pays[] tableauPaysEurope = new Pays[250];
		int nbPaysEurope = 0;

		for (int i = 0; i < nbPays; i++) {
			if (tableauPays[i].getContinentCode() == 3) {
				tableauPaysAsie[nbPaysAsie++] = tableauPays[i];
			} else if (tableauPays[i].getContinentCode() == 5) {
				tableauPaysEurope[nbPaysEurope++] = tableauPays[i];
			}
		}

		System.out.printf(
				"\n\n7. Creation des fichiers Asie et Europe avec l'affichage des informations de leurs pays :\n");
		Pays.creerTexte(tableauPaysAsie, nbPaysAsie, "Asie.txt");
		Pays.creerTexte(tableauPaysEurope, nbPaysEurope, "Europe.txt");

	}

}


/* 

1. Il y a 197 pays lus dans le fichier Pays.txt


2. Affichage de l'information des 10 premiers pays lus avant le tri :

1) ETATS-UNIS
capitale   : WASHINGTON 
superficie : 9629047 km2 
population : 291289535 habitants

2) CHINE
capitale   : PEKIN 
superficie : 9596960 km2 
population : 1273111290 habitants

3) RUSSIE
capitale   : MOSCOU 
superficie : 17075400 km2 
population : 143954573 habitants

4) AUSTRALIE
capitale   : CANBERRA 
superficie : 7686850 km2 
population : 19834248 habitants

5) JAPON
capitale   : TOKYO 
superficie : 377835 km2 
population : 12761000 habitants

6) ALLEMAGNE
capitale   : BERLIN 
superficie : 357027 km2 
population : 8253700 habitants

7) FRANCE
capitale   : PARIS 
superficie : 543964 km2 
population : 61387038 habitants

8) ITALIE
capitale   : ROME 
superficie : 301230 km2 
population : 57715620 habitants

9) COREE DU SUD
capitale   : SEOUL 
superficie : 99274 km2 
population : 48324000 habitants

10) ROYAUME-UNI
capitale   : LONDRES 
superficie : 244101 km2 
population : 58789194 habitants



3. Information des 8 premiers pays après modification de la population de l'Allemagne :

1) ETATS-UNIS
capitale   : WASHINGTON 
superficie : 9629047 km2 
population : 291289535 habitants

2) CHINE
capitale   : PEKIN 
superficie : 9596960 km2 
population : 1273111290 habitants

3) RUSSIE
capitale   : MOSCOU 
superficie : 17075400 km2 
population : 143954573 habitants

4) AUSTRALIE
capitale   : CANBERRA 
superficie : 7686850 km2 
population : 19834248 habitants

5) JAPON
capitale   : TOKYO 
superficie : 377835 km2 
population : 12761000 habitants

6) ALLEMAGNE
capitale   : BERLIN 
superficie : 357027 km2 
population : 8253700 habitants

7) FRANCE
capitale   : PARIS 
superficie : 543964 km2 
population : 61387038 habitants

8) ITALIE
capitale   : ROME 
superficie : 301230 km2 
population : 57715620 habitants



4. Information des pays dont le nom est identique à la capitale :

DJIBOUTI
capitale   : DJIBOUTI 
superficie : 22000 km2 
population : 460700 habitants

KOWEIT
capitale   : KOWEIT 
superficie : 17820 km2 
population : 2041961 habitants

LUXEMBOURG
capitale   : LUXEMBOURG 
superficie : 2586 km2 
population : 442972 habitants

MONACO
capitale   : MONACO 
superficie : 195 km2 
population : 31842 habitants

PANAMA
capitale   : PANAMA 
superficie : 78200 km2 
population : 2845647 habitants

SAINT MARIN
capitale   : SAINT MARIN 
superficie : 61 km2 
population : 27336 habitants

SAO TOME
capitale   : SAO TOME 
superficie : 1001 km2 
population : 165034 habitants



5. Information des 10 premiers pays trouves apres le tri, en fonction du nom :

  1) AFGHANISTAN
capitale   : KABOUL 
superficie : 652225 km2 
population : 29547078 habitants

  2) AFRIQUE DU SUD
capitale   : PRETORIA 
superficie : 1219912 km2 
population : 42718530 habitants

  3) ALBANIE
capitale   : TIRANA 
superficie : 28748 km2 
population : 3510484 habitants

  4) ALGERIE
capitale   : ALGER 
superficie : 2381740 km2 
population : 31763053 habitants

  5) ALLEMAGNE
capitale   : BERLIN 
superficie : 357027 km2 
population : 8253700 habitants

  6) ANDORRE
capitale   : ANDORRA LA VELLA 
superficie : 468 km2 
population : 67627 habitants

  7) ANGOLA
capitale   : LUANDA 
superficie : 1246700 km2 
population : 10766471 habitants

  8) ANTIGUA-ET-BARBUDA
capitale   : SAINT-JOHNS 
superficie : 442 km2 
population : 67448 habitants

  9) ANTILLES NEERLANDAISES
capitale   : WILLEMSTAD 
superficie : 800 km2 
population : 210000 habitants

 10) ARABIE SAOUDITE
capitale   : RIYAD 
superficie : 1960582 km2 
population : 23513330 habitants



6. Recherche dichotomique et affichage du Canada, de la France, de Japon et le Mexique :

CANADA
capitale   : OTTAWA 
superficie : 9984670 km2 
population : 31499560 habitants

FRANCE
capitale   : PARIS 
superficie : 543964 km2 
population : 61387038 habitants

JAPON
capitale   : TOKYO 
superficie : 377835 km2 
population : 12761000 habitants

MEXIQUE
capitale   : MEXICO 
superficie : 1972550 km2 
population : 103400165 habitants



7. Creation des fichiers Asie et Europe avec l'affichage des informations de leurs pays :

Début de la création du fichier
Fin de la création du fichier Asie.txt

Début de la création du fichier
Fin de la création du fichier Europe.txt

*/