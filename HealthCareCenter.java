package guide;

public class HealthCareCenter extends Place {

    private String specialization;

    public HealthCareCenter(String name,
                            String address,
                            String contact,
                            String specialization) {

        super(name,address,contact);

        this.specialization = specialization;
    }

    @Override
    public String getDetails() {

        return "Healthcare Center\n"
                + "Name: " + name
                + "\nAddress: " + address
                + "\nContact: " + contact
                + "\nSpecialization: "
                + specialization;
    }
}
