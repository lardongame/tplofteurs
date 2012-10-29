package model;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Les lapins
 * @author Xenos
 *
 */
public class Lapin extends Neuneu {
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
	public Lapin() {
		super(Monde.getMonde().getLoft().randomCase(), Neuneu.randomSexe(), Lapin.energieMaxMoy);
	}
	
	/**
	 * Renvoie la quantite de nourriture mangeable par tour
	 * @return nourriture mangeable à chaque tour
	 */
	public double getNourritureParTour() {
		return (Lapin.nourritureParTour);
	}
	/**
	 * Que put manger le neuneu?
	 * @param p_type L'objet à manger
	 * @return true ou false suivant si il peut manger ca
	 */
	public boolean peutManger(TypeNourriture p_type) {
		return (
				p_type == TypeNourriture.Boisson ||
				p_type == TypeNourriture.Legume
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
				if (n != this && n.getCaseActuelle() == c && n.getSexe() != this.getSexe())
					p += 1.0;
			}
			poids.add(i, p);
		}
		return (Neuneu.ordonner_poids(poids, p_cases));
	}
}
