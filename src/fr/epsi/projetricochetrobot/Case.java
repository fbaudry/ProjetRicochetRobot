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
	
	public boolean isStarter() {
		return starter;
	}

	public void setStarter(boolean starter) {
		this.starter = starter;
	}

	public void setTarget(boolean target) {
		this.target = target;
	}

	private boolean target;
	private boolean starter;
	
	public Case(int caseNumber, int x, int y, boolean top, boolean right, boolean bottom, boolean left, File file, boolean target, Graphics g) {
		super(g, x, y, file);
		this.walls[0] = top;
		this.walls[1] = right;
		this.walls[2] = bottom;
		this.walls[3] = left;
		super.drawImage();
	}
	
	
	//-----------------------Contructeur
	public Case(int caseNumber, Graphics g) {
		super(g);
		this.caseNumber = caseNumber;
		
		System.out.println(caseNumber);	
	}
	
	public boolean isTarget()
	{
		return target;
	}
	
	public int getCaseNumber()
	{
		return caseNumber;
	}
	
	public boolean getWall(int border)
	{
		if(border > 0 && border < 3)
		{
			return this.walls[border];
		}
		
		return true;
	}

}
