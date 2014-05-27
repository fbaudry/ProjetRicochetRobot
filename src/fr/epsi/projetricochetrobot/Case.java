package fr.epsi.projetricochetrobot;

import java.awt.Graphics;
import java.io.File;

public class Case{
	//-----------------------Variables
	private int caseNumber;
	
	//les panels
	private Panel floorPanel;	//le panel qui affiche le sol
	private Panel wallsPanel;	//le panel qui affiche les murs
	private Panel markerPanel;	//le panel qui affiche le par exemple le starter ou la target
	private Panel pheronomePanel;	//le panel qui affiche le niveau de pheromone
	
	private boolean[] walls = {false, false, false, false};	//permet de définir quel coté est un mur ----> 0->haut; 1->droite; 2->bas; 3->gauche
	private int pheronomeLevel = Constant.minPheromone;
	private boolean target;
	private boolean starter;
	
	
	//-----------------------Contructeurs
	public Case(int caseNumber, int x, int y, boolean top, boolean right, boolean bottom, boolean left, boolean target, Graphics g) {
		//on initialise les différents panels
		this.floorPanel = new Panel(g, x, y);
		this.floorPanel.setFile(new File("./img/floor.png"));
		this.floorPanel.drawImage();
		
		this.wallsPanel = new Panel(g, x, y);
		
		this.markerPanel = new Panel(g, x, y);
		
		this.pheronomePanel = new Panel(g, x, y);
		this.pheronomePanel.setFile(new File("./img/path.png"));
		
		this.caseNumber = caseNumber;
		this.walls[0] = top;
		this.walls[1] = right;
		this.walls[2] = bottom;
		this.walls[3] = left;
	}
	
	
	//-----------------------Methodes
	public void drawPath(){
		this.pheronomePanel.drawImage();
	}
	
	public void drawWalls(){
		this.wallsPanel.drawImage();
	}
	
	public void drawFloor(){
		this.floorPanel.drawImage();
	}
	
	public void drawMarker(){
		if(this.starter || this.target)
			this.markerPanel.drawImage();
	}
	
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
		this.markerPanel.setFile(new File("./img/start_layer.png"));
	}

	public void setTarget() {
		this.target = true;
		this.markerPanel.setFile(new File("./img/end_layer.png"));
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
			this.wallsPanel.setFile(new File("./img/floor_top_left.png"));
		}else if(this.walls[0]==true && this.walls[1]==false && this.walls[2]==false && this.walls[3]==false){
			this.wallsPanel.setFile(new File("./img/floor_top.png"));
		}else if(this.walls[0]==true && this.walls[1]==true && this.walls[2]==false && this.walls[3]==false){
			this.wallsPanel.setFile(new File("./img/floor_top_right.png"));
		}else if(this.walls[0]==false && this.walls[1]==false && this.walls[2]==false && this.walls[3]==true){
			this.wallsPanel.setFile(new File("./img/floor_left.png"));
		}else if(this.walls[0]==false && this.walls[1]==true && this.walls[2]==false && this.walls[3]==false){
			this.wallsPanel.setFile(new File("./img/floor_right.png"));
		}else if(this.walls[0]==false && this.walls[1]==true && this.walls[2]==true && this.walls[3]==false){
			this.wallsPanel.setFile(new File("./img/floor_right_bottom.png"));
		}else if(this.walls[0]==false && this.walls[1]==false && this.walls[2]==true && this.walls[3]==true){
			this.wallsPanel.setFile(new File("./img/floor_left_bottom.png"));
		}else if(this.walls[0]==false && this.walls[1]==false && this.walls[2]==true && this.walls[3]==false){
			this.wallsPanel.setFile(new File("./img/floor_bottom.png"));
		}else if(this.walls[0]==true && this.walls[1]==false && this.walls[2]==true && this.walls[3]==false){
			this.wallsPanel.setFile(new File("./img/floor_top_bottom.png"));
		}else if(this.walls[0]==false && this.walls[1]==true && this.walls[2]==false && this.walls[3]==true){
			this.wallsPanel.setFile(new File("./img/floor_right_left.png"));
		}
	}
}
