package fr.epsi.projetricochetrobot;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author FERD
 *
 */
public class Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File file;
	private int x, y;
	private Graphics g;
	
	//-------------------------contructeur
	public Panel(Graphics g, int x, int y) {
		super();
		this.g = g;
		this.x = x;
		this.y = y;
		this.file = new File("./img/floor.png");
	}
	
	public Panel(Graphics g) {
		super();
		this.g = g;
	}

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void drawImage(){
	    try {
	    	Image img = ImageIO.read(file);
	    	g.drawImage(img, this.x, this.y, this);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }                
	}

	public Graphics getGraphics() {
		return g;
	}
}
