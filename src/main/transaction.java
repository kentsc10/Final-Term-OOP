package main;

public class transaction {
	protected int transactionID;
	protected String UserEmail;
	protected int qty;
	protected String FishID;
	
	public transaction(int transactionID, String userEmail, int qty, String fishID) {
		super();
		this.transactionID = transactionID;
		UserEmail = userEmail;
		this.qty = qty;
		FishID = fishID;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getFishID() {
		return FishID;
	}
	public void setFishID(String fishID) {
		FishID = fishID;
	}

}
