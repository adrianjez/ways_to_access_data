package model;

import java.util.Date;

public class BookTransaction {
	
	private int id;
	private User osoba;
	private Date data_wydania;
	private Date data_zwrotu;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData_zwrotu() {
		return data_zwrotu;
	}
	public void setData_zwrotu(Date data_zwrotu) {
		this.data_zwrotu = data_zwrotu;
	}
	public Date getData_wydania() {
		return data_wydania;
	}
	public void setData_wydania(Date data_wydania) {
		this.data_wydania = data_wydania;
	}
	public User getOsoba() {
		return osoba;
	}
	public void setOsoba(User osoba) {
		this.osoba = osoba;
	}
}
