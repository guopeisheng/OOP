package updatestrategy;

import model.*;
import util.Dispatcher;

/**
 * This is a concrete strategy that the ball can change its strategy
 * 

 */
public class SwitcherStrategy implements IUpdateStrategy {
	/**
	 * current strategy
	 */
	private IUpdateStrategy myStrategy;

	/**
	 * constructor of SwitcherStrategy
	 */
	public SwitcherStrategy() {
		myStrategy = new StraightStrategy();
	}

	/**
	 * change the current strategy
	 * 
	 * @param newStrategy new strategy
	 */
	public void setStrategy(IUpdateStrategy newStrategy) {
		myStrategy = newStrategy;
	}

	/**
	 * update 
	 * @param ball the corresbonding ball rotate the velocity of the ball to move in a curve
	 * @param disp dispatcher
	 */
	@Override
	public void updateState(ColorBall ball, Dispatcher disp) {
		myStrategy.updateState(ball, disp);
	}
}
