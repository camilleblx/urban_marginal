package vue;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import controleur.Global;
import outils.son.Son;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Arene extends JFrame implements Global {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSaisie;
	private JPanel jpnMurs;
	private JPanel jpnJeu ;
	private JTextArea txtChat;
	private boolean client ;
	private Controle controle ; 
	private Son[] lessons = new Son[SON.length] ; 
	
	/**
	 * Create the frame.
	 */
	public Arene(String typeJeu, Controle controle) {
		
		this.client = (typeJeu == "client");
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.controle = controle ;
		setBounds(100, 100, L_ARENE + 3*MARGE, H_ARENE + H_CHAT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		if (client) {
			contentPane.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					contentPane_keyPressed(arg0);
				}
			});
		}
		
		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, L_ARENE, H_ARENE);
		jpnJeu.setOpaque(false);
		contentPane.add(jpnJeu);
		jpnJeu.setLayout(null);
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, L_ARENE, H_ARENE);
		jpnMurs.setOpaque(false);
		contentPane.add(jpnMurs);
		jpnMurs.setLayout(null);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, L_ARENE, H_ARENE);
		lblFond.setIcon(new ImageIcon(FONDARENE));
		contentPane.add(lblFond);
		
		if (client) {
			txtSaisie = new JTextField();
			txtSaisie.setBounds(0, H_ARENE, L_ARENE, H_SAISIE);
			contentPane.add(txtSaisie);
			txtSaisie.setColumns(10);
	
			txtSaisie.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					txtSaisie_keyPressed(arg0) ;
				}
			});
		}
		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, H_ARENE + H_SAISIE, L_ARENE + H_CHAT, H_CHAT - H_SAISIE - 7 * MARGE);
		contentPane.add(jspChat);
		txtChat = new JTextArea();
		txtChat.setEditable(false); ;
		jspChat.setViewportView(txtChat);
		
		if(client){
			(new Son(SONAMBIANCE)).playContinue();
			for(int k=0;k<SON.length;k++){
				lessons[k] = new Son(CHEMINSONS+SON[k]);
			}
		}
	}
		
		public void ajoutMur (JLabel objet) {
			jpnMurs.add(objet) ;
			jpnMurs.repaint();
		}
		
		public void ajoutPanelMurs (JPanel objet){
			jpnMurs.add(objet);
			jpnMurs.repaint();
			contentPane.requestFocus() ;
		}
		
		public JPanel getJpnMurs() {
			return jpnMurs ;	
		}
		
		public void ajoutJoueur(JLabel unJoueur) {
			jpnJeu.add(unJoueur);
			jpnJeu.repaint();
		}
		
		public void ajoutModifJoueur(int num, JLabel unLabel) {
			try {
				this.jpnJeu.remove(num);
			} catch (ArrayIndexOutOfBoundsException e) {
	
			}
			this.jpnJeu.add(unLabel, num) ;
			this.jpnJeu.repaint();	
		}
		
		private void txtSaisie_keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				if (txtSaisie.getText() != "") {
					controle.evenementVue(this, CHAT + SEPARE + txtSaisie.getText());
					txtSaisie.setText("");
				}
				contentPane.requestFocus();
			}
		}
	
		private void contentPane_keyPressed(KeyEvent arg0) {
			int valeur = -1 ;
			switch (arg0.getKeyCode()) {
					case KeyEvent.VK_SPACE : 
						valeur = TIRE ;
						break ;
						
					case KeyEvent.VK_LEFT :
						valeur = GAUCHE ;
						break ;
					
					case KeyEvent.VK_RIGHT :
						valeur = DROITE ;
						break ;
						
					case KeyEvent.VK_DOWN :
						valeur = BAS ;
						break ;
						
					case KeyEvent.VK_UP :
						valeur = HAUT ;
						break ;
					}
					
					if (valeur != -1 ){
						controle.evenementVue(this, ACTION + SEPARE + valeur);	
					}
		}
		
		public void ajoutChat(String unePhrase) {
			txtChat.setText(unePhrase + "\r\n" + txtChat.getText());
		}

		public void remplaceChat (String remplace) {
			txtChat.setText(remplace);
		}
		
		public JTextArea getTxtChat() {
			return txtChat;
		}
		
		public void joueSon (int numSon) {
			lessons[numSon].play();
		}
	
}