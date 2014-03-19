package fr.epsi.projetricochetrobot;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Frame() {
		// affectation du titre et de l'icône
		this.setTitle("Ricochet Robot");
		//this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Appli0.class.getResource("/icone.gif")));
	    // affectation de l'opération à effectuer lors de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    // taille et position
		this.setPreferredSize(new Dimension(600, 600));
		this.setLocation(0, 0); // la fenêtre est en 100, 100
		this.setLocationRelativeTo(null); // la fenêtre est centrée à l'écran
	    // rendre la fenêtre visible, pack fait en sorte que tous les composants de l'application soient à
	    // leur preferredSize, ou au dessus
	    this.pack();
	    
	    //Instanciation d'un objet JPanel
	    JPanel pan = new JPanel();
	    //Définition de sa couleur de fond
	    pan.setBackground(Color.gray);        
	    //On prévient notre JFrame que notre JPanel sera son content pane
	    this.setContentPane(pan);
	    
	    this.setVisible(true);
	}
}
