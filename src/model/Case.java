package model;
/**
 * Case du loft
 * @author Xenos
 *
 */
public class Case {

	/**
	 * position abscisse
	 */
	private int x;
	/**
	 * position ordonn�e
	 */
	private int y;
	
	/**
	 * Construit une case � la position sp�cifi�e
	 * @param p_x	position X
	 * @param p_y	position Y
	 */
	public Case(int p_x, int p_y){
		x = p_x;
		y = p_y;
	}

	/**
	 * Renvoie l'abscisse
	 * @return Abscisse
	 */
	public int getX() {
		return x;
	}
	/**
	 * Renvoie l'ordonn�e
	 * @return ordonn�e
	 */
	public int getY() {
		return y;
	}
	
	
}
