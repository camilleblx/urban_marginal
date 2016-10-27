package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;
import outils.connexion.Connection;

public class Boule extends Objet implements Global {
	
	private JeuServeur jeuServeur ;
	
	public Boule (JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		super.label = new Label(Label.nbLabel++, new JLabel());
		super.label.getJLabel().setHorizontalAlignment(SwingConstants.CENTER);
		super.label.getJLabel().setVerticalAlignment(SwingConstants.CENTER);
		super.label.getJLabel().setBounds(0, 0, L_BOULE, H_BOULE);
		super.label.getJLabel().setIcon(new ImageIcon(BOULE));
		jeuServeur.nouveauLabelJeu(super.label);
		super.label.getJLabel().setVisible(false);
	}
	
	public void tireBoule (Joueur attaquant, ArrayList<Mur> lesMurs, Hashtable<Connection, Joueur> lesJoueurs) {
		if (attaquant.getOrientation() == GAUCHE ) {
			this.posX= attaquant.getposX()- L_BOULE-1 ;
		}
		else {
			this.posX=attaquant.getposX() + L_PERSO + 1 ;
		}
		this.posY = attaquant.getposY() + H_PERSO/2 ;
		new Attaque(attaquant, jeuServeur, lesMurs, lesJoueurs);
	}	
}
