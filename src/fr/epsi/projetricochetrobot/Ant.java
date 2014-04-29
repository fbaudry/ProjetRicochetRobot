package fr.epsi.projetricochetrobot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ant {
	private List<Case> path;
	private int nbMoveLeft = Constant.nbMove;
	
	private Case starter;
	private Case target;
	private Case[] cases;
	
	
	public Ant(Case[] cases, Case starter, Case target)
	{
		this.cases = cases;
		this.path = new ArrayList<Case>();
		this.path.add(starter);
		this.starter = starter;
		this.target = target;
	}
	
	public void move()
	{
		for(int numMove = 0; numMove < nbMoveLeft; numMove++){
			
			
			
		}
	}
	
	public List<Case> getVoisines(Case position)
	{
		List<Case> voisines = new ArrayList<Case>();
		
		int numCaseLeft = position.getCaseNumber() - 1;
		int numCaseRight = position.getCaseNumber() + 1;
		int numCaseTop = position.getCaseNumber() - 16;
		int numCaseBottom = position.getCaseNumber() + 16;
		
		
		// Si la case actuelle à une case à sa gauche
		// Si la case actuelle n'a pas de mur à sa gauche et que sa voisine de gauche n'a pas de mur à droite
		// Si la case de gauche n'a pas déjà été parcourue
		if(position.getCaseNumber()%16 != 0 && position.getWall(4) == false && cases[numCaseLeft].getWall(1) == false && isKnownCase(cases[numCaseLeft]) == false){
			voisines.add(cases[numCaseLeft]);
		}
		
		// Si la case actuelle à une case à sa droite
		// Si la case actuelle n'a pas de mur à sa droite et que sa voisine de droite n'a pas de mur à gauche
		// Si la case de droite n'a pas déjà été parcourue
		if(position.getCaseNumber()%16 != 15 && position.getWall(1) == false && cases[numCaseRight].getWall(4) == false && isKnownCase(cases[numCaseRight]) == false){
			voisines.add(cases[numCaseRight]);
		}
		
		// Si la case actuelle à une case en haut
		// Si la case actuelle n'a pas de mur en haut et que sa voisine du haut n'a pas de mur en bas
		// Si la case du haut n'a pas déjà été parcourue
		if(position.getCaseNumber() > 15 && position.getWall(0) == false && cases[numCaseTop].getWall(3) == false && isKnownCase(cases[numCaseTop]) == false){
			voisines.add(cases[numCaseTop]);
		}
		
		// Si la case actuelle à une case en bas
		// Si la case actuelle n'a pas de mur en bas et que sa vosiine du bas n'a pas de mur en haut
		// Si la case du bas n'a pas déjà été parcourue
		if(position.getCaseNumber() < 240 && position.getWall(3) == false && cases[numCaseBottom].getWall(0) == false && isKnownCase(cases[numCaseBottom]) == false){
			voisines.add(cases[numCaseBottom]);
		}
		
		
		return voisines;
	}
	
	public boolean isKnownCase(Case position){
		for(int i = 0; i < path.size(); i++){
			if(position == path.get(i))
				return true;
		}
		
		return false;
	}
	
	
	
	
}
