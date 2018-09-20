/**
 * 
 */
package updatestrategy;

import model.ColorBall;
import model.IUpdateStrategy;
import util.Dispatcher;
import util.Randomizer;

/**
 * This is a concrete strategy that the ball changes color randomly
 * 
 * @author Yining Bao
 * @author Luying Wang
 */
public class ColorStrategy implements IUpdateStrategy {

	/**
	 * update
	 * @param ball the corresbonding ball change the ball's color
	 *  * @param disp dispatcher
	 */

	@Override
	public void updateState(ColorBall ball, Dispatcher disp) {

		ball.setColor(Randomizer.singleton.randomColor());
	}

}
