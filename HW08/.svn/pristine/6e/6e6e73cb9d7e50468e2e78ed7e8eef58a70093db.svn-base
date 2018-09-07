/**
 * 
 */
package gp27_pg23.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import common.IChatRoom;
import common.IReceiver;
import gp27_pg23.model.miniModel.ChatRoom;
import gp27_pg23.model.miniModel.Connect;
import gp27_pg23.model.miniView.ChatWindow;

/**
 * Chat room manager hold and managing all the chat rooms
 * @author Gao Pan
 *
 */
public class ChatRoomManager {

	/**
	 * All connected chat rooms
	 */
	private transient List<ChatRoom> connCRs;

	/**
	 * The user
	 */
	private Peer user;

	/**
	 * Strong reference to all Receivers
	 */
	private Set<Connect> selfReceiverHolder;

	/**
	 * Construct with main view adapter and user
	 * @param user The user
	 */
	public ChatRoomManager(Peer user) {
		connCRs = new ArrayList<ChatRoom>();
		this.user = user;
		selfReceiverHolder = new HashSet<Connect>();
	}

	/**
	 * Get chat room list
	 * @return Chat room list
	 */
	public List<ChatRoom> getCRList() {
		return connCRs;
	}

	/**
	 * Leave a chat room
	 * @param cw The mini view of the chat room
	 */
	public void leaveCR(ChatWindow cw) {
		ChatRoom target = null;
		for (ChatRoom cr : connCRs) {
			if (cr.getUUID().equals(cw.getUUID())) {
				target = cr;
			}
		}
		if (target != null) {
			target.leave();
			connCRs.remove(target);
		}
	}

	/**
	 * Create a local copy of an existing chat room
	 * @param cr  Existing chat room
	 * @return The mini view
	 */
	public ChatWindow createCR(IChatRoom cr) {
		for (ChatRoom c : connCRs) {
			if (c.getUUID().equals(cr.getUUID()))
				return null;
		}
		return initCR(new ChatRoom(cr, this));
	}

	/**
	 * Create a new chat room
	 * @param userName THe user name to mark the chat room
	 * @return The mini view of the chat room
	 */
	public ChatWindow createCR(String userName) {
		return initCR(new ChatRoom(userName + ":" + connCRs.size(), this));
	}

	/**
	 * Initialize a chat room
	 * @param cr The chat room to initialize
	 * @return The mini view of the chat room
	 */
	private ChatWindow initCR(ChatRoom cr) {
		IReceiver conn = null;
		try {
			Connect new_conn = new Connect(cr, user);
			selfReceiverHolder.add(new_conn);
			conn = (IReceiver) UnicastRemoteObject.exportObject(new_conn, 0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (conn != null) {
			ChatRoomControl crc = new ChatRoomControl(cr);
			cr.setSelf(conn);
			connCRs.add(cr);
			return crc.getChatWindow();
			//adpt.addConnCR(cr);
		} else {
			return null;
		}
	}

	/**
	 * Leave all the chat room s
	 */
	public void shutdown() {
		for (ChatRoom cr : connCRs) {
			cr.leave();
		}

	}

	/**
	 * Get the chat room with a particular UUID
	 * @param uuid The UUID to match
	 * @return The chat room
	 */
	public ChatRoom getCR(UUID uuid) {
		for (ChatRoom cr : connCRs) {
			if (cr.getUUID().equals(uuid))
				return cr;
		}
		return null;
	}
}
