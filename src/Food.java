public class Food {
    String foodName;
    Integer foodPrice;

    Food(String foodName, Integer foodPrice){
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    @Override
    public String toString() {
        return "    Name: " + foodName + "\n    Price: " + foodPrice + "\n";
    }

    public String getFoodName() {
        return foodName;
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }
}
