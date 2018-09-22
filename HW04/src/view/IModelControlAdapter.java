package view;

/**
 * Adapter that the view uses to communicate to the model for non-repetitive
 * control tasks such as manipulating strategies.
 * 
 * @param TDropListItem
 *            The type of objects put into the view's drop lists.
 */

public interface IModelControlAdapter<TDropListItem, TPaintDropListItem> {
	/**
	 * Take the given short strategy name and return a corresponding something to
	 * put onto both drop lists.
	 * 
	 * @param classname The shortened class name of the desired strategy
	 * @return Something to put onto both the drop lists.
	 */

	public TDropListItem addStrategy(String classname);

	/**
	 * Make a ball with the selected short strategy name.
	 * 
	 * @param selectedItem  A shorten class name for the desired strategy
	 */
	public void makeBall(TDropListItem selectedItem1, TPaintDropListItem selectedItem2);

	/**
	 * Return a new object to put on both lists, given two items from the lists.
	 * 
	 * @param selectedItem1  An object from one drop list
	 * @param selectedItem2 An object from the other drop list
	 * @return An object to put back on both lists.
	 */

	public TDropListItem combineStrategies(TDropListItem selectedItem1, TDropListItem selectedItem2);

	/**
	 * clear
	 */
	public void clear();

	/**
	 * switch
	 * @param selectedItem  switch to specific strategy
	 */
	public void switchStrategy(TDropListItem selectedItem);

	/**
	 * make switch ball
	 * @param selectedItem  make a paint strategy ball
	 */
	public void makeSwitchBall(TPaintDropListItem selectedItem);

	/**
	 * Take the given short strategy name and return a corresponding something to
	 * put onto both drop lists.
	 * 
	 * @param classname The shortened class name of the desired strategy
	 * @return Something to put onto both the drop lists.
	 */

	public TPaintDropListItem addPaintStrategy(String classname);

	/*
	 * public static final IModelControlAdapter NULL_OBJECT=new
	 * IModelControlAdapter() { public TDropListItem addStrategy(String classname)
	 * {}; public void makeBall(TDropListItem selectedItem,Container panel) {};
	 * public TDropListItem combineStrategies(TDropListItem selectedItem1,
	 * TDropListItem selectedItem2){}; public void clear() {};
	 * 
	 * 
	 * };
	 */

}
