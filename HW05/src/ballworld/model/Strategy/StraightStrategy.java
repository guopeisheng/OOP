package ballworld.model.Strategy;

import ballworld.model.Ball;
import ballworld.model.IBallCmd;
import ballworld.model.IUpdateStrategy;
import util.IDispatcher;

public class StraightStrategy implements IUpdateStrategy<IBallCmd> {

	public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {
	}

	/**
	 * @see ballworld.model.IUpdateStrategy#init(ballworld.model.Ball)
	 */
	@Override
	public void init(Ball context) {
		// no-op

	}
}
