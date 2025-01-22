package GUI;

import com.example.borderguessrguiprova.GUI.BorderGuessrApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class EndOfTheMatchController {

    private String finalMessage;
    private HBox saidCountries;

    public void setMossa(String finalMessage) {this.finalMessage = finalMessage;}

    public void setSaidCountries(HBox saidCountries) {this.saidCountries = saidCountries;}

    @FXML
    private Label vincitore;

    @FXML
    private ImageView imgUser;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void initialize() {

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        saidCountries.setOnMousePressed(event -> {
            scrollPane.setUserData(new double[]{event.getSceneX(), event.getSceneY(), scrollPane.getHvalue(), scrollPane.getVvalue()});
        });

        saidCountries.setOnMouseDragged(event -> {
            double[] data = (double[]) scrollPane.getUserData();

            // Moltiplica il delta per aumentare la velocità
            double speedFactor = 6.0; // Modifica questo valore per aumentare/ridurre la velocità
            double deltaX = (event.getSceneX() - data[0]) / scrollPane.getWidth() * speedFactor;
            double deltaY = (event.getSceneY() - data[1]) / scrollPane.getHeight() * speedFactor;

            // Aggiorna hvalue e vvalue
            scrollPane.setHvalue(Math.max(0, Math.min(1, data[2] - deltaX)));
            scrollPane.setVvalue(Math.max(0, Math.min(1, data[3] - deltaY)));
        });

        vincitore.setText(finalMessage);

        Image image;

        if (finalMessage.contains("user"))
            image = new Image(getClass().getResourceAsStream("/img/vittoria.png"));
        else
            image = new Image(getClass().getResourceAsStream("/img/sconfitta.png"));

        imgUser.setImage(image);

        scrollPane.setContent(saidCountries);
    }

    @FXML
    protected void onGiocaDiNuovoClick() throws Exception{
        BorderGuessrApplication.switchScene("select-AI-view.fxml");
    }

    @FXML
    protected void onChiudiClick() {
        Platform.exit();
    }
}
