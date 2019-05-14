import java.io.Serializable;

public class Errors implements Serializable
{
    String errormessage;

    public Errors(String errormessage) {
        this.errormessage = errormessage;
    }
}
