package gp27_pg23.model.cmd;

import java.awt.Component;

import javax.swing.JLabel;

import common.DataPacketAlgoCmd;
import common.DataPacketChatRoom;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;

/**
 * Command to ImageType
 * @author Gao Pan
 *
 */
public class CmdImage extends DataPacketAlgoCmd<ImageType> {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -3838770341127745921L;

	/**
	 * command to model adapter
	 */
	private transient ICmd2ModelAdapter adpt;

	/**
	 * Construct with command to model adapter
	 * @param c2madpt The command to model adapter
	 */
	public CmdImage(ICmd2ModelAdapter c2madpt) {
		this.adpt = c2madpt;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter adpt) {
		this.adpt = adpt;

	}

	@Override
	public String apply(Class<?> index, DataPacketChatRoom<ImageType> host, String... params) {
		adpt.buildScrollableComponent(new IComponentFactory() {
			@Override
			public Component makeComponent() {
				JLabel lblImg = new JLabel();
				lblImg.setIcon(host.getData().getImage());
				return lblImg;
			}

		}, "Image");

		return "Cmd Image";
	}

}
