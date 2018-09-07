/**
 * 
 */
package gp27_pg23.model;

import java.awt.Component;
import java.util.List;
import java.util.UUID;

import common.IReceiver;
import gp27_pg23.model.miniModel.ChatRoom;
import gp27_pg23.model.miniModel.IChatWindowAdapter;
import gp27_pg23.model.miniView.ChatWindow;
import gp27_pg23.model.miniView.IChatRoomAdapter;

/**
 * Mini Controller for a chat room
 * @author Gao Pan
 *
 */
public class ChatRoomControl {

	/**
	 * The mini view
	 */
	private ChatWindow cw;

	/**
	 * Build the mini MVC
	 * @param cr The chat room - mini model
	 */
	public ChatRoomControl(ChatRoom cr) {
		cr.setAdpt(new IChatWindowAdapter() {

			@Override
			public void updateMsg(String msgText) {
				cw.updateMsg(msgText);

			}

			@Override
			public void popWindow(Component c) {
				cw.popWindow(c);
			}

			@Override
			public void updateUser(List<IReceiver> conns) {
				cw.updateUserList(conns);
			}

		});
		cw = new ChatWindow(new IChatRoomAdapter() {

			@Override
			public UUID getUUID() {
				return cr.getUUID();
			}

			@Override
			public String getTitle() {
				return cr.getName();
			}

			@Override
			public void sendDataPacket(String type, String content) {
				cr.sendDataPacket(type, content);

			}

		});
	}

	/**
	 * Start the view
	 */
	public void start() {
		cw.start();
	}

	/**
	 * Get the mini view
	 * @return The mini view
	 */
	public ChatWindow getChatWindow() {
		return cw;
	}

}
