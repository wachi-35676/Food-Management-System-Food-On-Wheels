public class Order {
    private String orderStatus;
    private Food food;
    private boolean delivered;
    private String customerUserName;
    private String restaurantUserName;
    private String riderUserName;

    Order(){}

    Order (String orderStatus, Food food, String customerUserName, String restaurantUserName){
        this.orderStatus = orderStatus;
        this.food = food;
        this.customerUserName = customerUserName;
        this.restaurantUserName = restaurantUserName;
        delivered = false;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public String getRestaurantUserName() {
        return restaurantUserName;
    }

    public String checkOrdersStatus(){
        String str = "";

        str += "    Food Name: " + food.getFoodName() + "\n";
        str += "    Food Price: " + food.getFoodPrice() + "\n";
        str += "    Status: " + orderStatus + "\n";

        return str;
    }

    public void setRiderUserName(String riderUserName) {
        this.riderUserName = riderUserName;
    }

    public String getRiderUserName() {
        return riderUserName;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Food getFood() {
        return food;
    }
}
