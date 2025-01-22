import interfaces.Algorithm;
import interfaces.Move;

import java.util.List;

public class BorderGuessrGame {
    private BorderGuessrBoard board;
    private Algorithm ai;

    public BorderGuessrGame(Algorithm ai, List<Country> allCountries, String turn) {
        board = BorderGuessrBoard.createBorderGuessrBoard(allCountries, turn);
        this.ai = ai;
    }

    private boolean handlePlayerMove(Country country) {
        //se il paese appena detto è già presente tra quelli detti oppure non confinante con l'ultimo paese detto
        if (board.getBoard().contains(country) || !board.getBoard().getLast().getNeighbors().contains(country.getName())) {
            return false;
        }
        Move playerMove = new BorderGuessrMove(board, country);
        playerMove.execute();
        return true;
    }

    private void handleAiMove() {
        Move aiMove = ai.findBestMove(board);
        aiMove.execute();
        BorderGuessrMove bMove = (BorderGuessrMove) aiMove;
        System.out.println(" " + bMove.getCurrentCountry().getName());
    }

    public void handleTurn(Country country){
        board.setTurn("user");
        if (!handlePlayerMove(country)) {
            //dopo che sbaglio la partita dovrebbe finire
            throw new RuntimeException("You can't say this country");
        }

        if (board.isGameFinished()){
            System.out.println(board.getWinner() + " WON!");
            return;
        }

        board.setTurn("computer");
        System.out.print("Computer's turn:");
        handleAiMove();

        if (board.isGameFinished()){
            System.out.println(board.getWinner() + " WON!");
        }
    }

    public boolean isFinished() {
        return board.isGameFinished();
    }

    public BorderGuessrBoard getBoard() {
        return board;
    }
}
