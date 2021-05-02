package metier;

public class ProduitFrais extends Produit {

	private final static double TAUX = 0.2d;
	
	public ProduitFrais(String nom, int code, double prix) {
		super(nom, code, prix);
	}
	
	public double getTaxe() {
		return (getPrix()*TAUX);
	}
	
	public double getprixTaxe() {
		return (getPrix() * (1 + TAUX));
	}
}