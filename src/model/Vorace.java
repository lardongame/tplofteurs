package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Vorace extends Neuneu {

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
	public Vorace() {
		super(Monde.getMonde().getLoft().randomCase(), Neuneu.randomSexe(), Vorace.energieMaxMoy);
	}


	protected boolean peutManger(TypeNourriture p_type) {
		return (
				p_type == TypeNourriture.Boisson ||
				p_type == TypeNourriture.Legume ||
				p_type == TypeNourriture.Viande
				);
	}


	protected ArrayList<Case> ordonnerCases(ArrayList<Case> p_cases) {
		ArrayList<Double> poids = new ArrayList<Double>();
		LinkedList<Neuneu> pop = Monde.getMonde().getPopulation().getLofteurs();

		Case nearestFood = this.nearestFood();

		int xVector = 0;
		int yVector = 0; 

		if(nearestFood != null){
			xVector = nearestFood.getX() - this.getCaseActuelle().getX();
			yVector = nearestFood.getY() - this.getCaseActuelle().getY();
		}

		int x2Vector;
		int y2Vector;

		for (int i = 0; i< p_cases.size(); i++)
		{
			x2Vector = p_cases.get(i).getX() - this.getCaseActuelle().getX();
			y2Vector = p_cases.get(i).getY() - this.getCaseActuelle().getY();


			double p = 1.0;
			Case c = p_cases.get(i);

			for (Neuneu n: pop) {
				if (n != this && n.getCaseActuelle() == c)
					p -= 2.0;
			}
			if(x2Vector == 0 && y2Vector == 0){
				if(xVector == 0 && yVector == 0)
					poids.add(i , Double.MAX_VALUE);
				else
					poids.add(i, 0.0);
			}
			else
				poids.add(i , p*(xVector*x2Vector + yVector*y2Vector)/ Math.sqrt(x2Vector*x2Vector + y2Vector*y2Vector));
		}
		return (Neuneu.ordonner_poids(poids, p_cases));
	}


	public double getNourritureParTour() {
		// TODO Auto-generated method stub
		return Vorace.nourritureParTour;
	}




}
