import java.util.ArrayList;
import java.util.Arrays;
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
        Country austria = new Country("Austria");
        austria.setNeighbors(Arrays.asList("Germania", "Italia", "Cechia", "Ungheria", "Slovacchia", "Slovenia"));
        allCountries.add(austria);

        Country belgio = new Country("Belgio");
        belgio.setNeighbors(Arrays.asList("Lussemburgo", "Germania", "Francia", "Paesi Bassi"));
        allCountries.add(belgio);

        Country bulgaria = new Country("Bulgaria");
        bulgaria.setNeighbors(Arrays.asList("Romania", "Grecia"));
        allCountries.add(bulgaria);

        Country cechia = new Country("Cechia");
        cechia.setNeighbors(Arrays.asList("Polonia", "Germania", "Austria", "Slovacchia"));
        allCountries.add(cechia);

        Country croazia = new Country("Croazia");
        croazia.setNeighbors(Arrays.asList("Slovenia", "Ungheria"));
        allCountries.add(croazia);

        Country danimarca = new Country("Danimarca");
        danimarca.setNeighbors(Arrays.asList("Germania"));
        allCountries.add(danimarca);

        Country estonia = new Country("Estonia");
        estonia.setNeighbors(Arrays.asList("Lettonia"));
        allCountries.add(estonia);

        Country finlandia = new Country("Finlandia");
        finlandia.setNeighbors(Arrays.asList("Svezia"));
        allCountries.add(finlandia);

        Country francia = new Country("Francia");
        francia.setNeighbors(Arrays.asList("Spagna", "Germania", "Italia", "Belgio", "Lussemburgo"));
        allCountries.add(francia);

        Country germania = new Country("Germania");
        germania.setNeighbors(Arrays.asList("Lussemburgo", "Paesi Bassi", "Belgio", "Francia", "Austria", "Cechia", "Polonia", "Danimarca"));
        allCountries.add(germania);

        Country grecia = new Country("Grecia");
        grecia.setNeighbors(Arrays.asList("Bulgaria"));
        allCountries.add(grecia);

        Country italia = new Country("Italia");
        italia.setNeighbors(Arrays.asList("Slovenia", "Francia", "Austria"));
        allCountries.add(italia);

        Country lettonia = new Country("Lettonia");
        lettonia.setNeighbors(Arrays.asList("Lituania", "Estonia"));
        allCountries.add(lettonia);

        Country lituania = new Country("Lituania");
        lituania.setNeighbors(Arrays.asList("Lettonia", "Polonia"));
        allCountries.add(lituania);

        Country lussemburgo = new Country("Lussemburgo");
        lussemburgo.setNeighbors(Arrays.asList("Francia", "Germania", "Belgio"));
        allCountries.add(lussemburgo);

        Country paesiBassi = new Country("Paesi Bassi");
        paesiBassi.setNeighbors(Arrays.asList("Belgio", "Germania"));
        allCountries.add(paesiBassi);

        Country polonia = new Country("Polonia");
        polonia.setNeighbors(Arrays.asList("Germania", "Cechia", "Slovacchia", "Lituania"));
        allCountries.add(polonia);

        Country portogallo = new Country("Portogallo");
        portogallo.setNeighbors(Arrays.asList("Spagna"));
        allCountries.add(portogallo);

        Country romania = new Country("Romania");
        romania.setNeighbors(Arrays.asList("Bulgaria", "Ungheria"));
        allCountries.add(romania);

        Country slovacchia = new Country("Slovacchia");
        slovacchia.setNeighbors(Arrays.asList("Cechia", "Polonia", "Ungheria", "Austria"));
        allCountries.add(slovacchia);

        Country slovenia = new Country("Slovenia");
        slovenia.setNeighbors(Arrays.asList("Italia", "Croazia", "Ungheria", "Austria"));
        allCountries.add(slovenia);

        Country spagna = new Country("Spagna");
        spagna.setNeighbors(Arrays.asList("Francia", "Portogallo"));
        allCountries.add(spagna);

        Country svezia = new Country("Svezia");
        svezia.setNeighbors(Arrays.asList("Finlandia"));
        allCountries.add(svezia);

        Country ungheria = new Country("Ungheria");
        ungheria.setNeighbors(Arrays.asList("Slovenia", "Croazia", "Slovacchia", "Austria", "Romania"));
        allCountries.add(ungheria);

        /*Country polonia = new Country("Polonia");
        polonia.setNeighbors(Arrays.asList("Germania", "Lituania"));
        allCountries.add(polonia);

        Country germania = new Country("Germania");
        germania.setNeighbors(Arrays.asList("Danimarca", "Polonia"));
        allCountries.add(germania);


        Country danimarca = new Country("Danimarca");
        danimarca.setNeighbors(Arrays.asList("Germania"));
        allCountries.add(danimarca);

        Country lituania = new Country("Lituania");
        lituania.setNeighbors(Arrays.asList("Polonia", "Lettonia"));
        allCountries.add(lituania);

        Country lettonia = new Country("Lettonia");
        lettonia.setNeighbors(Arrays.asList("Lituania", "Estonia"));
        allCountries.add(lettonia);

        Country estonia = new Country("Estonia");
        estonia.setNeighbors(Arrays.asList("Lettonia", "Finlandia"));
        allCountries.add(estonia);

        Country cechia = new Country("Cechia");
        cechia.setNeighbors(Arrays.asList("Polonia"));
        allCountries.add(cechia);

        Country finlandia = new Country("Finlandia");
        finlandia.setNeighbors(Arrays.asList("Estonia"));
        allCountries.add(finlandia);

        /*Country lettonia = new Country("Lettonia");
        lettonia.setNeighbors(Arrays.asList("Estonia", "Lituania", "Russia"));
        allCountries.add(lettonia);

        Country lituania = new Country("Lituania");
        lituania.setNeighbors(Arrays.asList("Lettonia", "Russia"));
        allCountries.add(lituania);

        Country estonia = new Country("Estonia");
        estonia.setNeighbors(Arrays.asList("Lettonia", "Russia"));
        allCountries.add(estonia);

        Country russia = new Country("Russia");
        russia.setNeighbors(Arrays.asList("Lettonia", "Lituania", "Estonia"));
        allCountries.add(russia);
        */

    /*}*/


}
