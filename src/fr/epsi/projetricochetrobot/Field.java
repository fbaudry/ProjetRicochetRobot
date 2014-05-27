package fr.epsi.projetricochetrobot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Field extends Window{
	

	private static final long serialVersionUID = 1L;

	private static Field instance = null;
	
	private int nbFoundWay = 0;
	
	public int nbRound;
	public boolean finished;
	public Map<Integer, Case> cases;
	public Case[] casefield = new Case[256];
	public List<Case> resultWay;

	
	private Case starter = null;
	private Case target = null;
	
	protected Field(){
		super();
	}

	public static Field getInstance() {
		if(instance == null) {
			instance = new Field();
		}
		return instance;
	}
	
	public void init()
	{
		this.nbRound = 0;
		this.finished = false;		
		
		FileReader fr;
	
		//on initialise les cases
		try {
			System.setProperty( "file.encoding", "UTF-8" );
			fr = new FileReader("./case.csv");
			BufferedReader reader = new BufferedReader(fr);
			LineNumberReader counter = new LineNumberReader(reader);
			String line = null;
			while ((line = counter.readLine()) != null) {    
				String[] lineCases = line.split(",");
				
				Integer i = Integer.parseInt(lineCases[0]);
				
				Case c = new Case(
						Integer.parseInt(lineCases[0]),
						Integer.parseInt(lineCases[1]),
						Integer.parseInt(lineCases[2]),
						Boolean.parseBoolean(lineCases[3]),
						Boolean.parseBoolean(lineCases[4]),
						Boolean.parseBoolean(lineCases[5]),
						Boolean.parseBoolean(lineCases[6]),
						false,
						super.getGraphics());
				
				//0->haut; 1->droite; 2->bas; 3->gauche				
				c.setFile();
				c.drawImage();
				
				if(Boolean.parseBoolean(lineCases[7])){
					c.setStarter();
					starter=c;
				}
				
				if(Boolean.parseBoolean(lineCases[8])){
					c.setTarget();
					target=c;
				}
				
				//this.cases.put(i, c);
				casefield[i]=c;
			}
			counter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//on initialise les mures
		try {
			System.setProperty( "file.encoding", "UTF-8" );
			fr = new FileReader("./walls.csv");
			BufferedReader reader = new BufferedReader(fr);
			LineNumberReader counter = new LineNumberReader(reader);
			String line = null;
			while ((line = counter.readLine()) != null) {    
				String[] lineCases = line.split(",");
				
				if(Integer.parseInt(lineCases[0])+1 == Integer.parseInt(lineCases[1])){
					Case c1 = this.casefield[Integer.parseInt(lineCases[0])];
					c1.setWallRight();
					c1.setFile();
					c1.drawImage();
					Case c2 = this.casefield[Integer.parseInt(lineCases[1])];
					c2.setWallLeft();
					c2.setFile();
					c2.drawImage();
				}else if(Integer.parseInt(lineCases[0])+16 == Integer.parseInt(lineCases[1])){
					Case c1 = this.casefield[Integer.parseInt(lineCases[0])];
					c1.setWallBottom();
					c1.setFile();
					c1.drawImage();
					Case c2 = this.casefield[Integer.parseInt(lineCases[1])];
					c2.setWallTop();
					c2.setFile();
					c2.drawImage();
				}
			}
			counter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		runAnt();
		
		findWay();
	}
	
	public void runAnt(){
		nbFoundWay = 0;
		while(nbFoundWay < Constant.nbFoundWay){
			Ant ant = new Ant(starter, target);
			ant.move();
			evaporation();
		}
	}
	
	public int getNbFoundWay() {
		return nbFoundWay;
	}

	public void setNbFoundWay(int nbFoundWay) {
		this.nbFoundWay = nbFoundWay;
	}

	public void incrementNbRound()
	{
		nbRound++;
	}
	
	public void setNbRound(int nbRound)
	{
		this.nbRound = nbRound;
	}
	
	public int getNbRound()
	{
		return this.nbRound;
	}
	
	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}
	
	public boolean isFinished()
	{
		return this.finished;
	}
	
	public Case runStraight(int direction, Case position)
	{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(position.getWall(direction) == false){
			return getCaseByDirection(direction, position);
		}
		
		this.clear();
		
		return null;
	}
	
	public Case getCaseByDirection(int direction, Case position){
		switch(direction){
			case 0:
			{
				if(position.getCaseNumber()-16 > 0){
					return this.casefield[position.getCaseNumber()-16];
				}
				
				return null;
			}
			case 1: 
			{
				if(position.getCaseNumber()%16 != 15){
					return this.casefield[position.getCaseNumber()+1];
				}
				
				return null;
			}
			case 2:
			{
				if(position.getCaseNumber()+16 < 256){
					return this.casefield[position.getCaseNumber()+16];
				}
			}
			case 3:
			{
				if(position.getCaseNumber()%16 != 0){
					
					return this.casefield[position.getCaseNumber()-1];
				}
				
				return null;
			}
			default:
				return null;
		}
	}
	
	public int getRandomDirection(){
		return (int)(Math.random()*4);
	}
	
	public void findWay()
	{
		resultWay =  new ArrayList<Case>();
		boolean foundTarget = false;
		
		int i = starter.getCaseNumber();
		
		while(foundTarget != true)
		{	
			//on récupère la list les case accessible
			List<Case> voisines = this.getVoisines(casefield[i], resultWay);
			
			//on choisit une case sur laquelle aller
			Case choisedCase = this.selectVoisine(voisines);
			
			if(choisedCase!=null){
				resultWay.add(choisedCase);
				i = choisedCase.getCaseNumber();
				if(choisedCase == target){
					foundTarget = true;
					showRightWay();
				}
			}
		}
	}
	
	public void showRightWay(){
		System.out.println("Chemin trouvé");
		System.out.println("Case de départ : " + starter.getCaseNumber());
		System.out.println("Case d'arrivée : " + target.getCaseNumber());
		System.out.println("-----------------");
		
		for(int i = 0; i < resultWay.size(); i++){
			System.out.println(resultWay.get(i).getCaseNumber());
			resultWay.get(i).setFile(new File("./img/path.png"));
			resultWay.get(i).drawImage();
		}
	}
	
	public List<Case> getVoisines(Case position, List<Case> way)
	{
		List<Case> voisines = new ArrayList<Case>();
				
		int numCaseLeft = position.getCaseNumber() - 1;
		int numCaseRight = position.getCaseNumber() + 1;
		int numCaseTop = position.getCaseNumber() - 16;
		int numCaseBottom = position.getCaseNumber() + 16;
		
		if(position.getWall(0) == false && position.getCaseNumber() > 15){
			voisines.add(this.casefield[numCaseLeft]);
		}
		
		if(position.getWall(1) == false && position.getCaseNumber()%16 != 15){
			voisines.add(this.casefield[numCaseLeft]);
		}
		
		if(position.getWall(2) == false && position.getCaseNumber()+16 < 256){
			voisines.add(this.casefield[numCaseBottom]);
		}
		
		if(position.getWall(3) == false && position.getCaseNumber()%16 != 0){
			voisines.add(this.casefield[numCaseLeft]);
		}
		/*// Si la case actuelle à une case à sa gauche
		// Si la case actuelle n'a pas de mur à sa gauche et que sa voisine de gauche n'a pas de mur à droite
		// Si la case de gauche n'a pas déjà été parcourue
		if(position.getCaseNumber()%16 != 0)
			if(position.getWall(3) == false)
				if(casefield[numCaseLeft].getWall(1) == false)
					if(isKnownCase(casefield[numCaseLeft],way) == false)
						voisines.add(casefield[numCaseLeft]);
		
		
		// Si la case actuelle à une case à sa droite
		// Si la case actuelle n'a pas de mur à sa droite et que sa voisine de droite n'a pas de mur à gauche
		// Si la case de droite n'a pas déjà été parcourue
		if(position.getCaseNumber()%16 != 15)
			if(position.getWall(1) == false)
				if(casefield[numCaseRight].getWall(3) == false)
					if(isKnownCase(casefield[numCaseRight],way) == false)
						voisines.add(casefield[numCaseRight]);
		
		
		// Si la case actuelle à une case en haut
		// Si la case actuelle n'a pas de mur en haut et que sa voisine du haut n'a pas de mur en bas
		// Si la case du haut n'a pas déjà été parcourue
		if(position.getCaseNumber() > 15)
			if(position.getWall(0) == false)
				if(casefield[numCaseTop].getWall(3) == false)
					if(isKnownCase(casefield[numCaseTop],way) == false)
						voisines.add(casefield[numCaseTop]);
		
		
		// Si la case actuelle à une case en bas
		// Si la case actuelle n'a pas de mur en bas et que sa vosiine du bas n'a pas de mur en haut
		// Si la case du bas n'a pas déjà été parcourue
		if(position.getCaseNumber() < 240)
			if(position.getWall(3) == false)
				if(casefield[numCaseBottom].getWall(0) == false)
					if(isKnownCase(casefield[numCaseBottom],way) == false)
						voisines.add(casefield[numCaseBottom]);
		
		*/
		
		
		return voisines;
	}
	
	public boolean isKnownCase(Case position, List<Case> way){
		for(int i = 0; i < way.size(); i++){
			if(position == way.get(i))
				return true;
		}
		
		return false;
	}
	
	public Case selectVoisine(List<Case> voisines){
		// Si une des fonction voisines est l'arrivee
		for(int i = 0; i < voisines.size(); i++){
			if(voisines.get(i) == target)
				return voisines.get(i);
		}
		
		// Sinon on utilise la roue biaisée
		Case selection = null;
		
		int totalPheromone = 0;
		for(int i = 0; i < voisines.size(); i++){
			totalPheromone += voisines.get(i).getPheronomeLevel();
		}
		
		double rand = Math.random();
		
		double temp = 0;
		
		for(int i = 0; i < voisines.size(); i++){
			double luck = (double)voisines.get(i).getPheronomeLevel()/totalPheromone;
			if(rand >= temp && rand < (luck + temp)){					
				selection =  voisines.get(i);
				break;
			}else{
				temp += luck;
			}
		}
		
		return selection;
	}
	
	public void incrNbFoundWay(){
		nbFoundWay++;
	}
	
	public void evaporation(){
		for(int i = 0; i < casefield.length; i++){
			casefield[i].removePheromone();
		}
	}
	
	public void clear(){
		for(int i = 0; i < casefield.length; i++){
			casefield[i].setFile();
			casefield[i].drawImage();
		}
	}
}
