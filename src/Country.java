import java.util.ArrayList;
import java.util.List;


public class Country {
    private String name;
    private List<String> neighbors;

    public Country() {}

    public Country(String name) {
        this.name = name;
        neighbors = new ArrayList<String>();
    }

    public void addBorderCountry(String country) {
        neighbors.add(country);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNeighbors() {
        //suggerimento di Sebastiano
        List<String> neighborsCopia = new ArrayList<>(neighbors);
        return neighborsCopia;
    }

    public void setNeighbors(List<String> neighbors) {
        this.neighbors = neighbors;
    }

    public void addNeighbor(String neighbor) {
        neighbors.add(neighbor);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Country country = (Country) obj;
        return country.getName().equals(this.name);
    }

    @Override
    public String toString() {
        return "Country [name=" + name + ", neighbors=" + neighbors + "]";
    }
}
