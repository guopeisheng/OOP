/**
 * 
 */
package gp27_pg23.control;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JPanel;

import common.IChatRoom;
import common.IUser;
import gp27_pg23.model.ChatModel;
import gp27_pg23.model.IViewAdapter;
import gp27_pg23.model.miniView.ChatWindow;
import gp27_pg23.view.ChatView;
import gp27_pg23.view.IModelAdapter;

/**
 * The Main Controller
 * @author Gao Pan
 *
 */
public class ChatControl {
	/**
	 * Main Model
	 */
	private ChatModel mainModel;

	/**
	 * Main View
	 */
	private ChatView mainView;

	/**
	 * Build the main framework - main model and main view
	 */
	public ChatControl() {

		mainModel = new ChatModel(new IViewAdapter() {
			@Override
			public void updatePeers(List<IUser> connectedPeers) {
				mainView.updatePeers(connectedPeers);
			}
		});

		mainView = new ChatView(new IModelAdapter() {

			@Override
			public void connectPeer(String addr) {
				mainModel.connectPeer(addr);

			}

			@Override
			public void setSelfName(String name) {
				mainModel.setSelfName(name);

			}

			@Override
			public ChatWindow createCR() {
				return mainModel.createCR();

			}

			@Override
			public List<IChatRoom> fetchCRList(IUser iPeer) {
				return mainModel.fetchCRList(iPeer);
			}

			@Override
			public ChatWindow joinCR(IUser iPeer, IChatRoom icr) {
				return mainModel.joinCR(iPeer, icr);

			}

			@Override
			public String getIP() {
				return mainModel.getIP();
			}

			@Override
			public String getUserName() {

				return mainModel.getUserName();
			}

			@Override
			public void shutdown() {
				mainModel.shutdown();

			}

			@Override
			public void leaveCR(JPanel icr) {
				mainModel.leaveCR(icr);

			}

			@Override
			public void sendDataPacket(IChatRoom icr, String dpType, String text) {
				mainModel.sendDataPacket(icr, dpType, text);
			}

		});
	}

	/**
	 * Application Entry
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatControl mainCtrl = new ChatControl();
					mainCtrl.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Launch the application
	 */
	public void start() {
		mainModel.start();
		mainView.start();
	}
}
