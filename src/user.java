import java.io.Serializable;

public class user implements Serializable {
    String username;
    String password;

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
