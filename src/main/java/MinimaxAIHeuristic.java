import interfaces.Board;
import interfaces.Move;

import java.util.List;

public class MinimaxAIHeuristic extends MinimaxAI {
    private static final int MAXDEPTH = 14;
    @Override
    public Move findBestMove(Board currentBoard) {
        return minimax(currentBoard, true, Integer.MIN_VALUE , Integer.MAX_VALUE, 0).getMove();
    }

    protected MinimaxAI.ScoredMove minimax(Board currentBoard, boolean isMaximizingPlayer, int alpha, int beta, int depth) {
        List<Move> nextMoves = currentBoard.generateNextMoves();
        int bestScore = isMaximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Move bestMove = null;

        //codice in più
        BorderGuessrBoard bBoard = (BorderGuessrBoard) currentBoard;

        // Caso base 1: Nodo terminale
        if (nextMoves.isEmpty()) {
            bestScore = currentBoard.utility();
            //codice in più
            System.out.println("Utility associata a " + bBoard.getBoard().getLast().getName() + " e\' " + bestScore);
        }
        // Caso base 2: Raggiunta la profondità di taglio
        else if (depth == MAXDEPTH) {
            bestScore = 0; // Valore euristico per i nodi intermedi alla profondità di taglio
            System.out.println("Utility associata a " + bBoard.getBoard().getLast().getName() + " e\' " + bestScore);
        }
        // Caso ricorsivo
        else {
            for (Move move : nextMoves) {
                move.execute();

                if (isMaximizingPlayer) {
                    MinimaxAI.ScoredMove scoredMove = minimax(currentBoard, false, alpha, beta, depth + 1);
                    if (scoredMove.getScore() > bestScore) {
                        bestScore = scoredMove.getScore();
                        bestMove = move;
                        if (bestScore >= beta) {
                            move.undo();
                            break; // Beta-cutoff
                        }
                        alpha = Math.max(alpha, bestScore);
                    }
                } else {
                    MinimaxAI.ScoredMove scoredMove = minimax(currentBoard, true, alpha, beta, depth + 1);
                    if (scoredMove.getScore() < bestScore) {
                        bestScore = scoredMove.getScore();
                        bestMove = move;
                        if (bestScore <= alpha) {
                            move.undo();
                            break; // Alpha-cutoff
                        }
                        beta = Math.min(beta, bestScore);
                    }
                }

                move.undo();
            }
        }

        return new MinimaxAI.ScoredMove(bestMove, bestScore);
    }
}
