package Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FenetreCloture extends JFrame {

	int nombreProduitFrais = 0;
	double CAProduitFrais = 0;
	int nombreProduitAutre = 0;
	double CAProduitAutre = 0;
	double chiffreAffaire = 0;
	int produitTotal = 0;
	
	DefaultListModel<String> modeleListeTransac = new DefaultListModel<String>();
	JList<String> listeTransac = new JList<String>(modeleListeTransac);
	ArrayList<String> transacText = new ArrayList<String>();
	JTextArea infoTransac = new JTextArea();
	
	JButton reinitialiser = new JButton("Reinitialiser");
	JButton reinitialiserBis = new JButton("Reinitialiser");
	
	JTextField nbProduitFrais = new JTextField("0");
	JTextField nbProduitAutre = new JTextField("0");
	JTextField chiffreAffaireProduitFrais = new JTextField("0€");
	JTextField chiffreAffaireProduitAutre = new JTextField("0€");
	JTextField chiffreAffaireProduit = new JTextField("0€");
	JTextField proportionProduitFraisCa = new JTextField("-%");
	JTextField proportionProduitAutreCa = new JTextField("-%");
	JTextField proportionProduitFrais = new JTextField("-%");
	JTextField proportionProduitAutre = new JTextField("-%");
	JTextField quantiteTotale = new JTextField("0");
	
	public FenetreCloture() {
		super("Cloture");
		
		setResizable(false);
		setSize(1200, 700);
		setLayout(new GridLayout(1,1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//PANELS
		JPanel panelGauche = new JPanel();
		JPanel panelDroite = new JPanel();
		panelDroite.setLayout(new GridLayout(0,2));
		panelGauche.setLayout(new BorderLayout());
		JPanel panelGaucheCentre = new JPanel();
		panelGaucheCentre.setLayout(new GridLayout(1,2));
		JPanel panelGaucheBas = new JPanel();
		panelGaucheCentre.setLayout(new GridLayout(1,1));
		JPanel PanelGaucheGauche = new JPanel();
		PanelGaucheGauche.setLayout(new GridLayout(1,1));
		
		//ELEMENTS
		UIManager.put("TabbedPane.selected", new Color(194, 194, 194));
		JTabbedPane onglets = new JTabbedPane();
		onglets.add("Historique des transactions",panelGauche);
		onglets.add("Statistiques",panelDroite);
		add(onglets);
		
		JLabel nbProduitFraisLabel = new JLabel("Nombre de produits frais : ");
		JLabel nbProduitAutreLabel = new JLabel("Nombre de produits autres : ");
		JLabel chiffreAffaireLabel = new JLabel("Chiffre d'affaire : ");
		JLabel chiffreAffaireProduitFraisLabel = new JLabel("Chiffre d'affaire produits frais : ");
		JLabel chiffreAffaireProduitAutreLabel = new JLabel("Chiffre d'affaire produits Autre : ");
		JLabel proportionProduitFraisCaLabel = new JLabel("Pourcentage produit frais du CA : ");
		JLabel proportionProduitAutreCaLabel = new JLabel("Pourcentage produit autre du CA : ");
		JLabel proportionProduitFraisLabel = new JLabel("Pourcentage produit frais : ");
		JLabel proportionProduitAutreLabel = new JLabel("Pourcentage produit autre : ");
		JLabel quantiteTotaleLabel = new JLabel("Quantité totale : ");
		panelDroite.add(nbProduitFraisLabel);
		panelDroite.add(nbProduitFrais);
		nbProduitFrais.setEditable(false);
		panelDroite.add(nbProduitAutreLabel);
		panelDroite.add(nbProduitAutre);
		nbProduitAutre.setEditable(false);
		panelDroite.add(quantiteTotaleLabel);
		panelDroite.add(quantiteTotale);
		quantiteTotale.setEditable(false);
		panelDroite.add(chiffreAffaireLabel);
		panelDroite.add(chiffreAffaireProduit);
		chiffreAffaireProduit.setEditable(false);
		panelDroite.add(chiffreAffaireProduitFraisLabel);
		panelDroite.add(chiffreAffaireProduitFrais);
		chiffreAffaireProduitFrais.setEditable(false);
		panelDroite.add(chiffreAffaireProduitAutreLabel);
		panelDroite.add(chiffreAffaireProduitAutre);
		chiffreAffaireProduitAutre.setEditable(false);
		panelDroite.add(proportionProduitFraisCaLabel);
		panelDroite.add(proportionProduitFraisCa);
		proportionProduitFraisCa.setEditable(false);
		panelDroite.add(proportionProduitAutreCaLabel);
		panelDroite.add(proportionProduitAutreCa);
		proportionProduitAutreCa.setEditable(false);
		panelDroite.add(proportionProduitFraisLabel);
		panelDroite.add(proportionProduitFrais);
		proportionProduitFrais.setEditable(false);
		panelDroite.add(proportionProduitAutreLabel);
		panelDroite.add(proportionProduitAutre);
		proportionProduitAutre.setEditable(false);
		panelDroite.add(new JLabel());
		panelDroite.add(new JLabel());
		panelDroite.add(new JLabel());
		
		listeTransac.setFixedCellHeight(30);
		infoTransac.setEditable(false);
		
		panelGauche.add(panelGaucheCentre,BorderLayout.CENTER);
		panelGaucheCentre.add(infoTransac);
		panelGauche.add(panelGaucheBas,BorderLayout.SOUTH);
		PanelGaucheGauche.add(listeTransac);
		panelGauche.add(PanelGaucheGauche,BorderLayout.WEST);
		panelGaucheBas.add(reinitialiserBis);
		panelDroite.add(reinitialiser);
		
		
		// Mise en forme
		getContentPane().setBackground(Color.gray);
		panelDroite.setBackground(new Color(27, 135, 106));
		panelGauche.setBackground(new Color(27, 135, 106));
		panelGaucheBas.setBackground(new Color(27, 135, 106));
		chiffreAffaireLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		chiffreAffaireLabel.setForeground(Color.white);
		chiffreAffaireProduitAutreLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		chiffreAffaireProduitAutreLabel.setForeground(Color.white);
		chiffreAffaireProduitFraisLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		chiffreAffaireProduitFraisLabel.setForeground(Color.white);
		nbProduitAutreLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		nbProduitAutreLabel.setForeground(Color.white);
		nbProduitFraisLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		nbProduitFraisLabel.setForeground(Color.white);
		proportionProduitAutreCaLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		proportionProduitAutreCaLabel.setForeground(Color.white);
		proportionProduitAutreLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		proportionProduitAutreLabel.setForeground(Color.white);
		proportionProduitFraisCaLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		proportionProduitFraisCaLabel.setForeground(Color.white);
		proportionProduitFraisLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		proportionProduitFraisLabel.setForeground(Color.white);
		quantiteTotaleLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		quantiteTotaleLabel.setForeground(Color.white);
		reinitialiser.setBackground(Color.lightGray);
		reinitialiserBis.setBackground(Color.lightGray);
		infoTransac.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		listeTransac.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		infoTransac.setBackground(Color.lightGray);
		listeTransac.setBackground(Color.lightGray);
		onglets.setBackground(new Color(150, 150, 150));
		infoTransac.setFont(new Font("Calibri", Font.BOLD, 24));
		
		nbProduitAutre.setBorder(BorderFactory.createBevelBorder(1));
		nbProduitAutre.setFont(new Font("Calibri", Font.BOLD, 18));
		nbProduitFrais.setBorder(BorderFactory.createBevelBorder(1));
		nbProduitFrais.setFont(new Font("Calibri", Font.BOLD, 18));
		quantiteTotale.setBorder(BorderFactory.createBevelBorder(1));
		quantiteTotale.setFont(new Font("Calibri", Font.BOLD, 18));
		chiffreAffaireProduit.setBorder(BorderFactory.createBevelBorder(1));
		chiffreAffaireProduit.setFont(new Font("Calibri", Font.BOLD, 18));
		chiffreAffaireProduitAutre.setBorder(BorderFactory.createBevelBorder(1));
		chiffreAffaireProduitAutre.setFont(new Font("Calibri", Font.BOLD, 18));
		chiffreAffaireProduitFrais.setBorder(BorderFactory.createBevelBorder(1));
		chiffreAffaireProduitFrais.setFont(new Font("Calibri", Font.BOLD, 18));
		proportionProduitFrais.setBorder(BorderFactory.createBevelBorder(1));
		proportionProduitFrais.setFont(new Font("Calibri", Font.BOLD, 18));
		proportionProduitFraisCa.setBorder(BorderFactory.createBevelBorder(1));
		proportionProduitFraisCa.setFont(new Font("Calibri", Font.BOLD, 18));
		proportionProduitAutre.setBorder(BorderFactory.createBevelBorder(1));
		proportionProduitAutre.setFont(new Font("Calibri", Font.BOLD, 18));
		proportionProduitAutreCa.setBorder(BorderFactory.createBevelBorder(1));
		proportionProduitAutreCa.setFont(new Font("Calibri", Font.BOLD, 18));
		reinitialiser.setBorder(BorderFactory.createRaisedBevelBorder());
		reinitialiserBis.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
		//LISTENER
		listeTransac.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				for (int i=0 ; i<modeleListeTransac.getSize() ; i++) {
					if (listeTransac.isSelectedIndex(i)) {
						infoTransac.setText(transacText.get(i));
					}
				}
				
			}
		});
	}
}
