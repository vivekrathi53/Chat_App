import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Signup extends Application
{
    Stage window;
    Signupcontroller controller;
    FXMLLoader loader;
    AnchorPane Display;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Display = loader.load();
        controller=loader.getController();
        controller.window=primaryStage;
        window=primaryStage;
        window.setScene(new Scene(Display));
        window.setTitle("SignUp Window");
        window.show();
    }
}
