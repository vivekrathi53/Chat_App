import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChatWindowController
{
    public Connection connection;
    @FXML
    Button Send;
    @FXML
    TextArea textBox;
    @FXML
    AnchorPane WindowPane;
    @FXML
    VBox VerticalPane;
    @FXML
    VBox AllChats;
    public Socket socket;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;
    public Stage currentStage;
    public ClientReciever reciever;
    public ArrayList<Message> chats;
    private String username;

    public void addChat(String username)
    {
        Label name = new Label(username);
        AllChats.getChildren().add(name);
        name.setOnMouseClicked(e -> display(username));
    }
    public void display(String username)// Current Chats of user with username person displayed
    {

    }
    private void fetchAllChats(String username)// Fetch All chats of user from local database
    {
        if(chats!=null)
            chats.clear();
        String q="SELECT * FROM LocalChats";
        PreparedStatement ps=null;
        try {
            ps = connection.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                chats.add(new Message(rs.getString("Sender"),rs.getString("Receiver"),rs.getString("Message"),rs.getTimestamp("SentTime"),rs.getTimestamp("ReceivedTime"),rs.getTimestamp("SeenTime")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void refresh()
    {
        chats = new ArrayList<>();
        fetchAllChats(username);
        for(int i=0;i<chats.size();i++)
        {
            try {
                addMessageToDisplay(chats.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void addMessageToDisplay(Message mesg) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageDisplay.fxml"));
        VBox vbox = loader.load();
        MessageDisplayController mdc = loader.getController();
        mdc.MessageContent.setText(mesg.getContent());
        mdc.SenderName.setText(mesg.getFrom());
        VerticalPane.getChildren().add(vbox);
    }
    public void addChatToDisplay(String user)
    {
        AllChats.getChildren().add(new Label(user));
    }
}
