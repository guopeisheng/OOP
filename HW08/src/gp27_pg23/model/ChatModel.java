/**
 * 
 */
package gp27_pg23.model;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JPanel;

import common.IChatRoom;
import common.IUser;
import gp27_pg23.model.miniModel.ChatRoom;
import gp27_pg23.model.miniView.ChatWindow;
import provided.rmiUtils.IRMI_Defs;
import provided.rmiUtils.RMIUtils;

/**
 * Main model - top level of the logic
 * @author Gao Pan
 *
 */
public class ChatModel {

	/**
	 * RMI utility instance
	 */
	private RMIUtils _rmiUtil;

	/**
	 * Adapter to the view
	 */
	private IViewAdapter adpt;

	/**
	 * A stub of the user itself
	 */
	private IUser selfStub;

	/**
	 * The Remote object representing the user
	 */
	private Peer selfRemote;

	/**
	 * Local IP address for others to connect 
	 */
	private String localIP;

	/**
	 * Construct model with view adapter
	 * @param adpt View adapter
	 */
	public ChatModel(IViewAdapter adpt) {
		this.adpt = adpt;
	}

	/**
	 * Run the model - initilization
	 */
	public void start() {

		try {
			Consumer<String> welcomeMsg = new Consumer<String>() {
				@Override
				public void accept(String t) {
				}
			};
			_rmiUtil = new RMIUtils(welcomeMsg);
			_rmiUtil.startRMI(IRMI_Defs.CLASS_SERVER_PORT_CLIENT);
			localIP = _rmiUtil.getLocalAddress();
			selfRemote = new Peer(localIP, adpt);
			selfStub = (IUser) UnicastRemoteObject.exportObject(selfRemote, 0);
			Registry registry = _rmiUtil.getLocalRegistry();
			registry.rebind(IUser.BOUND_NAME, selfStub);

		} catch (Exception e) {
			System.err.println("start exception:");
			e.printStackTrace();
		}
	}

	/**
	 * Connect to another user using ip address
	 * @param addr The IP address
	 */
	public void connectPeer(String addr) {
		if (addr.equals(localIP)) {
			return;
		}

		try {
			Registry registry = _rmiUtil.getRemoteRegistry(addr);
			IUser remoteStub = (IUser) registry.lookup(IUser.BOUND_NAME);
			remoteStub.connect(selfStub);
			selfRemote.connect(remoteStub);

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Set user name
	 * @param name The new user name
	 */
	public void setSelfName(String name) {
		selfRemote.setUserName(name);
	}

	/**
	 * Create a new chat room
	 * @return The GUI of the new chat room
	 */
	public ChatWindow createCR() {
		return selfRemote.createCR();

	}

	/**
	 * Join a chat room
	 * @param iPeer The owner of the chat room
	 * @param cr The chat room to join
	 * @return The GUI of the new chat room
	 */
	public ChatWindow joinCR(IUser iPeer, IChatRoom cr) {
		return selfRemote.joinCR(iPeer, cr);
	}

	/**
	 * Fetch chat room list of a user
	 * @param iPeer The owner of chat rooms
	 * @return The chat rooms available
	 */
	public List<IChatRoom> fetchCRList(IUser iPeer) {
		List<IChatRoom> list = new ArrayList<IChatRoom>();
		try {
			Iterable<IChatRoom> rooms = iPeer.getChatRooms();
			for (IChatRoom cr : rooms) {
				list.add(cr);
			}
			return list;
		} catch (RemoteException e) {
			e.printStackTrace();
			return new ArrayList<IChatRoom>();
		}
	}

	/**
	 * Get local IP address
	 * @return IP address
	 */
	public String getIP() {
		try {
			return _rmiUtil.getLocalAddress();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "N/A";
	}

	/**
	 * Get the user name
	 * @return String The user name
	 */
	public String getUserName() {
		return selfRemote.getName();
	}

	/**
	 * Leave a chat room
	 * @param icr The chat room to leave
	 */
	public void leaveCR(JPanel icr) {
		selfRemote.leaveCR((ChatWindow) icr);
	}

	/**
	 * Shut down the application
	 */
	public void shutdown() {
		selfRemote.shutdown();

	}

	/**
	 * Send a data packet in a chat room
	 * @param icr The chat room
	 * @param dpType The data packet type 
	 * @param text The parameter for the packet
	 */
	public void sendDataPacket(IChatRoom icr, String dpType, String text) {
		((ChatRoom) icr).sendDataPacket(dpType, text);

	}

}
