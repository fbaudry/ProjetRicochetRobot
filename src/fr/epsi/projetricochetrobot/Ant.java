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
			Case position = path.get(path.size()-1);
			List<Case> voisines = field.getVoisines(position);
			
			
		}
	}

}
