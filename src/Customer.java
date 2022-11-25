import java.util.ArrayList;

public class Customer extends User{
    private boolean ordered;
    private ArrayList<Item> cart;
    private String address;
    private Integer bill;

    Customer(String userName, String password, String address){
        super(userName, password);
        ordered = false;
        cart = new ArrayList<>();
        this.address = address;
    }

    public ArrayList<Item> getCart() {
        return cart;
    }
    
    public void handleCustomer(ArrayList <Restaurant> restaurants, ArrayList <Order> orders){
        do {
            if(!isOrdered()) {
                if (helpCustomerOrder(restaurants, orders)){
                    break;
                }
            }
            else {
                if (helpCustomerManageOrder(restaurants, orders)){
                    break;
                }
            }
        }while(true);
    }
    
    private boolean helpCustomerOrder(ArrayList <Restaurant> restaurants, ArrayList <Order> orders){
        UserInterface userInterface = new UserInterface();
        int options;
        do {
            options = userInterface.getIntInput("1 --> View Restaurants\n2 --> View Cart\n3 --> Confirm Order\n0 --> Log Out\n");
            switch (options) {
                case 1: {
                    int choice = userInterface.getIntInput(userInterface.viewRestaurants(restaurants)); //asking the user to select a restaurant
                    int foodChoice = userInterface.getIntInput(restaurants.get(choice).getMenu()); //chosing food from the restaurant
                    getCart().add(new Item(restaurants.get(choice).getUserName(), restaurants.get(choice).getFullMenu().get(foodChoice))); //adding to cart
                    break;
                }
                case 2: {
                    if (getCart().size() == 0) {
                        userInterface.givePrompt("Your Cart Is Empty Right Now");
                    } else {
                        int cartItemToRemove = userInterface.getIntInput(showCart(restaurants) + "\nEnter Item Number to Remove (Enter -1 to exit)");

                        if (cartItemToRemove == -1) {
                            break;
                        }

                        getCart().remove(cartItemToRemove);

                        userInterface.givePrompt("Item " + cartItemToRemove + " was removed from your Cart");
                    }
                    break;
                }
                case 3: {
                    if (getCart().size() == 0) {
                        userInterface.givePrompt("Your Cart Is Empty Right Now");
                    } else {
                        placeOrder(orders);
                        emptyCart();
                    }
                    break;
                }
                case 0: {
                    return true;
                }
            }
        }while (options != 3);
        return false;
    }

    private boolean helpCustomerManageOrder(ArrayList <Restaurant> restaurants, ArrayList <Order> orders){
        UserInterface userInterface = new UserInterface();

        int choice = userInterface.getIntInput("1 --> Check Your Order\n2 --> Cancel Order\n3 --> Make New Order\n0 --> Log Out");

        switch (choice){
            case 1:{
                String str = "";

                for (int i = 0; i < orders.size(); i++) {
                    if (orders.get(i).getCustomerUserName().equals(getUserName())){
                        str += "Order " + i + ":\n";
                        str += orders.get(i).checkOrdersStatus();
                    }
                }

                userInterface.givePrompt(str);
                break;
            }
            case 2:{
                deleteOrder(orders);
                break;
            }
            case 3:{
                helpCustomerOrder(restaurants, orders);
                break;
            }
            case 0:{
                return true;
            }
        }

        return false;
    }
    
    private String showCart(ArrayList<Restaurant> restaurants){
        String str = "";

        for (int i = 0; i < cart.size(); i++) {

            Restaurant restaurant = null;

            for (int j = 0; j < restaurants.size(); j++) {
                if (restaurants.get(j).getUserName().equals(cart.get(i).getRestaurantUserName())){
                    restaurant = restaurants.get(j);
                    break;
                }
            }


            str += "Item " + i + "\n";
            str += "    Restaurant: " + restaurant.getRestaurantName() + "\n";
            str += "    Food Item: " + cart.get(i).getFood().getFoodName() + "\n";
        }

        return str;
    }

    public String getAddress() {
        return address;
    }

    private ArrayList<Order> placeOrder(ArrayList<Order> orders){
        bill = 0;

        ordered = true;
        for (int i = 0; i < cart.size(); i++) {
            Order newOrder = new Order("Order Requested From Restaurant",cart.get(i).getFood(), getUserName(), cart.get(i).getRestaurantUserName());

            orders.add (newOrder);

            bill += cart.get(i).getFood().getFoodPrice();
        }

        return orders;
    }

    private ArrayList<Order> deleteOrder(ArrayList<Order> orders){
        UserInterface userInterface = new UserInterface();
        String str = "";

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getCustomerUserName().equals(getUserName())){
                str += "Order " + i + ":\n";
                str += orders.get(i).checkOrdersStatus();
            }
        }

        int orderToRemove = userInterface.getIntInput(str + "Enter Order Number you would like to cancel (-1 to Exit): ");

        if (orderToRemove != -1){
            orders.remove(orderToRemove);
        }

        boolean usersOrderFinished = true;

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getCustomerUserName().equals(getUserName())){
                usersOrderFinished = false;
                break;
            }
        }

        if (usersOrderFinished){
            ordered = false;
        }

        return orders;
    }

    public boolean isOrdered() {
        return ordered;
    }

    private void emptyCart(){
        cart = new ArrayList<>();
    }
}
