import java.io.Serializable;

public class Signupclass implements Serializable
{
    String user,password;
    public Signupclass(String user,String password) {
        this.user=user;
        this.password=password;
    }
}


