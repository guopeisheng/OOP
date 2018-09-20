
package updatestrategy;

import model.ColorBall;
import model.IUpdateStrategy;
import util.Dispatcher;
import util.SineMaker;

/**
 * This is a concrete strategy that the ball is breathing
 * 
 * @author Yining Bao
 * @author Luying Wang
 */
public class BreathingStrategy implements IUpdateStrategy {
	/**
	 * after count equals to 0, ball's radius change
	 */
	private int cnt = 3;

	/**
	 * create smoothly varying sinusoidal numerical data for radius
	 */
	SineMaker smoothRadius = new SineMaker(10, 25, 30);

	/**
	 * update
	 * @param ball the corresbonding ball change the ball's radius
	 * @param disp dispatcher
	 */
	@Override
	public void updateState(ColorBall ball, Dispatcher disp) {

		cnt--;
		if (cnt == 0) {
			ball.setRadius(smoothRadius.getIntVal());
			cnt = 3;
		}

	}

}
