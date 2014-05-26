package fr.epsi.projetricochetrobot;

import java.util.ArrayList;
import java.util.List;

public class Ant {
	private List<Case> path;
	private int nbMoveLeft = Constant.nbMove;
	
	private Case target;
	private Field field;
	
	private Case position;
	
	
	public Ant(Case starter, Case target)
	{
		this.path = new ArrayList<Case>();
		this.path.add(starter);
		this.target = target;
		field = Field.getInstance();
		position = starter;
	}
	
	public void move()
	{
		for(int numMove = 0; numMove < nbMoveLeft; numMove++){
						
			List<Case> voisines = field.getVoisines(position, path);
			
			if(voisines == null || voisines.size() == 0){
				numMove = nbMoveLeft;
			}else{
				//System.out.println("");
				
				Case selectedCase = field.selectVoisine(voisines);
				path.add(selectedCase);
				
				position = selectedCase;
				
				if(selectedCase == target){
					pheromonize();
					numMove = nbMoveLeft;
					field.incrNbFoundWay();
				}
			}
		}
	}
	
	public void pheromonize(){
		for(int i = 0; i < path.size(); i++){
			path.get(i).addPheromone();
		}
	}
}
