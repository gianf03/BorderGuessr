public class Nodo {

    private Nodo padre;
    private int costo;
    private Country stato;

    public Nodo(Nodo padre, int costo, Country stato) {
        this.padre = padre;
        this.costo = costo;
        this.stato = stato;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }


    public Country getStato() {
        return stato;
    }

    public void setStato(Country stato) {
        this.stato = stato;
    }
}
