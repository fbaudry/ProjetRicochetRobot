package fr.epsi.projetricochetrobot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Field extends Window{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Field instance = null;
	
	public int nbRound;
	public boolean finished;
	public Case casefield[];
	
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
		this.casefield = new Case[256];
		
		FileReader fr;
	
		try {
			System.setProperty( "file.encoding", "UTF-8" );
			fr = new FileReader("/Users/FERD/Git/ProjetRicochetRobot/case.txt");
			BufferedReader reader = new BufferedReader(fr);
			LineNumberReader counter = new LineNumberReader(reader);
			String line = null;
			while ((line = counter.readLine()) != null) {    
				this.casefield[Integer.parseInt(line.substring(1, line.indexOf(",") - 1))] =  
						new Case(
								Integer.parseInt(line.substring(1, line.indexOf(",") - 1)),
								Integer.parseInt(line.substring(1, line.indexOf(","))),
								Integer.parseInt(line.substring(1, line.indexOf(",") + 1)),
								Boolean.parseBoolean(line.substring(1, line.indexOf(",") + 2)),
								Boolean.parseBoolean(line.substring(1, line.indexOf(",") + 3)),
								Boolean.parseBoolean(line.substring(1, line.indexOf(",") + 4)),
								Boolean.parseBoolean(line.substring(1, line.indexOf(",") + 5)),
								new File(line.substring(1, line.indexOf(",") + 6)),
								false,
								super.getGraphics());
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

	

}
