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

/*

EXECUTION :

Partie 1 : Contenu de la liste des pays d'Amérique :

  1) ETATS-UNIS
continent code 	: 2
Capitale   	: WASHINGTON 
Superficie 	: 9629047 km2 
Population 	: 291289535 Habitants

  2) CUBA
continent code 	: 2
Capitale   	: LA HAVANE 
Superficie 	: 100860 km2 
Population 	: 11184023 Habitants

  3) BRESIL
continent code 	: 2
Capitale   	: BRASILIA 
Superficie 	: 8511965 km2 
Population 	: 174468575 Habitants

  4) CANADA
continent code 	: 2
Capitale   	: OTTAWA 
Superficie 	: 9984670 km2 
Population 	: 31499560 Habitants

  5) JAMAIQUE
continent code 	: 2
Capitale   	: KINGSTON 
Superficie 	: 10991 km2 
Population 	: 1695867 Habitants

  6) ARGENTINE
continent code 	: 2
Capitale   	: BUENOS AIRES 
Superficie 	: 2766890 km2 
Population 	: 37812817 Habitants

  7) BAHAMAS
continent code 	: 2
Capitale   	: NASSAU 
Superficie 	: 13940 km2 
Population 	: 300529 Habitants

  8) REPUBLIQUE DOMINICAINE
continent code 	: 2
Capitale   	: SAINT-DOMINGUE 
Superficie 	: 48730 km2 
Population 	: 8442533 Habitants

  9) MEXIQUE
continent code 	: 2
Capitale   	: MEXICO 
Superficie 	: 1972550 km2 
Population 	: 103400165 Habitants

 10) PARAGUAY
continent code 	: 2
Capitale   	: ASUNCIO 
Superficie 	: 406750 km2 
Population 	: 5734139 Habitants

 11) VENEZUALA
continent code 	: 2
Capitale   	: CARACAS 
Superficie 	: 912050 km2 
Population 	: 23542649 Habitants

 12) COLOMBIE
continent code 	: 2
Capitale   	: BOGOTA 
Superficie 	: 1138910 km2 
Population 	: 41088227 Habitants

 13) TRINITE-ET-TOBAGO
continent code 	: 2
Capitale   	: PORT D'ESPAGNE 
Superficie 	: 5128 km2 
Population 	: 1104209 Habitants

 14) ANTIGUA-ET-BARBUDA
continent code 	: 2
Capitale   	: SAINT-JOHNS 
Superficie 	: 442 km2 
Population 	: 67448 Habitants

 15) ANTILLES NEERLANDAISES
continent code 	: 2
Capitale   	: WILLEMSTAD 
Superficie 	: 800 km2 
Population 	: 210000 Habitants

 16) ARUBA
continent code 	: 2
Capitale   	: ORANJESTAD 
Superficie 	: 193 km2 
Population 	: 69000 Habitants

 17) BARBADE
continent code 	: 2
Capitale   	: BRIDGETOWN 
Superficie 	: 430 km2 
Population 	: 276607 Habitants

 18) BELIZE
continent code 	: 2
Capitale   	: BELMOPAN 
Superficie 	: 22966 km2 
Population 	: 266440 Habitants

 19) BERMUDES
continent code 	: 2
Capitale   	: HAMILTON 
Superficie 	: 53 km2 
Population 	: 113208 Habitants

 20) BOLIVIE
continent code 	: 2
Capitale   	: LA PAZ 
Superficie 	: 1098580 km2 
Population 	: 8724156 Habitants

 21) COSTA RICA
continent code 	: 2
Capitale   	: SAN JOSE 
Superficie 	: 51100 km2 
Population 	: 3835000 Habitants

 22) EL SALVADOR
continent code 	: 2
Capitale   	: SAN SALVADOR 
Superficie 	: 21041 km2 
Population 	: 6122075 Habitants

 23) EQUATEUR
continent code 	: 2
Capitale   	: QUITO 
Superficie 	: 283560 km2 
Population 	: 13183978 Habitants

 24) GRENADE
continent code 	: 2
Capitale   	: SAINT-GEORGES 
Superficie 	: 344 km2 
Population 	: 89260 Habitants

 25) GUAM
continent code 	: 2
Capitale   	: AGANA 
Superficie 	: 541 km2 
Population 	: 160796 Habitants

 26) GUATEMALA
continent code 	: 2
Capitale   	: GUATEMALA CIUDAD 
Superficie 	: 108890 km2 
Population 	: 12974361 Habitants

 27) GUYANA
continent code 	: 2
Capitale   	: GEORGETOWN 
Superficie 	: 214970 km2 
Population 	: 697181 Habitants

 28) HAITI
continent code 	: 2
Capitale   	: PORT-AU-PRINCE 
Superficie 	: 27750 km2 
Population 	: 7527817 Habitants

 29) HONDURAS
continent code 	: 2
Capitale   	: TEGUCIGALPA 
Superficie 	: 112090 km2 
Population 	: 6249598 Habitants

 30) ILES CAIMANS
continent code 	: 2
Capitale   	: GEORGE TOWN 
Superficie 	: 262 km2 
Population 	: 39000 Habitants

 31) ILES VIERGES BRITANNIQUES
continent code 	: 2
Capitale   	: ROAD TOWN 
Superficie 	: 153 km2 
Population 	: 19000 Habitants

 32) NICARAGUA
continent code 	: 2
Capitale   	: MANAGUA 
Superficie 	: 129494 km2 
Population 	: 5128517 Habitants

 33) PANAMA
continent code 	: 2
Capitale   	: PANAMA 
Superficie 	: 78200 km2 
Population 	: 2845647 Habitants

 34) PEROU
continent code 	: 2
Capitale   	: LIMA 
Superficie 	: 1285220 km2 
Population 	: 27949639 Habitants

 35) PORTO RICO
continent code 	: 2
Capitale   	: SAN JUAN 
Superficie 	: 8959 km2 
Population 	: 3000000 Habitants

 36) SAINT KITTSETNEVIS
continent code 	: 2
Capitale   	: BASSETERRE 
Superficie 	: 261 km2 
Population 	: 38756 Habitants

 37) SAINT-VINCENT-ET-LES GRENADINES
continent code 	: 2
Capitale   	: KINGSTOWN 
Superficie 	: 389 km2 
Population 	: 116812 Habitants

 38) SAINTE LUCIE
continent code 	: 2
Capitale   	: CASTRIES 
Superficie 	: 620 km2 
Population 	: 160145 Habitants

 39) SURINAME
continent code 	: 2
Capitale   	: PARAMARIBO 
Superficie 	: 163270 km2 
Population 	: 433998 Habitants

 40) URUGUAY
continent code 	: 2
Capitale   	: MONTEVIDEO 
Superficie 	: 176220 km2 
Population 	: 3360105 Habitants


Partie 2 : Recherche séquentielle en fonction de la capitale.

On procède a la recherche de WASHINGTON :

Le pays a été trouvé !

ETATS-UNIS
continent code 	: 2
Capitale   	: WASHINGTON 
Superficie 	: 9629047 km2 
Population 	: 291289535 Habitants


On procède a la recherche de OTTAWA :

Le pays a été trouvé !

CANADA
continent code 	: 2
Capitale   	: OTTAWA 
Superficie 	: 9984670 km2 
Population 	: 31499560 Habitants


On procède a la recherche de SANTIAGO :

Le pays n'a pas été trouvé.


Partie 3 : Les 7 premiers pays triés en fonction de la capitale sont :

1) EMIRATS ARABES UNIS
continent code 	: 3
Capitale   	: ABOU DHABI 
Superficie 	: 82880 km2 
Population 	: 2407460 Habitants

2) NIGERIA
continent code 	: 1
Capitale   	: ABUJA 
Superficie 	: 923768 km2 
Population 	: 133881703 Habitants

3) GHANA
continent code 	: 1
Capitale   	: ACCRA 
Superficie 	: 238540 km2 
Population 	: 19894014 Habitants

4) TURKMENISTAN
continent code 	: 3
Capitale   	: ACHGABAT 
Superficie 	: 488100 km2 
Population 	: 4603244 Habitants

5) ETHIOPIE
continent code 	: 1
Capitale   	: ADDIS-ABEBA 
Superficie 	: 1127127 km2 
Population 	: 67673031 Habitants

6) GUAM
continent code 	: 2
Capitale   	: AGANA 
Superficie 	: 541 km2 
Population 	: 160796 Habitants

7) ALGERIE
continent code 	: 1
Capitale   	: ALGER 
Superficie 	: 2381740 km2 
Population 	: 31763053 Habitants


Partie 4 : Affichage de tous les pays triés en fonction de la capitale après l'insertion du Chili :

1) EMIRATS ARABES UNIS
continent code 	: 3
Capitale   	: ABOU DHABI 
Superficie 	: 82880 km2 
Population 	: 2407460 Habitants

2) NIGERIA
continent code 	: 1
Capitale   	: ABUJA 
Superficie 	: 923768 km2 
Population 	: 133881703 Habitants

3) GHANA
continent code 	: 1
Capitale   	: ACCRA 
Superficie 	: 238540 km2 
Population 	: 19894014 Habitants

4) TURKMENISTAN
continent code 	: 3
Capitale   	: ACHGABAT 
Superficie 	: 488100 km2 
Population 	: 4603244 Habitants

5) ETHIOPIE
continent code 	: 1
Capitale   	: ADDIS-ABEBA 
Superficie 	: 1127127 km2 
Population 	: 67673031 Habitants

6) GUAM
continent code 	: 2
Capitale   	: AGANA 
Superficie 	: 541 km2 
Population 	: 160796 Habitants

7) ALGERIE
continent code 	: 1
Capitale   	: ALGER 
Superficie 	: 2381740 km2 
Population 	: 31763053 Habitants

8) JORDANIE
continent code 	: 3
Capitale   	: AMMAN 
Superficie 	: 92300 km2 
Population 	: 5153378 Habitants

9) PAYS-BAS
continent code 	: 5
Capitale   	: AMSTERDAM 
Superficie 	: 41526 km2 
Population 	: 16135992 Habitants

10) ANDORRE
continent code 	: 5
Capitale   	: ANDORRA LA VELLA 
Superficie 	: 468 km2 
Population 	: 67627 Habitants

11) TURQUIE
continent code 	: 5
Capitale   	: ANKARA 
Superficie 	: 780580 km2 
Population 	: 67308928 Habitants

12) MADAGASCAR
continent code 	: 1
Capitale   	: ANTANARIVO 
Superficie 	: 587040 km2 
Population 	: 16979744 Habitants

13) ERYTHREE
continent code 	: 1
Capitale   	: ASMARA 
Superficie 	: 121320 km2 
Population 	: 4465651 Habitants

14) SAMOA
continent code 	: 4
Capitale   	: ASPIA 
Superficie 	: 2944 km2 
Population 	: 178173 Habitants

15) KAZAKHSTAN
continent code 	: 3
Capitale   	: ASTANA 
Superficie 	: 2717300 km2 
Population 	: 16741519 Habitants

16) PARAGUAY
continent code 	: 2
Capitale   	: ASUNCIO 
Superficie 	: 406750 km2 
Population 	: 5734139 Habitants

17) GRECE
continent code 	: 5
Capitale   	: ATHENES 
Superficie 	: 131940 km2 
Population 	: 10623835 Habitants

18) IRAK
continent code 	: 3
Capitale   	: BAGDAD 
Superficie 	: 437072 km2 
Population 	: 23331985 Habitants

19) AZERBAIDJAN
continent code 	: 3
Capitale   	: BAKU 
Superficie 	: 86100 km2 
Population 	: 7830764 Habitants

20) MALI
continent code 	: 1
Capitale   	: BAMAKO 
Superficie 	: 1241000 km2 
Population 	: 11008518 Habitants

21) BRUNEI
continent code 	: 3
Capitale   	: BANDAR SERI BEGAWAN 
Superficie 	: 5770 km2 
Population 	: 343653 Habitants

22) THAILANDE
continent code 	: 3
Capitale   	: BANGKOK 
Superficie 	: 514000 km2 
Population 	: 62354402 Habitants

23) REPUBLIQUE CENTRAFRICAINE
continent code 	: 1
Capitale   	: BANGUI 
Superficie 	: 622984 km2 
Population 	: 3742482 Habitants

24) GAMBIE
continent code 	: 1
Capitale   	: BANJUL 
Superficie 	: 11300 km2 
Population 	: 1411205 Habitants

25) SAINT KITTSETNEVIS
continent code 	: 2
Capitale   	: BASSETERRE 
Superficie 	: 261 km2 
Population 	: 38756 Habitants

26) SERBIE ET MONTENEGRO
continent code 	: 5
Capitale   	: BELGRADE 
Superficie 	: 102173 km2 
Population 	: 10660000 Habitants

27) BELIZE
continent code 	: 2
Capitale   	: BELMOPAN 
Superficie 	: 22966 km2 
Population 	: 266440 Habitants

28) ALLEMAGNE
continent code 	: 5
Capitale   	: BERLIN 
Superficie 	: 357027 km2 
Population 	: 82537000 Habitants

29) SUISSE
continent code 	: 5
Capitale   	: BERNE 
Superficie 	: 41285 km2 
Population 	: 7261200 Habitants

30) LIBAN
continent code 	: 3
Capitale   	: BEYROUTH 
Superficie 	: 10452 km2 
Population 	: 3727703 Habitants

31) KIRGHIZISTAN
continent code 	: 3
Capitale   	: BICHLEK 
Superficie 	: 198500 km2 
Population 	: 4753003 Habitants

32) GUINEE-BISSAU
continent code 	: 1
Capitale   	: BISSAU 
Superficie 	: 36120 km2 
Population 	: 1345479 Habitants

33) COLOMBIE
continent code 	: 2
Capitale   	: BOGOTA 
Superficie 	: 1138910 km2 
Population 	: 41088227 Habitants

34) BRESIL
continent code 	: 2
Capitale   	: BRASILIA 
Superficie 	: 8511965 km2 
Population 	: 174468575 Habitants

35) SLOVAQUIE
continent code 	: 5
Capitale   	: BRATISLAVA 
Superficie 	: 48845 km2 
Population 	: 5414937 Habitants

36) CONGO
continent code 	: 1
Capitale   	: BRAZZAVILLE 
Superficie 	: 341821 km2 
Population 	: 2894336 Habitants

37) BARBADE
continent code 	: 2
Capitale   	: BRIDGETOWN 
Superficie 	: 430 km2 
Population 	: 276607 Habitants

38) BELGIQUE
continent code 	: 5
Capitale   	: BRUXELLES 
Superficie 	: 32545 km2 
Population 	: 10309725 Habitants

39) ROUMANIE
continent code 	: 5
Capitale   	: BUCAREST 
Superficie 	: 238390 km2 
Population 	: 22272000 Habitants

40) HONGRIE
continent code 	: 5
Capitale   	: BUDAPEST 
Superficie 	: 93030 km2 
Population 	: 10106017 Habitants

41) ARGENTINE
continent code 	: 2
Capitale   	: BUENOS AIRES 
Superficie 	: 2766890 km2 
Population 	: 37812817 Habitants

42) BURUNDI
continent code 	: 1
Capitale   	: BUJUMBURA 
Superficie 	: 27830 km2 
Population 	: 6223897 Habitants

43) AUSTRALIE
continent code 	: 4
Capitale   	: CANBERRA 
Superficie 	: 7686850 km2 
Population 	: 19834248 Habitants

44) VENEZUALA
continent code 	: 2
Capitale   	: CARACAS 
Superficie 	: 912050 km2 
Population 	: 23542649 Habitants

45) SAINTE LUCIE
continent code 	: 2
Capitale   	: CASTRIES 
Superficie 	: 620 km2 
Population 	: 160145 Habitants

46) MOLDAVIE
continent code 	: 5
Capitale   	: CHISINAU 
Superficie 	: 33843 km2 
Population 	: 4431570 Habitants

47) SRI LANKA
continent code 	: 3
Capitale   	: COLOMBO 
Superficie 	: 65610 km2 
Population 	: 19607519 Habitants

48) GUINEE
continent code 	: 1
Capitale   	: CONAKRY 
Superficie 	: 245857 km2 
Population 	: 7613870 Habitants

49) DANEMARK
continent code 	: 5
Capitale   	: COPENHAGUE 
Superficie 	: 43094 km2 
Population 	: 5413392 Habitants

50) BENIN
continent code 	: 1
Capitale   	: COTONOU 
Superficie 	: 112620 km2 
Population 	: 7041490 Habitants

51) BANGLADESH
continent code 	: 3
Capitale   	: DACCA 
Superficie 	: 144000 km2 
Population 	: 133376684 Habitants

52) SENEGAL
continent code 	: 1
Capitale   	: DAKAR 
Superficie 	: 196190 km2 
Population 	: 10284929 Habitants

53) SYRIE
continent code 	: 3
Capitale   	: DAMAS 
Superficie 	: 185180 km2 
Population 	: 17585540 Habitants

54) TIMOR LESTE
continent code 	: 3
Capitale   	: DILI 
Superficie 	: 15007 km2 
Population 	: 952618 Habitants

55) INDONESIE
continent code 	: 3
Capitale   	: DJAKARTA 
Superficie 	: 1919440 km2 
Population 	: 228437870 Habitants

56) DJIBOUTI
continent code 	: 1
Capitale   	: DJIBOUTI 
Superficie 	: 22000 km2 
Population 	: 460700 Habitants

57) TANZANIE
continent code 	: 1
Capitale   	: DODOMA 
Superficie 	: 945090 km2 
Population 	: 35922454 Habitants

58) QATAR
continent code 	: 3
Capitale   	: DOHA 
Superficie 	: 11437 km2 
Population 	: 769152 Habitants

59) TADJIKISTAN
continent code 	: 3
Capitale   	: DOUCHANBE 
Superficie 	: 143000 km2 
Population 	: 6719567 Habitants

60) IRLANDE
continent code 	: 5
Capitale   	: DUBLIN 
Superficie 	: 70273 km2 
Population 	: 3917336 Habitants

61) ARMENIE
continent code 	: 3
Capitale   	: EREVAN 
Superficie 	: 29800 km2 
Population 	: 3326448 Habitants

62) SIERRA LEONE
continent code 	: 1
Capitale   	: FREETOWN 
Superficie 	: 71740 km2 
Population 	: 5426618 Habitants

63) BOTSWANA
continent code 	: 1
Capitale   	: GABORONE 
Superficie 	: 581730 km2 
Population 	: 1812547 Habitants

64) ILES CAIMANS
continent code 	: 2
Capitale   	: GEORGE TOWN 
Superficie 	: 262 km2 
Population 	: 39000 Habitants

65) GUYANA
continent code 	: 2
Capitale   	: GEORGETOWN 
Superficie 	: 214970 km2 
Population 	: 697181 Habitants

66) GUATEMALA
continent code 	: 2
Capitale   	: GUATEMALA CIUDAD 
Superficie 	: 108890 km2 
Population 	: 12974361 Habitants

67) BERMUDES
continent code 	: 2
Capitale   	: HAMILTON 
Superficie 	: 53 km2 
Population 	: 113208 Habitants

68) VIETNAM
continent code 	: 3
Capitale   	: HANOI 
Superficie 	: 329560 km2 
Population 	: 81098416 Habitants

69) ZIMBABWE
continent code 	: 1
Capitale   	: HARARE 
Superficie 	: 350580 km2 
Population 	: 12576742 Habitants

70) FINLANDE
continent code 	: 5
Capitale   	: HELSINKI 
Superficie 	: 337030 km2 
Population 	: 5211311 Habitants

71) ILES SALOMON
continent code 	: 4
Capitale   	: HONIARA 
Superficie 	: 28450 km2 
Population 	: 480442 Habitants

72) PAKISTAN
continent code 	: 3
Capitale   	: ISLAMABAD 
Superficie 	: 803940 km2 
Population 	: 147663429 Habitants

73) ISRAEL
continent code 	: 5
Capitale   	: JERUSALEM 
Superficie 	: 20770 km2 
Population 	: 6116533 Habitants

74) AFGHANISTAN
continent code 	: 3
Capitale   	: KABOUL 
Superficie 	: 652225 km2 
Population 	: 29547078 Habitants

75) OUGANDA
continent code 	: 1
Capitale   	: KAMPALA 
Superficie 	: 236040 km2 
Population 	: 24699073 Habitants

76) NEPAL
continent code 	: 3
Capitale   	: KATMANDOU 
Superficie 	: 140800 km2 
Population 	: 25284463 Habitants

77) SOUDAN
continent code 	: 1
Capitale   	: KHARTOUM 
Superficie 	: 2505810 km2 
Population 	: 37090298 Habitants

78) UKRAINE
continent code 	: 5
Capitale   	: KIEV 
Superficie 	: 603700 km2 
Population 	: 48396470 Habitants

79) RWANDA
continent code 	: 1
Capitale   	: KIGALI 
Superficie 	: 26338 km2 
Population 	: 7312756 Habitants

80) JAMAIQUE
continent code 	: 2
Capitale   	: KINGSTON 
Superficie 	: 10991 km2 
Population 	: 1695867 Habitants

81) SAINT-VINCENT-ET-LES GRENADINES
continent code 	: 2
Capitale   	: KINGSTOWN 
Superficie 	: 389 km2 
Population 	: 116812 Habitants

82) REPUBLIQUE DEMOCRATIQUE DU CONGO
continent code 	: 1
Capitale   	: KINSHASA 
Superficie 	: 2345410 km2 
Population 	: 55225478 Habitants

83) PALAOS
continent code 	: 4
Capitale   	: KOROR 
Superficie 	: 458 km2 
Population 	: 19092 Habitants

84) KOWEIT
continent code 	: 3
Capitale   	: KOWEIT 
Superficie 	: 17820 km2 
Population 	: 2041961 Habitants

85) MALAISIE
continent code 	: 3
Capitale   	: KUALA LAMPUR 
Superficie 	: 329750 km2 
Population 	: 21793293 Habitants

86) CUBA
continent code 	: 2
Capitale   	: LA HAVANE 
Superficie 	: 100860 km2 
Population 	: 11184023 Habitants

87) BOLIVIE
continent code 	: 2
Capitale   	: LA PAZ 
Superficie 	: 1098580 km2 
Population 	: 8724156 Habitants

88) MALTE
continent code 	: 5
Capitale   	: LA VALETTE 
Superficie 	: 316 km2 
Population 	: 394583 Habitants

89) EGYPTE
continent code 	: 1
Capitale   	: LE CAIRE 
Superficie 	: 995450 km2 
Population 	: 74718797 Habitants

90) GABON
continent code 	: 1
Capitale   	: LIBREVILLE 
Superficie 	: 267667 km2 
Population 	: 1155749 Habitants

91) MALAWI
continent code 	: 1
Capitale   	: LILONGWE 
Superficie 	: 118484 km2 
Population 	: 10000000 Habitants

92) PEROU
continent code 	: 2
Capitale   	: LIMA 
Superficie 	: 1285220 km2 
Population 	: 27949639 Habitants

93) PORTUGAL
continent code 	: 5
Capitale   	: LISBONNE 
Superficie 	: 92391 km2 
Population 	: 10066253 Habitants

94) SLOVENIE
continent code 	: 5
Capitale   	: LJUBLJANA 
Superficie 	: 20273 km2 
Population 	: 1930132 Habitants

95) TOGO
continent code 	: 1
Capitale   	: LOME 
Superficie 	: 56785 km2 
Population 	: 5018502 Habitants

96) ROYAUME-UNI
continent code 	: 5
Capitale   	: LONDRES 
Superficie 	: 244101 km2 
Population 	: 58789194 Habitants

97) ANGOLA
continent code 	: 1
Capitale   	: LUANDA 
Superficie 	: 1246700 km2 
Population 	: 10766471 Habitants

98) ZAMBIE
continent code 	: 1
Capitale   	: LUSAKA 
Superficie 	: 752614 km2 
Population 	: 9582418 Habitants

99) LUXEMBOURG
continent code 	: 5
Capitale   	: LUXEMBOURG 
Superficie 	: 2586 km2 
Population 	: 442972 Habitants

100) ESPAGNE
continent code 	: 5
Capitale   	: MADRID 
Superficie 	: 504782 km2 
Population 	: 40037995 Habitants

101) GUINEE EQUATORIALE
continent code 	: 1
Capitale   	: MALABO 
Superficie 	: 28051 km2 
Population 	: 486060 Habitants

102) MALDIVES
continent code 	: 3
Capitale   	: MALE 
Superficie 	: 298 km2 
Population 	: 329684 Habitants

103) NICARAGUA
continent code 	: 2
Capitale   	: MANAGUA 
Superficie 	: 129494 km2 
Population 	: 5128517 Habitants

104) BAHREIN
continent code 	: 3
Capitale   	: MANAMA 
Superficie 	: 707 km2 
Population 	: 645361 Habitants

105) PHILIPPINES
continent code 	: 3
Capitale   	: MANILLE 
Superficie 	: 300000 km2 
Population 	: 86241697 Habitants

106) MOZAMBIQUE
continent code 	: 1
Capitale   	: MAPUTO 
Superficie 	: 801590 km2 
Population 	: 19104696 Habitants

107) OMAN
continent code 	: 3
Capitale   	: MASCATE 
Superficie 	: 212460 km2 
Population 	: 2622198 Habitants

108) LESOTHO
continent code 	: 1
Capitale   	: MASERU 
Superficie 	: 30355 km2 
Population 	: 1861959 Habitants

109) SWAZILAND
continent code 	: 1
Capitale   	: MBABANE 
Superficie 	: 17363 km2 
Population 	: 1104343 Habitants

110) MEXIQUE
continent code 	: 2
Capitale   	: MEXICO 
Superficie 	: 1972550 km2 
Population 	: 103400165 Habitants

111) BELARUS
continent code 	: 5
Capitale   	: MINSK 
Superficie 	: 207600 km2 
Population 	: 10350194 Habitants

112) MONACO
continent code 	: 5
Capitale   	: MONACO 
Superficie 	: 195 km2 
Population 	: 31842 Habitants

113) LIBERIA
continent code 	: 1
Capitale   	: MONROVIA 
Superficie 	: 111370 km2 
Population 	: 3317176 Habitants

114) URUGUAY
continent code 	: 2
Capitale   	: MONTEVIDEO 
Superficie 	: 176220 km2 
Population 	: 3360105 Habitants

115) SOMALIE
continent code 	: 1
Capitale   	: MOQDISHO 
Superficie 	: 637657 km2 
Population 	: 8025190 Habitants

116) COMORES
continent code 	: 3
Capitale   	: MORONI 
Superficie 	: 2170 km2 
Population 	: 596202 Habitants

117) RUSSIE
continent code 	: 5
Capitale   	: MOSCOU 
Superficie 	: 7075400 km2 
Population 	: 143954573 Habitants

118) BAHAMAS
continent code 	: 2
Capitale   	: NASSAU 
Superficie 	: 13940 km2 
Population 	: 300529 Habitants

119) NAURU
continent code 	: 4
Capitale   	: NAURUAN 
Superficie 	: 21 km2 
Population 	: 12329 Habitants

120) TCHAD
continent code 	: 1
Capitale   	: NDJAMENA 
Superficie 	: 1284000 km2 
Population 	: 9253493 Habitants

121) KENYA
continent code 	: 1
Capitale   	: NEIROBI 
Superficie 	: 582650 km2 
Population 	: 31138735 Habitants

122) INDE
continent code 	: 3
Capitale   	: NEW DELHI 
Superficie 	: 3287590 km2 
Population 	: 29991145 Habitants

123) NIGER
continent code 	: 1
Capitale   	: NIAMEY 
Superficie 	: 1267000 km2 
Population 	: 11058590 Habitants

124) CHYPRE
continent code 	: 5
Capitale   	: NICOSIE 
Superficie 	: 9250 km2 
Population 	: 771657 Habitants

125) MAURITANIE
continent code 	: 1
Capitale   	: NOUAKCHOTT 
Superficie 	: 1026000 km2 
Population 	: 2000000 Habitants

126) TONGA
continent code 	: 4
Capitale   	: NUKUALOFA 
Superficie 	: 748 km2 
Population 	: 108141 Habitants

127) ARUBA
continent code 	: 2
Capitale   	: ORANJESTAD 
Superficie 	: 193 km2 
Population 	: 69000 Habitants

128) NORVEGE
continent code 	: 5
Capitale   	: OSLO 
Superficie 	: 324220 km2 
Population 	: 4525116 Habitants

129) CANADA
continent code 	: 2
Capitale   	: OTTAWA 
Superficie 	: 9984670 km2 
Population 	: 31499560 Habitants

130) BURKINA-FASO
continent code 	: 1
Capitale   	: OUAGADOUGOU 
Superficie 	: 274200 km2 
Population 	: 12272289 Habitants

131) MONGOLIE
continent code 	: 3
Capitale   	: OULAN-BATOR 
Superficie 	: 1565000 km2 
Population 	: 2654999 Habitants

132) MICRONESIE
continent code 	: 4
Capitale   	: PALIKIR 
Superficie 	: 702 km2 
Population 	: 108143 Habitants

133) PANAMA
continent code 	: 2
Capitale   	: PANAMA 
Superficie 	: 78200 km2 
Population 	: 2845647 Habitants

134) SURINAME
continent code 	: 2
Capitale   	: PARAMARIBO 
Superficie 	: 163270 km2 
Population 	: 433998 Habitants

135) FRANCE
continent code 	: 5
Capitale   	: PARIS 
Superficie 	: 543964 km2 
Population 	: 61387038 Habitants

136) CHINE
continent code 	: 3
Capitale   	: PEKIN 
Superficie 	: 9596960 km2 
Population 	: 273111290 Habitants

137) CAMBODGE
continent code 	: 3
Capitale   	: PHNOM PENH 
Superficie 	: 181040 km2 
Population 	: 13124764 Habitants

138) TRINITE-ET-TOBAGO
continent code 	: 2
Capitale   	: PORT D'ESPAGNE 
Superficie 	: 5128 km2 
Population 	: 1104209 Habitants

139) MAURICE
continent code 	: 1
Capitale   	: PORT LOUIS 
Superficie 	: 1860 km2 
Population 	: 1189825 Habitants

140) PAPOUASIE-NOUVELLE-GUINEE
continent code 	: 4
Capitale   	: PORT MORESBY 
Superficie 	: 462840 km2 
Population 	: 4927000 Habitants

141) HAITI
continent code 	: 2
Capitale   	: PORT-AU-PRINCE 
Superficie 	: 27750 km2 
Population 	: 7527817 Habitants

142) VANUATU
continent code 	: 4
Capitale   	: PORT-VILA 
Superficie 	: 12200 km2 
Population 	: 199414 Habitants

143) REPUBLIQUE TCHEQUE
continent code 	: 5
Capitale   	: PRAGUE 
Superficie 	: 78866 km2 
Population 	: 10264212 Habitants

144) CAP VERT
continent code 	: 1
Capitale   	: PRAIA 
Superficie 	: 4033 km2 
Population 	: 405163 Habitants

145) AFRIQUE DU SUD
continent code 	: 1
Capitale   	: PRETORIA 
Superficie 	: 1219912 km2 
Population 	: 42718530 Habitants

146) COREE DU NORD
continent code 	: 3
Capitale   	: PYONGYANG 
Superficie 	: 120540 km2 
Population 	: 21968228 Habitants

147) EQUATEUR
continent code 	: 2
Capitale   	: QUITO 
Superficie 	: 283560 km2 
Population 	: 13183978 Habitants

148) MYANMAR
continent code 	: 3
Capitale   	: RANGOUN 
Superficie 	: 678500 km2 
Population 	: 42510537 Habitants

149) ISLANDE
continent code 	: 5
Capitale   	: REYKJAVIC 
Superficie 	: 103125 km2 
Population 	: 288201 Habitants

150) LETTONIE
continent code 	: 5
Capitale   	: RIGA 
Superficie 	: 64589 km2 
Population 	: 2385231 Habitants

151) ARABIE SAOUDITE
continent code 	: 3
Capitale   	: RIYAD 
Superficie 	: 1960582 km2 
Population 	: 23513330 Habitants

152) ILES VIERGES BRITANNIQUES
continent code 	: 2
Capitale   	: ROAD TOWN 
Superficie 	: 153 km2 
Population 	: 19000 Habitants

153) ITALIE
continent code 	: 5
Capitale   	: ROME 
Superficie 	: 301230 km2 
Population 	: 57715620 Habitants

154) DOMINIQUE
continent code 	: 4
Capitale   	: ROSEAU 
Superficie 	: 754 km2 
Population 	: 75851 Habitants

155) SAINT MARIN
continent code 	: 5
Capitale   	: SAINT MARIN 
Superficie 	: 61 km2 
Population 	: 27336 Habitants

156) REPUBLIQUE DOMINICAINE
continent code 	: 2
Capitale   	: SAINT-DOMINGUE 
Superficie 	: 48730 km2 
Population 	: 8442533 Habitants

157) GRENADE
continent code 	: 2
Capitale   	: SAINT-GEORGES 
Superficie 	: 344 km2 
Population 	: 89260 Habitants

158) ANTIGUA-ET-BARBUDA
continent code 	: 2
Capitale   	: SAINT-JOHNS 
Superficie 	: 442 km2 
Population 	: 67448 Habitants

159) COSTA RICA
continent code 	: 2
Capitale   	: SAN JOSE 
Superficie 	: 51100 km2 
Population 	: 3835000 Habitants

160) PORTO RICO
continent code 	: 2
Capitale   	: SAN JUAN 
Superficie 	: 8959 km2 
Population 	: 3000000 Habitants

161) EL SALVADOR
continent code 	: 2
Capitale   	: SAN SALVADOR 
Superficie 	: 21041 km2 
Population 	: 6122075 Habitants

162) YEMEN
continent code 	: 3
Capitale   	: SANAA 
Superficie 	: 527970 km2 
Population 	: 19349881 Habitants

163) CHILI
continent code 	: 1
Capitale   	: SANTIAGO 
Superficie 	: 756950 km2 
Population 	: 15328467 Habitants

164) SAO TOME
continent code 	: 1
Capitale   	: SAO TOME 
Superficie 	: 1001 km2 
Population 	: 165034 Habitants

165) BOSNIE-HERZEGOVINE
continent code 	: 5
Capitale   	: SARAJEVO 
Superficie 	: 51129 km2 
Population 	: 3922205 Habitants

166) COREE DU SUD
continent code 	: 3
Capitale   	: SEOUL 
Superficie 	: 99274 km2 
Population 	: 48324000 Habitants

167) SINGAPOUR
continent code 	: 3
Capitale   	: SINGAPORE 
Superficie 	: 692 km2 
Population 	: 4608595 Habitants

168) MACEDOINE
continent code 	: 5
Capitale   	: SKOPJE 
Superficie 	: 25713 km2 
Population 	: 2100000 Habitants

169) BULGARIE
continent code 	: 5
Capitale   	: SOFIA 
Superficie 	: 110910 km2 
Population 	: 7707495 Habitants

170) SUEDE
continent code 	: 5
Capitale   	: STOCKHOLM 
Superficie 	: 449964 km2 
Population 	: 8875053 Habitants

171) FIDJI
continent code 	: 4
Capitale   	: SUVA 
Superficie 	: 18270 km2 
Population 	: 844330 Habitants

172) OUZBEKISTAN
continent code 	: 3
Capitale   	: TACHKENT 
Superficie 	: 447400 km2 
Population 	: 25563441 Habitants

173) TAIWAN
continent code 	: 3
Capitale   	: TAIPEI 
Superficie 	: 36006 km2 
Population 	: 22000000 Habitants

174) ESTONIE
continent code 	: 5
Capitale   	: TALINN 
Superficie 	: 45226 km2 
Population 	: 1401945 Habitants

175) KIRIBATI
continent code 	: 4
Capitale   	: TARAWA SUD 
Superficie 	: 811 km2 
Population 	: 98549 Habitants

176) GEORGIE
continent code 	: 3
Capitale   	: TBILISSI 
Superficie 	: 69700 km2 
Population 	: 4934413 Habitants

177) HONDURAS
continent code 	: 2
Capitale   	: TEGUCIGALPA 
Superficie 	: 112090 km2 
Population 	: 6249598 Habitants

178) IRAN
continent code 	: 3
Capitale   	: TEHERAN 
Superficie 	: 1648000 km2 
Population 	: 76000000 Habitants

179) DES OURAGANS
continent code 	: 4
Capitale   	: TEMPETE 
Superficie 	: 1 km2 
Population 	: 1 Habitants

180) BHOUTAN
continent code 	: 3
Capitale   	: THIMPHOU 
Superficie 	: 47000 km2 
Population 	: 2094176 Habitants

181) ALBANIE
continent code 	: 5
Capitale   	: TIRANA 
Superficie 	: 28748 km2 
Population 	: 3510484 Habitants

182) JAPON
continent code 	: 3
Capitale   	: TOKYO 
Superficie 	: 377835 km2 
Population 	: 12761000 Habitants

183) LIBYE
continent code 	: 1
Capitale   	: TRIPOLI 
Superficie 	: 1759540 km2 
Population 	: 5499074 Habitants

184) TUNISIE
continent code 	: 1
Capitale   	: TUNIS 
Superficie 	: 164150 km2 
Population 	: 9593402 Habitants

185) LIECHTENSTEIN
continent code 	: 5
Capitale   	: VADUZ 
Superficie 	: 160 km2 
Population 	: 32528 Habitants

186) POLOGNE
continent code 	: 5
Capitale   	: VARSOVIE 
Superficie 	: 312685 km2 
Population 	: 38633912 Habitants

187) HONG-KONG
continent code 	: 3
Capitale   	: VICTORIA 
Superficie 	: 1067 km2 
Population 	: 6000000 Habitants

188) SEYCHELLES
continent code 	: 1
Capitale   	: VICTORIA 
Superficie 	: 455 km2 
Population 	: 80098 Habitants

189) AUTRICHE
continent code 	: 5
Capitale   	: VIENNE 
Superficie 	: 83858 km2 
Population 	: 8150835 Habitants

190) LAOS
continent code 	: 3
Capitale   	: VIENTIANE 
Superficie 	: 236800 km2 
Population 	: 5635967 Habitants

191) LITUANIE
continent code 	: 5
Capitale   	: VILNIUS 
Superficie 	: 65200 km2 
Population 	: 3610535 Habitants

192) ETATS-UNIS
continent code 	: 2
Capitale   	: WASHINGTON 
Superficie 	: 9629047 km2 
Population 	: 291289535 Habitants

193) NOUVELLE-ZELANDE
continent code 	: 4
Capitale   	: WELLINGTON 
Superficie 	: 268680 km2 
Population 	: 3864129 Habitants

194) ANTILLES NEERLANDAISES
continent code 	: 2
Capitale   	: WILLEMSTAD 
Superficie 	: 800 km2 
Population 	: 210000 Habitants

195) NAMIBIE
continent code 	: 1
Capitale   	: WINDHOEK 
Superficie 	: 825418 km2 
Population 	: 1820918 Habitants

196) COTE D'IVOIRE
continent code 	: 1
Capitale   	: YAMOUSSOUKRO 
Superficie 	: 322460 km2 
Population 	: 16962491 Habitants

197) CAMEROUN
continent code 	: 1
Capitale   	: YAOUNDE 
Superficie 	: 475440 km2 
Population 	: 15746179 Habitants

198) CROATIE
continent code 	: 5
Capitale   	: ZAGREB 
Superficie 	: 56542 km2 
Population 	: 4390751 Habitants

*/

