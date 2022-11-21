import java.io.*;
import java.util.ArrayList;

public class FileHandling {

    public ArrayList<Food> getFoodList(String fileName){
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Food> foodArrayList = new ArrayList<>();

        while (true) {

            String str = null;

            try {
                str = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(str == null){
                break;
            }

            String [] str1 = str.split(",");

            foodArrayList.add(new Food(str1[0], Integer.parseInt(str1[1])));
        }
        return foodArrayList;
    }

    public ArrayList<Restaurant> getRestaurantList(String fileName){
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        while (true) {

            String str = null;

            try {
                str = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(str == null){
                break;
            }

            String [] str1 = str.split(",");

            restaurants.add(new Restaurant(str1[0], str1[1], str1[2], str1[3]));
        }
        return restaurants;
    }

    public void setRestaurantList(String fileName, ArrayList<Restaurant> restaurants){
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < restaurants.size(); i++) {
            try {
                bufferedWriter.write(restaurants.get(i).getUserName() + "," + restaurants.get(i).getPassword() + "," + restaurants.get(i).getFileName() + "," + restaurants.get(i).getRestaurantName() + "\n");
            }
            catch (Exception e){
                System.out.println(e);
            }
        }

        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> getCustomerList(String fileName){
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Customer> customers = new ArrayList<>();

        while (true) {

            String str = null;

            try {
                str = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(str == null){
                break;
            }

            String [] str1 = str.split(",");

            customers.add(new Customer(str1[0], str1[1], str1[2]));
        }
        return customers;
    }

    public void setCustomerList(String fileName, ArrayList<Customer> customers){
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < customers.size(); i++) {
            try {
                bufferedWriter.write(customers.get(i).getUserName() + "," + customers.get(i).getPassword() + "," + customers.get(i).getAddress() + "\n");
            }
            catch (Exception e){
                System.out.println(e);
            }
        }

        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Rider> getRiderList(String fileName){
        {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<Rider> riders = new ArrayList<>();

            while (true) {

                String str = null;

                try {
                    str = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (str == null) {
                    break;
                }

                String[] str1 = str.split(",");

                riders.add(new Rider(str1[0], str1[1], str1[2]));
            }
            return riders;
        }
    }

    public void setRiderList(String fileName,ArrayList<Rider> riders){
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < riders.size(); i++) {
            try {
                bufferedWriter.write(riders.get(i).getUserName() + "," + riders.get(i).getPassword() + "," + riders.get(i).getApproved() +"\n");
            }
            catch (Exception e){
                System.out.println(e);
            }
        }

        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Admin> getAdminList(String fileName){
        {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<Admin> admins = new ArrayList<>();

            while (true) {

                String str = null;

                try {
                    str = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (str == null) {
                    break;
                }

                String[] str1 = str.split(",");

                admins.add(new Admin(str1[0], str1[1]));
            }
            return admins;
        }
    }

    public void updateFoodList(String fileName, ArrayList<Food> foodArrayList){
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < foodArrayList.size(); i++) {
            try {
                bufferedWriter.write(foodArrayList.get(i).getFoodName() + "," + foodArrayList.get(i).getFoodPrice() + "\n");
            }
            catch (Exception e){
                System.out.println(e);
            }
        }

        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
