/**
 * 
 */
package gp27_pg23.model.cmd;

import java.rmi.RemoteException;

import common.DataPacketAlgoCmd;
import common.DataPacketChatRoom;
import common.ICmd2ModelAdapter;
import common.IRemoveReceiverType;
import gp27_pg23.model.miniModel.ChatRoom;

/**
 * Well-known dataType command - remove a receiver
 * @author Gao Pan
 *
 */
public class CmdCommRemoveReceiver extends DataPacketAlgoCmd<IRemoveReceiverType> {

	/**
	 * The binding chat room
	 */
	private transient ChatRoom cr;

	/**
	 * Construct with the chat room
	 * @param chatRoom The chat room
	 */
	public CmdCommRemoveReceiver(ChatRoom chatRoom) {
		this.cr = chatRoom;
	}

	/**
	 * UID
	 */
	private static final long serialVersionUID = -5473442460103576115L;

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

	}

	@Override
	public String apply(Class<?> index, DataPacketChatRoom<IRemoveReceiverType> host, String... params) {
		try {
			cr.removeConn(host.getData().getReceiverStub().getUUID());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return "Comm Remove Receiver";
	}
}
