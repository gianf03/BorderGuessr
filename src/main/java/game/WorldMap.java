package game;

import java.util.ArrayList;
import java.util.List;

public class WorldMap {
    private List<Country> allCountries = new ArrayList<Country>();

    public List<Country> getAllCountries() {
        return allCountries;
    }

    public void setAllCountries(List<Country> allCountries) {
        this.allCountries = allCountries;
    }

    /*static {
        game.Country austria = new game.Country("Austria");
        austria.setNeighbors(Arrays.asList("Germania", "Italia", "Cechia", "Ungheria", "Slovacchia", "Slovenia"));
        allCountries.add(austria);

        game.Country belgio = new game.Country("Belgio");
        belgio.setNeighbors(Arrays.asList("Lussemburgo", "Germania", "Francia", "Paesi Bassi"));
        allCountries.add(belgio);

        game.Country bulgaria = new game.Country("Bulgaria");
        bulgaria.setNeighbors(Arrays.asList("Romania", "Grecia"));
        allCountries.add(bulgaria);

        game.Country cechia = new game.Country("Cechia");
        cechia.setNeighbors(Arrays.asList("Polonia", "Germania", "Austria", "Slovacchia"));
        allCountries.add(cechia);

        game.Country croazia = new game.Country("Croazia");
        croazia.setNeighbors(Arrays.asList("Slovenia", "Ungheria"));
        allCountries.add(croazia);

        game.Country danimarca = new game.Country("Danimarca");
        danimarca.setNeighbors(Arrays.asList("Germania"));
        allCountries.add(danimarca);

        game.Country estonia = new game.Country("Estonia");
        estonia.setNeighbors(Arrays.asList("Lettonia"));
        allCountries.add(estonia);

        game.Country finlandia = new game.Country("Finlandia");
        finlandia.setNeighbors(Arrays.asList("Svezia"));
        allCountries.add(finlandia);

        game.Country francia = new game.Country("Francia");
        francia.setNeighbors(Arrays.asList("Spagna", "Germania", "Italia", "Belgio", "Lussemburgo"));
        allCountries.add(francia);

        game.Country germania = new game.Country("Germania");
        germania.setNeighbors(Arrays.asList("Lussemburgo", "Paesi Bassi", "Belgio", "Francia", "Austria", "Cechia", "Polonia", "Danimarca"));
        allCountries.add(germania);

        game.Country grecia = new game.Country("Grecia");
        grecia.setNeighbors(Arrays.asList("Bulgaria"));
        allCountries.add(grecia);

        game.Country italia = new game.Country("Italia");
        italia.setNeighbors(Arrays.asList("Slovenia", "Francia", "Austria"));
        allCountries.add(italia);

        game.Country lettonia = new game.Country("Lettonia");
        lettonia.setNeighbors(Arrays.asList("Lituania", "Estonia"));
        allCountries.add(lettonia);

        game.Country lituania = new game.Country("Lituania");
        lituania.setNeighbors(Arrays.asList("Lettonia", "Polonia"));
        allCountries.add(lituania);

        game.Country lussemburgo = new game.Country("Lussemburgo");
        lussemburgo.setNeighbors(Arrays.asList("Francia", "Germania", "Belgio"));
        allCountries.add(lussemburgo);

        game.Country paesiBassi = new game.Country("Paesi Bassi");
        paesiBassi.setNeighbors(Arrays.asList("Belgio", "Germania"));
        allCountries.add(paesiBassi);

        game.Country polonia = new game.Country("Polonia");
        polonia.setNeighbors(Arrays.asList("Germania", "Cechia", "Slovacchia", "Lituania"));
        allCountries.add(polonia);

        game.Country portogallo = new game.Country("Portogallo");
        portogallo.setNeighbors(Arrays.asList("Spagna"));
        allCountries.add(portogallo);

        game.Country romania = new game.Country("Romania");
        romania.setNeighbors(Arrays.asList("Bulgaria", "Ungheria"));
        allCountries.add(romania);

        game.Country slovacchia = new game.Country("Slovacchia");
        slovacchia.setNeighbors(Arrays.asList("Cechia", "Polonia", "Ungheria", "Austria"));
        allCountries.add(slovacchia);

        game.Country slovenia = new game.Country("Slovenia");
        slovenia.setNeighbors(Arrays.asList("Italia", "Croazia", "Ungheria", "Austria"));
        allCountries.add(slovenia);

        game.Country spagna = new game.Country("Spagna");
        spagna.setNeighbors(Arrays.asList("Francia", "Portogallo"));
        allCountries.add(spagna);

        game.Country svezia = new game.Country("Svezia");
        svezia.setNeighbors(Arrays.asList("Finlandia"));
        allCountries.add(svezia);

        game.Country ungheria = new game.Country("Ungheria");
        ungheria.setNeighbors(Arrays.asList("Slovenia", "Croazia", "Slovacchia", "Austria", "Romania"));
        allCountries.add(ungheria);

        /*game.Country polonia = new game.Country("Polonia");
        polonia.setNeighbors(Arrays.asList("Germania", "Lituania"));
        allCountries.add(polonia);

        game.Country germania = new game.Country("Germania");
        germania.setNeighbors(Arrays.asList("Danimarca", "Polonia"));
        allCountries.add(germania);


        game.Country danimarca = new game.Country("Danimarca");
        danimarca.setNeighbors(Arrays.asList("Germania"));
        allCountries.add(danimarca);

        game.Country lituania = new game.Country("Lituania");
        lituania.setNeighbors(Arrays.asList("Polonia", "Lettonia"));
        allCountries.add(lituania);

        game.Country lettonia = new game.Country("Lettonia");
        lettonia.setNeighbors(Arrays.asList("Lituania", "Estonia"));
        allCountries.add(lettonia);

        game.Country estonia = new game.Country("Estonia");
        estonia.setNeighbors(Arrays.asList("Lettonia", "Finlandia"));
        allCountries.add(estonia);

        game.Country cechia = new game.Country("Cechia");
        cechia.setNeighbors(Arrays.asList("Polonia"));
        allCountries.add(cechia);

        game.Country finlandia = new game.Country("Finlandia");
        finlandia.setNeighbors(Arrays.asList("Estonia"));
        allCountries.add(finlandia);

        /*game.Country lettonia = new game.Country("Lettonia");
        lettonia.setNeighbors(Arrays.asList("Estonia", "Lituania", "Russia"));
        allCountries.add(lettonia);

        game.Country lituania = new game.Country("Lituania");
        lituania.setNeighbors(Arrays.asList("Lettonia", "Russia"));
        allCountries.add(lituania);

        game.Country estonia = new game.Country("Estonia");
        estonia.setNeighbors(Arrays.asList("Lettonia", "Russia"));
        allCountries.add(estonia);

        game.Country russia = new game.Country("Russia");
        russia.setNeighbors(Arrays.asList("Lettonia", "Lituania", "Estonia"));
        allCountries.add(russia);
        */

    /*}*/


}
