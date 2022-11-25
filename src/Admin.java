import java.util.ArrayList;

public class Admin extends User{

    Admin(String userName, String password){
        super(userName, password);
    }
    

	public void handleAdmin(ArrayList<Customer> customers, ArrayList <Restaurant> restaurants,ArrayList <Order> orders, ArrayList <Rider> riders){
        UserInterface userInterface = new UserInterface();
        int choice;
        do{
            choice = userInterface.getIntInput("1 --> Manage Restaurants\n2 --> Manage Customers\n3 --> Manage Rider\n4 --> Assign Rider to an Order\n0 --> Exit");
            switch (choice){
                case 1:{
                    manageRestaurants(restaurants);
                    break;
                }
                case 2:{
                    manageCustomers(customers);
                    break;
                }
                case 3:{
                    manageRider(riders);
                    break;
                }
                case 4:{
                    assignOrder(orders, riders);
                }
            }

        }while (choice != 0);
    }
	
	private void manageCustomers(ArrayList<Customer> customers){
        UserInterface userInterface = new UserInterface();
        int choice = userInterface.getIntInput(userInterface.viewCustomers(customers) + "Chose a Customer (-1 to Exit): ");
        if (choice >= 0 && choice < customers.size()) {
            int options;

            do {
                options = userInterface.getIntInput("1 --> Remove Customer\n0 --> Exit");

                switch (options) {
                    case 1: {
                        FileHandling fileHandling = new FileHandling();
                        customers.remove(choice);
                        fileHandling.setCustomerList("Customers.csv", customers);
                        break;
                    }
                    default: {
                        userInterface.givePrompt("Invalid Input");
                    }
                }
            } while (options > 1);
        }
    }


	private void manageRider(ArrayList <Rider> riders){
        UserInterface userInterface = new UserInterface();
        int choice;
        do{
            choice = userInterface.getIntInput("1 --> Approve Rider\n2 --> Manage Rider\n0 --> Exit");

            switch (choice){
                case 1:{
                    FileHandling fileHandling = new FileHandling();
                    String str = "";
                    for (int i = 0; i < riders.size(); i++) {
                        if (riders.get(i).getApproved().equals("No")){
                            str += "Rider " + i + ": \n";
                            str += "    User Name:" + riders.get(i).getUserName() + "\n";
                        }
                    }

                    if (!str.equals("")) {
                        int riderToApprove = userInterface.getIntInput(str + "Enter Rider number of the rider you want to approve (Enter -1 to Exit)");

                        if (riderToApprove != -1) {
                            riders.get(riderToApprove).approveRider();
                            fileHandling.setRiderList("Riders.csv", riders);
                        }
                    }
                    else{
                        userInterface.givePrompt("No Riders Waiting Approval");
                    }
                    break;
                }
                case 2:{
                    String str = "";
                    for (int i = 0; i < riders.size(); i++) {
                        str += "Rider " + i + ": \n";
                        str += "    User Name: " + riders.get(i).getUserName() + "\n";
                        str += "    Approved:  " + riders.get(i).getApproved() + "\n";
                    }
                    int riderToManage = userInterface.getIntInput(str + "Which User to Manage (-1 to exit): ");
                    if (riderToManage >= 0 && riderToManage <= riders.size()){
                        manageRider(riders, riderToManage);
                    }
                    break;
                }
            }

        }while (choice != 0);
    }

	private void manageRider(ArrayList<Rider>riders, int index){
        FileHandling fileHandling = new FileHandling();
        UserInterface userInterface = new UserInterface();
        int option;

        do{
            option = userInterface.getIntInput("1 --> Remove Rider\n2 --> View Current Order\n0 --> Exit");

            switch (option){
                case 1:{
                    riders.remove(index);
                    fileHandling.setRiderList("Riders.csv",riders);
                    break;
                }
                case 2:{
                    if (riders.get(index).getOrder() == null){
                        userInterface.givePrompt("Rider is not handling any orders right now");
                    }
                    else {
                        userInterface.givePrompt(riders.get(index).getOrder().getOrderStatus());
                    }
                    break;
                }
                case 0:{
                    break;
                }
                default:{
                    userInterface.givePrompt("Invalid Input");
                }
            }

        }while (option > 1);

    }


	private void manageRestaurants( ArrayList <Restaurant> restaurants){
        UserInterface userInterface = new UserInterface();
        int choice = userInterface.getIntInput(userInterface.viewRestaurants(restaurants) + "Chose The Restaurant (-1 to Exit): ");
        if (choice >= 0 && choice < restaurants.size()){
            int options;

            do {
                options = userInterface.getIntInput("1 --> Remove Restaurant\n2 --> Edit an Item\n3 --> Remove an Item from a Restaurant\n0 --> Exit");

                switch (options) {
                    case 1: {
                        FileHandling fileHandling = new FileHandling();
                        restaurants.remove(choice);
                        fileHandling.setRestaurantList("Restaurants.csv", restaurants);
                        break;
                    }
                    case 2: {
                    	restaurants.get(choice).editRestaurant();

                    }
                    case 3: {
                    	restaurants.get(choice).removeItem();
                        break;
                    }
                    default:{
                        userInterface.givePrompt("Invalid Input");
                    }
                }
            }while (options > 1 );
        }
    }

	

	private void assignOrder(ArrayList <Order> orders, ArrayList <Rider> riders){
        UserInterface userInterface = new UserInterface();
        String str = "";

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderStatus().equals("Ready. Waiting for rider to pick up.") && orders.get(i).getRiderUserName() == null){
                str += "Order " + i + ":\n";
                str += orders.get(i).checkOrdersStatus();
            }
        }

        if (str.equals("")) {
            userInterface.givePrompt("No Orders are currently Available");
        }
        else {
            int orderChosen = userInterface.getIntInput(str + "Chose an order (-1 to exit)");

            if (orderChosen != -1) {
                String str1 = "";

                for (int i = 0; i < riders.size(); i++) {
                    if (riders.get(i).getOrder() == null && riders.get(i).isApproved()) {
                        str1 += "Rider " + i + ":\n";
                        str1 += "   " + riders.get(i).getUserName() + "\n";
                    }
                }

                if (str1.equals("")){
                    userInterface.givePrompt("No riders are available right now");
                }
                else {
                    int riderChosen = userInterface.getIntInput(str1 + "Chose a rider (-1 to exit):");

                    if (riderChosen != -1) {
                        orders.get(orderChosen).setRiderUserName(riders.get(riderChosen).getUserName());
                    }
                }
            }
        }

    }

	
}
