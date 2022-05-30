package cinema.configuration;

public class AppConfiguration {

    private static final String PASSWORD = "super_secret";

    public static boolean isPasswordCorrect(String password){

        return password.equals(PASSWORD);

    }
}
