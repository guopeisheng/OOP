package client.clientView;

public interface IModelAdapter<TUpdateDropListItem> {

	/**
	 * connect to the remote host with the given name
	 * @param remoteHost
	 * @return
	 */
	public String connectTo(String remoteHost);

	/**
	 * stop RMI
	 */
	public void quit();

	/**
	 * @param text
	 */
	public void sendMessageToServer(String text);

	/**
	 * The adapter that connects all the way back to the remote system's view enabling this client to append messages to the server's view.
	 */
	public String makeBall(TUpdateDropListItem classname, String runparameter);

	/**
	 * add item into Jcombobox
	 * @param classname
	 * @return
	 */
	public TUpdateDropListItem addStrategy(String classname);

	/**
	 * combine two tasks
	 * @param task1
	 * @param task2
	 * @return
	 */
	public TUpdateDropListItem combineBall(TUpdateDropListItem task1, TUpdateDropListItem task2);
}
