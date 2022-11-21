import java.util.ArrayList;

public class LogIn {

    FileHandling fileHandling = new FileHandling();
    ArrayList<Customer> customers = fileHandling.getCustomerList("Customers.csv");
    ArrayList<Restaurant> restaurants = fileHandling.getRestaurantList("Restaurants.csv");
    ArrayList<Rider> riders = fileHandling.getRiderList("Riders.csv");
    ArrayList<Admin> admins = fileHandling.getAdminList("Admins.csv");
    ArrayList<Order> orders = new ArrayList<>();

	LogIn(){
        User user = login();
        int loggedInUserType = getAccountType(user);
        user.handleUser(loggedInUserType, customers, riders, orders, restaurants, admins);
	}
	
	private User login(){
	        User user = null;

	        ArrayList<User> users = new ArrayList<>();

	        users.addAll(customers);
	        users.addAll(restaurants);
	        users.addAll(riders);
	        users.addAll(admins);

	        UserInterface userInterface = new UserInterface();

	        String userName = userInterface.getInput("Enter User Name: ");
	        String password = userInterface.getInput("Enter Password: ");

	        for (int i = 0; i < users.size(); i++) {
	            if(users.get(i).userMatches(userName,password)){
	                userInterface.givePrompt("Log In Successful");
	                user = users.get(i);
	                break;
	            }
	        }

	        if (user == null){
	            userInterface.givePrompt("No Such User was found");
	        }
	        
	        return user;

	}
	

	private int getAccountType(User user){
        int accountType = 0;
        if(user instanceof Customer){
            accountType = 1;
        }
        else if(user instanceof Restaurant){
            accountType = 2;
        }
        else if(user instanceof Rider){
            accountType = 3;
        }
        else if(user instanceof Admin){
            accountType = 4;
        }

        return accountType;
    }

	
}
