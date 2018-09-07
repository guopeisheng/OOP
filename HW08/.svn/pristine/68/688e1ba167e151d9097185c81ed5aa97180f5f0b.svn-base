/**
 * 
 */
package gp27_pg23.model.miniModel;

import java.rmi.RemoteException;
import java.util.UUID;

import common.DataPacketChatRoom;
import common.IFailureStatusType;
import common.IReceiver;
import common.IUser;
import gp27_pg23.model.Peer;
import gp27_pg23.model.cmd.FailureStatusType;

/**
 * Concrete receiver
 * @author Gao Pan
 *
 */
public class Connect implements IReceiver {

	/**
	 * UUID
	 */
	private UUID uuid;

	/**
	 * Binding chat room
	 */
	private ChatRoom cr;

	/**
	 * The user
	 */
	private transient IUser user;

	/**
	 * Construct with the chat room and the user
	 * @param cr The chat room
	 * @param user The user
	 */
	public Connect(ChatRoom cr, Peer user) {
		this.uuid = UUID.randomUUID();
		this.cr = cr;
		this.user = user;
	}

	@Override
	public UUID getUUID() throws RemoteException {
		return uuid;
	}

	@Override
	public <T> void receive(DataPacketChatRoom<T> data) throws RemoteException {
		String rtn = data.execute(cr.getCmdMngr());
		if (rtn != null && rtn.compareTo("DEFAULT") == 0) {
			data.execute(cr.getCmdMngr());
		}
		if (rtn == null)
			data.getSender().receive(new DataPacketChatRoom<IFailureStatusType>(IFailureStatusType.class,
					new FailureStatusType(data, "Null returned"), cr.getSelfStub()));

	}

	@Override
	public IUser getUserStub() throws RemoteException {
		return user;
	}

}
