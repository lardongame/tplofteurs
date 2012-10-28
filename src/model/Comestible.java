package model;
/**
 * @author Xenos
 * @brief Les trucs comestibls, incluant les neuneus.
 * 
 */
public abstract class Comestible {
	/**
	 * La case actuelle où se situe l'objet comestible.
	 */
	private Case caseActuelle;
	
	/**
	 * Renvoie la case où se situe l'objet.
	 * @return	Case de l'objet
	 */
	public Case getCaseActuelle() {
		return (this.caseActuelle);
	}
	
	/**
	 * Définit la case où se situe l'objet
	 * @warn		La méthode ne vérifie pas que cette case est bien une case du monde.
	 * @param p_case Case où se truve l'objet.
	 */
	public void setCaseActuelle(Case p_case) {
		this.caseActuelle = p_case;
	}
	
	/**
	 * Constructeur
	 * @param	p_case	La case de l'objet.
	 */
	protected Comestible(Case p_case) {
		this.setCaseActuelle(p_case);
	}
}
