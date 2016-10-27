package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;

import controleur.Global;
import outils.connexion.Connection;

public class Attaqiue extends Thread implements Global {
	
	private Joueur attaquant ;
	private JeuServeur jeuServeur ;
	private ArrayList<Mur> lesMurs ;
	Hashtable<Connection, Joueur> lesJoueurs ;
	
	public Attaque (Joueur attaquant, JeuServeur jeuServeur, ArrayList<Mur>lesMurs, Hashtable<Connection, Joueur> lesJoueurs) {
		this.lesJoueurs = lesJoueurs ;
		this.attaquant = attaquant ;
		this.jeuServeur = jeuServeur ;
		this.lesMurs = lesMurs ;
		super.start();
	}
	
	public void pause (Long mill, int nano) {
		try {
			Thread.sleep(mill, nano) ;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run () {
		Boule laboule = attaquant.getBoule() ;
		int orientation = attaquant.getOrientation() ;
		laboule.getLabel().getJLabel().setVisible(true) ;
		Joueur victime = null ;
		
		do {
			if (orientation == GAUCHE) {
				laboule.setposX(laboule.getposX() - LEPAS);
			} else {
				laboule.setposX(laboule.getposX() + LEPAS);
			}
			laboule.getLabel().getJLabel().setBounds(laboule.getposX(), laboule.getposY(), L_BOULE, H_BOULE);
			laboule.getLabel().getJLabel().setIcon(new ImageIcon(BOULE));
			jeuServeur.envoi(laboule.getLabel());
			victime = toucheJoueur();
		} while (laboule.getposX() > 0 && laboule.getposX() < L_ARENE && toucheMur() == false && victime == null);
		
		if (victime!= null && victime.estMort()==false) {
			jeuServeur.envoi(HURT);
			victime.perteVie();
			attaquant.gainVie();
			for (int i=1 ; i<NBETATSBLESSE ; i++) {
				victime.affiche(BLESSE, i) ;
				this.pause((long)80, 80);
			}
		if (victime.estMort()) {
			jeuServeur.envoi(DEATH);
				
			for (int i = 1; i <= NBETATSMORT; i++) {
				victime.affiche(MORT, i);
				this.pause((long)120, 0);
			}
			victime.recussite() ;
			victime.affiche(MARCHE, 1) ;
		}else{
			victime.affiche(MARCHE, 1) ;
		}
		attaquant.affiche(MARCHE, 1);
		}
	
		laboule.getLabel().getJLabel().setVisible(false);
		jeuServeur.envoi(laboule.getLabel());	
	}

	
	private boolean toucheMur () {
		for (Mur unMur : lesMurs) {
			if (attaquant.getBoule().toucheObjet(unMur)) {
				return true;
			}
		}
		return false;
	}
	
	private Joueur toucheJoueur() {
		for (Joueur unJoueur : lesJoueurs.values()) {
			if (attaquant.getBoule().toucheObjet(unJoueur)) {
				return unJoueur;
			}
		}
		return null;
	}
	
}
