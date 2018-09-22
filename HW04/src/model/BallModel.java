package model;

import java.awt.Component;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import updatestrategy.*;
import util.Dispatcher;
import util.IRandomizer;
import util.Randomizer;

/**
 * 
 * ballmodel
 *
 */
public class BallModel {
	/**
	 * max radius
	 */
	private int _maxR = 25;
	/**
	 * min radius
	 */
	private int _minR = 5;
	/**
	 * max speed
	 */
	private int _maxSpeed = 15;
	/**
	 * randomizer
	 */
	private IRandomizer rand = Randomizer.singleton;
	/**
	 * Insures that the adapter is always valid
	 */
	private IModel2ViewAdapter _model2ViewAdpt = IModel2ViewAdapter.NULL_OBJECT;
	/**
	 * time slice for updating
	 */
	private int _painttimeSlice = 60; // update every 60 milliseconds
	/**
	 * timer for updating
	 */
	private Timer _paintTimer = new Timer(_painttimeSlice, (e) -> _model2ViewAdpt.update());// need to start and stop the timer
	/**
	 * dispatcher the observer
	 */
	private Dispatcher dispatcher = new Dispatcher();
	/**
	 * max velocity
	 */
	private Rectangle _maxVel = new Rectangle(_maxSpeed, _maxSpeed, _maxSpeed, _maxSpeed);
	/**
	 * switcher
	 */
	private SwitcherStrategy switcher = new SwitcherStrategy();
	/**
	 * update time slice
	 */
	private int _updateTimeSlice = 60;
	/**
	 * update timer
	 */
	private Timer _updateTimer = new Timer(_updateTimeSlice, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispatcher.notifyAll(new IBallCmd() {
				@Override
				public void apply(ColorBall context, Dispatcher disp) {
					context.updateState(disp);
					context.move();
					context.bounce();
				}
			});
		}
	});

	/**
	 * constructor
	 * 
	 * @param model2ViewAdpt adapter
	 */
	public BallModel(IModel2ViewAdapter model2ViewAdpt) {
		this._model2ViewAdpt = model2ViewAdpt;
	}

	/**
	 * start
	 */
	public void start() {
		_paintTimer.start();
		_updateTimer.start();
	}

	/**
	 * this method is called by view2model adapter
	 * 
	 * @param g place to draw
	 */
	public void paint(Graphics g) {
		//dispatcher.notifyAll(g);
		dispatcher.notifyAll((IBallCmd) (context, disp) -> {

			context.paint((Graphics) g);
			// Whatever you want to do with the context Ball!

		});

	}

	/**
	 * clear
	 */
	public void clearBalls() {
		dispatcher.deleteObservers();
	}

	/**
	 * get the switcherStrategy 
	 * @return  switcherStrategy 
	 */
	public IUpdateStrategy getSwitcherStrategy() {
		if (null == this.switcher)
			this.switcher = new SwitcherStrategy();
		return this.switcher;
	}

	/**
	 * Set  switcherStrategy .
	 * @param newStrategy .
	 */
	public void switchSwitcherStrategy(IUpdateStrategy newStrategy) {
		this.switcher.setStrategy(newStrategy);
	}

	/**
	 * A factory for a beeping error strategy that beeps the speaker every 25
	 * updates. Either use the _errorStrategyFac variable directly if you need a
	 * factory that makes an error strategy, or call _errorStrategyFac.make() to
	 * create an instance of a beeping error strategy.
	 */
	private IStrategyFac _errorStrategyFac = new IStrategyFac() {
		@Override
		/**
		 * Make the beeping error strategy
		 * 
		 * @return An instance of a beeping error strategy
		 */
		public IUpdateStrategy make() {
			return new IUpdateStrategy() {
				private int count = 0; // update counter

				@Override
				/**
				 * Beep the speaker every 25 updates
				 */
				public void updateState(ColorBall context, Dispatcher disp) {
					if (25 < count++) {
						java.awt.Toolkit.getDefaultToolkit().beep();
						count = 0;
					}
				}
			};
		}
	};
	/**
	 * error paint strategy fac
	 */
	private IPaintStrategyFac _errorPaintStrategyFac = new IPaintStrategyFac() {
		@Override

		public IPaintStrategy make() {
			return IPaintStrategy.NULL_OBJECT;
		}
	};

	/**
	 * The following method returns an instance of an ABall, given a fully qualified
	 * class name (package.classname) of a subclass of ABall. The method assumes
	 * that there is only one constructor for the supplied class that has the same
	 * *number* of input parameters as specified in the args array and that it
	 * conforms to a specific signature, i.e. specific order and types of input
	 * parameters in the args array.
	 * 
	 * 
	 * @param strategy strategy
	 * @param paintStrategy paint strategy
	 * @return An instance of the supplied class.
	 */
	public void loadBall(IUpdateStrategy strategy, IPaintStrategy paintStrategy) {

		// Randomizer is implemented using Singleton Pattern
		// centerPanel getBounds

		Component canvas = _model2ViewAdpt.getComponent();

		ColorBall myball = new ColorBall(rand.randomLoc(canvas.getSize()), rand.randomInt(_minR, _maxR),
				rand.randomVel(_maxVel), rand.randomColor(), canvas, strategy, paintStrategy);
		dispatcher.addObserver(myball);

	}

	/**
	 * Returns an IStrategyFac that can instantiate the strategy specified by
	 * classname. Returns a factory for a beeping error strategy if classname is
	 * null. The toString() of the returned factory is the classname.
	 * 
	 * @param classname  Shortened name of desired strategy
	 * @return A factory to make that strategy
	 */
	public IStrategyFac makeStrategyFac(final String classname) {
		if (null == classname)
			return _errorStrategyFac;

		return new IStrategyFac() {

			/**
			 * 
			 * Instantiate a strategy corresponding to the given class name.
			 * 
			 * @return An IUpdateStrategy instance
			 * 
			 */

			public IUpdateStrategy make() {

				return loadStrategy(fixName("updatestrategy.", classname, "Strategy"));

			}

			/**
			 * 
			 * Return the given class name string
			 * 
			 */

			public String toString() {

				return classname;

			}

		};

	}

	/**
	 * 
	 * @param classname  name
	 * @return strategy name
	 */
	public String fixName(String pakeage, String classname, String suffix) {
		return pakeage + classname + suffix;
	}

	/**
	 * 
	 * @param className name
	 * @return strategy strategy
	 */
	public IUpdateStrategy loadStrategy(String className) {
		try {
			Object[] args = new Object[] {};
			java.lang.reflect.Constructor<?> cs[] = Class.forName(className).getConstructors();
			java.lang.reflect.Constructor<?> c = null;
			for (int i = 0; i < cs.length; i++) {
				// find the first constructor with the right number of input parameters
				if (args.length == (cs[i]).getParameterCount()) {
					c = cs[i];
					break;
				}
			}

			return (IUpdateStrategy) c.newInstance(args);
		} catch (Exception ex) {
			System.err.println("Strategy " + className + " failed to load. \nException = \n" + ex);
			ex.printStackTrace();
			return _errorStrategyFac.make();
		}
	}

	/**
	 * Returns an IStrategyFac that can instantiate a MultiStrategy with the two
	 * strategies made by the two given IStrategyFac objects. Returns null if either
	 * supplied factory is null. The toString() of the returned factory is the
	 * toString()'s of the two given factories, concatenated with "-". If either
	 * factory is null, then a factory for a beeping error strategy is returned.
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
			 * Instantiate a new MultiStrategy with the strategies from the given strategy
			 * factories
			 * 
			 * @return A MultiStrategy instance
			 */

			public IUpdateStrategy make() {

				return new MultiStrategy(stratFac1.make(), stratFac2.make());

			}

			/**
			 * Return a string that is the toString()'s of the given strategy factories
			 * concatenated with a "-"
			 * 
			 */

			public String toString() {

				return stratFac1.toString() + "-" + stratFac2.toString();

			}

		};

	}

	/**
	 *  make a paint strategy Fac
	 *  @param classname the class name of the paint strategy
	 */
	public IPaintStrategyFac makePaintStrategyFac(String classname) {
		if (null == classname)
			return _errorPaintStrategyFac;

		return new IPaintStrategyFac() {

			/**
			 * 
			 * Instantiate a strategy corresponding to the given class name.
			 * 
			 * @return An IUpdateStrategy instance
			 * 
			 */

			public IPaintStrategy make() {

				return loadPaintStrategy(fixName("paint.strategy.", classname, "PaintStrategy"));

			}

			/**
			 * 
			 * Return the given class name string
			 * 
			 */

			public String toString() {

				return classname;

			}

		};
	}

	/**
	 * load a paint strategy from given name
	 * @param name the paint strategy 
	 */
	public IPaintStrategy loadPaintStrategy(String name) {
		try {
			Object[] args = new Object[] {};
			java.lang.reflect.Constructor<?> cs[] = Class.forName(name).getConstructors();
			java.lang.reflect.Constructor<?> c = null;
			for (int i = 0; i < cs.length; i++) {
				// find the first constructor with the right number of input parameters
				if (args.length == (cs[i]).getParameterCount()) {
					c = cs[i];
					break;
				}
			}

			return (IPaintStrategy) c.newInstance(args);
		} catch (Exception ex) {
			System.err.println("Strategy " + name + " failed to load. \nException = \n" + ex);
			ex.printStackTrace();
			return _errorPaintStrategyFac.make();
		}
	}

}
