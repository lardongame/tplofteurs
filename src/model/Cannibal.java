package model;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Xenos
 *	Mange les autres
 */
public class Cannibal extends Neuneu {
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
	public Cannibal() {
		super(Monde.getMonde().getLoft().randomCase(), Neuneu.randomSexe(), Cannibal.energieMaxMoy);
	}

	/**
	 * Renvoie la quantite de nourriture mangeable par tour
	 * @return nourriture mangeable à chaque tour
	 */
	public double getNourritureParTour() {
		return (Cannibal.nourritureParTour);
	}
	/**
	 * Que put manger le neuneu?
	 * @param p_type L'objet à manger
	 * @return true ou false suivant si il peut manger ca
	 */
	public boolean peutManger(TypeNourriture p_type) {
		return (
				p_type == TypeNourriture.Boisson ||
				p_type == TypeNourriture.Legume ||
				p_type == TypeNourriture.NeuneuMort ||
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
		/*LinkedList<Neuneu> pop = Monde.getMonde().getPopulation().getLofteurs();
		LinkedList<Nourriture> corne = Monde.getMonde().getCorneAbondance().getNourritureList();*/
		Case bouffeproche = this.nearestFood(true); 

		for (int i=0;i<p_cases.size();++i)
		{
			double p = 1.0;
			Case c = p_cases.get(i);
			double ps = 
					(bouffeproche.getX()-this.getCaseActuelle().getX())*
					(c.getX()-this.getCaseActuelle().getX())
					+
					(bouffeproche.getY()-this.getCaseActuelle().getY())*
					(c.getY()-this.getCaseActuelle().getY());
			//Utilité? Déjà pris en compte par nearestFood		
			/*for (Neuneu n: pop) {
				if (n != this && n.getCaseActuelle() == c)
					p += 1.0;
			}
			for (Nourriture n: corne) {
				if (n.getCaseActuelle() == c && this.peutManger(n.getType()))
					p += 1.0;
			}*/
			poids.add(i, p*ps);
		}
		return (Neuneu.ordonner_poids(poids, p_cases));
	}

	/*/**
	 * @brief	Effectue le déplacement du tour.
	 * 		Récupère les cases accessibles, appel choixcase() et déplace si besoin.
	 * @todo	Codage
	 */
	//@Override
	/*protected void deplacerTour() {
		ArrayList<Case> voisins = Monde.getMonde().getLoft().getVoisins(this.getCaseActuelle());
		Population pop = Monde.getMonde().getPopulation();
		for (int i=voisins.size()-1;i>=0;i--) {
			Case destination = voisins.get(i);			
		}
		voisins = this.ordonnerCases(voisins);
		if (voisins.size() > 0)
			this.setCaseActuelle(voisins.get(0));
	}*/

	/**
	 * @brief		Fait manger le neuneu par nourriture.manger(...)
	 */
	@Override
	protected void mangerTour() {
		Population pop = Monde.getMonde().getPopulation();
		double qtte = this.getNourritureParTour();

		int i = 0;
		Neuneu manger;

		while(i < pop.getLofteurs().size() ){

			manger = pop.getLofteurs().get(i);

			if(manger == this)
				i++;
			else{
				if (this.getCaseActuelle() == manger.getCaseActuelle()	) {
					
					new Nourriture(this.getCaseActuelle(), TypeNourriture.NeuneuMort, manger.getEnergie());
					
					manger.mourir();
					
					System.out.println("Bouffé !!!!");

					break;
				}
				else 
					i++;
			}
			
			if (qtte <= 0)
				break;
		}
		
		super.mangerTour();
	}
}
