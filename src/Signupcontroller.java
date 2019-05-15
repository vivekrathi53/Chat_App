import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Signupcontroller
{
    @FXML
    Button Submit;
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    TextField ServerIP;
    @FXML
    TextField PortNo;
    private Socket socket;

    public void Sign() throws IOException
    {
        socket = new Socket(ServerIP.getText(), Integer.parseInt(PortNo.getText()));
        System.out.println("Connected to server");
        Signupclass data = new Signupclass(username.getText(),password.getText());
        ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(data);
        oos.flush();
    }
}
