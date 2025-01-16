import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.Algorithm;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ObjectMapper objectMapper = new ObjectMapper();
        WorldMap wm = null;
        try {
            // Leggere il file JSON e convertirlo nella classe WorldMap
             wm = objectMapper.readValue(new File("data\\allCountriesFull.json"), WorldMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(wm.getAllCountries().size());

        do {
            System.out.println("BorderGuessr!");

            System.out.println("Choose the algorithm: ");
            System.out.println("1. Minimax");
            System.out.println("2. Breadth First Search");
            System.out.println("3. Depth First Search");
            System.out.println("4. Greedy Search");
            System.out.print("Your Choice: ");
            String algorithmChoice = in.next();

            Algorithm algorithm = null;
            String response = null;
            if (algorithmChoice.equals("1")) {

                System.out.print("Do you want minimax OPTIMIZED? (Type Y for YES, any key for NO): ");
                char aiChoice = in.next().charAt(0);

                if (Character.toUpperCase(aiChoice) == 'Y') {
                    System.out.print("Do you want heuristic Minimax? (Type Y for YES, any key for NO): ");
                    char heuristicChoice = in.next().charAt(0);

                    if (Character.toUpperCase(heuristicChoice) == 'Y') {
                        algorithm = new MinimaxAIHeuristic();
                        response = "Heuristic Minimax Optimized with Alpha-Beta pruning selected...";
                    } else {
                        algorithm = new MinimaxAIAlphaBeta();
                        response = "Minimax Optimized with Alpha-Beta pruning selected...";
                    }
                } else {
                    algorithm = new MinimaxAIClassic();
                    response = "You choose Minimax Classic selected...";
                }
            } else if (algorithmChoice.equals("2")) {

                algorithm = new BreadthFirstSearch();
                response = "Breadth First Search selected...";
            } else if (algorithmChoice.equals("3")) {

                algorithm = new DepthFirstSearch();
                response = "Depth First Search selected...";
            } else if (algorithmChoice.equals("4")) {
                algorithm = new GreedySearch();
                response = "Greedy Search selected...";
            }else {
                return;
            }
            System.out.println(response);

            BorderGuessrGame game = new BorderGuessrGame(algorithm, wm.getAllCountries(), "user");

            //mio
            //Country bosnia = new Country("Bosnia ed Erzegovina"); bosnia.setNeighbors(Arrays.asList("Croazia", "Serbia", "Montenegro"));
            Country randomCountry =  wm.getAllCountries().get(new Random().nextInt(wm.getAllCountries().size()));
            game.getBoard().addCountry(randomCountry);
            //System.out.println(wm.getAllCountries().size());
            System.out.println("This match starts with: " + randomCountry.getName().toUpperCase());
            in.nextLine();

            while (!game.isFinished()) {
                System.out.print("Enter a country:");
                String name = in.nextLine();

                //System.out.println("User has said: " + name);

                Country country = new Country(name);

                List<String> neighbors = new ArrayList<>();
                for (Country c : wm.getAllCountries()) {
                    if (c.getName().equals(name)) {
                        neighbors = c.getNeighbors();
                    }
                }
                country.setNeighbors(neighbors);

                /*System.out.println("Main, Country: " + country.getName() + ", Neighbors: " + neighbors);
                for (Country c : wm.getAllCountries()) {System.out.println(c);}*/

                try {
                    game.handleTurn(country);
                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                    System.out.println("Computer WON!");
                    break;
                }
            }
        } while (playAgain(in));
    }

    private static boolean playAgain(Scanner sc) {
        System.out.print("Play again (y/n)? ");
        String playerChoice = sc.nextLine();
        return playerChoice.equalsIgnoreCase("y");
    }
}
