package single_port;

import java.util.*;

public class Ship implements Runnable {
	private Berth berth;
	private int id;
	private int currentLoad;
	private int maxLoad;
	private boolean requestLoad, requestUnload;
	private volatile boolean wasLaod, wasUnload;
	private List<Container> containers = new ArrayList<>();
	public Ship(int id, int maxLoad, Berth berth, boolean requestLoad, boolean requestUnload) {
		this.berth = berth;
		this.id = id;
		this.maxLoad = maxLoad;
		this.requestLoad = requestLoad;
		this.requestUnload = requestUnload;
	}
	public Berth getBerth() {
		return berth;
	}
	public int getID() {
		return id;
	}
	public int getMaxLoad() {
		return maxLoad;
	}
	public int getCurrentLoad() {
		return currentLoad;
	}
	public List<Container> getListContainers() {
		return containers;
	}
	public void setListContainers(List<Container> containers) {
		this.containers = containers;
	}
	public boolean isRequestLoad() {
		return requestLoad;
	}
	public boolean isRequestUnload() {
		return requestUnload;
	}
	public boolean wasLoad() {
		return wasLaod;
	}
	public void wasLoad(boolean wasLoad) {
		this.wasLaod = wasLoad;
	}
	public boolean wasUnload() {
		return wasUnload;
	}
	public void wasUnload(boolean wasUnload) {
		this.wasUnload = wasUnload;
	}
	public void run() {
		berth.unloadShip(this);
		berth.loadShip(this);
	}
	public String toString() {
		return getClass().getSimpleName() + " #" + id;
	}
}
