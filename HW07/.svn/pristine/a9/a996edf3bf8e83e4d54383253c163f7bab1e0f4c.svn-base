package client.clientModel.task;

import java.rmi.RemoteException;
import java.util.ArrayList;

import provided.compute.ILocalTaskViewAdapter;
import provided.compute.ITask;
import provided.compute.ITaskResultFormatter;

public class MultipleTask implements ITask<ArrayList<Object>> {

	/**
	 * UID for well-defined serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the first task to be combined
	 */
	private ITask<?> task1;

	/**
	 * the 2nd task to be combined
	 */
	private ITask<?> task2;

	/**
	 * the adapter which is not serialize
	 */
	private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;

	/**
	 * constructor
	 * @param task1
	 * @param task2
	 */
	public MultipleTask(ITask<?> task1, ITask<?> task2) {
		this.task1 = task1;
		this.task2 = task2;
	}

	/**
	 * the execution detail of the task, which is appending the two tasks into the view
	 * @see provided.compute.ITask#execute()
	 */
	@Override
	public ArrayList<Object> execute() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<Object> list = new ArrayList<Object>();
		taskView.append("MultipleTask execute: \n");
		taskView.append("(Executing task No.1...) \n");
		list.add(task1.execute());
		taskView.append("(Executing task No.2...) \n");
		list.add(task2.execute());
		return list;
	}

	/**
	 * set the task adapter
	 * @see provided.compute.ITask#setTaskViewAdapter(provided.compute.ILocalTaskViewAdapter)
	 */
	@Override
	public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
		// TODO Auto-generated method stub
		taskView = viewAdapter;
		task1.setTaskViewAdapter(viewAdapter);
		task2.setTaskViewAdapter(viewAdapter);
	}

	/**
	 * get the formatter obj
	 * @see provided.compute.ITask#getFormatter()
	 */
	@Override
	public ITaskResultFormatter<ArrayList<Object>> getFormatter() {
		// TODO Auto-generated method stub
		return new ITaskResultFormatter<ArrayList<Object>>() {
			@SuppressWarnings("unchecked")
			@Override
			public String format(ArrayList<Object> result) {
				ITaskResultFormatter<Object> f1 = (ITaskResultFormatter<Object>) task1.getFormatter();
				ITaskResultFormatter<Object> f2 = (ITaskResultFormatter<Object>) task2.getFormatter();
				return "MultiTask results:\n" + " No.1 " + f1.format(result.get(0)) + "\n" + " No.2 "
						+ f2.format(result.get(1));
			}
		};
	}

}
