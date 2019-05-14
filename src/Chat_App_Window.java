import com.sun.javafx.binding.StringFormatter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Chat_App_Window extends Application {

    Stage window;
    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    FXMLLoader loader;
    AnchorPane DisplayPane;
    ChatWindowController controller;
    ClientReceiver reciever;
    Connection connection;
    String username;

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException
    {

        loader = new FXMLLoader(getClass().getResource("Chat_Window.fxml"));
        try
        {
            DisplayPane  = (AnchorPane) loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        controller = loader.getController();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://192.168.0.100:3306/Chat_App";
        try {
            connection = DriverManager.getConnection(url, "root", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        reciever = new ClientReceiver();
        reciever.ois=ois;
        reciever.controller=controller;
        reciever.connection=connection;
        Thread t = new Thread(reciever);
        window=primaryStage;
        controller.socket = socket;//ChatWindowController getting client object
        controller.ois = ois;
        controller.oos = oos;
        controller.currentStage = primaryStage;
        controller.connection=connection;
        controller.username=username;
        controller.refresh();
        t.start();
        primaryStage.setTitle("Chat Window!");
        primaryStage.setScene(new Scene(DisplayPane));
        // set size of current window to maximum
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth((primScreenBounds.getWidth()));
        primaryStage.setHeight((primScreenBounds.getHeight()));
        // display window now
        primaryStage.show();
    }
}
