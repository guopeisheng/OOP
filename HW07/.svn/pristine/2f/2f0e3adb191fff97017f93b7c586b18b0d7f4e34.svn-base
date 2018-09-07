package client.clientView;

import javax.swing.*;

import provided.client.model.taskUtils.SingletonTaskFactoryLoader;

import java.awt.event.*;
import java.awt.*;

/**
	 * The view of the client MVC system.
	 */
public class ClientGUI<TUpdateDropListItem> extends JFrame {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The GUI panel
	 */
	private final JPanel panel = new JPanel();
	/**
	 * The upper panel
	 */
	private final JPanel pnlUpper = new JPanel();
	/**
	 * the btn to quit
	 */
	private final JButton btnQuit = new JButton("Quit");
	/**
	 * The panel for connection
	 */
	private final JPanel panel_1 = new JPanel();
	/**
	 * Textfile for IP address
	 */
	private final JTextField textField = new JTextField();
	/**
	 * btn for connectoin
	 */
	private final JButton btnConnect = new JButton("Connect");
	/**
	 * pnl to for inputing msg
	 */
	private final JPanel panel_2 = new JPanel();
	/**
	 * editor pnl to enter msg
	 */
	private final JEditorPane dtrpnHitEnterTo = new JEditorPane();
	/**
	 * pnl for adding task
	 */
	private final JPanel panel_3 = new JPanel();
	/**
	 * Text field to enter task
	 */
	private final JTextField textFieldAdd = new JTextField();
	/**
	 * btn to add item to list
	 */
	private final JButton btnAddToList = new JButton("Add to lists");
	/**
	 * pnl for combine & run
	 */
	private final JPanel panel_4 = new JPanel();
	/**
	 * btn for running the task
	 */
	private final JButton btnRunTask = new JButton("Run Task");
	/**
	 * JComboBox that store the tasks
	 */
	private final JComboBox<TUpdateDropListItem> runTaskCmb = new JComboBox<TUpdateDropListItem>();
	/**
	 * JComboBox to store 2nd task for combination
	 */
	private final JComboBox<TUpdateDropListItem> combineTaskCmb = new JComboBox<TUpdateDropListItem>();

	/**
	 * btn for combine tasks
	 */
	private final JButton btnCombineTasks = new JButton("Combine Tasks");
	/**
	 * pnl for collecting parameter
	 */
	private final JPanel panel_5 = new JPanel();
	/**
	 * text field for input param
	 */
	private final JTextField runParameterTxF = new JTextField();

	/**
	 * adapter to model
	 */
	private IModelAdapter<TUpdateDropListItem> _modelAdpt;

	/**
	 * text area for Output result 
	 */
	private final JTextArea textArea = new JTextArea();
	/**
	 * JScrollPane for Output result 
	 */
	private final JScrollPane scrollPane = new JScrollPane(textArea);

	/**
	 * Constructor of this view
	 * @param _modelAdpt
	 */
	public ClientGUI(IModelAdapter<TUpdateDropListItem> _modelAdpt) {
		this._modelAdpt = _modelAdpt;
		runParameterTxF.setToolTipText("give the parameter of the task");
		runParameterTxF.setText("0");
		runParameterTxF.setColumns(10);
		textFieldAdd.setToolTipText("add task to the list");
		textFieldAdd.setColumns(10);
		textField.setToolTipText("Set the location of the server");
		textField.setText("10.112.79.177");
		textField.setColumns(20);
		initGUI();
	}

	/**
	 * Initializes the view and its components.
	 */
	private void initGUI() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_modelAdpt.quit();
			}
		});

		setTitle("RMI Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(280, 100, 1054, 410);
		panel.setToolTipText("the hole panel");

		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		pnlUpper.setBackground(Color.LIGHT_GRAY);
		pnlUpper.setToolTipText("the upper panel");
		panel.add(pnlUpper, BorderLayout.NORTH);

		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_modelAdpt.quit();
			}
		});
		btnQuit.setToolTipText("Quit the program");

		pnlUpper.add(btnQuit);
		panel_1.setToolTipText("the panel of the remote host");

		pnlUpper.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		panel_1.add(textField);
		panel_1.setBorder(BorderFactory.createTitledBorder("Remote Host:"));
		btnConnect.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		btnConnect.setToolTipText("connect to the server");
		panel_1.add(btnConnect);
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		panel_2.setToolTipText("send message panel");
		panel_2.setBorder(BorderFactory.createTitledBorder("Send msg to remote host's view"));
		pnlUpper.add(panel_2);
		dtrpnHitEnterTo.setToolTipText("this is the message board");
		dtrpnHitEnterTo.setText("Hit Enter to send msg...");
		dtrpnHitEnterTo.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
					System.out.println("Pressed");
					sendText();
				}
			}
		});

		panel_2.add(dtrpnHitEnterTo);
		panel_3.setToolTipText("add to list panel");

		pnlUpper.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		panel_3.add(textFieldAdd);
		btnAddToList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TUpdateDropListItem o = _modelAdpt.addStrategy(textFieldAdd.getText());
				if (null == o)
					return; // just in case
				addToTUpdateDropListItem(o);

			}
		});
		btnAddToList.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAddToList.setToolTipText("add task to the list");
		panel_3.add(btnAddToList);

		GridBagConstraints gbc_runParameterTxF = new GridBagConstraints();
		gbc_runParameterTxF.fill = GridBagConstraints.HORIZONTAL;
		gbc_runParameterTxF.gridx = 0;
		gbc_runParameterTxF.gridy = 2;

		GridBagConstraints gbc_btnRunTask = new GridBagConstraints();
		gbc_btnRunTask.insets = new Insets(0, 0, 5, 0);
		gbc_btnRunTask.gridx = 0;
		gbc_btnRunTask.gridy = 2;
		btnRunTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TUpdateDropListItem taskname = (TUpdateDropListItem) runTaskCmb
						.getItemAt(runTaskCmb.getSelectedIndex());
				String runparameter = runParameterTxF.getText();
				String result = _modelAdpt.makeBall(taskname, runparameter);
				append("Task is finished and returned result is:" + result + "\n");
				//_modelAdpt.makeBall(runTaskCmb.getItemAt(runTaskCmb.getSelectedIndex()));
				//			    runTaskCmb.insertItemAt(o, 0);
				//				combineTaskCmb.insertItemAt(o, 0);	

			}
		});
		btnRunTask.setToolTipText("run the tasks");
		panel_4.add(btnRunTask, gbc_btnRunTask);
		panel_4.setToolTipText("run task panel");

		pnlUpper.add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 0 };
		gbl_panel_4.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_4.columnWeights = new double[] { 1.0 };
		gbl_panel_4.rowWeights = new double[] { Double.MIN_VALUE, 0.0, 0.0, 0.0 };
		panel_4.setLayout(gbl_panel_4);

		GridBagConstraints gbc_runTaskCmb = new GridBagConstraints();
		gbc_runTaskCmb.insets = new Insets(0, 0, 5, 0);
		gbc_runTaskCmb.fill = GridBagConstraints.HORIZONTAL;
		gbc_runTaskCmb.gridx = 0;
		gbc_runTaskCmb.gridy = 1;
		runTaskCmb.setToolTipText("run task combo box");
		panel_4.add(runTaskCmb, gbc_runTaskCmb);

		GridBagConstraints gbc_combineTaskCmb = new GridBagConstraints();
		gbc_combineTaskCmb.insets = new Insets(0, 0, 5, 0);
		gbc_combineTaskCmb.fill = GridBagConstraints.HORIZONTAL;
		gbc_combineTaskCmb.gridx = 0;
		gbc_combineTaskCmb.gridy = 2;
		combineTaskCmb.setToolTipText("combine task combo box");
		panel_4.add(combineTaskCmb, gbc_combineTaskCmb);

		GridBagConstraints gbc_btnCombineTasks = new GridBagConstraints();
		gbc_btnCombineTasks.gridx = 0;
		gbc_btnCombineTasks.gridy = 3;
		btnCombineTasks.setToolTipText("Combine the tasks");
		btnCombineTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//				TUpdateDropListItem o = runTaskCmb.getItemAt(runTaskCmb.getSelectedIndex());
				//				TUpdateDropListItem o2 = combineTaskCmb.getItemAt(combineTaskCmb.getSelectedIndex());
				//				if (o == null || o2 == null)
				//					return;
				//				TUpdateDropListItem Combine = _modelAdpt.combineStrategies(o, o2);
				////				runTaskCmb.insertItemAt(Combine, 0);
				////				combineTaskCmb.insertItemAt(Combine, 0);
				TUpdateDropListItem o = _modelAdpt.combineBall(runTaskCmb.getItemAt(runTaskCmb.getSelectedIndex()),
						combineTaskCmb.getItemAt(combineTaskCmb.getSelectedIndex()));
				if (o == null) {
					return;
				}
				addToTUpdateDropListItem(o);

			}
		});
		panel_4.add(btnCombineTasks, gbc_btnCombineTasks);
		panel_5.setToolTipText("run parameter panel");
		panel_5.setBorder(BorderFactory.createTitledBorder("Run Parameter:"));
		panel_5.add(runParameterTxF, gbc_runParameterTxF);
		pnlUpper.add(panel_5);

		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 1;
		panel.add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * Starts the view by making it visible.
	 */
	public void start() {
		setVisible(true);
	}

	public void addToTUpdateDropListItem(TUpdateDropListItem o) {
		runTaskCmb.insertItemAt(o, 0);
		combineTaskCmb.insertItemAt(o, 0);
		runTaskCmb.setSelectedIndex(0);
		combineTaskCmb.setSelectedIndex(0);
	}

	/**
	 * Have the model connect to the remote server.
	 */
	private void connect() {
		append("Connecting...\n");
		append(_modelAdpt.connectTo(textField.getText()) + "\n");
	}

	public void append(String s) {
		textArea.append(s);
		textArea.setCaretPosition(textArea.getText().length());
	}

	/**
	 * Set the displayed remote host text field to the actual remote system's IP address or host name
	 * @param host
	 */
	public void setRemoteHost(String host) {
		textField.setText(host);
	}

	/**
	 * An general purpose input text field used to input parameters to send with the task executions.
	 */
	private void sendText() {
		_modelAdpt.sendMessageToServer(dtrpnHitEnterTo.getText());
	}

}
