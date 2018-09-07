/**
 * 
 */
package gp27_pg23.model.miniView;

import java.awt.Component;
import java.rmi.RemoteException;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import common.IReceiver;

/**
 * Customized list cell render
 * @author Gao Pan
 *
 */
public class IReceiverListCellRenderer extends DefaultListCellRenderer {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1357604528984677863L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		try {
			setText(((IReceiver) value).getUserStub().getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return c;
	}

}
