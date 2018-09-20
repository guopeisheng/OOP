package updatestrategy;

import java.awt.Point;

import model.*;
import util.Dispatcher;

/**
 * This is a concrete strategy that the ball move curly
 * 
 * @author Yining Bao
 * @author Luying Wang
 */
public class CurveStrategy implements IUpdateStrategy {
	/**
	 * update 
	 * @param ball the corresbonding ball rotate the velocity of the ball to move in a curve
	 * @param disp dispatcher
	 */
	@Override
	public void updateState(ColorBall ball, Dispatcher disp) {
		double radians = 30 * Math.PI / 180;
		Point oldV = ball.getVelocity();
		int oldX = oldV.x;
		int oldY = oldV.y;
		int newX = (int) Math.round(oldX * Math.cos(radians) - oldY * Math.sin(radians));
		int newY = (int) Math.round(oldY * Math.cos(radians) + oldX * Math.sin(radians));
		ball.setVelocity(new Point(newX, newY));

	}
}
