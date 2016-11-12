package model;

public class Wydawnictwo {
	
	public static String TABLE_NAME = "PUBLISHERS";
	
	private int id;
	private String nazwa;
	private String adres;
	private String telefon;
	private String email;
	private String stronaWWW;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStronaWWW() {
		return stronaWWW;
	}
	public void setStronaWWW(String stronaWWW) {
		this.stronaWWW = stronaWWW;
	}
	
	
}
