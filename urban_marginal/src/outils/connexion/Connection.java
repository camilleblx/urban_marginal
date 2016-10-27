package outils.connexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controleur.Controle ;
import javax.swing.JOptionPane;

public class Connection extends Thread {

	private Object leRecepteur;
	private ObjectInputStream in ;
	private ObjectOutputStream out ;

	public Connection (Socket socket, Object leRecepteur ) {
		this.leRecepteur=leRecepteur ;
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.print("erreur grave création socket serveur :" +e);
			System.exit(0);
		}
		try {
			this.in = new ObjectInputStream(socket.getInputStream()) ;
		} catch (IOException e) {
			System.out.print("erreur :" +e);
			System.exit(0);
		}
		super.start();
		((controleur.Controle)this.leRecepteur).setConnection(this);
	}
	
	public void run() {
  		boolean inOk = true;
  		Object reception;
  		while (inOk) {
  			try {
  				reception = in.readObject();
				((Controle)this.leRecepteur).receptionInfo(this, reception) ;
  			  				
  			} catch (ClassNotFoundException e) {
  				System.out.println("erreur format d'objet : "+e);
 				System.exit(0);	
  			} catch (IOException e) {
  				JOptionPane.showMessageDialog(null, "Le client s'est déconnecté");
  				inOk = false;
  				((Controle)this.leRecepteur).deconnection(this);
  				try {
  					in.close();
  				} catch (IOException e1) {
  					System.out.println("La fermeture du canal s'est mal passé : "+e);		
  				}  				
 			}
		}
	}
	
	public synchronized void envoi (Object unObjet) {
		try {
			this.out.reset();
			out.writeObject(unObjet) ;
			this.out.reset();
			out.flush();
		} catch (IOException e) {
			System.out.println("Erreur sur l'objet");
		}
	}
}