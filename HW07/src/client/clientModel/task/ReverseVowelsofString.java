package client.clientModel.task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import provided.client.model.taskUtils.ITaskFactory;
import provided.compute.ILocalTaskViewAdapter;
import provided.compute.ITask;
import provided.compute.ITaskResultFormatter;

public class ReverseVowelsofString implements ITask<String> {

	/**
	 * UID for well-defined serialization
	 */
	private static final long serialVersionUID = -7551041113673208287L;

	/**
	 * parameter s->string
	 */
	private String s;

	/**
	 * task view adapter
	 */
	private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;

	/**
	 * constructor
	 * @param s->string
	 */
	public ReverseVowelsofString(String s) {
		this.s = s;
	}

	/**
	 * execution of the task
	 * @see provided.compute.ITask#execute()
	 */
	@Override
	public String execute() throws RemoteException {
		taskView.append("Calculating function PlusOne...");
		char[] list = s.toCharArray();
		Set<Character> set = new HashSet<>();
		set.add('a');
		set.add('e');
		set.add('i');
		set.add('o');
		set.add('u');
		set.add('A');
		set.add('E');
		set.add('I');
		set.add('O');
		set.add('U');
		for (int i = 0, j = list.length - 1; i < j;) {
			if (!set.contains(list[i])) {
				i++;
				continue;
			}
			if (!set.contains(list[j])) {
				j--;
				continue;
			}
			char temp = list[i];
			list[i] = list[j];
			list[j] = temp;
			i++;
			j--;
		}
		return String.valueOf(list);
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
	public static final ITaskFactory<String> FACTORY = new ITaskFactory<String>() {
		public ITask<String> make(String param) {
			return new ReverseVowelsofString(param);
		}

		public String toString() {
			return ReverseVowelsofString.class.getName();
		}
	};

	/**
	 * get the formatter of the result
	 * @see provided.compute.ITask#getFormatter()
	 */
	@Override
	public ITaskResultFormatter<String> getFormatter() {
		return new ITaskResultFormatter<String>() {

			public String format(String result) {
				return "Reverse Vowels of a String: " + s + " is became as: " + result;
			}
		};
	}

}
