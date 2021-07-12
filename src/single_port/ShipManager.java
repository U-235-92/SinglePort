package single_port;

import java.util.*;

public class ShipManager {
	private Port port;
	public ShipManager(Port port) {
		this.port = port;
	}
	private void getListShips(List<Ship> where, List<Ship> ships, int countShips, int maxLoad, 
			boolean requestLoad, boolean requestUnload) {
		if(where.size() > 0) {
			List<Ship> tmp = new ArrayList<>();
			for(int i = where.size(); i < countShips + where.size(); i++) {
				tmp.add(new Ship(i + 1, maxLoad,  port.getBerth(), requestLoad, requestUnload));
			}
			ships.addAll(tmp);
		} else {
			for(int i = 0; i < countShips; i++) {
				ships.add(new Ship(i + 1, maxLoad,  port.getBerth(), requestLoad, requestUnload));
			}
		}
	}
	private void addContainers(List<Ship> ships, int cargoCapcity) {
		for(Ship ship : ships) {
			List<Container> containers = new ArrayList<>();
			int tmp = 0;
			while(tmp < ship.getMaxLoad()) {
				containers.add(new Container(cargoCapcity));
				tmp += cargoCapcity;
				if(tmp > ship.getMaxLoad()) {
					containers.add(new Container(tmp - ship.getMaxLoad()));
					break;
				}
			}
			ship.setListContainers(containers);
		}
	}
	public List<Ship> getLoadedShipList(List<Ship> where, int countShips, int maxLoadShip,
			int cargoCapcityContainer) {
		List<Ship> ships = new ArrayList<>();
		getListShips(where, ships, countShips, maxLoadShip, false, true);
		addContainers(ships, cargoCapcityContainer);
		return ships;
	}
	public List<Ship> getUnloadedShipList(List<Ship> where, int countShips, int maxLoadShip) {
		List<Ship> ships = new ArrayList<>();
		getListShips(where, ships, countShips, maxLoadShip, true, false);
		return ships;
	}
	public Ship getSingleShip(int maxLoadShip, int currentLoadShip, int cargoCapcityContainer,
			boolean requestLoad, boolean requestUnload) {
		Ship ship = new Ship(port.getListShips().size() + 1, maxLoadShip, port.getBerth(), 
				requestLoad, requestUnload);
		List<Container> containers = new ArrayList<Container>();
		int tmp = 0;
		if(cargoCapcityContainer > currentLoadShip) {
			cargoCapcityContainer = currentLoadShip;
		}
		if(cargoCapcityContainer > maxLoadShip) {
			cargoCapcityContainer = currentLoadShip;
		}
		if(currentLoadShip == maxLoadShip) {
			if(cargoCapcityContainer > 0) {
				while(tmp < currentLoadShip) {
					containers.add(new Container(cargoCapcityContainer));
					tmp += cargoCapcityContainer;
					if(tmp + currentLoadShip > currentLoadShip) {
						containers.add(new Container(currentLoadShip - tmp));
						break;
					}
				}
			} else {
				cargoCapcityContainer = 1;
				while(tmp < currentLoadShip) {
					containers.add(new Container(cargoCapcityContainer));
					tmp += cargoCapcityContainer;
				}
			}
		} 
		if(currentLoadShip != 0 && currentLoadShip < maxLoadShip) {
			while(tmp < currentLoadShip) {
				containers.add(new Container(cargoCapcityContainer));
				tmp += cargoCapcityContainer;
				if(tmp + currentLoadShip > currentLoadShip) {
					containers.add(new Container(currentLoadShip - tmp));
					break;
				}
			}
		} 
		ship.setListContainers(containers);
		return ship;
	}
}
