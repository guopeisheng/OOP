/**
 * 
 */
package gp27_pg23.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import common.IChatRoom;
import common.IUser;
import gp27_pg23.model.miniView.ChatWindow;

/**
 * The user
 * @author Gao Pan
 *
 */
public class Peer implements IUser {

	/**
	 * Connected user list
	 */
	private List<IUser> connectedPeers;

	/**
	 * Main view adapter
	 */
	private transient IViewAdapter adpt;

	/**
	 * The user name
	 */
	private String userName;

	/**
	 * The user's UUID
	 */
	private UUID uuid;

	/**
	 * The chat room manager
	 */
	private transient ChatRoomManager crMngr;

	/**
	 * Construct with name and view adapter
	 * @param name The user name
	 * @param adpt The main view adapter
	 */
	public Peer(String name, IViewAdapter adpt) {
		uuid = UUID.randomUUID();
		connectedPeers = new ArrayList<IUser>();
		crMngr = new ChatRoomManager(this);
		this.adpt = adpt;
		userName = name;
	}

	/**
	 * Set the user name
	 * @param name The new user name
	 */
	public void setUserName(String name) {
		userName = name;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public String getName() {
		return userName;
	}

	@Override
	public void connect(IUser peer) {
		IUser target = null;
		boolean exists = false;
		for (IUser p : connectedPeers) {
			try {
				if (p.getUUID().equals(peer.getUUID())) {
					target = p;
					exists = true;
					break;
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if (target != null)
			connectedPeers.remove(target);
		if (!exists) {
			connectedPeers.add(peer);
			adpt.updatePeers(connectedPeers);
		}
	}

	@Override
	public List<IChatRoom> getChatRooms() {
		return new ArrayList<IChatRoom>(crMngr.getCRList());
	}

	/**
	 * Leave a chat rooom
	 * @param cw The mini view of the chat room to leave
	 */
	public void leaveCR(ChatWindow cw) {
		crMngr.leaveCR(cw);
	}

	/**
	 * Create a new chat room
	 * @return The mini view of the new chat room
	 */
	public ChatWindow createCR() {
		return crMngr.createCR(userName);
	}

	/**
	 * Join a chat room 
	 * @param iPeer The user having the chat room
	 * @param cr The chat room to join
	 * @return The mini view of the new chat room
	 */
	public ChatWindow joinCR(IUser iPeer, IChatRoom cr) {
		Collection<IChatRoom> clcRooms = null;
		try {
			clcRooms = iPeer.getChatRooms();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		if (clcRooms == null)
			return null;
		List<IChatRoom> crList = new ArrayList<IChatRoom>();
		for (IChatRoom tcr : clcRooms) {
			crList.add(tcr);
		}
		if (crList != null) {
			IChatRoom targetCR = null;
			for (IChatRoom c : crList) {
				if (c.getUUID().equals(cr.getUUID()))
					targetCR = c;
			}
			if (targetCR != null) {
				//crMngr.createCR(new ChatRoom(targetCR,cmdMngr));
				return crMngr.createCR(targetCR);
			}
		}
		return null;
	}

	/**
	 * Close
	 */
	public void shutdown() {
		crMngr.shutdown();
		/*
		for(IUser p:connectedPeers) {
				try {
					p.disconnect(uuid);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
		}
		*/
	}

	/*
		public void disconnect(UUID uuid) throws RemoteException {
			IUser target =null;
			for(IUser p:connectedPeers) {
				if(p.getUUID().equals(uuid)) {
					target = p;
					break;
				}
			}
			if(target!=null) {
				connectedPeers.remove(target);
				adpt.updatePeers(connectedPeers);
			}
			
		}
	*/

}
