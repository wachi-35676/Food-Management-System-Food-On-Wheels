import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SignUp {

public SignUp() {}

public int signUpUser() {
	UserInterface userInterface = new UserInterface();
	
	String name = getUniqueUserName();
	String passWord = userInterface.getInput("Password:");
	
	int accountType = getAccountType();
	switch(accountType) {
		case 1: {
			  SignupCustomer(name,passWord);   
			 break;
		}
		case 2:{
			SignupResturant(name,passWord);
			break;
		}
		case 3 :{
			SignupRider(name,passWord);
		}
	}
	return accountType;
}

private String getUniqueUserName() {
	UserInterface userInterface = new UserInterface();
	FileHandling fh = new FileHandling();
	ArrayList<User> users = new ArrayList<>();

    users.addAll(fh.getCustomerList("Customers.csv"));
    users.addAll(fh.getRestaurantList("Restaurants.csv"));
    users.addAll(fh.getRiderList("Riders.csv"));
    users.addAll(fh.getAdminList("Admins.csv"));
    
    boolean uniqueUser;
	
	String userName;
    
    do {
    	uniqueUser = true;
    	userName = userInterface.getInput("User Name: ");
    	
    	for (int i = 0; i < users.size(); i++) {
			if (userName.equals(users.get(i).getUserName())) {
				userInterface.givePrompt("User Name Already Exists!");
				uniqueUser = false;
			}
		}
    	
    }while(!uniqueUser);
	
	
	
	return userName;
}

private Rider SignupRider(String name, String passWord2) {
	
	FileHandling fh = new FileHandling();
	String approve = "No";
	Rider rider = new Rider(name, passWord2, approve);
	ArrayList <Rider> r = new ArrayList<>();
	r = fh.getRiderList("Riders.csv");
	r.add(rider);
	fh.setRiderList("Riders.csv", r);

	return rider;
}

private Restaurant SignupResturant(String name, String passWord2) {
	String File = name+".csv";
	UserInterface userIterface = new UserInterface();
	File restfile = new File(File);
	try {
		restfile.createNewFile();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	FileHandling fh = new FileHandling();
	Restaurant rest = new Restaurant(name,passWord2,File,userIterface.getInput("Enter Restaurant name"));
	ArrayList<Restaurant> r = new ArrayList<>();
	r = fh.getRestaurantList("Restaurants.csv");
	r.add(rest);
	fh.setRestaurantList("Restaurants.csv", r);

	return rest;
}

private int getAccountType() {

	UserInterface userIterface = new UserInterface();
	
	int input = userIterface.getIntInput("[1] for Coustomer\n[2] for Resturant\n[3]for Roder");
	if(input > 3 || input < 1 ) {
		userIterface.givePrompt("Please Select the Correct Option...");
		return getAccountType();
	}
return input;
}
private Customer SignupCustomer(String name,String passWord) {
	UserInterface userIterface = new UserInterface();
	Customer customer =  new Customer(name,passWord,userIterface.getInput("Enter address"));
	FileHandling fh = new FileHandling();
	ArrayList<Customer> c = new ArrayList<>();
	c = fh.getCustomerList("Customers.csv");
	c.add(customer);
	fh.setCustomerList("Customers.csv", c);
	
	return customer;
}
	
}

