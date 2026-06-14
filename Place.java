package guide;

public abstract class Place implements Contactable {

    protected String name;
    protected String address;
    protected String contact;

    public Place(String name,
                 String address,
                 String contact) {

        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String getContact() {
        return contact;
    }

    public abstract String getDetails();
}
