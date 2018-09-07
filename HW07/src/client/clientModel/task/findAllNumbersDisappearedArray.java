package client.clientModel.task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import provided.client.model.taskUtils.ITaskFactory;
import provided.compute.ILocalTaskViewAdapter;
import provided.compute.ITask;
import provided.compute.ITaskResultFormatter;

public class findAllNumbersDisappearedArray implements ITask<ArrayList<Integer>> {

	/**
	 * UID for well-defined serialization
	 */
	private static final long serialVersionUID = -4979077879785376535L;

	/**
	 * paramter num
	 */
	private String num;

	/**
	 * task view adapter
	 */
	private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;

	/**
	 * constructor
	 * @param num-number
	 */
	public findAllNumbersDisappearedArray(String num) {
		this.num = num;
	}

	/**
	 * execution of the task
	 * @see provided.compute.ITask#execute()
	 */
	@Override
	public ArrayList<Integer> execute() throws RemoteException {
		taskView.append("Calculating function PlusOne...");
		ArrayList<Integer> list = new ArrayList<>();
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < num.length(); i++) {
			hm.put(Character.getNumericValue(num.charAt(i)), i);
		}
		for (int i = 1; i <= num.length(); i++) {
			if (!hm.containsKey(i))
				list.add(i);
		}
		return list;
	}

	/**
	 * read Object
	 * @param stream-> input
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
		taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;
	}

	/**
	 * set the task view adapter
	 * @see provided.compute.ITask#setTaskViewAdapter(provided.compute.ILocalTaskViewAdapter)
	 */
	@Override
	public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
		taskView = viewAdapter;
	}

	/**
	 * Factory of the task
	 */
	public static final ITaskFactory<ArrayList<Integer>> FACTORY = new ITaskFactory<ArrayList<Integer>>() {
		public ITask<ArrayList<Integer>> make(String param) {
			return new findAllNumbersDisappearedArray(param);
		}

		public String toString() {
			return findAllNumbersDisappearedArray.class.getName();
		}
	};

	/**
	 * get the formatter of the result
	 * @see provided.compute.ITask#getFormatter()
	 */
	@Override
	public ITaskResultFormatter<ArrayList<Integer>> getFormatter() {
		return new ITaskResultFormatter<ArrayList<Integer>>() {

			public String format(ArrayList<Integer> result) {
				return num + " there are these numbers that are disappearing from the array: " + result;
			}
		};
	}
}
