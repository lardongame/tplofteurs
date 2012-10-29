package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.CorneAbondance;
import model.Loft;
import model.Monde;
import model.Neuneu;
import model.Nourriture;

public class GMonde extends JPanel {

	public final static int caseSize = 10;

	private final static Color erratiqueColor = Color.blue;
	private final static Color voraceColor = Color.green;
	private final static Color cannibalColor = Color.yellow;
	private final static Color lapinColor = Color.red;

	private final static Color mortColor = Color.orange;
	private final static Color nourritureColor = Color.pink;
	
	private Monde monde  = Monde.getMonde();
	
	public GMonde(){
		monde  = Monde.getMonde();
	}
	
	public void paintComponent(Graphics g){
		this.createMonde(g, monde);
		this.createFood(g, monde);
		this.createPopulation(g, monde);
		
	}

	private void paintCase(Graphics g, Color c, int x, int y){
		g.setColor(c);
		g.fillRect(x*caseSize, y*caseSize, caseSize, caseSize);
	}

	private void createMonde(Graphics g,Monde m){
		Loft l = m.getLoft();
		
		int width = l.getCases().length;
		int height = l.getCases()[0].length;

		for(int i=0; i<width; i++){
			for(int j = 0 ; j<height; j++){
				this.paintCase(g, Color.gray, i, j);
			}
		}
	}
	
	private void createPopulation(Graphics g,Monde m){
		for(Neuneu n : m.getPopulation().getLofteurs())
			this.createNeuneu(g, n);
	}
	
	public void createFood(Graphics g,Monde m){
		CorneAbondance c = m.getCorneAbondance();
		
		for(Nourriture n : c.getNourritureList()){
			this.paintCase(g, nourritureColor, n.getCaseActuelle().getX(), n.getCaseActuelle().getY());
		}
	}
	
	private void createNeuneu(Graphics g, Neuneu n){
		Color c = this.getNeuneuColor(n);
		this.paintCase(g, c, n.getCaseActuelle().getX(), n.getCaseActuelle().getY() );
	}

	private Color getNeuneuColor(Neuneu n){
		String neuneuName = n.getClass().getName();

		Color c ;

		switch(neuneuName){
		case "model.Cannibal" :
			c=  cannibalColor;
			break;
		case "model.Vorace" :
			c=  voraceColor;
			break;
		case "model.Lapin" :
			c=  lapinColor;
			break;
		case "model.Erratique" :
			c=  erratiqueColor;
			break;
		default :
			c=  erratiqueColor;
			break;

		}

		return c;
	}

	public void setMonde(Monde monde) {
		this.monde = monde;
	}
	
	
}
