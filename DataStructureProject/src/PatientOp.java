/**
 * @author Jared Holliday
 *
 */
public class PatientOp {

	private int time;
	private double cost;
	private String description;

	// Patient operations will be entered with estimated time and cost.
	/**
	 * @param description
	 * @param time
	 * @param cost
	 */
	public PatientOp(String description, int time, double cost) {
		this.description = description;
		this.time = time;
		this.cost = cost;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.description;
	}

}
