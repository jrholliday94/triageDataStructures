import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class PatientEntry extends JPanel {
	private JTextField enteredName;
	private JTextField enteredAge;
	private JTextField enteredWeight;
	private JTextField enteredHeight;
	LinkedList<Patient> patientList = new LinkedList<Patient>();

	/**
	 * Create the panel.
	 */
	public PatientEntry() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Patient name");
		lblNewLabel.setBounds(10, 11, 109, 14);
		add(lblNewLabel);

		JLabel lblPatientAge = new JLabel("Patient age");
		lblPatientAge.setBounds(10, 36, 109, 14);
		add(lblPatientAge);

		JLabel lblPatientWeight = new JLabel("Patient weight");
		lblPatientWeight.setBounds(10, 61, 109, 14);
		add(lblPatientWeight);

		JLabel lblPatientHeight = new JLabel("Patient height");
		lblPatientHeight.setBounds(10, 86, 109, 14);
		add(lblPatientHeight);

		JLabel lblPatientBill = new JLabel("Patient bill");
		lblPatientBill.setBounds(10, 111, 109, 14);
		add(lblPatientBill);

		enteredName = new JTextField();
		enteredName.setBounds(129, 11, 86, 20);
		add(enteredName);
		enteredName.setColumns(10);

		enteredAge = new JTextField();
		enteredAge.setColumns(10);
		enteredAge.setBounds(129, 36, 86, 20);
		add(enteredAge);

		enteredWeight = new JTextField();
		enteredWeight.setColumns(10);
		enteredWeight.setBounds(129, 61, 86, 20);
		add(enteredWeight);

		enteredHeight = new JTextField();
		enteredHeight.setColumns(10);
		enteredHeight.setBounds(129, 86, 86, 20);
		add(enteredHeight);

		JLabel billLabel = new JLabel("");
		billLabel.setBounds(129, 111, 86, 20);
		Border border = BorderFactory.createLineBorder(Color.black, 1);
		billLabel.setBorder(border);
		add(billLabel);

		JList<PatientOp> patientOpList = new JList<PatientOp>();
		patientOpList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		patientOpList.setBackground(Color.WHITE);
		patientOpList.setBounds(397, 10, 127, 121);
		add(patientOpList);

		JList patientQueue = new JList();
		patientQueue.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				// On selection change load up operation list of selected patient
				String selectedName = (String) patientQueue.getSelectedValue();

				// Check linkedlist to match patient name
				for (int i = 0; i < patientList.size(); i++) {

					// If name matches, retrieve queue of operations for selected patient
					if (patientList.get(i).getName().toLowerCase().equals(selectedName.toLowerCase())) {
						// Create new queue to hold operations
						Queue<PatientOp> operations = patientList.get(i).getOperations();

						DefaultListModel listModel = new DefaultListModel();

						// Iterate through operation queue
						for (Object PatientOp : operations) {
							// For some reason only .tostring works in here.. Works for my purposes though.
							listModel.addElement(PatientOp.toString());

						}

						// Refresh JList contents
						patientOpList.setModel(listModel);
					}
				}

			}
		});
		patientQueue.setBackground(new Color(255, 255, 255));
		patientQueue.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		patientQueue.setBounds(225, 10, 150, 121);
		add(patientQueue);

		JButton btnAddPatient = new JButton("Add Patient");
		btnAddPatient.addActionListener(new ActionListener() {

			// on click create patient if all fields are valid
			// then add to linked list of patients
			// then refresh jlist with patient names
			public void actionPerformed(ActionEvent e) {
				int age = 0;
				int weight = 0;
				int height = 0;
				String name = enteredName.getText();
				boolean correct = true;

				if (name.trim().isEmpty() || name.length() < 1) {
					correct = false;
					String message = "The name entered is not in a correct format.";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
					correct = false;
				}

				// parse age, don't create object if not an int
				try {
					age = Integer.parseInt(enteredAge.getText());
				} catch (NumberFormatException ex) {
					String message = "The age entered is not in a correct format.";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
					correct = false;
				}

				// parse weight
				try {
					weight = Integer.parseInt(enteredWeight.getText());
				} catch (NumberFormatException ex) {
					String message = "The weight entered is not in a correct format.";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
					correct = false;
				}

				// parse height
				try {
					height = Integer.parseInt(enteredHeight.getText());
				} catch (NumberFormatException ex) {
					String message = "The height entered is not in a correct format.";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
					correct = false;
				}

				if (correct) {
					Patient enteredPatient = new Patient(name, age, weight, height);
					patientList.add(enteredPatient);

					DefaultListModel listModel = new DefaultListModel();

					for (int i = 0; i < patientList.size(); i++) {
						listModel.addElement(patientList.get(i).getName());
					}

					patientQueue.setModel(listModel);

				}

			}
		});
		btnAddPatient.setBounds(10, 139, 205, 23);
		add(btnAddPatient);

		JButton btnOperateOnPatient = new JButton("Operate");
		btnOperateOnPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Remove head of operation list queue
				String selectedName = (String) patientQueue.getSelectedValue();

				// Check linkedlist to match patient name
				for (int i = 0; i < patientList.size(); i++) {

					// If name matches, retrieve queue of operations for selected patient
					if (patientList.get(i).getName().toLowerCase().equals(selectedName.toLowerCase())) {
						// Create new queue to hold operations
						Queue<PatientOp> operations = patientList.get(i).getOperations();

						DefaultListModel listModel = new DefaultListModel();

						// Remove operations list head
						operations.remove();

						// Iterate through operation queue
						for (Object PatientOp : operations) {
							// For some reason only .tostring works in here.. Works for my purposes though.
							listModel.addElement(PatientOp.toString());

						}

						// Refresh JList contents
						patientOpList.setModel(listModel);
					}
				}

			}

		});
		btnOperateOnPatient.setBounds(396, 139, 127, 23);

		add(btnOperateOnPatient);

		JButton btnAddOperation = new JButton("Add Operation");
		btnAddOperation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String selection = (String) patientQueue.getSelectedValue();

				Patient foundPatient = null;
				for (int i = 0; i < patientList.size(); i++) {
					String name = patientList.get(i).getName();
					if (name.equals(selection)) {
						foundPatient = patientList.get(i);
					}
				}

				// Open operation entry window if patient is valid
				if (foundPatient != null) {
					JFrame frame = new JFrame("Input operation information");
					JPanel panel = new OperationEntry(foundPatient);
					frame.getContentPane().add(panel);
					frame.setSize(410, 170);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				} else {
					String message = "Error. Patient not found.";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAddOperation.setBounds(225, 139, 150, 23);
		add(btnAddOperation);

	}
}
