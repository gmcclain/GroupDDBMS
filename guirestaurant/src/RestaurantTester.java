import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class RestaurantTester {

	public static void main(String[] args) throws FileNotFoundException {

		try {

			File inputFile = new File("max-soha");
			RestaurantFrame guiFrame = new RestaurantFrame(inputFile);
			barUI.sqlConnection();
		}

		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error! Menu File not found!",
					"Please reinput", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

}
