package metier;

public abstract class Produit {

	private String nom;
	private int code;
	private double prix;
	
	public Produit(String nom, int code, double prix) {
		this.nom=nom;
		this.code=code;
		this.prix=prix;
	}
	
	public String getNom() {
		return (nom);
	}
	
	public int getCode() {
		return (code);
	}
	
	public double getPrix() {
		return (prix);
	}
	
	public abstract double getTaxe();
	
	public abstract double getprixTaxe();
}