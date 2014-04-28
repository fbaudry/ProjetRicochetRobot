package fr.epsi.projetricochetrobot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Field extends Window{
	

	private static final long serialVersionUID = 1L;

	private static Field instance = null;
	
	public int nbRound;
	public boolean finished;
	public Map<Integer, Case> cases;
	public Case[] casefield;
	
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
	
		try {
			System.setProperty( "file.encoding", "UTF-8" );
			fr = new FileReader("/Users/FERD/Git/ProjetRicochetRobot/case.txt");
			BufferedReader reader = new BufferedReader(fr);
			LineNumberReader counter = new LineNumberReader(reader);
			String line = null;
			while ((line = counter.readLine()) != null) {    
				String[] lineCases = line.split(",");
				this.cases.put(Integer.parseInt(lineCases[0]), new Case(
						Integer.parseInt(lineCases[0]),
						Integer.parseInt(lineCases[1]),
						Integer.parseInt(lineCases[2]),
						Boolean.parseBoolean(lineCases[3]),
						Boolean.parseBoolean(lineCases[4]),
						Boolean.parseBoolean(lineCases[5]),
						Boolean.parseBoolean(lineCases[6]),
						new File(lineCases[7]),
						false,
						super.getGraphics()));
			}
			counter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		for(int i=0;i<casefield.length;i++)
//		{
//			if(i == Constant.targetNumCase)
//				this.casefield[i] =  new Case(i, true, super.getGraphics());
//			else
//				this.casefield[i] = new Case(i, false, super.getGraphics());
//			
//			
//			
//			
//		}
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
	}

}
