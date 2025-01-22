package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class HomeController {
    @FXML
    private ImageView imgLogo;

    @FXML
    public void initialize() {

        Image image = new Image(getClass().getResourceAsStream("/img/logo.png"));
        imgLogo.setImage(image);
    }

    @FXML
    protected void onNuovaPartitaClick() throws Exception {
        BorderGuessrApplication.switchScene("select-AI-view.fxml");
    }

    @FXML
    protected void onAboutClick() throws Exception {
        //BorderGuessrApplication.switchScene("about-view.fxml");

        try {
            FXMLLoader Loader = new FXMLLoader(BorderGuessrApplication.class.getResource("/borderguessrView/about-view.fxml"));
            Scene popupScene = new Scene(Loader.load());

            Stage popupStage = new Stage();
            popupStage.setTitle("About");
            popupStage.setScene(popupScene);
            popupStage.initModality(Modality.WINDOW_MODAL);
            popupStage.initOwner(BorderGuessrApplication.getPrimaryStage());

            popupStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
