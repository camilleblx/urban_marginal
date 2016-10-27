package modele;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import outils.connexion.Connection;
import controleur.Global;

public class Joueur extends Objet implements Global {
	
	private static final int MAXVIE = 10 ;
	private static final int GAIN = 1 ;
	private static final int PERTE = 2 ;
	
	private String pseudo ;
	private int numPerso ;
	private Label message ;
	private JeuServeur jeuServeur ;
	private int vie ;
	private int orientation ;
	private int etape ;
	private Boule boule ;
	
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur ;
		this.vie = MAXVIE ;
		this.etape = 1 ;
		this.orientation = DROITE ;
	}
	
	public void initPerso(String unPseudo, int numPerso, Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		this.pseudo = pseudo ;
		this.numPerso = numPerso ;
		label = new Label (Label.getNbLabel(), new JLabel()) ;
		Label.setNbLabel(Label.getNbLabel() + 1 );
		jeuServeur.nouveauLabelJeu(label);
		label.getJLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getJLabel().setVerticalAlignment(SwingConstants.CENTER);
		
		message = new Label(Label.getNbLabel(), new JLabel());
		Label.setNbLabel(Label.getNbLabel() + 1);
		message.getJLabel().setHorizontalAlignment(SwingConstants.CENTER);
		message.getJLabel().setFont(new Font("Dialog", Font.PLAIN, 8));
		jeuServeur.nouveauLabelJeu(message) ;
		premierePosition(lesJoueurs, lesMurs) ;
		affiche(MARCHE, etape) ;
		
		boule = new Boule(jeuServeur) ;
		jeuServeur.envoi(boule.getLabel());
	}
	
	public void affiche (String etat, int etape) {
		label.getJLabel().setBounds(posX, posY, L_PERSO, H_PERSO) ;
		label.getJLabel().setIcon(new ImageIcon(PERSO + this.numPerso + etat + etape + "d" + orientation + EXTIMAGE)) ;
		message.getJLabel().setBounds(posX - 10, posY + H_PERSO, L_PERSO + 0, H_MESSAGE) ;
		message.getJLabel().setText(PSEUDO + " : " + vie);
		this.jeuServeur.envoi(super.label) ;
		this.jeuServeur.envoi(message);		
	}
	
	private void premierePosition (Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		label.getJLabel().setBounds(0,0, L_PERSO, H_PERSO ) ;
		do {
			super.posX = (int) Math.round(Math.random() * (L_ARENE - L_PERSO))  ;
			super.posY = (int) Math.round(Math.random() * (H_ARENE - H_PERSO - H_MESSAGE)) ;
		}
		while (toucheJoueur(lesJoueurs) || toucheMur(lesMurs)) ;
	}
	
	private int deplace (int action, int position, int orientation, int lepas, int max,
			Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		this.orientation=orientation ;
		int ancpos = position ;
		position += lepas ;
		if (position < 0) {
			position = 0 ;
		}
		if (position > max ){
			position = max ;
		}
		if (action == GAUCHE || action == DROITE) {
			posX = position ;
		}else {
			posY = position ;
		}
		if (toucheMur (lesMurs) || toucheJoueur(lesJoueurs)) {
			position = ancpos ;
		}
		etape = etape % NBETATSMARCHE + 1;
		return position ;
	}
	
	public void action (int action, Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		switch (action) {
		case GAUCHE :
			posX = deplace(action, super.posX, GAUCHE, -LEPAS, L_ARENE - (H_PERSO + H_MESSAGE), lesJoueurs, lesMurs );
			break ;
			
		case DROITE :
			posX = deplace(action, super.posX, DROITE, LEPAS, L_ARENE - (H_PERSO + H_MESSAGE), lesJoueurs, lesMurs );
			break ;
			
		case HAUT :
			posY = deplace(action, super.posY, orientation, -LEPAS, H_ARENE - (H_PERSO + H_MESSAGE), lesJoueurs, lesMurs );
			break ;
			
		case BAS :
			posX = deplace(action, super.posY, orientation, LEPAS, H_ARENE - (H_PERSO + H_MESSAGE), lesJoueurs, lesMurs );
			break ;
			
		case TIRE :
			if (!boule.getLabel().getJLabel().isVisible()) {
				jeuServeur.envoi(FIGHT) ;
				boule.tireBoule(this, lesMurs, lesJoueurs);
			}
			break ;
		}
		affiche(MARCHE, etape) ;
	}
	
	private boolean toucheJoueur(Hashtable<Connection, Joueur> lesJoueurs) {
		for (Joueur unJoueur : lesJoueurs.values()) {
			if (!unJoueur.equals(this)) {
				if (super.toucheObjet(unJoueur)) {
					return true ;
				}
			}
		}
		return false ;
	}
	
	private boolean toucheMur (ArrayList<Mur> lesMurs ) {
		for (Mur unMur : lesMurs ){
			if (super.toucheObjet(unMur)) {
				return true;
			}
		}
		return false;
	}
	
	public void recussite() {
		vie = 20 ;
	}
	
	public boolean estMort() {
		if(vie == 0) {
			return true ;
		}
		return false ;
	}

	public Label getMessage() {
		return message ;
	}
	
	public Boule getBoule() {
		return boule ;
	}
	
	public String getPseudo() {
		return pseudo ;
	}
	
	public int getOrientation() {
		
		return 0;
	}
	
	public void gainVie() {
		vie+=GAIN ;
		if (vie > 20 ){
			vie = 20 ;
		}
	}
	
	public void perteVie() {
		vie-=PERTE ;
		if (vie < 0) {
			vie = 0;
		}
	}

	public void departJoueur() {
		if(this.label != null) {
			this.message.getJLabel().setVisible(false);
			super.label.getJLabel().setVisible(false);
			this.boule.getLabel().getJLabel().setVisible(false);
			jeuServeur.envoi(label);
			jeuServeur.envoi(message);
			jeuServeur.envoi(boule.getLabel());
		
		}
	}
}
