package GUI;


import com.example.borderguessrguiprova.algorithms.bestFirstSearch.GreedySearch;
import com.example.borderguessrguiprova.algorithms.minimax.MinimaxAIAlphaBeta;
import com.example.borderguessrguiprova.algorithms.minimax.MinimaxAIClassic;
import com.example.borderguessrguiprova.algorithms.minimax.MinimaxAIHeuristic;
import com.example.borderguessrguiprova.algorithms.uninformedSearch.BreadthFirstSearch;
import com.example.borderguessrguiprova.algorithms.uninformedSearch.DepthFirstSearch;
import com.example.borderguessrguiprova.interfaces.Algorithm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;


public class SelectAIController {

    @FXML
    private ComboBox<String> comboBoxAI;
    @FXML
    private ComboBox<String> comboBoxConfini;

    @FXML
    public void initialize() {

        ObservableList<String> optionsAI = FXCollections.observableArrayList("Minimax classico", "Minimax con potatura", "Minimax euristico", "DFS", "BFS", "Greedy search");
        comboBoxAI.setItems(optionsAI);
        comboBoxAI.setValue("Minimax classico");

        ObservableList<String> optionsConfini = FXCollections.observableArrayList("Terrestri", "Terrestri e marittimi");
        comboBoxConfini.setItems(optionsConfini);
        comboBoxConfini.setValue("Terrestri");
    }

    @FXML
    protected void onAvviaPartitaClick() throws Exception {

        String scelta = comboBoxAI.getValue();

        Algorithm ai = null;

        switch (scelta) {
            case "Minimax classico" : ai = new MinimaxAIClassic(); break;
            case "Minimax con potatura" : ai = new MinimaxAIAlphaBeta(); break;
            case "Minimax euristico" : ai = new MinimaxAIHeuristic(); break;
            case "DFS" : ai = new DepthFirstSearch(); break;
            case "BFS" : ai = new BreadthFirstSearch(); break;
            case "Greedy search" : ai = new GreedySearch();
        }

        // Carica la scena e il controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/borderguessrguiprova/match-view.fxml"));

        // Il problema principale è probabilmente un disallineamento tra il caricamento
        // della scena e la configurazione del controller. Usando setControllerFactory,
        // possiamo passare i dati al controller prima che venga caricato.

        //la variabila passata in un'espressione lambda deve essere necessariamente final
        final Algorithm aiFinal = ai;
        final String tipoConfini = comboBoxConfini.getValue();
        loader.setControllerFactory(param -> {
            MatchController matchController = new MatchController();
            matchController.setAi(aiFinal);  // Imposta l'AI direttamente
            matchController.setTipoConfini(tipoConfini);
            return matchController;
        });

        Parent root = loader.load();  // Carica la vista

        // Cambia scena
        Scene scene = new Scene(root);
        BorderGuessrApplication.getPrimaryStage().setScene(scene);
    }
}