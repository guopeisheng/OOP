package ballworld.model.Strategy;

import ballworld.model.Ball;
import ballworld.model.IBallCmd;
import ballworld.model.IUpdateStrategy;
import util.IDispatcher;
import util.Randomizer;

// keep changing its color.
public class ColorChangingStrategy implements IUpdateStrategy<IBallCmd> {
	@Override
	public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {
		// ColorChanging balls need to update their colors
		context.color = Randomizer.singleton.randomColor();
	}

	@Override
	public void init(Ball context) {
	}
}
