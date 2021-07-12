package single_port;

import java.util.*;

public class Port {
	public static final int MAX_CARGO_LOAD = 50;
	private int currentLoad = 20;
	private Berth berth = new Berth(this);
	private List<Ship> ships = new ArrayList<>(); 
	private List<Container> containers = new ArrayList<>();
	private ShipManager manager = new ShipManager(this);
	public Port() {
		int tmp = 0, cargoCapcity = 3;
		while((tmp = tmp + cargoCapcity) <= currentLoad) {
			containers.add(new Container(cargoCapcity));
		}
//		ships.addAll(manager.getLoadedShipList(ships, 3, 5, 1)); //check
//		ships.addAll(manager.getUnloadedShipList(ships, 2, 10)); //check
//		ships.add(manager.getSingleShip(10, 4, 3, true, false)); //check
//		ships.add(manager.getSingleShip(5, 0, 2, false, true)); //check
		ships.add(manager.getSingleShip(5, 5, 2, true, true)); //check
		ships.add(manager.getSingleShip(10, 10, 1, false, false)); //check
//		for(Ship ship : ships) {
//			System.out.println(ship);
//		}
	}
	public List<Container> getListContainers() {
		return containers;
	}
	public int getCurrentLoad() {
		return currentLoad;
	}
	public Berth getBerth() {
		return berth;
	}
	public List<Ship> getListShips() {
		return ships;
	}
	public void start() {
		for(Ship ship : ships) {
			Thread th = new Thread(ship);
			th.start();
		}
	}
	public String toString() {
		return getClass().getSimpleName();
	}
}
