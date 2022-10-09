package model;

public class freshWater extends fish {
	protected int fishAlgae;
	

	public freshWater(String fishID, String fishName, double fishSize, int fishSpeed, int fishAlgae) {
		super(fishID, fishName, fishSize, fishSpeed);
		this.fishAlgae = fishAlgae;
	}

	public int getFishAlgae() {
		return fishAlgae;
	}

	public void setFishAlgae(int fishAlgae) {
		this.fishAlgae = fishAlgae;
	}

	@Override
	public void printing() {
		// TODO Auto-generated method stub

	}

}
