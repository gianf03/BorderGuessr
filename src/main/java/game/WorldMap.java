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

    public Country getCountryByName(String name) {

        Country country = null;

        for(Country c : allCountries) {
            if(c.getName().equals(name)) {
                country = c;
                break;
            }
        }

        return country;
    }
}
