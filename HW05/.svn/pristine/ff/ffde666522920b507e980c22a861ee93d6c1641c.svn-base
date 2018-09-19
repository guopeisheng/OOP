package ballworld.model;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.Timer;

import ballworld.model.Strategy.*;
import util.IDispatcher;
import util.Randomizer;
import util.SetDispatcherSequential;

public class ballModel {
	/**
	 * MVC Model for the ball world.
	 */

	// parameters for ball world
	private IViewUpdateAdapter model2ViewAdpter;
	IViewControlAdapter viewCtrlAdpt;
	/**
	 * This define the max velocity in x and y of this model
	 */
	private Rectangle maxVel = new Rectangle(12, 12);
	/**
	 * This Define the max velocity in x and y of this model
	 */
	private Point radiRange = new Point(10, 20);
	/**
	 * This Define the max velocity in x and y of this model
	 */
	private Component canvas;

	/**
	 * The randomizer which is used to generate various random
	 */
	private Randomizer rand = Randomizer.singleton;

	/**
	 * The time interval between two timer clicks
	 */
	private int timeSlice = 30; // update every 30 milliseconds
	/**
	 * The timer for the model to know when it should notify the view to update again
	 */
	private Timer updateTimer = new Timer(timeSlice, (e) -> model2ViewAdpter.update());
	//To start the timer:_timer.start(); To stop the timer:_timer.stop();
	/**
	 * The dispatcher(observable) that model uses to notify all the balls(observers) when the painting of balls is needed
	 */
	private IDispatcher<IBallCmd> dispatcher = new SetDispatcherSequential<IBallCmd>();

	/**
	 * The _switchStrategy is used to make switchBall
	 */
	private SwitchStrategy switcherStrategy = new SwitchStrategy();

	// Constructor and other functions
	// BallModel constructor 
	public ballModel(IViewUpdateAdapter iModel2ViewAdapter, IViewControlAdapter viewCtrlAdpter) {
		model2ViewAdpter = iModel2ViewAdapter;
		viewCtrlAdpt = viewCtrlAdpter;
	}

	/**
	 * A factory for a beeping error strategy that beeps the speaker every 25 updates.
	 * Either use the _errorStrategyFac variable directly if you need a factory that makes an error strategy,
	 * or call _errorStrategyFac.make() to create an instance of a beeping error strategy.
	 */
	private IUpdateStrategyFac<IBallCmd> errorStrategyFac = new IUpdateStrategyFac<IBallCmd>() {
		@Override
		/**
		 * Make the beeping error strategy
		 * @return  An instance of a beeping error strategy
		 */
		public IUpdateStrategy<IBallCmd> make() {
			return new IUpdateStrategy<IBallCmd>() {
				private int count;

				@Override
				/**
				 * Beep the speaker every 25 updates
				 */
				public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {
					if (25 < count++) {
						java.awt.Toolkit.getDefaultToolkit().beep();
						count = 0;
					}
				}

				@Override
				public void init(Ball context) {
					// TODO Auto-generated method stub
					count = 0;
				}

			};
		}
	};

	/**
	 * IUpdateStrategyFac make a strategy factory
	 * @param name String name
	 * @return update strategy factory
	 */
	public IUpdateStrategyFac<IBallCmd> makeStrategyFac(final String name) {
		if (null == name)
			return errorStrategyFac;
		return new IUpdateStrategyFac<IBallCmd>() {
			/**
			 * Instantiate a strategy corresponding to the given class name.
			 * @return An IUpdateStrategy instance
			 */
			public IUpdateStrategy<IBallCmd> make() {
				Object[] args = new Object[] {};
				return loadUpdateStrategy(revisedName(name), args);
			}

			private String revisedName(String classname) {
				if (classname.startsWith("ballworld.model.Strategy.")) {
					if (classname.endsWith("Strategy")) {
						return classname;
					} else {
						return classname + "Strategy";
					}
				} else if (classname.endsWith("Strategy")) {
					return "ballworld.model.Strategy." + classname;
				} else
					return "ballworld.model.Strategy." + classname + "Strategy";
			}

			public String toString() {
				return name;
			}
		};
	}

	// IUpdate Strategy
	public IUpdateStrategyFac<IBallCmd> combineStrategyFacs(final IUpdateStrategyFac<IBallCmd> stratFac1,
			final IUpdateStrategyFac<IBallCmd> stratFac2) {
		if (null == stratFac1 || null == stratFac2)
			return errorStrategyFac;
		return new IUpdateStrategyFac<IBallCmd>() {

			// instantiate a new MultiStrategy with the strategies from the given strategy factories

			public IUpdateStrategy<IBallCmd> make() {
				return new MultiStrategy(stratFac1.make(), stratFac2.make());
			}
			// return a string represents that the given strategy factories concatenated with a "-"

			public String toString() {
				return stratFac1.toString() + "-" + stratFac2.toString();
			}
		};
	}

	// called by the view's adapter to the model
	public void update(Graphics g) {
		dispatcher.dispatch(new IBallCmd() {

			@Override
			public void apply(Ball context, IDispatcher<IBallCmd> disp) {
				// TODO Auto-generated method stub
				context.move();
				context.updateState(disp);
				context.bounce();
				context.paintBall(g);
			}
		});
	}

	// this method will clear all balls which observes the dispatcher in the model
	public void clearAll() {
		dispatcher.deleteObservers();
	}

	// make the straight ball with the specific strategy on it
	public void loadBall(IUpdateStrategy<IBallCmd> updateStrategy, IPaintStrategy paintStrategy) {
		Ball ball = new Ball(canvas, rand.randomLoc(canvas.getBounds()), rand.randomInt(radiRange.x, radiRange.y),
				rand.randomColor(), rand.randomVel(maxVel));
		ball.setUpdateStrategy(updateStrategy);
		ball.getUpdateStrategy();
		ball.setPaintStrategy(paintStrategy);
		ball.getPaintStrategy();
		dispatcher.addObserver(ball);
	}

	public void switchStrategy(IUpdateStrategyFac<IBallCmd> selectedItem) {
		switcherStrategy.setStrategy(selectedItem.make());
	}

	public SwitchStrategy getSwitchStrategy() {
		return this.switcherStrategy;
	}

	// This method will start timer and retrieve the display panel

	public void start() {
		canvas = this.viewCtrlAdpt.getCanvas();
		updateTimer.start();
	}

	@SuppressWarnings("unchecked")
	private IUpdateStrategy<IBallCmd> loadUpdateStrategy(String className, Object[] args) {
		try {
			java.lang.reflect.Constructor<?> cs[] = Class.forName(className).getConstructors();
			java.lang.reflect.Constructor<?> c = null;
			for (int i = 0; i < cs.length;) {
				c = cs[i];
				break;
			}
			return (IUpdateStrategy<IBallCmd>) c.newInstance(args);
		} catch (Exception ex) {
			System.err.println("Class " + className + " failed to load. \nException = \n" + ex);
			ex.printStackTrace();
			return errorStrategyFac.make();
		}
	}

	//dynamic loading
	private IPaintStrategy loadPaintStrategy(String classname, Object[] args) {
		try {
			java.lang.reflect.Constructor<?> cs[] = Class.forName(classname).getConstructors();
			java.lang.reflect.Constructor<?> c = null;
			for (int i = 0; i < cs.length; i++) {
				if (args.length == (cs[i]).getParameterTypes().length) {
					c = cs[i];
					break;
				}
			}
			return (IPaintStrategy) c.newInstance(args);
		} catch (Exception ex) {
			System.err.println("Class " + classname + "failed to load.\nException = \n" + ex);
			ex.printStackTrace();
			return errorPaintStrategyFac.make();
		}
	}

	private IPaintStrategyFac errorPaintStrategyFac = new IPaintStrategyFac() {
		@Override
		/**
		 * Make the beeping error strategy
		 * @return  An instance of a beeping error strategy
		 */
		public IPaintStrategy make() {
			return new IPaintStrategy() {
				private int count = 0; // update counter

				public void init(Ball host) {
				};

				@Override
				/**
				 * Beep the speaker every 25 updates
				 */
				public void paint(Graphics g, Ball host) {
					if (25 < count++) {
						java.awt.Toolkit.getDefaultToolkit().beep();
						count = 0;
					}
				}
			};
		}
	};

	public IPaintStrategyFac makePaintStrategyFac(java.lang.String classname) {
		if (null == classname)
			return errorPaintStrategyFac;
		return new IPaintStrategyFac() {

			public IPaintStrategy make() {
				Object[] args = new Object[] {};
				return (IPaintStrategy) loadPaintStrategy(fixPaintName(classname), args);
			}

			private String fixPaintName(String classname) {
				if (classname.startsWith("ballworld.model.paint.strategy.")) {
					if (classname.endsWith("Paintstrategy"))
						return classname;
					else
						return classname + "Paintstrategy";
				} else if (classname.endsWith("Paintstrategy")) {
					return "ballworld.model.paint.strategy." + classname;
				} else
					return "ballworld.model.paint.strategy." + classname + "PaintStrategy";
			}
			// return the given class name string

			public String toString() {
				return classname;
			}
		};
	}
}
