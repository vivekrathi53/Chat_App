import javafx.event.EventType;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatWindowController
{
    @FXML
    Button Send;
    @FXML
    TextArea textBox;
    @FXML
    AnchorPane WindowPane;
    @FXML
    FlowPane flowPane;
    @FXML
    VBox AllChats;
    public Socket socket;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;
    public Stage currentStage;
    public ClientReciever reciever;
    public void addChat(String username)
    {
        Label name = new Label(username);
        AllChats.getChildren().add(name);
        name.setOnMouseClicked(e -> display(username));
    }
    private void display(String username)// Current Chats of user with username person displayed
    {

    }
    private void fetchAllChats(String username)
    {

    }

}
