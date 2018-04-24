import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jared Holliday
 *
 */
public class Patient {

	private String name;
	private int age;
	private double weight;
	private double height;
	private double bill;
	Queue<PatientOp> operations = new LinkedList<PatientOp>();

	// Only one constructor, fields could be guessed by nurse if pt doesn't know or
	// is unconscious
	/**
	 * @param name
	 * @param age
	 * @param weight
	 * @param height
	 */
	public Patient(String name, int age, double weight, double height) {
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.height = height;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @return the bill
	 */
	public double getBill() {
		return bill;
	}

	/**
	 * @return the operations
	 */
	public Queue<PatientOp> getOperations() {
		return operations;
	}

	// take operation object and add to patient's linked list
	public void addOperation(PatientOp op) {
		this.operations.add(op);
	}

	// remove operation if op is performed and add to running total of bill
	public void performOperation(PatientOp op) {
		this.operations.remove(op);
		this.bill = this.bill + op.getCost();
	}

	// remove operation if pt no longer needs. 2 methods because first one bills
	public void removeOperation(PatientOp op) {
		this.operations.remove(op);

	}

}
