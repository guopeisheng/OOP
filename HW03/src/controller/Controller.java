package controller;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;

import view.*;
import model.*;
import strategy.IStrategyFac;

/**
 *  The class to control the information flow between view and model
 *
 */
public class Controller {
	private MainFrame<IStrategyFac> ballView;
	private BallModel ballModel;

	Controller() {
		ballView = new MainFrame<IStrategyFac>(new IView2ModelCtrlAdapter<IStrategyFac>() {
			@Override
			public void clearBalls() {
				// TODO Auto-generated method stub
				ballModel.deleteBalls();
			}

			@Override
			/**
			* Returns an IStrategyFac that can instantiate the strategy specified
			* by classname. Returns null if classname is null. The toString() of
			* the returned factory is the classname.
			* @param classname  Shortened name of desired strategy 
			* @return A factory to make that strategy
			*/
			public IStrategyFac addStrategy(String classname) {
				return ballModel.makeStrategyFac(classname);
			}

			@Override
			/**
			* Add a ball to the system with a strategy asgiven by the given IStrategyFac
			* @param selectedItem The fully qualified name of the desired strategy.
			*/
			public void makeBall(Component window, IStrategyFac selectedItem) {
				// TODO Auto-generated method stub
				if (null != selectedItem)
					ballModel.loadBall(window, selectedItem.make()); // Here, loadBall takes a strategy object, but your method may take the strategy factory instead.

			}

			@Override
			public IStrategyFac combineStrategies(IStrategyFac s2, IStrategyFac s1) {
				// TODO Auto-generated method stub
				return ballModel.combineStrategyFacs(s2, s1);
			}

			@Override
			public void switchStrategy(IStrategyFac newStrategy) {
				// TODO Auto-generated method stub
				if (newStrategy == null)
					ballModel.swichSwichStrategy(ballModel._errorStrategyFac.make());
				else
					ballModel.swichSwichStrategy(newStrategy.make());

			}

			@Override
			public void makeSwitchBall(Component window) {
				// TODO Auto-generated method stub
				ballModel.loadBall(window, ballModel.getSwitchStrategy());

			}

		}, new IView2ModelPaintAdapter() {
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				ballModel.update(g);
			};
		});
		ballModel = new BallModel(new IModel2ViewAdapter() {

			@Override
			public void update() {
				// TODO Auto-generated method stub
				ballView.update();

			}
		});
	}

	/**
	 * Start both View and Model
	 */
	public void start() {
		ballView.start();
		ballModel.start();

	}

	/**
	 * @param args
	 * new an object of Controller and call the start method
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller controller = new Controller();
					controller.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
