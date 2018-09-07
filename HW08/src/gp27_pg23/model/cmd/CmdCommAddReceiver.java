package gp27_pg23.model.cmd;

import common.DataPacketAlgoCmd;
import common.DataPacketChatRoom;
import common.IAddReceiverType;
import common.ICmd2ModelAdapter;
import gp27_pg23.model.miniModel.ChatRoom;

/**
 * Well-known dataType command - add a receiver
 * @author Gao Pan
 *
 */
public class CmdCommAddReceiver extends DataPacketAlgoCmd<IAddReceiverType> {

	/**
	 * The binding chat room
	 */
	private transient ChatRoom cr;

	/**
	 * Construct with the binding chat room
	 * @param chatRoom The chat room
	 */
	public CmdCommAddReceiver(ChatRoom chatRoom) {
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
	public String apply(Class<?> index, DataPacketChatRoom<IAddReceiverType> host, String... params) {
		cr.addConn(host.getData().getReceiverStub());
		return "Comm Add Receiver";
	}
}
