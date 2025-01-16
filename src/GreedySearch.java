import interfaces.Algorithm;
import interfaces.Board;
import interfaces.Move;

import java.util.*;

public class GreedySearch implements Algorithm {
    @Override
    public Move findBestMove(Board currentBoard) {
        Move bestMove = greedySearch(currentBoard);

        //se non ci sono country che vengono fuori dalla ricerca greedy, ne restituisco uno a caso tra quelli confinanti non ancora detti
        if (bestMove == null) {
            BorderGuessrBoard borderGuessrBoard = (BorderGuessrBoard) currentBoard;
            List<Country> confinanti = new ArrayList<>();
            for (String s : borderGuessrBoard.getBoard().getLast().getNeighbors()) {
                for (Country c : borderGuessrBoard.getAllCountries()) {
                    List<String> saidCountries = new ArrayList<>();

                    for (Country country : borderGuessrBoard.getBoard()) {
                        saidCountries.add(country.getName());
                    }

                    if (c.getName().equals(s) && !saidCountries.contains(c.getName())) {
                        confinanti.add(c);
                    }
                }
            }

            if (confinanti.isEmpty())
                return null;

            System.out.print("Lista da cui verra\' generato casualmente : ");
            for (Country c : confinanti) { System.out.print(c.getName()); }
            return new BorderGuessrMove(borderGuessrBoard, confinanti.get(new Random().nextInt(confinanti.size())));
        }

        return bestMove;
    }

    //euristica: numero stati confinanti non ancora detti, meno ne hai più è probabile arrivare ad un blocco
    public Move greedySearch(Board currentBoard) {
        Comparator<Nodo> comparatorNodo = (n1, n2) -> Integer.compare(n1.getCosto(), n2.getCosto());
        PriorityQueue<Nodo> pq = new PriorityQueue<>(comparatorNodo);

        BorderGuessrBoard borderGuessrBoard = (BorderGuessrBoard) currentBoard;
        Nodo radice = new Nodo(null, calcolaEuristica(new HashSet<>(borderGuessrBoard.getBoard()), borderGuessrBoard.getBoard().getLast()), borderGuessrBoard.getBoard().getLast());
        System.out.println("Confini nodo radice : " + radice.getStato().getNeighbors());

        Nodo nodo;
        List<Nodo> cammino = new ArrayList<>();
        for (nodo = radice; nodo != null; nodo = pq.poll(), pq.clear()) {
            System.out.println("Ci sono nel primo for : " + nodo.getStato().getName() + " size: " + nodo.getCosto());

            //va sopra perché aggiungo subito lo stata in cima alla coda a priorità nel cammino
            cammino.add(nodo);

            //rimuovo dagli stati confinanti quelli già presenti nel cammino
            if (nodo.getCosto()>0) {
                List<String> unsaidNeighbors = new ArrayList<>();
                unsaidNeighbors.addAll(nodo.getStato().getNeighbors());

                for (Nodo n : cammino)
                    unsaidNeighbors.remove(n.getStato().getName());

                System.out.println();
                System.out.println("********************************************************");

                for (Country c : borderGuessrBoard.getAllCountries()) {
                    System.out.println(c);
                }

                System.out.println("********************************************************");
                System.out.println();

                System.out.println("Stati non detti ma confinanti : " + unsaidNeighbors);
                System.out.println("Nodo " + nodo.getStato().getName());

                for (String s : unsaidNeighbors) {
                    Country country = null;
                    for (Country c : borderGuessrBoard.getAllCountries()) {
                        if (s.equals(c.getName()) && !borderGuessrBoard.getBoard().contains(c)) {
                            country = c;
                            break;
                        }
                    }

                    if (country != null) {
                        System.out.print("Ci sono nel secondo for : country : " + country.getName() + " neighbors : " + country.getNeighbors().size());
                        for (String a : country.getNeighbors()){
                            System.out.print(" " + a);
                        }

                        Set<Country> unsaidCountries = new HashSet<>();
                        unsaidCountries.addAll(borderGuessrBoard.getBoard());
                        for (Nodo m : cammino)
                            unsaidCountries.add(m.getStato());

                        Nodo n = new Nodo(nodo, calcolaEuristica(unsaidCountries, country), country);
                        System.out.println("Costo: " + n.getCosto());
                        pq.add(n);
                    }
                }
            }
            /*if (pq.isEmpty()) {
                return null;
            }*/


        }
        //rimuovo il primo nodo dal cammino siccome è già stato detto dall'utente
        cammino.remove(cammino.getFirst());

        System.out.print("Cammino : ");
        for (Nodo n : cammino) {System.out.print(n.getStato().getName());}
        System.out.println();

        /*Runtime runtime = Runtime.getRuntime();
        long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory used: " + memoryUsed / (1024 * 1024) + " MB");
        System.out.println("Free memory: " + runtime.freeMemory() / (1024 * 1024) + " MB");*/

        Move bestMove = null;
        if (cammino.size() % 2 == 1) {
            bestMove = new BorderGuessrMove(borderGuessrBoard, cammino.getFirst().getStato());
        }
        return bestMove;
    }

    public int calcolaEuristica(Set<Country> saidCountries, Country currentCountry) {
        List<String> unsaidNeighbors = currentCountry.getNeighbors();

        //tolgo dagli stati confinanti quelli già detti nel percorso fino a quella nazione
        for (Country c : saidCountries) {
            unsaidNeighbors.remove(c.getName());
        }
        System.out.println("Dopo rimozione: " + unsaidNeighbors);

        System.out.print("Gia\' detti: ");
        for (Country c : saidCountries) { System.out.print(c.getName() + " "); }
        System.out.println();

        System.out.println("Euristica: " + currentCountry.getName() + " neighbors : " + unsaidNeighbors + " size: " + unsaidNeighbors.size());

        return unsaidNeighbors.size();
    }
}
