package fr.epsi.projetricochetrobot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ant {
	private List<Case> path;
	private int nbMoveLeft = Constant.nbMove;
	
	private Case starter;
	private Case target;
	private Field field;
	
	
	public Ant(Case starter, Case target)
	{
		this.path = new ArrayList<Case>();
		this.path.add(starter);
		this.starter = starter;
		this.target = target;
		field = Field.getInstance();
	}
	
	public void move()
	{
		for(int numMove = 0; numMove < nbMoveLeft; numMove++){
			Case position = null;
			
			if(path.size() > 0)
				position = path.get(path.size()-1);
			else
				position = starter;
			
			
			
			List<Case> voisines = field.getVoisines(position, path);
			
			Case selectedCase = field.selectVoisine(voisines);
			path.add(selectedCase);
			
			if(selectedCase == target){
				pheromonize();
				numMove = nbMoveLeft;
				field.incrNbFoundWay();
			}
			
		}
	}
	
	public void pheromonize(){
		for(int i = 0; i < path.size(); i++){
			path.get(i).addPheromone();
		}
	}
}
