package strategy;

import util.Randomizer;
import util.SineMaker;
import model.balls.Ball;

/**
 * @author zihanli
 * The strategy to change the ball's radius
 */
public class BreathingStrategy implements IUpdateStrategy {
	/**
	 * the utility used to generate the radius
	 */
	private SineMaker makeRadius = new SineMaker(Randomizer.Singleton.randomInt(5, 10),
			Randomizer.Singleton.randomInt(20, 50), Randomizer.Singleton.randomDouble(0.1, 0.5));

	/** (non-Javadoc)
	 * @see strategy.IUpdateStrategy#updateState(model.balls.Ball)
	 * Using SineMaker to change the radius of the ball
	 */
	@Override
	public void updateState(Ball ball) {
		// TODO Auto-generated method stub
		ball.setRadius(makeRadius.getIntVal());

	}

}
