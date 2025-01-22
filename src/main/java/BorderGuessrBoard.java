import interfaces.Board;
import interfaces.Move;

import java.util.*;

public class BorderGuessrBoard implements Board {
    private final List<Country> board;
    private final List<Country> allCountries;
    private String turn;

    private BorderGuessrBoard(List<Country> board, List<Country> allCountries, String turn) {
        this.board = board;
        this.allCountries = allCountries;
        this.turn = turn;
    }

    public static BorderGuessrBoard createBorderGuessrBoard(List<Country> allCountries, String turn){
        List<Country> board = new ArrayList<>();
        return new BorderGuessrBoard(board, allCountries, turn);
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public void addCountry(Country country){
        board.add(country);
    }

    public void removeCountry(Country country){
        board.remove(country);
    }

    public List<Country> getBoard() {
        return board;
    }

    public List<Country> getAllCountries() {
        return allCountries;
    }


    //il player vince quando l'avversario non può più nominare nessun country
    public boolean hasWon(String player){
        Country lastCountry = board.getLast();
        List<Country> remainingCountries = new ArrayList<>();


        for (String s : lastCountry.getNeighbors()) {
            Country c = new Country(s);
            if (!board.contains(c))
                remainingCountries.add(c);
        }

        //se non ci sono più opzioni per il prossimo turno ed è il mio turno, ho vinto
        if (remainingCountries.isEmpty() && player.equals(turn))
            return true;
        return false;
    }

    public boolean isGameFinished(){
        return hasWon("user") || hasWon("computer");
    }

    public String getWinner(){
        if (hasWon("computer"))
            return "computer";

        if (hasWon("user"))
            return "user";

        return "NONE";
    }

    public int utility(){
        //il computer deve massimizzare, quando sa che vincerà sceglie il percorso più breve, quando sa che perderà sceglie percorso più lungo
        if (board.size() % 2 == 0)
            return -1 * (allCountries.size() - board.size());
        return (allCountries.size() - board.size());
    }


    public List<Move> generateNextMoves() {
        if (isGameFinished())
            return Collections.emptyList();

        List<Move> nextMoves = new ArrayList<>();
        Country lastCountry = board.getLast();

        System.out.print("Next moves from " + lastCountry.getName() + " : ");
        for (String s : lastCountry.getNeighbors()) {
            Country c = new Country(s);

            if (!board.contains(c)) {
                for (Country country : allCountries) {
                    if (country.getName().equals(c.getName())) {
                        c.setNeighbors(country.getNeighbors());
                    }
                }
                nextMoves.add(new BorderGuessrMove(this, c));
                System.out.print(s + " ");
            }
        }
        System.out.print("\n");

        return nextMoves;
    }
}
