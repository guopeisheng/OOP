package updatestrategy;

import java.awt.Point;

import model.ColorBall;
import model.IUpdateStrategy;
import util.Dispatcher;
import util.Randomizer;

/**
 * This is a concrete strategy that the ball is abstracted by the right side of
 * the wall
 * 
 * @author Yining Bao
 */
public class MagneticStrategy implements IUpdateStrategy {
	/**
	 * after count equals to 0, ball stop curve and go to the right wall
	 */
	int cnt = 50;

	/**
	 * update 
	 * @param ball the corresbonding ball rotate the velocity of the ball to move in a curve
	 * @param disp dispatcher
	 */
	@Override
	public void updateState(ColorBall ball, Dispatcher disp) {
		cnt--;
		if (cnt > 0) {
			double radians = 30 * Math.PI / 180;
			Point oldV = ball.getVelocity();
			int oldX = oldV.x;
			int oldY = oldV.y;
			int newX = (int) Math.round(oldX * Math.cos(radians) - oldY * Math.sin(radians));
			newX += Randomizer.singleton.randomInt(5, 10);
			int newY = (int) Math.round(oldY * Math.cos(radians) + oldX * Math.sin(radians));
			ball.setVelocity(new Point(newX, newY));
		} else {
			ball.setVelocity(new Point(Randomizer.singleton.randomInt(5, 10), Randomizer.singleton.randomInt(-5, 5)));
		}

	}

}
