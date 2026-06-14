package guide;

public class SecurityService extends Place {

    private String serviceType;

    public SecurityService(
            String name,
            String address,
            String contact,
            String serviceType) {

        super(name,address,contact);

        this.serviceType = serviceType;
    }

    @Override
    public String getDetails() {

        return "Security Service\n"
                + "Name: " + name
                + "\nAddress: " + address
                + "\nContact: " + contact
                + "\nService Type: "
                + serviceType;
    }
}
