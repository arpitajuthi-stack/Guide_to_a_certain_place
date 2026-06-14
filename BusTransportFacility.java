package guide;

public class BusTransportFacility extends Place {

    private String busType;
    private int capacity;

    public BusTransportFacility(
            String name,
            String address,
            String contact,
            String busType,
            int capacity) {

        super(name,address,contact);

        this.busType = busType;
        this.capacity = capacity;
    }

    @Override
    public String getDetails() {

        return "Bus Facility\n"
                + "Name: " + name
                + "\nAddress: " + address
                + "\nContact: " + contact
                + "\nType: " + busType
                + "\nCapacity: "
                + capacity;
    }
}
