public class Item {
    private String restaurantUserName;
    private Food food;

    Item(String  restaurantUserName, Food food){
        this.restaurantUserName = restaurantUserName;
        this.food = food;
    }

    public String getRestaurantUserName() {
        return restaurantUserName;
    }

    public Food getFood() {
        return food;
    }
}
