import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoginWindowController
{
    public TextField ServerIP;
    public TextField PortNo;
    Socket socket;
    @FXML
    TextField name;
    @FXML
    TextField pass;
    @FXML
    Button customer;
    @FXML
    Button retailer;
    @FXML
    Button userLogin;
    int type;
    public Stage window;
    public void signUp()
    {
        try {
            Signup signup = new Signup();
            signup.start(window);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Login() throws IOException
    {
        window = (Stage)name.getScene().getWindow();
        socket = new Socket(ServerIP.getText(),Integer.parseInt(PortNo.getText()));
        System.out.println("Connected to server");
        user data = new user(name.getText(),pass.getText());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(data);
        oos.flush();
        System.out.println("Waiting for Approval!");
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        try
        {
            System.out.println("reading letter");
            Object temp=ois.readObject();
            Authentication a = (Authentication) temp;
            if(a.auth)
            {
                Chat_App_Window ChattingWindow = new Chat_App_Window();
                ChattingWindow.oos=oos;
                ChattingWindow.ois=ois;
                ChattingWindow.socket=socket;
                ChattingWindow.username=name.getText();
                ChattingWindow.start(window);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Authentication Failed");
                alert.setHeaderText("Please Check Your Login Credentials");
                alert.setContentText(a.Error);
                alert.show();
            }
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Class Not Found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
