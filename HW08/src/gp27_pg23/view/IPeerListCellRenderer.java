package gp27_pg23.view;

import java.awt.Component;
import java.rmi.RemoteException;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import common.IUser;

/**
 * Customized list cell render
 * @author Gao Pan
 *
 */
public class IPeerListCellRenderer extends DefaultListCellRenderer {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 2300251271757278763L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		try {
			setText(((IUser) value).getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return c;
	}

}
