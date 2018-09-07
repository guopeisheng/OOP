package client.clientModel;

import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.function.Consumer;

import client.clientModel.task.MultipleTask;
import provided.client.model.taskUtils.ITaskFactory;
import provided.compute.*;
import provided.rmiUtils.*;

/**
 *The model of the client system.
 *
 */
/**
 * @author GPS
 *
 */
/**
 * @author GPS
 *
 */
public class ClientModel {
	/**
	 * The view adapter that the server use to 
	 * append messages to this client's view.
	 */
	private IViewAdapter _iViewAdater;

	/**
	 * A copy to the stub of the remote ICompute object.
	 */
	private ICompute iCompute;

	/**
	 * The adapter that connects all the way back to the remote system's view enabling this client to append messages to the server's view.
	 */
	private IRemoteTaskViewAdapter serverTextAdapter;

	private Consumer<String> outputCmd = new Consumer<String>() {

		@Override
		public void accept(String t) {
			_iViewAdater.append(t);
		}
	};

	/**
	 * Factory for the Registry and other uses.
	 */
	IRMIUtils rmiUtils = new RMIUtils(outputCmd);

	/**
	 * The view adapter that the server can use to append messages to this client's view.
	 */
	private IRemoteTaskViewAdapter clientTA = new IRemoteTaskViewAdapter() {
		public void append(String s) {
			_iViewAdater.append("[Server] " + s);
		}
	};

	/**
	 * The view adapter that the server can use to append messages to this client's view.
	 */
	private IRemoteTaskViewAdapter clientTAstub = null;

	/**
	 * Constructor 
	 * @param _iViewAdater
	 */
	public ClientModel(IViewAdapter _iViewAdater) {
		this._iViewAdater = _iViewAdater;
	}

	/**
	 * Starts the model by setting all the required RMI system properties, 
	 * starts up the class server and installs the security manager.
	 */
	public void start() {
		rmiUtils.startRMI(IRMI_Defs.CLASS_SERVER_PORT_CLIENT);

		try {
			_iViewAdater.setRemoteHost(rmiUtils.getLocalAddress());
		} catch (Exception e) {
			System.err.println("Error getting local address: " + e);
		}
	}

	/**
	 * Stops the client
	 */
	public void quit() {
		System.out.println("ClientModel.quit(): client is quitting the program.");
		try {
			rmiUtils.stopRMI();

		} catch (Exception e) {
			System.err.println("ClientModel.quit(): Error stopping class server: " + e);
		}
		System.exit(0);
	}

	/**
	 * Connects to the given remote host and retrieves the stub to the ICompute object
	 * @param remoteHost
	 * @return a message 
	 */
	public String connectTo(String remoteHost) {
		try {
			_iViewAdater.append("Locating registry at " + remoteHost + "...\n");
			Registry registry = rmiUtils.getRemoteRegistry(remoteHost);
			_iViewAdater.append("The registry is found: " + registry + "\n");
			//'lookup' could loop up any reference,including compute engine and maybe others
			//(在这个程序里只用了一次用来找 compute engine
			iCompute = (ICompute) registry.lookup(ICompute.BOUND_NAME);
			_iViewAdater.append("Found remote Compute object which is: " + iCompute + "\n");
			if (null == clientTAstub) { // Don't re-export clientTA if already done.
				clientTAstub = (IRemoteTaskViewAdapter) UnicastRemoteObject.exportObject(clientTA,
						IRemoteTaskViewAdapter.BOUND_PORT_CLIENT);
			}
			serverTextAdapter = iCompute.setTextAdapter(clientTAstub);
			_iViewAdater.append("Get text adapter is: " + serverTextAdapter + "\n");
			serverTextAdapter.append("Hello from the client! We are HW07 Group 17, qy11 and pg23\n");
		} catch (Exception e) {
			_iViewAdater.append("Exception connecting to " + remoteHost + ": " + e + "\n");
			e.printStackTrace();
			return "No connection was established!";
		}
		return "Connection to the " + remoteHost + " established!";
	}

	public void sendMessageToServer(String text) {
		try {
			_iViewAdater.append("[ClientModel.sendMessageToServer()] Sending message to the connected remote host =  "
					+ text + "\n");
			serverTextAdapter.append(text);
		} catch (Exception e) {
			_iViewAdater.append("ERROR! [ClientModel.sendMessageToServer()]: " + e + "\n");
			e.printStackTrace();
		}

	}

	/**
	 * Run the given ITask on the remote engine server
	 * @param task
	 * @return string
	 */
	public <T> String makeBall(ITask<T> task) {
		String resultStr = "";
		try {
			T result = iCompute.executeTask(task);
			resultStr += task.getFormatter().format(result);
		} catch (Exception e) {
			_iViewAdater.append("ERROR! [ClientModel.makeBall()] Task execution exception: " + e + "\n");
			e.printStackTrace();
			resultStr += "No result.";
		}
		return resultStr;
	}

	/**
	 * Combine two tasks and generate factory
	 * @param task1
	 * @param task2
	 * @return
	 */
	public ITaskFactory<ArrayList<Object>> combineBall(ITaskFactory<?> task1, ITaskFactory<?> task2) {
		if (null == task1 || null == task2)
			_iViewAdater.append("ERROR! [ClientModel.sendMessageToServer()]: " + "\n");
		return new ITaskFactory<ArrayList<Object>>() {
			/**
			 * Instantiate a new MultiStrategy with the strategies from the given strategy factories
			 * @return A MultiStrategy instance
			 */

			public ITask<ArrayList<Object>> make(String runparameter) {
				return new MultipleTask(task1.make(runparameter), task2.make(runparameter));
			}

			public String toString() {
				return task1 + "-" + task2;
			}
		};
	}
}