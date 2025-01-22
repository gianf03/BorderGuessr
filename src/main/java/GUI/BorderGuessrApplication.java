package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class BorderGuessrApplication extends Application {
    private static Stage primaryStage;

    public static Stage getPrimaryStage() {return primaryStage;}

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        switchScene("main-view.fxml");
        stage.setTitle("BorderGuessr!");

        Image img = new Image(getClass().getResourceAsStream("/img/logo.png"));
        primaryStage.getIcons().add(img);

        primaryStage.setResizable(false);
        stage.show();
    }

    public static void switchScene(String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(BorderGuessrApplication.class.getResource("/com/example/borderguessrguiprova/" + fxmlFile));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
    }

    public static void main(String[] args) {launch();}
}