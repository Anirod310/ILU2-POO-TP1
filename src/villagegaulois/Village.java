package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder output = new StringBuilder();
		output.append(vendeur + " cherche un endroit pour vendre " + nbProduit + " " + produit + "./n");
		int numEtal = marche.trouverEtalLibre();
		if(numEtal != -1) {
		output.append("Le vendeur " + vendeur + " vend des " + produit + " a l'etal n°" + numEtal);}
		else {
			String output1 = "Il n'y a pas d'etale libre.";
			return output1;
		}
	}
	
	private static class Marche { 
		private Etal[] etals;
		
		private Marche(int nbEtal) {
			etals = new Etal[nbEtal];
			for (int i = 0; i < nbEtal; i++) {
				etals[i] = new Etal(); 
			}
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur,
				 String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		 }
		
		public int trouverEtatLibre() {
			for (int i=0; i<etals.length; i++) {
				if(!(etals[i].isEtalOccupe())) {
					return i;
				}
			}
			return -1;
		}
		
		public Etal[] trouverEtals(String produit) {
			
			int counter = 0;
			
			for (int i=0; i<etals.length; i++) {
				if(etals[i].contientProduit(produit)) {
					counter ++;
				}
			}
			
			Etal[] etalAvecProduit = new Etal[counter];
			
			int pos = 0;
			
			for (int j=0; j<etals.length; j++) {
				if(etals[j].contientProduit(produit)) {
					etalAvecProduit[pos] = etals[j];
					pos ++;
				}
			}
			
			return etalAvecProduit;
			
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0; i<etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
					if(etals[i].getVendeur() == gaulois) {
						return etals[i];
					}
				}
			}
			
			return null;
		}
		
		public String afficherMarche(){
			int counter = 0;
			StringBuilder output = new StringBuilder();
			for (int i=0; i<etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
					output.append(etals[i].afficherEtal());
					counter ++;
				}
			}
			
			if (counter != etals.length) {
				int nbEtalVide = etals.length - counter;
				output.append("Il reste " +
						nbEtalVide + " étals non utilisés dans le marché.\n");
			}
			
			return output.toString();
		}
		
	}
}