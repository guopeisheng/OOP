package strategy;

import model.balls.Ball;

/**
 * @author zihanli
 * the class used to create strategy that can change its method pf updating
 */
public class SwitchStrategy implements IUpdateStrategy {

	private IUpdateStrategy tempStrategy = new StraightStrategy();

	/** (non-Javadoc)
	 * @see strategy.IUpdateStrategy#updateState(model.balls.Ball)
	 * using temp strategy to update the ball
	 */
	@Override
	public void updateState(Ball ball) {
		// TODO Auto-generated method stub
		tempStrategy.updateState(ball);

	}

	/**
	 * @param newStrategy
	 * set  tempStrategy to a new one
	 */
	public void setStrategy(IUpdateStrategy newStrategy) {
		tempStrategy = newStrategy;
	}

}
