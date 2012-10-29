package model;

import java.util.ArrayList;

/**
 * @author Xenos
 * @brief		CLasse de neuneu (toi-m�me!)
 *
 */
public abstract class Neuneu extends Comestible {
	/**
	 * Le type de sexe du neuneu.
	 */
	private TypeSexe sexe;
	/**
	 * L'�nergie du Neuneu.
	 */
	private double energie;
	/**
	 * La quantit� max d'energie de ce neuneu.
	 */
	private double energieMax;
	/**
	 * Indique si le neuneu est enceinte; et donne le nombre de tours avant d'accoucher.
	 */
	private int enceinte;
	/**
	 * Dur�e de gestation chez les neuneus.
	 */
	private static int dureeEnceinte=9;
	/**
	 * Quantit� d'�nergie perdue par tour.
	 */
	private static double drainageParTour=1.0;
	
	private static double energieReproduction = 15.0;

	/**
	 * @brief	Renvoie l'�nergie du neuneu.
	 * @return	L'�nergie du neuneu.
	 */
	public double getEnergie() {
		return (this.energie);
	}
	/**
	 * @brief	G�re le tour complet
	 * @todo	Codage
	 */
	public void cycleDeVie() {
		this.deplacerTour();
		this.mangerTour();
		this.accoucherTour();
		this.reproduireTour();
		this.drainageTour();
	}
	/**
	 * @brief	Effectue le d�placement du tour.
	 * 		R�cup�re les cases accessibles, appel choixcase() et d�place si besoin.
	 * @todo	Codage
	 */
	protected void deplacerTour() {
		ArrayList<Case> voisins = Monde.getMonde().getLoft().getVoisins(this.getCaseActuelle());
		Population pop = Monde.getMonde().getPopulation();
		/*for (int i=voisins.size()-1;i>=0;i--) {
			Case destination = voisins.get(i);
			for (Neuneu occupe: pop.getLofteurs()) {
				if (this != occupe && occupe.getCaseActuelle() == destination) {
					voisins.remove(i);
					break;
				}
			}
		}*/
		voisins = this.ordonnerCases(voisins);
		if (voisins.size() > 0)
			this.setCaseActuelle(voisins.get(0));
	}

	/**
	 * @brief		A partir des cases accessibles, la m�thode d�termine l'ordre de pr�f�rence pour le neuneu.
	 * @param p_cases	Cases accessibles au neuneu.
	 * @return		Cases dans l'ordre de pr�f�rence
	 */
	protected abstract ArrayList<Case> ordonnerCases(ArrayList<Case> p_cases);

	/**
	 * @brief		Renvoie la quantit� de manger assimilable en un tour
	 * @return		La quantit� de nourriture mangeable par tour.
	 */
	public abstract double getNourritureParTour();

	/**
	 * @brief		Fait manger le neuneu par nourriture.manger(...)
	 */
	protected void mangerTour() {
		CorneAbondance corne = Monde.getMonde().getCorneAbondance();
		double qtte = this.getNourritureParTour();

		int i = 0;
		Nourriture manger;

		while(i < corne.getNourritureList().size() ){

			manger = corne.getNourritureList().get(i);

			if (this.getCaseActuelle() == manger.getCaseActuelle()
					&& this.peutManger(manger)	) {
				double coeff = Nourriture.getCoeff(manger.getType());
				double energie = manger.manger(this.energieMax-this.energie, qtte);
				this.energie += energie;
				qtte -= energie/coeff;

				if(manger.getQuantite() > 0.0) /*
				 *	On regarde si la nourriture existe encore
				 *	Si la quantit� est strictement sup�rieure � 0, cela signifie quelle existe encore
				 *	sinon elle a �t� d�truite et donc la liste contenant la nourriture a �t� modifi�
				 *	On en prend compte pour le d�filement de la nourriture
				 */
					i++;
			}
			else 
				i++;


			if (qtte <= 0)
				break;
		}
	}

	/**
	 * @brief		Reproduction avec le ou les neuneus pr�sents sur la case, qui peuvent alors repartir imm�diatement.
	 */
	private void reproduireTour() {
		Population pop = Monde.getMonde().getPopulation();
		for (Neuneu autre: pop.getLofteurs()) {
			if (
					autre != this && 
					this.getCaseActuelle() == autre.getCaseActuelle() &&
					this.sexe != autre.sexe &&
					this.energie > Neuneu.energieReproduction &&
					autre.getEnergie() > Neuneu.energieReproduction
					) {
				if (this.sexe != TypeSexe.Homme){
					if(this.enceinte == 0)
						this.enceinte = Neuneu.dureeEnceinte;
				}
				else{
					if (autre.enceinte == 0)
						autre.enceinte = Neuneu.dureeEnceinte;
				}
				this.energie -= Neuneu.energieReproduction;
				autre.setEnergie(autre.getEnergie() - Neuneu.energieReproduction);
			}
		}
	}

	/**
	 * Appel� par le cycle de vie
	 */
	private void accoucherTour() {
		try {
			if (this.enceinte > 1)
				this.enceinte--;
			else if (this.enceinte == 1) {
				this.enceinte = 0;
				Neuneu nigaud = this.getClass().newInstance();
				nigaud.setCaseActuelle(this.getCaseActuelle());
				Monde.getMonde().getPopulation().addNeuneu( nigaud );
			}
		}
		catch (Exception ex) {

		}
	}

	/**
	 * Renvoie true si on peut manger
	 * @param p_nourriture	L'objet nourriture � tester
	 * @return				true si on peut manger cet objet.
	 */
	private boolean peutManger(Nourriture p_nourriture) {
		return (this.peutManger(p_nourriture.getType()));
	}
	/**
	 * Renvoie true si on peut manger
	 * @param p_nourriture	Le type de nourriture
	 * @return				true si on peut manger ce type
	 */
	protected abstract boolean peutManger(TypeNourriture p_nourriture);

	/**
	 * Exclut le neuneu du loft (si son �nergie est nulle par exemple)
	 * Cette m�thode n'est pas appel�e directement par le cycle de vie.
	 */
	private void exclure() {
		Monde.getMonde().getPopulation().removeNeuneu(this);
	}

	/**
	 * Diminue l'�nergie au cours d'un tour, la constante de diminution d�pend de la race.
	 */
	private void drainageTour() {
		this.perdreEnergie(Neuneu.drainageParTour);
	}
	/**
	 * Diminue l'�nergie et exclus le neuneu si besoin
	 * @param p_perte	Nombre de points � perdre. 
	 */
	public void perdreEnergie(double p_perte) {
		if (this.energie <= p_perte) {
			this.exclure();
			return;
		}
		this.energie -= p_perte;
	}

	/**
	 * Le neuneu devient de la viande froide
	 */
	public void mourir() {
		Nourriture viandefroide = new Nourriture(
				this.getCaseActuelle(), 
				TypeNourriture.NeuneuMort,
				this.energie/Nourriture.getCoeff(TypeNourriture.NeuneuMort)
				);
		Monde.getMonde().getCorneAbondance().add(viandefroide);
		Monde.getMonde().getPopulation().removeNeuneu(this);
	}

	/**
	 * Construit un neuneu.
	 * @param p_case		Case de d�part.
	 * @param p_sexe		Le type de sexe du neuneu
	 * @param p_energmax	L'�nergie maximale de ce neuneu
	 */
	public Neuneu(Case p_case, TypeSexe p_sexe, double p_energmax) {
		super(p_case);
		this.sexe = p_sexe;
		this.energieMax = p_energmax;
		this.energie = this.energieMax;
		this.enceinte = 0;
	}
	/**
	 * Renvoie un sexe au pif
	 * @return	TypeSexe	Un sexe pour neuneu
	 */
	public static TypeSexe randomSexe() {
		double pc = Math.random()*100.0;
		if (pc > 55)
			return (TypeSexe.Femme);
		else if (pc > 10)
			return (TypeSexe.Homme);
		return (TypeSexe.Herma);
	}

	/**
	 * Ordonne les cases � partir des poids
	 * @param	p_cases	Les cases
	 * @param	p_poids	Les poids
	 * @return	La liste ordonn�e des cases
	 */
	public static ArrayList<Case> ordonner_poids(ArrayList<Double> p_poids, ArrayList<Case> p_cases) {
		ArrayList<Case> sortie = new ArrayList<Case>();
		for (int x=p_cases.size();x>0;--x) {
			double maxi = 0.;
			int j=0;
			for (int i=0;i<p_cases.size();++i) {
				if (maxi < p_poids.get(i)) {
					maxi = p_poids.get(i);
					j=i;
				}
			}
			sortie.add(p_cases.get(j));
			p_cases.remove(j);
		}
		return (sortie);
	}
	/**
	 * Renvoie le sexe du neuneu
	 * @return Le sexe du neuneu
	 */
	public TypeSexe getSexe() {
		return (this.sexe);
	}

	public Case nearestFood(){

		return this.nearestFood(false);

	}

	public Case nearestFood(boolean isCannibal){
		Monde m = Monde.getMonde();
		double squareDistance = Double.MAX_VALUE;
		Case nearestCase = null;

		ArrayList<Comestible> com = new ArrayList<Comestible>();

		for(Nourriture n : m.getCorneAbondance().getNourritureList()){
			if(this.peutManger(n))
				com.add(n);
		}

		if(isCannibal){
			for(Neuneu n2 : m.getPopulation().getLofteurs()){
				if(n2 != this)
					com.add(n2);
			}
		}
		/*	com.addAll(m.getPopulation().getLofteurs());

		com.remove(this);*/

		for(Comestible n : com){
			double localDistance = Math.pow(n.getCaseActuelle().getX() - this.getCaseActuelle().getX() , 2) 
					+ Math.pow(n.getCaseActuelle().getY() - this.getCaseActuelle().getY() , 2);
			if(localDistance< squareDistance){
				squareDistance = localDistance;
				nearestCase = m.getLoft().getCases()[n.getCaseActuelle().getX()][n.getCaseActuelle().getY()];
			}

		}

		return nearestCase;
	}
	public void setEnergie(double energie) {
		this.energie = energie;
	}
	
	
}
