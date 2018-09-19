package ballworld.view;

public interface IModelControlAdapter<TStrategyDropListItem, TPaintDropListItem> {

	public TStrategyDropListItem addStrategy(String className);

	public void makeBall(TStrategyDropListItem selectedItem1, TPaintDropListItem selectedObject2);

	public TStrategyDropListItem combineStrategies(TStrategyDropListItem selectedItem1,
			TStrategyDropListItem selectedItem2);

	public void switchStrategy(TStrategyDropListItem selectedItem);

	public void makeSwitchBall(TPaintDropListItem selectedItem);

	// clear all items on canvas
	public void clearBalls();

	// paint strategies
	public TPaintDropListItem addPaintStrategy(String strategy);
}
