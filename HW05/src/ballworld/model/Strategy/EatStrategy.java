package ballworld.model.Strategy;

import ballworld.model.Ball;
import ballworld.model.IBallCmd;
import ballworld.model.IInteractStrategy;
import ballworld.model.IUpdateStrategy;
import util.IDispatcher;

/**
 * need to be combined with collied or other strategy(because no update state
 * @author GPS
 *
 * @param <TDispMsg>
 */
public class EatStrategy<TDispMsg> implements IUpdateStrategy<IBallCmd> {
	/**
	 * If the ball interact with a collide ball, it will grow bigger and
	 * the target ball will disappear
	 */
	@Override
	public void init(Ball context) {
		context.set_interactStrategy(new IInteractStrategy<IBallCmd>() {
			@Override
			public void interact(Ball context, Ball target, IDispatcher<IBallCmd> disp) {
				double contextMass = context.getRadius() * context.getRadius();
				double targetMass = target.getRadius() * target.getRadius();
				double newMass = contextMass + targetMass;
				context.setRadius((int) Math.sqrt(newMass));
				disp.deleteObserver(target);
			}
		});
	}

	/** (non-Javadoc)
	 * @see ballworld.model.IUpdateStrategy#updateState(ballworld.model.Ball, util.IDispatcher)
	 */
	@Override
	public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {
		//no-op
	}
}