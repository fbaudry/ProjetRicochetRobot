package fr.epsi.projetricochetrobot;

public class Field {
	
	public static int nbRound;
	public static boolean isFinished;
	public static Case casefield[];
	
	private Field()
	{
		
	}

	public void initField()
	{
		this.nbRound = 0;
		this.isFinished = false;		
		this.casefield = new Case[256];
		for(int i=0;i<casefield.length;i++)
		{
			if(i == Constant.targetNumCase)
				this.casefield[i] =  new Case(i,true);
			else
				this.casefield[i] = new Case(i,false);
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
	
	public void setIsFinished(boolean isFinished)
	{
		this.isFinished = isFinished;
	}
	
	public boolean getIsFinished()
	{
		return this.isFinished;
	}
	

}
