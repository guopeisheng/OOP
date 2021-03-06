package strategy;

import model.balls.*;

/**
 * @author zihanli
 * the interface which is used to change a ball's state
 */
public interface IUpdateStrategy {
	/**
	 * @param ball
	 * it's a abstract method to update the state of a ball
	 */
	public void updateState(Ball ball);
}
