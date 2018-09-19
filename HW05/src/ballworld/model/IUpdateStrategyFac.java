package ballworld.model;

/**
 * Factory to make a Update Strategy
 *
 */
public interface IUpdateStrategyFac<TDispMsg> {
	//produce updatedstrategy obj
	public IUpdateStrategy<TDispMsg> make();
}
