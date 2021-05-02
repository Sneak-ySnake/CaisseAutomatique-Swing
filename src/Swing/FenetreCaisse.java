package Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import javax.swing.*;
import metier.*;

public class FenetreCaisse extends JFrame {

	Stock stock = new Stock();
	Caisse caisse = new Caisse();
	Magasin magasin = new Magasin(stock);
	double reduc = 0;
	int numeroTransac = 1;
	double montant = 0;
	double montantCarte = 0;
	double montantEspece = 0;
	double montantRenduEspece = 0;
	
	DecimalFormat deciFormat = new DecimalFormat("#.##");
	
	Produit ordinateur = new ProduitAutre("Ordinateur", 16154890, 599.99d);
	Produit chaise = new ProduitAutre("Chaise", 19254700, 49.99d);
	Produit painChoco = new ProduitFrais("Pain au chocolat", 15154300, 1.67d);
	Produit lait = new ProduitFrais("Lait", 15164310, 1.99d);
	Produit yahourt = new ProduitFrais("Yahourt", 17194301, 1.50d);

	public FenetreCaisse() {
		super("Caisse");

		FenetreCloture fenetreCloture = new FenetreCloture();
		
		stock.ajouterProduit(ordinateur);
		stock.ajouterProduit(chaise);
		stock.ajouterProduit(painChoco);
		stock.ajouterProduit(lait);
		stock.ajouterProduit(yahourt);

		setResizable(false);
		setSize(1200, 700);
		setLayout(new GridLayout(1, 1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// PANEL
		JPanel panelGauche = new JPanel();
		JPanel panelGaucheNord = new JPanel();
		panelGauche.setLayout(new BorderLayout());
		panelGaucheNord.setLayout(new GridLayout(0, 1));
		JPanel panelGaucheCentre = new JPanel();
		panelGaucheCentre.setLayout(new FlowLayout());
		JPanel panelGaucheSud = new JPanel();
		panelGaucheSud.setLayout(new GridLayout(5, 4));
		JPanel panelDroite = new JPanel();
		JPanel panelDroiteNord = new JPanel();
		JPanel panelDroiteCentre = new JPanel();
		panelDroiteCentre.setLayout(new GridLayout(3,1));
		JPanel panelDroiteSud = new JPanel();
		JPanel panelDroiteGauche = new JPanel();
		panelDroiteSud.setLayout(new GridLayout(0, 2));
		panelDroite.setLayout(new BorderLayout());

		add(panelGauche);
		panelGauche.add(panelGaucheNord, BorderLayout.NORTH);
		panelGauche.add(panelGaucheCentre, BorderLayout.CENTER);
		panelGauche.add(panelGaucheSud, BorderLayout.SOUTH);
		add(panelDroite);
		panelDroite.add(panelDroiteNord, BorderLayout.NORTH);
		panelDroite.add(panelDroiteCentre, BorderLayout.CENTER);
		panelDroite.add(panelDroiteSud, BorderLayout.SOUTH);
		panelDroite.add(panelDroiteGauche, BorderLayout.WEST);


		//PAIEMENT
		JLabel montantRestant = new JLabel();
		montantRestant.setFont(new Font("Calibri", Font.BOLD, 16));
		JButton carte = new JButton("carte");
		carte.setEnabled(false);
		JButton espece = new JButton("espece");
		espece.setEnabled(false);
		JTextField paiementEspece = new JTextField();
		paiementEspece.setEnabled(false);

		// ELEMENTS FENETRE PRINCIPALE
		JLabel scan = new JLabel("1. Scannez un article");
		JTextField entrerArticle = new JTextField();
		JLabel quantite = new JLabel("2. Quantité ?");
		JTextField entrerQuantite = new JTextField();
		JButton valider = new JButton("Valider");
		getRootPane().setDefaultButton(valider);
		panelGaucheNord.add(scan);
		panelGaucheNord.add(entrerArticle);
		panelGaucheNord.add(quantite);
		panelGaucheNord.add(entrerQuantite);
		panelGaucheNord.add(valider);

		JLabel liste = new JLabel("Liste :");
		JLabel listeInfo = new JLabel("Code   -    Produit   -    Prix   -   Quantité");
		DefaultListModel<String> modele = new DefaultListModel<String>();
		JList<String> listeArticle = new JList<String>(modele);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(listeArticle);
		listeArticle.setPreferredSize(new Dimension(400, 325));
		JButton annuler = new JButton("Annuler");
		JButton terminer = new JButton("4. Terminer");
		terminer.setEnabled(false);
		JButton cloturer = new JButton("Cloturer");
		JLabel prixTotalLabel = new JLabel(" Prix total :");
		JTextField prixTotal = new JTextField();
		prixTotal.setEditable(false);
		JLabel tvaLabel = new JLabel(" TVA :");
		JTextField tva = new JTextField();
		tva.setEditable(false);
		JLabel prixTtcLabel = new JLabel(" Prix TTC :");
		JTextField prixTtc = new JTextField();
		JLabel prixTtcLabelReduc = new JLabel(" Prix TTC réduit:");
		JTextField prixTtcReduc = new JTextField();
		prixTtcReduc.setEditable(false);
		JButton finaliser = new JButton("3. Valider panier");
		JLabel reduction = new JLabel(" Reduction :");
		JTextField reducTotale = new JTextField();
		reducTotale.setEditable(false);
		finaliser.setEnabled(false);
		prixTtc.setEditable(false);
		panelDroiteNord.add(liste);
		panelDroiteCentre.add(listeInfo);
		panelDroiteCentre.add(scroll);
		panelDroiteSud.add(new JLabel());
		panelDroiteSud.add(finaliser);
		panelDroiteSud.add(prixTotalLabel);
		panelDroiteSud.add(prixTotal);
		panelDroiteSud.add(tvaLabel);
		panelDroiteSud.add(tva);
		panelDroiteSud.add(reduction);
		panelDroiteSud.add(reducTotale);
		panelDroiteSud.add(prixTtcLabel);
		panelDroiteSud.add(prixTtc);
		panelDroiteSud.add(prixTtcLabelReduc);
		panelDroiteSud.add(prixTtcReduc);
		
		JPanel test = new JPanel();
		test.setBackground(Color.darkGray);
		JPanel test2 = new JPanel();
		test2.setBackground(Color.darkGray);
		panelDroiteSud.add(test);
		panelDroiteSud.add(test2);
		
		panelDroiteSud.add(new JLabel(" Restant à payer : "));
		panelDroiteSud.add(montantRestant);
		panelDroiteSud.add(espece);
		panelDroiteSud.add(carte);
		panelDroiteSud.add(paiementEspece);
		

		panelDroiteSud.add(terminer);
		JPanel test3 = new JPanel();
		test3.setBackground(Color.darkGray);
		JPanel test4 = new JPanel();
		test4.setBackground(Color.darkGray);
		panelDroiteSud.add(test3);
		panelDroiteSud.add(test4);
		panelDroiteSud.add(annuler);
		panelDroiteSud.add(cloturer);

		// BOUTON REDUC
		JButton reduc5 = new JButton("5%");
		reduc5.setEnabled(false);
		panelGaucheCentre.add(reduc5);
		JButton reduc10 = new JButton("10%");
		reduc10.setEnabled(false);
		panelGaucheCentre.add(reduc10);
		JButton reduc25 = new JButton("25%");
		reduc25.setEnabled(false);
		panelGaucheCentre.add(reduc25);
		JButton reduc50 = new JButton("50%");
		reduc50.setEnabled(false);
		panelGaucheCentre.add(reduc50);
		JButton reduc70 = new JButton("70%");
		reduc70.setEnabled(false);
		panelGaucheCentre.add(reduc70);

		//PAVE NUMERIQUE
		JButton bouton1 = new JButton("1");
		bouton1.setFocusable(false);
		panelGaucheSud.add(bouton1);
		JButton bouton2 = new JButton("2");
		bouton2.setFocusable(false);
		panelGaucheSud.add(bouton2);
		JButton bouton3 = new JButton("3");
		bouton3.setFocusable(false);
		panelGaucheSud.add(bouton3);
		panelGaucheSud.add(new JLabel());
		JButton bouton4 = new JButton("4");
		bouton4.setFocusable(false);
		panelGaucheSud.add(bouton4);
		JButton bouton5 = new JButton("5");
		bouton5.setFocusable(false);
		panelGaucheSud.add(bouton5);
		JButton bouton6 = new JButton("6");
		bouton6.setFocusable(false);
		panelGaucheSud.add(bouton6);
		panelGaucheSud.add(new JLabel());
		JButton bouton7 = new JButton("7");
		bouton7.setFocusable(false);
		panelGaucheSud.add(bouton7);
		JButton bouton8 = new JButton("8");
		bouton8.setFocusable(false);
		panelGaucheSud.add(bouton8);
		JButton bouton9 = new JButton("9");
		bouton9.setFocusable(false);
		panelGaucheSud.add(bouton9);
		panelGaucheSud.add(new JLabel());
		JButton bouton0 = new JButton("0");
		bouton0.setFocusable(false);
		panelGaucheSud.add(bouton0);
		panelGaucheSud.add(new JLabel());
		panelGaucheSud.add(new JLabel());
		panelGaucheSud.add(new JLabel());

		
		// Mise en forme
		panelGaucheNord.setBackground(new Color(27, 135, 106));
		panelGaucheCentre.setBackground(new Color(27, 135, 106));
		panelGaucheSud.setBackground(new Color(27, 135, 106));
		panelDroiteNord.setBackground(new Color(27, 135, 106));
		panelDroiteCentre.setBackground(new Color(27, 135, 106));
		panelDroiteGauche.setBackground(new Color(27, 135, 106));
		panelDroiteSud.setBackground(new Color(145, 158, 155));
		panelDroiteSud.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));
		listeArticle.setBorder(BorderFactory.createRaisedBevelBorder());
		liste.setForeground(Color.white);
		liste.setFont(new Font("Calibri", Font.BOLD, 28));
		listeInfo.setForeground(Color.white);
		listeInfo.setFont(new Font("Calibri", Font.BOLD, 18));
		scan.setForeground(Color.white);
		scan.setFont(new Font("Calibri", Font.BOLD, 18));
		prixTotalLabel.setForeground(new Color(28, 26, 26));
		prixTtcLabel.setForeground(new Color(28, 26, 26));
		tvaLabel.setForeground(new Color(28, 26, 26));
		reduction.setForeground(new Color(28, 26, 26));
		prixTtcLabelReduc.setForeground(new Color(28, 26, 26));
		listeArticle.setBackground(Color.lightGray);
		quantite.setForeground(Color.white);
		quantite.setFont(new Font("Calibri", Font.BOLD, 18));
		valider.setBackground(Color.lightGray);
		annuler.setBackground(Color.lightGray);
		terminer.setBackground(Color.lightGray);
		cloturer.setBackground(Color.lightGray);
		finaliser.setBackground(Color.lightGray);
		reduc5.setBackground(Color.lightGray);
		reduc10.setBackground(Color.lightGray);
		reduc25.setBackground(Color.lightGray);
		reduc50.setBackground(Color.lightGray);
		reduc70.setBackground(Color.lightGray);
		bouton1.setBackground(Color.lightGray);
		bouton1.setBorder(BorderFactory.createRaisedBevelBorder());
		bouton2.setBackground(Color.lightGray);
		bouton2.setBorder(BorderFactory.createRaisedBevelBorder());
		bouton3.setBackground(Color.lightGray);
		bouton3.setBorder(BorderFactory.createRaisedBevelBorder());
		bouton4.setBackground(Color.lightGray);
		bouton4.setBorder(BorderFactory.createRaisedBevelBorder());
		bouton5.setBackground(Color.lightGray);
		bouton5.setBorder(BorderFactory.createRaisedBevelBorder());
		bouton6.setBackground(Color.lightGray);
		bouton6.setBorder(BorderFactory.createRaisedBevelBorder());
		bouton7.setBackground(Color.lightGray);
		bouton7.setBorder(BorderFactory.createRaisedBevelBorder());
		bouton8.setBackground(Color.lightGray);
		bouton8.setBorder(BorderFactory.createRaisedBevelBorder());
		bouton9.setBackground(Color.lightGray);
		bouton9.setBorder(BorderFactory.createRaisedBevelBorder());
		bouton0.setBackground(Color.lightGray);
		bouton0.setBorder(BorderFactory.createRaisedBevelBorder());
		carte.setBackground(Color.lightGray);
		espece.setBackground(Color.lightGray);
		setVisible(true);
		listeArticle.setFixedCellHeight(30);
		terminer.setBorder(BorderFactory.createRaisedBevelBorder());
		espece.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		carte.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		valider.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
		// LISTENER
		valider.addActionListener(new ActionListener() {

			boolean valider;

			@Override
			public void actionPerformed(ActionEvent e) {
				reduc5.setEnabled(true);
				reduc10.setEnabled(true);
				reduc25.setEnabled(true);
				reduc50.setEnabled(true);
				reduc70.setEnabled(true);
				//
				try {
					int codeSaisi = Integer.parseInt(entrerArticle.getText());
					int quantiteSaisi = Integer.parseInt(entrerQuantite.getText());
					valider = caisse.scannerArticle(stock, codeSaisi, quantiteSaisi);
					
						if (valider) {
							modele.addElement(stock.getListeStock().get(codeSaisi).getCode()+"    -    "+ stock.getListeStock().get(codeSaisi).getNom() +"   -    "+ stock.getListeStock().get(codeSaisi).getPrix() + "€     -     "+""+caisse.getListeClient().get(codeSaisi).intValue());
							entrerArticle.setText("");
							entrerQuantite.setText("");
							finaliser.setEnabled(true);
						}
						else if (!valider && stock.verifierArticle(codeSaisi)==null){
							JOptionPane.showMessageDialog(getContentPane(), "Code inconnu");
							entrerArticle.setText("");
							entrerQuantite.setText("");
						}
						else if (!valider) {
							entrerArticle.setText("");
							entrerQuantite.setText("");
						}
					
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(getContentPane(), "Format du code invalide");
					entrerArticle.setText("");
					entrerQuantite.setText("");
				}
			}
		});

		annuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				caisse.getListeClient().clear();
				modele.removeAllElements();
				entrerArticle.setText("");
				entrerQuantite.setText("");
				prixTotal.setText("");
				prixTtc.setText("");
				tva.setText("");
				reducTotale.setText("");
				prixTtcReduc.setText("");
				valider.setEnabled(true);
				finaliser.setEnabled(false);
				terminer.setEnabled(false);
				entrerArticle.setEditable(true);
				entrerQuantite.setEditable(true);
				reduc5.setEnabled(false);
				reduc10.setEnabled(false);
				reduc25.setEnabled(false);
				reduc50.setEnabled(false);
				reduc70.setEnabled(false);
				bouton0.setEnabled(true);
				bouton1.setEnabled(true);
				bouton2.setEnabled(true);
				bouton3.setEnabled(true);
				bouton4.setEnabled(true);
				bouton5.setEnabled(true);
				bouton6.setEnabled(true);
				bouton7.setEnabled(true);
				bouton8.setEnabled(true);
				bouton9.setEnabled(true);
				reduc = 0;
				entrerArticle.setBackground(Color.white);
				entrerQuantite.setBackground(Color.white);
				montantRestant.setText("");
				espece.setEnabled(false);
				carte.setEnabled(false);
				paiementEspece.setEnabled(false);
			}
		});

		finaliser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!caisse.getListeClient().isEmpty() && !modele.isEmpty()) {

					double prixHt = 0;
					double prixTaxe = 0;
					double prixTva = 0;
					double prixReduit = 0;
					
					for (int key : caisse.getListeClient().keySet()) {
						prixHt = prixHt + stock.getListeStock().get(key).getPrix()
								* caisse.getListeClient().get(key).intValue();
						prixTotal.setText(deciFormat.format(prixHt) + "");
						prixTaxe = prixTaxe + stock.getListeStock().get(key).getprixTaxe()
								* caisse.getListeClient().get(key).intValue();
						prixTtc.setText(deciFormat.format(prixTaxe) + "");
						prixTva = prixTva + stock.getListeStock().get(key).getTaxe()
								* caisse.getListeClient().get(key).intValue();
						tva.setText(deciFormat.format(prixTva) + "");
						if (reduc != 0) {
							prixReduit = prixTaxe - reduc;
							prixTtcReduc.setText(deciFormat.format(prixReduit) + "");
						}
					}

					reduc5.setEnabled(false);
					reduc10.setEnabled(false);
					reduc25.setEnabled(false);
					reduc50.setEnabled(false);
					reduc70.setEnabled(false);
					bouton0.setEnabled(false);
					bouton1.setEnabled(false);
					bouton2.setEnabled(false);
					bouton3.setEnabled(false);
					bouton4.setEnabled(false);
					bouton5.setEnabled(false);
					bouton6.setEnabled(false);
					bouton7.setEnabled(false);
					bouton8.setEnabled(false);
					bouton9.setEnabled(false);
					terminer.setEnabled(true);
					valider.setEnabled(false);
					entrerArticle.setBackground(Color.lightGray);
					entrerQuantite.setBackground(Color.lightGray);
					entrerArticle.setEditable(false);
					entrerQuantite.setEditable(false);
					finaliser.setEnabled(false);
					
					espece.setEnabled(true);
					carte.setEnabled(true);
					paiementEspece.setEnabled(true);
					paiementEspece.setText("Entrez un montant ici");
					
					montantCarte = 0;
					montantEspece = 0;
					montantRenduEspece = 0;
					
					if (prixTtcReduc.getText().equals("")) {
						montant = Double.parseDouble(prixTtc.getText().replace(",", "."));
						montantRestant.setText(montant+"€");
						
					}
					else {
						montant = Double.parseDouble(prixTtcReduc.getText().replace(",", "."));
						montantRestant.setText(montant+"€");
					}
					
				}
			}
		});

		terminer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if (montant == 0) {
					
					terminer.setEnabled(false);
					
					if (!prixTotal.getText().equals("") && !prixTtcLabel.getText().equals("")
							&& !tva.getText().equals("")) {
						
						fenetreCloture.modeleListeTransac.addElement("<html>Transaction numéro <b>"+numeroTransac+"</b><br><i>"+"Date : "+LocalDate.now()+" "+LocalTime.now()+"</i></html>");
						
						int quantiteArticle = 0;
						
						for (int key : caisse.getListeClient().keySet()) {
							quantiteArticle = quantiteArticle + caisse.getListeClient().get(key).intValue();
							if (stock.getListeStock().get(key) instanceof ProduitFrais) {
								fenetreCloture.nombreProduitFrais = fenetreCloture.nombreProduitFrais + caisse.getListeClient().get(key).intValue();
								fenetreCloture.CAProduitFrais = fenetreCloture.CAProduitFrais + (stock.getListeStock().get(key).getPrix() * caisse.getListeClient().get(key).intValue());
							}
						}
						for (int key : caisse.getListeClient().keySet()) {
							if (stock.getListeStock().get(key) instanceof ProduitAutre) {
								fenetreCloture.nombreProduitAutre = fenetreCloture.nombreProduitAutre + caisse.getListeClient().get(key).intValue();
								fenetreCloture.CAProduitAutre = fenetreCloture.CAProduitAutre + (stock.getListeStock().get(key).getPrix() * caisse.getListeClient().get(key).intValue());
							}
						}
						
						fenetreCloture.produitTotal = fenetreCloture.nombreProduitAutre + fenetreCloture.nombreProduitFrais;
						fenetreCloture.chiffreAffaireProduit.setText(deciFormat.format((fenetreCloture.CAProduitAutre+fenetreCloture.CAProduitFrais))+"€");
						fenetreCloture.chiffreAffaireProduitAutre.setText(deciFormat.format(fenetreCloture.CAProduitAutre)+"€");
						fenetreCloture.chiffreAffaireProduitFrais.setText(deciFormat.format(fenetreCloture.CAProduitFrais)+"€");
						fenetreCloture.nbProduitAutre.setText(fenetreCloture.nombreProduitAutre+"");
						fenetreCloture.nbProduitFrais.setText(fenetreCloture.nombreProduitFrais+"");
						fenetreCloture.proportionProduitAutreCa.setText(deciFormat.format(((fenetreCloture.CAProduitAutre/(fenetreCloture.CAProduitAutre+fenetreCloture.CAProduitFrais))*100))+"%");
						fenetreCloture.proportionProduitFraisCa.setText(deciFormat.format(((fenetreCloture.CAProduitFrais/(fenetreCloture.CAProduitAutre+fenetreCloture.CAProduitFrais))*100))+"%");
						fenetreCloture.quantiteTotale.setText(fenetreCloture.produitTotal+"");
						fenetreCloture.proportionProduitAutre.setText(deciFormat.format(((double)fenetreCloture.nombreProduitAutre/(double)(fenetreCloture.nombreProduitAutre+fenetreCloture.nombreProduitFrais))*100)+"%");
						fenetreCloture.proportionProduitFrais.setText(deciFormat.format(((double)fenetreCloture.nombreProduitFrais/(double)(fenetreCloture.nombreProduitAutre+fenetreCloture.nombreProduitFrais))*100)+"%");
						
						String info = "";
						for (int i=0 ; i<modele.getSize() ; i++) {
							info = info + modele.getElementAt(i).replace("<html>","").replace("</html>", "").replace("<br>", "\n").replace("<i>", "").replace("</i>", "")+ "\n";
						}
						info = info + "\n\nNombre d'articles : "+quantiteArticle+"\nPrix Total : "+prixTotal.getText()+"€\nTva : "+tva.getText()+"€\nPrix TTC : "+prixTtc.getText()+"€";
						
						if (!prixTtcReduc.getText().equals("")) {
							info = info + "\nReduction : "+reducTotale.getText()+"€\nPrix réduit : "+prixTtcReduc.getText()+"€";
						}
						
						info = info + "\n\nMontant payé par carte : " + deciFormat.format(montantCarte) + "€\nMontant payé en espèce : " + deciFormat.format(montantEspece) + " €\nEspèce rendu : " + deciFormat.format(montantRenduEspece) + "€"; 
						
						fenetreCloture.transacText.add(info);
						numeroTransac++;
						
						caisse.imprimerTicketFichier(stock, reduc, montantCarte, montantEspece, montantRenduEspece);
						caisse.getListeClient().clear();
						modele.removeAllElements();
						entrerArticle.setText("");
						entrerQuantite.setText("");
						prixTotal.setText("");
						prixTtc.setText("");
						tva.setText("");
						reducTotale.setText("");
						prixTtcReduc.setText("");
						valider.setEnabled(true);
						finaliser.setEnabled(false);
						entrerArticle.setEditable(true);
						entrerQuantite.setEditable(true);
						reduc5.setEnabled(false);
						reduc10.setEnabled(false);
						reduc25.setEnabled(false);
						reduc50.setEnabled(false);
						reduc70.setEnabled(false);
						bouton0.setEnabled(true);
						bouton1.setEnabled(true);
						bouton2.setEnabled(true);
						bouton3.setEnabled(true);
						bouton4.setEnabled(true);
						bouton5.setEnabled(true);
						bouton6.setEnabled(true);
						bouton7.setEnabled(true);
						bouton8.setEnabled(true);
						bouton9.setEnabled(true);
						reduc = 0;
						entrerArticle.setBackground(Color.white);
						entrerQuantite.setBackground(Color.white);
						montantRestant.setText("");
						espece.setEnabled(false);
						carte.setEnabled(false);
						paiementEspece.setEnabled(false);
					}
				
				}
				

			}
		});
		
		carte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				montantCarte = montant;
				montant = 0;
				montantRestant.setText(montant+"€");
				
			}
		});
		
		espece.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
				if (Double.parseDouble(paiementEspece.getText())>montant) {
					montantEspece = Double.parseDouble(paiementEspece.getText());
					montant = montant -  Double.parseDouble(paiementEspece.getText());
					montantRenduEspece = montant*-1;
					montant = 0;
				}
				else if (Double.parseDouble(paiementEspece.getText())<=montant) {
					montantEspece = Double.parseDouble(paiementEspece.getText());
					montant = montant -  Double.parseDouble(paiementEspece.getText());
				}
				
				} catch (NumberFormatException ex) {
					paiementEspece.setText("");
				}

				montantRestant.setText(montant+"€");
				paiementEspece.setText("");
			}
		});
		
		
		cloturer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fenetreCloture.setVisible(true);
				fenetreCloture.setLocationRelativeTo(getContentPane());
				modele.removeAllElements();
				entrerArticle.setText("");
				entrerQuantite.setText("");
				prixTotal.setText("");
				prixTtc.setText("");
				tva.setText("");
				reducTotale.setText("");
				prixTtcReduc.setText("");
				valider.setEnabled(true);
				finaliser.setEnabled(false);
				entrerArticle.setEditable(true);
				entrerQuantite.setEditable(true);
				reduc5.setEnabled(false);
				reduc10.setEnabled(false);
				reduc25.setEnabled(false);
				reduc50.setEnabled(false);
				reduc70.setEnabled(false);
				bouton0.setEnabled(true);
				bouton1.setEnabled(true);
				bouton2.setEnabled(true);
				bouton3.setEnabled(true);
				bouton4.setEnabled(true);
				bouton5.setEnabled(true);
				bouton6.setEnabled(true);
				bouton7.setEnabled(true);
				bouton8.setEnabled(true);
				bouton9.setEnabled(true);
				setVisible(false);
				reduc = 0;
				entrerArticle.setBackground(Color.white);
				entrerQuantite.setBackground(Color.white);
				
			}
		});
		
		fenetreCloture.reinitialiser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetreCloture.setVisible(false);
				setVisible(true);
				
				caisse.getListeClient().clear();
				numeroTransac = 1;
				fenetreCloture.CAProduitAutre = 0;
				fenetreCloture.CAProduitFrais = 0;
				fenetreCloture.chiffreAffaire = 0;
				fenetreCloture.chiffreAffaireProduit.setText("0€");
				fenetreCloture.chiffreAffaireProduitAutre.setText("0€");
				fenetreCloture.chiffreAffaireProduitFrais.setText("0€");
				fenetreCloture.nbProduitAutre.setText("0");
				fenetreCloture.nbProduitFrais.setText("0");
				fenetreCloture.produitTotal = 0;
				fenetreCloture.proportionProduitAutre.setText("-%");
				fenetreCloture.proportionProduitFrais.setText("-%");
				fenetreCloture.proportionProduitAutreCa.setText("-%");
				fenetreCloture.proportionProduitFraisCa.setText("-%");
				fenetreCloture.quantiteTotale.setText("0");
				fenetreCloture.infoTransac.setText("");
				fenetreCloture.modeleListeTransac.clear();
				fenetreCloture.nombreProduitAutre = 0;
				fenetreCloture.nombreProduitFrais = 0;
				fenetreCloture.transacText.clear();
				montantRestant.setText("");
			}
		});

		fenetreCloture.reinitialiserBis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetreCloture.setVisible(false);
				setVisible(true);
				
				caisse.getListeClient().clear();
				numeroTransac = 1;
				fenetreCloture.CAProduitAutre = 0;
				fenetreCloture.CAProduitFrais = 0;
				fenetreCloture.chiffreAffaire = 0;
				fenetreCloture.chiffreAffaireProduit.setText("0€");
				fenetreCloture.chiffreAffaireProduitAutre.setText("0€");
				fenetreCloture.chiffreAffaireProduitFrais.setText("0€");
				fenetreCloture.nbProduitAutre.setText("0");
				fenetreCloture.nbProduitFrais.setText("0");
				fenetreCloture.produitTotal = 0;
				fenetreCloture.proportionProduitAutre.setText("-%");
				fenetreCloture.proportionProduitFrais.setText("-%");
				fenetreCloture.proportionProduitAutreCa.setText("-%");
				fenetreCloture.proportionProduitFraisCa.setText("-%");
				fenetreCloture.quantiteTotale.setText("0");
				fenetreCloture.infoTransac.setText("");
				fenetreCloture.modeleListeTransac.clear();
				fenetreCloture.nombreProduitAutre = 0;
				fenetreCloture.nombreProduitFrais = 0;
				fenetreCloture.transacText.clear();
				montantRestant.setText("");
			}
		});

		
		reduc5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int code = 0;

				try {
					for (int i = 0; i <= modele.getSize(); i++) {
						if (listeArticle.isSelectedIndex(i)) {
							
							if (!modele.getElementAt(i).contains("%")) {
								Scanner scan = new Scanner(modele.getElementAt(i));
								code = Integer.parseInt(scan.findInLine("^[0-9]{8}"));
								modele.setElementAt("<html>"+modele.getElementAt(i) +"<br><i>Reduction : 5%   -"+deciFormat.format((stock.getListeStock().get(code).getPrix() * caisse.getListeClient().get(code).intValue()) * 0.05)+"€"+"</i></html>", i);
							}
						}
					}

					reduc = reduc + (stock.getListeStock().get(code).getPrix() * caisse.getListeClient().get(code).intValue()) * 0.05;
					reducTotale.setText(deciFormat.format(reduc) + "");

				} catch (NumberFormatException ex1) {
				}
				catch (NullPointerException ex2) {
				}
			}
		});
		reduc10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int code = 0;

				try {
					for (int i = 0; i <= modele.getSize(); i++) {
						if (listeArticle.isSelectedIndex(i)) {
							if (!modele.getElementAt(i).contains("%")) {
								Scanner scan = new Scanner(modele.getElementAt(i));
								code = Integer.parseInt(scan.findInLine("^[0-9]{8}"));
								modele.setElementAt("<html>"+modele.getElementAt(i) +"<br><i>Reduction : 10%   -"+deciFormat.format((stock.getListeStock().get(code).getPrix() * caisse.getListeClient().get(code).intValue()) * 0.1)+"€"+"</i></html>", i);
							}
						}
					}

					reduc = reduc + (stock.getListeStock().get(code).getPrix() * caisse.getListeClient().get(code).intValue()) * 0.1;
					reducTotale.setText(deciFormat.format(reduc) + "");

				} catch (NumberFormatException ex1) {
				}
				catch (NullPointerException ex2) {
				}
			}
		});
		reduc25.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int code = 0;

				try {
					for (int i = 0; i <= modele.getSize(); i++) {
						if (listeArticle.isSelectedIndex(i)) {
							if (!modele.getElementAt(i).contains("%")) {
								Scanner scan = new Scanner(modele.getElementAt(i));
								code = Integer.parseInt(scan.findInLine("^[0-9]{8}"));
								modele.setElementAt("<html>"+modele.getElementAt(i) +"<br><i>Reduction : 25%   -"+deciFormat.format((stock.getListeStock().get(code).getPrix() * caisse.getListeClient().get(code).intValue()) * 0.25)+"€"+"</i></html>", i);
							}
						}
					}

					reduc = reduc + (stock.getListeStock().get(code).getPrix() * caisse.getListeClient().get(code).intValue()) * 0.25;
					reducTotale.setText(deciFormat.format(reduc) + "");

				} catch (NumberFormatException ex1) {
				}
				catch (NullPointerException ex2) {
				}
			}
		});
		reduc50.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int code = 0;

				try {
					for (int i = 0; i <= modele.getSize(); i++) {
						if (listeArticle.isSelectedIndex(i)) {
							if (!modele.getElementAt(i).contains("%")) {
								Scanner scan = new Scanner(modele.getElementAt(i));
								code = Integer.parseInt(scan.findInLine("^[0-9]{8}"));
								modele.setElementAt("<html>"+modele.getElementAt(i) +"<br><i>Reduction : 50%   -"+deciFormat.format((stock.getListeStock().get(code).getPrix() * caisse.getListeClient().get(code).intValue()) * 0.5)+"€"+"</i></html>", i);
							}
						}
					}
						
					reduc = reduc + (stock.getListeStock().get(code).getPrix() * caisse.getListeClient().get(code).intValue()) * 0.5;
					reducTotale.setText(deciFormat.format(reduc) + "");

				} catch (NumberFormatException ex1) {
				}
				catch (NullPointerException ex2) {
				}
			}
		});
		reduc70.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int code = 0;

				try {
					for (int i = 0; i <= modele.getSize(); i++) {
						if (listeArticle.isSelectedIndex(i)) {
							if (!modele.getElementAt(i).contains("%")) {
								Scanner scan = new Scanner(modele.getElementAt(i));
								code = Integer.parseInt(scan.findInLine("^[0-9]{8}"));
								modele.setElementAt("<html>"+modele.getElementAt(i) +"<br><i>Reduction : 70%   -"+deciFormat.format((stock.getListeStock().get(code).getPrix() * caisse.getListeClient().get(code).intValue()) * 0.7)+"€"+"</i></html>", i);
							}
						}
					}

					reduc = reduc + (stock.getListeStock().get(code).getPrix() * caisse.getListeClient().get(code).intValue()) * 0.7;
					reducTotale.setText(deciFormat.format(reduc) + "");
				} catch (NumberFormatException ex1) {
				}
				catch (NullPointerException ex2) {
				}
			}
		});

		bouton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (entrerArticle.hasFocus()) {
					entrerArticle.setText(entrerArticle.getText() + "1");
				} else if (entrerQuantite.hasFocus()) {
					entrerQuantite.setText(entrerQuantite.getText() + "1");
				}
			}
		});
		bouton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (entrerArticle.hasFocus()) {
					entrerArticle.setText(entrerArticle.getText() + "2");
				} else if (entrerQuantite.hasFocus()) {
					entrerQuantite.setText(entrerQuantite.getText() + "2");
				}
			}
		});
		bouton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (entrerArticle.hasFocus()) {
					entrerArticle.setText(entrerArticle.getText() + "3");
				} else if (entrerQuantite.hasFocus()) {
					entrerQuantite.setText(entrerQuantite.getText() + "3");
				}
			}
		});
		bouton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (entrerArticle.hasFocus()) {
					entrerArticle.setText(entrerArticle.getText() + "4");
				} else if (entrerQuantite.hasFocus()) {
					entrerQuantite.setText(entrerQuantite.getText() + "4");
				}
			}
		});
		bouton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (entrerArticle.hasFocus()) {
					entrerArticle.setText(entrerArticle.getText() + "5");
				} else if (entrerQuantite.hasFocus()) {
					entrerQuantite.setText(entrerQuantite.getText() + "5");
				}
			}
		});
		bouton6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (entrerArticle.hasFocus()) {
					entrerArticle.setText(entrerArticle.getText() + "6");
				} else if (entrerQuantite.hasFocus()) {
					entrerQuantite.setText(entrerQuantite.getText() + "6");
				}
			}
		});
		bouton7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (entrerArticle.hasFocus()) {
					entrerArticle.setText(entrerArticle.getText() + "7");
				} else if (entrerQuantite.hasFocus()) {
					entrerQuantite.setText(entrerQuantite.getText() + "7");
				}
			}
		});
		bouton8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (entrerArticle.hasFocus()) {
					entrerArticle.setText(entrerArticle.getText() + "8");
				} else if (entrerQuantite.hasFocus()) {
					entrerQuantite.setText(entrerQuantite.getText() + "8");
				}
			}
		});
		bouton9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (entrerArticle.hasFocus()) {
					entrerArticle.setText(entrerArticle.getText() + "9");
				} else if (entrerQuantite.hasFocus()) {
					entrerQuantite.setText(entrerQuantite.getText() + "9");
				}
			}
		});
		bouton0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (entrerArticle.hasFocus()) {
					entrerArticle.setText(entrerArticle.getText() + "0");
				} else if (entrerQuantite.hasFocus()) {
					entrerQuantite.setText(entrerQuantite.getText() + "0");
				}
			}
		});

		paiementEspece.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (paiementEspece.getText().equals("Entrez un montant ici")) {
					paiementEspece.setText("");
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (paiementEspece.getText().equals("Entrez un montant ici")) {
					paiementEspece.setText("");
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (paiementEspece.getText().equals("Entrez un montant ici")) {
					paiementEspece.setText("");
				}
			}
		});
		
	}
}
