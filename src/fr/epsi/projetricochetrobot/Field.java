package fr.epsi.projetricochetrobot;

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
		
		for(int i=0;i<casefield.length;i++)
		{
			this.casefield[i] =  new Case(i, super.getGraphics());
		}
		initStarterAndTarget();
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
