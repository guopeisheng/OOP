package ballworld.controller;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;

import ballworld.model.IBallCmd;
import ballworld.model.IPaintStrategyFac;
import ballworld.model.IUpdateStrategyFac;
import ballworld.model.IViewControlAdapter;
import ballworld.model.IViewUpdateAdapter;
import ballworld.model.ballModel;
import ballworld.view.MyGUI;
import ballworld.view.IModelControlAdapter;
import ballworld.view.IModelUpdateAdapter;

public class BallControl {
	private MyGUI<IUpdateStrategyFac<IBallCmd>, IPaintStrategyFac> view;
	private ballModel model;

	public BallControl() {
		// set the model field
		model = new ballModel(new IViewUpdateAdapter() {
			@Override
			public void update() {
				view.update();
			}
		}, new IViewControlAdapter() {
			@Override
			public Component getCanvas() {
				return view.getComponent();
			}

		});
		// set the view field
		view = new MyGUI<IUpdateStrategyFac<IBallCmd>, IPaintStrategyFac>(new IModelUpdateAdapter() {
			@Override
			public void update(Graphics g) {
				model.update(g);
			}
		}, new IModelControlAdapter<IUpdateStrategyFac<IBallCmd>, IPaintStrategyFac>() {
			@Override
			public IUpdateStrategyFac<IBallCmd> addStrategy(String className) {
				return model.makeStrategyFac(className);
			}

			public IPaintStrategyFac addPaintStrategy(String className) {
				return model.makePaintStrategyFac(className);
			}

			@Override
			public void makeBall(IUpdateStrategyFac<IBallCmd> UpdateStrSelectedItem,
					IPaintStrategyFac PaintSelectedItem) {
				if (null != UpdateStrSelectedItem)
					model.loadBall(UpdateStrSelectedItem.make(), PaintSelectedItem.make());
			}

			// combine two Strategies for one item
			public IUpdateStrategyFac<IBallCmd> combineStrategies(IUpdateStrategyFac<IBallCmd> SelectedItem1,
					IUpdateStrategyFac<IBallCmd> SelectedItem2) {
				return model.combineStrategyFacs(SelectedItem1, SelectedItem2);
			}

			// change the strategy of the switch ball to the selected one
			public void switchStrategy(IUpdateStrategyFac<IBallCmd> selectedItem) {
				model.switchStrategy(selectedItem);
			};

			// paint the switch ball
			public void makeSwitchBall(IPaintStrategyFac selectedItem) {
				model.loadBall(model.getSwitchStrategy(), selectedItem.make());

			};

			// clear all items
			public void clearBalls() {
				model.clearAll();
			};

		});

	}

	public void start() {
		model.start();
		view.start();
	}

	// launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // Java specs say that the system must be constructed on the GUI event thread.
			public void run() {
				try {
					BallControl controller = new BallControl(); // instantiate the system
					controller.start(); // start the system
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
