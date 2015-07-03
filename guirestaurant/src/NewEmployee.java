public class NewEmployee extends Employee {
   public NewEmployee() {
   }

   public NewEmployee(String ID, String h, String p, double r) {
     super(ID, h, p, r);
   }

   public void setPayData(String ID, String h, String p, double r) {
     empID = ID;
     name = h;
     positionID = p;
     rate = r;
   }


	public String toString() {
		String result = "Employee ID is " + empID + "\n" +
				"Employee name is " + name + "\n" +
				"Position ID is " + positionID + "\n" +
		       "Hourly rate is $" + rate + "\n";
    return result;
	}
}