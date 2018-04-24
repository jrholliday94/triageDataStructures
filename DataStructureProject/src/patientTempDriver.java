import javax.swing.JFrame;
import javax.swing.JPanel;


public class patientTempDriver {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Input patient information");
		JPanel panel = new PatientEntry();

		frame.add(panel);
		frame.setSize(560, 225);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
