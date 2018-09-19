package ballworld.model;

import util.IDispatcher;

// ball-update strategy.

public interface IUpdateStrategy<TDispMsg> {

	public void init(Ball context);

	// update the state of the context Ball.
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher);

	public static final class NullFactory<TDispMsg> implements IUpdateStrategyFac<TDispMsg> {

		/**
		 * Return a  null strategy
		 */
		@Override
		public IUpdateStrategy<TDispMsg> make() {
			return new IUpdateStrategy<TDispMsg>() {

				@Override
				/**
				 * No-op
				 */
				public void init(Ball context) {
				}

				@Override
				/**
				 * No-op
				 */
				public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
				}
			};
		}
	}

}
