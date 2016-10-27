package modele;

import java.io.Serializable;
import javax.swing.JLabel;

public class Label implements Serializable {

	private static final long serialVersionUID = 1L;
	static int nbLabel ;
	private int numLabel ;
	private JLabel JLabel ;
	
	public Label(int numLabel, JLabel JLabel) {
		this.numLabel = numLabel ;
		this.JLabel = JLabel ;
	}
	
	public int getNumLabel() {
		return this.numLabel ;
	}
	
	public JLabel getJLabel() {
		return this.JLabel ;
	}
	
	public static int getNbLabel() {
		return nbLabel;
	}

	public static void setNbLabel(int nbLabel) {
		Label.nbLabel = nbLabel;
	}

}
