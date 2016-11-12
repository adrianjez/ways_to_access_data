package model;

@Deprecated
public class RejestrKsiazkek {
	private long id;
	private int userID;
	private int ksiazkaID;
	private long dataWyporzyczenia;
	private long dataZwrotu;
	private long terminZwrotu;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getKsiazkaID() {
		return ksiazkaID;
	}
	public void setKsiazkaID(int ksiazkaID) {
		this.ksiazkaID = ksiazkaID;
	}
	public long getDataWyporzyczenia() {
		return dataWyporzyczenia;
	}
	public void setDataWyporzyczenia(long dataWyporzyczenia) {
		this.dataWyporzyczenia = dataWyporzyczenia;
	}
	public long getDataZwrotu() {
		return dataZwrotu;
	}
	public void setDataZwrotu(long dataZwrotu) {
		this.dataZwrotu = dataZwrotu;
	}
	public long getTerminZwrotu() {
		return terminZwrotu;
	}
	public void setTerminZwrotu(long terminZwrotu) {
		this.terminZwrotu = terminZwrotu;
	}
	
}
