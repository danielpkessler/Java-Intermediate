import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

class Nation_C {

	int continentCode;
	private String nom;
	private String capitale;
	int superficie;
	int population;

	public Nation_C(int continentCode, String nom, String capitale, int superficie, int population) {
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
		return nom + "\ncontinent code 	: " + continentCode + "\nCapitale   	: " + capitale + " \nSuperficie 	: "
				+ superficie + " km2 \nPopulation 	: " + population + " Habitants\n";
	}

}

public class Nation {
	static LinkedList<Nation_C> lireCreer(String nomFichier) throws IOException {
		LinkedList<Nation_C> liste = new LinkedList<Nation_C>();
		boolean existeFichier = true;

		FileReader fr = null;

		try {
			fr = new FileReader(nomFichier);
		} catch (java.io.FileNotFoundException erreur) {
			System.out.println("Probleme lors de l'ouverture du fichier " + nomFichier);
			existeFichier = false;
		}
		if (existeFichier) {
			BufferedReader entree = new BufferedReader(fr);
			boolean finFichier = false;
			while (!finFichier) {
				String uneLigne = entree.readLine();
				if (uneLigne == null)
					finFichier = true;
				else {
					int continentCode = Integer.parseInt(uneLigne.substring(0, 1).trim());
					String nom = uneLigne.substring(1, 33).trim();
					String capitale = uneLigne.substring(36, 62).trim();
					int superficie = Integer.parseInt(uneLigne.substring(63, 71).trim());
					int population = Integer.parseInt(uneLigne.substring(76).trim());
					liste.add(new Nation_C(continentCode, nom, capitale, superficie, population));
				}
			}
			entree.close();
		}
		return liste;
	}

	static void afficher(LinkedList<Nation_C> liste, String mess) {
		System.out.printf("Partie 1 : Contenu de la liste %s :\n\n", mess);
		for (int i = 0; i < liste.size(); i++) {
				System.out.printf("%3d) %s", i + 1, liste.get(i).toString());
				System.out.printf("\n");
		}
	}

	

	/* 2. Recherche Sequentielle pays avec les capitales suivantes : Wahington, Ottawa
	& Santiago */
	static void RechSeq(LinkedList<Nation_C> liste) {
		String[] Capitale = { "WASHINGTON", "OTTAWA", "SANTIAGO" };
		System.out.println("\nPartie 2 : Recherche séquentielle en fonction de la capitale.");
		for (int i = 0; i < Capitale.length; i++) {
			System.out.printf("\nOn procède a la recherche de %s :\n\n", Capitale[i]);
			boolean trouve = false;
			for (Nation_C pays : liste) {
				if (pays.getCapitale().equalsIgnoreCase(Capitale[i])) {
					System.out.println("Le pays a été trouvé !\n");
					System.out.println(pays);
					trouve = true;
				}
		}
		if (!trouve) {
			System.out.printf("Le pays n'a pas été trouvé.\n");
		}
	}
}
	
			

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		LinkedList<Nation_C> listePays = lireCreer("/Users/dpkessler/Desktop/pays_bis.txt");
		LinkedList<Nation_C> listeAmerique = new LinkedList<Nation_C>();
		
		for (Nation_C pays : listePays) {
			if (pays.getContinentCode() == 2) {
				listeAmerique.add(pays);
			}
		}
		afficher(listeAmerique, "des pays d'Amérique");
		RechSeq(listeAmerique);
	
		/* 3. Trier avec Collections.Sort la liste selon les capitales des pays
		& afficher les 7 premiers pays de cette liste */
		Collections.sort(listePays, new Comparator<Nation_C>() {
		    public int compare(Nation_C pays1, Nation_C pays2) {
		        return pays1.getCapitale().compareToIgnoreCase(pays2.getCapitale());
		    }
		});
		
		System.out.println("\n\nPartie 3 : Les 7 premiers pays triés en fonction de la capitale sont :\n");
		for (int i = 0; i < 7 && i < listePays.size(); i++) {
			System.out.printf("%d) ", i + 1);
			System.out.println(listePays.get(i));
		}
		
		
		Nation_C chili = new Nation_C(1, "CHILI", "SANTIAGO", 756950, 15328467);
		listePays.add(chili);
		System.out.println("\nPartie 4 : Affichage de tous les pays triés en fonction de la capitale après l'insertion du Chili :\n");
		Collections.sort(listePays, new Comparator<Nation_C>() {
		    public int compare(Nation_C pays1, Nation_C pays2) {
		        return pays1.getCapitale().compareTo(pays2.getCapitale());
		    }
		});
		int i = 0;
		for (Nation_C pays : listePays) {
		    System.out.printf("%d) ", i + 1);
		    System.out.println(pays);
		    i++;
		}


	}

}
