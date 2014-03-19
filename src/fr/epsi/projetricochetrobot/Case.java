package fr.epsi.projetricochetrobot;

public class Case {
	
	//-----------------------Variables
	private int caseNumber;
	private boolean[] walls;	//permet de définir quel coté est un mur ----> 0->haut; 1->droite; 2->bas; 3->gauche
	private int pheronomeLevel = 0;
	private boolean target;
	
	
	//-----------------------Contructeur
	public Case(int caseNumber, boolean target) {
		super();
		this.caseNumber = caseNumber;
		this.target = target;
		
		// Case du contour avec les bordures 
		if(caseNumber>=0 && caseNumber<=15){
			//si c'est un case du haut
			this.walls[0] = true;
		}else if(caseNumber%16==0){
			//si c'est un case de gauche
			this.walls[1] = true;
		}else if(caseNumber%16-1==0){
			//si c'est un case de droite
			this.walls[2] = true;
		}else if(caseNumber>=239 && caseNumber<=255){
			//si c'est un case du bas
			this.walls[3] = true;
		}
		
		// Les 4 cases du centre
		if(caseNumber == 119){
			this.walls[0] = true;
			this.walls[3] = true;
		}else if(caseNumber == 120){
			this.walls[0] = true;
			this.walls[1] = true;
		}else if(caseNumber == 136){
			this.walls[3] = true;
			this.walls[4] = true;
		}else if(caseNumber == 137){
			this.walls[2] = true;
			this.walls[3] = true;
		}
			
		
	}
	
	
}
