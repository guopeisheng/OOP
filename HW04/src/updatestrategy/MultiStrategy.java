package updatestrategy;

import model.*;
import util.Dispatcher;

/**
 * This is a concrete strategy that combines two strategy
 * 
 * @author Yining Bao
 */
public class MultiStrategy implements IUpdateStrategy {
	/**
	 * first strategy
	 */
	private IUpdateStrategy f1;
	/**
	 * second strategy
	 */
	private IUpdateStrategy f2;

	/**
	 * update 
	 * @param ball the corresbonding ball rotate the velocity of the ball to move in a curve
	 * @param disp dispatcher
	 */
	public void updateState(ColorBall ball, Dispatcher disp) {
		f1.updateState(ball, disp);
		f2.updateState(ball, disp);
	}

	/**
	 * constructor of multistrategy
	 * 
	 * @param f1  first strategy
	 * @param f2  second strategy
	 */
	public MultiStrategy(IUpdateStrategy f1, IUpdateStrategy f2) {
		this.f1 = f1;
		this.f2 = f2;
	}
}
