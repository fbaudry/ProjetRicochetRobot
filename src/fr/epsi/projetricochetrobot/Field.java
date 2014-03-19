package fr.epsi.projetricochetrobot;

public class Field {
	
	int nbRound;
	boolean isFinished;
	private Case casefield[];
	
	public Field()
	{
		this.nbRound = 0;
		this.casefield = new Case[256];
		for(int i=0;i<casefield.length;i++)
		{
			if(i == Constant.targetNumCase)
				this.casefield[i] =  new Case(i,true);
			else
				this.casefield[i] = new Case(i,false);
		}
		this.isFinished = false;
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
	
	public void isFinished(boolean isFinished)
	{
		this.isFinished = isFinished;
	}
	
	public boolean getIsFinished()
	{
		return this.isFinished;
	}
	

}
