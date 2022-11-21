import javax.swing.*;
import java.util.ArrayList;

public class UserInterface {

    public String getInput(String message){
        return JOptionPane.showInputDialog(message);
    }

    public Integer getIntInput(String message){
        Integer temp = null;
        try {
            temp = Integer.parseInt(JOptionPane.showInputDialog(message));
        }
        catch (Exception e){
            givePrompt("Invalid Input");
            return getIntInput(message);
        }
        return temp;
    }

    public void givePrompt(String message) throws BlankOutputException{
    	
    	if (message == null || message == "") {
    		throw new BlankOutputException();
    	}
    	
        JOptionPane.showMessageDialog(new JFrame(), message);
        
    }

    public String viewRestaurants(ArrayList<Restaurant>restaurants){
        String str = "";

        for (int i = 0; i < restaurants.size(); i++) {
            str += i + " --> " + restaurants.get(i).getRestaurantName() + "\n";
        }

        return str;
    }

    public String viewCustomers(ArrayList<Customer>customers){
        String str = "";

        for (int i = 0; i < customers.size(); i++) {
            str += "User " + i + ":\n";
            str += "    User Name: " + customers.get(i).getUserName() + "\n";
            str += "    Password:  " + customers.get(i).getPassword() + "\n";
        }

        return str;

    }
}
