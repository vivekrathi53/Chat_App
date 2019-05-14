import com.sun.javafx.scene.traversal.SubSceneTraversalEngine;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatWindowController
{
    public Connection connection;
    @FXML
    Label currentUserStatus;
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
    @FXML
    Label currentUser;
    public Socket socket;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;
    public Stage currentStage;
    public ClientReceiver reciever;
    public ArrayList<Message> chats;
    public ArrayList<String> friends;
    public String username;

    public void addChat(String username)
    {
        Label name = new Label(username);
        AllChats.getChildren().add(name);//Add name of user to vbox
        name.setOnMouseClicked(e -> {
            try {
                display(username);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
    public void display(String username) throws IOException// Current Chats of user with username person displayed
    {
        currentUser.setText(username);
        VerticalPane.getChildren().clear();
        for(int i=0;i<chats.size();i++)
        {
            if(chats.get(i).getFrom().equals(username))
            {
                addMessageToDisplay(chats.get(i));
            }
        }
        currentUser.setText(username);
    }
    public void fetchAllChats()// Fetch All chats of user from local database
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
        Send.setOnMouseClicked(e -> sendMessage());
        chats = new ArrayList<>();
        friends= new ArrayList<>();
        HashMap<String,Integer> hs = new HashMap<String, Integer>();
        fetchAllChats();
        for(int i=0;i<chats.size();i++)
        {
            try {
                if(chats.get(i).getFrom().equals(currentUser))addMessageToDisplay(chats.get(i));
                if((!hs.containsKey(chats.get(i).getFrom()))&&(!chats.get(i).getFrom().equals(username)))
                {
                    friends.add(chats.get(i).getFrom());
                    Label person = new Label(chats.get(i).getFrom());
                    AllChats.getChildren().add(person);
                    person.setOnMouseClicked(e -> {
                        try {
                            display(person.getText());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage()
    {
        Message msg = new Message(username,currentUser.getText(),textBox.getText(),new Timestamp(System.currentTimeMillis()),null,null);
        try {
            System.out.println(msg.getContent());
            oos.writeObject(msg);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
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
