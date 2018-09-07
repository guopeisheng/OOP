package gp27_pg23.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.IChatRoom;
import common.IUser;

import javax.swing.JList;
import javax.swing.DefaultListModel;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.awt.SystemColor;

/**
 * The main view
 * @author Gao Pan
 *
 */
public class ChatView extends JFrame {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -7777480934061132979L;
	private IModelAdapter adpt;
	private JPanel contentPane;
	private DefaultListModel<IChatRoom> avaCRsModel = new DefaultListModel<IChatRoom>();
	private DefaultListModel<IUser> connPeersModel = new DefaultListModel<IUser>();
	private DefaultListModel<JPanel> connCRsModel = new DefaultListModel<JPanel>();
	private final JPanel panel = new JPanel();
	private final JTextField txfUserName = new JTextField();
	private final JLabel lblIp = new JLabel("Local IP Address");
	private final JList<IChatRoom> lstCR = new JList<IChatRoom>(avaCRsModel);
	private final JButton btnJoin = new JButton("Join");
	private final JTextField txfPeerAddr = new JTextField();
	private final JPanel pnlConn = new JPanel();
	private final JList<IUser> lstConnPeer = new JList<IUser>(connPeersModel);
	private final JList<JPanel> lstConnCR = new JList<JPanel>(connCRsModel);
	private final JLabel lblIpAddr = new JLabel("N/A");
	private final JButton btnCreateCR = new JButton("Create Chatroom");
	private final JLabel lblUserName = new JLabel("User Name");
	private final JButton btnLeaveCR = new JButton("Leave Chatroom");
	private final JPanel pnlWork = new JPanel();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JScrollPane scrollPane_2 = new JScrollPane();
	private final JLabel lblConnectedUser = new JLabel("Connected User");
	private final JLabel lblAvailableChatRooms = new JLabel("Available Chat Rooms");
	private final JLabel lblLocalIpTip = new JLabel("Give others for connection");
	private final JLabel lblNewLabel = new JLabel("Your user name. Press Enter to apply change");
	private final JTextArea txtrInputIpAddress = new JTextArea();
	private final JTextArea txtrSelectAUser = new JTextArea();

	/**
	 * Start the main view
	 */
	public void start() {
		setVisible(true);
		txfUserName.setText(adpt.getIP());
		lblIpAddr.setText(adpt.getIP());
	}

	/**
	 * Create the frame.
	 * @param adpt  The main model adapter
	 */
	public ChatView(IModelAdapter adpt) {

		this.adpt = adpt;
		initGUI();
	}

	/**
	 * Initialize GUI
	 */
	private void initGUI() {
		setTitle("ChatApp");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				adpt.shutdown();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		panel.setFont(new Font("Andalus", Font.PLAIN, 12));

		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 107, 210, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 36, 29, 0, 0, 25, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.fill = GridBagConstraints.BOTH;
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		lblIp.setToolTipText("Local IP Address");
		lblIp.setFont(new Font("Andalus", Font.BOLD, 14));
		panel.add(lblIp, gbc_lblIp);

		GridBagConstraints gbc_lblIpAddr = new GridBagConstraints();
		gbc_lblIpAddr.insets = new Insets(0, 0, 5, 5);
		gbc_lblIpAddr.gridx = 1;
		gbc_lblIpAddr.gridy = 0;
		lblIpAddr.setToolTipText("Your local IP Address");
		lblIpAddr.setFont(new Font("Andalus", Font.PLAIN, 14));
		panel.add(lblIpAddr, gbc_lblIpAddr);

		GridBagConstraints gbc_lblLocalIpTip = new GridBagConstraints();
		gbc_lblLocalIpTip.gridwidth = 2;
		gbc_lblLocalIpTip.fill = GridBagConstraints.VERTICAL;
		gbc_lblLocalIpTip.insets = new Insets(0, 0, 5, 0);
		gbc_lblLocalIpTip.gridx = 2;
		gbc_lblLocalIpTip.gridy = 0;
		lblLocalIpTip.setToolTipText("Give others for connection");
		lblLocalIpTip.setFont(new Font("Andalus", Font.PLAIN, 14));
		panel.add(lblLocalIpTip, gbc_lblLocalIpTip);

		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.fill = GridBagConstraints.BOTH;
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 1;
		lblUserName.setToolTipText("User Name");
		lblUserName.setFont(new Font("Andalus", Font.BOLD, 14));
		panel.add(lblUserName, gbc_lblUserName);
		txfUserName.setToolTipText("Your User Name");
		txfUserName.setFont(new Font("Andalus", Font.PLAIN, 14));
		txfUserName.setHorizontalAlignment(SwingConstants.CENTER);
		txfUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				txfUserName.setText(adpt.getUserName());
			}
		});
		txfUserName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txfUserName.getText().isEmpty()) {
					adpt.setSelfName(txfUserName.getText());
				}
				txfUserName.setText(adpt.getUserName());
				btnCreateCR.grabFocus();
			}
		});
		txfUserName.setColumns(10);

		GridBagConstraints gbc_txfUserName = new GridBagConstraints();
		gbc_txfUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfUserName.insets = new Insets(0, 0, 5, 5);
		gbc_txfUserName.gridx = 1;
		gbc_txfUserName.gridy = 1;
		panel.add(txfUserName, gbc_txfUserName);

		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		lblNewLabel.setToolTipText("Your user name. Press Enter to apply change");
		lblNewLabel.setFont(new Font("Andalus", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, gbc_lblNewLabel);

		GridBagConstraints gbc_lblConnectedUser = new GridBagConstraints();
		gbc_lblConnectedUser.fill = GridBagConstraints.VERTICAL;
		gbc_lblConnectedUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblConnectedUser.gridx = 1;
		gbc_lblConnectedUser.gridy = 2;
		lblConnectedUser.setToolTipText("Connected User");
		lblConnectedUser.setFont(new Font("Andalus", Font.BOLD, 14));
		panel.add(lblConnectedUser, gbc_lblConnectedUser);

		GridBagConstraints gbc_lblAvailableChatRooms = new GridBagConstraints();
		gbc_lblAvailableChatRooms.insets = new Insets(0, 0, 5, 5);
		gbc_lblAvailableChatRooms.gridx = 3;
		gbc_lblAvailableChatRooms.gridy = 2;
		lblAvailableChatRooms.setToolTipText("Available Chat Rooms");
		lblAvailableChatRooms.setFont(new Font("Andalus", Font.BOLD, 14));
		panel.add(lblAvailableChatRooms, gbc_lblAvailableChatRooms);

		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 3;
		panel.add(scrollPane_2, gbc_scrollPane_2);
		lstConnPeer.setToolTipText("Connected User List");
		lstConnPeer.setFont(new Font("Andalus", Font.PLAIN, 12));
		lstConnPeer.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				new LineBorder(new Color(0, 0, 0))));
		scrollPane_2.setViewportView(lstConnPeer);
		lstConnPeer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateAvaCR();

			}
		});
		lstConnPeer.setCellRenderer(new IPeerListCellRenderer());

		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 3;
		gbc_scrollPane_1.gridy = 3;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		lstCR.setFont(new Font("Andalus", Font.PLAIN, 12));
		lstCR.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				new LineBorder(new Color(0, 0, 0))));
		scrollPane_1.setViewportView(lstCR);

		GridBagConstraints gbc_btnJoin = new GridBagConstraints();
		gbc_btnJoin.fill = GridBagConstraints.BOTH;
		gbc_btnJoin.gridx = 3;
		gbc_btnJoin.gridy = 4;
		btnJoin.setToolTipText("Join the chat room");
		btnJoin.setFont(new Font("Andalus", Font.PLAIN, 12));
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!lstCR.isSelectionEmpty()) {
					JPanel rtn = adpt.joinCR(connPeersModel.getElementAt(lstConnPeer.getSelectedIndex()),
							avaCRsModel.getElementAt(lstCR.getSelectedIndex()));
					if (rtn != null)
						connCRsModel.addElement(rtn);
				}
			}
		});
		txfPeerAddr.setToolTipText("Input IP Address and Enter");
		txfPeerAddr.setHorizontalAlignment(SwingConstants.CENTER);
		txfPeerAddr.setFont(new Font("Andalus", Font.PLAIN, 12));
		txfPeerAddr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txfPeerAddr.getText().isEmpty()) {
					adpt.connectPeer(txfPeerAddr.getText());
					txfPeerAddr.setText("");
				}
			}
		});

		GridBagConstraints gbc_txtrInputIpAddress = new GridBagConstraints();
		gbc_txtrInputIpAddress.gridheight = 3;
		gbc_txtrInputIpAddress.insets = new Insets(0, 0, 0, 5);
		gbc_txtrInputIpAddress.gridx = 0;
		gbc_txtrInputIpAddress.gridy = 2;
		txtrInputIpAddress.setToolTipText("Input IP address and Press enter to connect to other users");
		txtrInputIpAddress.setEditable(false);
		txtrInputIpAddress.setBackground(SystemColor.control);
		txtrInputIpAddress.setWrapStyleWord(true);
		txtrInputIpAddress.setFont(new Font("Andalus", Font.PLAIN, 13));
		txtrInputIpAddress.setLineWrap(true);
		txtrInputIpAddress.setText("Input IP address and Press enter to connect to other users");
		panel.add(txtrInputIpAddress, gbc_txtrInputIpAddress);
		txfPeerAddr.setColumns(10);

		GridBagConstraints gbc_txfPeerAddr = new GridBagConstraints();
		gbc_txfPeerAddr.insets = new Insets(0, 0, 0, 5);
		gbc_txfPeerAddr.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfPeerAddr.gridx = 1;
		gbc_txfPeerAddr.gridy = 4;
		panel.add(txfPeerAddr, gbc_txfPeerAddr);

		GridBagConstraints gbc_txtrSelectAUser = new GridBagConstraints();
		gbc_txtrSelectAUser.gridheight = 3;
		gbc_txtrSelectAUser.insets = new Insets(0, 0, 0, 5);
		gbc_txtrSelectAUser.gridx = 2;
		gbc_txtrSelectAUser.gridy = 2;
		txtrSelectAUser.setToolTipText(
				"Select a user at left to browse its chat room(s) available. Pick a chat room and click the button to join");
		txtrSelectAUser.setEditable(false);
		txtrSelectAUser.setBackground(SystemColor.control);
		txtrSelectAUser.setWrapStyleWord(true);
		txtrSelectAUser.setText(
				"Select a user at left to browse its chat room(s) available. Pick a chat room and click the button to join");
		txtrSelectAUser.setLineWrap(true);
		txtrSelectAUser.setFont(new Font("Andalus", Font.PLAIN, 13));
		panel.add(txtrSelectAUser, gbc_txtrSelectAUser);
		panel.add(btnJoin, gbc_btnJoin);

		contentPane.add(pnlConn, BorderLayout.WEST);
		GridBagLayout gbl_pnlConn = new GridBagLayout();
		gbl_pnlConn.columnWidths = new int[] { 81, 0 };
		gbl_pnlConn.rowHeights = new int[] { 0, 0, 218, 0 };
		gbl_pnlConn.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlConn.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		pnlConn.setLayout(gbl_pnlConn);

		GridBagConstraints gbc_btnCreateCR = new GridBagConstraints();
		gbc_btnCreateCR.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCreateCR.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreateCR.gridx = 0;
		gbc_btnCreateCR.gridy = 0;
		btnCreateCR.setToolTipText("Create a chat room");
		btnCreateCR.setFont(new Font("Andalus", Font.PLAIN, 12));
		btnCreateCR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connCRsModel.addElement(adpt.createCR());

			}
		});
		pnlConn.add(btnCreateCR, gbc_btnCreateCR);

		GridBagConstraints gbc_lstConnCR = new GridBagConstraints();
		gbc_lstConnCR.fill = GridBagConstraints.BOTH;
		gbc_lstConnCR.gridx = 0;
		gbc_lstConnCR.gridy = 2;
		lstConnCR.setToolTipText("Joined chat room list");
		lstConnCR.setFont(new Font("Andalus", Font.PLAIN, 12));
		lstConnCR.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				new LineBorder(new Color(0, 0, 0))));
		lstConnCR.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				pnlWork.removeAll();
				if (!lstConnCR.isSelectionEmpty()) {
					pnlWork.add(BorderLayout.CENTER, connCRsModel.getElementAt(lstConnCR.getSelectedIndex()));

				}
				pnlWork.revalidate();
				pnlWork.repaint();

			}
		});

		GridBagConstraints gbc_btnLeaveCR = new GridBagConstraints();
		gbc_btnLeaveCR.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLeaveCR.insets = new Insets(0, 0, 5, 0);
		gbc_btnLeaveCR.gridx = 0;
		gbc_btnLeaveCR.gridy = 1;
		btnLeaveCR.setToolTipText("Leave the chat room");
		btnLeaveCR.setFont(new Font("Andalus", Font.PLAIN, 12));
		btnLeaveCR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!lstConnCR.isSelectionEmpty()) {
					adpt.leaveCR(connCRsModel.getElementAt(lstConnCR.getSelectedIndex()));
					connCRsModel.removeElementAt(lstConnCR.getSelectedIndex());
				}
			}
		});
		pnlConn.add(btnLeaveCR, gbc_btnLeaveCR);
		pnlConn.add(lstConnCR, gbc_lstConnCR);
		pnlWork.setToolTipText("Chat Window");

		pnlWork.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(0, 0, 0))));
		contentPane.add(pnlWork, BorderLayout.CENTER);
		pnlWork.setLayout(new BorderLayout(0, 0));
	}

	/**
	 * Update available chat room list
	 */
	private void updateAvaCR() {
		avaCRsModel.clear();
		if (!lstConnPeer.isSelectionEmpty()) {
			List<IChatRoom> crList = adpt.fetchCRList(connPeersModel.get(lstConnPeer.getSelectedIndex()));
			for (IChatRoom cr : crList) {
				avaCRsModel.addElement(cr);
			}
		}
	}

	/**
	 * Update connected chat room list 
	 * @param lst List of mini views
	 */
	public void updateCRs(List<JPanel> lst) {
		connCRsModel.clear();
		for (JPanel cr : lst) {
			connCRsModel.addElement(cr);
		}

	}

	/**
	 * Update connected users' list
	 * @param connectedPeers List of users connected
	 */
	public void updatePeers(List<IUser> connectedPeers) {
		connPeersModel.clear();
		for (IUser p : connectedPeers) {
			connPeersModel.addElement(p);
		}
		updateAvaCR();
	}

}
