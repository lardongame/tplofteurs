package model;

import java.util.HashMap;

/**
 * @author Xenos
 *	La bouffe!
 */
public class Nourriture extends Comestible {
	/**
	 * Quantit� de nourriture de cet objet.
	 */
	private double quantite;
	/**
	 * Type de nourriture de cet objet.
	 */
	private TypeNourriture type;
	/**
	 * Coefficient d'�nergie (energie = coeff * qtte)
	 */
	private final static HashMap<TypeNourriture, Double> energieCoeff;
    static {
    	energieCoeff = new HashMap<TypeNourriture, Double>();
    	energieCoeff.put(TypeNourriture.Boisson,	1.0);
    	energieCoeff.put(TypeNourriture.Legume, 	1.0);
    	energieCoeff.put(TypeNourriture.NeuneuMort, 1.0);
    	energieCoeff.put(TypeNourriture.Viande, 	1.0);
	}
    /**
     * Renvoie le type de nourriture
     * @return	Le type de nourriture.
     */
    public TypeNourriture getType() {
    	return (this.type);
    }
    /**
     * La nourriture est mang�e, et d�truite dans la limite d'energie assimilable.
     * @return		L'�nergie gagn�e par ce mangeage
     * @param	p_energieMaxAssimilable		L'�nergioe maximale pouvant �tre r�cupr�e.
     * @param	p_quantiteMax				La quantit� maximale mangeable.
     */
    public double manger(double p_energieMaxAssimilable, double p_quantiteMax) {
    	double coeffenerg = Nourriture.energieCoeff.get(this.getType());
    	double vraieqttemax = Math.min( p_energieMaxAssimilable/coeffenerg, p_quantiteMax);
    		// La quantit� maximale � partir des deux entr�es
    	vraieqttemax = Math.min(vraieqttemax, this.quantite);
    		// On prend en compte la qtte dispo 
    	this.quantite -= vraieqttemax;
    	if (this.quantite <= 0)
    		Monde.getMonde().getCorneAbondance().remove(this);
    	return (vraieqttemax*coeffenerg);
    }
    
    /**
     * Cr�e un objet nourriture et l'ajoute � la liste du monde
     * @param p_case	La case o� se trouve le manger
     * @param p_type	Le type de manger
     * @param p_qtte	La quantite de manger
     */
    public Nourriture(Case p_case, TypeNourriture p_type, double p_qtte) {
    	super(p_case);
    	this.type = p_type;
    	this.quantite = p_qtte;
    	Monde.getMonde().getCorneAbondance().add(this);
    }
    
    /**
     * Renvoie le coeff d'un type de manger donn�
     * @param	p_type	Le type de manger
     * @return	Le coeff
     */
    public static double getCoeff(TypeNourriture p_type) {
    	return (Nourriture.energieCoeff.get(p_type));
    }
    /**
     * Renvoie un type de bouffe au hasard
     * @return Type de manger au hasard
	*/
    public static TypeNourriture getRandomType() {
    	double v = Math.random();
    	if (v < 0.3)
    		return (TypeNourriture.Viande);
    	else if (v < 0.6)
    		return (TypeNourriture.Legume);
    	return (TypeNourriture.Boisson);
    }
	public double getQuantite() {
		return quantite;
	}
    
    
}
