package fr.epsi.projetricochetrobot;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Window() throws HeadlessException {
		// affectation du titre et de l'icône
		this.setTitle("Ricochet Robot");
		//this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Appli0.class.getResource("/icone.gif")));
	    // affectation de l'opération à effectuer lors de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    // taille et position
		this.setPreferredSize(new Dimension(512, 512));
		this.setUndecorated(true);

		
		this.setLocation(100, 100);
		
		//this.setLocationRelativeTo(null); // la fenêtre est centrée à l'écran
	    // rendre la fenêtre visible, pack fait en sorte que tous les composants de l'application soient à
	    // leur preferredSize, ou au dessus
	    this.pack();
	    this.setVisible(true);
	}
}
