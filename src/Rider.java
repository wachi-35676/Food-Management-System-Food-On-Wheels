import java.util.ArrayList;

public class Rider extends User{
    private String approved;
    Order order;

    Rider(String userName, String password, String approved){
        super(userName, password);
        this.approved = approved;
    }

    Rider(String userName, String password){
        super(userName, password);
        approved = "No";
    }

    public Order getOrder() {
        return order;
    }

    public boolean isApproved(){
        if (approved.equals("Yes")){
            return true;
        }
        else {
            return false;
        }
    }

    public String getApproved() {
        return approved;
    }

    public void approveRider(){
        approved = "Yes";
    }

    public void handleRider(ArrayList <Order> orders, ArrayList <Restaurant> restaurants, ArrayList<Customer> customers){
        UserInterface userInterface = new UserInterface();
        if (isApproved()) {
            int choice;
            do {
                choice = userInterface.getIntInput("1 --> Accept/Decline an Order\n2 --> Update Status\n0 --> Log Out\nEnter Your Choice: ");
                switch (choice) {
                    case 1: {
                        confirmOrder(orders, restaurants, customers);
                        break;
                    }
                    case 2: {
                        updateStatus(orders);
                        break;
                    }
                    case 0:{
                        break;
                    }
                    default:{
                        userInterface.givePrompt("Invalid Input");
                    }
                }

            } while (choice != 0);
        }
        else{
            userInterface.givePrompt("Your account has not been approved yet");
        }
    }
    
    private void updateStatus(ArrayList <Order> orders){
        UserInterface userInterface = new UserInterface();
        int orderIndex = -1;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getRiderUserName().equals(getUserName()) && !orders.get(i).isDelivered()) {
                orderIndex = i;
                break;
            }
        }

        if(orderIndex == -1){
            userInterface.givePrompt("You Have Not been given any orders yet");
        }
        else if(orders.get(orderIndex).getOrderStatus().equals("Ready. Waiting for rider to pick up.")){
            userInterface.givePrompt("You have been assigned order kindly accept it or decline it to update any status");
        }
        else if(orders.get(orderIndex).getOrderStatus().equals("Rider Selected. Going to Pick Up from restaurant")){
            int newStatus;
            do{
                newStatus = userInterface.getIntInput("1 --> Picked Up Delivery\n2 --> Have to Pick Up Delivery\n");
                switch (newStatus){
                    case 1:{
                        orders.get(orderIndex).setOrderStatus("Picked Up From restaurant. Heading to Customer.");
                    }
                    case 2:{
                        break;
                    }
                    default:{
                        userInterface.givePrompt("Invalid Input");
                    }
                }
            }while (newStatus < 1 || newStatus >2);
        }
        else if(orders.get(orderIndex).getOrderStatus().equals("Picked Up From restaurant. Heading to Customer.")){
            int newStatus;
            do{
                newStatus = userInterface.getIntInput("1 --> Delivered\n2 --> On My way\n");
                switch (newStatus){
                    case 1:{
                        orders.get(orderIndex).setOrderStatus("Delivered");

                        int payCollect = userInterface.getIntInput("Collect amount " + orders.get(orderIndex).getFood().getFoodPrice() + " from customer.\n1 --> Payed\n2 --> Cant Collect amount from customer");

                        switch (payCollect){
                            case 1:{
                                orders.get(orderIndex).setDelivered(true);
                                break;
                            }
                            case 2:{
                                orders.get(orderIndex).setDelivered(true);
                                orders.get(orderIndex).setOrderStatus("Delivered but Customer did not pay/collected");
                                break;
                            }
                            default:{
                                userInterface.givePrompt("Invalid Input");
                            }
                        }
                    }
                    case 2:{
                        break;
                    }
                    default:{
                        userInterface.givePrompt("Invalid Input");
                    }
                }
            }while (newStatus < 1 || newStatus >2);
        }
        else {
            userInterface.givePrompt("Failed");
        }
    }

    private void confirmOrder(ArrayList <Order> orders, ArrayList <Restaurant> restaurants,ArrayList<Customer> customers){
        UserInterface userInterface = new UserInterface();

        int customerIndex = -1;
        int restaurantIndex = -1;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getRiderUserName().equals(getUserName())){
                for (int j = 0; j < restaurants.size(); j++) {
                    if(restaurants.get(j).getUserName().equals(orders.get(i).getRestaurantUserName())){
                        restaurantIndex = j;
                    }
                }
                for (int j = 0; j < customers.size(); j++) {
                    if(customers.get(j).getUserName().equals(orders.get(i).getCustomerUserName())){
                        customerIndex = j;
                    }
                }

                String str = "";

                str += "Order from " + restaurants.get(restaurantIndex).getRestaurantName() + "\n";
                str += "To " + customers.get(restaurantIndex).getAddress() + "\n";

                int approval;
                do {
                    approval = userInterface.getIntInput(str + "1 --> Accept\n2 --> Decline");

                    switch (approval){
                        case 1:{
                            orders.get(i).setOrderStatus("Rider Selected. Going to Pick Up from restaurant");
                            break;
                        }
                        case 2:{
                            orders.get(i).setRiderUserName(null);
                            break;
                        }
                        default:{
                            userInterface.givePrompt("You have to accept or decline.");
                        }
                    }
                }while (approval < 1 || approval > 2);

                break;
            }
        }

        if (customerIndex == -1){
            userInterface.givePrompt("You have no Delivery Requests right now");
        }
    }
}
