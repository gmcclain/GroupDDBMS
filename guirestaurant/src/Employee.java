public class Employee {
	// Data fields
	protected String empID;
	protected String name;
	protected String positionID;
	protected double rate;

	// Methods
	public Employee() {
	}

	public Employee(String s, String h, String p, double r) {
		empID = s;
		name = h;
		positionID = p;
		rate = r;
	}

	public void setPayData(double r) {
		rate = r;
	}

	// Shows employee information.
	public String toString() {
		return "Employee ID is " + empID + "\n" + "Employee name is " + name
				+ "\n" + "Position ID is " + positionID + "\n"
				+ "Hourly rate is $" + rate + "\n";
	}

}
