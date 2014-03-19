package main.java.fr.epsi.projetricochetrobot;

public class Field {
	
	int nbRound;
	boolean isFinished;
	
	
	
	
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
	
	public void isFinished(boolean isFinished)
	{
		this.isFinished = isFinished;
	}
	
	public boolean getIsFinished()
	{
		return this.isFinished;
	}
	

}
