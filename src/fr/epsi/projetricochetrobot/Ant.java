package fr.epsi.projetricochetrobot;

import java.io.File;
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
		/*for(int numMove = 0; numMove < nbMoveLeft; numMove++){
						
			List<Case> voisines = field.getVoisines(this.getPosition(), path);
			
			if(voisines == null || voisines.size() == 0){
				numMove = nbMoveLeft;
			}else{
				
				int direction = field.getRandomDirection();
				Case position = this.getPosition();
				
				while(position != null){
					//position = field.getCaseByDirection(direction, position);
					position= field.runStraight(direction, position);
					if(position != null){
						path.add(position);
						position.setFile(new File("./img/path.png"));
						position.drawImage();
					}else {
						voisines = field.getVoisines(this.getPosition(), path);
						Case selectedCase = field.selectVoisine(voisines);
						path.add(selectedCase);
						selectedCase.setFile(new File("./img/path.png"));
						selectedCase.drawImage();
						
						if(selectedCase == target){
							pheromonize();
							numMove = nbMoveLeft;
							field.incrNbFoundWay();
						}
						selectedCase = null;
					}
				}
			}
		}*/
		int direction = -1;
		
		for(int i = 0; i < nbMoveLeft; i++){
			direction = this.getAnotherDirection(this.getPosition());
			
			if(direction != -1){
				System.out.println("Nouvelle direction:" + direction);
				System.out.println("--------------");
				
				boolean bool = true;
				while(bool){
					Case newCase = field.runStraight(direction, this.getPosition());
					
					if(newCase != null){
						path.add(newCase);
						newCase.setFile(new File("./img/path.png"));
						newCase.drawImage();
					}else {
						bool = false;
					}
					
					if(newCase == target){
						i = nbMoveLeft;
						field.incrNbFoundWay();
					}
				}
			}else {
				i = nbMoveLeft;
			}
			
		}
	}
	
	public int getAnotherDirection(Case position){
		
		List<Integer> voisines = new ArrayList<Integer>();
		
		if((position.getCaseNumber()-16) > 0){
			if(!path.contains(field.casefield[position.getCaseNumber()-16])){
				voisines.add(0);
			}
			
		}
		
		if((position.getCaseNumber()%16) != 15){
			if(!path.contains(field.casefield[position.getCaseNumber()+1])){
				voisines.add(1);
			}
		}
		
		if((position.getCaseNumber()+16) < 256){
			if(!path.contains(field.casefield[position.getCaseNumber()+16])){
				voisines.add(2);
			}
		}
		
		if((position.getCaseNumber())%16 != 0){
			if(!path.contains(field.casefield[position.getCaseNumber()-1])){
				voisines.add(3);
			}
		}
		
		int direction = -1;
		
		direction = field.getRandomDirection();
			
		return direction;
	
	}
	
	
	
	
	
	
	
	
	
	/*-------------------------------------------------------------*/
	
	
	
	
	
	public void pheromonize(){
		for(int i = 0; i < path.size(); i++){
			path.get(i).addPheromone();
		}
	}
	
	public Case getPosition(){
		return path.get(path.size()-1);
	}
}
