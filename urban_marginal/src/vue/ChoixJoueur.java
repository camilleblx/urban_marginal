package vue;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import controleur.Global;
import outils.son.Son;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ChoixJoueur extends JFrame implements Global {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPseudo ;
	private Integer numPerso ;
	private JLabel lblPersonnage;
	private Controle controle ;
	private Son precedent ;
	private Son suivant ;
	private Son go ;
	private Son welcome ;
	
	private void lblPrecedent_clic() {
		this.precedent.play();
		numPerso--;
		if(numPerso == 0){
			numPerso = NBPERSOS;
		}
			affichePerso();
	}

	private void lblSuivant_clic() {
		this.suivant.play();
		if(numPerso == 3){
			numPerso = 0;
		}
			numPerso++;
			affichePerso();
	}
	
	private void lblGo_clic() {
		this.go.play();
		if((txtPseudo.getText()).equals("")){
			JOptionPane.showMessageDialog(null,"Le Pseudo est obligatoire");
			txtPseudo.requestFocus();	
		} else{
			controle.evenementVue(this, PSEUDO + SEPARE + txtPseudo.getText() + SEPARE + numPerso);
		}
	}
	
	private void souris_normale () {
		contentPane.setCursor (new Cursor(Cursor.DEFAULT_CURSOR));
		}
	
	private void souris_doigt () {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	private void affichePerso () {
		lblPersonnage.setIcon(new ImageIcon(PERSO + numPerso + MARCHE + 1 + "d" + 1 + EXTIMAGE ));
	}
	
	public ChoixJoueur(Controle controle) {
		setTitle("Choice") ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 416, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.controle = controle ;
		
		
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblPrecedent_clic() ;
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				souris_doigt () ;
			}
			public void mouseExited(MouseEvent e) {
				souris_normale() ;
			}
		});
		lblPrecedent.setBounds(64, 144, 41, 48);
		contentPane.add(lblPrecedent);
		
		
		
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblSuivant_clic () ;
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				souris_doigt () ;
			}
			public void mouseExited(MouseEvent e) {
				souris_normale() ;
			}
		});
		lblSuivant.setBounds(298, 144, 35, 48);
		contentPane.add(lblSuivant);

		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic() ;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				souris_doigt () ;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				souris_normale() ;
			}
		});
		lblGo.setBounds(298, 188, 97, 82);
		contentPane.add(lblGo);
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(147, 244, 120, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		lblPersonnage = new JLabel("");
		lblPersonnage.setBounds(147, 115, 120, 118);
		contentPane.add(lblPersonnage);
		
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(5, 5, 390, 265);
		lblFond.setIcon(new ImageIcon(FONDCHOIX));	
		contentPane.add(lblFond);
		
		txtPseudo.requestFocus() ;
		
		numPerso = 1 ;
		affichePerso() ;
		
		this.precedent = new Son (SONPRECEDENT);
		this.suivant = new Son (SONSUIVANT);
		this.welcome = new Son (SONWELCOME);
		this.welcome.play();
		this.go = new Son (SONGO);	
	}		
}