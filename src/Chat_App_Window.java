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

public class Chat_App_Window extends Application {

    Stage window;
    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    FXMLLoader loader;
    AnchorPane DisplayPane;
    ChatWindowController controller;
    ClientReciever reciever;
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
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
        reciever = new ClientReciever();
        Thread t = new Thread(reciever);
        window=primaryStage;
        controller.socket = this.socket;
        controller.ois = this.ois;
        controller.oos = this.oos;
        controller.currentStage = primaryStage;
        controller.socket = socket;
        reciever.ois=this.ois;
        reciever.controller=controller;
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
