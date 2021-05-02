package metier;
import java.util.HashMap;

public class Stock {

	protected HashMap<Integer, Produit> listeStock = new HashMap<Integer, Produit>();
	
	public void ajouterProduit(Produit p) {
		listeStock.put(p.getCode(), p);
	}
	
	public void retirerProduit(Produit p) {
		listeStock.remove(p.getCode());
	}
	
	public Produit verifierArticle(int codeSaisi) {
		if (listeStock.containsKey(codeSaisi)) {
			return (listeStock.get(codeSaisi));
		}
		else return (null);
	}

	public HashMap<Integer, Produit> getListeStock() {
		return listeStock;
	}
}