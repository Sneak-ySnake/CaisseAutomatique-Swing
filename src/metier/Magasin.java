package metier;

public class Magasin {

	Stock stockMagasin = new Stock();
	Caisse caisseMagasin = new Caisse();
	
	public Magasin(Stock stock) {
		stockMagasin = stock;
	}
	
	/*public void demarrerTransaction() {
		caisseMagasin.scannerArticle(stockMagasin);
	}*/

	public Stock getStockMagasin() {
		return stockMagasin;
	}

	public void setStockMagasin(Stock stockMagasin) {
		this.stockMagasin = stockMagasin;
	}

	public Caisse getCaisseMagasin() {
		return caisseMagasin;
	}

	public void setCaisseMagasin(Caisse caisseMagasin) {
		this.caisseMagasin = caisseMagasin;
	}
	
	
}
