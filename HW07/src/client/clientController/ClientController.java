package client.clientController;

import client.clientModel.ClientModel;
import client.clientView.ClientGUI;
import client.clientView.IModelAdapter;
import provided.client.model.taskUtils.ITaskFactory;
import provided.client.model.taskUtils.SingletonTaskFactoryLoader;
import client.clientModel.IViewAdapter;

/**
 * Controller for the Client MVC system.
 *
 */
public class ClientController {

	/**
	 * The view of the client
	 */
	private ClientGUI<ITaskFactory<?>> view;

	/**
	 * The model of the client
	 */
	private ClientModel model;

	/**
	 * Constructor of the ClientController
	 */
	public ClientController() {

		model = new ClientModel(new IViewAdapter() {

			@Override
			public void append(String s) {
				view.append(s);
			}

			@Override
			public void setRemoteHost(String hostAddress) {
				view.setRemoteHost(hostAddress);
			}
		});

		view = new ClientGUI<ITaskFactory<?>>(new IModelAdapter<ITaskFactory<?>>() {

			@Override
			public String connectTo(String remoteHost) {
				return model.connectTo(remoteHost);
			}

			@Override
			public void quit() {
				model.quit();
			}

			@Override
			public void sendMessageToServer(String text) {
				// TODO Auto-generated method stub
				model.sendMessageToServer(text);
			}

			@Override
			public ITaskFactory<?> addStrategy(String classname) {
				String className = "client.clientModel.task." + classname;
				return SingletonTaskFactoryLoader.SINGLETON.load(className);
			}

			@Override
			public String makeBall(ITaskFactory<?> classname, String runparameter) {
				return model.makeBall(classname.make(runparameter));
			}

			@Override
			public ITaskFactory<?> combineBall(ITaskFactory<?> task1, ITaskFactory<?> task2) {
				return model.combineBall(task1, task2);
			}

			//			@Override
			//			public IStrategyFac addStrategy(String classname) {
			//				//return model.makeStrategyFac(classname);
			//			}

			//			@Override
			//			public void makeBall(IStrategyFac strategyFac) {
			//				//model.loadBall(strategyFac.make());
			//			}

		});

	}

	/**
	 * Starts the view then the model. The view needs to be started first 
	 * so that it can display the model status updates as it starts.
	 */
	public void start() {
		view.addToTUpdateDropListItem(SingletonTaskFactoryLoader.SINGLETON.load("provided.client.model.task.GetInfo"));
		view.addToTUpdateDropListItem(SingletonTaskFactoryLoader.SINGLETON.load("provided.client.model.task.Pi2"));
		view.addToTUpdateDropListItem(
				SingletonTaskFactoryLoader.SINGLETON.load("client.clientModel.task.findAllNumbersDisappearedArray"));
		view.addToTUpdateDropListItem(SingletonTaskFactoryLoader.SINGLETON.load("client.clientModel.task.PlusOne"));
		view.addToTUpdateDropListItem(
				SingletonTaskFactoryLoader.SINGLETON.load("client.clientModel.task.ReverseVowelsofString"));
		view.start();
		model.start();
	}

	/**
	 * main 
	 * @param args
	 */
	public static void main(String[] args) {
		(new ClientController()).start();
	}
}
