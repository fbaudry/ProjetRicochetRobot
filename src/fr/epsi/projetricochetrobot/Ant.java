package fr.epsi.projetricochetrobot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ant {
	private List<Case> path;
	private int nbMoveLeft = Constant.nbMove;
	
	
	public Ant(Case starter)
	{
		this.path = new ArrayList<Case>();
		this.path.add(starter);
	}
	
	public void move()
	{
		// La fourmi bouge vers la gauche, la droite, le haut ou le bas
		path.add(this.getCaseToMove());
		
		// Réduire le nombre de coup restant
		nbMoveLeft--;
		
		// Vérifier si la foumi est sur la case cible
		if(this.getAntPosition().isTarget())
			Field.getInstance().setFinished(false);
	}
	
	public Case getCaseToMove()
	{
		List<Case> cases = new ArrayList<Case>();
		
		// Vérifier si il y a une case a gauche, et l'ajouter dans la liste de tirage
		if(this.getAntPosition().getCaseNumber() % 16 != 0 && this.getAntPosition().getCaseNumber() != 121 && this.getAntPosition().getCaseNumber() != 138)
		{
			// Vérifier si il n'y a pas de bordure à gauche sur la case actuelle
			// Vérifier si il n'y a pas de bordure droite sur la case à gauche
			if(this.getAntPosition().getWall(3) == false && Field.getInstance().casefield[this.getAntPosition().getCaseNumber()-1].getWall(1) == false)
			{
				cases.add(Field.getInstance().casefield[this.getAntPosition().getCaseNumber()-1]);	
			}
		}
			
		
		// Vérifier si il y a une case a droite, et l'ajouter dans la liste de  tirage
		if((this.getAntPosition().getCaseNumber() - 15) < 0 || (this.getAntPosition().getCaseNumber()-15) % 16 != 0 && this.getAntPosition().getCaseNumber() != 118 && this.getAntPosition().getCaseNumber() != 135)
		{
			// Vérifier si il n'y a pas de bordure a droite sur la case actuelle
			// Vérifier si il n'y a pas de bordure gauche sur la case à droite
			if(this.getAntPosition().getWall(1) == false && Field.getInstance().casefield[this.getAntPosition().getCaseNumber()+1].getWall(3) == false)
			{
				cases.add(Field.getInstance().casefield[this.getAntPosition().getCaseNumber()+1]);	
			}			
		}
		
		// Vérifier si il y a une case en haut, et l'ajouter dans la liste de tirage
		if(this.getAntPosition().getCaseNumber() > 15 && this.getAntPosition().getCaseNumber() != 152 && this.getAntPosition().getCaseNumber() != 153)
		{
			// Vérifier si il n'y a pas de bordure en haut sur la case actuelle
			// Vérifier si il n'y a pas de bordure en bas sur la case en haut
			if(this.getAntPosition().getWall(0) == false && Field.getInstance().casefield[this.getAntPosition().getCaseNumber()-16].getWall(2) == false)
			{
				cases.add(Field.getInstance().casefield[this.getAntPosition().getCaseNumber()-16]);
			}
		}
			
		
		// Vérifier si il y a une case en bas et l'ajouter dans la liste de tirage
		if(this.getAntPosition().getCaseNumber() < 239 && this.getAntPosition().getCaseNumber() != 103 && this.getAntPosition().getCaseNumber() != 104)
		{
			// Vérifier si il n'y a pas de bordure en bas sur la case actuelle
			// Vérifier si il n'y a pas de bordure en haut sur la case en bas
			if(this.getAntPosition().getWall(2) == false && Field.getInstance().casefield[this.getAntPosition().getCaseNumber()+16].getWall(0) == false)
			{
				cases.add(Field.getInstance().casefield[this.getAntPosition().getCaseNumber()+16]);
			}
		}
			
		//Tirage d'un nombre entre 0 et 1
		Random random = new Random();
		double value = random.nextDouble();
		
		
			
		// Retourner la case selectionnée
		return null;
	}
	
	
	public Case getAntPosition()
	{
		return path.get(path.size()-1);
	}
	
	
	
	
	
}
