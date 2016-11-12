package model;

public class Ksiazka {
	
	
	protected int id;
	protected String tytul;
	protected String ISBN;
	protected int rokWydania;
	protected String opis;
	protected String URLOkladki;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTytul() {
		return tytul;
	}
	public void setTytul(String tytul) {
		this.tytul = tytul;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public int getRokWydania() {
		return rokWydania;
	}
	public void setRokWydania(int rokWydania) {
		this.rokWydania = rokWydania;
	}
	public String getURLOkladki() {
		return URLOkladki;
	}
	public void setURLOkladki(String uRLOkladki) {
		URLOkladki = uRLOkladki;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
}
