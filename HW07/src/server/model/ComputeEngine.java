package server.model;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.function.Consumer;

import provided.compute.ICompute;
import provided.compute.ITask;
import provided.compute.ILocalTaskViewAdapter;
import provided.compute.IRemoteTaskViewAdapter;

/**
 * Implementation of an ICompute engine
 *
 */
public class ComputeEngine implements ICompute {

	/**
	 * constructor 
	 */
	private Consumer<String> computeEngine;

	
	/**
	 * A local task view adapter that delegates the to the main view adapter.
	 */
	private ILocalTaskViewAdapter taskView = new ILocalTaskViewAdapter() {
		public void append(String s) {
			computeEngine.accept(s);
		}
	};

	
	/**
	 * The constructor for the class
	 * @param computeEngine
	 */
	public ComputeEngine(final Consumer<String> computeEngine) {
		this.computeEngine = computeEngine;
	}

	/**
	 * Executes the given task and returns the result.
	 * @see provided.compute.ICompute#executeTask(provided.compute.ITask)
	 */
	@Override
	public <T> T executeTask(ITask<T> task) {
		try {
			task.setTaskViewAdapter(taskView);
			computeEngine.accept("Executing task... " + task + "\n");
			T result = task.execute();
			computeEngine.accept("Task result is: " + result + "\n");
			return result;
		} catch (Exception e) {
			computeEngine.accept("executeTask has an exception: " + e);
			return null;
		}
	}

	/**
	 * A remote view adapter connected to the client's view
	 */
	private IRemoteTaskViewAdapter clientTaskViewAdapter;

	/**
	 * set the text adapter based on the adapter
	 * @see provided.compute.ICompute#setTextAdapter(provided.compute.IRemoteTaskViewAdapter)
	 */
	@Override
	public IRemoteTaskViewAdapter setTextAdapter(IRemoteTaskViewAdapter clientTaskViewAdapter) {
		this.clientTaskViewAdapter = clientTaskViewAdapter;
		computeEngine.accept("Got text adapter is: " + clientTaskViewAdapter + "\n");
		IRemoteTaskViewAdapter myTA = new IRemoteTaskViewAdapter() {
			public void append(String s) {
				computeEngine.accept("(Client) " + s);
			}
		};
		try {
			this.clientTaskViewAdapter.append("Hello from the server! We are HW07 Group 17, qy11 and pg23 \n");
			IRemoteTaskViewAdapter tvaStub = (IRemoteTaskViewAdapter) UnicastRemoteObject.exportObject(myTA,
					IRemoteTaskViewAdapter.BOUND_PORT_SERVER);
			return tvaStub;
		} catch (Exception e) {
			computeEngine.accept("ComputeEngine.setClient: Exception creating remote task view adapter: " + e);
			System.err.println("ComputeEngine.setClient: " + e);
			return null;
		}
	}

	/**
	 * send the msg to client
	 * @param msg
	 */
	public void sendMessageToClient(String msg) {
		try {
			String output = "[ComputeEngine.sendMessageToClient()] Sending message to connected "
					+ "remote client. (msg = " + msg + ") \n";
			System.out.println(output);
			this.clientTaskViewAdapter.append(output);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
