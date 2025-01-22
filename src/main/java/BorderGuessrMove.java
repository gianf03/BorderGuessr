import interfaces.Board;
import interfaces.Move;

public class BorderGuessrMove implements Move {
    private final BorderGuessrBoard currentBoard;
    private final Country  currentCountry;

    public BorderGuessrMove(BorderGuessrBoard currentBoard, Country currentCountry) {
        this.currentBoard = currentBoard;
        this.currentCountry = currentCountry;
    }

    @Override
    public void execute() {
        currentBoard.addCountry(this.currentCountry);
    }

    @Override
    public void undo() {
        currentBoard.removeCountry(this.currentCountry);
    }

    public Country getCurrentCountry() {
        return currentCountry;
    }
}
