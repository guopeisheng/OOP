package ballworld.model;

import util.IDispatcher;

// Define the interface of command sent to the ball

/**
 * Define the interface of command sent to the ball
 * @author GPS
 *
 */
@FunctionalInterface
public interface IBallCmd {

	/**
	 * @param context
	 * @param dispatcher
	 */
	public abstract void apply(Ball context, IDispatcher<IBallCmd> dispatcher);

}
