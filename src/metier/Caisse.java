package metier;
import java.util.HashMap;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.text.DecimalFormat;

public class Caisse {

	Map<Integer, Integer> listeClient = new HashMap<Integer, Integer>();
	
	Scanner scan = new Scanner(System.in);
	Scanner continuerScan = new Scanner (System.in);
	DecimalFormat deciFormat = new DecimalFormat("0.00");
	
	/////////////// METHODES ////////////////
	
	public boolean scannerArticle(Stock stock, int code, int quantite) {
		int codeSaisi = code;
		int quantiteSaisi = quantite;
		boolean valider=false;
		Produit p;

		p = stock.verifierArticle(codeSaisi);
		if (p!=null) {
			if (!listeClient.containsKey(p.getCode())) {
				listeClient.put(p.getCode(), quantiteSaisi);
				valider=true;
			}
			return valider;
		}
		else  {
			valider=false;
			return valider;
		}
	}
	
	
	public void imprimerTicketFichier(Stock stock, double reduction, double montantCarte, double montantEspece, double especeRendu) {
		try {
			
		File file = new File("ticket.txt");
		PrintStream print = new PrintStream(file);
		System.setOut(print);
			
		double prixTotal=0;
		double prixTaxe=0;
		double tva=0;
		int quantiteTotale=0;
		
		System.out.println("Article    Quantité    Prix\n");
		
		for (int key : listeClient.keySet()) {
			prixTotal = prixTotal + stock.listeStock.get(key).getPrix() * listeClient.get(key).intValue();
			prixTaxe = prixTaxe + stock.listeStock.get(key).getprixTaxe() * listeClient.get(key).intValue();
			tva = tva + stock.listeStock.get(key).getTaxe() * listeClient.get(key).intValue();
			quantiteTotale = quantiteTotale + listeClient.get(key).intValue();
			System.out.println(stock.listeStock.get(key).getNom()+"    "+listeClient.get(key).intValue()+"    "+deciFormat.format(stock.listeStock.get(key).getPrix())+"€");
		}
		System.out.println("_______________________\nTotal : "+deciFormat.format(prixTotal)+"€\n_______________________\n");
		System.out.println("TVA :    "+deciFormat.format(tva)+"€\nPrix avec TVA :    "+deciFormat.format(prixTaxe)+"€");
		System.out.println("Reduction :    "+reduction+"€\nPrix réduit :    "+deciFormat.format(prixTaxe-reduction)+"€");
		System.out.println("Payé par carte : "+deciFormat.format(montantCarte)+"€");
		System.out.println("Payé par espèce : "+deciFormat.format(montantEspece)+"€");
		System.out.println("Espèce rendu : "+deciFormat.format(especeRendu)+"€");
		System.out.println("_______________________\n        "+quantiteTotale+" Articles\n_______________________");
		System.out.println("Date : "+LocalDate.now()+"    "+LocalTime.now());
		
		} catch (IOException e) {
	        e.printStackTrace();
		}
	}

	public Map<Integer, Integer> getListeClient() {
		return listeClient;
	}

}