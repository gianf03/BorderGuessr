import interfaces.Algorithm;
import interfaces.Board;
import interfaces.Move;

import java.util.List;

public abstract class MinimaxAI implements Algorithm {
    public abstract Move findBestMove(Board currentBoard);

    protected static class ScoredMove {
        private final Move move;
        private final int score;

        public ScoredMove(Move move, int score) {
            this.move = move;
            this.score = score;
        }

        public Move getMove() {
            return move;
        }

        public int getScore() {
            return score;
        }
    }
}