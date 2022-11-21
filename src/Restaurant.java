import java.util.ArrayList;

public class Restaurant extends User{
    private String restaurantName;
    private ArrayList<Food> menu;
    private String fileName;

    Restaurant(String userName, String password, String fileName, String restaurantName){
        super(userName, password);
        FileHandling fileHandling = new FileHandling();

        this.restaurantName = restaurantName;
        this.fileName = fileName;

        menu = fileHandling.getFoodList(fileName);
    }

    public String checkOrders(ArrayList<Order> orders){
        String str = "";
        Integer bill = 0;

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getRestaurantUserName().equals(getUserName())){
                str += "Order " + i + ":\n";
                str += orders.get(i).checkOrdersStatus();
                bill += orders.get(i).getFood().getFoodPrice();
            }
        }

        str += "Your Total Earnings: " + bill;

        return str;
    }
    
    public  void handleRestaurantOwner(ArrayList <Order> orders){
        UserInterface userInterface = new UserInterface();
        int options = 0;
        do {
            options = userInterface.getIntInput("1 --> Add Food\n2 --> Edit Food\n3 --> Delete Food\n4 --> Check Orders\n5 --> Update Orders Status\n0 --> Log Out\n");
            switch (options) {
                case 1: {
                	addFood();
                    break;
                }
                case 2: {
                    editRestaurant();
                    break;
                }
                case 3: {
                    removeItem();
                    break;
                }
                case 4:{
                    userInterface.givePrompt(checkOrders(orders));
                    break;
                }
                case 5:{
                    updateOrderStatus(orders);
                    }
                }
            }while (options != 0);
        }

    private void updateOrderStatus(ArrayList<Order>orders){
        UserInterface userInterface = new UserInterface();
        String str = "";

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getRestaurantUserName().equals(getUserName()) && orders.get(i).getOrderStatus().equals("Order Requested From Restaurant")){
                str += "Order " + i + ":\n";
                str += orders.get(i).checkOrdersStatus();
            }
        }

        int choice = userInterface.getIntInput(str + "Select an Order which is ready for delivery (-1 to exit)");

        if(choice != -1) {
            orders.get(choice).setOrderStatus("Ready. Waiting for rider to pick up.");
        }
    }
    
    private void addFood() {
        FileHandling fileHandling = new FileHandling();
        UserInterface userInterface = new UserInterface();
    	if (getFullMenu().size()<=15) {
            String foodName = userInterface.getInput("Enter Name of Food: ");
            Integer foodPrice = userInterface.getIntInput("Enter Price of Food: ");

            getFullMenu().add(new Food(foodName, foodPrice));

            fileHandling.updateFoodList(getFileName(), getFullMenu());
    	}
    	else {
    		userInterface.givePrompt("Can not add anymore to the menu");
    	}
    }

    public ArrayList<Food> getFullMenu(){
        return menu;
    }

    public String getMenu(){
        String completeMenu = "";
        for (int i = 0; i < menu.size(); i++) {
            completeMenu += "Food Item " + i + ":\n" + menu.get(i).toString();
        }
        return completeMenu;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getFileName() {
        return fileName;
    }
    
    public void editRestaurant(){
        UserInterface userInterface = new UserInterface();
        FileHandling fileHandling = new FileHandling();

        int foodToEdit = userInterface.getIntInput(getMenu() + "\nEnter item number to Edit: ");

        String foodName = userInterface.getInput("Enter Name of Food: ");
        Integer foodPrice = userInterface.getIntInput("Enter Price of Food: ");

        getFullMenu().set(foodToEdit,new Food(foodName, foodPrice));

        fileHandling.updateFoodList(getFileName(), getFullMenu());
    }
    
    public void removeItem(){
        UserInterface userInterface = new UserInterface();
        FileHandling fileHandling = new FileHandling();

        int foodToRemove = userInterface.getIntInput(getMenu() + "\nEnter item number to remove: ");
        getFullMenu().remove(foodToRemove);

        fileHandling.updateFoodList(getFileName(), getFullMenu());

    }
}
