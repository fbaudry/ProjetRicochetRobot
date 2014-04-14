package fr.epsi.projetricochetrobot;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		// création de l'application
		JFrame f = new JFrame();
		// affectation du titre et de l'icône
		f.setTitle("le titre");
		//f.setIconImage(Toolkit.getDefaultToolkit().getImage(Appli0.class.getResource("/icone.gif")));
		// affectation de l'opération à effectuer lors de la fermeture de la fenêtre
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// taille et position
		f.setPreferredSize(new Dimension(600, 600));
		f.setLocation(100, 0); // la fenêtre est en 100, 100
		f.setLocationRelativeTo(null); // la fenêtre est centrée à l'écran
		// rendre la fenêtre visible, pack fait en sorte que tous les composants de l'application soient à
		// leur preferredSize, ou au dessus
		f.pack();
		f.setVisible(true);
		
	}
}
