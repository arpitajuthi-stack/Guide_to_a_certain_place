package guide;

public class Resturant extends Place {

    private String cuisineType;
    private int capacity;

    public Resturant(String name,
                      String address,
                      String contact,
                      String cuisineType,
                      int capacity) {

        super(name,address,contact);

        this.cuisineType = cuisineType;
        this.capacity = capacity;
    }

    @Override
    public String getDetails() {

        return "Resturant\n"
                + "Name: " + name
                + "\nAddress: " + address
                + "\nContact: " + contact
                + "\nCuisine: "
                + cuisineType
                + "\nCapacity: "
                + capacity;
    }
}

