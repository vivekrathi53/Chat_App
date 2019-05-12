import java.io.Serializable;
import java.sql.Timestamp;


public class SystemMessage implements Serializable
{
    String sender;
    int valid;
    Timestamp time;



    public SystemMessage(String sender, int valid, Timestamp time) {
        this.sender=sender;
        this.valid=valid;
        this.time=time;
    }
}
