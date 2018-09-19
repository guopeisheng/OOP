package ballworld.model.Strategy;

import ballworld.model.*;
import util.IDispatcher;

// combine two strategies together.
public class MultiStrategy implements IUpdateStrategy<IBallCmd> {
	private IUpdateStrategy<IBallCmd> s1;
	private IUpdateStrategy<IBallCmd> s2;

	/**
	 * paint multiple strategy
	 * @param strategy1  an IUpdateStrategy strategy
	 * @param strategy2  an IUpdateStrategy strategy
	 */
	public MultiStrategy(IUpdateStrategy<IBallCmd> strategy1, IUpdateStrategy<IBallCmd> strategy2) {
		s1 = strategy1;
		s2 = strategy2;
	}

	/**
	 * update the state
	 * @param context  a Ball instance
	 * @param dispatcher  a IDispatcher instance
	 */
	public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {
		s1.updateState(context, dispatcher);
		s2.updateState(context, dispatcher);
	}

	/**
	 * Delegates to all the IPaintStrategies in the composite.
	 *  Used to initialize the paint strategies.
	 */
	@Override
	public void init(Ball context) {
		s1.init(context);
		s2.init(context);
	}

}
