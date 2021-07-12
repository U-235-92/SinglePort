package single_port;

public class Container {
	private int cargoCapcity;
	public Container(int cargoCapcity) {
		this.cargoCapcity = cargoCapcity;
	}
	public int getCargoCapcity() {
		return cargoCapcity;
	}
	public String toString() {
		return getClass().getSimpleName() + ", cargo capcity: " + cargoCapcity;
	}
}
