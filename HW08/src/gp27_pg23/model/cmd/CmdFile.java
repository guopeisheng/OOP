package gp27_pg23.model.cmd;

import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JLabel;

import common.DataPacketAlgoCmd;
import common.DataPacketChatRoom;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;

/**
 * Command for FileType
 * @author Gao Pan
 *
 */
public class CmdFile extends DataPacketAlgoCmd<FileType> {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -3838770341127745921L;

	/**
	 * Command to model adapter
	 */
	private transient ICmd2ModelAdapter adpt;

	/**
	 * Construct with the command to model adapter
	 * @param c2madpt The command to model adapter
	 */
	public CmdFile(ICmd2ModelAdapter c2madpt) {
		this.adpt = c2madpt;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter adpt) {
		this.adpt = adpt;

	}

	@Override
	public String apply(Class<?> index, DataPacketChatRoom<FileType> host, String... params) {
		File dlDir = new File("download/");
		dlDir.mkdirs();
		File of = new File("download/" + new String(host.getData().getFile()[0]));
		if (!of.exists()) {
			try {
				of.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(of);
			fos.write(host.getData().getFile()[1]);

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		adpt.buildScrollableComponent(new IComponentFactory() {
			@Override
			public Component makeComponent() {
				return new JLabel("File received:[download/" + of.getName() + "]");
			}

		}, "File");

		return "Cmd File";
	}

}
