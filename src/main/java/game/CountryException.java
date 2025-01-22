package game;

public class CountryException extends RuntimeException{

    private String country;

    public CountryException(String message) {
        super(message);
    }

    public CountryException(String message, String country) {
        super(message);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
