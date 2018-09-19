package ballworld.model.Strategy;

import ballworld.model.Ball;
import ballworld.model.IBallCmd;
import ballworld.model.IUpdateStrategy;
import util.IDispatcher;
import util.Randomizer;

/**
 * A curve ball would move in a curve direction.
 */
public class CurveStrategy implements IUpdateStrategy<IBallCmd> {

	/**
	 * The angle that the curve ball would curve
	 */
	private double angle = 0.0;

	/**
	 * set the curve angle
	 */
	public CurveStrategy() {
		while (angle < 0.1)
			angle = Randomizer.singleton.randomDouble(-Math.PI / 20, Math.PI / 20);
	}

	/**
	 * define the velocity of the curve ball
	 */
	@Override
	public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {

		int newVelX = (int) Math.round(context.velocity.x * Math.cos(angle) - context.velocity.y * Math.sin(angle));
		int newVelY = (int) Math.round(context.velocity.y * Math.cos(angle) + context.velocity.x * Math.sin(angle));
		context.velocity.x = newVelX;
		context.velocity.y = newVelY;
	}

	@Override
	public void init(Ball context) {
		// TODO Auto-generated method stub
	}
}
