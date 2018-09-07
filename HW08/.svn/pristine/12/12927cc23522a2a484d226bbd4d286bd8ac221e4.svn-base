package gp27_pg23.model.cmd;

import java.rmi.RemoteException;

import common.DataPacketAlgoCmd;
import common.DataPacketChatRoom;
import common.ICmd2ModelAdapter;
import common.IInstallCmdType;
import common.IReceiver;
import common.IRequestCmdType;
import provided.datapacket.DataPacketAlgo;

/**
 * Well-known dataType command - Request a command for unknown type
 * @author Gao Pan
 *
 */
public class CmdCommRequestCmd extends DataPacketAlgoCmd<IRequestCmdType> {

	/**
	 * The receiver
	 */
	private transient IReceiver recv;

	/**
	 * The command manager
	 */
	private DataPacketAlgo<String, String> cmdMngr;

	/**
	 * Construct with the command manager and the receiver
	 * @param cmdMngr The command manger
	 * @param recv The receiver
	 */
	public CmdCommRequestCmd(DataPacketAlgo<String, String> cmdMngr, IReceiver recv) {
		this.recv = recv;
		this.cmdMngr = cmdMngr;
	}

	/**
	 * UID
	 */
	private static final long serialVersionUID = -5473442460103576115L;

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

	}

	@Override
	public String apply(Class<?> index, DataPacketChatRoom<IRequestCmdType> host, String... params) {
		DataPacketAlgoCmd<?> cmd = (DataPacketAlgoCmd<?>) cmdMngr.getCmd(host.getData().getCmdId());
		try {
			host.getSender()
					.receive(new DataPacketChatRoom<IInstallCmdType>(IInstallCmdType.class, new IInstallCmdType() {

						private static final long serialVersionUID = 1L;

						@Override
						public DataPacketAlgoCmd<?> getCmd() {
							return cmd;
						}

						@Override
						public Class<?> getCmdId() {
							return host.getData().getCmdId();
						}

					}, recv));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return "Comm Request Cmd";
	}
}
