import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.File;

public class masterUI extends JFrame implements ActionListener, ItemListener {

	private JButton barUI = new JButton("Bar Orders");
	private JButton drinkTester = new JButton("Drink Entry");
	private JButton employeeGUI = new JButton("Employee Data");
	private JButton restaurantTester = new JButton("Food Entry");
	private JButton seatingUI = new JButton("Seating Entry");
	private JButton kitchenUI = new JButton("Kitchen Orders");
	private JLabel sites2 = new JLabel("Site Selector: ");
	String[] sites = { " ", "Pangaea", "Strabo", "Plato" };
	private JComboBox siteSelector = new JComboBox(sites);
	String selectedSite = "";
	NewEmployee programmer = new NewEmployee();

	public masterUI() throws ClassNotFoundException {
		getContentPane().setLayout(new FlowLayout());

		add(barUI);

		JPanel namesPanel = new JPanel();
		namesPanel.add(drinkTester);
		add(namesPanel);

		JPanel positionIDPanel = new JPanel();
		positionIDPanel.add(employeeGUI);
		add(positionIDPanel);

		JPanel ratePanel = new JPanel();
		ratePanel.add(restaurantTester);
		add(ratePanel);

		add(seatingUI);
		add(kitchenUI);
		add(sites2);
		add(siteSelector);
		barUI.addActionListener(this);
		drinkTester.addActionListener(this);
		employeeGUI.addActionListener(this);
		restaurantTester.addActionListener(this);
		seatingUI.addActionListener(this);
		kitchenUI.addActionListener(this);
	}

	public void actionPerformed(ActionEvent aE) {

		Object buttonPressed = aE.getSource();
		try {
			selectedSite = (String) siteSelector.getSelectedItem();
			if (buttonPressed == barUI) {
				barUI b = new barUI();
				b.createAndShowGUI();
			} else if (buttonPressed == drinkTester) {
				File inputFile = new File("max-soha2");
				RestaurantFrame guiFrame = new RestaurantFrame(inputFile);

			} else if (buttonPressed == employeeGUI) {
				EmployeeGUI pay = new EmployeeGUI();
				pay.setSize(350, 430);
				pay.setVisible(true);
				pay.setTitle("Employee Administration");
				pay.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});

			} else if (buttonPressed == restaurantTester) {
				File inputFile = new File("max-soha");
				RestaurantFrame guiFrame = new RestaurantFrame(inputFile);

			} else if (buttonPressed == seatingUI) {
				seatingUI seatingChart = new seatingUI();
			} else if (buttonPressed == kitchenUI) {
				KitchenUI k = new KitchenUI();
				k.createAndShowGUI();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void itemStateChanged(ItemEvent iE) {

	}

	public static void main(String[] args) throws ClassNotFoundException {
		masterUI pay = new masterUI();
		pay.pack();
		pay.setVisible(true);
		pay.setTitle("Restaurant Interface");
		pay.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}