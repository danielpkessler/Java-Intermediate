/*
 
FICHIER TP 3 - B

Auteur : Daniel Kessler
Cours : IFT 1170

But du Programme :

1. Lire et remplir le fichier "pays_h23.txt". A la suite retourner un vecteur de pays.
2. Afficher les 15 premiers pays lus.
3. Modifier le continent de la Russie;
   Changer la population de l'Allemagne (10 fois plus grande)
   Supprimer le pays "Des Ouragans".
4. Trier les pays en fonction du nom avec Collections.Sort 
   Afficher les 10 premiers pays lus.
5. Chercher avec Collections.BinarySearch et afficher : Canada, Mexique, Japon et Chili.

 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.Collections;
import java.util.Comparator;

class Nation implements Comparable<Nation> {
	int continentCode;
	private String nom;
	private String capitale;
	int superficie;
	int population;

	public Nation(int continentCode, String nom, String capitale, int superficie, int population) {
		this.continentCode = continentCode;
		this.nom = nom;
		this.capitale = capitale;
		this.superficie = superficie;
		this.population = population;
	}

	public void setContinentCode(int continentCode) {
		this.continentCode = continentCode;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setCapitale(String capitale) {
		this.capitale = capitale;
	}

	public void setSuperficie(int superficie) {
		this.superficie = superficie;
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
		return nom + "\ncontinent code 	: " + continentCode + "\ncapitale   	: " + capitale + " \nsuperficie 	: "
				+ superficie + " km2 \npopulation 	: " + population + " habitants\n";
	}

	public int compareTo(Nation autreNation) {
		return this.nom.compareTo(autreNation.nom);
	}

}

public class VectorNation {

	// 1. Fonction pour lire et remplir le fichier "pays_h23.txt"; retourner un
	// vecteur de pays.

	static Vector<Nation> lireRemplir(String nomFichier, int capaciteMax) throws IOException {
		Vector<Nation> v = new Vector<Nation>(capaciteMax);
		boolean existeFichier = true;

		FileReader fr = null;
		try {
			fr = new FileReader(nomFichier);
		} catch (java.io.FileNotFoundException erreur) {
			System.out.println("Probleme d'ouvrir le fichier " + nomFichier);
			existeFichier = false;
		}

		if (existeFichier) {

			BufferedReader entree = new BufferedReader(fr);
			boolean finFichier = false;

			while (!finFichier) {
				String uneLigne = entree.readLine();
				if (uneLigne == null) {
					finFichier = true;
				} else {
					int continentCode = Integer.parseInt(uneLigne.substring(0, 1).trim());
					String nom = uneLigne.substring(1, 33).trim(); // On utilise trim() pour enlever les espaces en trop
					String capitale = uneLigne.substring(36, 55).trim();
					int superficie = Integer.parseInt(uneLigne.substring(55, 64).trim());
					int population = Integer.parseInt(uneLigne.substring(64).trim());
					v.add(new Nation(continentCode, nom, capitale, superficie, population));
				}
			}
			entree.close();
		}
		return v;
	}


		public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub

			int capaciteMax = 250;
			Vector<Nation> vecNations = new Vector<Nation>(capaciteMax);
			try {
				vecNations = lireRemplir("src/pays_h23.txt", capaciteMax);
			} catch (IOException e) {
				System.out.println("Erreur lors de la lecture du fichier");
				System.out.println(e.getMessage());
			}

			int nbElem = vecNations.size();

			// Vérifier si le vecteur est vide
			if (vecNations.isEmpty()) {
				System.out.println("Le vecteur est vide");
				return;
			}

			
			// 2. Afficher les 15 premiers pays
			System.out.println("2. Affichage de l'information des 15 premiers pays lus avant le tri :\n");
			for (int i = 0; i < 15; i++) {
				System.out.println((i + 1) + ") " + vecNations.get(i).toString());
			}

			
			// 3. Modifications :

			// a) Modifier le continent de la Russie (Europe)
			for (int i = 0; i < nbElem; i++) {
				if (vecNations.get(i).getNom().equals("RUSSIE")) {
					vecNations.get(i).setContinentCode(5);
				}
			}

			// b) Modifier la population de l'Allemagne (10 fois plus grande)
			for (int i = 0; i < nbElem; i++) {
				if (vecNations.get(i).getNom().equals("ALLEMAGNE")) {
					vecNations.get(i).setPopulation(vecNations.get(i).getPopulation() * 10);
				}
			}
			
			// c) Supprimer le pays "Des Ouragans"
			for (int i = 0; i < nbElem; i++) {
				if (vecNations.get(i).getNom().equals("DES OURAGANS")) {
					vecNations.remove(i);
			        break;
				}
			}

			// d) Affichage des 20 pays apres les deux modifications
			System.out.println(
					"\n\n3. Information des 20 premiers pays après les modifications :\n");
			for (int i = 0; i < 20; i++) {
				System.out.println((i + 1) + ") " + vecNations.get(i).toString());
			}

			// 4. Trier les pays en fonction du nom avec Collections.sort et afficher les 10
			// premiers pays après le tri.
			Collections.sort(vecNations);

			System.out.println("\n\n4. Affichage des 10 premiers pays après le tri en fonction du nom des pays :\n");
			for (int i = 0; i < 10; i++) {
				System.out.println((i + 1) + ") " + vecNations.get(i).toString());
			}
			
			System.out.println("\n\n5. Recherche et affichage des pays : Canada, Mexique, Japon et Chili.\n");

			String[] paysRecherches = {"CANADA", "MEXIQUE", "JAPON", "CHILI"};
	        for (String pays : paysRecherches) {
	            int index = Collections.binarySearch(vecNations, new Nation(0, pays, "", 0, 0), new Comparator<Nation>() {
	                public int compare(Nation a, Nation b) {
	                    return a.getNom().compareTo(b.getNom());
	                }
	            });

	            if (index >= 0) {
	                System.out.println("Pays trouvé 	: " + vecNations.get(index).toString());
	            } else {
	                System.out.println("Pays non trouvé : " + pays);
	            }
	        }
		}
		


/*

EXECUTION :

2. Affichage de l'information des 15 premiers pays lus avant le tri :

1) ETATS-UNIS
continent code 	: 2
capitale   	: WASHINGTON 
superficie 	: 9629047 km2 
population 	: 291289535 habitants

2) CHINE
continent code 	: 3
capitale   	: PEKIN 
superficie 	: 9596960 km2 
population 	: 1273111290 habitants

3) RUSSIE
continent code 	: 1
capitale   	: MOSCOU 
superficie 	: 17075400 km2 
population 	: 143954573 habitants

4) AUSTRALIE
continent code 	: 4
capitale   	: CANBERRA 
superficie 	: 7686850 km2 
population 	: 19834248 habitants

5) JAPON
continent code 	: 3
capitale   	: KYOTO 
superficie 	: 377835 km2 
population 	: 12761000 habitants

6) DES OURAGANS
continent code 	: 4
capitale   	: TEMPETE 
superficie 	: 1 km2 
population 	: 1 habitants

7) ALLEMAGNE
continent code 	: 5
capitale   	: BERLIN 
superficie 	: 357027 km2 
population 	: 8253700 habitants

8) FRANCE
continent code 	: 5
capitale   	: MARSEILLE 
superficie 	: 543964 km2 
population 	: 61387038 habitants

9) ITALIE
continent code 	: 5
capitale   	: ROME 
superficie 	: 301230 km2 
population 	: 57715620 habitants

10) COREE DU SUD
continent code 	: 3
capitale   	: SEOUL 
superficie 	: 99274 km2 
population 	: 48324000 habitants

11) ROYAUME-UNI
continent code 	: 5
capitale   	: LONDRES 
superficie 	: 244101 km2 
population 	: 58789194 habitants

12) CUBA
continent code 	: 2
capitale   	: LA HAVANE 
superficie 	: 100860 km2 
population 	: 11184023 habitants

13) UKRAINE
continent code 	: 5
capitale   	: KIEV 
superficie 	: 603700 km2 
population 	: 48396470 habitants

14) HONGRIE
continent code 	: 5
capitale   	: BUDAPEST 
superficie 	: 93030 km2 
population 	: 10106017 habitants

15) ROUMANIE
continent code 	: 5
capitale   	: BUCAREST 
superficie 	: 238390 km2 
population 	: 22272000 habitants



3. Information des 20 premiers pays après les modifications :

1) ETATS-UNIS
continent code 	: 2
capitale   	: WASHINGTON 
superficie 	: 9629047 km2 
population 	: 291289535 habitants

2) CHINE
continent code 	: 3
capitale   	: PEKIN 
superficie 	: 9596960 km2 
population 	: 1273111290 habitants

3) RUSSIE
continent code 	: 5
capitale   	: MOSCOU 
superficie 	: 17075400 km2 
population 	: 143954573 habitants

4) AUSTRALIE
continent code 	: 4
capitale   	: CANBERRA 
superficie 	: 7686850 km2 
population 	: 19834248 habitants

5) JAPON
continent code 	: 3
capitale   	: KYOTO 
superficie 	: 377835 km2 
population 	: 12761000 habitants

6) ALLEMAGNE
continent code 	: 5
capitale   	: BERLIN 
superficie 	: 357027 km2 
population 	: 82537000 habitants

7) FRANCE
continent code 	: 5
capitale   	: MARSEILLE 
superficie 	: 543964 km2 
population 	: 61387038 habitants

8) ITALIE
continent code 	: 5
capitale   	: ROME 
superficie 	: 301230 km2 
population 	: 57715620 habitants

9) COREE DU SUD
continent code 	: 3
capitale   	: SEOUL 
superficie 	: 99274 km2 
population 	: 48324000 habitants

10) ROYAUME-UNI
continent code 	: 5
capitale   	: LONDRES 
superficie 	: 244101 km2 
population 	: 58789194 habitants

11) CUBA
continent code 	: 2
capitale   	: LA HAVANE 
superficie 	: 100860 km2 
population 	: 11184023 habitants

12) UKRAINE
continent code 	: 5
capitale   	: KIEV 
superficie 	: 603700 km2 
population 	: 48396470 habitants

13) HONGRIE
continent code 	: 5
capitale   	: BUDAPEST 
superficie 	: 93030 km2 
population 	: 10106017 habitants

14) ROUMANIE
continent code 	: 5
capitale   	: BUCAREST 
superficie 	: 238390 km2 
population 	: 22272000 habitants

15) GRECE
continent code 	: 5
capitale   	: ATHENES 
superficie 	: 131940 km2 
population 	: 10623835 habitants

16) NORVEGE
continent code 	: 5
capitale   	: OSLO 
superficie 	: 324220 km2 
population 	: 4525116 habitants

17) PAYS-BAS
continent code 	: 5
capitale   	: AMSTERDAM 
superficie 	: 41526 km2 
population 	: 16135992 habitants

18) BRESIL
continent code 	: 2
capitale   	: BRASILIA 
superficie 	: 8511965 km2 
population 	: 174468575 habitants

19) SUEDE
continent code 	: 5
capitale   	: STOCKHOLM 
superficie 	: 449964 km2 
population 	: 8875053 habitants

20) ESPAGNE
continent code 	: 5
capitale   	: MADRID 
superficie 	: 504782 km2 
population 	: 40037995 habitants



4. Affichage des 10 premiers pays après le tri en fonction du nom des pays :

1) AFGHANISTAN
continent code 	: 3
capitale   	: KABOUL 
superficie 	: 652225 km2 
population 	: 29547078 habitants

2) AFRIQUE DU SUD
continent code 	: 1
capitale   	: PRETORIA 
superficie 	: 1219912 km2 
population 	: 42718530 habitants

3) ALBANIE
continent code 	: 5
capitale   	: TIRANA 
superficie 	: 28748 km2 
population 	: 3510484 habitants

4) ALGERIE
continent code 	: 1
capitale   	: ALGER 
superficie 	: 2381740 km2 
population 	: 31763053 habitants

5) ALLEMAGNE
continent code 	: 5
capitale   	: BERLIN 
superficie 	: 357027 km2 
population 	: 82537000 habitants

6) ANDORRE
continent code 	: 5
capitale   	: ANDORRA LA VELLA 
superficie 	: 468 km2 
population 	: 67627 habitants

7) ANGOLA
continent code 	: 1
capitale   	: LUANDA 
superficie 	: 1246700 km2 
population 	: 10766471 habitants

8) ANTIGUA-ET-BARBUDA
continent code 	: 2
capitale   	: SAINT-JOHNS 
superficie 	: 442 km2 
population 	: 67448 habitants

9) ANTILLES NEERLANDAISES
continent code 	: 2
capitale   	: WILLEMSTAD 
superficie 	: 800 km2 
population 	: 210000 habitants

10) ARABIE SAOUDITE
continent code 	: 3
capitale   	: RIYAD 
superficie 	: 1960582 km2 
population 	: 23513330 habitants



5. Recherche et affichage des pays : Canada, Mexique, Japon et Chili.

Pays trouvé 	: CANADA
continent code 	: 2
capitale   	: OTTAWA 
superficie 	: 9984670 km2 
population 	: 31499560 habitants

Pays trouvé 	: MEXIQUE
continent code 	: 2
capitale   	: MEXICO 
superficie 	: 1972550 km2 
population 	: 103400165 habitants

Pays trouvé 	: JAPON
continent code 	: 3
capitale   	: KYOTO 
superficie 	: 377835 km2 
population 	: 12761000 habitants

Pays trouvé 	: CHILI
continent code 	: 3
capitale   	: SANTIAGO 
superficie 	: 756950 km2 
population 	: 15328467 habitants

*/

 */
}