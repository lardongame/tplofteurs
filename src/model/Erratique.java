package model;

import java.util.ArrayList;
import java.util.LinkedList;
/**
 * L'erratique, que se ballade au pif
 * @author Xenos
 *
 */
public class Erratique extends Neuneu {
	/**
	 * L'énergie max pour ce type de neuneu
	 */
	static double energieMaxMoy=100.0;
	/**
	 * La quantite de bouffe ingurgitable par tour
	 */
	static double nourritureParTour=10;
	
	/**
	 * Construit un neuneu et le place sur une case au pif
	 */
	public Erratique() {
		super(Monde.getMonde().getLoft().randomCase(), Neuneu.randomSexe(), Erratique.energieMaxMoy);
	}
	
	/**
	 * Renvoie la quantite de nourriture mangeable par tour
	 * @return nourriture mangeable à chaque tour
	 */
	public double getNourritureParTour() {
		return (Erratique.nourritureParTour);
	}
	/**
	 * Que put manger le neuneu?
	 * @param p_type L'objet à manger
	 * @return true ou false suivant si il peut manger ca
	 */
	public boolean peutManger(TypeNourriture p_type) {
		return (
				p_type == TypeNourriture.Boisson &&
				p_type == TypeNourriture.Legume &&
				p_type == TypeNourriture.Viande
				);
	}
	/**
	 * Ordonne les cases par préférence du neuneu.
	 * @param p_cases	Les cases de base
	 * @return			La liste des cases ordonnées
	 */
	protected ArrayList<Case> ordonnerCases(ArrayList<Case> p_cases) {
		ArrayList<Double> poids = new ArrayList<Double>();
		LinkedList<Neuneu> pop = Monde.getMonde().getPopulation().getLofteurs();
		
		for (int i=0;i<p_cases.size();++i)
		{
			double p = 0;
			Case c = p_cases.get(i);

			for (Neuneu n: pop) {
				if (n != this && n.getCaseActuelle() == c)
					p -= 2.0;
			}
			p += Math.random();
			poids.add(i, p);
		}
		return (Neuneu.ordonner_poids(poids, p_cases));
	}

}
