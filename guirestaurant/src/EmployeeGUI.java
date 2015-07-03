import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeGUI extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private JTextField empIDText = new JTextField(10);
	private JTextField nameText = new JTextField(10);
	private JTextField rateText = new JTextField(10);
	private JTextArea output = new JTextArea(8, 30);
	private JButton clearRecord = new JButton("clear payroll");
	private JButton addRecord = new JButton("add to record");
	String[] positions = { " ", "server", "bartender", "cook" };
	private JComboBox positionSelect = new JComboBox(positions);

	NewEmployee programmer = new NewEmployee();

	public EmployeeGUI() throws ClassNotFoundException, SQLException {
		getContentPane().setLayout(new FlowLayout());
		barUI.sqlConnection();
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
		JPanel idPanel = new JPanel();
		JLabel askEmpID = new JLabel("Employee ID >>");
		idPanel.add(askEmpID);
		idPanel.add(empIDText);
		dataPanel.add(idPanel);

		JPanel namesPanel = new JPanel();
		JLabel askName = new JLabel("Employee Name >>");
		namesPanel.add(askName);
		namesPanel.add(nameText);
		dataPanel.add(namesPanel);

		JPanel positionIDPanel = new JPanel();
		JLabel askPosition = new JLabel("Employee Position >>");
		positionIDPanel.add(askPosition);
		positionIDPanel.add(positionSelect);
		dataPanel.add(positionIDPanel);

		JPanel ratePanel = new JPanel();
		JLabel askRate = new JLabel("Hourly Rate >>");
		ratePanel.add(askRate);
		ratePanel.add(rateText);
		dataPanel.add(ratePanel);

		JPanel unionPanel = new JPanel();
		dataPanel.add(unionPanel);
		getContentPane().add(dataPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addRecord);
		buttonPanel.add(clearRecord);
		getContentPane().add(buttonPanel);

		getContentPane().add(output);
		addRecord.addActionListener(this);
		clearRecord.addActionListener(this);
	}

	public void actionPerformed(ActionEvent aE) {
		String empID;
		String name;
		String rateStr;
		String positionID;
		double rate;

		Object buttonPressed = aE.getSource();
		if (buttonPressed == clearRecord) {
			output.setText("Record is cleared ");
			empIDText.setText("");
			nameText.setText("");
			rateText.setText("");
			positionSelect.setSelectedIndex(0);
		} else if (buttonPressed == addRecord) {
			empID = empIDText.getText();
			name = nameText.getText();
			positionID = (String) positionSelect.getSelectedItem();
			String positionChoice = "11234";
			if ((String) positionSelect.getSelectedItem() == "server")
				positionChoice = "11234";
			else if ((String) positionSelect.getSelectedItem() == "bartender")
				positionChoice = "11240";
			else if ((String) positionSelect.getSelectedItem() == "cook")
				positionChoice = "11246";
			rateStr = rateText.getText().trim();
			rate = Double.parseDouble(rateStr);
			try {
				String sql = "INSERT INTO employee(employeeID,name,positionID) VALUES("
						+ "'"
						+ empID
						+ "'"
						+ ","
						+ "'"
						+ name
						+ "'"
						+ ","
						+ "'" + positionChoice + "'" + ");";
				Statement stmt = barUI.conn.createStatement();
				stmt.executeUpdate(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
			programmer.setPayData(empID, name, positionID, rate);

			output.setText(programmer.toString());

			empIDText.setText("");
			nameText.setText("");
			rateText.setText("");
			positionSelect.setSelectedIndex(0);
		}
	}

	public void itemStateChanged(ItemEvent iE) {

	}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		EmployeeGUI pay = new EmployeeGUI();
		pay.setSize(350, 430);
		pay.setVisible(true);
		pay.setTitle("Employee Administration");
		pay.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

}