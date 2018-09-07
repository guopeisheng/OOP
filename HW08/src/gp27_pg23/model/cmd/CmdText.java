package gp27_pg23.model.cmd;

import common.DataPacketAlgoCmd;
import common.DataPacketChatRoom;
import common.ICmd2ModelAdapter;

/**
 * Command to TextType
 * @author Gao Pan
 *
 */
public class CmdText extends DataPacketAlgoCmd<TextType> {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -3838770341127745921L;

	/**
	 * Command to model adapter
	 */
	private transient ICmd2ModelAdapter adpt;

	/**
	 * Contruct with command to model adapter
	 * @param iCmd2ModelAdapter The command to model adapter
	 */
	public CmdText(ICmd2ModelAdapter iCmd2ModelAdapter) {
		this.adpt = iCmd2ModelAdapter;
	}

	@Override
	public String apply(Class<?> index, DataPacketChatRoom<TextType> host, String... params) {
		adpt.appendMsg(host.getData().getMsg(), "Text");
		return host.getData().getMsg();
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter adpt) {
		this.adpt = adpt;
	}
}
