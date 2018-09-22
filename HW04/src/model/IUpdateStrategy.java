package model;

import util.Dispatcher;

/**
 * interface
 *
 */
public interface IUpdateStrategy {
	/**
	 * update the state of the passing ball
	 * 
	 * @param ball  current ball
	 */
	public void updateState(ColorBall ball, Dispatcher dispatcher);

}
