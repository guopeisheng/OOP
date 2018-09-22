
package updatestrategy;

import java.awt.Point;

import model.ColorBall;
import model.IUpdateStrategy;
import util.Dispatcher;
import util.Randomizer;

/**
 * This is a concrete strategy that the ball looks like floating
 * 
 * @author Yining Bao
 * @author Luying Wang
 */
public class FloatingStrategy implements IUpdateStrategy {

	/**
	 * update 
	 * @param ball the corresbonding ball rotate the velocity of the ball to move in a curve
	 * @param disp dispatcher
	 */
	@Override
	public void updateState(ColorBall ball, Dispatcher disp) {

		ball.setVelocity(new Point(Randomizer.singleton.randomInt(-10, 10), Randomizer.singleton.randomInt(-10, 10)));
	}

}
