package fr.epsi.projetricochetrobot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Field extends Window{
	

	private static final long serialVersionUID = 1L;

	private static Field instance = null;
	
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
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		
		initStarterAndTarget();
		runAnt();
	}
	
	public void runAnt(){
		int nbFoundWay = 0;
		while(nbFoundWay < Constant.nbFoundWay){
			Ant ant = new Ant(starter, target);
		}
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
	
	public void initStarterAndTarget()
	{
		int numberCaseStarter = (int)(Math.random() * (256));
		
		int numberCaseTarget = (int)(Math.random() * (256));
		while(numberCaseTarget == numberCaseStarter)
		{
			numberCaseTarget = (int)(Math.random() * (256));
		}
		this.casefield[numberCaseStarter].setStarter(true);
		this.casefield[numberCaseTarget].setTarget(true);
		
		starter = this.casefield[numberCaseStarter];
		target = this.casefield[numberCaseTarget];
	}
	
	public void findWay()
	{
		resultWay =  new ArrayList<Case>();
		boolean foundTarget = false;
		int i = starter.getCaseNumber();
		int j;
		// Nous plaçons un booléen à false afin d'arreter la boucle lorsque toute les fourmi aurons chercher
		while(foundTarget != true)
		{
			Case choisedCase = null;
			List<Case> voisines = this.getVoisines(casefield[i]);
			choisedCase = this.selectVoisine(voisines);
			resultWay.add(choisedCase);
			i = choisedCase.getCaseNumber();
			if(choisedCase == target)
			{
					foundTarget = true;
			}
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
		if(position.getCaseNumber()%16 != 0 && position.getWall(4) == false && casefield[numCaseLeft].getWall(1) == false && isKnownCase(casefield[numCaseLeft],resultWay) == false){
			voisines.add(casefield[numCaseLeft]);
		}
		
		// Si la case actuelle à une case à sa droite
		// Si la case actuelle n'a pas de mur à sa droite et que sa voisine de droite n'a pas de mur à gauche
		// Si la case de droite n'a pas déjà été parcourue
		if(position.getCaseNumber()%16 != 15 && position.getWall(1) == false && casefield[numCaseRight].getWall(4) == false && isKnownCase(casefield[numCaseRight],resultWay) == false){
			voisines.add(casefield[numCaseRight]);
		}
		
		// Si la case actuelle à une case en haut
		// Si la case actuelle n'a pas de mur en haut et que sa voisine du haut n'a pas de mur en bas
		// Si la case du haut n'a pas déjà été parcourue
		if(position.getCaseNumber() > 15 && position.getWall(0) == false && casefield[numCaseTop].getWall(3) == false && isKnownCase(casefield[numCaseTop],resultWay) == false){
			voisines.add(casefield[numCaseTop]);
		}
		
		// Si la case actuelle à une case en bas
		// Si la case actuelle n'a pas de mur en bas et que sa vosiine du bas n'a pas de mur en haut
		// Si la case du bas n'a pas déjà été parcourue
		if(position.getCaseNumber() < 240 && position.getWall(3) == false && casefield[numCaseBottom].getWall(0) == false && isKnownCase(casefield[numCaseBottom],resultWay) == false){
			voisines.add(casefield[numCaseBottom]);
		}
		
		
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
			if(rand >= temp && rand < luck){
				selection =  voisines.get(i);
			}else{
				temp += luck;
			}
		}
		
		return selection;
	}
}
