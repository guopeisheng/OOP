package model;

import java.awt.Component;
import strategy.*;
import java.awt.Graphics;
import javax.swing.Timer;

import model.balls.Ball;
import util.Dispatcher;

/**
 * @author zihanli,yiranwei
 * The model that drives the view MainFrame
 */
public class BallModel {
	private IModel2ViewAdapter _model2ViewAdpt = IModel2ViewAdapter.NULL_OBJECT; // Insures that the adapter is always valid
	private int _timeSlice = 50; // update every 50 milliseconds
	private Dispatcher myDispatcher = new Dispatcher();
	private Timer _timer = new Timer(_timeSlice, (e) -> _model2ViewAdpt.update());
	/**
	 * the strategy that is used when the class is wrong
	 */
	public IStrategyFac _errorStrategyFac;
	private SwitchStrategy _switcher = new SwitchStrategy();

	/**
	 * @param g
	 * notify all the balls to paint
	 */
	public void update(Graphics g) {
		myDispatcher.notifyAll(g); // The Graphics object is being given to all the sprites (Observers)
	}

	/**
	 * @return the SwitchStrategy
	 */
	public SwitchStrategy getSwitchStrategy() {
		return _switcher;
	}

	/**
	 * @param newStrategy change the temp strategy to a new strategy
	 */
	public void swichSwichStrategy(IUpdateStrategy newStrategy) {
		_switcher.setStrategy(newStrategy);
	}

	/**
	 * @param model2ViewAdpt 
	 * use IModel2ViewAdapter to initialize the class
	 */
	public BallModel(IModel2ViewAdapter model2ViewAdpt) {
		_model2ViewAdpt = model2ViewAdpt;
		_errorStrategyFac = new IStrategyFac() {

			@Override
			/**
			 * Make the beeping error strategy
			 * @return  An instance of a beeping error strategy
			 */
			public IUpdateStrategy make() {
				return new IUpdateStrategy() {
					private int count = 0; // update counter

					@Override
					/**
					 * Beep the speaker every 25 updates
					 */
					public void updateState(Ball context) {
						if (25 < count++) {
							java.awt.Toolkit.getDefaultToolkit().beep();
							count = 0;
						}
					}
				};
			}

		};
	}

	/**
	 * Delete all observers from the dispatcher and from the screen
	 */
	public void deleteBalls() {
		myDispatcher.deleteObservers();
	}

	/**
	 * Returns an IStrategyFac that can instantiate the strategy specified by
	 * classname. Returns a factory for a beeping error strategy if classname is null. 
	 * The toString() of the returned factory is the classname.
	 * 
	 * @param classname  Shortened name of desired strategy
	 * @return A factory to make that strategy
	 */
	public IStrategyFac makeStrategyFac(String classname) {
		IStrategyFac strategyFac = new IStrategyFac() {
			/**
			 * Instantiate a strategy corresponding to the given class name.
			 * @return An IUpdateStrategy instance
			 */
			public IUpdateStrategy make() {
				return loadStrategy(fixName(classname)) == null ? new StraightStrategy()
						: loadStrategy(fixName(classname));
			}

			public String toString() {
				return classname;
			}
		};
		return strategyFac;
	}

	/**
	 * Returns an IStrategyFac that can instantiate a MultiStrategy with the two
	 * strategies made by the two given IStrategyFac objects. Returns null if
	 * either supplied factory is null. The toString() of the returned factory
	 * is the toString()'s of the two given factories, concatenated with "-". 
	 * If either factory is null, then a factory for a beeping error strategy is returned.
	 * 
	 * @param stratFac1 An IStrategyFac for a strategy
	 * @param stratFac2 An IStrategyFac for a strategy
	 * @return An IStrategyFac for the composition of the two strategies
	 */
	public IStrategyFac combineStrategyFacs(final IStrategyFac stratFac1, final IStrategyFac stratFac2) {
		if (null == stratFac1 || null == stratFac2)
			return _errorStrategyFac;
		return new IStrategyFac() {
			/**
			 * Instantiate a new MultiStrategy with the strategies from the given strategy factories
			 * @return A MultiStrategy instance
			 */
			public IUpdateStrategy make() {
				return new MultiStrategy(stratFac1.make(), stratFac2.make());
			}

			/**
			 * Return a string that is the toString()'s of the given strategy factories concatenated with a "-"
			 */
			public String toString() {
				return stratFac1.toString() + "-" + stratFac2.toString();
			}
		};
	}

	/**
	 * Use the classname to create an object of IUpdateStrategy
	 * @param classname
	 * @return
	 */
	public IUpdateStrategy loadStrategy(String classname) {
		try {
			java.lang.reflect.Constructor<?> cs[] = Class.forName(classname).getConstructors(); // get all the constructors
			java.lang.reflect.Constructor<?> c = null;
			for (int i = 0; i < cs.length; i++) { // find the first constructor with the right number of input parameters
				if (0 == (cs[i]).getParameterTypes().length) {
					c = cs[i];
					break;
				}
			}
			return (IUpdateStrategy) c.newInstance();
			//IUpdateStrategy strategy=(IUpdateStrategy) Class.forName(classname).newInstance();
			//return strategy;
		} catch (Exception e) {
			System.err.println("Class " + classname + " failed to load. \nException = \n" + e);
			e.printStackTrace(); // print the stack trace to help in debugging.
			return null; // Is this really a useful thing to return here?  Is there something better that could be returned?
		} catch (Error err) {
			return null;
		}
	}

	/**
	 * @param window
	 * @param strategy
	 * To create a ball
	 */
	public void loadBall(Component window, IUpdateStrategy strategy) {
		Ball ball = new Ball(window, strategy);
		myDispatcher.addObserver(ball);
	}

	/**
	 * @param classname
	 * @return
	 * make sure to support  either full or abbreviated strategy class name
	 */
	public String fixName(String classname) {
		if (classname.length() >= 9) {
			if (classname.substring(0, 8) == "strategy.")
				return classname;
		}
		return "strategy." + classname + "Strategy";

	}

	/**
	 * start the timer to make the animations
	 */
	public void start() {
		_timer.start();
	}

}
