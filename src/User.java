import java.util.ArrayList;

public abstract class User {
    private String userName;
    private String password;

    User(){

    }

    User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean userMatches(String userName, String password){
        return this.userName.equals(userName) && this.password.equals(password);
    }
    
    public void handleUser(int loggedInUserType, ArrayList<Customer> customers, ArrayList <Rider> riders, ArrayList <Order> orders,  ArrayList <Restaurant> restaurants, ArrayList <Admin> admins){
        switch(loggedInUserType){
            case 1:{
            	Customer customerToHandle = customers.get(customers.indexOf((Customer) this));
            	customerToHandle.handleCustomer(restaurants, orders);
                break;
            }
            case 2:{
            	Restaurant restaurantToHandle = restaurants.get(restaurants.indexOf((Restaurant) this));
            	restaurantToHandle.handleRestaurantOwner(orders);
                break;
            }
            case 3:{
            	Rider riderToHandle = riders.get(riders.indexOf((Rider) this));
                riderToHandle.handleRider(orders,restaurants,customers);
                break;
            }
            case 4:{
            	Admin admin = admins.get(admins.indexOf((Admin) this));
                admin.handleAdmin(customers, restaurants, orders,riders);
                break;
            }
            default:{
                UserInterface userInterface = new UserInterface();
                userInterface.givePrompt("Log In Failed");
            }
        }
    }
}
