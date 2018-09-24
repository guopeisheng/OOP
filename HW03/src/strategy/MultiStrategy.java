package strategy;

import model.balls.Ball;

/**
 * @author zihanli
 * Composition design pattern
 */
public class MultiStrategy implements IUpdateStrategy {
	private IUpdateStrategy strategy1, strategy2;

	/**
	 * @param s1
	 * @param s2
	 * initialize the strategy and strategy2
	 */
	public MultiStrategy(IUpdateStrategy s1, IUpdateStrategy s2) {
		strategy1 = s1;
		strategy2 = s2;
	}

	/** (non-Javadoc)
	 * @see strategy.IUpdateStrategy#updateState(model.balls.Ball)
	 * call the method of the two strategy to update
	 */
	@Override
	public void updateState(Ball ball) {
		// TODO Auto-generated method stub
		strategy1.updateState(ball);
		strategy2.updateState(ball);

	}

}
