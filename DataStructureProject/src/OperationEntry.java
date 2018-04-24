import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OperationEntry extends JPanel {
	private JTextField operationDesc;
	private JTextField operationTime;
	private JTextField operationCost;

	/**
	 * Create the panel.
	 */
	public OperationEntry(Patient opPatient) {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Operation Description");
		lblNewLabel.setBounds(10, 11, 134, 14);
		add(lblNewLabel);

		JLabel lblEstimatedTime = new JLabel("Estimated Time");
		lblEstimatedTime.setBounds(10, 36, 134, 14);
		add(lblEstimatedTime);

		JLabel lblOpertationCost = new JLabel("Operation Cost");
		lblOpertationCost.setBounds(10, 61, 134, 14);
		add(lblOpertationCost);

		operationDesc = new JTextField();
		operationDesc.setBounds(188, 11, 202, 20);
		add(operationDesc);
		operationDesc.setColumns(10);

		operationTime = new JTextField();
		operationTime.setBounds(188, 36, 202, 20);
		add(operationTime);
		operationTime.setColumns(10);

		operationCost = new JTextField();
		operationCost.setBounds(188, 61, 202, 20);
		add(operationCost);
		operationCost.setColumns(10);

		JButton btnAddOp = new JButton("Add");
		btnAddOp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int opTime = 0;
				double opCost = 0;
				String opDesc = operationDesc.getText();
				boolean correct = true;

				// Parse opTime
				try {
					opTime = Integer.parseInt(operationTime.getText());
				} catch (NumberFormatException ex) {
					String message = "The time entered is not in a correct format.";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
					correct = false;
				}
				// Parse cost
				try {
					opCost = Double.parseDouble(operationCost.getText());
				} catch (NumberFormatException ex) {
					String message = "The operation cost entered is not in a correct format.";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
					correct = false;
				}

				// Create operation if parsed correctly
				if (correct) {
					PatientOp addedOp = new PatientOp(opDesc, opTime, opCost);
					opPatient.addOperation(addedOp);
					operationTime.setText("");
					operationDesc.setText("");
					operationCost.setText("");
					System.out.println("Operation added");
					System.exit(0);
				}

			}
		});
		btnAddOp.setBounds(188, 90, 202, 23);
		add(btnAddOp);
	}
}
