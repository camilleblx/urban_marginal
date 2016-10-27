package outils.connexion;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ClientSocket {

	private  boolean connexionOK = false ;
	
	public ClientSocket (String ip, int port, Object leRecepteur) {
		try {
			Socket socket = new Socket(ip, port);
			System.out.println("La connexion au serveur s'est bien déroulé");
			this.connexionOK = true ;
			new Connection(socket, leRecepteur);
		} catch (UnknownHostException e){
			JOptionPane.showMessageDialog(null, "Le serveur est indisponible");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "L'adresse IP est incorrect");
		}
	}
	/**
	 * @return the connexionOK
	 */
	public boolean isConnexionOK() {
		return connexionOK;
	}
}