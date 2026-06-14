package guide;

public class UserLogin {

    private String username =
            "admin";

    private String password =
            "1234";

    public boolean login(
            String user,
            String pass) {

        return username.equals(user)
                && password.equals(pass);
    }
}
