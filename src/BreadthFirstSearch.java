import interfaces.Algorithm;
import interfaces.Board;
import interfaces.Move;

import java.util.*;

public class BreadthFirstSearch implements Algorithm {

    public BreadthFirstSearch() {
    }

    public Move findBestMove(Board currentBoard) {
        Move bestMove = breadthFirstSearch(currentBoard);

        //se non ci sono country che vengono fuori dalla ricerca in ampiezza, ne restituisco uno a caso tra quelli confinanti non ancora detti
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
            return new BorderGuessrMove(borderGuessrBoard, confinanti.get(new Random().nextInt(confinanti.size())));
        }

        return bestMove;
    }


    public Move breadthFirstSearch(Board currentBoard) {
        List<Nodo> frontiera = new ArrayList<>();

        BorderGuessrBoard borderGuessrBoard = (BorderGuessrBoard) currentBoard;

        List<String> saidCountries = new ArrayList<>();
        for (Country c : borderGuessrBoard.getBoard()){
            saidCountries.add(c.getName());
        }

        //inizializzo la radice dell'albero che coincide con lo stato sorteggiato dal computer o l'ultimo inserito dall'utente
        Nodo radice = new Nodo(null, 0, borderGuessrBoard.getBoard().getLast());
        frontiera.add(radice);

        Set<Nodo> esplorati = new HashSet<>();

        while (!frontiera.isEmpty()) {
            System.out.println(frontiera.size());
            Nodo nodo = frontiera.removeFirst();
            esplorati.add(nodo);

            List<String> nomiFigli = new ArrayList<>();
            System.out.println("Sto per farlo su: " + nodo.getStato());
            List<String> nomiStatiConfinantiNodoCorrente = nodo.getStato().getNeighbors();
            List<String> saidCountriesAndAncestors = new ArrayList<>();
            saidCountriesAndAncestors.addAll(saidCountries);

            Nodo n = nodo;
            while (n.getPadre() != null) {
                if (!saidCountriesAndAncestors.contains(n.getPadre().getStato().getName()))
                    saidCountriesAndAncestors.add(n.getPadre().getStato().getName());
                n = n.getPadre();
            }

            //seleziono quegli stati che confinano con il nodo corrente ma che non si trovano nel percorso verso la radice
            for (String s : nomiStatiConfinantiNodoCorrente) {
                boolean flag = false;

                for(String t : saidCountriesAndAncestors) {
                    if (t.equals(s)) {
                        flag = true;
                        break;
                    }
                }

                if(!flag) {
                    nomiFigli.add(s);
                }
            }

            //se il nodo corrente non ha figli e si trova su un cammino di lunghezza dispari
            // restituisci lo stato che si trova nel nodo immediatamente successivo alla radice,
            // altrimenti continua con l'esplorazione
            if (nomiFigli.isEmpty()) {
                if ((nodo.getCosto() + saidCountries.size()) % 2 == 1) {
                    n = nodo;

                    System.out.println("Nodo papabile : " + n.getStato().getName());
                    System.out.print("Percorso :");
                    while (n.getPadre() != radice) {
                        n = n.getPadre();
                        System.out.print(" " + n.getStato().getName());
                    }
                    System.out.println();

                    return new BorderGuessrMove(borderGuessrBoard, n.getStato());
                }
            } else {
                for (String f : nomiFigli) {
                    Country country = null;

                    for (Country c : borderGuessrBoard.getAllCountries()) {
                        if (c.getName().equals(f)) {
                            country = c;
                            break;
                        }
                    }

                    Nodo figlio = new Nodo(nodo, nodo.getCosto() + 1, country);
                    System.out.println("Sto aggiungendo : " + figlio.getStato());
                    frontiera.add(figlio);
                }
            }
        }

        return null;
    }
}
