package view;

import java.awt.Component;

/**
 * @author zihanli
 * the interface that enables the view to talk to the model to load a ball or clear balls
 */
public interface IView2ModelCtrlAdapter<TDropListItem> {

	/**
	 * @param ClassName
	 * tell the model to add a strategyFac
	 */
	public TDropListItem addStrategy(String classname);

	/**
	 * tell the model to clear all the balls
	 */
	public void clearBalls();

	/**
	 * @param window
	 * @param selectedItem
	 * tell the model to add a ball
	 */
	public void makeBall(Component window, TDropListItem selectedItem);

	/**
	 * @param s2
	 * @param s1
	 * @return
	 * tell the model to combine two strategies and get the multiStrategy
	 */
	public TDropListItem combineStrategies(TDropListItem s2, TDropListItem s1);

	/**
	 * @param newStrategy 
	 * switch the strategy of switchStrategy ball
	 */
	public void switchStrategy(TDropListItem newStrategy);

	/**
	 * tell model to make a switchStrategy ball using the switchStrategy to update
	 */
	public void makeSwitchBall(Component window);

}
