package GUI;


import com.fasterxml.jackson.databind.ObjectMapper;
import game.BorderGuessrGame;
import game.Country;
import game.CountryException;
import game.WorldMap;
import interfaces.Algorithm;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchController {

    @FXML
    private Label nazioneCorrente;
    @FXML
    private ImageView imgFlag;
    @FXML
    private TextField sceltaUtente;
    @FXML
    private HBox saidCountries;
    @FXML
    private ScrollPane scrollPane;

    private Algorithm ai;
    private BorderGuessrGame game;
    private String tipoConfini;
    private WorldMap worldMap;
    public void setAi(Algorithm ai) {this.ai = ai;}
    public void setTipoConfini(String tipoConfini) {this.tipoConfini = tipoConfini;}

    @FXML
    public void initialize() {

        // Configurazione dello ScrollPane
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

        worldMap = readWorldMap();

        if (worldMap != null) {
            game = new BorderGuessrGame(ai, worldMap.getAllCountries(), "user");

            //genera casualmente la nazione da cui inizia la partita
            Country initialCountry = worldMap.getAllCountries().get(new Random().nextInt(worldMap.getAllCountries().size()));

            //mostra il nome della nazione iniziale
            nazioneCorrente.setText(initialCountry.getName());

            //System.out.println(initialCountry.getName());

            Image image = new Image(getClass().getResourceAsStream(initialCountry.getImage()));
            imgFlag.setImage(image);

            //aggiunge la prima nazione alla tavola di gioco
            game.getBoard().addCountry(initialCountry);
        }
    }

    @FXML
    protected void onConfermaClick() {


        addSaidCountry(worldMap.getCountryByName(nazioneCorrente.getText()));

        String name = sceltaUtente.getText();
        sceltaUtente.setText("");

        Country country = new Country(name);

        List<String> neighbors = new ArrayList<>();
        String urlFlag = null;
        for (Country c : worldMap.getAllCountries()) {
            if (c.getName().equals(name)) {
                neighbors = c.getNeighbors();
                urlFlag = c.getImage();
            }
        }
        country.setNeighbors(neighbors);
        country.setImage(urlFlag);

        addSaidCountry(country);

        try {
            String mossa = game.handleTurn(country);
            nazioneCorrente.setText(mossa);

            Country c = worldMap.getCountryByName(mossa);

            Image image = new Image(getClass().getResourceAsStream(c.getImage()));
            imgFlag.setImage(image);
        } catch (CountryException e) {
            try {

                //recupero l'ultima mossa fatta dall'IA tramite l'eccezione
                if (e.getCountry() != null) {
                    addSaidCountry(worldMap.getCountryByName(e.getCountry()));
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/borderguessrView/end-of-the-match-view.fxml"));

                // Il problema principale è probabilmente un disallineamento tra il caricamento
                // della scena e la configurazione del controller. Usando setControllerFactory,
                // possiamo passare i dati al controller prima che venga caricato.

                //la variabila passata in un'espressione lambda deve essere necessariamente final
                final String mossaFinal = e.getMessage();
                final HBox saidCountriesFinal = saidCountries;
                loader.setControllerFactory(param -> {
                    EndOfTheMatchController endOfTheMatchController = new EndOfTheMatchController();
                    endOfTheMatchController.setMossa(mossaFinal);  // Imposta l'AI direttamente
                    endOfTheMatchController.setSaidCountries(saidCountriesFinal);
                    return endOfTheMatchController;
                });

                Parent root = loader.load();  // Carica la vista

                // Cambia scena
                Scene scene = new Scene(root);
                BorderGuessrApplication.getPrimaryStage().setScene(scene);
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        }

    }

    private void addSaidCountry(Country country) {

        if (worldMap.getAllCountries().contains(country)) {
            //contenitore per una nazione detta
            Label nazioneDetta = new Label(country.getName());

            Font font = new Font(14);
            nazioneDetta.setFont(font);
            nazioneDetta.setPadding(new Insets(5));
            nazioneDetta.setAlignment(Pos.CENTER);
            nazioneDetta.setStyle("-fx-background-radius: 15; -fx-background-color: lightgray;");
            nazioneDetta.setPrefWidth(100);

            Image imgNazioneDetta = new Image(getClass().getResourceAsStream(country.getImage()));
            ImageView imageView = new ImageView(imgNazioneDetta);

            //imposta le dimensioni massima dell'immagine
            imageView.setFitHeight(80);
            imageView.setFitWidth(80);
            //preserva le proporzioni dell'immagine
            imageView.setPreserveRatio(true);

            VBox itemBox = new VBox(nazioneDetta, imageView);
            itemBox.setAlignment(Pos.CENTER);
            itemBox.setMargin(nazioneDetta, new Insets(0, 0, 10, 0));
            saidCountries.getChildren().add(itemBox);
        }
    }

    private WorldMap readWorldMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        WorldMap wm = null;

        String jsonFile;

        if (tipoConfini.equals("Terrestri"))
            jsonFile = "allCountries.json";
        else
            jsonFile = "allCountriesFull.json";

        try {
            // Leggere il file JSON e convertirlo nella classe WorldMap
            wm = objectMapper.readValue(new File("src/main/resources/data/" + jsonFile), WorldMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wm;
    }
}
