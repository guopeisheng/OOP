package server.controller;

import server.model.EngineModel;
import server.model.IViewAdapter;
import server.view.EngineGUI;
import server.view.IModelAdapter;

/**
 * Controller for the Server (Engine) part of the system.
 *
 */
public class EngineController {

	/**
	 * view
	 */
	private EngineGUI view;

	/**
	 * model
	 */
	private EngineModel model;

	/**
	 * cosntructor
	 */
	public EngineController() {

		view = new EngineGUI(new IModelAdapter() {

			@Override
			public void sendMessageToClient(String message) {
				model.sendMessageToClient(message);
			}

			@Override
			public void quit() {
				// TODO Auto-generated method stub
				model.quit();
			}
		});
		model = new EngineModel(new IViewAdapter() {

			@Override
			public void append(String s) {
				// TODO Auto-generated method stub
				view.append(s);
			}

		});
	}

	/**
	 * start the controller
	 */
	public void start() {
		view.start();
		model.start();
	}

	/**
	 * main func
	 */
	public static void main(String[] args) {
		(new EngineController()).start();
	}

}
