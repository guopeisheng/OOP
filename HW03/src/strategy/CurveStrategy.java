package strategy;

import java.awt.Point;

import model.balls.Ball;
import util.Randomizer;

/**
 * @author zihanli
 * The strategy to make the ball move in a curve path
 */
public class CurveStrategy implements IUpdateStrategy {
	private double theta = Randomizer.Singleton.randomDouble(0, 1);

	/** (non-Javadoc)
	 * @see strategy.IUpdateStrategy#updateState(model.balls.Ball)
	 * Change the direction of velocity to make the ball move in a curve path
	 */
	@Override
	public void updateState(Ball ball) {
		Point oldVel = ball.getVel();
		int velX, velY;
		velX = (int) Math.round(oldVel.getX() * Math.cos(theta) - oldVel.getY() * Math.sin(theta));
		velY = (int) Math.round(oldVel.getY() * Math.cos(theta) + oldVel.getX() * Math.sin(theta));
		ball.setVel(new Point(velX, velY));

	}

}
