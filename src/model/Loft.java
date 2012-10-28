package model;

import java.util.ArrayList;
/**
 * Le loft
 * @author Xenos
 *
 */
public class Loft {

	/**
	 * Les cases
	 */
	private Case[][] cases ;
	/**
	 * Largeur
	 */
	private int width;
	/**
	 * Hauteur
	 */
	private int height;

	/**
	 * Crée un loft
	 * @param p_width		Largeur
	 * @param p_height		Hauteur
	 */
	public Loft(int p_width, int p_height){
		cases = new Case[p_width][p_height];
		this.width = p_width;
		this.height = p_height;
		for(int i=0; i<this.width; i++){
			for(int j=0; j<this.height; j++){
				cases[i][j] = new Case(i,j);
			}
		}
	}

	/**
	 * Renvoie les voisins d'une case
	 * @param p_case	Case de base
	 * @return			Les voisins de la case
	 */
	public ArrayList<Case> getVoisins(Case p_case){
		ArrayList<Case> voisins = new ArrayList<Case>();

		for (int x=p_case.getX()-1;x<=p_case.getX()+1;x++)
			for (int y=p_case.getY()-1;y<=p_case.getY()+1;y++)
				if(isIn(x,y))
					voisins.add(cases[x][y]);
		return voisins;
	}

	/**
	 * 
	 * @param x		Position x
	 * @param y		Position y
	 * @return		Renvoie si la case x y est accessible.
	 */
	private boolean isIn(int x, int y){
		int width = cases.length;
		if(width == 0)
			return false;
		else{
			int height = cases[0].length;
			
			if(x>=0 && x< width && y>=0 && y<height)
				return true;
			else return false;
		}
	}
	
	/**
	 * Renvoie une case au pif dans le loft
	 * @return		Case au pif
	 * @todo		CODAGE
	 */
	public Case randomCase() {
		return (this.cases[(int)(Math.random()*this.width)][(int)(Math.random()*this.height)]);
	}
	
	/**
	 * Renvoie les cases
	 * @return	les cases
	 */
	public Case[][] getCases() {
		return (this.cases);
	}
	/**
	 * Renvoie la largeur
	 * @return	largeur
	 */
	public int getWidth() {
		return (this.width);
	}
	/**
	 * Renvoie la hauteur
	 * @return	hauteur
	 */
	public int getHeight() {
		return (this.height);
	}
}