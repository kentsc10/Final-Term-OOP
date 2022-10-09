package model;

public abstract class fish {
	protected String fishID;
	protected String fishName;
	protected double fishSize;
	protected int fishSpeed;
	
	
	public fish(String fishID, String fishName, double fishSize, int fishSpeed) {
		super();
		this.fishID = fishID;
		this.fishName = fishName;
		this.fishSize = fishSize;
		this.fishSpeed = fishSpeed;
	}


	public String getFishID() {
		return fishID;
	}


	public void setFishID(String fishID) {
		this.fishID = fishID;
	}


	public String getFishName() {
		return fishName;
	}


	public void setFishName(String fishName) {
		this.fishName = fishName;
	}


	public double getFishSize() {
		return fishSize;
	}


	public void setFishSize(double fishSize) {
		this.fishSize = fishSize;
	}


	public int getFishSpeed() {
		return fishSpeed;
	}


	public void setFishSpeed(int fishSpeed) {
		this.fishSpeed = fishSpeed;
	}


	public abstract void printing();
}
