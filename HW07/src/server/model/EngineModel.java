package server.model;

import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Consumer;

import provided.compute.*;
import provided.rmiUtils.*;

/**
 * model of server
 *
 */
public class EngineModel {

	/**
	 * The adapter to the view
	 */
	private IViewAdapter _iViewAdater;

	/**
	 * The RMI Registry
	 */
	private Registry registry;

	/**
	 * constructor of output command
	 */
	private Consumer<String> outputCmd = new Consumer<String>() {
		@Override
		public void accept(String t) {
			_iViewAdater.append(t);
		}
	};

	/**
	 * Initialize rmi utility
	 */
	private IRMIUtils rmiUtils = new RMIUtils(outputCmd);

	/**
	 * The constructor for the class
	 * @param _iViewAdater
	 */
	public EngineModel(IViewAdapter _iViewAdater) {
		this._iViewAdater = _iViewAdater;
	}

	/**
	 * Implementation of an ICompute engine
	 */
	private ComputeEngine computeEngine;

	/**
	 * start RMI 
	 */
	public void start() {
		rmiUtils.startRMI(IRMI_Defs.CLASS_SERVER_PORT_SERVER);

		try {
			computeEngine = new ComputeEngine(outputCmd);
			_iViewAdater.append("Instantiated new Compute engine: " + computeEngine + "\n");
			ICompute stub = (ICompute) UnicastRemoteObject.exportObject(computeEngine,
					IRemoteTaskViewAdapter.BOUND_PORT_SERVER);
			_iViewAdater.append("Looking for registry ing" + "\n");

			registry = rmiUtils.getLocalRegistry();
			_iViewAdater.append("The registry is found: " + registry + "\n");
			registry.rebind(ICompute.BOUND_NAME, stub);
			_iViewAdater.append("ComputeEngine bound to " + ICompute.BOUND_NAME + "\n");
		} catch (Exception e) {
			System.err.println("ComputeEngine exception:" + "\n");
			e.printStackTrace();
			System.exit(-1);
		}
		_iViewAdater.append("Waiting" + "\n");
	}

	/**
	 * stop RMI
	 */
	public void quit() {
		try {
			registry.unbind(ICompute.BOUND_NAME);
			System.out.println("EngineController: " + ICompute.BOUND_NAME + " has been unbound.");
			rmiUtils.stopRMI();
			System.exit(0);
		} catch (Exception e) {
			System.err.println("EngineController: Error unbinding " + ICompute.BOUND_NAME + ":\n" + e);
			System.exit(-1);
		}
	}

	/**
	 * send the msg to the client
	 * @param message
	 */
	public void sendMessageToClient(String message) {
		computeEngine.sendMessageToClient(message);
	}

	/**
	 * getter of registry
	 * @return
	 */
	public Registry getRegistry() {
		return registry;
	}

	/**
	 * setter of registry of the model
	 * @param registry
	 */
	public void setRegistry(Registry registry) {
		this.registry = registry;
	}

}
