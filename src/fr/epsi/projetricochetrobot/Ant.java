package fr.epsi.projetricochetrobot;

import java.util.ArrayList;
import java.util.List;

public class Ant {
	private List<Case> path;
	private int nbMoveLeft = Constant.nbMove;
	
	private Case target;
	private Field field;
	
	
	
	public Ant(Case starter, Case target)
	{
		this.path = new ArrayList<Case>();
		this.path.add(starter);
		this.target = target;
		field = Field.getInstance();
	}
	
	public void move()
	{
		for(int numMove = 0; numMove < nbMoveLeft; numMove++){
						
			List<Case> voisines = field.getVoisines(this.getPosition(), path);
			
			if(voisines == null || voisines.size() == 0){
				numMove = nbMoveLeft;
			}else{
				
				int direction = field.getRandomDirection();
				Case position = this.getPosition();
				
				while(position != null){
					position = field.getCaseByDirection(direction, position);
					if(position != null){
						path.add(position);
					}else {
						Case selectedCase = field.selectVoisine(voisines);
						path.add(selectedCase);
						
						if(selectedCase == target){
							pheromonize();
							numMove = nbMoveLeft;
							field.incrNbFoundWay();
						}
					}
				}
			}
		}
	}
	
	public void pheromonize(){
		for(int i = 0; i < path.size(); i++){
			path.get(i).addPheromone();
		}
	}
	
	public Case getPosition(){
		return path.get(path.size()-1);
	}
}
