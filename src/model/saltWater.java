package model;

public class saltWater extends fish {
	protected String fishDepth;

	public saltWater(String fishID, String fishName, double fishSize, int fishSpeed, String fishDepth) {
		super(fishID, fishName, fishSize, fishSpeed);
		this.fishDepth = fishDepth;
	}

	public String getFishDepth() {
		return fishDepth;
	}

	public void setFishDepth(String fishDepth) {
		this.fishDepth = fishDepth;
	}

	@Override
	public void printing() {
		// TODO Auto-generated method stub
		
			
	}

}
