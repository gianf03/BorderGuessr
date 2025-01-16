package interfaces;

import java.util.List;

public interface Board {
    List<Move> generateNextMoves();
    int utility();
    boolean isGameFinished();
    String getWinner();
}