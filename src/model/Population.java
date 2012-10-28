package model;

import java.util.LinkedList;
import view.Display;

/**
 * La population du loft
 * @author Xenos
 *
 */
public class Population {

	/**
	 * La liste des lofteurs
	 */
	private LinkedList<Neuneu> lofteurs;
	private LinkedList<Neuneu> lofteursToRemove;

	/**
	 * Construit la population
	 * @param p_numberNeuneu	Nombre de neuneus dans le loft
	 * @param l					Le loft
	 */
	public Population(int p_numberNeuneu, Loft l){
		lofteurs = new LinkedList<Neuneu>();
		this.lofteursToRemove = new LinkedList<Neuneu>();


	}


	public void init(int p_numberNeuneu, Loft l){
		for(int i = 0; i< p_numberNeuneu; i++){

			switch(i%4){
			case 0:
				lofteurs.add(new Vorace());
				break;
			case 1:
				lofteurs.add(new Lapin());
				break;
			case 2:
				lofteurs.add(new Erratique());
				break;
			case 3:
				lofteurs.add(new Cannibal());
				break;
			default :
				break;
			}
		}
		for(Neuneu n : this.lofteurs){
			n.setCaseActuelle(l.randomCase());
		}
	}

	/**
	 * Vire un neuneu
	 * @param p_nigaud	Neuneu a virer
	 */
	public void removeNeuneu(Neuneu p_nigaud){
		this.lofteursToRemove.add(p_nigaud);
	}

	/**
	 * Ajoute un neuneu
	 * @param p_nigaud	Le neuneu a ajouter
	 */
	public void addNeuneu(Neuneu p_nigaud){
		lofteurs.add(p_nigaud);
	}

	/**
	 * Realise un tour
	 */
	public void Tour(){
		for(Neuneu n : this.lofteurs){
			if(n.getEnergie() == 1.0){
				System.out.println("enegie critique");
			}
			n.cycleDeVie();
		}
		
		// On fait le ménage, en différé pour éviter les problèmes d'appels simultanés à une même liste
		for(Neuneu n2 : this.lofteursToRemove){
			this.lofteurs.remove(n2);
		}
		this.lofteursToRemove = new LinkedList<Neuneu>();
		Display.getInstance().actualiser();
	}

	/**
	 * Renvoie les lofteurs
	 * @return	Lofteurs
	 */
	public LinkedList<Neuneu> getLofteurs() {
		return lofteurs;
	}
}
