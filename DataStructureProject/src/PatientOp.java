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
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

}
