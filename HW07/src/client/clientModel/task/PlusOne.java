package client.clientModel.task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;

import provided.client.model.taskUtils.ITaskFactory;
import provided.compute.ILocalTaskViewAdapter;
import provided.compute.ITask;
import provided.compute.ITaskResultFormatter;

public class PlusOne implements ITask<Integer> {

	/**
	 * UID for well-defined serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * paramter num
	 */
	private int num;
	/**
	 * task view adapter
	 */
	private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;

	/**
	 * constructor
	 * @param num-number
	 */
	public PlusOne(int num) {
		this.num = num;
	}

	/**
	 * execution of the task
	 * @see provided.compute.ITask#execute()
	 */
	@Override
	public Integer execute() throws RemoteException {
		taskView.append("Calculating function PlusOne...\n");
		num = num + 1;
		return num;
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
	public static final ITaskFactory<Integer> FACTORY = new ITaskFactory<Integer>() {
		public ITask<Integer> make(String param) {
			return new PlusOne(Integer.parseInt(param));
		}

		public String toString() {
			return PlusOne.class.getName();
		}
	};

	/**
	 * get the formatter of the result
	 * @see provided.compute.ITask#getFormatter()
	 */
	@Override
	public ITaskResultFormatter<Integer> getFormatter() {
		return new ITaskResultFormatter<Integer>() {

			public String format(Integer result) {
				return num + " + 1 = " + result;
			}
		};
	}

}
