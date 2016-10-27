package controleur;

import vue.Arene ;
import vue.ChoixJoueur;
import vue.EntreeJeu ;

import java.awt.Window;
import java.awt.event.KeyAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.JeuClient; 
import modele.Jeu;
import modele.JeuServeur;
import modele.Label;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;

public class Controle implements Global {
	private EntreeJeu frmEntreeJeu ;
	private Connection connection;
	private Jeu leJeu ;
	private Arene frmArene ;
	private ChoixJoueur frmChoixJoueur ;

	
	public static void main(String[] args) {
			new Controle ();
	}
	
	public void setConnection(Connection connection){
		this.connection=connection;
		if(leJeu instanceof JeuServeur){
			leJeu.setConnection(connection);
		}
		
	}
	
	public Controle () {
		this.frmEntreeJeu = new EntreeJeu(this);
		frmEntreeJeu.setVisible(true);
	}
	
	public void receptionInfo (Connection connection, Object info) {
		this.leJeu.reception(connection, info) ;	
	}
		
	public void evenementVue (JFrame uneFrame, Object info) {
		if (uneFrame instanceof EntreeJeu){
			evenementEntreeJeu(info);
		}
		if (uneFrame instanceof ChoixJoueur){
			evenementChoixJoueur(info);
		}
		if (uneFrame instanceof Arene) {
			evenementArene(info);	
		}
	}
	
	private void evenementArene(Object info) {
		((JeuClient) this.leJeu).envoi(info);
	}
	
	public void evenementChoixJoueur (Object info) {
		((JeuClient)this.leJeu).envoi(info);
		frmChoixJoueur.dispose();
		frmArene.setVisible(true);
	}

	private void evenementEntreeJeu(Object info) {	
		if((String) info == "serveur") {
			new ServeurSocket(this, PORT);
			leJeu = new JeuServeur(this) ;
			frmEntreeJeu.dispose();
			frmArene = new Arene("serveur", this) ;
			((JeuServeur)this.leJeu).constructionMurs();
			frmArene.setVisible(true);
		} else {
				if((new ClientSocket((String)info, PORT, this)).isConnexionOK()) {
					leJeu = new JeuClient(this) ;
					leJeu.setConnection(connection);
					frmArene = new Arene("client", this) ;
					frmEntreeJeu.dispose();
					frmChoixJoueur = new ChoixJoueur(this);
					frmChoixJoueur.setVisible(true);
				}
		}	
	}
	
	public void evenementModele(Object unJeu, String ordre, Object info) {
		if (unJeu instanceof JeuServeur){
			evenementJeuServeur(ordre, info) ;	
		}
		if (unJeu instanceof JeuClient) {
			evenementJeuClient(ordre, info) ;
		}		
	}
	
	private void evenementJeuClient(String ordre, Object info) {
		if(ordre == "ajout panel murs"){
			frmArene.ajoutPanelMurs((JPanel)info);
		}
		if(ordre == "ajout joueur"){
			frmArene.ajoutModifJoueur(((Label)info).getNumLabel(), ((Label)info).getJLabel());
		}
		if(ordre == "remplace chat"){
			frmArene.remplaceChat((String)info) ;
		}
		if (ordre == "son"){
			frmArene.joueSon((Integer)info) ;
		}
	}

	private void evenementJeuServeur(String ordre, Object info) {
		if (ordre == "ajout mur") {
			frmArene.ajoutMur((JLabel)info);
		}
		if (ordre == "envoi panel murs") {
			((JeuServeur)this.leJeu).envoi((Connection)info, frmArene.getJpnMurs());
		}
		if (ordre == "ajout joueur") {
			frmArene.ajoutJoueur((JLabel) info);
		}
		if (ordre == "ajout phrase") {
			frmArene.ajoutChat(((String)info));
			((JeuServeur) this.leJeu).envoi(frmArene.getTxtChat().getText());
		}	
	}

	public void deconnection(Connection connection) {
		leJeu.deconnection(connection);
	}

	public Jeu getLeJeu() {
		return leJeu;
	}
}