package ballworld.model.Strategy;

import java.awt.Point;

import ballworld.model.Ball;
import ballworld.model.IBallCmd;
import ballworld.model.IUpdateStrategy;
import util.IDispatcher;

// A Breathing ball means a ball will change its size
//  it would keep increasing and the decreasing its radius again and again.

public class BreathingStrategy implements IUpdateStrategy<IBallCmd> {

	private boolean isDecreasingSize = true;
	private Point change = new Point(0, 30);

	@Override
	public void init(Ball context) {
	}

	@Override
	public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {
		if (context.radius == change.x)
			isDecreasingSize = false;
		if (context.radius == change.y)
			isDecreasingSize = true;
		if (isDecreasingSize && context.radius > change.x)
			context.radius--;
		if (!isDecreasingSize && context.radius < change.y)
			context.radius++;
	}
}
