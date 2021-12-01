package model;

import java.util.Objects;

public class Beleg implements  Comparable<Beleg>{
    private String name;
    private double prijs;
    private int aantal;
    private int verkocht;


    public Beleg(String name, double prijs, int aantal, int verkocht) {
        this.name = name;
        this.prijs = prijs;
        this.aantal = aantal;
        this.verkocht = verkocht;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public int getVerkocht() {
        return verkocht;
    }

    public void setVerkocht(int verkocht) {
        this.verkocht = verkocht;
    }

    @Override
    public String toString() {
        return "Beleg{" +
                "name='" + name + '\'' +
                ", prijs=" + prijs +
                ", aantal=" + aantal +
                ", verkocht=" + verkocht +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beleg beleg = (Beleg) o;
        return Double.compare(beleg.prijs, prijs) == 0 && aantal == beleg.aantal && verkocht == beleg.verkocht && Objects.equals(name, beleg.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, prijs, aantal, verkocht);
    }

    @Override
    public int compareTo(Beleg arg0) {
        return this.getName().compareTo(arg0.getName());
    }
}
