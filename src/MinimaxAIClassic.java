import interfaces.Board;
import interfaces.Move;

import java.util.List;

public class MinimaxAIClassic extends MinimaxAI {

    @Override
    public Move findBestMove(Board currentBoard) {
        return minimax(currentBoard, true).getMove();
    }

    protected ScoredMove minimax(Board currentBoard, boolean isMaximizingPlayer) {
        List<Move> nextMoves = currentBoard.generateNextMoves();
        int bestScore = isMaximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Move bestMove = null;

        if(nextMoves.isEmpty()){
            bestScore = currentBoard.utility();
            //codice in più
            BorderGuessrBoard bBoard = (BorderGuessrBoard) currentBoard;
            System.out.println("Utility associata a " + bBoard.getBoard().getLast().getName() + " e\' " + bestScore);
        } else {
            for (Move move: nextMoves){
                move.execute();
                if(isMaximizingPlayer){
                    ScoredMove scoredMove = minimax(currentBoard, false);
                    if(scoredMove.getScore() > bestScore){
                        bestScore = scoredMove.getScore();
                        bestMove = move;

                        //aggiunte personali
                        BorderGuessrMove bMove = (BorderGuessrMove) move;
                        System.out.println("Best move : " + bMove.getCurrentCountry().getName() + " con valore " + bestScore);
                    }


                } else {
                    ScoredMove scoredMove = minimax(currentBoard, true);
                    if(scoredMove.getScore() < bestScore){
                        bestScore = scoredMove.getScore();
                        bestMove = move;
                    }
                }


                move.undo();
            }
        }
        return new ScoredMove(bestMove, bestScore);
    }
}