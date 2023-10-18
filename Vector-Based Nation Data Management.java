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
}
