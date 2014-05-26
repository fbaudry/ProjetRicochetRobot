package fr.epsi.projetricochetrobot;

import java.awt.Graphics;
import java.io.File;

public class Case extends Panel{

	private static final long serialVersionUID = 1L;
	
	//-----------------------Variables
	private int caseNumber;
	private boolean[] walls = {false, false, false, false};	//permet de définir quel coté est un mur ----> 0->haut; 1->droite; 2->bas; 3->gauche
	private int pheronomeLevel = Constant.minPheromone;
	
	public int getPheronomeLevel() {
		return pheronomeLevel;
	}

	public void addPheromone(){
		if(pheronomeLevel + Constant.tauxPheromone > Constant.maxPheromone)
			pheronomeLevel = Constant.maxPheromone;
		else
			pheronomeLevel += Constant.tauxPheromone;
	}
	
	public void removePheromone(){
		if(pheronomeLevel - Constant.tauxPheromone < Constant.minPheromone)
			pheronomeLevel = Constant.minPheromone;
		else
			pheronomeLevel -= Constant.tauxPheromone;
	}
	
	public void setPheronomeLevel(int pheronomeLevel) {
		this.pheronomeLevel = pheronomeLevel;
	}

	public void setStarter() {
		this.starter = true;
		Panel starterLayer = new Panel(super.getGraphics(), super.getX(), super.getY());
		starterLayer.setFile(new File("./img/start_layer.png"));
		starterLayer.drawImage();
	}

	public void setTarget() {
		this.target = true;
		Panel starterLayer = new Panel(super.getGraphics(), super.getX(), super.getY());
		starterLayer.setFile(new File("./img/end_layer.png"));
		starterLayer.drawImage();
	}

	private boolean target;
	private boolean starter;
	
	
	
	public Case(int caseNumber, int x, int y, boolean top, boolean right, boolean bottom, boolean left, boolean target, Graphics g) {
		super(g, x, y);
		this.caseNumber = caseNumber;
		this.walls[0] = top;
		this.walls[1] = right;
		this.walls[2] = bottom;
		this.walls[3] = left;
		this.setFile(new File("./img/floor.png"));
		this.drawImage();
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
	
	//0->haut; 1->droite; 2->bas; 3->gauche
	public void setWallTop() {
		this.walls[0]=true;
	}
	
	//0->haut; 1->droite; 2->bas; 3->gauche
	public void setWallRight() {
		this.walls[1]=true;
	}
	
	//0->haut; 1->droite; 2->bas; 3->gauche
	public void setWallBottom() {
		this.walls[2]=true;
	}
	
	//0->haut; 1->droite; 2->bas; 3->gauche
	public void setWallLeft() {
		this.walls[3]=true;
	}

	public boolean getWall(int border)
	{
		if(border >= 0 && border <= 3)
		{
			return this.walls[border];
		}
		
		return true;
	}

	public void setFile(){
		if(this.walls[0]==true && this.walls[1]==false && this.walls[2]==false && this.walls[3]==true){
			super.setFile(new File("./img/floor_top_left.png"));
		}else if(this.walls[0]==true && this.walls[1]==false && this.walls[2]==false && this.walls[3]==false){
			super.setFile(new File("./img/floor_top.png"));
		}else if(this.walls[0]==true && this.walls[1]==true && this.walls[2]==false && this.walls[3]==false){
			super.setFile(new File("./img/floor_top_right.png"));
		}else if(this.walls[0]==false && this.walls[1]==false && this.walls[2]==false && this.walls[3]==true){
			super.setFile(new File("./img/floor_left.png"));
		}else if(this.walls[0]==false && this.walls[1]==true && this.walls[2]==false && this.walls[3]==false){
			super.setFile(new File("./img/floor_right.png"));
		}else if(this.walls[0]==false && this.walls[1]==true && this.walls[2]==true && this.walls[3]==false){
			super.setFile(new File("./img/floor_right_bottom.png"));
		}else if(this.walls[0]==false && this.walls[1]==false && this.walls[2]==true && this.walls[3]==true){
			super.setFile(new File("./img/floor_left_bottom.png"));
		}else if(this.walls[0]==false && this.walls[1]==false && this.walls[2]==true && this.walls[3]==false){
			super.setFile(new File("./img/floor_bottom.png"));
		}else if(this.walls[0]==true && this.walls[1]==false && this.walls[2]==true && this.walls[3]==false){
			super.setFile(new File("./img/floor_top_bottom.png"));
		}else if(this.walls[0]==false && this.walls[1]==true && this.walls[2]==false && this.walls[3]==true){
			super.setFile(new File("./img/floor_right_left.png"));
		}
	}
}
