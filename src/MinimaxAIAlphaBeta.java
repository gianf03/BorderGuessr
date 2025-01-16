import interfaces.Board;
import interfaces.Move;

import java.util.List;

public class MinimaxAIAlphaBeta extends MinimaxAI {
    @Override
    public Move findBestMove(Board currentBoard) {
        return minimax(currentBoard, true, Integer.MIN_VALUE , Integer.MAX_VALUE).getMove();
    }


    protected ScoredMove minimax(Board currentBoard, boolean isMaximizingPlayer, int alpha, int beta) {
        List<Move> nextMoves = currentBoard.generateNextMoves();
        int bestScore = isMaximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Move bestMove = null;

        //codice in più
        BorderGuessrBoard bBoard = (BorderGuessrBoard) currentBoard;

        if(nextMoves.isEmpty()){
            bestScore = currentBoard.utility();
            //codice in più
            System.out.println("Utility associata a " + bBoard.getBoard().getLast().getName() + " e\' " + bestScore);
        } else {
            for (Move move: nextMoves){
                move.execute();
                if(isMaximizingPlayer){
                    ScoredMove scoredMove = minimax(currentBoard, false, alpha, beta);
                    if(scoredMove.getScore() > bestScore){
                        bestScore = scoredMove.getScore();
                        bestMove = move;
                        if(bestScore >= beta){
                            move.undo();
                            break;
                        }
                        alpha = Math.max(alpha,bestScore);
                    }


                } else {
                    ScoredMove scoredMove = minimax(currentBoard, true, alpha,beta );
                    if(scoredMove.getScore() < bestScore){
                        bestScore = scoredMove.getScore();
                        bestMove = move;
                        if (bestScore <= alpha){
                            move.undo();
                            break;
                        }
                        beta = Math.min(beta,bestScore);
                    }
                }


                move.undo();
            }
        }
        return new ScoredMove(bestMove, bestScore);
    }
}
