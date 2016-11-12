package model;

public class ExtendedBook extends Ksiazka {
	
	/**
	 * This field says how many users have already taken this book is greater than 0
	 * if is equal 0 -> is possible to lend it
	 */
	private int currentUsers;

	public int getCurrentUsers() {
		return currentUsers;
	}

	public void setCurrentUsers(int currentUsers) {
		this.currentUsers = currentUsers;
	}
	
	public boolean isBookTaken(){
		return currentUsers > 0;
	}

}
