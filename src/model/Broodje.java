package model;

import java.util.Objects;

public class Broodje implements Comparable<Broodje>{
		private String name;
		private double prijs;
		private int aantal;
		private int verkocht;


	public Broodje(String name, double prijs, int aantal, int verkocht) {
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

	public void aanpassenVoorraad(int aantal) {
		this.aantal = aantal;
	}

	public int getVerkocht() {
		return verkocht;
	}

	public void setVerkocht(int verkocht) {
		this.verkocht = verkocht;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Broodje broodje = (Broodje) o;
		return Double.compare(broodje.prijs, prijs) == 0 && aantal == broodje.aantal && verkocht == broodje.verkocht && Objects.equals(name, broodje.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, prijs, aantal, verkocht);
	}

	@Override
	public String toString() {
		return "Broodje{" +
				"name='" + name + '\'' +
				", prijs=" + prijs +
				", aantal=" + aantal +
				", verkocht=" + verkocht +
				'}';
	}

	@Override
		public int compareTo(Broodje arg0) {
			return this.getName().compareTo(arg0.getName());
		}
	}

