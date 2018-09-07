package server.model;

/**
 * Adapter the model uses to communicate to the view
 *
 */
public interface IViewAdapter {

	/**
	* Appends the string to the end of the text display on the view
	* @param s
	*/
	public void append(String s);
}