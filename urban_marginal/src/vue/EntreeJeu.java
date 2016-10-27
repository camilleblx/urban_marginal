package vue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class EntreeJeu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIp;
	private Controle controle ;
	private String ip ;
	
	private void btnStart_clic() {
		controle.evenementVue(this, "serveur");
	}
	private void btnExit_clic() {
		System.exit(0);
	}
	private void btnConnect_clic() {
		controle.evenementVue(this,txtIp.getText());
		}
	
	public EntreeJeu(Controle controle) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Start a server :");
		lblNewLabel.setBounds(16, 46, 84, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblConnectAn = new JLabel("Connect an existing server :");
		lblConnectAn.setBounds(16, 81, 166, 14);
		contentPane.add(lblConnectAn);
		
		JLabel lblIpServer = new JLabel("IP Server :");
		lblIpServer.setBounds(16, 106, 73, 24);
		contentPane.add(lblIpServer);
		
		JButton btnNewButtonStart = new JButton("Start");
		btnNewButtonStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnStart_clic() ;
			}
		});
		btnNewButtonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButtonStart.setBounds(199, 47, 89, 23);
		contentPane.add(btnNewButtonStart);
		
		JButton btnNewButtonConnect = new JButton("Connect");
		btnNewButtonConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnConnect_clic() ;
			}
		});
		btnNewButtonConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButtonConnect.setBounds(199, 107, 89, 23);
		contentPane.add(btnNewButtonConnect);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnExit_clic() ;
			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit.setBounds(199, 151, 89, 23);
		contentPane.add(btnExit);
		
		txtIp = new JTextField();
		txtIp.setText("127.0.0.1");
		txtIp.setBounds(96, 108, 86, 20);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
		
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
			txtIp.setText(ip);
		} catch (UnknownHostException e1) {
			System.out.println(e1);
		}
		this.controle = controle ;
	}
}