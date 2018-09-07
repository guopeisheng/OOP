package gp27_pg23.view;

import java.util.List;

import javax.swing.JPanel;

import common.IChatRoom;
import common.IUser;

/**
 * Main model adapter
 * @author Gao Pan
 *
 */
public interface IModelAdapter {
	/**
	 * Connect to another user
	 * @param addr IP Address
	 */
	public void connectPeer(String addr);

	/**
	 * Set user name
	 * @param name The user name
	 */
	public void setSelfName(String name);

	/**
	 * Create a new chat room
	 * @return The mini view
	 */
	public JPanel createCR();

	/**
	 * Fetch chat room list
	 * @param iPeer The user to query
	 * @return The list of chat room
	 */
	public List<IChatRoom> fetchCRList(IUser iPeer);

	/**
	 * Join a chat room
	 * @param iPeer The owner of that chat room
	 * @param cr The chat room to join
	 * @return The mini view
	 */
	public JPanel joinCR(IUser iPeer, IChatRoom cr);

	/**
	 * Get local IP address
	 * @return The ip address
	 */
	public String getIP();

	/**
	 * Get user name
	 * @return The user name
	 */
	public String getUserName();

	/**
	 * Leave a chat room
	 * @param jPanel The mini view
	 */
	public void leaveCR(JPanel jPanel);

	/**
	 * Exit
	 */
	public void shutdown();

	/**
	 * Send a data packet in chat room
	 * @param cr The chat room
	 * @param dpType The data type
	 * @param text The parameter
	 */
	public void sendDataPacket(IChatRoom cr, String dpType, String text);

}
