package fr.epsi.projetricochetrobot;

import java.awt.Graphics;
import java.io.File;

public class Case extends Panel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//-----------------------Variables
	private int caseNumber;
	private boolean[] walls = {false, false, false, false};	//permet de définir quel coté est un mur ----> 0->haut; 1->droite; 2->bas; 3->gauche
	private int pheronomeLevel = 0;
	private boolean target;
	
	//-----------------------Contructeur
	public Case(int caseNumber, boolean target, Graphics g) {
		super(g);
		this.caseNumber = caseNumber;
		this.target = target;
		
		System.out.println(caseNumber);
		
		// Case du contour avec les bordures 
		if(caseNumber==0){
			//si c'est la case de l'angle en haut à gauche
			this.walls[0] = true;
			this.walls[3] = true;
			//on lui attribut une image
			super.setFile(new File("./img/floor_top_left.png"));
			//on definit sa position
			super.setX(0);
			super.setY(22);
			//on affiche l'image
			super.drawImage();
		}else if(caseNumber>=1 && caseNumber<=14){
			//si c'est un case du haut
			this.walls[0] = true;
			super.setFile(new File("./img/floor_top.png"));
			//on definit sa position
			super.setX(caseNumber*32);
			super.setY(22);
			//on affiche l'image
			super.drawImage();
		}else if(caseNumber==15){
			//si c'est la case de haut à droite
			this.walls[0] = true;
			this.walls[1] = true;
			super.setFile(new File("./img/floor_top_right.png"));
			//on definit sa position
			super.setX(480);
			super.setY(22);
			//on affiche l'image
			super.drawImage();
		}else if(caseNumber%16>0 && caseNumber%16<15){
			//si c'est un case de gauche
			this.walls[3] = true;
			super.setFile(new File("./img/floor_left.png"));
			//on definit sa position
			super.setX(0);
			super.setY(22+((caseNumber%16)*32));
			//on affiche l'image
			super.drawImage();
		}else if(caseNumber==240){
			//si c'est la case d'en bas à gauche
			this.walls[2] = true;
			this.walls[3] = true;
			super.setFile(new File("./img/floor_left_bottom.png"));
			//on definit sa position
			super.setX(0);
			super.setY(22+480);
			//on affiche l'image
			super.drawImage();
		}else if(caseNumber==244){
			//si c'est la case d'en bas à gauche
			this.walls[2] = true;
			this.walls[1] = true;
			super.setFile(new File("./img/floor_right_bottom.png"));
			//on definit sa position
			super.setX(480);
			super.setY(22+480);
			//on affiche l'image
			super.drawImage();
			System.out.println("test");
		}
		
		// Les 4 cases du centre
		if(caseNumber == 119){
			//haut et gauche
			this.walls[0] = true;
			this.walls[3] = true;
		}else if(caseNumber == 120){
			//haut et droite
			this.walls[0] = true;
			this.walls[1] = true;
		}else if(caseNumber == 136){
			//bah et droite
			this.walls[2] = true;
			this.walls[1] = true;
		}else if(caseNumber == 137){
			//bas et gauche
			this.walls[2] = true;
			this.walls[3] = true;
		}
	}
}
