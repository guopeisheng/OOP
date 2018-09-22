package model;

import util.Dispatcher;

/**
 * commander 
 */
public abstract interface IBallCmd {

	/**
	 * The method run by the ball's update method which is called when the ball is updated by the dispatcher.
	 * @param context ball
	 * @param disp dispatcher
	 */
	public abstract void apply(ColorBall context, Dispatcher disp);
}
