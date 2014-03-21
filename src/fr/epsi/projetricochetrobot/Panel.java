package fr.epsi.projetricochetrobot;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void drawImage(Graphics g, File f){
	    try {
	    	Image img = ImageIO.read(f);
	    	g.drawImage(img, 0, 0, this);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }                
	}
}
