package single_port;

import java.util.concurrent.TimeUnit;

public class Berth {
	private Port port;
	private volatile boolean isLoading = false;
	private volatile boolean isUnloading = true;
	public Berth(Port port) {
		this.port = port;
	}
	public Port getPort() {
		return port;
	}
	public synchronized void loadShip(Ship ship) {
		if(ship.wasLoad() && ship.wasUnload()) {
			port.getListShips().remove(ship);
			return;
		} else {
			if(ship.isRequestLoad()) {
				try {
					while(isUnloading) {
						wait();
					}			
				} catch(InterruptedException exc) {
					exc.printStackTrace();
				}
				System.out.println("Load ship... " + ship);
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch(InterruptedException exc) {
					exc.printStackTrace();
				}
				System.out.println(ship + " loaded!");
				ship.wasLoad(true);
				isLoading = false;
				isUnloading = true;
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch(InterruptedException exc) {
					exc.printStackTrace();
				}
				notifyAll();
			} else {
				if(ship.isRequestUnload()) {
					System.out.println(ship + " no need load!");
					ship.wasLoad(true);
					isLoading = false;
					isUnloading = true;
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch(InterruptedException exc) {
						exc.printStackTrace();
					}
					unloadShip(ship);
				} else {
					System.out.println(ship  + " no need load!");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch(InterruptedException exc) {
						exc.printStackTrace();
					}
					notifyAll();
				}
			}
		}
	}
	public synchronized void unloadShip(Ship ship) {
		if(ship.wasUnload() && ship.wasLoad()) {
			port.getListShips().remove(ship);
			return;
		} else {
			if(ship.isRequestUnload()) {
				try {
					while(isLoading) {
						wait();
					}			
				} catch(InterruptedException exc) {
					exc.printStackTrace();
				}
				System.out.println("Unload ship... " + ship);
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch(InterruptedException exc) {
					exc.printStackTrace();
				}
				System.out.println(ship + " unloaded!");
				ship.wasUnload(true);
				isLoading = true;
				isUnloading = false;
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch(InterruptedException exc) {
					exc.printStackTrace();
				}
				notifyAll();
			} else {
				if(ship.isRequestLoad()) {
					System.out.println(ship + " no need unload!");
					ship.wasUnload(true);
					isLoading = true;
					isUnloading = false;
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch(InterruptedException exc) {
						exc.printStackTrace();
					}
					loadShip(ship);
				} else {
					System.out.println(ship  + " no need unload!");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch(InterruptedException exc) {
						exc.printStackTrace();
					}
					notifyAll();
				}
			}
		}
	}
	public String toString() {
		return getClass().getSimpleName();
	}
}
