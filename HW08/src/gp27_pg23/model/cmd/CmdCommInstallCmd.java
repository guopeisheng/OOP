package gp27_pg23.model.cmd;

import common.DataPacketAlgoCmd;
import common.DataPacketChatRoom;
import common.ICmd2ModelAdapter;
import common.IInstallCmdType;
import common.IReceiver;
import provided.datapacket.DataPacketAlgo;

/**
 * Well-known dataType command - install a command
 * @author Gao Pan
 *
 */
public class CmdCommInstallCmd extends DataPacketAlgoCmd<IInstallCmdType> {

	/**
	 * The command manager to install command in
	 */
	private DataPacketAlgo<String, String> cmdMngr;

	/**
	 * The command to model adapter
	 */
	private transient ICmd2ModelAdapter adpt;

	/**
	 * Construct with the command manager, the receiver and command to model adapter
	 * @param cmdMngr The command manager
	 * @param recv The receiver
	 * @param c2madpt The command to model adapter
	 */
	public CmdCommInstallCmd(DataPacketAlgo<String, String> cmdMngr, IReceiver recv, ICmd2ModelAdapter c2madpt) {
		this.cmdMngr = cmdMngr;
		this.adpt = c2madpt;
	}

	/**
	 * UID
	 */
	private static final long serialVersionUID = -5473442460103576115L;

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.adpt = cmd2ModelAdpt;
	}

	@Override
	public String apply(Class<?> index, DataPacketChatRoom<IInstallCmdType> host, String... params) {
		DataPacketAlgoCmd<?> cmd = host.getData().getCmd();
		if (cmd == null)
			return null;
		cmd.setCmd2ModelAdpt(adpt);
		cmdMngr.setCmd(host.getData().getCmdId(), cmd);
		return "Comm Install Cmd";
	}

}
