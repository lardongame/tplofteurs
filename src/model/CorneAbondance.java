package model;

import java.util.LinkedList;
/**
 * La corne d'abondance
 * Cela représente la "population" de nourriture, aka, l'ensemble de nourriture dispo dans le loft.
 * @author Xenos
 *
 */
public class CorneAbondance {

	/**
	 * La nourriture dispo sur le loft
	 */
	private LinkedList<Nourriture> nourritureList;
	
	/**
	 * Construit une corne d'abondance avec n objets de nourriture dedans
	 * @param n	Nombre d'objets de ourriture à mettre dans la corne
	 */
	public CorneAbondance(int n){
		nourritureList = new LinkedList<Nourriture>();
	}
	
	public void init(int n){
		Loft loft = Monde.getMonde().getLoft();
		
		for(int i = 0; i< n; i++){
			nourritureList.add( new Nourriture(loft.randomCase(), Nourriture.getRandomType(),(int) Math.random() * 100)); // FIX ME
		}
	}
	
	/**
	 * Vire de la nourriture de la corne
	 * @param nou	Nourriture à supprimer
	 */
	public void remove(Nourriture nou){
		nourritureList.remove(nou);
	}
	
	/**
	 * Ajoute de la nourriture dans la corne
	 * @param nou	Nourriture à ajouter
	 */
	public void add(Nourriture nou){
		nourritureList.add(nou);
	}

	/**
	 * Renvoie la liste 
	 * @return	La liste de nourriture
	 */
	public LinkedList<Nourriture> getNourritureList() {
		return nourritureList;
	}
}
