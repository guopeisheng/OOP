package ballworld.model;

import util.IDispatcher;

/**
 * @author GPS
 *
 * @param <TCommand>
 */
public interface IInteractStrategy<TCommand> {

	/**
	 * @param context
	 * @param target
	 * @param disp
	 */
	public void interact(Ball context, Ball target, IDispatcher<TCommand> disp);

	/**
	 * 
	 */
	public static final IInteractStrategy<IBallCmd> nullStrategy = (context, target, disp) -> {
		//No-op
	};
}
