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

	/**
	 * Construit la population
	 * @param p_numberNeuneu	Nombre de neuneus dans le loft
	 * @param l					Le loft
	 */
	public Population(int p_numberNeuneu, Loft l){
		lofteurs = new LinkedList<Neuneu>();

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
		this.lofteurs.remove(p_nigaud);
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
		/*
		 * Amélioration faite depuis le mail:
		 * suppression du "neuneu to remove"
		 * j'ai remis la fonction remove telle qu'elle était au départ
		 * Je copie tous les neuneus
		 * Puis ils font leur cycle de vie depuis cette liste.
		 * 
		 * J'ai notamment fait en sorte qu'on traite les différents neuneus dans un ordre aléatoire
		 */

		LinkedList<Neuneu> lofteursTreated = new LinkedList<Neuneu>();
		lofteursTreated.addAll(this.lofteurs);
		int i;
		Neuneu neuneuInTreatment;

		while(!lofteursTreated.isEmpty()){

			i =  ( (int) Math.random() )*lofteursTreated.size();
			neuneuInTreatment = lofteursTreated.get(i);	
			lofteursTreated.remove(neuneuInTreatment);
			
			if(this.lofteurs.contains(neuneuInTreatment) )//Vérification que le neuneu existe toujours, il a pu se faire manger par un autre
				neuneuInTreatment.cycleDeVie();
		}

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
