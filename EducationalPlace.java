package guide;

public class EducationalPlace extends Place {

    private String institutionType;

    public EducationalPlace(String name,
                            String address,
                            String contact,
                            String institutionType) {

        super(name,address,contact);

        this.institutionType = institutionType;
    }

    @Override
    public String getDetails() {

        return "Educational Place\n"
                + "Name: " + name
                + "\nAddress: " + address
                + "\nContact: " + contact
                + "\nType: " + institutionType;
    }
}