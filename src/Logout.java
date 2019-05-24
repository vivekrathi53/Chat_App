import java.net.Socket;
import java.sql.Timestamp;

public class Logout {
    private Socket socket;
    private String username;
    private  Timestamp time;
    public Logout(String username,Timestamp time,Socket socket) {
        this.username=username;
        this.time=time;
        this.socket=socket;
    }

    public String getUsername() {
        return username;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
