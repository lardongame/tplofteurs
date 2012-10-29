package model;
/**
 * Le monde des lofteurs
 * @author Xenos
 *
 */
public class Monde {

	/**
	 * L'instance (modèle singleton)
	 */
	private static Monde instance ;
	
	/**
	 * Le loft
	 */
	private Loft loft;
	/**
	 * La population (ensemble des lofteurs)
	 */
	private Population population ;
	/**
	 * La cordne d'abondance, ensemble de la nourriture disponible dans le loft
	 */
	private CorneAbondance corneAbondance;
	
	/**
	 * Constructeur privé (singleton)
	 */
	private Monde(){
		
	}
	
	/**
	 * Renvoie l'instance du monde
	 * @return	L'instance du monde
	 */
	public static Monde getMonde(){
		if(instance == null)
			instance = new Monde();
		
		return instance;
	}
	
	/**
	 * Initialise le monde
	 * @param width		Largeur
	 * @param height	Hauteur
	 * @param numberNeuneus	Nombre de neuneu
	 */
	public void init(int width, int height, int numberNeuneus, int numberFood){
		this.loft = new Loft(width, height);
		this.population = new Population(numberNeuneus, this.loft);
		this.population.init(numberNeuneus, this.loft);
		this.corneAbondance = new CorneAbondance();
		this.corneAbondance.init(numberFood);
	}

	/**
	 * Renvoie le loft
	 * @return Le loft
	 */
	public Loft getLoft() {
		return loft;
	}

	/**
	 * Renvoie la population
	 * @return population
	 */
	public Population getPopulation() {
		return population;
	}

	/**
	 * Renvoie la corne d'abondance (merci Yohann, ce nom est tout simplement magique!)
	 * @return	La corne d'abondance
	 */
	public CorneAbondance getCorneAbondance() {
		return corneAbondance;
	}
}
