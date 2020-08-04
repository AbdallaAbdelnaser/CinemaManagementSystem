package entity;

public class Cineplex {
	private String name;
	private Cinema[]cinemaList;
	public Cineplex(String name, Cinema[] cinemaList) {
		this.name = name;
		this.cinemaList = cinemaList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Cinema[] getCinemaList() {
		return cinemaList;
	}
	public void setCinemaList(Cinema[] cinemaList) {
		this.cinemaList = cinemaList;
	}
	@Override
	public String toString() {
		return "name:" + name + "]";
	}

	
}
