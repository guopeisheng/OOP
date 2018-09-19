package ballworld.model.Strategy;

import ballworld.model.Ball;
import ballworld.model.IBallCmd;
import ballworld.model.IInteractStrategy;
import ballworld.model.IUpdateStrategy;
import util.IDispatcher;

/**
 * @author GPS
 *
 */
public class AverageMassStrategy implements IUpdateStrategy<IBallCmd> {
	/** 
	 * @see ballworld.model.IUpdateStrategy#init(ballworld.model.Ball)
	 */
	public void init(Ball context) {
		context.set_interactStrategy(new IInteractStrategy<IBallCmd>() {
			@Override
			public void interact(Ball context, Ball targetBall, IDispatcher<IBallCmd> dispatcher) {

				if (context.getRadius() > targetBall.getRadius()) {
					context.setRadius(context.getRadius() - (context.getRadius() - targetBall.getRadius()) / 2);
					targetBall.setRadius(targetBall.getRadius() + (context.getRadius() - targetBall.getRadius()) / 2);
				} else {
					context.setRadius(context.getRadius() + (targetBall.getRadius() - context.getRadius()) / 2);
					targetBall.setRadius(targetBall.getRadius() - (targetBall.getRadius() - context.getRadius()) / 2);
				}
			}
		});
	}

	/** (non-Javadoc)
	 * @see ballworld.model.IUpdateStrategy#updateState(ballworld.model.Ball, util.IDispatcher)
	 */
	@Override
	public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {
	}
}