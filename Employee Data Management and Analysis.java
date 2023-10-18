/*

FICHIER TP 2 - A

Auteur : Daniel Kessler
Cours : IFT 1170
But du Programme :

Ecrire des constructeurs en utilisant "this" pour instancier 4 employes.
Ecrire la methode pour afficher les informations de emp3 et emp4.
MOdifier le salaire de emp2 = salaire de emp1 + emp2. Ensuite afficher les informations de emp2.
Ecrire la declaration et initiation d'un nouveau tableau de 6 employes, ensuite l'afficher.
Compter et afficher le nombre d'employes gagnant moins de 1300$ par semaine dont le NAS contient '5'.
Compter et afficher le nombre d'employes gagnant moins de 750$ par semaine dont le NAS contient '3'.
Trier le tableau en fonction du NAS (avec un tri rapide)
Creer le fichier texte "empTri.dta"

 */

import java.util.*;
import java.io.*;

class Employe {

	private String NAS; // numero d'assurance sociale
	private double salHebdo; // salaire hebdomadaire
	private double tauxHor; // taux horaire

	// 1. Constructeurs en utilisant THIS pour instancier les 4 employes

	// Premier constructeur
	public Employe(String NAS, double salHebdo) {
		this.NAS = NAS;
		this.salHebdo = salHebdo;
		this.tauxHor = 1;
	}

	// Deuxieme constructeur
	public Employe(String NAS) {
		this.NAS = NAS;
		salHebdo = 1250.25; // par defaut
		this.tauxHor = 1;
	}

	// Troisieme constructeur
	public Employe(String NAS, double salHebdo, double tauxHor) {
		this.NAS = NAS;
		this.salHebdo = salHebdo;
		this.tauxHor = tauxHor;
	}

	public String getNAS() {
		return this.NAS;
	}

	public double getsalHebdo() {
		return this.salHebdo;
	}

	public double gettauxHor() {
		return this.tauxHor;
	}

	// 2. Methode pour afficher les informations de emp3 et emp4

	public void affichPersVoulue(String en_tete) {
		System.out.printf(en_tete);
		System.out.printf("- NAS 		       : %s\n", getNAS());
		System.out.println("- Salaire Hebdomadaire : " + gettauxHor() * getsalHebdo());
		System.out.println("\n");
	}

	// Methode de modification du salaire hebdomadaire
	public void setSalHebdo(double nouvSalHebdo) {
		this.salHebdo = nouvSalHebdo;
	}

	public String toString() {

		return String.format("%s	%.2f\n", NAS, salHebdo * tauxHor);

	}
}

public class TestEmploye {

	static void creerTexte(Employe[] emp, int nbEmp, String nomACreer) throws IOException {

		boolean probleme = false;
		FileWriter fw = null;
		try {
			fw = new FileWriter(nomACreer);
		} catch (java.io.FileNotFoundException erreur) {
			System.out.println("Problème de préparer l'écriture\n");
			probleme = true;
		}
		if (!probleme) {
			System.out.println("Début de la création du fichier\n");
			PrintWriter aCreer = new PrintWriter(fw);
			
			aCreer.printf("[i]	NAS		salaire\n");
			aCreer.printf("-------------------------------\n");

			for (int i = 0; i < nbEmp; i++) {
				aCreer.printf(" %d) 	%s", i, emp[i]);
			}

			aCreer.close();
			System.out.println("Fin de la création du fichier " + nomACreer + "\n\n");
		}
	}



	// Fonction pour trier le tableau des 6 employes en fonction du NAS

	static void trier(Employe[] emp, int nbEmp) {
		for (int i = 0; i < nbEmp - 1; i++) {
			int indMin = i;
			for (int j = i + 1; j < nbEmp; j++) {
				if (emp[j].getNAS().compareTo(emp[indMin].getNAS()) < 0) {
					indMin = j;
				}
			}
			if (indMin != i) {
				Employe temp = emp[i];
				emp[i] = emp[indMin];
				emp[indMin] = temp;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// Instanciation des employes

		Employe emp1 = new Employe("250 642 753", 1234.56), emp2 = new Employe("123 456 789"), // par defaut 1250.25$
				emp3 = new Employe("250 642 753", 40.00, 25.75), // salHebdo = 40 heures par semaine * taux horaire de
																	// 25.75$
				emp4 = new Employe("450 279 321", 1750.75);

		// 2. Affichage des informations des employes 3 et 4

		emp3.affichPersVoulue("Informations de la troisieme personne :\n");
		emp4.affichPersVoulue("Informations de la quatrieme personne :\n");

		// 3. Affichage des informations de l'employe 2 apres la modification

		emp2.setSalHebdo(emp1.getsalHebdo() + (emp3.getsalHebdo() * emp3.gettauxHor()));
		emp2.affichPersVoulue("\nInformations de l'employe 2 apres la modification :\n");

		// Tableau des 6 nouveaux employes

		Employe[] emp = { new Employe("250 642 753", 1234.25), new Employe("123 456 789", 1250.25),
				new Employe("250 343 654", 40.00, 17.25), new Employe("231 467 890", 1671.50),
				new Employe("478 343 689", 1750.75), new Employe("371 238 432", 50.00, 20.25) };
		int nbEmp = emp.length;

		// 4. Affichage du contenu du tableau avec la methode toString()

		System.out.printf("Contenu du tableau des %d employes :\n\n", nbEmp);
		System.out.printf("[i]	NAS		Salaire\n");
		System.out.println("---------------------------------");
		for (int i = 0; i < nbEmp; i++) {
			System.out.printf(" %d	%s", i, emp[i].toString());
		}

		// 5. Methode pour compter et afficher le nombre d'employes qui :

		// a) Gagnent moins de 1300.00$ par semaine dont le NAS contient '5'

		int nbEmp1300 = 0;
		for (int i = 0; i < nbEmp; i++) {
			if (emp[i].getsalHebdo() * emp[i].gettauxHor() < 1300 && emp[i].getNAS().indexOf('5') != -1) {
				nbEmp1300++;
			}
		}

		// b) Gagnent moins de 750.00$ par semaine dont le NAS contient '3'

		int nbEmp750 = 0;
		for (int i = 0; i < nbEmp; i++) {
			if (emp[i].getsalHebdo() * emp[i].gettauxHor() < 750 && emp[i].getNAS().indexOf('3') != -1) {
				nbEmp750++;
			}
		}

		System.out.printf(
				"\n\nIl y a %d employé(s) qui gagne(nt) moins de 1300$ par semaine et dont le NAS contient le chiffre 5.\n",
				nbEmp1300);
		System.out.printf(
				"Il y a %d employé(s) qui gagne(nt) moins de 750$ par semaine et dont le NAS contient le chiffre 3.\n",
				nbEmp750);

		// 6. Affichage du tableau des 6 employes apres trier en fonction du NAS

		trier(emp, nbEmp);
		System.out.printf("\n\nContenu du tableau des %d employes apres le tri :\n\n", nbEmp);
		System.out.printf("[i]	NAS		Salaire\n");
		System.out.println("---------------------------------");
		for (int i = 0; i < nbEmp; i++) {
			System.out.printf(" %d	%s", i, emp[i].toString());
		}
		
		creerTexte(emp, nbEmp, "empTri.dat");

	}
}

/*
 * 
 * EXECUTION :
 * 
 * Informations de la troisieme personne : - NAS : 250 642 753 - Salaire
 * Hebdomadaire : 1030.0
 * 
 * 
 * Informations de la quatrieme personne : - NAS : 450 279 321 - Salaire
 * Hebdomadaire : 1750.75
 * 
 * 
 * 
 * Informations de l'employe 2 apres la modification : - NAS : 123 456 789 -
 * Salaire Hebdomadaire : 2264.56
 * 
 * 
 * Contenu du tableau des 6 employes :
 * 
 * [i] NAS Salaire --------------------------------- 0 250 642 753 1234.25 1 123
 * 456 789 1250.25 2 250 343 654 690.00 3 231 467 890 1671.50 4 478 343 689
 * 1750.75 5 371 238 432 1012.50
 * 
 * 
 * Il y a 3 employé(s) qui gagne(nt) moins de 1300$ par semaine et dont le NAS
 * contient le chiffre 5. Il y a 1 employé(s) qui gagne(nt) moins de 750$ par
 * semaine et dont le NAS contient le chiffre 3.
 * 
 * 
 * Contenu du tableau des 6 employes apres le tri :
 * 
 * [i] NAS Salaire --------------------------------- 0 123 456 789 1250.25 1 231
 * 467 890 1671.50 2 250 343 654 690.00 3 250 642 753 1234.25 4 371 238 432
 * 1012.50 5 478 343 689 1750.75
 * 
 */
