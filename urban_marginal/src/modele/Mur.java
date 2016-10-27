package modele;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;

public class Mur extends Objet implements Global {
	
	public Mur () {
		posY = (int)Math.round(Math.random() * (H_ARENE - H_MUR)) ;
		posX = (int)Math.round(Math.random() * (L_ARENE - L_MUR)) ;
		
		label = new Label(-1,new JLabel());
		label.getJLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getJLabel().setVerticalAlignment(SwingConstants.CENTER);
		label.getJLabel().setBounds(posX, posY, L_MUR, H_MUR);
		label.getJLabel().setIcon(new ImageIcon(MUR));		
	}
}