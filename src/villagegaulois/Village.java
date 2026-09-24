package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new
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
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public abstract class Marche {
		private Etal[] etals;
		public Marche(int nbEtals) {
			etals = new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
			    etals[i] = new Etal();
			}
		}
		
		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		int trouverEtalLibre() {
			for(int i = 0; i< etals.length; i++) {
				if(!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			
			return -1;
		}
		
		 Etal[] trouverEtals(String produit) {
			 int nbProduit = 0;
			 for(int i = 0; i< etals.length; i++) {
				 if(etals[i].contientProduit(produit)) {
					 nbProduit ++;
				 }
			 }
			 
			 Etal[] etalsProduit = new Etal[nbProduit];
			 
			 for(int i = 0, j = 0; i< etals.length; i++) {
				 if(etals[i].contientProduit(produit)) {
					 if(j < etalsProduit.length) {
						 etalsProduit[j] = etals[i];
						 j ++;
					 }
				 }
			 }
			 
			 return etalsProduit;
		 }
		 
		 Etal trouverVendeur(Gaulois gaulois){
			 for(int i = 0; i< etals.length; i++) {
				 if(etals[i].getVendeur() == gaulois) {
					 return etals[i];
				 }
			 }
			 
			 return null;
		 }
		 
		 String afficherMarche(){
			 
			int nbEtalVide = 0;
			StringBuilder chaine = new StringBuilder();
			 
			 for(int i = 0; i<etals.length; i++) {
				 if(!etals[i].isEtalOccupe()) {
					 nbEtalVide++;
				 }
				 else {
					 chaine.append(etals[i].afficherEtal());
				 }
			 }
			 
			 if (nbEtalVide > 0) {
		            chaine.append("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");
		        }
		     return chaine.toString();
		 }
	
	}	
	
	
}