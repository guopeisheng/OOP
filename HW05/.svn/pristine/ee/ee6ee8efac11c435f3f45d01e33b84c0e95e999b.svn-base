package util;

import java.util.Observable;

public class Dispatcher extends Observable {
	// notifies all the Observers.
	public void notifyAll(Object param) {
		setChanged();
		notifyObservers(param);
	}

}