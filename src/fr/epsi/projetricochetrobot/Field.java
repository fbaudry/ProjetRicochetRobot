package fr.epsi.projetricochetrobot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class Field extends Window{
	

	private static final long serialVersionUID = 1L;

	private static Field instance = null;
	
	public int nbRound;
	public boolean finished;
	//public HashMap<Integer, Case> cases = new HashMap<Integer, Case>();
	public Case[] casefield = new Case[256];
	
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
			Ant ant = new Ant(casefield, starter, target);
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

}
