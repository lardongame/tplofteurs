package view;

import java.awt.Color;

import javax.swing.JFrame;

import model.Loft;
import model.Monde;

public class Display extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3031592359043728228L;


	private static Display instance ;
	private GMonde graphicMonde;
	
	private Monde m = Monde.getMonde();

	private Display(){
		if(instance != null)
			System.out.println("A sngleton can be instantiate only one time!!!");
		else{


			graphicMonde = new GMonde();

			//Définit un titre pour votre fenêtre
			this.setTitle("Neuneu Story");
			
			Loft l = m.getLoft();
			this.setSize(l.getWidth()*GMonde.caseSize+16, l.getHeight()*GMonde.caseSize+38);
			//Nous allons maintenant dire à notre objet de se positionner au centre
			this.setLocationRelativeTo(null);
			//Terminer le processus lorsqu'on clique sur "Fermer"
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			this.setBackground(Color.cyan);
			//Définition de sa couleur de fond

			this.setContentPane( graphicMonde);
			this.setVisible(true);

			this.repaint();
		}
	}

	public static Display getInstance(){
		if(instance == null)
			instance = new Display();

		return instance;
	}

	public void actualiser(){
		graphicMonde.repaint();	
	}
}
