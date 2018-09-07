/**
 * 
 */
package gp27_pg23.model.miniModel;

import java.awt.Component;
import java.util.List;

import common.IReceiver;

/**
 * Mini view adapter
 * @author Gao Pan
 *
 */
public interface IChatWindowAdapter {

	/**
	 * Append a message
	 * @param msgText The message
	 */
	void updateMsg(String msgText);

	/**
	 * Append a component
	 * @param c The component
	 */
	void popWindow(Component c);

	/**
	 * Refresh the user list
	 * @param conns The list of connected users
	 */
	void updateUser(List<IReceiver> conns);

}
