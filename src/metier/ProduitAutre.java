package metier;

public class ProduitAutre extends Produit {

	private final static double TAUX = 0.05d;
	
	public ProduitAutre(String nom, int code, double prix) {
		super(nom, code, prix);
	}
	
	public double getTaxe() {
		return (getPrix()*TAUX);
	}
	
	public double getprixTaxe() {
		return (getPrix() * (1 + TAUX));
	}
}