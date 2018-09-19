package ballworld.model.Strategy;

import ballworld.model.Ball;
import ballworld.model.IBallCmd;
import ballworld.model.IUpdateStrategy;
import util.IDispatcher;

public class SwitchStrategy implements IUpdateStrategy<IBallCmd> {

	private IUpdateStrategy<IBallCmd> strategy = (new IUpdateStrategy.NullFactory<IBallCmd>()).make();

	@Override
	public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {
		// TODO Auto-generated method stub
		strategy.updateState(context, dispatcher);
	}

	public void setStrategy(IUpdateStrategy<IBallCmd> strategy) {
		this.strategy = strategy;
	}

	@Override
	public void init(Ball context) {
	}
}
